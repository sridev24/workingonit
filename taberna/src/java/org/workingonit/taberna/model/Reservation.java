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

import static org.workingonit.taberna.model.StatusEnum.CREATED;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author Vladimir Ritz Bossicard
 */
@XStreamAlias("reservation")
public class Reservation {

    private String name;
    private Integer week;
    private Integer price;
    private StatusEnum status = CREATED;
    
    public Reservation() {
        // noop
    }

    public Reservation(String name, Integer week) {
        this.name = name;
        this.week = week;
    }

    public String getName() {
        return this.name;
    }

    public Integer getWeek() {
        return this.week;
    }

    public void setPrice(Integer price) {
       this.price = price;
    }

    public Integer getPrice() {
        return this.price;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }
    
    public StatusEnum getStatus() {
        return this.status;
    }

    @Override
    public String toString() {
        return "booking for " + this.name + " for week " + this.week;
    }
}
