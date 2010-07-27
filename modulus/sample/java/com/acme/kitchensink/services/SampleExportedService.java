/*
 * Copyright (C) 2008-2010 Vladimir Ritz Bossicard
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
import org.springframework.jmx.export.annotation.ManagedOperationParameter;
import org.springframework.jmx.export.annotation.ManagedOperationParameters;
import org.springframework.jmx.export.annotation.ManagedResource;

/**
 * @author Vladimir Ritz Bossicard
 */
@ManagedResource(
    description="sample MBean",
    objectName="com.acme.services:name=SampleMBean")
public class SampleExportedService {

    private String name;

    @ManagedAttribute(description="sets the name attribute")
    public void setName(String value) {
        this.name = value;
    }

    @ManagedAttribute(description="returns the attribute name")
    public String getName() {
        return this.name;
    }

    @ManagedOperation(description="simple processing")
    @ManagedOperationParameters({
        @ManagedOperationParameter(name="value1", description="The first value"),
        @ManagedOperationParameter(name="value2", description="The second value")})
    public void process(int value1, int value2) {
        System.out.println("processing " + value1 + "/" + value2);
    }

    @ManagedOperation(description="simple addition")
    @ManagedOperationParameters({
        @ManagedOperationParameter(name="value1", description="The first value"),
        @ManagedOperationParameter(name="value2", description="The second value")})
    public int addition(int value1, int value2) {
        return value1 + value2;
    }

}