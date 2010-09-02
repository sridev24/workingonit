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
package org.workingonit.modulus.checks;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.workingonit.modulus.findings.EvaluatedFinding;
import org.workingonit.modulus.findings.Finding;

/**
 * <b>Dependencies</b>
 * <ul>
 *    <li>Apache HttpClient</li>
 * </ul>
 *
 * @author Vladimir Ritz Bossicard
 */
public class ReacheableUrlCheck extends AbstractCheck {

  private String url;

  public ReacheableUrlCheck(String description, String url, boolean fatal) {
    super(description, fatal);
    this.url = url;
  }

  public ReacheableUrlCheck(String description, String url) {
    this(description, url, false);
  }

  @Override
  public Finding perform() {
    if (this.url == null) {
      return new EvaluatedFinding("Url '" + this.description + "' must be defined", false, true);
    }
    return new EvaluatedFinding(createMessage(), isReacheable(), this.fatal).addCause("unreacheable url: " + this.url);
  }

  private String createMessage() {
    return this.fatal ? "Url '" + this.description + "' is reacheable (mandatory)" : "Url '" + this.description
        + "' is reacheable (optional)";
  }

  private boolean isReacheable() {
    try {
      HttpClient client = new HttpClient();
      client.getHttpConnectionManager().getParams().setConnectionTimeout(1000);

      return client.executeMethod(new GetMethod(this.url)) == HttpStatus.SC_OK;
    } catch (Exception ex) {
      // ex.printStackTrace();
    }
    return false;
  }

}
