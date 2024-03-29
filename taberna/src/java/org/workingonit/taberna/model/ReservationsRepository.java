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
 * Version     : $Revision: 344 $
 * Last edit   : $Date: 2010-02-16 21:58:48 +0100 (Tue, 16 Feb 2010) $
 * Last editor : $Author: vbossica $
 */
package org.workingonit.taberna.model;

/**
 * @author Vladimir Ritz Bossicard
 */
public interface ReservationsRepository {

    void add(Integer number, Reservation booking);

    Reservation get(Integer number, Integer week);

    void delete(Integer number, Integer week);

}
