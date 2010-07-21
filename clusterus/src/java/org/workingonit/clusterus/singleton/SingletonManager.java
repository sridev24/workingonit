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
package org.workingonit.clusterus.singleton;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;

/**
 * Manages <i>business singletons</i> implementing the {@link SingletonService}
 * interface.
 * 
 * @author Vladimir Ritz Bossicard
 */
public class SingletonManager implements ClusterListener, InitializingBean {

    private static final Log log = LogFactory.getLog(SingletonManager.class);

    private enum Status { STARTED, STOPPED }

    private Cluster cluster;
    /** contains the list of services */
    private List<SingletonService> services = new ArrayList<SingletonService>();
    private Status status = Status.STOPPED;

    public void setCluster(final Cluster cluster) {
        this.cluster = cluster;
    }

    public void setServices(final List<SingletonService> services) {
        this.services = services;
    }

    public void afterPropertiesSet() throws Exception {
        this.cluster.addClusterListener(this);
        this.cluster.start();
    }

    private void startServices() {
        if (this.status == Status.STARTED) {
            if (log.isInfoEnabled()) {
                log.info("services already started");
            }
            return;
        }
        if (log.isInfoEnabled()) {
            log.info("starting services");
        }
        /*
         * The services should ideally be started within a thread, but this is
         * out of scope for this little prototype.
         */
        for (SingletonService service : this.services ) {
            try {
                service.start();
            } catch (Exception ex) {
                log.warn("Exception caught when starting service '" + service.toString() + "'", ex);
            }
        }
        this.status = Status.STARTED;
    }

    private void stopServices() {
        if (this.status == Status.STOPPED) {
            if (log.isInfoEnabled()) {
                log.info("services already stopped");
            }
            return;
        }
        if (log.isInfoEnabled()) {
            log.info("stopping services");
        }
        for (SingletonService service : this.services) {
            try {
                service.stop();
            } catch (Exception ex) {
                log.warn("Exception caught when stopping service '" + service.toString() + "'", ex);
            }
        }
        this.status = Status.STOPPED;
    }

    public void onNewIncumbentElected(final ClusterEvent event) {
        if (log.isInfoEnabled()) {
            log.info("new incumbent elected = " + event.getNode().getName());
        }
        if (event.getCluster().getLocalNode().isIncumbent()) {
            startServices();
        } else {
            stopServices();
        }
    }

}