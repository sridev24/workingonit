/*
 * Copyright (C) 2009-2010 Vladimir Ritz Bossicard
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.workingonit.modulus.findings;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;
import org.workingonit.modulus.findings.Finding.Status;

/**
 * @author Vladimir Ritz Bossicard
 */
@Test
public class FindingsTest {

  private final static String NO_MSG = "no message";

  public void neutral() {
    assertEquals(new Information(NO_MSG).getStatus(), Status.NEUTRAL);
  }

  public void healthy() {
    assertEquals(new EvaluatedFinding(NO_MSG, true).getStatus(), Status.OK);
  }

  public void caution() {
    assertEquals(new EvaluatedFinding(NO_MSG, false).getStatus(), Status.WARNING);
  }

  public void sick() {
    assertEquals(new EvaluatedFinding(NO_MSG, false, true).getStatus(), Status.ERROR);
  }

}