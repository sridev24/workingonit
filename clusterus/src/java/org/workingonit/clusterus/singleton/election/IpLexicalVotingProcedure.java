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
package org.workingonit.clusterus.singleton.election;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.workingonit.clusterus.singleton.Cluster;
import org.workingonit.clusterus.singleton.Node;

/**
 * Elects the {@link Node} with the smaller lexical IP address.
 * 
 * @author Vladimir Ritz Bossicard
 */
public class IpLexicalVotingProcedure implements VotingProcedure {

    private static final Log log = LogFactory.getLog(IpLexicalVotingProcedure.class);

    public Node electIncumbent(final Cluster cluster) throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("starting election for node : " + cluster.getLocalNode().getName());
        }
        Node elect = cluster.getLocalNode();

        for (Node node : cluster.getNodes()) {
            if (isElected(elect, node)) {
                elect = node;
            }
        }

        if (log.isDebugEnabled()) {
            log.debug("and the winner is... : " + elect.getName());
        }

        return elect;
    }

    protected boolean isElected(final Node elected, final Node candidate) {
        return elected.getName().compareTo(candidate.getName()) > 0;
    }

}
