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
 * Class implementing this interface are responsible for returning the list of
 * {@link Color} interpolated between the <i>start</i> and <i>end</i> colors,
 * with the given <i>steps</i> number.
 * 
 * @author Vladimir Ritz Bossicard
 */
public interface ColorInterpolator {

    /**
     * Returns an array with the <code>steps</code> {@link Color}s interpolated
     * from the <code>start</code> to the <code>end</code> Color.
     * 
     * @param start
     *            starting color (far left side)
     * @param end
     *            ending color (far right side)
     * @param steps
     *            number of steps for calculating the sampling
     * @return the array containing the interpolated colors
     */
    Color[] interpolate(final Color start, final Color end, final int steps);

}
