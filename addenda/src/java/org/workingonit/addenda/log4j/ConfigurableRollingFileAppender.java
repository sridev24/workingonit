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
 * Version     : $Revision: 275 $
 * Last edit   : $Date: 2009-09-16 07:52:09 +0200 (Wed, 16 Sep 2009) $
 * Last editor : $Author: vbossica $
 */
package org.workingonit.addenda.log4j;

import java.io.IOException;

import org.apache.log4j.RollingFileAppender;

/**
 * See {@link ConfigurableFileAppender} for documentation.
 * 
 * @author Vladimir Ritz Bossicard
 */
public class ConfigurableRollingFileAppender extends RollingFileAppender {

    // note that the setFile(String file) should not be overridden!
    
    @Override
    public synchronized void setFile(String fileName, boolean append,
            boolean bufferedIO, int bufferSize) throws IOException {
        super.setFile(ConfigurableFileAppender.getOutputFile(fileName), append, bufferedIO, bufferSize);
    }
    
}