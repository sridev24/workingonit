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
package org.workingonit.addenda.subversion;

import java.io.File;
import java.util.List;

import org.tmatesoft.svn.core.SVNDepth;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.auth.ISVNAuthenticationManager;
import org.tmatesoft.svn.core.internal.io.dav.DAVRepositoryFactory;
import org.tmatesoft.svn.core.wc.SVNDiffStatus;
import org.tmatesoft.svn.core.wc.SVNRevision;
import org.tmatesoft.svn.core.wc.SVNUpdateClient;
import org.tmatesoft.svn.core.wc.SVNWCUtil;

/**
 * Class that exports files from a Subversion repository.
 * 
 * <p><b>Dependencies</b>
 * <ul>
 *     <li><a href="http://svnkit.com">SVNKit</a>, 1.2.2 or later</li>
 * </ul>
 *
 * @author Vladimir Ritz Bossicard
 */
public class SVNFileExporter {
    
    static {
        DAVRepositoryFactory.setup();
    }

    private ISVNAuthenticationManager authManager;
    
    public SVNFileExporter(ISVNAuthenticationManager authManager) {
        this.authManager = authManager;
    }

    /**
     * Exports the list for <code>files</code> from a revision into a directory.
     * 
     * @param file list of files to export.
     * @param rev the revision (last one) from which the file must be exported
     * @param outDir the output directory
     */
    public void export(List<SVNDiffStatus> files, int rev, File outDir) throws SVNException {
        SVNUpdateClient client = new SVNUpdateClient(authManager, SVNWCUtil.createDefaultOptions(true));
        for (SVNDiffStatus file : files) {
            File dest = new File(outDir, file.getPath());
            client.doExport(file.getURL(), dest, SVNRevision.create(rev), SVNRevision.create(rev), null, true, SVNDepth.IMMEDIATES);
        }
    }

}