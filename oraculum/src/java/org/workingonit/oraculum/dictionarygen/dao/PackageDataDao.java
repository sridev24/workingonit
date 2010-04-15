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
 * Version     : $Revision: 335 $
 * Last edit   : $Date: 2010-01-22 21:55:55 +0100 (Fri, 22 Jan 2010) $
 * Last editor : $Author: vbossica $
 */
package org.workingonit.oraculum.dictionarygen.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

/**
 * @author Vladimir Ritz Bossicard
 */
public class PackageDataDao extends JdbcDaoSupport {

    private final static String DEPENDING_QUERY = 
        "select object_id, object_name, object_type, owner from sys.dba_objects " +
        "where object_id in (" +
        "  select referenced_object_id from public_dependency where object_id in (" +
        "    select object_id FROM sys.dba_objects " +
        "    where owner = ? " +
        "    and object_name = ?" +
        "    and object_type in ('FUNCTION', 'PROCEDURE', 'PACKAGE', 'PACKAGE BODY', 'SYNONYM')" +
        "  ) )" +
        "and object_type in ('FUNCTION', 'PROCEDURE', 'PACKAGE', 'PACKAGE BODY', 'SYNONYM')" +
        "and owner = ?";

    /**
     * Query finds the objects that
     */
    private final static String DEPENDENT_QUERY = 
        "select object_id, object_name, object_type, owner from sys.dba_objects " + 
        "where object_id in ( " +
        "  select object_id from public_dependency where referenced_object_id in ( " +
        "    select object_id FROM sys.dba_objects " +
        "    where owner = ? " +
        "    and object_name = ?" +
        "    and object_type in ('FUNCTION', 'PROCEDURE', 'PACKAGE', 'PACKAGE BODY', 'SYNONYM')" +
        "  ) )" +
        "and object_type in ('FUNCTION', 'PROCEDURE', 'PACKAGE', 'PACKAGE BODY', 'SYNONYM')" +
        "and owner = ?";
    
    @SuppressWarnings("unchecked")
    public List<PackageDataTO> findDependingObjects(String owner, String name) {
        return getJdbcTemplate().query(DEPENDING_QUERY, new Object[] { owner, name, owner }, new RowMapper() {
            public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                PackageDataTO res = new PackageDataTO(rs.getString("object_name"));
                res.setType(rs.getString("object_type"));
                
                return res;
            }
        });
    }
    
    @SuppressWarnings("unchecked")
    public List<PackageDataTO> findDependentObjects(String owner, String name) {
        return getJdbcTemplate().query(DEPENDENT_QUERY, new Object[] { owner, name, owner }, new RowMapper() {
            public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                PackageDataTO res = new PackageDataTO(rs.getString("object_name"));
                res.setType(rs.getString("object_type"));
                
                return res;
            }
        });
    }

    public PackageDataTO loadPackageData(String owner, String name) {
        PackageDataTO res = new PackageDataTO(name);
        res.setParents(findDependentObjects(owner, name));
        res.setChildren(findDependingObjects(owner, name));

        return res;
    }

}