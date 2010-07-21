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
package org.workingonit.configo.model;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import java.io.InputStream;
import java.io.PrintWriter;

import org.testng.annotations.Test;
import org.workingonit.addenda.commons.io.InputStreamUtils;

/**
 *
 * @author Vladimir Ritz Bossicard
 */
@Test
public class ConfigurationDaoTest {

    public void saveConfiguration() {
        ConfigurationDao dao = new ConfigurationDao();

        Configuration config = new Configuration();
        config.setName("the config");

        Module module = new Module("test module");
        config.addModule(module);

        Property prop = new Property();
        prop.setKey("org.wkg.key1");
        module.addProperty(prop);

        dao.toXml(config, new PrintWriter(System.out));
    }

    public void loadConfiguration() {
        ConfigurationDao dao = new ConfigurationDao();

        Configuration config = dao.fromXml("<configuration><name>Hello</name><modules><module><name>abc</name></module></modules></configuration>");
        assertNotNull(config);
        assertEquals("Hello", config.getName());
    }

    public void loadClasspathConfiguration() throws Exception {
        InputStream stream = getClass().getClassLoader().getResourceAsStream("resources/definition.xml");
        String content = InputStreamUtils.readInputStreamToString(stream);
        assertNotNull(content);

        System.out.println(content);
    }
}
