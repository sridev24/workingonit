/*
 * Copyright 2010 Vladimir Ritz Bossicard
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
 * Version     : $Revision: 344 $
 * Last edit   : $Date: 2010-02-16 21:58:48 +0100 (Tue, 16 Feb 2010) $
 * Last editor : $Author: vbossica $
 */
package org.workingonit.addenda.concordion;

import java.io.File;

import org.concordion.Concordion;
import org.concordion.internal.ConcordionBuilder;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * @author Vladimir Ritz Bossicard
 */
public class ConcordionFactoryBean implements FactoryBean<Concordion>, InitializingBean {

    private Concordion concordion;
    private File baseOutputDir;

    public void setBaseOutputDir(File baseOutputDir) {
        this.baseOutputDir = baseOutputDir;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        ConcordionBuilder builder = new ConcordionBuilder();

        if (this.baseOutputDir != null) {
            this.baseOutputDir.mkdirs();
            builder.withBaseOutputDir(this.baseOutputDir);
        }
        this.concordion = builder.build();
    }

    @Override
    public Class<? extends Concordion> getObjectType() {
        return Concordion.class;
    }

    @Override
    public Concordion getObject() throws Exception {
        return this.concordion;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

}
