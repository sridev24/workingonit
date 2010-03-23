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

import static org.workingonit.taberna.model.StatusEnum.PUBLISHED;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.ApplicationListener;
import org.workingonit.taberna.model.Reservation;
import org.workingonit.taberna.model.ReservationsRepository;

/**
 * @author Vladimir Ritz Bossicard
 */
public class MockReservationPublisherListener implements ApplicationListener<ReservationBookedEvent> {

    private ReservationsRepository reservations;

    @Required
    public void setReservationsRepository(ReservationsRepository reservations) {
        this.reservations = reservations;
    }

    @Override
    public void onApplicationEvent(ReservationBookedEvent event) {
        Reservation reservation = this.reservations.get(event.getNumber(), event.getWeek());
        reservation.setStatus(PUBLISHED);
    }

}