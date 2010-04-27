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
 * Version     : $Revision: 316 $
 * Last edit   : $Date: 2010-01-18 12:36:57 +0100 (Mon, 18 Jan 2010) $
 * Last editor : $Author: vbossica $
 */
package org.workingonit.oraculum.dictionarygen;

import java.io.File;

import org.testng.annotations.Test;
import org.workingonit.oraculum.dictionarygen.dao.ColumnDataTO;
import org.workingonit.oraculum.dictionarygen.dao.RefTableDataTO;
import org.workingonit.oraculum.dictionarygen.dao.TableDataTO;

@Test
public class TableGraphGeneratorTest {

    private TableDataTO createTableA() {
        TableDataTO table = new TableDataTO("TABLE_A");
        table.addColumn(new ColumnDataTO("ID", "NUMBER", true));
        table.addColumn(new ColumnDataTO("REF_TO_B", "NUMBER"));
        table.addColumn(new ColumnDataTO("COL_1", "VARCHAR2"));

        return table;
    }

    private TableDataTO createTableB() {
        TableDataTO table = new TableDataTO("TABLE_B");
        table.addColumn(new ColumnDataTO("ID", "NUMBER", true));
        table.addColumn(new ColumnDataTO("COL_2", "VARCHAR2"));

        return table;
    }

    public void single_table() throws Exception {
        new TableGraphGenerator().graph(new File("test/data/table_single.dot"), 
            createTableA());
    }

    public void multiple_tables() throws Exception {
        new TableGraphGenerator().graph(new File("test/data/table_multiple.dot"), 
            createTableA(), createTableB());
    }

    public void multiple_linked_tables() throws Exception {
        TableDataTO tableA = createTableA();
        ColumnDataTO column = tableA.getColumn("REF_TO_B");
        column.addForeignKey(new RefTableDataTO("TABLE_B", "ID"));
        TableDataTO tableB = createTableB();
        
        new TableGraphGenerator().graph(new File("test/data/table_multiple_linked.dot"), 
                tableA, tableB);
    }

    public void single_linked_table() throws Exception {
        TableDataTO tableA = createTableA();
        ColumnDataTO column = tableA.getColumn("REF_TO_B");
        column.addForeignKey(new RefTableDataTO("TABLE_B", "ID"));
        
        new TableGraphGenerator().graph(new File("test/data/table_single_linked.dot"), 
                tableA);
    }

    @Test(enabled=false)
    public void generate_from_db() throws Exception {
        TableGraphGenerator generator = new TableGraphGenerator();
        
        generator.setUrl("jdbc:oracle:thin:@sausalito:1521:XE");
        generator.setUsername("scott");
        generator.setPassword("tiger");
        
        generator.generate("msg_info", new File("target/msg_info.dot"));
    }
    
}