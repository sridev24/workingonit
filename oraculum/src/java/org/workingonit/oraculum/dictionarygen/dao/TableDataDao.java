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
package org.workingonit.oraculum.dictionarygen.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

/**
 * @author Vladimir Ritz Bossicard
 */
public class TableDataDao extends JdbcDaoSupport {

    private final static String TABLE_QUERY = "select comments from all_tab_comments where table_name = ?";
    private final static String COLUMNS_QUERY = "SELECT all_tab_columns.column_name, data_type, nullable, comments "
            + "FROM all_tab_columns, all_col_comments "
            + "WHERE all_tab_columns.table_name = ? "
            + "AND all_tab_columns.table_name = all_col_comments.table_name "
            + "AND all_tab_columns.column_name = all_col_comments.column_name "
            + "ORDER BY column_id";
    private final static String PRIMARY_KEY_QUERY = "select column_name, position "
            + "from all_cons_columns A "
            + "join all_constraints C "
            + "on A.constraint_name = C.constraint_name "
            + "where C.table_name = ? "
            + "and C.constraint_type = 'P'";
    private final static String FOREIGN_KEY_QUERY = "select col.column_name as column_name, "
            + "rel.table_name as ref_table, "
            + "rel.column_name as ref_column "
            + "from user_tab_columns col "
            + "join user_cons_columns con on col.table_name = con.table_name and col.column_name = con.column_name "
            + "join user_constraints cc on con.constraint_name = cc.constraint_name "
            + "join user_cons_columns rel on cc.r_constraint_name = rel.constraint_name and con.position = rel.position "
            + "where cc.constraint_type = 'R' "
            + "and cc.table_name = ?";

    @SuppressWarnings("unchecked")
    public TableDataTO loadTableData(String table) {
        TableDataTO res = new TableDataTO();
        res.setName(table.toLowerCase());

        try {
            String comments = (String) getJdbcTemplate().queryForObject(TABLE_QUERY,
                new Object[] { table.toUpperCase() }, String.class);
            res.setComments(comments);
        } catch (EmptyResultDataAccessException ex) {
            // do nothing if no comment was defined
        }

        List<ColumnDataTO> cols = getJdbcTemplate().query(COLUMNS_QUERY,
            new Object[] { table.toUpperCase() }, new ColumnDataRowMapper());
        res.setColumns(cols);

        getJdbcTemplate().query(PRIMARY_KEY_QUERY, 
            new Object[] { table.toUpperCase() }, new PrimaryKeyUpdater(res));
            
        getJdbcTemplate().query(FOREIGN_KEY_QUERY, 
            new Object[] { table.toUpperCase() }, new ForeignKeyUpdater(res));
            
        return res;
    }

    private final static class ColumnDataRowMapper implements RowMapper {

        public Object mapRow(ResultSet rs, int position) throws SQLException {
            ColumnDataTO res = new ColumnDataTO();

            res.setName(rs.getString("column_name"));
            res.setType(rs.getString("data_type"));
            res.setNullable(BooleanUtils.toBoolean(rs.getString("nullable"),
                    "Y", "N"));
            res.setComments(StringUtils.defaultString(rs.getString("comments")));

            return res;
        }
    }

    private final static class PrimaryKeyUpdater implements RowCallbackHandler {

        private TableDataTO table;
        
        public PrimaryKeyUpdater(TableDataTO table) {
            this.table = table;
        }
        
        @Override
        public void processRow(ResultSet rs) throws SQLException {
            String name = rs.getString("column_name");
            ColumnDataTO column = table.getColumn(name);
            if (column != null) {
                column.setPrimary(true);
            }
        }
    }
    
    private final static class ForeignKeyUpdater implements RowCallbackHandler {

        private TableDataTO table;
        
        public ForeignKeyUpdater(TableDataTO table) {
            this.table = table;
        }
        
        @Override
        public void processRow(ResultSet rs) throws SQLException {
            String name = rs.getString("column_name");
            ColumnDataTO column = table.getColumn(name);
            if (column != null) {
                column.addForeignKey(new RefTableDataTO(rs.getString("ref_table"), rs.getString("ref_column")));
            }
        }
    }
    
}