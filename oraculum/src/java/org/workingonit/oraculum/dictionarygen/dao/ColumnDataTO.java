/*
 * Copyright 2009 Vladimir Ritz Bossicard
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
package org.workingonit.oraculum.dictionarygen.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Vladimir Ritz Bossicard
 */
public class ColumnDataTO {

    private String name;
    private String type;
    private boolean nullable;
    /** <code>true</code> if the column is a primary key */
    private boolean primary;
    private String comments = "";
    private boolean ignored = false;
    private List<RefTableDataTO> fkeys = new ArrayList<RefTableDataTO>();
    
    public ColumnDataTO() {
        // noop
    }

    public ColumnDataTO(String name, String type) {
        this(name, type, false);
    }

    public ColumnDataTO(String name, String type, boolean primary) {
        this.name = name;
        this.type = type;
        this.primary = primary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFlags() {
        StringBuffer buffer = new StringBuffer();
        if (this.primary) {
            buffer.append(" PK");
        }
        if (!this.nullable) {
            buffer.append(" M");
        }
        return buffer.toString().trim();
    }

    public boolean isNullable() {
        return this.nullable;
    }

    public void setNullable(boolean nullable) {
        this.nullable = nullable;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public boolean isIgnored() {
        return ignored;
    }

    public void setIgnored(boolean ignored) {
        this.ignored = ignored;
    }

    public void setPrimary(boolean primary) {
        this.primary = primary;
    }

    public boolean isPrimary() {
        return this.primary;
    }

    public Collection<RefTableDataTO> getForeignKeys() {
        return this.fkeys;
    }
    
    public void addForeignKey(RefTableDataTO reference) {
        this.fkeys.add(reference);
    }

}
