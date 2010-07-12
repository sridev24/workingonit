<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="linguae" uri="http://workingonit.googlecode.com/tags/linguae" %>

<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
   	<link href="<spring:theme code="styleSheet"/>" rel="stylesheet" type="text/css"/>
   	<title>Linguae</title>
</head>

<body>
	<table align="center" width="100%">
		<tr>
			<td><img src="<spring:theme code="logo"/>"/></td>
			<td align="right"><linguae:flags/></td>
		</tr>
	</table>

    <spring:message code="org.wkg.linguae.greetings"/><p>
</body>

</html>
