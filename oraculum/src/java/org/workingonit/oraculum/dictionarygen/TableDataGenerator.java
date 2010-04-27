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
package org.workingonit.oraculum.dictionarygen;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Map.Entry;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.workingonit.addenda.freemarker.FreemarkerOutputTemplate;
import org.workingonit.addenda.freemarker.FreemarkerUtils;
import org.workingonit.addenda.wikitext.FragmentParser;
import org.workingonit.oraculum.dictionarygen.dao.ColumnDataTO;
import org.workingonit.oraculum.dictionarygen.dao.TableDataDao;
import org.workingonit.oraculum.dictionarygen.dao.TableDataTO;

import freemarker.template.Configuration;

/**
 * @author Vladimir Ritz Bossicard
 */
public class TableDataGenerator extends AbstractTableDatabaseGenerator {

    private Properties values = new Properties();

    protected Configuration cfg;

    public TableDataGenerator() {
        cfg = FreemarkerUtils.createClassloaderConfiguration(getClass());
    }

    public void setDefaultColumnValues(File defaults) {
        FileInputStream stream = null;
        try {
            stream = new FileInputStream(defaults);
            values.load(stream);
            
            FragmentParser parser = new FragmentParser();
            for (Entry<Object, Object> entry : values.entrySet()) {
                values.setProperty(entry.getKey().toString(), 
                    parser.parse(entry.getValue().toString()));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public void generate(String table, File outFile) throws Exception {
        if (!outFile.getParentFile().exists()) {
            outFile.getParentFile().mkdirs();
        }

        TableDataDao dao = new TableDataDao();
        dao.setDataSource(connect());

        TableDataTO data = dao.loadTableData(table);
        setIgnoredFlag(data);
        
        FragmentParser parser = new FragmentParser();
        for (ColumnDataTO col : data.getColumns()) {
            String comments = col.getComments();
            if (StringUtils.isNotBlank(comments)) {
                col.setComments(parser.parse(comments));
            } else {
                /* override the comment with the default value if it exists */
                if (this.values.containsKey(col.getName().toString())) {
                    col.setComments(this.values.get(col.getName()).toString());
                }
            }
        }

        Map<String, Object> model = new HashMap<String, Object>();
        model.put("table", data);

        System.out.println("generating file " + outFile);
        String content = new FreemarkerOutputTemplate(this.cfg).writeToString(model, "tabledata.dbk.ftl");
        FileUtils.writeStringToFile(outFile, content, "UTF-8");
    }

    public static void main(String[] args) throws Exception {
        TableDataGenerator generator = new TableDataGenerator();
        generator.setUrl(args[0]);
        generator.setUsername(args[1]);
        generator.setPassword(args[2]);
        if (args[4] != null) {
            generator.setIgnoredColumns(args[4]);
        }
        if (args[5] != null) {
            generator.setDefaultColumnValues(new File(args[5]));
        }
        generator.generate(args[3], new File(args[6]));
    }

}