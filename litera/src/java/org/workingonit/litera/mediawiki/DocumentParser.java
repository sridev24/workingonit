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

import java.io.StringReader;
import java.io.StringWriter;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.eclipse.mylyn.wikitext.core.parser.Attributes;
import org.eclipse.mylyn.wikitext.core.parser.MarkupParser;
import org.eclipse.mylyn.wikitext.core.parser.builder.DocBookDocumentBuilder;
import org.eclipse.mylyn.wikitext.mediawiki.core.MediaWikiLanguage;

/**
 * Transform the content of a MediaWiki page into a valid Docbook document.
 *
 * @author Vladimir Ritz Bossicard
 */
public class DocumentParser {

    private final static Logger log = Logger.getLogger(DocumentParser.class);

    private String enclosingTag = "article";

    /**
     * Either <i>article</i> or <i>section</i>.
     */
    public void setEnclosingTag(String enclosingTag) {
        this.enclosingTag = enclosingTag;
    }

    public String parse(final String url, final String title, final String content) throws Exception {
        StringWriter writer = new StringWriter();

        if (log.isInfoEnabled()) {
            log.info("parsing url " + url);
        }
        DocBookDocumentBuilder builder = new DocBookDocumentBuilder(writer) {
            @Override
            public void image(Attributes attributes, String imgUrl) {
                try {
                    if (log.isDebugEnabled()) {
                        log.debug("loading image " + imgUrl);
                    }
                    String base = url.substring(0, url.indexOf("index.php/") + "index.php/".length());

                    GetMethod method = new GetMethod(base + "Special:FilePath?file=" + imgUrl);
                    method.setFollowRedirects(true);

                    if (new HttpClient().executeMethod(method) != -1) {
                        super.image(attributes, method.getURI().toString());
                        method.releaseConnection();
                    }
                } catch (Exception ex) {
                    log.warn("error while retrieving image + " + imgUrl, ex);
                }
            }
        };
        builder.setAutomaticGlossary(false);

        MarkupParser parser = new MarkupParser(new MediaWikiLanguage());
        parser.setBuilder(builder);
        parser.parse(new StringReader(content), true);

        String data = writer.toString();

        data = data.substring(data.indexOf("<book>"));
        data = StringUtils.replaceEachRepeatedly(data,
            new String[] { "book>", "<chapter", "</chapter" },
            new String[] { this.enclosingTag + ">", "<section", "</section" });

        Document doc = new SAXReader().read(new StringReader(data));
        Element root = doc.getRootElement();
        root.element("title").setText(title);

        return root.asXML();
    }

}