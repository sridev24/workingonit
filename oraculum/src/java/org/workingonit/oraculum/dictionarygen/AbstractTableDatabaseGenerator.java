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
package org.workingonit.oraculum.dictionarygen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.workingonit.oraculum.dictionarygen.dao.ColumnDataTO;
import org.workingonit.oraculum.dictionarygen.dao.TableDataTO;

/**
 * @author Vladimir Ritz Bossicard
 */
public class AbstractTableDatabaseGenerator extends AbstractDatabaseGenerator {

    private List<String> ignored = new ArrayList<String>();

    public void setIgnoredColumns(String names) {
        this.ignored.addAll(Arrays.asList(StringUtils.split(names.trim(), ',')));
    }

    /**
     * Sets the column's <code>ignored</code> flag to <code>true</code> if the
     * its name matches is found in the ignored list.
     */
    protected void setIgnoredFlag(TableDataTO table) {
        for (ColumnDataTO col : table.getColumns()) {
            if (ignored.contains(col.getName())) {
                col.setIgnored(true);
            }
        }        
    }

}
