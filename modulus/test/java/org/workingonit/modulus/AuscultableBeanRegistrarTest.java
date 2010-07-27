/*
 * Copyright (C) 2008-2010 Vladimir Ritz Bossicard
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.workingonit.modulus;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;
import org.workingonit.modulus.findings.Finding;

/**
 * @author Vladimir Ritz Bossicard
 */
@ContextConfiguration(locations={
    "classpath:test-platform.xml",
    "classpath:META-INF/spring/com.acme.kitchensink.services.xml",
    "classpath:META-INF/spring/com.acme.kitchensink.modulus.xml"
})
public class AuscultableBeanRegistrarTest extends AbstractTestNGSpringContextTests {

    @Autowired
    @Qualifier(value="org.workingonit.modulus.AuscultableBeanRegistrar")
    private AuscultableBeanRegistrar registrar;

    @Test
    public void auscultate_all() {
        this.registrar.auscultate();
        List<Diagnostic> res = this.registrar.lastExamination().getDiagnostics();
        assertEquals(res.size(), 3);
    }

    @Test(enabled=true)
    public void print_all() {
        this.registrar.auscultate();
        List<Diagnostic> results = this.registrar.lastExamination().getDiagnostics();
        for (Diagnostic summary : results) {
            System.out.println(summary.getName());
            for (Finding fact : summary.getFindings()) {
                if (fact != null) {
                    System.out.println("   " + fact.getMessage());
                }
            }
        }
    }

}