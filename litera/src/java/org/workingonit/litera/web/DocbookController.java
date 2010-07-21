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
 * Version     : $Revision: 337 $
 * Last edit   : $Date: 2010-01-26 23:41:24 +0100 (Tue, 26 Jan 2010) $
 * Last editor : $Author: vbossica $
 */
package org.workingonit.litera.web;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.workingonit.litera.docbook.TableGenerator;

/**
 * @author Vladimir Ritz Bossicard
 */
@Controller
@RequestMapping(value="docbook.htm")
public class DocbookController {

    @RequestMapping(method=RequestMethod.GET)
    public String getCreateForm(Model model) {
        model.addAttribute(new TableRequest());
        return "docbook";
    }

    @RequestMapping(method=RequestMethod.POST)
    public void create(TableRequest table, HttpServletResponse response) throws Exception {
        
        String content = new TableGenerator().generate(table.getTitle(), table.getContent());
        content = StringEscapeUtils.escapeHtml(content);
        content = StringUtils.replace(content, " ", "&nbsp;");
        content = StringUtils.replace(content, "\n", "<br>");
        
        ServletOutputStream out = response.getOutputStream();

        out.write(content.getBytes());
    }

}
