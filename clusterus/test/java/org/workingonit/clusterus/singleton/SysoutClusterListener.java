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
 * Version     : $Revision: 340 $
 * Last edit   : $Date: 2010-02-09 08:47:28 +0100 (Tue, 09 Feb 2010) $
 * Last editor : $Author: vbossica $
 */
package org.workingonit.clusterus.singleton;

import org.springframework.beans.factory.InitializingBean;
import org.workingonit.clusterus.singleton.Cluster;
import org.workingonit.clusterus.singleton.ClusterEvent;
import org.workingonit.clusterus.singleton.ClusterListener;

/**
 * @author Vladimir Ritz Bossicard
 */
public class SysoutClusterListener implements ClusterListener, InitializingBean {

    private Cluster cluster;

    public void setCluster(final Cluster cluster) {
        this.cluster = cluster;
    }

    public void afterPropertiesSet() throws Exception {
        this.cluster.addClusterListener(this);
    }

    public void onNewIncumbentElected(final ClusterEvent event) {
        System.out.println("event received from the cluster: " + event.getNode());
    }

}
