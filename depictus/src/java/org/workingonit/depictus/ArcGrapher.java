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

import static java.awt.BasicStroke.CAP_ROUND;
import static java.awt.BasicStroke.JOIN_ROUND;
import static java.awt.Color.GREEN;
import static java.awt.Color.RED;
import static java.awt.Color.WHITE;
import static java.awt.RenderingHints.KEY_ANTIALIASING;
import static java.awt.RenderingHints.KEY_TEXT_ANTIALIASING;
import static java.awt.RenderingHints.VALUE_ANTIALIAS_ON;
import static java.awt.RenderingHints.VALUE_TEXT_ANTIALIAS_ON;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

/**
 * Class responsible for drawing the color arc. The values {@link #startAngle},
 * {@link #optimumSize}, {@link #diameter} and {@link #thickness} have been
 * empirically found by comparing various values with the GOM result. Their
 * respective default values correspond to the GOM ones, so there's no need to
 * tweak these.
 * 
 * @author Vladimir Ritz Bossicard
 */
public class ArcGrapher {

    private ColorInterpolator sampler = new HueColorSampler();

    /**
     * Defaulted to 22 (just like GOM).
     */
    private int startAngle = 22;

    /**
     * Defaulted to 300 (just like GOM).
     */
    private int optimumSize = 300;

    /**
     * Diameter of the arc, from the arrow's foot. Defaulted to 190 (just like
     * GOM).
     */
    private int diameter = 190;

    /**
     * Defaulted to 11 (just like GOM).
     */
    private int steps = 11;

    /**
     * Defaulted to 30.
     */
    private int thickness = 30;

    /**
     * Defaulted to {@link RED}.
     */
    private Color startColor = RED;

    /**
     * Defaulted to {@link GREEN}.
     */
    private Color endColor = GREEN;

    public void setColorSampler(final ColorInterpolator sampler) {
        this.sampler = sampler;
    }

    public void setThickness(final int thickness) {
        this.thickness = thickness;
    }

    public void setStartColor(final Color startColor) {
        this.startColor = startColor;
    }

    public void setEndColor(final Color endColor) {
        this.endColor = endColor;
    }

    public void setDiameter(final int diameter) {
        this.diameter = diameter;
    }

    public int getDiameter() {
        return this.diameter;
    }

    /**
     * Returns the optimum size (height, width) for which the default values
     * have been calculated. The entire graph has been calibrated for a specific
     * size, so that the arrows and text look nicer so when you want to scale
     * the entire graphic (everything except the text, of course), you need to
     * know how every scales. By getting the theoretical maximum size of a
     * graphic, you can later scale everything accordingly. The value is
     * defaulted to 300 (just like GOM).
     */
    public int getOptimumSize() {
        return this.optimumSize;
    }

    public void setStartAngle(final int angle) {
        this.startAngle = angle;
    }

    public int getStartAngle() {
        return this.startAngle;
    }

    /**
     * Draws the colored graph into the given graphics.
     * 
     * @param graphics the Java2D graphics to draw into
     */
    public void graph(final Graphics2D graphics) {
        graphics.setStroke(new BasicStroke(7.0f, CAP_ROUND, JOIN_ROUND));

        graphics.setRenderingHint(KEY_TEXT_ANTIALIASING, VALUE_TEXT_ANTIALIAS_ON);
        graphics.setRenderingHint(KEY_ANTIALIASING, VALUE_ANTIALIAS_ON);

        /*
         * Once we have found out all the colors that must fill the arc, we
         * simply draw a full arc with the given color.
         */
        Color[] colors = this.sampler.interpolate(this.startColor, this.endColor, this.steps);
        float delta = ((float) (180 - 2*this.startAngle) / (float) colors.length);

        int angle = this.startAngle;
        for (int i = 0; i < colors.length-1; i++) {
            int resultingAngle = this.startAngle + Math.round((i+1) * delta);
            graphics.setPaint(colors[i]);
            graphics.fillArc(0, 0, this.diameter, this.diameter, angle, resultingAngle - angle);
            angle = resultingAngle;
        }
        graphics.setPaint(colors[colors.length - 1]);
        graphics.fillArc(0, 0, this.diameter, this.diameter, angle, 180 - this.startAngle - angle);

        // paint the inner circle in white to let the arc appear
        graphics.translate(this.thickness, this.thickness + 1);
        graphics.setPaint(WHITE);
        graphics.fillArc(0, 0, this.diameter - (2 * this.thickness), this.diameter - (2 * this.thickness), 0, 180);

        // don't forget to return to the previous location!
        graphics.translate(-this.thickness, -this.thickness);
    }

}