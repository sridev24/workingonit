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

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

import org.apache.commons.lang.Validate;
import org.springframework.beans.factory.InitializingBean;
import org.workingonit.addenda.xml.transform.StringSource;
import org.workingonit.litera.xml.transformers.XmlTransformer;

/**
 * @author Vladimir Ritz Bossicard
 */
public class MediaWiki2PdfGenerator implements InitializingBean {

    private HttpDocumentGrabber grabber = new HttpDocumentGrabber();
    private DocumentParser parser = new DocumentParser();
    private XmlTransformer foTransformer;
    private XmlTransformer pdfTransformer;

    public void setHttpDocumentGrabber(HttpDocumentGrabber grabber) {
        this.grabber = grabber;
    }

    public void setFoTransformer(XmlTransformer transformer) {
        this.foTransformer = transformer;
    }

    public void setPdfTransformer(XmlTransformer transformer) {
        this.pdfTransformer = transformer;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Validate.notNull(this.grabber, "documentGrabber must be set");
    }

    public void generate(String url, OutputStream out) throws Exception {
        /*
         * The steps to generate a document are the following:
         *
         * 1. retrieve the raw document
         * 2. transform it into XHTML (using Mylyn)
         * 3. transform it into PDF (using Apache Fop)
         */

        String data = this.grabber.grab(url);
        System.out.println(data);

        String xhtml = this.parser.parse(url, UrlUtils.formatTitle(url), data);
        System.out.println(xhtml);

        OutputStream tmp = new ByteArrayOutputStream();
        this.foTransformer.transform(new StringSource(xhtml), tmp);

        System.out.println(tmp.toString());
        this.pdfTransformer.transform(new StringSource(tmp.toString()), out);
    }

}
