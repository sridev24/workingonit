/*
 * Copyright 2009 Vladimir Ritz Bossicard
 *
 * This file is part of Configo.
 *
 * Configo is free software: you can redistribute it and/or modify it 
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
 * Version     : $Revision: 131 $
 * Last edit   : $Date: 2009-04-21 13:26:37 +0200 (Tue, 21 Apr 2009) $
 * Last editor : $Author: vbossica $
 */
package org.workingonit.configo.swing;

import java.awt.Font;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.text.JTextComponent;

import net.miginfocom.swing.MigLayout;

import org.apache.commons.lang.StringUtils;
import org.workingonit.ui.AwtUtils;
import org.workingonit.configo.model.Configuration;
import org.workingonit.configo.model.Module;
import org.workingonit.configo.model.Property;

/**
 * 
 * @author Vladimir Ritz Bossicard
 */
public final class ComponentsFactory {

    public static JPanel buildConfigurationCard(final Configuration model) {
        JPanel res = new JPanel();
        res.setLayout(new MigLayout("fillx"));

        JLabel name = new JLabel(model.getName());
        name.setFont(AwtUtils.styleFont(name.getFont(), Font.BOLD));
        res.add(name, "wrap");
        res.add(new JSeparator(), "growx, wrap");
        if (model.getDescription() != null) {
            JTextPane descr = new JTextPane();
            descr.setBorder(null);
            descr.setBackground(null);
            descr.setText(model.getDescription());
            descr.setEditable(false);

            JScrollPane scroll = new JScrollPane(descr);
            scroll.setBorder(null);
            
            res.add(scroll); //, "growx, wrap");
        }

        return res;
    }

    public static JPanel buildModuleCard(final Module module, final Map<String, JTextComponent> holder) {
        JPanel res = new JPanel();
        res.setLayout(new MigLayout("fillx"));

        JLabel name = new JLabel(module.getName());
        name.setFont(AwtUtils.styleFont(name.getFont(), Font.BOLD));
        res.add(name, "span, wrap");
        if (module.getDescription() != null) {
            res.add(new JLabel(module.getDescription()), "span, wrap");
        }
        res.add(new JSeparator(), "growx, wrap");
        for (Property prop : module.getProperties()) {
            JLabel label = new JLabel(prop.getName() + " :");
            label.setToolTipText("<html>" + StringUtils.defaultString(prop.getDescription()) + "</html>");
            res.add(label, "split, span");
            JTextField field = new JTextField(prop.getValue());
            
            holder.put(prop.getKey(), field);
            res.add(field, "growx, split, span, wrap");
        }
        return res;
    }

}
