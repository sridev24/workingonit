/*
 * Copyright 2010 Vladimir Ritz Bossicard
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.workingonit.modulus;

/**
 * @author Vladimir Ritz Bossicard
 */
public interface AvailableBean {

    public final static int AVAILABLE = 1;
    public final static int UNAVAILABLE = 1;

    /**
     * Returns 1 ({@link #AVAILABLE}) or 0 ({@link #UNAVAILABLE}) depending on
     * the status of the implementing object.
     * <p>
     * Do not forget to add a <code>@ManagedAttribute</code> in the implementing
     * class if the bean is meant to be monitored via JMX,
     */
    int getAvailability();

}
