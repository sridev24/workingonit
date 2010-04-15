/*
 * Copyright 2009 Vladimir Ritz Bossicard
 *
 * This file is part of Configo.
 *
 * Configo is free software: you can redistribute it and/or modify it 
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
 * Version     : $Revision: 153 $
 * Last edit   : $Date: 2009-05-15 12:58:20 +0200 (Fri, 15 May 2009) $
 * Last editor : $Author: vbossica $
 */
package org.workingonit.configo;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;

import java.util.Properties;

import org.testng.annotations.Test;
import org.workingonit.addenda.commons.util.PropertiesUtils;
import org.workingonit.configo.model.Configuration;
import org.workingonit.configo.model.Module;

@Test
public class ConfigoTest {

    public void importProperties() throws Exception {
        Configo conf = new Configo("resources/definition.xml");
        conf.init();

        Properties props = PropertiesUtils.loadProperties("test/data/definition.properties");
        conf.importProperties(props);

        Configuration config = conf.getConfiguration();
        assertNotNull(config);

        Module modA = config.getModule("Module A");
        assertEquals("abc", modA.getProperty("key1").getValue());
        assertFalse(modA.getProperty("key1").isNewOne());

        Module modB = config.getModule("Module B");
        assertEquals("def", modB.getProperty("key2").getValue());
        assertFalse(modB.getProperty("key2").isNewOne());
    }

}
