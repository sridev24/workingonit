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
 * Version     : $Revision: 232 $
 * Last edit   : $Date: 2009-07-09 16:56:36 +0200 (Thu, 09 Jul 2009) $
 * Last editor : $Author: vbossica $
 */
package org.workingonit.addenda.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.Assert;

/**
 * 
 * @author Vladimir Ritz Bossicard
 */
public class BeanLocator implements ApplicationContextAware, InitializingBean {

    private static ApplicationContext context;

    public void setApplicationContext(ApplicationContext context)
            throws BeansException {
        BeanLocator.context = context;
    }

    public void afterPropertiesSet() throws Exception {
        Assert.notNull(BeanLocator.context, "BeanLocator not property initialized by Spring!");
    }
    
    @SuppressWarnings("unchecked")
    public static <T> T getBean(BeanEnum bean) {
        return (T) getBean(bean.getName());
    }

    @SuppressWarnings("unchecked")
    public static <T> T getBean(String name) {
        return (T) context.getBean(name);
    }

}