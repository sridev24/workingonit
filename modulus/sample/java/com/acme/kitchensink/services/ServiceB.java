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

import org.springframework.jmx.export.annotation.ManagedResource;
import org.workingonit.modulus.AuscultableBean;
import org.workingonit.modulus.Diagnostic;
import org.workingonit.modulus.DiagnosticBuilder;
import org.workingonit.modulus.annotation.Group;
import org.workingonit.modulus.checks.ReacheableUrlCheck;
import org.workingonit.modulus.checks.ReadeableFileCheck;
import org.workingonit.modulus.checks.WritableFileCheck;

/**
 * @author Vladimir Ritz Bossicard
 */
@Group(name="Group 2")
@ManagedResource(
    description="Service B MBean",
    objectName="com.acme.kitchensink.services:name=ServiceBMBean")
public class ServiceB implements AuscultableBean {

    private File dir;
    private String url; // = new URL("http://www.google.ch");

    public void setOutDir(File dir) {
        this.dir = dir;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Diagnostic auscultate() {
        return new DiagnosticBuilder("service B diagnostic")
            .add(new ReadeableFileCheck("output", this.dir, true))
            .add(new WritableFileCheck("output", this.dir))
            .add(new ReacheableUrlCheck("Output web service", this.url))
            .build();
    }

}
