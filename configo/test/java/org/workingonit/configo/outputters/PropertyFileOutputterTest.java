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
package org.workingonit.configo.outputters;

import java.io.File;
import java.util.Properties;

import org.testng.annotations.Test;
import org.workingonit.addenda.commons.util.PropertiesUtils;
import org.workingonit.configo.Configo;

/**
 * @author Vladimir Ritz Bossicard
 */
@Test
public class PropertyFileOutputterTest {

    public void output() throws Exception {
        Configo conf = new Configo("resources/definition.xml");
        conf.init();

        Properties props = PropertiesUtils.loadProperties("test/data/definition.properties");
        conf.importProperties(props);

        File out = new File("target/test.props");
        conf.generate(out);
    }

}
