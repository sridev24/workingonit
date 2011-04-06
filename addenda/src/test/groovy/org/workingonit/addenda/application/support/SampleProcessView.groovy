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
package org.workingonit.addenda.application.support

import net.miginfocom.swing.MigLayout

import org.workingonit.addenda.application.*
import org.workingonit.addenda.application.process.*

/**
 * @author Vladimir Ritz Bossicard
 */
public class SampleProcessView extends AbstractView {
    
    public final static String VIEW_NAME = 'SAMPLEPROCESS_VIEW'
    
    public SampleProcessView(ProcessListener listener) {
        super(listener)
    }

    Object init(swing) {
        return swing.panel(layout: new MigLayout('fill')) {
            label(text:'information', constraints: 'split, span')
            separator(constraints: 'growx, wrap')
            
            label('short description ')
            textField(id:'dummy', text:'', columns:30, constraints:'span, grow, wrap') 
            
            button('Process', 
                    constraints: 'span, split, align right', actionPerformed: {
                new SampleProcess(listener).process(dummy.text)
            })
        }        
    }    
    
}