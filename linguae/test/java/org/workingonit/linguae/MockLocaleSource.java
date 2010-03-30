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

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * @author Vladimir Ritz Bossicard
 */
public class MockLocaleSource implements LocaleSource {

    @Override
    public List<Locale> getLocales() {
        List<Locale> res = new ArrayList<Locale>();
        res.add(Locale.FRENCH);
        res.add(Locale.ENGLISH);

        return res;
    }

}
