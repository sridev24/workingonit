/*
 * Copyright (C) 2009-2010 Vladimir Ritz Bossicard
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.workingonit.modulus;

import java.io.Serializable;
import java.util.Properties;

import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedResource;

/**
 * @author Vladimir Ritz Bossicard
 */
@ManagedResource(
    description="multiple introspection MBean",
    objectName="org.workingonit.modulus:name=PlatformMBean")
public class Platform implements AvailableBean, Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    protected Properties props = new Properties();

    public Platform(String name) {
        this.name = name;
    }

    @ManagedAttribute
    public String getName() {
        return this.name;
    }

    public void setProperties(Properties props) {
        this.props.putAll(props);
    }

    @ManagedAttribute
    public Properties getProperties() {
        return this.props;
    }

    /**
     * Returns {@link AvailableBean#AVAILABLE} by default.
     */
    @Override
    @ManagedAttribute
    public int getAvailability() {
        return AVAILABLE;
    }

}