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
 * Version     : $Revision: 288 $
 * Last edit   : $Date: 2009-11-28 20:32:49 +0100 (Sat, 28 Nov 2009) $
 * Last editor : $Author: vbossica $
 */
package org.workingonit.clusterus.singleton.jgroups;

import java.io.File;

import org.jgroups.Channel;
import org.jgroups.JChannelFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.config.AbstractFactoryBean;

/**
 * Specific {@link FactoryBean} that creates a JGroups {@link Channel} instance.
 *  
 * @author Vladimir Ritz Bossicard
 */
public class ChannelBeanFactory extends AbstractFactoryBean {

    /**
     * Name of the cluster, default to 'SINGULUS'
     */
    private String name = "SINGULUS";
    private boolean autoconnect = false;
    private File config;
    private JChannelFactory factory;

    /**
     * Set the name used as connection name if {@link #setAutoconnect(boolean)}
     * is set to <code>true</code>.
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * If set to <code>true</code>, automatically connects to the created
     * channel using the {@link #name} as identification.
     */
    public void setAutoconnect(final boolean autoconnect) {
        this.autoconnect = autoconnect;
    }

    /**
     * Sets the JGroups XML configuration file.
     */
    public void setConfig(final File config) {
        this.config = config;
    }

    @Override
    public Class<?> getObjectType() {
        return Channel.class;
    }

    @Override
    protected Object createInstance() throws Exception {
        if (this.factory == null) {
            this.factory = new JChannelFactory(this.config);
        }
        Channel channel = this.factory.createChannel();
        if (this.autoconnect) {
            channel.connect(this.name);
        }
        return channel;
    }

    @Override
    public void destroy() throws Exception {
        if (this.factory != null) {
            this.factory.destroy();
        }
        super.destroy();
    }
}