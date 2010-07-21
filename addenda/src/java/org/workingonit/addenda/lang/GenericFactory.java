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
 * Version     : $Revision: 245 $
 * Last edit   : $Date: 2009-07-15 12:24:14 +0200 (Wed, 15 Jul 2009) $
 * Last editor : $Author: vbossica $
 */
package org.workingonit.addenda.lang;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Factory pattern taken from Joshua Bloch - Effective Java, 2nd edition.<p>
 * 
 * <b>Usage</b></p>
 * <pre>
 *   import static org.workingonit.addenda.lang.GenericFactory.*;
 * 
 *   List<T> list = newArrayList();
 * </pre>
 * 
 * @author Vladimir Ritz Bossicard
 */
public class GenericFactory {

    public static <K, V> HashMap<K, V> newHashMap() {
        return new HashMap<K, V>();
    }

    public static <E> HashSet<E> newHashSet() {
        return new HashSet<E>();
    }

    public static <E> ArrayList<E> newArrayList() {
        return new ArrayList<E>();
    }

}
