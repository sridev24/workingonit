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
 * Version     : $Revision: 267 $
 * Last edit   : $Date: 2009-08-21 08:09:55 +0200 (Fri, 21 Aug 2009) $
 * Last editor : $Author: vbossica $
 */
package org.workingonit.linguae.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Locale;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

/**
 * @author Vladimir Ritz Bossicard
 */
public class MessageDao extends JdbcDaoSupport {

    private final static String ALL_MESSAGES_QUERY = "select * from wkg_messages where language = ? order by msgkey";

    private final static String ALL_LOCALES_QUERY = "select distinct language from wkg_messages";

    @SuppressWarnings("unchecked")
    public List<MessageTO> getMessages(final String language) throws Exception {
        return getJdbcTemplate().query(ALL_MESSAGES_QUERY, new Object[] { language }, new RowMapper() {

            @Override
            public Object mapRow(final ResultSet rs, final int position) throws SQLException {
                MessageTO res = new MessageTO();

                res.setKey(rs.getString("msgkey"));
                res.setValue(rs.getString("msgvalue"));
                return res;
            }

        });
    }

    @SuppressWarnings("unchecked")
    public List<Locale> getAvailableLocales() throws Exception {
        return getJdbcTemplate().query(ALL_LOCALES_QUERY, new RowMapper() {

            @Override
            public Object mapRow(final ResultSet rs, final int position) throws SQLException {
                return new Locale(rs.getString("language"));
            }

        });
    }

}