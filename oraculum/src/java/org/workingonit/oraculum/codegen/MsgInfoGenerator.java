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
package org.workingonit.oraculum.codegen;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.workingonit.addenda.freemarker.FreemarkerOutputTemplate;
import org.workingonit.addenda.freemarker.FreemarkerUtils;
import org.workingonit.oraculum.codegen.dao.MsgInfoDao;
import org.workingonit.oraculum.codegen.dao.MsgInfoTO;

import freemarker.template.Configuration;

/**
 * @author Vladimir Ritz Bossicard
 */
public class MsgInfoGenerator {

    private String url;
    private String username;
    private String password;

    protected Configuration cfg;

    public MsgInfoGenerator() {
        cfg = FreemarkerUtils.createClassloaderConfiguration(getClass());
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void generate(String template, String module, File outFile) throws Exception {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("oracle.jdbc.OracleDriver");
        ds.setUrl(this.url);
        ds.setUsername(this.username);
        ds.setPassword(this.password);

        MsgInfoDao dao = new MsgInfoDao();
        dao.setDataSource(ds);

        List<MsgInfoTO> infos = "all".equals(module) ? dao.loadAllMsgInfo(): dao.loadModuleMsgInfo(module);
        
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("infos", infos);
        new FreemarkerOutputTemplate(this.cfg).writeToFile(model, template, outFile);
    }

    public static void main(String[] args) throws Exception {
        MsgInfoGenerator generator = new MsgInfoGenerator();
        generator.setUrl(args[0]);
        generator.setUsername(args[1]);
        generator.setPassword(args[2]);
        
        generator.generate(args[3], args[4], new File(args[5]));
    }
    
}
