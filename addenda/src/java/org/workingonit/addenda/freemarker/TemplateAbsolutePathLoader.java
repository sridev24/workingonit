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
 * Version     : $Revision: 308 $
 * Last edit   : $Date: 2009-12-16 20:10:54 +0100 (Wed, 16 Dec 2009) $
 * Last editor : $Author: vbossica $
 */
package org.workingonit.addenda.freemarker;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import freemarker.cache.TemplateLoader;

/**
 * {@link TemplateLoader} that loads the template from the file system. 
 *  
 * @author Vladimir Ritz Bossicard
 */
public class TemplateAbsolutePathLoader implements TemplateLoader {

    public Object findTemplateSource(String name) throws IOException {
        File source = new File(name);
        return source.isFile() ? source : null;
    }

    public long getLastModified(Object templateSource) {
        return ((File) templateSource).lastModified();
    }

    public Reader getReader(Object templateSource, String encoding)
            throws IOException {
        if (!(templateSource instanceof File)) {
            throw new IllegalArgumentException("templateSource is a: " + templateSource.getClass().getName());
        }
        return new InputStreamReader(new FileInputStream((File) templateSource), encoding);
    }

    public void closeTemplateSource(Object templateSource) throws IOException {
        // Do nothing.
    }

}
