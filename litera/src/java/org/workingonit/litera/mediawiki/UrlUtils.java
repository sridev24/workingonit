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
 * Version      : $Revision: 352 $
 * Last edit    : $Date: 2010-03-05 17:33:45 +0100 (Fri, 05 Mar 2010) $
 * Last editor  : $Author: vbossica $
 */
package org.workingonit.litera.mediawiki;

import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Vladimir Ritz Bossicard
 */
public class UrlUtils {

    private UrlUtils() {
        //
    }

    public static String createExportUrl(String url) {
        int pos = url.lastIndexOf('/');

        if (StringUtils.contains(url, "index.php")) {
            return url.substring(0, pos) + "/Special:Export" + url.substring(pos);
        }

        return url.substring(0, pos) + "/index.php/Special:Export" + url.substring(pos);
    }

    public static String formatTitle(String url) {
        int pos = url.lastIndexOf('/');

        String title = url.substring(pos + 1);

        return StringUtils.replace(title, "_", " ");
    }

}