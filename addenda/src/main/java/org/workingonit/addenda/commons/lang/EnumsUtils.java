/*
 * Copyright 2009-2010 Vladimir Ritz Bossicard
 *
 * This file is part of WorkingOnIt.
 *
 * WorkingOnIt is free software: you can redistribute it and/or modify it 
 * under the terms of the GNU General Public License as published by the Free 
 * Software Foundation, either version 3 of the License, or (at your option) 
 * any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for
 * more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 *
 * Version     : $Revision: 317 $
 * Last edit   : $Date: 2010-01-18 20:13:22 +0100 (Mon, 18 Jan 2010) $
 * Last editor : $Author: vbossica $
 */
package org.workingonit.addenda.commons.lang;

/**
 * Utility class to compare Java <code>enum</code>s.
 * 
 * @author Vladimir Ritz Bossicard
 */
public class EnumsUtils {

    /**
     * Returns the minimum (ordinal value) of two <code>Enum</code> values.
     */
    public static Enum minimum(Enum value1, Enum value2) {
        return value1.compareTo(value2) <= 0 ? value1 : value2;
    }

    /**
     * Returns the maximal (ordinal value) of two <code>Enum</code> values.
     */
    public static Enum maximum(Enum value1, Enum value2) {
        return value1.compareTo(value2) > 0 ? value1 : value2;
    }

}
