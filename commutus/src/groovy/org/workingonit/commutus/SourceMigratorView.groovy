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
 * Version     : $Revision: 80 $
 * Last edit   : $Date: 2009-03-24 21:38:28 +0100 (Tue, 24 Mar 2009) $
 * Last editor : $Author: vbossica $
 */
package org.workingonit.commutus

import javax.swing.JFrame
import javax.swing.JOptionPane

import net.miginfocom.swing.MigLayout

import org.workingonit.addenda.application.AbstractView
import org.workingonit.addenda.application.process.*

/**
 * @author Vladimir Ritz Bossicard
 */
public class SourceMigratorView extends AbstractView {
     
     public final static String VIEW_NAME = 'SOURCEMIGRATOR_VIEW'
         
     public SourceMigratorView(ProcessListener listener) {
         super(listener)
     }

     Object init(swing) {
         return swing.panel(layout: new MigLayout('fill')) {
             label(text:'source information', constraints: 'split, span')
             separator(constraints: 'growx, wrap')
             
            label('directory ')
            textField(id:'directory', text:'', columns:30, constraints:'span, grow, wrap') 
             
             button('Process', 
                     constraints: 'span, split, align right', actionPerformed: {
                 def dir = new File(directory.text)
                 if (!dir.exists()) {
                     JOptionPane.showMessageDialog(new JFrame(), "directory doesn't '${dir}' exist", 
                         "Dialog", JOptionPane.ERROR_MESSAGE)
                 } else {
                     new SourceMigrator(listener).process(dir)
                 }
             })
         }       
     }   
     
}