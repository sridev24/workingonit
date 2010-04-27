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
 * Version     : $Revision: 120 $
 * Last edit   : $Date: 2009-04-20 21:09:15 +0200 (Mon, 20 Apr 2009) $
 * Last editor : $Author: vbossica $
 */
package org.workingonit.configo.model;

import java.io.Writer;

import com.thoughtworks.xstream.XStream;

/**
 *
 * @author Vladimir Ritz Bossicard
 */
public class ConfigurationDao {

    private XStream xstream = new XStream();

    public ConfigurationDao() {
        this.xstream.alias("configuration", Configuration.class);
        this.xstream.alias("module", Module.class);
        this.xstream.alias("property", Property.class);
    }

    public void toXml(final Configuration config, final Writer out) {
        this.xstream.toXML(config, out);
    }

    public Configuration fromXml(final String xml) {
        return (Configuration) this.xstream.fromXML(xml);
    }

}
