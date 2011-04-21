/*
 * Copyright 2011 Vladimir Ritz Bossicard
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
 */
package org.workingonit.addenda.subversion;

import java.io.File;

import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.auth.BasicAuthenticationManager;
import org.tmatesoft.svn.core.auth.ISVNAuthenticationManager;
import org.tmatesoft.svn.core.internal.io.dav.DAVRepositoryFactory;
import org.tmatesoft.svn.core.wc.SVNInfo;
import org.tmatesoft.svn.core.wc.SVNRevision;
import org.tmatesoft.svn.core.wc.SVNStatus;
import org.tmatesoft.svn.core.wc.SVNStatusClient;
import org.tmatesoft.svn.core.wc.SVNWCClient;
import org.tmatesoft.svn.core.wc.SVNWCUtil;

public class SubversionInfoResolver {

    public String[] resolve( String username, String password, String url ) throws SVNException {
        ISVNAuthenticationManager manager = null;
        if (username != null && password != null) {
            manager = new BasicAuthenticationManager(username, password);
        }

        return url == null ? seachLocalProperties(manager) : searchRemoteProperties(manager, url);
    }
    
    private String[] seachLocalProperties(final ISVNAuthenticationManager manager) throws SVNException {
        SVNStatusClient statusClient =
            new SVNStatusClient(manager, SVNWCUtil.createDefaultOptions(true));

        SVNStatus status = statusClient.doStatus(new File("."), false);
        return new String[] {
            status.getAuthor(),
            status.getCommittedDate().toString(),
            status.getRevision().toString(),
            status.getURL().toString()
        };
    }

    private String[] searchRemoteProperties(final ISVNAuthenticationManager manager, final String url) throws SVNException {
        DAVRepositoryFactory.setup();

        SVNWCClient wcClient = new SVNWCClient(manager, SVNWCUtil.createDefaultOptions(true));
        SVNInfo info = wcClient.doInfo(SVNURL.parseURIEncoded(url), SVNRevision.HEAD, SVNRevision.HEAD);
        
        return new String[] { 
            info.getAuthor(),
            info.getCommittedDate().toString(),
            "" + info.getCommittedRevision().getNumber(),
            url
        };
    }

}
