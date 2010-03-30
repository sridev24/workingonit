/*
 * Copyright 2009 Vladimir Ritz Bossicard
 *
 * This file is part of Configo.
 *
 * Configo is free software: you can redistribute it and/or modify it 
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
package org.workingonit.configo;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.workingonit.addenda.commons.io.InputStreamUtils;
import org.workingonit.addenda.freemarker.FreemarkerOutputTemplate;
import org.workingonit.addenda.freemarker.FreemarkerUtils;
import org.workingonit.configo.model.Configuration;
import org.workingonit.configo.model.ConfigurationDao;
import org.workingonit.configo.model.Module;
import org.workingonit.configo.model.Property;

/**
 *
 * @author Vladimir Ritz Bossicard
 */
public class Configo {

    private final static String CONFIGURATION_DEFAULT = "resources/configo-config.xml";

    private String configurationName;
    private Configuration configuration;

    public Configo() {
        this(CONFIGURATION_DEFAULT);
    }

    public Configo(final String configurationName) {
        this.configurationName = configurationName;
        this.configuration = new Configuration();
    }

    public void init() throws Exception {
        String data = InputStreamUtils.readInputStreamToString(getClass().getClassLoader().getResourceAsStream(this.configurationName));
        this.configuration = new ConfigurationDao().fromXml(data);
    }

    public void importProperties(final Properties props) {
        // build a temporary cache
        Map<String, Property> cache = new HashMap<String, Property>();
        for (Module module : this.configuration.getModules()) {
            for (Property prop : module.getProperties()) {
                prop.setNewOne(true);
                cache.put(prop.getKey(), prop);
            }
        }

        for (Entry<Object, Object> key : props.entrySet()) {
            if (cache.containsKey(key.getKey())) {
                Property prop = cache.get(key.getKey());
                prop.setValue(StringUtils.defaultString(String.valueOf(key.getValue())));
                prop.setNewOne(false);
            }
        }
    }

    public Configuration getConfiguration() {
        return this.configuration;
    }

    public void generate(final File out) throws Exception {
        freemarker.template.Configuration cfg = FreemarkerUtils.createClassloaderConfiguration(getClass());

        Map<String, Object> model = new HashMap<String, Object>();
        model.put("config", this.configuration);

        new FreemarkerOutputTemplate(cfg).writeToFile(model, "file.properties.ftl", out);
    }

}