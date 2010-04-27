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

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.client.RestTemplate;
import org.workingonit.taberna.model.Reservation;
import org.workingonit.taberna.model.Room;

/**
 * @author Vladimir Ritz Bossicard
 */
public class Client {

    private RestTemplate template;
    private String url;

    @Autowired
    @Required
    public void setRestTemplate(RestTemplate template) {
        this.template = template;
    }

    @Autowired
    @Required
    public void setUrl(String url) {
        this.url = url;
    }

    public Room[] getRooms() {
        return this.template.getForObject(this.url + "/rooms/", Room[].class);
    }

    public Room getRoom(Integer id) {
        return this.template.getForObject(this.url + "/rooms/{id}", Room.class, id);
    }

    public URI addRoom(Room room) {
        System.out.println("creating room " + room);
        return this.template.postForLocation(this.url + "/rooms/", room);
    }

    public void deleteRoom(Integer number) {
        this.template.delete(this.url + "/rooms/{id}", number);
    }

    public URI addBooking(Integer number, Reservation booking) {
        System.out.println("transmitting " + booking);

        return this.template.postForLocation(this.url + "/rooms/{number}/bookings/", booking, number);
    }

    public Reservation getBooking(Integer number, Integer week) {
        return this.template.getForObject(this.url + "/rooms/{number}/bookings/{week}", Reservation.class, number, week);
    }

    public void deleteBooking(Integer number, Integer week) {
        this.template.delete(this.url + "/rooms/{id}/bookings/{week}", number, week);
    }

}