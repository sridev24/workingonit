/*
 * Copyright (C) 2008-2010 Vladimir Ritz Bossicard
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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Vladimir Ritz Bossicard
 */
public class Examination implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private Platform platform;
    private Date date;
    private List<GroupedDiagnostics> groupedDiagnostics = new ArrayList<GroupedDiagnostics>();
    /** all diagnostics not belonging to a particular group */
    private List<Diagnostic> diagnostics = new ArrayList<Diagnostic>();

    public Examination() {
        this(null);
    }

    public Examination(String name) {
        this.name = name;
        this.date = new Date();
    }

    public String getName() {
        return this.name;
    }

    public Platform getPlatform() {
        return this.platform;
    }

    public void setPlatform(Platform platform) {
        this.platform = platform;
    }

    /**
     * Returns the date when this <code>Examination</code> was done.
     */
    public Date getDate() {
        return this.date;
    }

    public void setDiagnostics(List<Diagnostic> diagnostics) {
        this.diagnostics = diagnostics;
    }

    public void addDiagnostic(Diagnostic diagnostic) {
        this.diagnostics.add(diagnostic);
    }

    public List<Diagnostic> getDiagnostics() {
        return this.diagnostics;
    }

    /**
     * @param groupedDiagnostics
     */
    public void addGroupedDiagnostics(GroupedDiagnostics groupedDiagnostics) {
        this.groupedDiagnostics.add(groupedDiagnostics);
    }

    public List<GroupedDiagnostics> getGroupedDiagnostics() {
        return this.groupedDiagnostics;
    }

}