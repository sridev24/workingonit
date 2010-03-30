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
 * Version     : $Revision: 288 $
 * Last edit   : $Date: 2009-11-28 20:32:49 +0100 (Sat, 28 Nov 2009) $
 * Last editor : $Author: vbossica $
 */
package org.workingonit.clusterus.singleton;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.workingonit.clusterus.singleton.election.VotingProcedure;

/**
 * Base implementation of a {@link Cluster} that handles the voting according to
 * the {@link VotingProcedure} and notifies the {@link ClusterListener}s or the
 * result.
 * 
 * @author Vladimir Ritz Bossicard
 */
public abstract class AbstractCluster implements Cluster {

    private static final Log log = LogFactory.getLog(AbstractCluster.class);

    protected VotingProcedure procedure;
    private List<ClusterListener> listeners =  new ArrayList<ClusterListener>();
    protected Node incumbent = null;

    public void setVotingProcedure(final VotingProcedure procedure) {
        this.procedure = procedure;
    }

    public void addClusterListener(final ClusterListener listener) {
        if (log.isDebugEnabled()) {
            log.debug("adding listener");
        }
        this.listeners.add(listener);
    }

    public void removeClusterListener(final ClusterListener listener) {
        this.listeners.remove(listener);
    }

    protected void doElection() {
        if (this.procedure != null) {
            try {
                Node newIncumbent = this.procedure.electIncumbent(this);
                if (newIncumbent != null && !newIncumbent.equals(this.incumbent)) {
                    if (this.incumbent != null) {
                        this.incumbent.setIncumbent(false);
                    }
                    this.incumbent = newIncumbent;
                    this.incumbent.setIncumbent(true);

                    fireChangedCoordinatorEvent(new ClusterEvent(this, this.incumbent));
                }
            }
            catch (Exception ex) {
                log.error("do election failed", ex);
            }
        }
    }

    protected void fireChangedCoordinatorEvent(final ClusterEvent event) {
        if (log.isDebugEnabled()) {
            log.debug("Coordinator has changed!");
            log.debug("listener # = " + this.listeners.size());
        }
        for (ClusterListener listener : this.listeners) {
            listener.onNewIncumbentElected(event);
        }
    }

}
