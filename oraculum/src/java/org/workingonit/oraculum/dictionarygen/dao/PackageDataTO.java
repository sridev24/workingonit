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
 * Version     : $Revision: 333 $
 * Last edit   : $Date: 2010-01-22 21:19:48 +0100 (Fri, 22 Jan 2010) $
 * Last editor : $Author: vbossica $
 */
package org.workingonit.oraculum.dictionarygen.dao;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Vladimir Ritz Bossicard
 */
public class PackageDataTO {

    private String name;
    private String type;
    private List<PackageDataTO> parents = new ArrayList<PackageDataTO>();
    private List<PackageDataTO> children = new ArrayList<PackageDataTO>();
    
    public PackageDataTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setParents(List<PackageDataTO> parents) {
        this.parents = parents;
    }
    
    public void addParent(PackageDataTO parent) {
        this.parents.add(parent);
    }

    public List<PackageDataTO> getParents() {
        return this.parents;
    }
    
    public void setChildren(List<PackageDataTO> children) {
        this.children = children;
    }
    
    public void addChild(PackageDataTO child) {
        this.children.add(child);
    }
    
    public List<PackageDataTO> getChildren() {
        return this.children;
    }

}