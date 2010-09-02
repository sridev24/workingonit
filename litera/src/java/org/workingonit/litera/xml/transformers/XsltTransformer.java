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
package org.workingonit.litera.xml.transformers;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

import javax.xml.transform.Source;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.commons.lang.Validate;
import org.springframework.beans.factory.InitializingBean;

/**
 * The first pipeline transforms the incoming data into an FO object
 */
public class XsltTransformer implements XmlTransformer, InitializingBean {

    private File stylesheet;

    public void setStylesheet(File stylesheet) {
        this.stylesheet = stylesheet;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Validate.notNull(this.stylesheet, "stylesheet must be set");
    }

    @Override
    public void transform(Source source, OutputStream output) throws Exception {
        TransformerFactory factory  = TransformerFactory.newInstance();

        StreamSource xslSource = new StreamSource(new FileInputStream(this.stylesheet));
        factory.newTransformer(xslSource).transform(source, new StreamResult(output));
    }

}