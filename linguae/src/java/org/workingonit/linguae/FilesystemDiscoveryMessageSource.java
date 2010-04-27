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
 * Version     : $Revision: 267 $
 * Last edit   : $Date: 2009-08-21 08:09:55 +0200 (Fri, 21 Aug 2009) $
 * Last editor : $Author: vbossica $
 */
package org.workingonit.linguae;

import java.util.Locale;

import org.springframework.jmx.export.annotation.ManagedResource;

/**
 * Automatically finds list of supported languages reload of properties
 * 
 * @author Vladimir Ritz Bossicard
 */
@ManagedResource(objectName="linguae:name=MessageSource",
    description="Responsible for loading and storing languages")
public class FilesystemDiscoveryMessageSource extends AbstractDiscoveryMessageSource {

    @Override
    protected Locale[] getAvailableLocales() {
        return Locale.getAvailableLocales();
    }

    @Override
    protected void loadMessages(final Locale locale) {
        // the message files should already be on the file system => do nothing
    }

}