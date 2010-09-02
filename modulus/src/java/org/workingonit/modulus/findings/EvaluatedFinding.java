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
package org.workingonit.modulus.findings;

/**
 * @author Vladimir Ritz Bossicard
 */
public class EvaluatedFinding extends AbstractFinding {

    private static final long serialVersionUID = 1L;

    private boolean fatal = false;
    private boolean successful;
    /** optional message only displayed when the finding is not successful */
    private String cause;

    public EvaluatedFinding(String type, String subject, String message, boolean successful, boolean fatal) {
        super(type, subject, message);
        this.fatal = fatal;
        this.successful = successful;
    }

    public EvaluatedFinding(String message, boolean successful, boolean fatal) {
        this(null, null, message, successful, fatal);
    }

    public EvaluatedFinding(String message, boolean successful) {
        this(message, successful, false);
    }

    @Override
    public Status getStatus() {
        if (this.successful) {
            return Status.OK;
        }
        return this.fatal ? Status.ERROR : Status.WARNING;
    }

    public Finding addCause(String cause) {
        this.cause = cause;
        return this;
    }

    public String getCause() {
        return this.cause;
    }

    @Override
    public boolean isSuccessful() {
        return this.successful;
    }

    @Override
    public String toString() {
        return this.message + ": " + getStatus().name() + (this.cause != null ? " (" + this.cause + ")" : "");
    }

}