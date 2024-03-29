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
package org.workingonit.modulus;

import java.io.Serializable;
import java.util.List;

/**
 * @author Vladimir Ritz Bossicard
 */
public class GroupedDiagnostics implements Serializable {

  private static final long serialVersionUID = 1L;

  private String name;
  private List<Diagnostic> diagnostics;

  /**
   * @param name
   * @param res
   */
  public GroupedDiagnostics(String name, List<Diagnostic> diagnostics) {
    this.name = name;
    this.diagnostics = diagnostics;
  }

  public String getName() {
    return this.name;
  }

  public List<Diagnostic> getDiagnostics() {
    return this.diagnostics;
  }

}
