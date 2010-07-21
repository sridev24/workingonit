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
package org.workingonit.taberna.services;

import static org.testng.Assert.assertEquals;
import static org.workingonit.taberna.model.InMemoryRoomsRepository.BELLA_DONNA;
import static org.workingonit.taberna.model.StatusEnum.PUBLISHED;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;
import org.workingonit.taberna.model.Reservation;
import org.workingonit.taberna.model.ReservationsRepository;

/**
 * @author Vladimir Ritz Bossicard
 */
@ContextConfiguration(locations = "classpath:org/workingonit/taberna/context-test.xml")
@Test
public class BookingServiceTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private BookingEngine booker;

    @Autowired
    private ReservationsRepository bookings;

    public void book_room() {
        this.booker.book(BELLA_DONNA, new Reservation("harry", 48));

        Reservation temp = this.bookings.get(BELLA_DONNA, 48);
        assertEquals(temp.getName(), "harry");
        assertEquals(temp.getStatus(), PUBLISHED);
    }

    public void doublebook_room() {
        this.booker.book(BELLA_DONNA, new Reservation("sally", 49));
        this.booker.book(BELLA_DONNA, new Reservation("harry", 49));

        Reservation temp = this.bookings.get(BELLA_DONNA, 49);
        assertEquals(temp.getName(), "sally");
        assertEquals(temp.getStatus(), PUBLISHED);
    }

    public void cancel_room() {
        this.booker.book(BELLA_DONNA, new Reservation("sally", 50));
        this.booker.cancel(BELLA_DONNA, new Reservation("sally", 50));
        this.booker.book(BELLA_DONNA, new Reservation("harry", 50));

        Reservation temp = this.bookings.get(BELLA_DONNA, 50);
        assertEquals(temp.getName(), "harry");
        assertEquals(temp.getStatus(), PUBLISHED);
    }

}