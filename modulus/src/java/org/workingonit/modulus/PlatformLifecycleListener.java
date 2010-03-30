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
 * Version      : $Revision: 319 $
 * Last edit    : $Date: 2010-01-18 22:01:37 +0100 (Mon, 18 Jan 2010) $
 * Last editor  : $Author: vbossica $
 */
package org.workingonit.modulus;

import java.util.Date;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * @author Vladimir Ritz Bossicard
 */
public class PlatformLifecycleListener implements ApplicationListener<ApplicationEvent> {

    private final static Log log = LogFactory.getLog(PlatformLifecycleListener.class);

    @Autowired
    private Platform platform = null;

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof ContextRefreshedEvent) {
            onStart((ContextRefreshedEvent) event, new Date(event.getTimestamp()));
        } else if (event instanceof ContextClosedEvent) {
            onStop((ContextClosedEvent) event, new Date(event.getTimestamp()));
        } else {
            if (log.isDebugEnabled()) {
                log.debug("unhandled event received: " + event.getClass());
            }
        }
    }

    protected void onStart(ContextRefreshedEvent event, Date timestamp) {
        if (log.isInfoEnabled()) {
            log.info("Application '" + this.platform.getName() + "' started at " + timestamp);
            if (this.platform.getProperties() != null) {
                for (Map.Entry<Object, Object> entry : this.platform.getProperties().entrySet()) {
                    log.info("    " + entry.getKey() + ": " + entry.getValue());
                }
            }
        }
    }

    protected void onStop(ContextClosedEvent event, Date timestamp) {
        log.info("Application '" + this.platform.getName() + "' stopped at " + timestamp);
    }

}
