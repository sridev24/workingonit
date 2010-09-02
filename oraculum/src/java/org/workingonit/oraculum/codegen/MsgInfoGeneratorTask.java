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
 * Version     : $Revision: 352 $
 * Last edit   : $Date: 2010-03-05 17:33:45 +0100 (Fri, 05 Mar 2010) $
 * Last editor : $Author: vbossica $
 */
package org.workingonit.oraculum.codegen;

import java.io.File;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.types.Path;
import org.workingonit.addenda.ant.JavaCommandExecutor;

/**
 * Simple Ant task to execute the generation of files from the content of the
 * <code>msg_info</code> table.
 * 
 * @author Vladimir Ritz Bossicard
 */
public class MsgInfoGeneratorTask extends Task {

    private JavaCommandExecutor executor;

    private String url;
    private String username;
    private String password;
    private String template;
    private String module = "all";
    private File outFile;
    
    public MsgInfoGeneratorTask() {
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
    
    public void setTemplate(String template) {
        this.template = template;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public void setOutFile(File outFile) {
        this.outFile = outFile;
    }

    public Path createClasspath() {
        return executor.createClasspath().createPath();
    }

    public void setClasspath(Path path) {
        createClasspath().append(path);
    }

    @Override
    public void execute() throws BuildException {
        int exitValue = this.executor.execute(MsgInfoGenerator.class, 
                this.url, this.username, this.password, this.template, this.module, this.outFile.getAbsolutePath());
    }
    
}
