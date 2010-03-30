/*
 * Copyright 2010 Vladimir Ritz Bossicard
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
 * Version     : $Revision: 319 $
 * Last edit   : $Date: 2010-01-18 22:01:37 +0100 (Mon, 18 Jan 2010) $
 * Last editor : $Author: vbossica $
 */
package org.workingonit.modulus.web;

import javax.servlet.ServletContext;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.ConfigurableWebApplicationContext;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.ContextLoaderListener;

/**
 * @author Vladimir Ritz Bossicard
 */
public class PlatformContextLoaderListener extends ContextLoaderListener {

    private final static Log log = LogFactory.getLog(PlatformContextLoaderListener.class);

    private final static String DEFAULT_LOCATION = "/WEB-INF/context/";

    @Override
    protected ContextLoader createContextLoader() {
        return new PlatformContextLoader();
    }

    public final static class PlatformContextLoader extends ContextLoader {
        @Override
        protected void customizeContext(ServletContext servletContext, ConfigurableWebApplicationContext applicationContext) {
            String name = System.getProperty("modulus.platform.name", "");
            if (StringUtils.isBlank(name)) {
                name = servletContext.getInitParameter("modulus.platform.name");
            }

            String context = name + "Context.xml";
            if (log.isInfoEnabled()) {
                log.info("loading application " + context);
            }
            applicationContext.setConfigLocation(DEFAULT_LOCATION + context);
        }
    }

}
