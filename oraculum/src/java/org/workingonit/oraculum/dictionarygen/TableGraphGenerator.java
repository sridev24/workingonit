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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.workingonit.addenda.freemarker.FreemarkerOutputTemplate;
import org.workingonit.addenda.freemarker.FreemarkerUtils;
import org.workingonit.oraculum.dictionarygen.dao.ColumnDataTO;
import org.workingonit.oraculum.dictionarygen.dao.RefTableDataTO;
import org.workingonit.oraculum.dictionarygen.dao.TableDataDao;
import org.workingonit.oraculum.dictionarygen.dao.TableDataTO;

import freemarker.template.Configuration;

/**
 * @author Vladimir Ritz Bossicard
 */
public class TableGraphGenerator extends AbstractTableDatabaseGenerator {

    protected Configuration cfg;

    private List<String> ignoredKeys = new ArrayList<String>();

    public TableGraphGenerator() {
        cfg = FreemarkerUtils.createClassloaderConfiguration(getClass());
    }

    public void setIgnoredKeys(String names) {
        this.ignoredKeys.addAll(Arrays.asList(StringUtils.split(names.trim(), ',')));
    }

    /**
     * Sets the column's <code>ignored</code> flag to <code>true</code> if the
     * its name matches is found in the ignored list.
     */
    protected void setIgnoredKeyFlag(TableDataTO table) {
        for (ColumnDataTO column : table.getColumns()) {
            if (ignoredKeys.contains(column.getName())) {
                for (RefTableDataTO ref : column.getForeignKeys()) {
                    ref.setIgnored(true);
                }
            }
        }
    }

    public void generate(String tbls, File outFile) throws Exception {
        TableDataDao dao = new TableDataDao();
        dao.setDataSource(connect());

        String[] tables = StringUtils.split(tbls, ",");
        List<TableDataTO> data = new ArrayList<TableDataTO>();
        for (String table : tables) {
            System.out.println("loading table : " + table);
            TableDataTO tmp = dao.loadTableData(table);
            setIgnoredFlag(tmp);
            setIgnoredKeyFlag(tmp);
            data.add(tmp);
        }

        TableDataTO[] res = new TableDataTO[data.size()];
        data.toArray(res);
        graph(outFile, res);
    }

    protected void graph(File outFile, TableDataTO... data) throws Exception {
        if (!outFile.getParentFile().exists()) {
            outFile.getParentFile().mkdirs();
        }

        Map<String, Object> originals = new HashMap<String, Object>();
        for (TableDataTO table : data) {
            originals.put(table.getName(), table);
        }
        
        Map<String, Object> additionals = new HashMap<String, Object>();
        for (TableDataTO table : data) {
            for (ColumnDataTO column : table.getColumns()) {
                for (RefTableDataTO ref : column.getForeignKeys()) {
                    String refTable = ref.getTable();
                    if (!originals.containsKey(refTable)) {
                        // System.out.println("found link to unspecified table: " + refTable);
                        if (!additionals.containsKey(refTable)) {
                            additionals.put(refTable, new TableDataTO(refTable));
                        }
                        ref.setColumn("HEADER");
                    }
                }
            }
        }
        originals.putAll(additionals);

        Map<String, Object> model = new HashMap<String, Object>();
        model.put("tables", originals.values());

        System.out.println("generating file " + outFile);
        String content = new FreemarkerOutputTemplate(this.cfg).writeToString(model, "tablesgraph.dot.ftl");
        FileUtils.writeStringToFile(outFile, content, "UTF-8");
    }

    public static void main(String[] args) throws Exception {
        TableGraphGenerator generator = new TableGraphGenerator();
        generator.setUrl(args[0]);
        generator.setUsername(args[1]);
        generator.setPassword(args[2]);
        generator.setIgnoredColumns(StringUtils.defaultString(args[4]));
        generator.setIgnoredKeys(StringUtils.defaultString(args[5]));

        generator.generate(args[3], new File(args[6]));
    }

}
