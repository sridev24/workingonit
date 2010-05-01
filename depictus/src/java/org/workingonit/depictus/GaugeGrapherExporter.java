/*
 * Copyright 2008-2010 Vladimir Ritz Bossicard
 *
 * This file is part of WorkingOnIt Depictus.
 *
 * WorkingOnIt Depictus is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License
 * for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.workingonit.depictus;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 * Utility class that exports the result of a {@link GaugeGrapher}'s graphic
 * into an image.
 * 
 * @author Vladimir Ritz Bossicard
 */
public class GaugeGrapherExporter {

    /**
     * Creates an image with the given width and height and draws the graphs.
     * 
     * @param width
     *            resulting image's width
     * @param height
     *            resulting image's height
     * @param percentile
     *            completion value (from <code>0.0f</code> to <code>1.0f</code>)
     * @param label
     *            optional label to display near the arrowhead.
     * @return the image containing the graph
     */
    public BufferedImage export(final int width, final int height, final float percentile, final String label) {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = image.createGraphics();

        GaugeGrapher grapher = new GaugeGrapher();
        grapher.graph(graphics, width, height, percentile, label);

        return image;
    }

}
