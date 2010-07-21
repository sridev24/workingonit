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
 * Version     : $Revision: 131 $
 * Last edit   : $Date: 2009-04-21 13:26:37 +0200 (Tue, 21 Apr 2009) $
 * Last editor : $Author: vbossica $
 */
package org.workingonit.ui;

import java.awt.Font;

/**
 * Utility class to deal with AWT components.
 * 
 * @author Vladimir Ritz Bossicard
 */
public class AwtUtils {

    /**
     * Creates and returns a new {@link Font} based on the base one, while
     * replacing the style with the new style.
     * 
     * @see {@link Font#getStyle()}
     */
    public static Font styleFont(Font font, int style) {
        return new Font(font.getFamily(), Font.BOLD, font.getSize());
    }

}
