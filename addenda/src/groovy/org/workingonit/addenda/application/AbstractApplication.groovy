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
package org.workingonit.addenda.application

import java.awt.*
import javax.swing.*
import javax.swing.WindowConstants as WC
import javax.swing.JOptionPane

import groovy.swing.SwingBuilder

import org.workingonit.addenda.application.process.ProcessListener
import org.workingonit.addenda.application.process.SysOutProcessListener

/**
 * @author Vladimir Ritz Bossicard
 */
class AbstractApplication {

    def static PANEL_NAME = 'panes'

    def appname = 'Application'

    def frame

    def listener = new SysOutProcessListener()

    def swing = new SwingBuilder()

    def exit = swing.action(
        name:           'Exit',
        mnemonic:       'X',
        closure:        {
            System.exit(0)
        }
    )

    def AbstractApplication(appname) {
        this.appname = appname
    }

    def protected showPane(name) {
        def cardLayout = (CardLayout) swing.panes.getLayout()
        cardLayout.show(swing.panes, name) 
    }

    def createFrame() {
        doCreateFrame()
        
        frame.pack()
        frame.show()
    }

}