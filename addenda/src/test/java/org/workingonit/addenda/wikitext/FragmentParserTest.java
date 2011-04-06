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
package org.workingonit.addenda.wikitext;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

/**
 * @author Vladimir Ritz Bossicard
 */
@Test
public class FragmentParserTest {

    public void multi_paragraph() {
        String content = new FragmentParser().parse("Hello world\n\nbonjour a tous");
        assertEquals(content, "<para>Hello world</para><para>bonjour a tous</para>");
    }

    public void multi_line_paragraph() {
        String content = new FragmentParser().parse("Hello world\nbonjour a tous");
        assertEquals(content, "<para>Hello world\nbonjour a tous</para>");
    }

    public void itemisedlist() {
        String content = new FragmentParser().parse("* list 1");
        assertEquals(content, "<itemizedlist><listitem><para>list 1</para></listitem></itemizedlist>");
    }

    public void paragraph_and_itemisedlist() {
        String content = new FragmentParser().parse("Hello world\n* list 1");
        assertEquals(content, "<para>Hello world</para><itemizedlist><listitem><para>list 1</para></listitem></itemizedlist>");
    }

    public void paragraph_and_itemisedlist2() {
        String content = new FragmentParser().parse("Hello world\r\n* list 1");
        assertEquals(content, "<para>Hello world</para><itemizedlist><listitem><para>list 1</para></listitem></itemizedlist>");
    }

    public void italic() {
        String content = new FragmentParser().parse("Hello ''world''");
        assertEquals(content, "<para>Hello <emphasis role=\"italic\">world</emphasis></para>");
    }

    public void bold() {
        String content = new FragmentParser().parse("Hello '''world'''");
        assertEquals(content, "<para>Hello <emphasis role=\"bold\">world</emphasis></para>");
    }

}