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
package org.workingonit.oraculum.codegen.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

/**
 * @author Vladimir Ritz Bossicard
 */
public class MsgInfoDao extends JdbcDaoSupport {

    private final static String ALL_MSG_INFO_QUERY = "select * from msg_info order by msgcode desc";

    private final static String MODULE_MSG_INFO_QUERY = "select * from msg_info where msgmodule = ? order by msgcode desc";

    @SuppressWarnings("unchecked")
    public List<MsgInfoTO> loadAllMsgInfo() {
        return getJdbcTemplate().query(ALL_MSG_INFO_QUERY, new MsgInfoRowMapper());
    }
    
    @SuppressWarnings("unchecked")
    public List<MsgInfoTO> loadModuleMsgInfo(String module) {
        return getJdbcTemplate().query(MODULE_MSG_INFO_QUERY, new Object[] { module }, new MsgInfoRowMapper());
    }
    
    private final static class MsgInfoRowMapper implements RowMapper {

        public Object mapRow(ResultSet rs, int position) throws SQLException {
            MsgInfoTO res = new MsgInfoTO();
            
            res.setCode(rs.getInt("msgcode"));
            res.setType(rs.getString("msgtype"));
            res.setModule(rs.getString("msgmodule"));
            res.setText(rs.getString("msgtext"));
            res.setName(rs.getString("msgname"));
            res.setDescription(rs.getString("description"));
            
            return res;
        }
    }

}
