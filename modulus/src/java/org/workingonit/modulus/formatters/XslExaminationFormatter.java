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
package org.workingonit.modulus.formatters;

import java.io.InputStream;

import nu.xom.Document;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.workingonit.addenda.xml.XsltProcessor;
import org.workingonit.modulus.Examination;

/**
 * Apply an XSLT transformation to the XML document produced by the
 * {@link XmlExaminationFormatter} super class.
 *
 * @author Vladimir Ritz Bossicard
 */
public class XslExaminationFormatter extends XmlExaminationFormatter {

    private final static Log log = LogFactory.getLog(XslExaminationFormatter.class);

    private String templateName;

    public XslExaminationFormatter() {
        // noop
    }

    public XslExaminationFormatter(String templateName) {
        this.templateName = templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    @Override
    public String format(Examination examination) {
        Document doc = createXmlDocument(examination);

        InputStream template = getClass().getResourceAsStream(this.templateName);
        if (template == null) {
            throw new IllegalArgumentException("template couldn't be find: " + this.templateName);
        }
        XsltProcessor processor = new XsltProcessor(template);
        String content = doc.toXML();
        if (log.isDebugEnabled()) {
            log.debug(content);
        }
        return processor.process(content);
    }

}
