/*
 * Copyright 2009-2010 Vladimir Ritz Bossicard
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
package org.workingonit.litera.ivy;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.types.FileSet;
import org.workingonit.addenda.ant.FileSetUtils;

/**
 * @author Vladimir Ritz Bossicard
 */
public class FileReportGeneratorTask extends Task {

    private String title;
    private File outFile;
    private List<FileSet> filesets = new ArrayList<FileSet>();

    public void setTitle(String title) {
        this.title = title;
    }

    public void setOutFile(File outFile) {
        this.outFile = outFile;
    }

    public void addFileSet(FileSet fileset) {
        if (!filesets.contains(fileset)) {
            filesets.add(fileset);
        }
    }

    @Override
    public void execute() throws BuildException {
        List<File> files = FileSetUtils.toFileList(this.filesets, getProject());
        
        try {
            new FileReportGenerator().output(files.toArray(new File[]{}), title, this.outFile);
        } catch (Exception ex) {
            throw new BuildException(ex);
        }
    }

}