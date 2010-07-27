/*
 * Copyright 2010 Vladimir Ritz Bossicard
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

/**
 * @author Vladimir Ritz Bossicard
 */
public abstract class AbstractFinding implements Finding {

    private static final long serialVersionUID = 1L;

    protected String type;
    protected String subject;
    protected String message;

    public AbstractFinding(String type, String subject, String message) {
        this.type = type;
        this.subject = subject;
        this.message = message;
    }

    public String getType() {
        return this.type;
    }

    public String getSubject() {
        return this.subject;
    }

    public String getMessage() {
        return this.subject == null ? this.message : this.subject + ": " + this.message;
    }

    @Override
    public String toString() {
        return this.message + ": " + getStatus().name();
    }

}