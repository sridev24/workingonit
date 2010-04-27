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

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Vladimir Ritz Bossicard
 */
public class TableDataTO {

    private String name = "";
    private String comments = "";
    private Map<String, ColumnDataTO> columns = new LinkedHashMap<String, ColumnDataTO>();
    
    public TableDataTO() {
        // noop
    }
    
    public TableDataTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
    
    public Collection<ColumnDataTO> getColumns() {
        return this.columns.values();
    }
    
    public ColumnDataTO getColumn(String name) {
        return this.columns.get(name);
    }
    
    public void addColumn(ColumnDataTO column) {
        this.columns.put(column.getName(), column);
    }

    public void setColumns(List<ColumnDataTO> columns) {
        for (ColumnDataTO column : columns) {
            this.columns.put(column.getName(), column);
        }
    }

}
