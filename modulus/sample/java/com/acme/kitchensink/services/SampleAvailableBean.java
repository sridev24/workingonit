/*
 * Copyright 2010 Vladimir Ritz Bossicard
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
package com.acme.kitchensink.services;

import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.workingonit.modulus.AvailableBean;

/**
 * This MBean is primarily used to test the monitoring with Hyperic.
 *
 * @author Vladimir Ritz Bossicard
 */
@ManagedResource(
    description="sample available MBean",
    objectName="com.acme.services:name=SampleAvailableMBean")
public class SampleAvailableBean implements AvailableBean {

    private int available = AVAILABLE;

    @ManagedOperation
    public void enable() {
        this.available = AVAILABLE;
    }

    @ManagedOperation
    public void disable() {
        this.available = UNAVAILABLE;
    }

    @ManagedAttribute
    public int getAvailability() {
        return this.available;
    }

}