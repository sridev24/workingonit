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

/**
 * Spring bean used to split the Hibernate configuration into several distinct
 * blocs. Beans implementing this interface are automatically searched for by
 * the {@link AnnotationSessionFactoryBean} before creating the Hibernate
 * SessionFactory.
 * 
 * @author Vladimir Ritz Bossicard
 */
public interface HibernateConfiguratorBean {

    /**
     * Returns the annotated classes, for which mappings will be read from
     * class-level JDK 1.5+ annotation metadata.
     * 
     * @see org.hibernate.cfg.AnnotationConfiguration#addAnnotatedClass(Class)
     * @see org.springframework.orm.hibernate3.annotation.AnnotationSessionFactoryBean#setAnnotatedClasses(Class[])
     */
    Class<?>[] getAnnotatedClasses();

    /**
     * Returns the names of annotated packages, for which package-level JDK 1.5+
     * annotation metadata will be read.
     * 
     * @see org.hibernate.cfg.AnnotationConfiguration#addPackage(String)
     * @see org.springframework.orm.hibernate3.annotation.AnnotationSessionFactoryBean#setAnnotatedPackages(String[])
     */
    String[] getAnnotatedPackages();

    /**
     * Returns the packages to search for autodetection of your entity classes
     * in the classpath.
     * 
     * @see org.springframework.orm.hibernate3.annotation.AnnotationSessionFactoryBean#setPackagesToScan(String[])
     */
    String[] getPackagesToScan();

}
