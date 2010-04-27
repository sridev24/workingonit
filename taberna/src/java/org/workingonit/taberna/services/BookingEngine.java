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

import static org.workingonit.taberna.model.StatusEnum.BOOKED;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.workingonit.taberna.events.ReservationBookedEvent;
import org.workingonit.taberna.model.Reservation;
import org.workingonit.taberna.model.ReservationsRepository;
import org.workingonit.taberna.model.Room;
import org.workingonit.taberna.model.RoomsRepository;

/**
 * @author Vladimir Ritz Bossicard
 */
public class BookingEngine implements ApplicationEventPublisherAware {

    private RoomsRepository rooms;

    private ReservationsRepository reservations;

    private ApplicationEventPublisher publisher;
    
    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    @Required
    public void setRoomsRepository(RoomsRepository rooms) {
        this.rooms = rooms;
    }

    @Required
    public void setReservationsRepository(ReservationsRepository reservations) {
        this.reservations = reservations;
    }

    public void book(Integer number, Reservation reservation) {
        Room tmp = this.rooms.get(number);
        reservation.setPrice(tmp.getPrice());
        reservation.setStatus(BOOKED);
        this.reservations.add(number, reservation);
        this.publisher.publishEvent(new ReservationBookedEvent(number, reservation));
    }

    public void cancel(Integer number, Reservation reservation) {
        this.reservations.delete(number, reservation.getWeek());
    }

}