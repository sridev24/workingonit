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
public class Configuration {

    private String name;
    private String description;
    private String version;
    private List<Module> modules = new ArrayList<Module>();

    public void setName(final String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public String getVersion() {
        return this.version;
    }

    public void setVersion(final String version) {
        this.version = version;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public void setModules(final List<Module> modules) {
        this.modules = modules;
    }

    public void addModule(final Module module) {
        this.modules.add(module);
    }

    public List<Module> getModules() {
        return this.modules;
    }

    // TODO find something nicer
    public Module getModule(final String name) {
        for (Module module : this.modules) {
            if (module.getName().equals(name)) {
                return module;
            }
        }
        return null;
    }

    private Object readResolve() {
        if (this.modules == null) {
            this.modules = new ArrayList<Module>();
        }
        return this;
    }

}
