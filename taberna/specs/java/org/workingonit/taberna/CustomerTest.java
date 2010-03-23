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
package org.workingonit.taberna;

import java.net.URI;

import org.concordion.Concordion;
import org.concordion.api.ResultSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.workingonit.taberna.model.Reservation;
import org.workingonit.taberna.model.Room;
import org.workingonit.taberna.web.Client;

/**
 * @author Vladimir Ritz Bossicard
 */
@ContextConfiguration("classpath:org/workingonit/taberna/context-specs-test.xml")
public class CustomerTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private Client client;
    @Autowired
    private Concordion concordion;

    public void createRoom(Integer number, String name) {
        this.client.addRoom(new Room(number, name, 250));
    }

    public String book(String name, Integer number, Integer week) {
        URI res = this.client.addBooking(number, new Reservation(name, week));
        System.out.println(res);

        Reservation booking = this.client.getBooking(number, week);
        return booking.getName();
    }

    public String rebook(String name, Integer number, Integer week) {
        this.client.deleteBooking(number, week);

        this.client.addBooking(number, new Reservation(name, week));

        Reservation booking = this.client.getBooking(number, week);
        return booking.getName();
    }

    @AfterClass
    public void cleanup() {
        this.client.deleteBooking(777, 51);
    }

    @Test(enabled = true, timeOut = 12000)
    public void execute_concordion() throws Exception {
        ResultSummary result = this.concordion.process(this);
        result.print(System.out, this);
        result.assertIsSatisfied(this);
    }

}
