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

import com.thoughtworks.xstream.annotations.XStreamOmitField;

/**
 *
 * @author Vladimir Ritz Bossicard
 */
public class Property {

    /**
     * Logical name for the property. This value will be presented to the user
     * and will hide the more technical <code>key</code> value.
     */
    private String name;
    private String key;
    private String description;
    @XStreamOmitField
    private String value;
    @XStreamOmitField
    private boolean newOne = false;

    public void setName(final String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public String getKey() {
        return this.key;
    }
    public void setKey(final String key) {
        this.key = key;
    }

    public String getDescription() {
        return this.description;
    }
    public void setDescription(final String description) {
        this.description = description;
    }

    public void setValue(final String value) {
        this.value = value;
    }
    public String getValue() {
        return this.value;
    }

    public boolean isNewOne() {
        return this.newOne;
    }
    public void setNewOne(final boolean newOne) {
        this.newOne = newOne;
    }

    private Object readResolve() {
        this.newOne = false;
        return this;
    }
}
