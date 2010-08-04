/*
 * Copyright (C) 2010 Vladimir Ritz Bossicard
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
package org.workingonit.modulus.hibernate3;

import java.util.ArrayList;
import java.util.Collection;

public class SessionFactoryConfigurator {

  private Collection<Class<?>> annotatedClasses = new ArrayList<Class<?>>();
  private Collection<String> annotatedPackages = new ArrayList<String>();

  public Collection<Class<?>> getAnnotatedClasses() {
    return annotatedClasses;
  }

  public void setAnnotatedClasses(Collection<Class<?>> annotatedClasses) {
    this.annotatedClasses = annotatedClasses;
  }

  public Collection<String> getAnnotatedPackages() {
    return annotatedPackages;
  }

  public void setAnnotatedPackages(Collection<String> annotatedPackages) {
    this.annotatedPackages = annotatedPackages;
  }

}
