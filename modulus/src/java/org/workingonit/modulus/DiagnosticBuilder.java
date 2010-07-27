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
package org.workingonit.modulus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.workingonit.modulus.checks.Check;
import org.workingonit.modulus.findings.Finding;

/**
 * The <code>DiagnosticBuilder</code> groups several {@link Finding}s and
 * {@link Check}s to give a {@link Diagnostic}.
 *
 * @author Vladimir Ritz Bossicard
 */
public class DiagnosticBuilder {

    private String name;
    private List<Finding> findings = new ArrayList<Finding>();

    /**
     * @param name the name of the platform.
     */
    public DiagnosticBuilder(String name) {
        this.name = name;
    }

    public DiagnosticBuilder add(Finding finding) {
        this.findings.add(finding);
        return this;
    }

    public DiagnosticBuilder add(Check check) {
        this.findings.addAll(Arrays.asList(check.perform()));
        return this;
    }

    /**
     * Adds all {@link Finding}s from the given <code>diagnostic</code>.
     */
    public DiagnosticBuilder add(Diagnostic diagnostic) {
        this.findings.addAll(Arrays.asList(diagnostic.getFindings()));
        return this;
    }

    public Diagnostic build() {
        return new Diagnostic(this.name, this.findings.toArray(new Finding[0]));
    }

}