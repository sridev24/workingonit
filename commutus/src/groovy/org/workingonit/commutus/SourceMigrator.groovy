/*
 * Copyright 2009 Vladimir Ritz Bossicard
 * 
 * This file is part of WorkingOnIt.
 *
 * WorkingOnIt is free software: you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 * PARTICULAR PURPOSE. See the GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License along
 * with this program. If not, see <http://www.gnu.org/licenses/>.
 *
 * Version     : $Revision: 131 $
 * Last edit   : $Date: 2009-04-21 13:26:37 +0200 (Tue, 21 Apr 2009) $
 * Last editor : $Author: vbossica $
 */
package org.workingonit.commutus

import org.workingonit.addenda.application.process.*

/**
 * @author Vladimir Ritz Bossicard
 */
public class SourceMigrator {
    
    def listener = new SysOutProcessListener()

    SourceMigrator(ProcessListener listener) {
        this.listener = listener
    }
     
    def process(dir) {
        dir.eachFileRecurse {
            def migrator = new SourceMigrator()
            if (it.name.endsWith('Test.java')) {
                this.listener.report("processing ${it.name}")
                
                new File(it.absolutePath).write(migrator.migrate(it))
            }
        }
    }
    
    public String migrate(File file) {
        def newTxt = ""
        file.eachLine { line ->
            line = line.replace('import junit.framework.TestCase;', 'import org.junit.Test;\nimport org.junit.Autowired;\nimport static org.junit.Assert.*;')
            
            line = line.replace(' extends TestCase', '')
            
            line = line.replace('public void testA', '@Test public void a')
            line = line.replace('public void testB', '@Test public void b')
            line = line.replace('public void testC', '@Test public void c')
            line = line.replace('public void testD', '@Test public void d')
            line = line.replace('public void testE', '@Test public void e')
            line = line.replace('public void testF', '@Test public void f')
            line = line.replace('public void testG', '@Test public void g')
            line = line.replace('public void testH', '@Test public void h')
            line = line.replace('public void testI', '@Test public void i')
            line = line.replace('public void testJ', '@Test public void j')
            line = line.replace('public void testK', '@Test public void k')
            line = line.replace('public void testL', '@Test public void l')
            line = line.replace('public void testM', '@Test public void m')
            line = line.replace('public void testN', '@Test public void n')
            line = line.replace('public void testO', '@Test public void o')
            line = line.replace('public void testP', '@Test public void p')
            line = line.replace('public void testQ', '@Test public void q')
            line = line.replace('public void testR', '@Test public void r')
            line = line.replace('public void testS', '@Test public void s')
            line = line.replace('public void testT', '@Test public void t')
            line = line.replace('public void testU', '@Test public void u')
            line = line.replace('public void testV', '@Test public void v')
            line = line.replace('public void testW', '@Test public void w')
            line = line.replace('public void testX', '@Test public void x')
            line = line.replace('public void testY', '@Test public void y')
            line = line.replace('public void testZ', '@Test public void z')

            line = line.replace('public void setUp', '@Before public void setUp')

            line = line.replace('public void set', '@Autowired public void set')

            newTxt += line + '\n'
        }
        return newTxt
    }

}