/*
 * Copyright 2008-2009 Vladimir Ritz Bossicard
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
 * Version     : $Revision: 155 $
 * Last edit   : $Date: 2009-05-18 22:53:11 +0200 (Mon, 18 May 2009) $
 * Last editor : $Author: vbossica $
 */
package org.workingonit.test.utils;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

/**
 * Template class that saved a {@link Graphics2D} into a file.
 * 
 * @author Vladimir Ritz Bossicard
 */
public abstract class Graphics2DFileOutputterTemplate {

    protected final static int DEFAULT_WIDTH = 300;
    protected final static int DEFAULT_HEIGHT = 300;

    public void process(final int width, final int height, final String filename) throws Exception {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = image.createGraphics();

        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, image.getHeight(), image.getWidth());
        graphics.setPaint(Color.BLACK);

        try {
            doWithGraphics(graphics);
        } finally {
            ImageIO.write(image, "png", new File(filename));
        }
    }

    public void process(final String filename) throws Exception {
        process(DEFAULT_WIDTH, DEFAULT_WIDTH, filename);
    }

    protected abstract void doWithGraphics(Graphics2D graphics) throws Exception;

}
