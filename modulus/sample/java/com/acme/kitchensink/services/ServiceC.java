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
 *
 * Version      : $Revision: 319 $
 * Last edit    : $Date: 2010-01-18 22:01:37 +0100 (Mon, 18 Jan 2010) $
 * Last editor  : $Author: vbossica $
 */
package com.acme.kitchensink.services;

import java.io.File;
import java.util.Date;

import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.workingonit.modulus.AuscultableBean;
import org.workingonit.modulus.Diagnostic;
import org.workingonit.modulus.DiagnosticBuilder;
import org.workingonit.modulus.checks.ReacheableUrlCheck;
import org.workingonit.modulus.checks.WritableFileCheck;
import org.workingonit.modulus.findings.Information;

/**
 * @author Vladimir Ritz Bossicard
 */
@ManagedResource(
    description="Service C MBean",
    objectName="com.acme.kitchensink.services:name=ServiceCMBean")
public class ServiceC implements AuscultableBean {

    private String url; // http://almaden:8080/sample
    private int counter = 0;

    public void setUrl(String url) {
        this.url = url;
    }

    @ManagedOperation
    public void increaseCounter() {
        this.counter++;
    }

    @ManagedOperation
    public void resetCounter() {
        this.counter = 0;
    }

    @ManagedAttribute
    public int getCounter() {
        return this.counter;
    }

    public Diagnostic auscultate() {
        return new DiagnosticBuilder("service C diagnostic")
            .add(new Information("starting date " + new Date()))
            .add(new ReacheableUrlCheck("Web service", this.url))
            .add(new WritableFileCheck("Temp directory", new File("/tmp/dummy"), false))
            .build();
    }

}