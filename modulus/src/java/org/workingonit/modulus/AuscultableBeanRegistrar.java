/*
 * Copyright (C) 2008-2010 Vladimir Ritz Bossicard
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.workingonit.modulus;

import static org.workingonit.modulus.annotation.Group.NO_GROUPNAME;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.management.MBeanServer;
import javax.management.ObjectName;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.workingonit.modulus.annotation.Group;
import org.workingonit.modulus.findings.Finding.Status;

/**
 * @author Vladimir Ritz Bossicard
 */
@ManagedResource(
    description="Examination Service",
    objectName="org.workingonit.modulus:name=ExaminationMBean")
public class AuscultableBeanRegistrar implements BeanPostProcessor, ApplicationListener<ContextRefreshedEvent>, BeanFactoryAware {

    private final static Log logger = LogFactory.getLog(AuscultableBeanRegistrar.class);

    /**
     * Name under which this bean will be registered on the MBeanServer.
     */
    private String prefix = "org.workingonit.modulus.availability";

    private Platform platform;
    private Map<String, PlatformGroup> groups = new HashMap<String, PlatformGroup>();

    /**
     * Cached {@link Examination} to prevent potential expensive checks to be
     * done each time the service is called.
     */
    private Examination lastExamination = null;

    private BeanFactory factory;
    private MBeanServer server;

    @Override
    public void setBeanFactory(BeanFactory factory) throws BeansException {
        this.factory = factory;
    }

    @Required
    public void setMbeanServer(MBeanServer server) {
        this.server = server;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName)
            throws BeansException {
        if (bean == this) {
            return bean;
        }

        if (bean instanceof Platform) {
            this.platform = (Platform) bean;
            if (logger.isInfoEnabled()) {
                logger.info("Platform bean '" + this.platform.getName() + "' was registered");
            }
        } else if (bean instanceof AuscultableBean) {
            System.out.println("postProcessAfterInitialization");
            if (logger.isInfoEnabled()) {
                logger.info("AuscultableBean bean '" + beanName + "' was registered");
            }

            // find the group name
            Group annotation = ((ListableBeanFactory) this.factory).findAnnotationOnBean(beanName, Group.class);
            String groupName = annotation != null ? annotation.name() : Group.NO_GROUPNAME;

            // System.out.println("finding group name '" + groupName + "'");

            PlatformGroup group = this.groups.get(groupName);
            if (group == null) {
                group = new PlatformGroup(groupName);
                group.beans.add((AuscultableBean) bean);
                this.groups.put(groupName, group);
            } else {
                group.beans.add((AuscultableBean) bean);
                this.groups.put(groupName, group);
            }
        }

        return bean;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName)
            throws BeansException {
        return bean;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        /*
         * Once everything has been registered, the next thing to do is to
         * register the JMX beans automatically.
         */
        for (PlatformGroup group : this.groups.values()) {
            System.out.println("registring MBean for " + group.name);
            try {
                String jmxName = this.prefix + ":name=" + formatJmxName(group.name) + "AvailabilityMBean";
                group.jmxName = jmxName;
                ObjectName name = new ObjectName(jmxName);

                if (this.server.isRegistered(name)) {
                    this.server.unregisterMBean(name);
                }
                this.server.registerMBean(group, name);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * Formats a name to make is jmx-compatible: no spaces and the words are
     * capitalized.
     */
    private String formatJmxName(String name) {
        String res = WordUtils.capitalize(name);
        return StringUtils.replace(res, " ", "");
    }

    @ManagedOperation
    public PlatformGroup[] listGroupNames() {
        return this.groups.values().toArray(new PlatformGroup[] {});
    }

    @ManagedOperation
    public synchronized void auscultate() {
        Examination examination = new Examination();

        examination.setPlatform(this.platform);
        for (PlatformGroup group : this.groups.values()) {
            List<Diagnostic> res = new ArrayList<Diagnostic>();
            for (AuscultableBean bean : group.beans) {
                try {
                    res.add(bean.auscultate());
                } catch (Exception ex) {
                    if (logger.isWarnEnabled()) {
                        logger.warn("exception while execution " + ex.getMessage(), ex);
                    }
                }
            }
            if (NO_GROUPNAME.equals(group.name)) {
                examination.setDiagnostics(res);
            } else {
                examination.addGroupedDiagnostics(
                    new GroupedDiagnostics(group.name, res));
            }
        }

        this.lastExamination = examination;
    }

    @ManagedOperation
    public Examination lastExamination() {
        return this.lastExamination;
    }

    public interface PlatformGroupMBean extends Serializable, AvailableBean {
        // required by JMX
    }

    public final static class PlatformGroup implements PlatformGroupMBean {

        private static final long serialVersionUID = 1L;

        /**
         * Name used for the JMX MBean.
         */
        private String jmxName;
        private String name;

        private transient List<AuscultableBean> beans = new ArrayList<AuscultableBean>();

        public PlatformGroup(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }

        public String getJmxName() {
            return this.jmxName;
        }

        @Override
        public int getAvailability() {
            for (AuscultableBean bean : this.beans) {
                Status status = bean.auscultate().getStatus();
                if ((status == Status.ERROR) || (status == Status.WARNING)) {
                    return UNAVAILABLE;
                }
            }
            return AVAILABLE;
        }

    }

}