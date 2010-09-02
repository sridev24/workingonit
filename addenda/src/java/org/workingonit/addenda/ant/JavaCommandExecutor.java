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
 * Version     : $Revision: 308 $
 * Last edit   : $Date: 2009-12-16 20:10:54 +0100 (Wed, 16 Dec 2009) $
 * Last editor : $Author: vbossica $
 */
package org.workingonit.addenda.ant;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.taskdefs.Execute;
import org.apache.tools.ant.taskdefs.ExecuteWatchdog;
import org.apache.tools.ant.taskdefs.LogStreamHandler;
import org.apache.tools.ant.types.CommandlineJava;
import org.apache.tools.ant.types.Path;

/**
 * Helper class to execute the <code>main</code> method of a given class within
 * an Ant {@link Task}. This is particularly useful when the classpath has to be
 * define in both the task definition and execution.
 * <p>
 * 
 * <b>Sample code:</b>
 * 
 * <pre>
 * public class MyTask extends Task {
 *
 *   private JavaCommandExecutor executor;
 *
 *   public MyTask() {
 *     this.executor = new JavaCommandExecutor(this, getProject());
 *   }
 *
 *   public Path createClasspath() {
 *     return executor.createClasspath().createPath();
 *   }
 *
 *   public void setClasspath(Path path) {
 *     createClasspath().append(path);
 *   }
 *
 *   public void execute() throws BuildException {
 *     int exitValue = this.executor.execute(MyMainClass.class, argument);
 *   }
 * }
 * </pre>
 * 
 * @author Vladimir Ritz Bossicard
 */
public class JavaCommandExecutor {
    
    private CommandlineJava cmd = new CommandlineJava();
    private Task task;
    private Project project;
    
    public JavaCommandExecutor(Task task, Project project) {
        this.task = task;
        this.project = project;
    }
    
    public Path createClasspath() {
        return cmd.createClasspath(project).createPath();
    }

    public int execute(Class<?> class2execute, String... args) {
        try {
            cmd.setClassname(class2execute.getName());

            for (String arg : args) {
                cmd.createArgument().setValue(arg);
            }
            Execute executor = new Execute(
                new LogStreamHandler(task, Project.MSG_INFO, Project.MSG_WARN), 
                new ExecuteWatchdog(5000l));
            executor.setCommandline(cmd.getCommandline());
            executor.setAntRun(project);

            return executor.execute();
        } catch (Exception ex) {
            throw new BuildException(ex);
        }
    }

}