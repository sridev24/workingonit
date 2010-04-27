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
package org.workingonit.addenda.freemarker;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 *
 * @author Vladimir Ritz Bossicard
 */
public class FreemarkerOutputTemplate {

    private final static Log log = LogFactory.getLog(FreemarkerOutputTemplate.class);
    
    private Configuration cfg;

    public FreemarkerOutputTemplate(final Configuration cfg) {
        this.cfg = cfg;
    }

    public void writeToFile(final Map<String, Object> model, final String template, final File out) throws IOException, TemplateException {
        Template tpl = this.cfg.getTemplate(template);
        if (tpl == null) {
            throw new IllegalArgumentException("template couldn't be found '" + template + "'");
        }
        
        if (!out.getParentFile().exists()) {
            if (log.isInfoEnabled()) {
                log.info("creating parent directory: " + out.getParentFile());
            }
            out.getParentFile().mkdirs();
        }

        if (log.isInfoEnabled()) {
            log.info("generating file: " + out.getAbsolutePath());
        }
        Writer writer = null;
        try {
            writer = new FileWriter(out);
            tpl.process(model, writer);
        } finally {
            if (writer != null) {
                writer.flush();
                writer.close();
            }
        }
    }

    public String writeToString(final Map<String, Object> model, final String template) throws IOException, TemplateException {
        Template tpl = this.cfg.getTemplate(template);
        if (tpl == null) {
            throw new IllegalArgumentException("template couldn't be found '" + template + "'");
        }
        
        Writer writer = null;
        try {
            writer = new StringWriter();
            tpl.process(model, writer);
        } finally {
            if (writer != null) {
                writer.flush();
                writer.close();
            }
        }
        return writer.toString();
    }
    
}
