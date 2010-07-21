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
 * Version     : $Revision: 232 $
 * Last edit   : $Date: 2009-07-09 16:56:36 +0200 (Thu, 09 Jul 2009) $
 * Last editor : $Author: vbossica $
 */
package org.workingonit.addenda.spring;

import static org.testng.Assert.assertEquals;

import org.springframework.beans.BeansException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

@ContextConfiguration(locations={
    "classpath:org/workingonit/addenda/spring/applicationContext.xml"
})
@Test
public class BeanLocatorTest extends AbstractTestNGSpringContextTests {

    public void correctBean() {
        Integer sample = BeanLocator.getBean("sample");
        assertEquals(sample.intValue(), 123);
    }
    
    @Test(expectedExceptions = BeansException.class)
    public void missingBean() {
        BeanLocator.getBean("missingOne");
    }

    public void correctEnumBean() {
        Integer sample = BeanLocator.getBean(TestBeanEnum.SAMPLE);
        assertEquals(sample.intValue(), 123);
    }
    
    @Test(expectedExceptions = BeansException.class)
    public void missingEnumBean() {
        BeanLocator.getBean(TestBeanEnum.MISSING);
    }

    static enum TestBeanEnum implements BeanEnum {

        SAMPLE("sample"),
        MISSING("missingOne");

        private String name;
        
        private TestBeanEnum(String name) {
            this.name = name;
        }
        
        public String getName() {
            return this.name;
        }
        
    }

}