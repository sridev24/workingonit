<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page import="
    java.util.List,
    java.util.Map,
    com.acme.kitchensink.web.ExaminationReportController,
    org.workingonit.modulus.*,
    org.workingonit.modulus.findings.Finding"%>

<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
       <link href="themes/stylesheet/kitchensink.css" rel="stylesheet" type="text/css"></link>
       <title>kitchensink</title>
</head>

<body>

<center>
<%
    Examination examination = (Examination) request.getAttribute("examination");     
    Platform platform = examination.getPlatform();
%>

    <table border="0" width="50%">
        <thead>
            <tr colspan="2"><font size="+1"><b><%= platform.getName() %></b></font></tr>
        </thead>
        <tbody>
<% if (platform.getProperties() != null) {
    for (Map.Entry<Object, Object> entry : platform.getProperties().entrySet()) { %>
            <tr>
                <td><%= entry.getKey() %></td>
                <td><%= entry.getValue() %></td>
            </tr>
    <% } %>
<% } %>
        </tbody>
    </table><p>

<%
   List<GroupedDiagnostics> groups = examination.getGroupedDiagnostics();
   for (GroupedDiagnostics group : groups) {
%>
    <table border="0" width="80%">
        <tr>
            <td colspan="2" align="center"><b><%= group.getName()%></b></td>
        </tr>
        <% for (Diagnostic diagnostic : group.getDiagnostics()) { %>
        <tr>
            <td colspan="2"><b><%= diagnostic.getName()%></b></td>
        </tr>
        
            <% for (Finding finding : diagnostic.getFindings()) { %>
        <tr>
            <td width="90%"><%= finding.getMessage() + ((!finding.isSuccessful() && (finding.getCause() != null)) ? "<br>&nbsp;&nbsp;&nbsp;&nbsp;" + finding.getCause() : "") %></td>
            <td style="text-align: center;"><%= ExaminationReportController.format(finding.getStatus()) %></td>
        </tr>
            <% } %>
        <% } %>
    </table><p>
<% } %>


<%
   List<Diagnostic> diagnostics = examination.getDiagnostics();
   for (Diagnostic diagnostic : diagnostics) {
%>

    <table border="0" width="80%">
        <tr>
            <td colspan="2"><b><%= diagnostic.getName()%></b></td>
        </tr>
        <% for (Finding finding : diagnostic.getFindings()) { %>
        <tr>
            <td width="90%"><%= finding.getMessage() + ((!finding.isSuccessful() && (finding.getCause() != null)) ? "<br>&nbsp;&nbsp;&nbsp;&nbsp;" + finding.getCause() : "") %></td>
            <td style="text-align: center;"><%= ExaminationReportController.format(finding.getStatus()) %></td>
        </tr>
        <% } %>
    </table><p>
<% } %>

</center>

<font size="-1">
generation date: <%= examination.getDate() %>
</font>
</body>

</html>