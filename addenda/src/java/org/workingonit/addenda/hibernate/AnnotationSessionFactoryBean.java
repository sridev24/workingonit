/*
 * Copyright 2009 Vladimir Ritz Bossicard
 *
 * This file is part of WorkingOnIt.
 *
 * WorkingOnIt is free software: you can redistribute it and/or modify it 
 * under the terms of the GNU General Public License as published by the Free 
 * Software Foundation, either version 3 of the License, or (at your option) 
 * any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for
 * more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 *
 * Version     : $Revision: 308 $
 * Last edit   : $Date: 2009-12-16 20:10:54 +0100 (Wed, 16 Dec 2009) $
 * Last editor : $Author: vbossica $
 */
package org.workingonit.addenda.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Special
 * {@link org.springframework.orm.hibernate3.annotation.AnnotationSessionFactoryBean}
 * that queries the {@link ApplicationContext} for beans implementing the
 * {@link HibernateConfiguratorBean} interface and configure the Hibernate
 * SessionFactory accordingly.
 * 
 * @author Vladimir Ritz Bossicard
 */
public class AnnotationSessionFactoryBean extends org.springframework.orm.hibernate3.annotation.AnnotationSessionFactoryBean 
        implements ApplicationContextAware {

    private ApplicationContext context;

    private List<Class<?>> annotatedClasses = new ArrayList<Class<?>>();
    private List<String> annotatedPackages = new ArrayList<String>();
    private List<String> packagesToScan = new ArrayList<String>();
    
    public void setApplicationContext(ApplicationContext context)
            throws BeansException {
        this.context = context;
    }

    @Override
    public void setAnnotatedClasses(Class[] annotatedClasses) {
        CollectionUtils.addAll(this.annotatedClasses, annotatedClasses);
    }

    @Override
    public void setAnnotatedPackages(String[] annotatedPackages) {
        CollectionUtils.addAll(this.annotatedPackages, annotatedPackages);
    }

    @Override
    public void setPackagesToScan(String[] packagesToScan) {
        CollectionUtils.addAll(this.packagesToScan, packagesToScan);
    }
    
    @Override
    public void afterPropertiesSet() throws Exception {
        String[] names = this.context.getBeanNamesForType(HibernateConfiguratorBean.class);
        for (String name : names) {
            HibernateConfiguratorBean bean = (HibernateConfiguratorBean) this.context.getBean(name);
            
            for (Class<?> cls : bean.getAnnotatedClasses()) {
                if (!this.annotatedClasses.contains(cls)) {
                    this.annotatedClasses.add(cls);
                }
            }

            for (String pkg : bean.getAnnotatedPackages()) {
                if (!this.annotatedPackages.contains(pkg)) {
                    this.annotatedPackages.add(pkg);
                }
            }

            for (String pkg : bean.getPackagesToScan()) {
                if (!this.packagesToScan.contains(pkg)) {
                    this.packagesToScan.add(pkg);
                }
            }
        }

        super.setAnnotatedClasses(this.annotatedClasses.toArray(new Class<?>[this.annotatedClasses.size()]));
        super.setAnnotatedPackages(this.annotatedPackages.toArray(new String[this.annotatedPackages.size()]));
        super.setPackagesToScan(this.packagesToScan.toArray(new String[this.packagesToScan.size()]));

        super.afterPropertiesSet();
    }
    
}
