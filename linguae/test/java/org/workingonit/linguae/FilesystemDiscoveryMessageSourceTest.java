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
 * Version     : $Revision: 340 $
 * Last edit   : $Date: 2010-02-09 08:47:28 +0100 (Tue, 09 Feb 2010) $
 * Last editor : $Author: vbossica $
 */
package org.workingonit.linguae;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.Locale;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

/**
 * @author Vladimir Ritz Bossicard
 */
@ContextConfiguration (
    locations = "classpath:org/workingonit/linguae/context-test.xml"
)
public class FilesystemDiscoveryMessageSourceTest extends AbstractTestNGSpringContextTests {

    private final static File outDir = new File("target/test/sample");

    @Autowired
    @Qualifier("messageSource")
    private FilesystemDiscoveryMessageSource source;

    @BeforeSuite
    public void setup() throws IOException {
        outDir.mkdirs();
        FileUtils.cleanDirectory(outDir);
    }

    @Test
    public void sampleUsage() throws IOException {
        File input = new File("test/data");
        FileUtils.copyFileToDirectory(new File(input, "labels_fr.properties"), outDir);
        this.source.clearCache();
        assertEquals(1, this.source.getLocales().size());
        assertEquals(this.source.getMessage("org.wkg.linguae.greetings", null, Locale.FRENCH), "bonjour!");

        FileUtils.copyFileToDirectory(new File(input, "labels_en.properties"), outDir);
        this.source.clearCache();
        assertEquals(2, this.source.getLocales().size());
        assertEquals(this.source.getMessage("org.wkg.linguae.greetings", null, Locale.ENGLISH), "hello!");

        new File(outDir, "labels_en.properties").delete();
        FileUtils.copyFile(new File(input, "labels_fr_updated.properties"), new File(outDir, "labels_fr.properties"));
        this.source.clearCache();
        assertEquals(1, this.source.getLocales().size());
        assertEquals(this.source.getMessage("org.wkg.linguae.greetings", null, Locale.FRENCH), "salut!");
    }

}