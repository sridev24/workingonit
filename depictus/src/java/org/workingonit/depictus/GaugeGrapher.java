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

import static java.awt.Color.BLACK;
import static java.awt.Color.WHITE;

import java.awt.Graphics2D;

/**
 * Class responsible for drawing the entire graph. Delegates to the
 * {@link ArcGrapher} and {@link ArrowGrapher} classes.
 *
 * @author Vladimir Ritz Bossicard
 */
public class GaugeGrapher {

    private final static int BOTTOM_BORDER = 6;
    
    private ArcGrapher arc = new ArcGrapher();
    private ArrowGrapher arrow = new ArrowGrapher();

    public void setArcGrapher(final ArcGrapher arc) {
        this.arc = arc;
    }

    public void setArrowGrapher(final ArrowGrapher arrow) {
        this.arrow = arrow;
    }

    /**
     * Draws the arc and the arrow.
     *
     * @param graphics
     *            the Java2D graphics to draw into
     * @param width
     *            width of the image containing the graph
     * @param height
     *            height of the image containing the graph
     * @param percentile
     *            completion value (from <code>0.0f</code> to <code>1.0f</code>)
     * @param label
     *            optional label to display near the arrowhead.
     */
    public void graph(final Graphics2D graphics, final int width, final int height, final float percentile, final String label) {
        graphics.setColor(WHITE);
        graphics.fillRect(0, 0, width, height);
        graphics.setPaint(BLACK);

        /*
         * before starting to draw the single components, it is necessary to
         * obtain the ratio between the desired size and the optimal one (that
         * has been quite arbitrarily defined (by comparing the results with the
         * GOM one), otherwise everything will be a little bit off.
         * 
         * Using this ratio, the position of the arc is calculated (upper left
         * corner). I found it trivial to do the translation *before* the
         * graphic has been scaled.
         */
        double ratio = 1.0d;
        if ((float) height/width <= 0.75) { // the 0.75 is arbitrary!
            ratio = (float) width / this.arc.getOptimumSize();
        } else {
            ratio = (float) Math.min(width, height) / this.arc.getOptimumSize();
        }
        int posx = (int) ((width - this.arc.getDiameter() * ratio ) / 2);
        int posy = height - ((int) (this.arc.getDiameter()/2 * ratio));

        graphics.translate(posx, posy - BOTTOM_BORDER); // for letting some border

        /*
         * the entire graphic will now be scaled accordingly so that we don't
         * have to play with the ratio anymore, which simplifies the calculation
         * a bit...
         * 
         * the ratio MUST be the same horizontally and vertically otherwise the
         * image will be distorted.
         */
        graphics.scale(ratio, ratio);

        this.arc.graph(graphics);

        graphics.translate(this.arc.getDiameter() / 2, this.arc.getDiameter() / 2);
        graphics.setColor(BLACK);

        double angle = (180 - this.arc.getStartAngle()) - percentile * (180 - (2 * this.arc.getStartAngle()));

        this.arrow.graph(graphics, angle, (int) (this.arc.getDiameter()/2 * 1.10), label, ratio);

        // don't forget to return to the previous location!
        graphics.translate(-this.arc.getDiameter() / 2, -height / 2);
    }

}