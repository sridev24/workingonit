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
package org.workingonit.taberna.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.workingonit.taberna.model.Reservation;
import org.workingonit.taberna.model.ReservationsRepository;
import org.workingonit.taberna.model.Room;
import org.workingonit.taberna.model.RoomsRepository;
import org.workingonit.taberna.services.BookingEngine;

/**
 * @author Vladimir Ritz Bossicard
 */
@Controller
public class RoomController {

    @Autowired
    private RoomsRepository rooms;

    @Autowired
    private ReservationsRepository bookings;

    @Autowired
    private BookingEngine booker;

    @RequestMapping(value = "/rooms/", method = RequestMethod.GET)
    public @ResponseBody Room[] getRooms() {
        return this.rooms.getAll().toArray(new Room[] {});
    }

    @RequestMapping(value = "/rooms/{id}", method = RequestMethod.GET)
    public @ResponseBody Room getRoom(@PathVariable String id) {
        return this.rooms.get(Integer.valueOf(id));
    }

    @RequestMapping(value = "/rooms/", method = RequestMethod.POST)
    public @ResponseBody String add(@RequestBody Room room) {
        this.rooms.add(room);

        return "redirect:/rooms/" + room.getNumber();
    }

    @RequestMapping(value = "/rooms/{id}", method = RequestMethod.DELETE)
    public @ResponseBody String deleteRoom(@PathVariable String id) {
        this.rooms.delete(Integer.valueOf(id));

        return "redirect:/rooms/";
    }

    @RequestMapping(value = "/rooms/{number}/bookings/{week}", method = RequestMethod.GET)
    public @ResponseBody Reservation getBooking(@PathVariable("number") Integer number, @PathVariable("week") Integer week) {
        return this.bookings.get(number, week);
    }

    @RequestMapping(value = "/rooms/{number}/bookings/", method = RequestMethod.POST)
    public @ResponseBody String addBooking(@PathVariable("number") Integer number, @RequestBody Reservation booking) {
        System.out.println("booking room: " + number + " (" + booking + ")");
        this.booker.book(number, booking);

        System.out.println("redirecting to '/rooms/" + number + "/bookings/" + booking.getWeek() + "'");
        return "redirect:/rooms/" + number + "/bookings/" + booking.getWeek();
    }

    @RequestMapping(value = "/rooms/{number}/bookings/{week}", method = RequestMethod.DELETE)
    public @ResponseBody String deleteBooking(@PathVariable("number") Integer number, @PathVariable("week") Integer week) {
        this.bookings.delete(number, week);

        return "redirect:/rooms/";
    }

}
