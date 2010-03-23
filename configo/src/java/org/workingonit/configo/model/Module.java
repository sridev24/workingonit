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
 * Version     : $Revision: 131 $
 * Last edit   : $Date: 2009-04-21 13:26:37 +0200 (Tue, 21 Apr 2009) $
 * Last editor : $Author: vbossica $
 */
package org.workingonit.configo.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Vladimir Ritz Bossicard
 */
public class Module {

    private String name;

    private String description;

    private List<Property> properties = new ArrayList<Property>();

    public Module() {
        // required for serialization
    }

    public Module(final String name) {
        this.name = name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public List<Property> getProperties() {
        return this.properties;
    }

    public void setProperties(final List<Property> properties) {
        this.properties = properties;
    }

    public void addProperty(final Property property) {
        this.properties.add(property);
    }

    // TODO find something nicer
    public Property getProperty(final String name) {
        for (Property prop : this.properties) {
            if (prop.getKey().equals(name)) {
                return prop;
            }
        }
        return null;
    }

    /**
     * This method is used while deserializing the class from the XML file.
     *
     * http://xstream.codehaus.org/faq.html#Serialization_no_ctor_running
     */
    private Object readResolve() {
        if (this.properties == null) {
            this.properties = new ArrayList<Property>();
        }
        return this;
    }

}