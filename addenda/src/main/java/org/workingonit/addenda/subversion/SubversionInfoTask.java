/*
 * Copyright 2009,2011 Vladimir Ritz Bossicard
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

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.types.LogLevel;
import org.tmatesoft.svn.core.SVNException;

/**
 * Task that makes available various Subversion information via Ant properties.
 *
 * <p><b>Usage</b><p>
 * To access a local repository:
 * <pre>
 *   &lt;taskdef resource="wkg-addenda.properties"/>
 *
 *   &lt;svninfo prefix="svn."/>
 *
 *   &lt;echo message="last author = ${svn.author}"/>
 *   &lt;echo message="last commit date = ${svn.date}"/>
 *   &lt;echo message="last revision number = ${svn.rev}"/>
 *   &lt;echo message="remote url = ${svn.url}"/>
 *   &lt;echo message="url name = ${svn.urlname}"/>
 * </pre>
 * To access a remote repository:
 * <pre>
 *   &lt;taskdef resource="workingonit.properties"/>
 *
 *   &lt;svninfo url="..." username="..." password="..." prefix="svn."/>
 * </pre>
 *
 * The URL name is determined following these rules:
 * <ul>
 *    <li><i>trunk</i> if the URL contains the term "trunk"
 *    <li><i>branch name</i> if the URL contains the term "branches"
 *    <li><i>tag name</i> if the URL contains the term "tags"
 * </ul>
 *
 * <p><b>Parameters</b>
 * <ul><table>
 *    <tr><td><b>username</b></td><td>(optional) username for access to remote directory</td></tr>
 *    <tr><td><b>password</b></td><td>(optional) password for access to remote directory</td></tr>
 *    <tr><td><b>url</b></td><td>(optional) remote directory url</td></tr>
 *    <tr><td><b>prefix</b></td><td>(optional) prefix of the properties, default to <code>""</code> (empty string)</td></tr>
 *    <tr><td><b>failonerror</b></td><td>bubble up any exception that may occur, default to <code>false</code></td></tr>
 * </table></ul>
 *
 * <p><b>Dependencies</b>
 * <ul>
 *     <li><a href="http://svnkit.com">SVNKit</a>, 1.2.2 or later</li>
 * </ul>
 *
 * @author Vladimir Ritz Bossicard
 */
public class SubversionInfoTask extends Task {

    private boolean failonerror = false;

    private String username = null;
    private String password = null;
    private String url = null;
    private String prefix = "";

    public void setUsername(final String username) {
        this.username = username;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public void setUrl(final String url) {
        this.url = url;
    }

    /**
     * Value prefixing the Ant properties saved during the execution of the
     * task.
     */
    public void setPrefix(final String prefix) {
        this.prefix = prefix == null ? "" : prefix;
    }

    /**
     * Stop the build process if an error occurred while retrieving Subversion
     * informations. Default is <code>false</code>.
     */
    public void setFailonerror(final boolean failonerror) {
        this.failonerror = failonerror;
    }

    @Override
    public void execute() throws BuildException {
        try {
            String[] res = new SubversionInfoResolver().resolve(username, password, url);
            
            getProject().setProperty(this.prefix + "author", res[0]);
            getProject().setProperty(this.prefix + "date", res[1]);
            getProject().setProperty(this.prefix + "rev", res[2]);
            getProject().setProperty(this.prefix + "url", res[3]);

            getProject().setProperty(this.prefix + "urlname", findName(res[3]));
        } catch (SVNException ex) {
            if (this.failonerror) {
                throw new BuildException(ex);
            }
            log("error occured while retrieving Subversion information: " + ex.getMessage(), ex, LogLevel.WARN.getLevel());
        }
    }

    /**
     * Tries to guess the best simple name for the given URL.
     */
    public String findName(final String url) {
        if (url.contains("trunk")) {
            return "trunk";
        } else if (url.contains("branches")) {
            return url.substring(url.lastIndexOf("branches") + "branches".length() + 1);
        } else if (url.contains("tags")) {
            return url.substring(url.lastIndexOf("tags") + "tags".length() + 1);
        }
        return "";
    }

}
