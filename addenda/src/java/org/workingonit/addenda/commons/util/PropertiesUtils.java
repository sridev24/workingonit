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
 * Version     : $Revision: 232 $
 * Last edit   : $Date: 2009-07-09 16:56:36 +0200 (Thu, 09 Jul 2009) $
 * Last editor : $Author: vbossica $
 */
package org.workingonit.addenda.commons.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Utility class to deal with {@link Properties}.
 * 
 * @author Vladimir Ritz Bossicard
 */
public class PropertiesUtils {

    /**
     * Returns a new {@link Properties} object loaded with the content of the
     * file defined by the given name.
     * 
     * @param name
     *            the system-dependent file name.
     * @throws IOException
     *             if an I/O error occurs
     */
    public static Properties loadProperties(String name) throws IOException {
        Properties props = new Properties();
        InputStream stream = null;
        try {
            stream = new FileInputStream(name);
            props.load(stream);
        } finally {
            if (stream != null) {
                stream.close();
            }
        }
        return props;
    }

    /**
     * Returns a new {@link Properties} object loaded with the content of the
     * given file.
     * 
     * @param file
     *            the system-dependent file.
     * @throws IOException
     *             if an I/O error occurs
     */
    public static Properties loadProperties(File file) throws IOException {
        Properties props = new Properties();
        InputStream stream = null;
        try {
            stream = new FileInputStream(file);
            props.load(stream);
        } finally {
            if (stream != null) {
                stream.close();
            }
        }
        return props;
    }

}
