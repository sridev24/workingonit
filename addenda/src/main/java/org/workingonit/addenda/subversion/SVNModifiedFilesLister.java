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

import java.util.ArrayList;
import java.util.List;

import org.tmatesoft.svn.core.SVNDepth;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNNodeKind;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.auth.ISVNAuthenticationManager;
import org.tmatesoft.svn.core.internal.io.dav.DAVRepositoryFactory;
import org.tmatesoft.svn.core.wc.ISVNDiffStatusHandler;
import org.tmatesoft.svn.core.wc.SVNDiffClient;
import org.tmatesoft.svn.core.wc.SVNDiffStatus;
import org.tmatesoft.svn.core.wc.SVNRevision;
import org.tmatesoft.svn.core.wc.SVNStatusType;
import org.tmatesoft.svn.core.wc.SVNWCUtil;

/**
 * Class that lists the modified files from a Subversion repository.
 * 
 * <p><b>Dependencies</b>
 * <ul>
 *     <li><a href="http://svnkit.com">SVNKit</a>, 1.2.2 or later</li>
 * </ul>
 *
 * @author Vladimir Ritz Bossicard
 */
public class SVNModifiedFilesLister {
    
    static {
        DAVRepositoryFactory.setup();
    }

    private ISVNAuthenticationManager authManager;

    public SVNModifiedFilesLister(ISVNAuthenticationManager auth) {
        this.authManager = auth;
    }

    /**
     * Recursively list the added or modified (content and not properties) files
     * from the given url and between two revisions.
     */
    public List<SVNDiffStatus> listModified(String url, long from, long to) throws SVNException {
        final List<SVNDiffStatus> res = new ArrayList<SVNDiffStatus>();
        SVNDiffClient client = new SVNDiffClient(authManager, SVNWCUtil.createDefaultOptions(true));

        SVNURL baseUrl = SVNURL.parseURIEncoded(url);
        client.doDiffStatus(baseUrl, SVNRevision.create(from),
            baseUrl, SVNRevision.create(to), 
            SVNDepth.INFINITY, false, 
            new ISVNDiffStatusHandler() {
                public void handleDiffStatus(SVNDiffStatus status) throws SVNException {
                    if (status.getKind() == SVNNodeKind.FILE && 
                            !status.isPropertiesModified() &&
                            (status.getModificationType() == SVNStatusType.STATUS_ADDED || 
                            status.getModificationType() == SVNStatusType.STATUS_MODIFIED)) {
                        res.add(status);
                    }
                }
            });
        return res;
    }

}