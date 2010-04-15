/*
 * Copyright 2009 Vladimir Ritz Bossicard
 * 
 * This file is part of Configo.
 *
 * Configo is free software: you can redistribute it and/or modify it 
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
 * Version     : $Revision: 133 $
 * Last edit   : $Date: 2009-04-21 22:00:12 +0200 (Tue, 21 Apr 2009) $
 * Last editor : $Author: vbossica $
 */
package org.workingonit.configo.swing;

import java.awt.Component;
import java.awt.Graphics;

import javax.swing.Icon;
import javax.swing.border.EmptyBorder;

/**
 * @author Vladimir Ritz Bossicard
 */
public class IconBorder extends EmptyBorder {

    private static final long serialVersionUID = 1L;

    protected Icon icon;

    public IconBorder(Icon icon) {
        super(0, 0, 0, icon.getIconWidth() + 5);
        this.icon = icon;
    }
    
    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        g.translate(x, y);
        
        this.icon.paintIcon(null, g, width - this.icon.getIconWidth(), 0);
    }

}
