/*
 * Copyright 2009 Vladimir Ritz Bossicard
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
 * Version     : $Revision: 352 $
 * Last edit   : $Date: 2010-03-05 17:33:45 +0100 (Fri, 05 Mar 2010) $
 * Last editor : $Author: vbossica $
 */
package org.workingonit.addenda.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * Specific {@link Map} that caches the values of the {@link Enum}s.
 * <p>
 * The code is taken from <a
 * href="http://www.javaspecialists.eu/archive/Issue113.html">Java Specialists
 * #113</a>.
 * 
 * @author Vladimir Ritz Bossicard
 */
public class ReverseEnumMap<V extends Enum<V> & EnumConverter<?>> {

    private Map<Byte, V> map = new HashMap<Byte, V>();

    @SuppressWarnings("boxing")
    public ReverseEnumMap(Class<V> valueType) {
        for (V v : valueType.getEnumConstants()) {
            this.map.put(v.convert(), v);
        }
    }

    @SuppressWarnings("boxing")
    public V get(byte num) {
        return this.map.get(num);
    }

}