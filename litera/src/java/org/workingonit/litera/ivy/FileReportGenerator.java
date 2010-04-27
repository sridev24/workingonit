/*
 * Copyright 2009-2010 Vladimir Ritz Bossicard
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
package org.workingonit.litera.ivy;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.workingonit.addenda.freemarker.FreemarkerOutputTemplate;
import org.workingonit.addenda.freemarker.FreemarkerUtils;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;

/**
 * @author Vladimir Ritz Bossicard
 */
public class FileReportGenerator {

    protected Configuration cfg = FreemarkerUtils.createClassloaderConfiguration(getClass());

    public void output(File[] files, String title, File outFile) throws Exception {
        List<Dependency> deps = new ArrayList<Dependency>();
        for (File file : files) {
            deps.addAll(findDependencies(file));
        }
        output(deps, title, outFile);
    }

    /**
     * Returns the list of {@link Dependency} found int the <i>input</i> file.
     */
    @SuppressWarnings("unchecked")
    protected List<Dependency> findDependencies(File input) throws DocumentException {
        Document doc = new SAXReader().read(input);

        List<Dependency> res = new ArrayList<Dependency>();
        List<Element> modules = (List<Element>) doc.selectNodes("//ivy-report/dependencies/module" );
        for (Element module : modules) {
            Dependency dep = new Dependency();
            dep.setOrganization(module.attributeValue("organisation", ""));
            dep.setName(module.attributeValue("name", ""));
            
            Element revision = module.element("revision");
            if (revision != null) {
                dep.setVersion(revision.attributeValue("name", ""));
                dep.setHomepage(revision.attributeValue("homepage", ""));
                
                Element license = revision.element("license");
                if (license != null) {
                    dep.setLicense(license.attributeValue("name", ""));
                }
            }
            res.add(dep);
        }
        return res;
    }

    /**
     * Writes into the <i>outFile</i> the list of <i>dependencies</i> described
     * by the given <i>title</i>.
     */
    protected void output(List<Dependency> dependencies, String title, File outFile) throws IOException, TemplateException {
        Collections.sort(dependencies, new Comparator<Dependency>() {

            @Override
            public int compare(Dependency o1, Dependency o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });

        Map<String, Object> model = new HashMap<String, Object>();
        model.put("title", title);
        model.put("dependencies", dependencies);
        new FreemarkerOutputTemplate(this.cfg).writeToFile(model, "dependency.dbk.ftl", outFile);
    }

    /**
     * Simple TO class holding values retrieved from the Ivy reports.
     */
    public final static class Dependency {

        private String organization = "";
        private String name = "";
        private String homepage = "";
        private String version = "";
        private String license = "";
        
        public String getOrganization() {
            return organization;
        }

        public void setOrganization(String organization) {
            this.organization = organization;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getHomepage() {
            return homepage;
        }

        public void setHomepage(String homepage) {
            this.homepage = homepage;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public String getLicense() {
            return license;
        }

        public void setLicense(String license) {
            this.license = license;
        }

    }    
}
