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
 * Version     : $Revision: 316 $
 * Last edit   : $Date: 2010-01-18 12:36:57 +0100 (Mon, 18 Jan 2010) $
 * Last editor : $Author: vbossica $
 */
package org.workingonit.oraculum.dictionarygen;

import java.io.File;

import org.apache.commons.lang.StringUtils;
import org.apache.tools.ant.BuildException;
import org.workingonit.oraculum.dictionarygen.AbstractDatabaseTask;

/**
 * @author Vladimir Ritz Bossicard
 */
public class TableGraphGeneratorTask extends AbstractDatabaseTask {

    private String tables;
    private String ignoredColumns;
    private String ignoredKeys;
    private File outFile;

    public void setTables(String tables) {
        this.tables = tables;
    }

    public void setIgnoredColumns(String ignored) {
        this.ignoredColumns = ignored;
    }

    public void setIgnoredKeys(String ignored) {
        this.ignoredKeys = ignored;
    }

    public void setOutFile(File outFile) {
        this.outFile = outFile;
    }

    @Override
    public void execute() throws BuildException {
        int exitValue = executor.execute(TableGraphGenerator.class, 
            this.url, this.username, this.password, 
            this.tables, 
            StringUtils.defaultString(this.ignoredColumns, " "), 
            StringUtils.defaultString(this.ignoredKeys, " "), 
            this.outFile.getAbsolutePath());
    }

}
