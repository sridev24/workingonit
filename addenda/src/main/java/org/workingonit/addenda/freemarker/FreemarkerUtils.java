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
package org.workingonit.addenda.freemarker;

import org.workingonit.addenda.freemarker.directives.DefaultStringDirective;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;

/**
 * Various utility methods for the Freemarker template engine.
 *
 * @author Vladimir Ritz Bossicard
 */
public final class FreemarkerUtils {

    private final static String DEFAULT_FOLDERNAME = "/resources";

    public static Configuration createClassloaderConfiguration(final Class<?> parent) {
        return createClassloaderConfiguration(parent, DEFAULT_FOLDERNAME);
    }

    /**
     * Returns a Freemarker configuration that supposes that all the resources
     * are located under the <code>folder</code> and available via the classpath
     * (i.e. inside a jar or webapp).
     * 
     * @param parent
     *            class used for the invocation of
     *            <code>Class.getResource()</code> method.
     * @param folder
     *            name of the folder containing the resources
     */
    public static Configuration createClassloaderConfiguration(final Class<?> parent, final String folder) {
        Configuration cfg = new Configuration();
        cfg.setClassForTemplateLoading(parent, folder);
        cfg.setObjectWrapper(new DefaultObjectWrapper());
        cfg.setSharedVariable("defaultString", new DefaultStringDirective());

        return cfg;
    }

}
