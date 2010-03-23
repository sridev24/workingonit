/*
 * Copyright (C) 2008-2010 Vladimir Ritz Bossicard
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
 *
 * Version      : $Revision: 352 $
 * Last edit    : $Date: 2010-03-05 17:33:45 +0100 (Fri, 05 Mar 2010) $
 * Last editor  : $Author: vbossica $
 */
package org.workingonit.modulus.findings;

import java.io.Serializable;

/**
 *
 * @author Vladimir Ritz Bossicard
 */
public interface Finding extends Serializable {

    public enum Status {
        NEUTRAL,
        OK,
        WARNING,
        ERROR;
    };

    Status getStatus();

    String getMessage();

    String getCause();

    /**
     * Returns <code>true</code> if the finding is considered as a success.
     */
    boolean isSuccessful();

}