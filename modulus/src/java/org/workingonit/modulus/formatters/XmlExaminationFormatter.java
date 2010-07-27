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
 */
package org.workingonit.modulus.formatters;

import java.util.Map;

import nu.xom.Attribute;
import nu.xom.Document;
import nu.xom.Element;

import org.workingonit.modulus.Diagnostic;
import org.workingonit.modulus.Examination;
import org.workingonit.modulus.Platform;
import org.workingonit.modulus.findings.Finding;

/**
 * @author Vladimir Ritz Bossicard
 */
public class XmlExaminationFormatter implements ExaminationFormatter {

    @Override
    public String format(Examination examination) {
        return createXmlDocument(examination).toXML();
    }

    protected Document createXmlDocument(Examination examination) {
        Document doc = new Document(new Element("medicus"));
        if (examination != null) {
            doc.getRootElement().appendChild(createCheckupNode(examination));
        }

        return doc;
    }

    private Element createCheckupNode(Examination examination) {
        Element node = new Element("examination");
        node.addAttribute(new Attribute("date", examination.getDate().toString()));

        node.appendChild(createPlatformNode(examination.getPlatform()));
        for (Diagnostic diagnostic : examination.getDiagnostics()) {
            node.appendChild(createDiagnosticNode(diagnostic));
        }
        return node;
    }

    private Element createPlatformNode(Platform platform) {
        Element node = new Element("platform");
        node.addAttribute(new Attribute("name", platform.getName()));

        if (platform.getProperties() != null) {
            for (Map.Entry<Object, Object> entry : platform.getProperties().entrySet()) {
                Element prop = new Element("property");
                prop.addAttribute(new Attribute("name", entry.getKey().toString()));
                prop.addAttribute(new Attribute("value", entry.getValue().toString()));
                node.appendChild(prop);
            }
        }
        return node;
    }

    private Element createDiagnosticNode(Diagnostic diagnostic) {
        Element node = new Element("diagnostic");
        node.addAttribute(new Attribute("name", diagnostic.getName()));
        node.addAttribute(new Attribute("status", diagnostic.getStatus().name()));

        for (Finding finding : diagnostic.getFindings()) {
            node.appendChild(createFindingNode(finding));
        }
        return node;
    }

    private Element createFindingNode(Finding finding) {
        Element node = new Element("finding");

        node.addAttribute(new Attribute("message", finding.getMessage()));
        node.addAttribute(new Attribute("status", finding.getStatus().toString()));

        return node;
    }

}
