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

import static org.testng.Assert.assertNotNull;

import java.util.HashMap;

import javax.management.MBeanInfo;
import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

import org.testng.annotations.Test;

/**
 * @author Vladimir Ritz Bossicard
 */
public class HypericTest {

    @Test
    public void retrieve_metric() throws Exception {
        String host = "localhost";
        String port = "18080";

        ObjectName name = new ObjectName("com.acme.services:name=SuccessfulIntrospectionMBean");

        String service = "service:jmx:rmi:///jndi/rmi://"+ host + ":" + port + "/jmxrmi";

        JMXConnector jmxc = JMXConnectorFactory.connect(new JMXServiceURL(service), new HashMap<String,Object>());
        MBeanServerConnection mbsc = jmxc.getMBeanServerConnection();

        MBeanInfo info = mbsc.getMBeanInfo(name);
        System.out.println(info);
        assertNotNull(info);

        Object obj = mbsc.getAttribute(name, "Availability");
        System.out.println(obj.toString());
    }

}
