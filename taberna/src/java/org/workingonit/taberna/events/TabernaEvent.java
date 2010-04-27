/*
 * Copyright 2010 Vladimir Ritz Bossicard
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
 * Version     : $Revision: 351 $
 * Last edit   : $Date: 2010-03-01 21:31:28 +0100 (Mon, 01 Mar 2010) $
 * Last editor : $Author: vbossica $
 */
package org.workingonit.taberna.events;

import org.springframework.context.ApplicationEvent;

/**
 * @author Vladimir Ritz Bossicard
 */
public class TabernaEvent extends ApplicationEvent {

    private static final long serialVersionUID = 1L;

    public TabernaEvent(Object source) {
        super(source);
    }

}
