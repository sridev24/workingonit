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
 * Version     : $Revision: 232 $
 * Last edit   : $Date: 2009-07-09 16:56:36 +0200 (Thu, 09 Jul 2009) $
 * Last editor : $Author: vbossica $
 */
package org.workingonit.addenda.subversion;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

/**
 * Tests for the {@link SubversionInfoTask} class.
 *
 * @author Vladimir Ritz Bossicard
 */
@Test
public class SubversionInfoTaskTest {

    public void findTrunkName() {
        assertEquals(new SubversionInfoTask().findName("http://workingonit.googlecode.com/svn/trunk"), "trunk");
    }

    public void findBranchesName() {
        assertEquals(new SubversionInfoTask().findName("http://workingonit.googlecode.com/svn/branches/BRANCH_ALPHA"), "BRANCH_ALPHA");
    }

    public void findTagName() {
        assertEquals(new SubversionInfoTask().findName("http://workingonit.googlecode.com/svn/tags/TAG_ALPHA1"), "TAG_ALPHA1");
    }

}