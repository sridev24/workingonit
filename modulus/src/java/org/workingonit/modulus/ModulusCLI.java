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
 * Version      : $Revision: 347 $
 * Last edit    : $Date: 2010-02-19 14:02:20 +0100 (Fri, 19 Feb 2010) $
 * Last editor  : $Author: vbossica $
 */
package org.workingonit.modulus;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.lang.StringUtils;
import org.workingonit.addenda.freemarker.FreemarkerOutputTemplate;
import org.workingonit.addenda.freemarker.FreemarkerUtils;
import org.workingonit.modulus.AuscultableBeanRegistrar.PlatformGroup;
import org.workingonit.modulus.formatters.ExaminationFormatter;
import org.workingonit.modulus.formatters.TextExaminationFormatter;
import org.workingonit.modulus.formatters.XmlExaminationFormatter;

import freemarker.template.Configuration;

/**
 * @author Vladimir Ritz Bossicard
 */
public class ModulusCLI {

    public final static String DEFAULT_PREFIX = "org.workingonit.modulus";

    protected Configuration cfg;

    public ModulusCLI() {
        this.cfg = FreemarkerUtils.createClassloaderConfiguration(getClass());
    }

    protected void start(final String[] args) {
        Options options = createOptions();
        try {
            CommandLineParser parser = new GnuParser();
            CommandLine line = parser.parse(options, args);

            if ((line.getOptions().length == 0) || line.hasOption("help") || line.hasOption("h")) {
                new HelpFormatter().printHelp(getClass().getName() + " [options]", options);
                return;
            }

            System.out.println("Modulus Client");

            String service = line.getOptionValue("service");
            String mbean = StringUtils.defaultString(line.getOptionValue("prefix"), DEFAULT_PREFIX);
            String username = line.getOptionValue("username");
            String password = line.getOptionValue("password");
            String format = StringUtils.defaultString(line.getOptionValue("format"), "txt");

            String result = proceed(service, username, password, mbean, format);
            System.out.println(result);
        } catch (ParseException e) {
            System.err.println("Execution failed : " + e.getMessage());
            new HelpFormatter().printHelp(getClass().getName() + " [options]", options);
        } catch (Exception e) {
            System.err.println("Execution failed : " + e.getMessage());
            e.printStackTrace(System.err);
        }
    }

    protected String generate(String service, String username, String password, String prefix) throws Exception {
        MBeanServerConnection mbsc = connect(service, username, password);

        String paltformMBean = prefix + ":name=PlatformMBean";
        String appName = (String) mbsc.getAttribute(new ObjectName(paltformMBean), "Name");

        String examinationMBean = prefix + ":name=ExaminationMBean";
        PlatformGroup[] groups = (PlatformGroup[]) mbsc.invoke(new ObjectName(examinationMBean), "listGroupNames", null, null);

        Map<String, Object> model = new HashMap<String, Object>();
        model.put("appname", appName);
        model.put("prefix", prefix);
        model.put("connection", service);
        model.put("groups", groups);

        return new FreemarkerOutputTemplate(this.cfg).writeToString(model,
            "modulus-plugin.xml.ftl");
    }


    protected String proceed(String service, String username, String password, String prefix,
            String format) throws Exception {
        MBeanServerConnection mbsc = connect(service, username, password);

        String examinationMBean = prefix + ":name=ExaminationMBean";
        ObjectName name = new ObjectName(examinationMBean);

        // the beans are evaluated each time!
        mbsc.invoke(name, "auscultate", null, null);
        Examination examination = (Examination) mbsc.invoke(name, "lastExamination", null, null);

        ExaminationFormatter formatter = "xml".equals(format) ? new XmlExaminationFormatter() : new TextExaminationFormatter();
        return formatter.format(examination);
    }

    /**
     * @param service
     * @param username
     * @param password
     * @return
     * @throws IOException
     * @throws MalformedURLException
     */
    private MBeanServerConnection connect(String service, String username,
            String password) throws IOException, MalformedURLException {
        // System.out.println("connecting to " + service + "\n");

        Map<String,Object> env = new HashMap<String,Object>();
        if ((username != null) && (password != null)) {
            env.put("jmx.remote.credentials", new String[] { username , password });
        }

        JMXConnector jmxc = JMXConnectorFactory.connect(new JMXServiceURL(service), env);
        MBeanServerConnection mbsc = jmxc.getMBeanServerConnection();
        return mbsc;
    }

    /**
     * Returns the {@link Options} available through the command line interface.
     */
    @SuppressWarnings("static-access")
    private Options createOptions() {
        Options options = new Options();
        options.addOption(
            OptionBuilder.withLongOpt("service").hasArg().withArgName("string").withDescription("JMX service URL").isRequired(true).create('s'));
        options.addOption(
            OptionBuilder.withLongOpt("prefix").hasArg().withArgName("string").withDescription("MBean prefix").isRequired(false).create());
        options.addOption(
            OptionBuilder.withLongOpt("username").hasArg().withArgName("string").withDescription("JMX username").isRequired(false).create('u'));
        options.addOption(
            OptionBuilder.withLongOpt("password").hasArg().withArgName("string").withDescription("JMX password").isRequired(false).create('p'));
        options.addOption(
            OptionBuilder.withLongOpt("format").hasArg().withArgName("string").withDescription("output format (txt or xml)").isRequired(false).create('f'));
        options.addOption(new Option("h", "help", false, "prints this message"));

        return options;
    }

    public static void main(final String[] args) {
        new ModulusCLI().start(args);
    }

}