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

import java.io.ByteArrayInputStream;

import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.dom4j.Document;
import org.dom4j.io.DOMReader;

/**
 * @author Vladimir Ritz Bossicard
 */
public class HttpDocumentGrabber {

    public String grab(String location) throws Exception {
        String exportUrl = UrlUtils.createExportUrl(location);

        System.out.println("extracting from " + exportUrl);
        String mwXml = grabContent(exportUrl);

        /*
         * Once the MediaWiki XML content has been grabbed, it is necessary to
         * find the raw text buried in the tag page/revision/text. Since the
         * returned XML is full of namespace and since we don't want to mess
         * around with them we simply decide to ignore them. For this, one has
         * to go with the standard DocumentBuilder and explicitly ignore the
         * namespaces.
         */

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(false);
        org.w3c.dom.Document w3cdoc = dbf.newDocumentBuilder().parse(new ByteArrayInputStream(mwXml.getBytes()));

        // now we can pass everything to the dom4j library and retrieve the content.
        Document doc = new DOMReader().read(w3cdoc);
        return doc.selectSingleNode("//mediawiki/page/revision/text").getText();
    }

    private String grabContent(String location) throws Exception {
        System.out.println("grabbing " + location);
        GetMethod method = new GetMethod(location);
        method.setFollowRedirects(true);

        if (new HttpClient().executeMethod(method) != -1) {
            String contents = method.getResponseBodyAsString();
            method.releaseConnection();
            return contents;
        }
        return "";
    }

}