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
 *
 * Version      : $Revision: 319 $
 * Last edit    : $Date: 2010-01-18 22:01:37 +0100 (Mon, 18 Jan 2010) $
 * Last editor  : $Author: vbossica $
 */
package org.workingonit.modulus.checks;

import java.io.File;

import org.workingonit.modulus.findings.EvaluatedFinding;
import org.workingonit.modulus.findings.Finding;

/**
 *
 * @author Vladimir Ritz Bossicard
 */
public class WritableFileCheck extends AbstractCheck {

    private File file;

    public WritableFileCheck(String description, File file, boolean fatal) {
        super(description, fatal);
        this.file = file;
    }

    public WritableFileCheck(String description, File file) {
        this(description, file, false);
    }

    public Finding perform() {
        if (this.file == null) {
            return new EvaluatedFinding("File/directory '" + this.description + "' must be defined", false, true);
        }
        return new EvaluatedFinding(createMessage(), this.file.canWrite(), this.fatal).addCause(this.file.getAbsolutePath() + " is not writable");
    }

    private String createMessage() {
        String type = this.file.isDirectory() ? "Directory" : "File";
        return this.fatal ?
            type + " '" + this.description + "' is writable (mandatory)" :
            type + " '" + this.description + "' is writable (optional)";
    }



}
