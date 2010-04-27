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
 * Version     : $Revision: 153 $
 * Last edit   : $Date: 2009-05-15 12:58:20 +0200 (Fri, 15 May 2009) $
 * Last editor : $Author: vbossica $
 */
package org.workingonit.configo;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.text.JTextComponent;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.jdesktop.application.Action;
import org.jdesktop.application.SingleFrameApplication;
import org.workingonit.addenda.commons.util.PropertiesUtils;
import org.workingonit.configo.model.Configuration;
import org.workingonit.configo.model.Module;
import org.workingonit.configo.model.Property;
import org.workingonit.configo.swing.ComponentsFactory;
import org.workingonit.configo.swing.IconBorder;
import org.workingonit.ui.SwingUtils;

/**
 *
 * @author Vladimir Ritz Bossicard
 */
public class ConfigoSwing extends SingleFrameApplication {

    private Configo app = new Configo();
    private JPanel cards = new JPanel();
    private Map<String, JTextComponent> values = new HashMap<String, JTextComponent>();

    protected File chooseFile() {
        JFileChooser chooser = new JFileChooser(new File("."));
        int option = chooser.showOpenDialog(getMainFrame());
        if (option == JFileChooser.APPROVE_OPTION) {
            return chooser.getSelectedFile();
        }
        return null;
    }

    protected File saveFile() {
        JFileChooser chooser = new JFileChooser(new File("."));
        int option = chooser.showSaveDialog(getMainFrame());
        if (option == JFileChooser.APPROVE_OPTION) {
            return chooser.getSelectedFile();
        }
        return null;
    }

    @Action
    public void importFile() {
        File file = chooseFile();
        if (file != null) {
            ImageIcon icon = SwingUtils.createImageIcon(getClass(), "resources/images/star.png", "new property");

            try {
                this.app.importProperties(PropertiesUtils.loadProperties(file));
                
                for (Module module : this.app.getConfiguration().getModules()) {
                    for (Property prop : module.getProperties()) {
                        JTextComponent field = this.values.get(prop.getKey());
                        field.setText(prop.getValue());
                        field.setBorder(!prop.isNewOne() ? field.getBorder() : 
                            new CompoundBorder(new IconBorder(icon), field.getBorder()));
                    }
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(getMainFrame(),
                    "Error occured during import: " + StringUtils.defaultIfEmpty(ex.getMessage(), "no message")
                        + "\n" + ExceptionUtils.getFullStackTrace(ex),
                    "Import error",
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    @Action
    public void previousPanel() {
        ((CardLayout) this.cards.getLayout()).previous(this.cards);
    }

    @Action
    public void nextPanel() {
        ((CardLayout) this.cards.getLayout()).next(this.cards);
    }

    @Action
    public void generate() {
        File file = saveFile();
        if (file != null) {
            try {
                // we safe all the values entered by the user
                for (Module module : this.app.getConfiguration().getModules()) {
                    for (Property prop : module.getProperties()) {
                        if (this.values.containsKey(prop.getKey())) {
                            prop.setValue(this.values.get(prop.getKey()).getText());
                        } else {
                            System.err.println("nothing found for key " + prop.getKey());
                        }
                    }
                }

                this.app.generate(file);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(getMainFrame(),
                    "Error occured during generation: " + StringUtils.defaultIfEmpty(ex.getMessage(), "no message")
                        + "\n" + ExceptionUtils.getFullStackTrace(ex),
                    "Generation error",
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    @Action
    public void quit() {
        exit();
    }

    @Action
    public void about() {
        JFrame frame = new JFrame("Configo");
        frame.getContentPane().add(new JLabel("About Configo"), BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
    }

    private javax.swing.Action getAction(final String actionName) {
        return getContext().getActionMap().get(actionName);
    }

    private JMenu createMenu(final String menuName, final String[] actionNames) {
        JMenu menu = new JMenu();
        menu.setName(menuName);
        for (String actionName : actionNames) {
            if (actionName.equals("---")) {
                menu.add(new JSeparator());
            } else {
                JMenuItem menuItem = new JMenuItem();
                menuItem.setAction(getAction(actionName));
                menuItem.setIcon(null);
                menu.add(menuItem);
            }
        }
        return menu;
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(createMenu("fileMenu",
               new String[] { "importFile", "previousPanel", "nextPanel", "generate", "---", "quit" }));
        menuBar.add(createMenu("helpMenu",
            new String[] { "about" }));
        return menuBar;
    }

    private JComponent createToolBar() {
        String[] toolbarActionNames = { "importFile", "previousPanel", "nextPanel" };
        JToolBar toolBar = new JToolBar();
        toolBar.setFloatable(false);
        Border border = new EmptyBorder(2, 9, 2, 9);
        for (String actionName : toolbarActionNames) {
            JButton button = new JButton();
            button.setBorder(border);
            button.setVerticalTextPosition(SwingConstants.BOTTOM);
            button.setHorizontalTextPosition(SwingConstants.CENTER);
            button.setAction(getAction(actionName));
            button.setFocusable(false);
            toolBar.add(button);
        }
        return toolBar;
    }

    private JComponent createMainPanel(final Configuration model) {
        this.cards.setLayout(new CardLayout());

        this.cards.add(ComponentsFactory.buildConfigurationCard(model), "FIRSTPAGE");
        for (Module module : model.getModules()) {
            this.cards.add(ComponentsFactory.buildModuleCard(module, this.values), module.getName());
        }

        JScrollPane scrollPane = new JScrollPane(this.cards);
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(createToolBar(), BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.setBorder(new EmptyBorder(0, 2, 2, 2));
        panel.setPreferredSize(new Dimension(640, 480));

        return panel;
    }

    @Override
    protected void startup() {
        try {
            this.app.init();
            getMainFrame().add(createMainPanel(this.app.getConfiguration()));
            getMainFrame().setJMenuBar(createMenuBar());
            
            getMainFrame().setSize(100, 200);
            show(getMainFrame());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Main entry point.
     */
    public static void main(final String[] args) {
        launch(ConfigoSwing.class, args);
    }

}
