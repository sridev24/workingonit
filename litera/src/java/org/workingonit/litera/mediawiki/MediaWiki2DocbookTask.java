package org.workingonit.litera.mediawiki;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;

/*
 * Copyright (C) 2009-2010 Vladimir Ritz Bossicard
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Version      : $Revision: 330 $
 * Last edit    : $Date: 2010-01-20 22:42:44 +0100 (Wed, 20 Jan 2010) $
 * Last editor  : $Author: vbossica $
 */
public class MediaWiki2DocbookTask extends Task {

    private String url;
    private File outFile;

    public void setUrl(String url) {
        this.url = url;
    }

    public void setOutFile(File outFile) {
        this.outFile = outFile;
    }

    @Override
    public void execute() throws BuildException {
        try {
            log("extracting " + this.url + " into " + this.outFile);
            String data = new HttpDocumentGrabber().grab(this.url);
            DocumentParser parser = new DocumentParser();
            parser.setEnclosingTag("section");
            String xhtml = parser.parse(this.url, UrlUtils.formatTitle(this.url), data);
            FileUtils.writeStringToFile(this.outFile, xhtml);
        } catch (Exception ex) {
            throw new BuildException(ex);
        }
    }

}