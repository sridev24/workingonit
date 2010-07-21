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

import java.util.Collection;

import org.workingonit.clusterus.singleton.election.VotingProcedure;

/**
 * Represents a group of {@link Node} forming a <i>Cluster</i>
 *
 * @author Vladimir Ritz Bossicard
 */
public interface Cluster {

    void addClusterListener(ClusterListener listener);

    void removeClusterListener(ClusterListener listener);

    void setVotingProcedure(VotingProcedure procedure);

    /**
     * <b>Important</b>: the method must be invoked <i>after</i> having
     * registered a {@link ClusterListener} otherwise nobody will be notified of
     * the result of the very first election.
     */
    void start() throws Exception;

    /**
     * Returns the {@link Node} attached to the local application.
     */
    Node getLocalNode();

    /**
     * Returns all the nodes of this cluster.
     */
    Collection<Node> getNodes();

}
