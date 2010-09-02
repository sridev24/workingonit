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
package org.workingonit.modulus;

import java.io.Serializable;

import org.apache.commons.lang.ArrayUtils;
import org.workingonit.addenda.commons.lang.EnumsUtils;
import org.workingonit.modulus.findings.Finding;
import org.workingonit.modulus.findings.Finding.Status;

/**
 * @author Vladimir Ritz Bossicard
 */
public class Diagnostic implements Serializable {

  private static final long serialVersionUID = 1L;

  private String name;
  private Finding[] findings;
  private Status status = Status.NEUTRAL;

  public Diagnostic(String name, Finding[] findings) {
    this.name = name;
    this.findings = findings;

    evaluateStatus();
  }

  public String getName() {
    return this.name;
  }

  public void addFindings(Finding[] findings) {
    this.findings = (Finding[]) ArrayUtils.addAll(this.findings, findings);

    evaluateStatus();
  }

  public Finding[] getFindings() {
    return this.findings;
  }

  /**
   * Returns the "maximum" status of all defined <i>findings</i>.
   */
  public Status getStatus() {
    return this.status;
  }

  private void evaluateStatus() {
    for (Finding finding : this.findings) {
      this.status = (Status) EnumsUtils.maximum(this.status, finding.getStatus());
    }
  }

}
