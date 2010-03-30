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

import java.io.ByteArrayOutputStream;

import javax.servlet.ServletException;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.workingonit.litera.mediawiki.MediaWiki2PdfGenerator;

/**
 * @author Vladimir Ritz Bossicard
 */
public class DocumentFormController extends SimpleFormController {

    private MediaWiki2PdfGenerator generator;

    public DocumentFormController() {
        setCommandClass(DocumentRequest.class);
    }

    public void setGenerator(MediaWiki2PdfGenerator generator) {
        this.generator = generator;
    }

    @Override
    public ModelAndView onSubmit(Object command) throws ServletException {
        DocumentRequest request = (DocumentRequest) command;
        System.out.println("process url: " + request.getUrl());

        try {
            ModelAndView model = new ModelAndView("documentView");
            ByteArrayOutputStream out = null;
            try {
                out = new ByteArrayOutputStream(4000);
                this.generator.generate(request.getUrl(), out);
                out.flush();

                byte[] doc = out.toByteArray();
                System.out.println("generated document length " + doc.length);

                model.addObject("document", doc);
            } finally {
                if (out != null) {
                    out.close();
                }
            }

            return model;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
