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

import org.springframework.jmx.export.annotation.ManagedResource;
import org.workingonit.modulus.AuscultableBean;
import org.workingonit.modulus.Diagnostic;
import org.workingonit.modulus.DiagnosticBuilder;
import org.workingonit.modulus.annotation.Group;
import org.workingonit.modulus.checks.ReacheableUrlCheck;

/**
 * @author Vladimir Ritz Bossicard
 */
@Group(name="Group 1")
@ManagedResource(
  description="Service A MBean",
  objectName="com.acme.kitchensink.services:name=ServiceAMBean")
public class ServiceA implements AuscultableBean {

  private String url; // invalid one

  public void setUrl(String url) {
    this.url = url;
  }

  public Diagnostic auscultate() {
    return new DiagnosticBuilder("service A diagnostic").add(new ReacheableUrlCheck("Web service", this.url)).build();
  }

}
