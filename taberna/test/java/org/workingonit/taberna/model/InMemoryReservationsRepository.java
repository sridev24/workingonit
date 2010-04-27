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
 * Version     : $Revision: 349 $
 * Last edit   : $Date: 2010-02-26 10:53:56 +0100 (Fri, 26 Feb 2010) $
 * Last editor : $Author: vbossica $
 */
package org.workingonit.taberna.model;

import static org.workingonit.taberna.model.InMemoryRoomsRepository.BELLEVUE;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.InitializingBean;

/**
 * @author Vladimir Ritz Bossicard
 */
public class InMemoryReservationsRepository implements ReservationsRepository, InitializingBean {

    private Map<String, Reservation> bookings = new HashMap<String, Reservation>();

    @Override
    public void add(Integer room, Reservation reservation) {
        System.out.println("adding reservation for room " + room + " and week " + reservation.getWeek());
        String key = key(room, reservation.getWeek());
        if (!this.bookings.containsKey(key)) {
            this.bookings.put(key, reservation);
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        add(BELLEVUE, new Reservation("toto", 40));
        add(BELLEVUE, new Reservation("tata", 41));
    }

    @Override
    public Reservation get(Integer room, Integer week) {
        return this.bookings.get(key(room, week));
    }

    @Override
    public void delete(Integer room, Integer week) {
        this.bookings.remove(key(room, week));
    }

    private String key(Integer room, Integer week) {
        return room + "-" + week;
    }

}
