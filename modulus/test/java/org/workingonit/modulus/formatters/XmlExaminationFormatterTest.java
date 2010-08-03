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
package org.workingonit.modulus.formatters;

import java.util.Date;
import java.util.Properties;

import org.testng.annotations.Test;
import org.workingonit.modulus.DiagnosticBuilder;
import org.workingonit.modulus.Examination;
import org.workingonit.modulus.Platform;
import org.workingonit.modulus.findings.EvaluatedFinding;
import org.workingonit.modulus.findings.Information;

/**
 * @author Vladimir Ritz Bossicard
 */
@Test
public class XmlExaminationFormatterTest {

  public static Examination createExamination() {
    Examination examination = new Examination();

    Platform platform = new Platform("sample application");
    Properties props = new Properties();
    props.put("version", "2.5.6");
    props.put("svn version", "123424");
    props.put("start date", new Date());
    platform.setProperties(props);

    examination.setPlatform(platform);

    examination.addDiagnostic(new DiagnosticBuilder("diagnostic 1").add(new Information("Info 1")).build());
    examination.addDiagnostic(new DiagnosticBuilder("diagnostic 2").add(new EvaluatedFinding("Evaluation 1", true))
        .add(new EvaluatedFinding("Evaluation 2", false)).add(new Information("Info 1")).build());

    return examination;
  }

  public void generate_xml() {
    XmlExaminationFormatter formatter = new XmlExaminationFormatter();
    String res = formatter.format(createExamination());
    System.out.println(res);
  }

  public void generate_html() {
    XslExaminationFormatter formatter = new XslExaminationFormatter();
    formatter.setTemplateName("/resources/medicus-html.xsl");
    String res = formatter.format(createExamination());
    System.out.println(res);
  }

  public void generate_txt() {
    XslExaminationFormatter formatter = new XslExaminationFormatter();
    formatter.setTemplateName("/resources/medicus-txt.xsl");
    String res = formatter.format(createExamination());
    System.out.println(res);
  }

}
