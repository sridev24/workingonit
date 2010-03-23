/*
 * Copyright 2010 Vladimir Ritz Bossicard
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
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.workingonit.addenda.freemarker.FreemarkerOutputTemplate;
import org.workingonit.addenda.freemarker.FreemarkerUtils;
import org.workingonit.oraculum.dictionarygen.dao.PackageDataDao;
import org.workingonit.oraculum.dictionarygen.dao.PackageDataTO;

import freemarker.template.Configuration;

/**
 * @author Vladimir Ritz Bossicard
 */
public class PackageGraphGenerator extends AbstractDatabaseGenerator {

    protected Configuration cfg;

    public PackageGraphGenerator() {
        cfg = FreemarkerUtils.createClassloaderConfiguration(getClass());
    }

    public void generate(String owner, String name, File outFile) throws Exception {
        PackageDataDao dao = new PackageDataDao();
        dao.setDataSource(connect());
        
        PackageDataTO pkg = dao.loadPackageData(owner, name);
        graph(outFile, pkg, true, true);
    }

    protected void graph(File outFile, PackageDataTO data, boolean drawParents, boolean drawChildren) throws Exception {
        if (!outFile.getParentFile().exists()) {
            outFile.getParentFile().mkdirs();
        }

        Map<String, Object> model = new HashMap<String, Object>();
        model.put("package", data);
        model.put("draw_parents", drawParents);
        model.put("draw_childen", drawChildren);

        System.out.println("generating file " + outFile);
        String content = new FreemarkerOutputTemplate(this.cfg).writeToString(model, "packagegraph.dot.ftl");
        FileUtils.writeStringToFile(outFile, content, "UTF-8");
    }

    public static void main(String[] args) throws Exception {
        PackageGraphGenerator generator = new PackageGraphGenerator();
        generator.setUrl(args[0]);
        generator.setUsername(args[1]);
        generator.setPassword(args[2]);
        generator.generate(args[3], args[4], new File(args[5]));
    }

}