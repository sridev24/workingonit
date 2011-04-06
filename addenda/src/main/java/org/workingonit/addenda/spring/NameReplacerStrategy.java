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
 *
 * Version      : $Revision: 352 $
 * Last edit    : $Date: 2010-03-05 17:33:45 +0100 (Fri, 05 Mar 2010) $
 * Last editor  : $Author: vbossica $
 */
package org.workingonit.addenda.spring;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.jmx.export.metadata.JmxAttributeSource;
import org.springframework.jmx.export.metadata.ManagedResource;
import org.springframework.jmx.export.naming.ObjectNamingStrategy;
import org.springframework.jmx.support.ObjectNameManager;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

/**
 * A {@link ObjectNamingStrategy} that prepends all bean keys with the default
 * domain. This can be used when several Spring application coexist inside a
 * same container and use annotated MBean classes.
 *
 * @see org.springframework.jmx.export.naming.MetadataNamingStrategy
 * @author Vladimir Ritz Bossicard
 */
public class NameReplacerStrategy implements ObjectNamingStrategy {

    private final static Log logger = LogFactory.getLog(NameReplacerStrategy.class);
    
    private JmxAttributeSource attributeSource;

    private Map<String, String> names = new HashMap<String, String>();

    @Required
    public void setAttributeSource(JmxAttributeSource attributeSource) {
        Assert.notNull(attributeSource, "JmxAttributeSource must not be null");
        this.attributeSource = attributeSource;
    }

    public void setNames(Map<String, String> names) {
        this.names = names;
    }

    public ObjectName getObjectName(Object managedBean, String beanKey)
            throws MalformedObjectNameException {
        Class<?> managedClass = AopUtils.getTargetClass(managedBean);
        ManagedResource mr = this.attributeSource.getManagedResource(managedClass);

        if ((mr != null) && StringUtils.hasText(mr.getObjectName())) {
            String name = mr.getObjectName();
            String tmp = name.substring(0, name.indexOf(":name"));

            if (this.names.containsKey(tmp)) {
                tmp = this.names.get(tmp) + name.substring(name.indexOf(":name"));
                if (logger.isDebugEnabled()) {
                    logger.debug("setting new name to " + tmp);
                }
                mr.setObjectName(tmp);
            }
            return ObjectNameManager.getInstance(mr.getObjectName());
        }
        else {
            try {
                return ObjectNameManager.getInstance(beanKey);
            } catch (MalformedObjectNameException ex) {
                String pkgName = ClassUtils.getPackageName(managedClass);

                Hashtable<String, String> properties = new Hashtable<String, String>();
                properties.put("type", ClassUtils.getShortName(managedClass));
                properties.put("name", beanKey);
                return ObjectNameManager.getInstance(pkgName, properties);
            }
        }
    }

}
