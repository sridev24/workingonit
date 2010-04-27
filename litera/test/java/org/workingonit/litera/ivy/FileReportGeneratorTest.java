/*
 * Copyright 2009-2010 Vladimir Ritz Bossicard
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
package org.workingonit.litera.ivy;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.util.List;

import org.testng.annotations.Test;

/**
 * @author Vladimir Ritz Bossicard
 */
@Test
public class FileReportGeneratorTest {

    public void docbookGeneration() throws Exception {
        FileReportGenerator aggregator = new FileReportGenerator();
        
        aggregator.output(new File[] { new File("test/data/ivy-report.xml") },
            "my title",
            new File("target/test/sample-report.docbook"));
    }

    public void sortOneReport() throws Exception {
        FileReportGenerator aggregator = new FileReportGenerator();
    
        List<FileReportGenerator.Dependency> dependencies = aggregator.findDependencies(new File("test/data/ivy-report.xml"));
        assertEquals(dependencies.size(), 16);
    }
    
}