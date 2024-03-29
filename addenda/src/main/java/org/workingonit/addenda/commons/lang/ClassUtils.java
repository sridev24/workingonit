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
 * Version     : $Revision: 313 $
 * Last edit   : $Date: 2010-01-04 21:30:57 +0100 (Mon, 04 Jan 2010) $
 * Last editor : $Author: vbossica $
 */
package org.workingonit.addenda.commons.lang;

/**
 * Utility class to deal with {@link Class}.
 * 
 * @author Vladimir Ritz Bossicard
 */
public class ClassUtils {

    public static String getImplementationVersion(Class cls) {
        return cls.getPackage().getImplementationVersion();
    }

}