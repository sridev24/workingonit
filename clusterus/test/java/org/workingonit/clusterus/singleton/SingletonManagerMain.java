/*
 * Copyright 2008-2009 Vladimir Ritz Bossicard
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
 * Version     : $Revision: 340 $
 * Last edit   : $Date: 2010-02-09 08:47:28 +0100 (Tue, 09 Feb 2010) $
 * Last editor : $Author: vbossica $
 */
package org.workingonit.clusterus.singleton;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Vladimir Ritz Bossicard
 */
public class SingletonManagerMain {

    public static void main(final String args[]) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("org/workingonit/clusterus/singleton/context-test.xml");
        try {
            context.start();
            while(true) {
                Thread.sleep(900);
            }
        } finally {
            if (context != null) {
                context.stop();
            }
        }
    }

}
