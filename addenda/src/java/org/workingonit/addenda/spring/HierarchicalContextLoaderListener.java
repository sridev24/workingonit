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
 * Version     : $Revision: 282 $
 * Last edit   : $Date: 2009-10-30 09:30:31 +0100 (Fri, 30 Oct 2009) $
 * Last editor : $Author: vbossica $
 */
package org.workingonit.addenda.spring;

import javax.servlet.ServletContext;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.StringUtils;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.ContextLoaderListener;

/**
 * Specific {@link ContextLoaderListener} that creates a parent
 * {@link ApplicationContext} delegating to the
 * {@link ClassPathXmlApplicationContext}.
 * <p>
 * 
 * This listener is useful when some beans must be created for specific
 * environments (e.g. WTP vs. JavaEE environments).
 * <p>
 * 
 * <b>Usage:</b>
 * <p>
 * 
 * Modify the <code>web.xml</code> file and define parent config files with the
 * <code>parentContextConfigLocation</code> context parameter.
 * 
 * <pre>
 *    &lt;context-param&gt;
 *        &lt;param-name&gt;parentContextConfigLocation&lt;/param-name&gt;
 *        &lt;param-value&gt;
 *            classpath:parentContext.xml
 *        &lt;/param-value&gt;
 *    &lt;/context-param&gt;
 *     
 *    &lt;listener&gt;
 *        &lt;listener-class&gt;org.workingonit.addenda.spring.HierarchicalContextLoaderListener&lt;/listener-class&gt;
 *    &lt;/listener&gt;
 * </pre>
 * 
 * @author Vladimir Ritz Bossicard
 */
public class HierarchicalContextLoaderListener extends ContextLoaderListener {

    public static final String PARENTCONFIG_LOCATION_PARAM = "parentContextConfigLocation";

    @Override
    protected ContextLoader createContextLoader() {
        return new HierarchicalContextLoader();
    }

    private final static class HierarchicalContextLoader extends ContextLoader {

        @Override
        protected ApplicationContext loadParentContext(
                ServletContext servletContext) throws BeansException {
            String location = servletContext.getInitParameter(PARENTCONFIG_LOCATION_PARAM);
            if (location != null) {
                String[] locations = StringUtils.tokenizeToStringArray(location,
                    ConfigurableApplicationContext.CONFIG_LOCATION_DELIMITERS);

                ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext();
                context.setConfigLocations(locations);
                context.refresh();

                return context;
            }
            return super.loadParentContext(servletContext);
        }

    }

}