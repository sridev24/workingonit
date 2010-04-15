/*
 * Copyright 2008-2009 Vladimir Ritz Bossicard
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
 * Version     : $Revision: 352 $
 * Last edit   : $Date: 2010-03-05 17:33:45 +0100 (Fri, 05 Mar 2010) $
 * Last editor : $Author: vbossica $
 */
package com.acme.kitchensink.services;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.BeanNameAware;
import org.workingonit.clusterus.singleton.SingletonService;

/**
 *
 * @author Vladimir Ritz Bossicard
 */
public class SampleSingletonService implements SingletonService, BeanNameAware {

    private static Log log = LogFactory.getLog(SampleSingletonService.class);

    private String name;

    public SampleSingletonService() {
        this("NO_NAME");
    }

    public SampleSingletonService(final String name) {
        this.name = name;
    }

    public void setBeanName(final String name) {
        this.name = name;
    }

    public void start() throws Exception {
        if (log.isInfoEnabled()) {
            log.info("Starting service " + this.name);
        }
    }

    public void stop() throws Exception {
        if (log.isInfoEnabled()) {
            log.info("Stopping service " + this.name);
        }
    }

    @Override
    public String toString() {
        return this.getClass().getName();
    }

}
