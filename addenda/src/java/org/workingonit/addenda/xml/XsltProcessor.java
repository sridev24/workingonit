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
 * Version      : $Revision: 313 $
 * Last edit    : $Date: 2010-01-04 21:30:57 +0100 (Mon, 04 Jan 2010) $
 * Last editor  : $Author: vbossica $
 */
package org.workingonit.addenda.xml;

import java.io.File;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Templates;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Utility class to apply a given template to an XML document.
 *
 * @author Vladimir Ritz Bossicard
 */
public class XsltProcessor {

    private final static Log log = LogFactory.getLog(XsltProcessor.class);

    private Templates stylesheet;

    private XsltProcessor(StreamSource source) {
        try {
            TransformerFactory factory = TransformerFactory.newInstance();
            this.stylesheet = factory.newTemplates(source);
        } catch (Exception ex) {
            log.error("Error while creating template: " + ex.getMessage(), ex);
        }
    }

    public XsltProcessor(InputStream styleSheet) {
        this(new StreamSource(styleSheet));
    }

    public XsltProcessor(File styleSheet) {
        this(new StreamSource(styleSheet));
    }

    public String process(String xml) {
        StringWriter out = new StringWriter();
        try {
            Source source = new StreamSource(new StringReader(xml));
            Result result = new StreamResult(out);
            this.stylesheet.newTransformer().transform(source, result);
        } catch (Exception ex) {
            log.error("Unable to process input document: " + ex.getMessage(), ex);
        }
        return out.toString();
    }

}
