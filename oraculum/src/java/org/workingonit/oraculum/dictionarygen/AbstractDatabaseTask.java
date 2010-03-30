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
 * Version     : $Revision: 299 $
 * Last edit   : $Date: 2009-12-13 21:22:42 +0100 (Sun, 13 Dec 2009) $
 * Last editor : $Author: vbossica $
 */
package org.workingonit.oraculum.dictionarygen;

import org.apache.tools.ant.Task;
import org.apache.tools.ant.types.Path;
import org.workingonit.addenda.ant.JavaCommandExecutor;

/**
 * Base class for Ant tasks dealing with database connections.
 * 
 * @author Vladimir Ritz Bossicard
 */
public abstract class AbstractDatabaseTask extends Task {

    protected String url;
    protected String username;
    protected String password;

    protected JavaCommandExecutor executor;

    public AbstractDatabaseTask() {
        this.executor = new JavaCommandExecutor(this, getProject());
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Path createClasspath() {
        return executor.createClasspath().createPath();
    }

    public void setClasspath(Path path) {
        createClasspath().append(path);
    }
    
}
