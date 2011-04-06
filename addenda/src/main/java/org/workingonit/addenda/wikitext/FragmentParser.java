/*
 * Copyright 2009 Vladimir Ritz Bossicard
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
 * Version     : $Revision: 352 $
 * Last edit   : $Date: 2010-03-05 17:33:45 +0100 (Fri, 05 Mar 2010) $
 * Last editor : $Author: vbossica $
 */
package org.workingonit.addenda.wikitext;

import java.io.StringWriter;
import java.io.Writer;

import org.apache.commons.lang.StringUtils;
import org.eclipse.mylyn.wikitext.core.parser.MarkupParser;
import org.eclipse.mylyn.wikitext.core.parser.builder.DocBookDocumentBuilder;
import org.eclipse.mylyn.wikitext.core.parser.markup.MarkupLanguage;
import org.eclipse.mylyn.wikitext.mediawiki.core.MediaWikiLanguage;

/**
 * Utility class that parses fragments of text.
 * 
 * @author Vladimir Ritz Bossicard
 */
public class FragmentParser {

    /**
     * Parses the input string and returns a DocBook fragment without any
     * <code>&lt;section></code> nor <code>&lt;chapter></code> tag.
     * 
     * @param input the string to parse and transform
     * @param language the specific language (e.g. MediaWikiLanguage)
     */
    public String parse(String input, MarkupLanguage language) {
        Writer writer = new StringWriter();

        MarkupParser parser = new MarkupParser(language);

        parser.setBuilder(new DocBookDocumentBuilder(writer));
        parser.parse(input, false);

        return StringUtils.replace(writer.toString(), "<chapter><title></title>", "");
    }

    /**
     * Invokes {@link #parse(String, MarkupLanguage)} with the
     * {@link MediaWikiLanguage}.
     * 
     * @param input the string to parse and transform
     */
    public String parse(String input) {
        return parse(input, new MediaWikiLanguage());
    }

}