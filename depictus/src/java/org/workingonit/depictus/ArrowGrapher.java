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
package org.workingonit.depictus;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.geom.Rectangle2D;

/**
 * Class responsible for drawing the arrow and the (optional) label.
 *
 * @author Vladimir Ritz Bossicard
 */
public class ArrowGrapher {

    private static final int DEFAULT_ARROWHEAD = 27;
    private static final Font DEFAULT_FONT = new Font("Helvetica", Font.PLAIN, 12);

    private int headSize = DEFAULT_ARROWHEAD;
    private Font font = DEFAULT_FONT;

    public void setHeadSize(final int headSize) {
        this.headSize = headSize;
    }

    public void setLabelFont(final Font font) {
        this.font = font;
    }

    /**
     * Similar to calling
     * {@link #graph(Graphics2D, double, int, String, double)} with a
     * <code>null</code> label.
     * 
     * @param graphics
     *            the Java2D graphics to draw into
     * @param angle
     *            arrow's draw angle
     * @param length
     *            length of the arrow, arrowhead <b>not</b> included
     * @see #graph(Graphics2D, double, int, String, double)
     */
    public void graph(final Graphics2D graphics, final double angle, final int length) {
        graph(graphics, angle, length, null, 1);
    }

    /**
     * Draws the arrow, including the arrowhead onto the {@link Graphics2D}
     * object. The label is optional and can be <code>null</code> but take care
     * that the label is correctly displayed for corner cases (typically for
     * small and large angles).
     * 
     * @param graphics
     *            the Java2D graphics to draw into
     * @param angle
     *            arrow's draw angle
     * @param length
     *            length of the arrow, arrowhead <b>not</b> included
     * @param label
     *            optional label to display near the arrowhead.
     * @param ratio
     *            ratio by which the general arc was "shrunk". This is necessary
     *            to keep the font with the same size.
     */
    public void graph(final Graphics2D graphics, final double angle, final int length, final String label, final double ratio) {
        /*
         * Note that it is important to revert all the translations
         */

        // draws the baseline
        graphics.rotate(-Math.toRadians(angle));
        graphics.drawLine(0, 0, length, 0);

        graphics.translate(length, - (this.headSize / 2));
        Polygon head = createArrowHead(this.headSize);
        graphics.fillPolygon(head);


        if (label != null) {
            int space = this.headSize + 4;
            graphics.translate(space, this.headSize / 2);

            graphics.rotate(Math.toRadians(angle));

            graphics.setFont(this.font);
            FontMetrics fm = graphics.getFontMetrics();
            Rectangle2D rect = fm.getStringBounds(label, graphics);

            int delta = (int) (- (angle / 180) * rect.getWidth());

            // FIXME explain it!
            graphics.scale(1/ratio, 1/ratio);
            graphics.drawString(label, delta, 0);
            graphics.scale(ratio, ratio);

            graphics.rotate(-Math.toRadians(angle));

            graphics.translate(-space, -this.headSize / 2);
        }

        // important to return to the initial location
        graphics.translate(-length, this.headSize / 2);
        graphics.rotate(Math.toRadians(angle));
    }

    /**
     * Returns a {@link Polygon} representing the arrow's head. Note that the
     * head needs to be horizontal.
     *
     * @param size
     *            the size of the head.
     */
    protected Polygon createArrowHead(final int size) {
        Polygon res = new Polygon();
        res.addPoint(0, 0);
        res.addPoint(0, size);
        res.addPoint(size, size/2);

        return res;
    }

}