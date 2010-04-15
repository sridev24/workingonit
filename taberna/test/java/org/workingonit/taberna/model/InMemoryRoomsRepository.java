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

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.InitializingBean;

/**
 * @author Vladimir Ritz Bossicard
 */
public class InMemoryRoomsRepository implements RoomsRepository, InitializingBean {

    public final static Integer BELLA_DONNA = 10101;
    public final static Integer BELLEVUE = 10204;
    public final static Integer BEAU_SEJOUR = 10709;

    private Map<Integer, Room> rooms = new HashMap<Integer, Room>();

    @Override
    public void afterPropertiesSet() throws Exception {
        add(new Room(BELLA_DONNA, "bella donna", 100));
        add(new Room(BELLEVUE, "bellevue", 125));
        add(new Room(BEAU_SEJOUR, "beau sejour", 300));
    }

    @Override
    public void add(Room room) {
        System.out.println("adding room " + room.getNumber());
        this.rooms.put(room.getNumber(), room);
    }

    @Override
    public void delete(Integer id) {
        System.out.println("deleting room " + id);
        this.rooms.remove(id);
    }

    @Override
    public Room get(Integer id) {
        return this.rooms.get(id);
    }

    @Override
    public Collection<Room> getAll() {
        return this.rooms.values();
    }

}