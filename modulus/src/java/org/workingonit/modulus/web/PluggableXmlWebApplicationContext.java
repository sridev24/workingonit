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
package org.workingonit.modulus.web;

import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.web.context.support.XmlWebApplicationContext;

/**
 * This specific <code>ApplicationContext</code> will load all the Spring
 * configuration files found in the classpath matching a particular pattern.
 * This can be very useful when loading an application made only of plug in.
 * 
 * <pre>
 *   &lt;servlet>
 *       &lt;servlet-name>platformServlet&lt;/servlet-name>
 *       &lt;servlet-class>org.springframework.web.servlet.DispatcherServlet&lt;/servlet-class>
 *       &lt;init-param>
 *           &lt;param-name>contextClass&lt;/param-name>
 *           &lt;param-value>org.workingonit.modulus.web.PluggableXmlWebApplicationContext&lt;/param-value>
 *       &lt;/init-param>
 *       &lt;init-param>
 *           &lt;param-name>platformConfigLocation&lt;/param-name>
 *           &lt;param-value>/WEB-INF/context/platformContext.xml&lt;/param-value>
 *       &lt;/init-param>
 *       &lt;init-param>
 *           &lt;param-name>moduleConfigLocation&lt;/param-name>
 *           &lt;param-value>classpath*:moduleContext.xml&lt;/param-value>
 *       &lt;/init-param>
 *       &lt;load-on-startup>1&lt;/load-on-startup>
 *   &lt;/servlet>
 * </pre>
 *
 * @author Vladimir Ritz Bossicard
 */
public class PluggableXmlWebApplicationContext extends XmlWebApplicationContext {
  
  private static final String PLATFORM_LOCATION = "platformConfigLocation";
  private static final String MODULE_LOCATION = "moduleConfigLocation";

  /**
   * Default location for the platform configuration file. Defined as 
   * <i>/WEB-INF/context/platformContext.xml</i>.
   */
  private static final String PLATFORM_LOCATION_DEFAULT = "/WEB-INF/context/platformContext.xml";

  /**
   * Default name for the plugin configuration file. Defined as
   * <i>classpath*:moduleContext.xml</i>.
   */
  private static final String MODULE_LOCATION_DEFAULT = "classpath*:moduleContext.xml";

  private final Log log = LogFactory.getLog(PluggableXmlWebApplicationContext.class);

  @Override
  protected void loadBeanDefinitions(XmlBeanDefinitionReader reader)
      throws IOException {
    super.loadBeanDefinitions(reader);

    String platformLocation = StringUtils.defaultString(
        getServletConfig().getInitParameter(PLATFORM_LOCATION), PLATFORM_LOCATION_DEFAULT);
    reader.loadBeanDefinitions(platformLocation);

    String modulePattern = StringUtils.defaultString(
        getServletConfig().getInitParameter(MODULE_LOCATION), MODULE_LOCATION_DEFAULT);

    ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
    try {
      Resource[] resources = resolver.getResources(modulePattern);
      if (resources.length != 0) {
        reader.loadBeanDefinitions(resources);
      } else {
        if (log.isInfoEnabled()) {
          log.info("No configuration files found matching: " + modulePattern);
        }
      }
    } catch (IOException ex) {
      throw new RuntimeException("Error loading plugin definitions", ex);
    }
  }

}