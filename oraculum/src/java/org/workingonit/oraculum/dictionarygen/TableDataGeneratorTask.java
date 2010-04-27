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
 * Version     : $Revision: 299 $
 * Last edit   : $Date: 2009-12-13 21:22:42 +0100 (Sun, 13 Dec 2009) $
 * Last editor : $Author: vbossica $
 */
package org.workingonit.oraculum.dictionarygen;

import java.io.File;

import org.apache.tools.ant.BuildException;

/**
 * Simple Ant task to execute the generation of files from the comments of an
 * Oracle table..
 * 
 * @author Vladimir Ritz Bossicard
 */
public class TableDataGeneratorTask extends AbstractDatabaseTask {

    private String table;
    private String ignored;
    private File defaultvalues;
    private File outFile;

    public void setTable(String table) {
        this.table = table;
    }

    public void setIgnoredColumns(String ignored) {
        this.ignored = ignored;
    }

    public void setDefaultColumnValues(File defaultvalues) {
        this.defaultvalues = defaultvalues;
    }

    public void setOutFile(File outFile) {
        this.outFile = outFile;
    }

    @Override
    public void execute() throws BuildException {
        int exitValue = executor.execute(TableDataGenerator.class, 
            this.url, this.username, this.password, 
            this.table, this.ignored, 
            this.defaultvalues == null ? null : this.defaultvalues.getAbsolutePath(),
            this.outFile.getAbsolutePath());
    }

}