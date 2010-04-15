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

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

/**
 * @author Vladimir Ritz Bossicard
 */
@Test
@ContextConfiguration (
    locations = "classpath:org/workingonit/litera/mediawiki/context-test.xml"
)
public class MediaWiki2PdfGeneratorTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private MediaWiki2PdfGenerator generator;

    public void generate() throws Exception {
        OutputStream pdfOutput = null;
        try {
            pdfOutput = new BufferedOutputStream(new FileOutputStream(new File("target/test/mediawiki-generator.pdf")));

            this.generator.generate("http://sausalito/mediawiki/index.php/Simple_section", pdfOutput);
        } finally {
            if (pdfOutput != null) {
                pdfOutput.close();
            }
        }
    }

}
