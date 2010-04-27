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

import java.awt.*
import javax.swing.WindowConstants as WC

import org.workingonit.addenda.application.*
import org.workingonit.addenda.application.process.*

/**
 * @author Vladimir Ritz Bossicard
 */
public class CommutusApplication extends AbstractApplication {
    
     def CommutusApplication() {
         super('Commutus')
     }

     def doCreateFrame() {
         
         // define your custom actions here...
         // you have to define the actions with 'def stuff' otherwise groovy
         // will take the name of the menu item and use it as the name of the menu.
         // maybe a bug, maybe I'm doing something wrong.
         
         frame = swing.frame(title: appname,
                 location:[100, 100], size:[300, 600],
                 defaultCloseOperation:WC.EXIT_ON_CLOSE) {
             menuBar {
                 menu(mnemonic: 'F', 'File') {
                     menuItem(action: exit)
                 }
             }

             // creation of the main content
             panel(id: AbstractApplication.PANEL_NAME, layout: new CardLayout()) {
                 // This is where you add the panels...
                 panel(constraints: SourceMigratorView.VIEW_NAME) {
                     new SourceMigratorView(listener).init(swing)
                 }
             }
         }
     }
     
     static void main(args) {
         new CommutusApplication().createFrame()
     }
    
}