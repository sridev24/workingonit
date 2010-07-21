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
 * Version      : $Revision: 352 $
 * Last edit    : $Date: 2010-03-05 17:33:45 +0100 (Fri, 05 Mar 2010) $
 * Last editor  : $Author: vbossica $
 */
package org.workingonit.litera.mediawiki;

import java.io.File;
import java.io.OutputStream;

import org.apache.commons.io.FileUtils;

/**
 * @author Vladimir Ritz Bossicard
 */
public class MockMediaWiki2PdfGenerator extends MediaWiki2PdfGenerator {

    private File dir;

    public void setDir(File dir) {
        this.dir = dir;
    }

    @Override
    public void generate(String url, OutputStream out) throws Exception {
        File file = new File(this.dir, "static-article.pdf");
        System.out.println("loading document '" + file.getAbsolutePath() + "'");
        if (!file.exists()) {
            throw new IllegalArgumentException("document doesn't exist: " + file.getAbsolutePath());
        }

        byte[] doc = FileUtils.readFileToByteArray(file);
        System.out.println("original document length " + doc.length);

        out.write(doc);
        out.flush();
    }

}