/*
 * Copyright 2009 Vladimir Ritz Bossicard
 * 
 * This file is part of WorkingOnIt.
 *
 * WorkingOnIt is free software: you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 * PARTICULAR PURPOSE. See the GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License along
 * with this program. If not, see <http://www.gnu.org/licenses/>.
 *
 * Version     : $Revision: 120 $
 * Last edit   : $Date: 2009-04-20 21:09:15 +0200 (Mon, 20 Apr 2009) $
 * Last editor : $Author: vbossica $
 */
package org.workingonit.ui;

import java.net.URL;

import javax.swing.ImageIcon;

/**
 * Utility class to deal with Swing components.
 * 
 * @author Vladimir Ritz Bossicard
 */
public class SwingUtils {

    /**
     * Returns an {@link ImageIcon}, or null if the path was invalid.
     */
    public static ImageIcon createImageIcon(Class<?> parent, String path, String description) {
        URL imgURL = parent.getClassLoader().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL, description);
        }
        System.err.println("Couldn't find file: " + path);
        return null;
    }
    
}
