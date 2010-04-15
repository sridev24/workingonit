/*
 * Copyright 2009 Vladimir Ritz Bossicard
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
 * Version     : $Revision: 352 $
 * Last edit   : $Date: 2010-03-05 17:33:45 +0100 (Fri, 05 Mar 2010) $
 * Last editor : $Author: vbossica $
 */
package org.workingonit.addenda.enums;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

import org.testng.annotations.Test;

/**
 * Tests for the {@link EnumConverter} class.
 * 
 * @author Vladimir Ritz Bossicard
 */
@Test
public class EnumConverterTest {

    public void enumMapping() {
        assertEquals(100, Animal.Ape.convert());
        assertEquals(Animal.Ape, Animal.Bee.convert((byte) 100));
    }

    public void enumMissingKey() {
        assertNull(Animal.Ape.convert((byte) 1));
    }

    enum Animal implements EnumConverter<Animal> {
        Ape(100), Bee(50), Cat(80);

        private final byte value;

        private static ReverseEnumMap<Animal> map = 
            new ReverseEnumMap<Animal>(Animal.class);

        Animal(int value) {
            this.value = (byte) value;
        }

        public byte convert() {
            return this.value;
        }

        public Animal convert(byte val) {
            return map.get(val);
        }
    }

}