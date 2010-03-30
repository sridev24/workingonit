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

/**
 * Represents, well, a <i>node</i> in a cluster.
 * 
 * @author Vladimir Ritz Bossicard
 */
public class Node {

    private String name;
    protected boolean incumbent;

    public Node(final String name) {
        this(name, false);
    }

    public Node(final String name, final boolean incumbent) {
        this.name = name;
        this.incumbent = incumbent;
    }

    public String getName() {
        return this.name;
    }

    public void setIncumbent(final boolean incumbent) {
        this.incumbent = incumbent;
    }

    public boolean isIncumbent() {
        return this.incumbent;
    }

    @Override
    public String toString() {
        return this.name + " (" + (this.incumbent ? "" : "not ") + "incumbent)";
    }

}