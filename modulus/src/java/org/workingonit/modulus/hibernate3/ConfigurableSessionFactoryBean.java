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
import java.util.List;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.orm.hibernate3.annotation.AnnotationSessionFactoryBean;

public class ConfigurableSessionFactoryBean extends
    AnnotationSessionFactoryBean implements ApplicationContextAware {

  private ApplicationContext context;

  public void setApplicationContext(ApplicationContext context)
      throws BeansException {
    this.context = context;
  }

  public void afterPropertiesSet() throws Exception {
    String[] names = this.context.getBeanNamesForType(SessionFactoryConfigurator.class);

    List<Class<?>> cls = new ArrayList<Class<?>>();
    List<String> pkgs = new ArrayList<String>();
    for (String name : names) {
      SessionFactoryConfigurator bean = this.context.getBean(name, SessionFactoryConfigurator.class);
      cls.addAll(bean.getAnnotatedClasses());
      pkgs.addAll(bean.getAnnotatedPackages());
    }
    setAnnotatedClasses(cls.toArray(new Class[] {}));
    setAnnotatedPackages(pkgs.toArray(new String[] {}));
    super.afterPropertiesSet();
  }

}
