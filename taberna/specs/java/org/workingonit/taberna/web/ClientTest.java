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
package org.workingonit.taberna.web;

import static org.testng.Assert.assertEquals;
import static org.workingonit.taberna.model.InMemoryRoomsRepository.BELLA_DONNA;
import static org.workingonit.taberna.model.InMemoryRoomsRepository.BELLEVUE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;
import org.workingonit.taberna.model.Reservation;
import org.workingonit.taberna.model.Room;

/**
 * @author Vladimir Ritz Bossicard
 */
@ContextConfiguration("classpath:org/workingonit/taberna/context-specs-test.xml")
public class ClientTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private Client client;

    @Test(enabled = true, timeOut = 12000)
    public void get_room() {
        Room room = this.client.getRoom(BELLA_DONNA);
        assertEquals(room.getName(), "bella donna");
    }

    @Test(enabled = true, timeOut = 12000)
    public void add_room() {
        Room room = new Room(99999, "prima luna", 250);

        int current = this.client.getRooms().length;
        this.client.addRoom(room);
        assertEquals(this.client.getRooms().length, current + 1);

        this.client.deleteRoom(99999);
        assertEquals(this.client.getRooms().length, current);
    }

    @Test(enabled = true, timeOut = 12000)
    public void get_booking() {
        Reservation booking = this.client.getBooking(BELLEVUE, 40);
        assertEquals(booking.getName(), "toto");
    }

    @Test(enabled = true, timeOut = 1200000)
    public void add_booking() {
        this.client.addBooking(BELLEVUE, new Reservation("titeuf", 15));
    }

}
