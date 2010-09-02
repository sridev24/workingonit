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

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.testng.annotations.Test;

/**
 * @author Vladimir Ritz Bossicard
 */
@Test
public class MedicusCLITest {

  private final static String HOST = "localhost";
  private final static String PORT = "18080";

  private final static String SERVICE = "service:jmx:rmi:///jndi/rmi://" + HOST + ":" + PORT + "/jmxrmi";

  public void proceed() throws Exception {
    System.out.println(new ModulusCLI().proceed(SERVICE, "", "", "com.acme.kitchensink", "txt"));
  }

  public void generatePlugin() throws Exception {
    String data = new ModulusCLI().generate(SERVICE, "", "", "com.acme.kitchensink");
    System.out.println(data);

    FileUtils.writeStringToFile(new File("sample/conf/kitchensink-webapp-plugin.xml"), data, "UTF-8");
  }

}