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

import java.awt.Color;

/**
 * A specific <code>ColorInterpolator</code> that interpolates the color
 * depending on their respective hue.
 * 
 * @author Vladimir Ritz Bossicard
 */
public class HueColorSampler implements ColorInterpolator {

    /**
     * Defaulted to 1.0f.
     */
    private float saturation = 1.0f;

    /**
     * Defaulted to 0.95f.
     */
    private float brightness = 0.95f;

    public void setSaturation(final float saturation) {
        this.saturation = saturation;
    }

    public void setBrightness(final float brightness) {
        this.brightness = brightness;
    }

    /**
     * Returns the list of <code>steps</code> {@link Color}s interpolated from
     * the <code>start</code> to the <code>end</code> Color.
     *
     * @param start
     *            starting color (far left side)
     * @param end
     *            ending color (far right side)
     * @param steps
     *            number of steps for calculating the sampling
     */
    public Color[] interpolate(final Color start, final Color end, final int steps) {
        Color[] res = new Color[steps + 2];

        float[] endHsb = Color.RGBtoHSB(end.getRed(), end.getGreen(),
                end.getBlue(), null);
        float[] startHsb = Color.RGBtoHSB(start.getRed(), start.getGreen(), start
                .getBlue(), null);

        float delta = (startHsb[0] - endHsb[0]) / (steps + 2);

        res[0] = Color.getHSBColor(endHsb[0], this.saturation, this.brightness);
        for (int i = 0; i < steps; i++) {
            float H = endHsb[0] + (i * delta);
            res[i + 1] = Color.getHSBColor(H, this.saturation, this.brightness);
        }
        res[res.length - 1] = Color.getHSBColor(startHsb[0], this.saturation, this.brightness);

        return res;
    }

}
