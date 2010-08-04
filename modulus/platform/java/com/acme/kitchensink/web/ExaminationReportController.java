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
package com.acme.kitchensink.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.workingonit.modulus.AuscultableBeanRegistrar;
import org.workingonit.modulus.findings.Finding.Status;

/**
 * @author Vladimir Ritz Bossicard
 */
@Controller
public class ExaminationReportController {

  @Autowired
  private AuscultableBeanRegistrar registrar;

  @RequestMapping("/medicus.htm")
  protected ModelAndView medicus() throws Exception {
    this.registrar.auscultate();
    return new ModelAndView("medicus", "examination", this.registrar.lastExamination());
  }

  public static String format(Status status) {
    switch (status) {
      case NEUTRAL:
        return "";
      case OK:
        return "<img src=\"themes/images/green-ball.png\"/>";
      case WARNING:
        return "<img src=\"themes/images/orange-ball.png\"/>";
      case ERROR:
        return "<img src=\"themes/images/red-ball.png\"/>";
      default:
        return "";
    }
  }

}
