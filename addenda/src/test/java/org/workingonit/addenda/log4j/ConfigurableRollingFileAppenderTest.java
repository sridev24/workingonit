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
 * Version     : $Revision: 273 $
 * Last edit   : $Date: 2009-09-15 20:49:19 +0200 (Tue, 15 Sep 2009) $
 * Last editor : $Author: vbossica $
 */
package org.workingonit.addenda.log4j;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

/**
 * Tests for the {@link ConfigurableRollingFileAppender} class.
 *
 * @author Vladimir Ritz Bossicard
 */
@Test
public class ConfigurableRollingFileAppenderTest {

    static {
        System.setProperty("log4j.outdir", "target");
    }

    private final static Logger logger = Logger.getLogger(ConfigurableRollingFileAppenderTest.class);

    /**
     * Checks that the files are rolled when full and that the filename is not
     * recursive :-)
     */
    public void rolling() {
        for (int i = 0; i < 1000; i++) {
            logger.info("this is juste a test");
        }
    }
}
