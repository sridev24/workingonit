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
 * Version     : $Revision: 319 $
 * Last edit   : $Date: 2010-01-18 22:01:37 +0100 (Mon, 18 Jan 2010) $
 * Last editor : $Author: vbossica $
 */
package org.workingonit.modulus;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;
import org.workingonit.modulus.findings.EvaluatedFinding;
import org.workingonit.modulus.findings.Finding;
import org.workingonit.modulus.findings.Information;
import org.workingonit.modulus.findings.Finding.Status;

/**
 * @author Vladimir Ritz Bossicard
 */
@Test
public class DiagnosticTest {

    public void neutral_status() {
        Diagnostic diagnostic = new Diagnostic("dummy",
            new Finding[] { new Information("info") } );
        assertEquals(diagnostic.getStatus(), Status.NEUTRAL);
    }

    public void ok_status() {
        Diagnostic diagnostic = new Diagnostic("dummy",
            new Finding[] {
                new Information("info"),
                new EvaluatedFinding("ok", true)
        } );
        assertEquals(diagnostic.getStatus(), Status.OK);
    }

    public void warning_status() {
        Diagnostic diagnostic = new Diagnostic("dummy",
            new Finding[] {
                new Information("info"),
                new EvaluatedFinding("ok", true),
                new EvaluatedFinding("warning", false, false)
        } );
        assertEquals(diagnostic.getStatus(), Status.WARNING);
    }

    public void error_status() {
        Diagnostic diagnostic = new Diagnostic("dummy",
            new Finding[] {
                new Information("info"),
                new EvaluatedFinding("ok", true),
                new EvaluatedFinding("warning", false, false),
                new EvaluatedFinding("error", false, true)
        } );
        assertEquals(diagnostic.getStatus(), Status.ERROR);
    }

}
