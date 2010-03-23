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
package org.workingonit.litera.web;

import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.AbstractView;

/**
 * @author Vladimir Ritz Bossicard
 */
public class DocumentView extends AbstractView {

    @Override
    @SuppressWarnings("unchecked")
    protected void renderMergedOutputModel(Map model,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        byte[] doc = (byte[]) model.get("document");

        response.setContentType("application/pdf");
        response.setHeader ("Content-Disposition", "inline; filename=\"litera.pdf\"");
        response.setHeader ("Content-Length", String.valueOf(doc.length));

        System.out.println("rendered document length " + doc.length);

        ServletOutputStream out = response.getOutputStream();

        out.write(doc);
        out.flush();
    }

}
