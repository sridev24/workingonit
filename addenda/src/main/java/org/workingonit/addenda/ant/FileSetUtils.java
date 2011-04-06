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
package org.workingonit.addenda.ant;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.tools.ant.DirectoryScanner;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.types.FileSet;

/**
 * Utility class to handle Ant {@link FileSet} class.
 *  
 * @author Vladimir Ritz Bossicard
 */
public class FileSetUtils {

    public static List<File> toFileList(List<FileSet> filesets, Project project) {
        List<File> files = new ArrayList<File>();
        
        for (FileSet fileset : filesets) {
            DirectoryScanner ds = fileset.getDirectoryScanner(project);
            File dir = ds.getBasedir();
            for (String filename : ds.getIncludedFiles()) {
                files.add(new File(dir, filename));
            }
        }
        return files;
    }

}
