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
package org.workingonit.addenda.commons.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Utility class to deal with {@link InputStream}.
 * 
 * @author Vladimir Ritz Bossicard
 */
public class InputStreamUtils {

    /**
     * Reads the input stream and concatenates its content, separating the lines
     * with a line feed ('\n').
     * 
     * @param in
     *            the input stream
     * @return A String containing the contents of the input stream
     * @throws IOException
     *             if an I/O error occurs
     */
    public static String readInputStreamToString(final InputStream in)
            throws IOException {
        if (in == null) {
            throw new IllegalArgumentException("Input stream must not be null");
        }
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(in));

        try {
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line + "\n");
            }
        } finally {
            bufferedReader.close();
        }
        return stringBuilder.toString();
    }

}
