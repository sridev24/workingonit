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

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.workingonit.modulus.Diagnostic;
import org.workingonit.modulus.Examination;
import org.workingonit.modulus.GroupedDiagnostics;
import org.workingonit.modulus.findings.Finding;
import org.workingonit.modulus.findings.Finding.Status;

/**
 * @author Vladimir Ritz Bossicard
 */
public class TextExaminationFormatter implements ExaminationFormatter {

    private final static int MARGIN = 80;

    @Override
    public String format(Examination examination) {
        StringBuffer buffer = new StringBuffer();

        buffer.append(examination.getPlatform().getName() + "\n");
        if (examination.getPlatform().getProperties() != null) {
            for (Map.Entry<Object, Object> entry : examination.getPlatform().getProperties().entrySet()) {
                buffer.append(align(entry.getKey().toString(), entry.getValue().toString()));
            }
        }
        buffer.append("\n");

        for (GroupedDiagnostics group : examination.getGroupedDiagnostics()) {
            buffer.append(StringUtils.center(group.getName(), MARGIN) + "\n");
            buffer.append(StringUtils.center(StringUtils.repeat("=", group.getName().length()), MARGIN) + "\n\n");
            for (Diagnostic diagnostic : group.getDiagnostics()) {
                buffer.append(outpuDiagnostic(diagnostic));
            }
            buffer.append(StringUtils.center("********************", MARGIN) + "\n\n");
        }

        for (Diagnostic diagnostic : examination.getDiagnostics()) {
            buffer.append(outpuDiagnostic(diagnostic));
        }

        return buffer.toString();
    }

    private String outpuDiagnostic(Diagnostic diagnostic) {
        StringBuffer buffer = new StringBuffer();

        buffer.append(alignTitle(diagnostic.getName(), diagnostic.getStatus()));
        for (Finding finding : diagnostic.getFindings()) {
            Status status = finding.getStatus();
            buffer.append(align(finding.getMessage(), status == Status.NEUTRAL ? "" : status.name()));
            if (!finding.isSuccessful() && (finding.getCause() != null)) {
                buffer.append("      " + finding.getCause() + "\n");
            }
        }
        buffer.append("\n");

        return buffer.toString();
    }

    private String alignTitle(String title, Status status) {
        if (status == Status.NEUTRAL) {
            return title + "\n";
        }
        return StringUtils.rightPad(title, MARGIN - status.name().length(), " ") + status.name() + "\n";
    }

    private String align(String left, String right) {
        return "   " + StringUtils.rightPad(left, MARGIN - 6 - right.length(), '.') + right + "\n";
    }

}