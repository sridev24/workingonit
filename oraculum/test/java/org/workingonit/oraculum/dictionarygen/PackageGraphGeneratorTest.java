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
 * Version     : $Revision: 333 $
 * Last edit   : $Date: 2010-01-22 21:19:48 +0100 (Fri, 22 Jan 2010) $
 * Last editor : $Author: vbossica $
 */
package org.workingonit.oraculum.dictionarygen;

import java.io.File;

import org.testng.annotations.Test;
import org.workingonit.oraculum.dictionarygen.dao.PackageDataTO;

/**
 * @author Vladimir Ritz Bossicard
 */
@Test
public class PackageGraphGeneratorTest {
    
    private PackageDataTO createPackage() {
        PackageDataTO res = new PackageDataTO("core_package");
        
        res.addParent(new PackageDataTO("package_a"));
        res.addParent(new PackageDataTO("package_b"));
        res.addParent(new PackageDataTO("package_c"));

        res.addChild(new PackageDataTO("package_1"));
        res.addChild(new PackageDataTO("package_2"));

        return res;
    }
    
    public void graph_both() throws Exception {
        new PackageGraphGenerator().graph(new File("test/data/packages_both.dot"), 
            createPackage(), true, true);
    }
    
    public void graph_parents() throws Exception {
        new PackageGraphGenerator().graph(new File("test/data/packages_parents.dot"), 
            createPackage(), true, false);
    }
    
    public void graph_children() throws Exception {
        new PackageGraphGenerator().graph(new File("test/data/packages_children.dot"), 
            createPackage(), false, true);
    }
    
}