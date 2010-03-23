<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
   	<link href="themes/default/stylesheet/litera.css" rel="stylesheet" type="text/css"></link>
   	<title>litera docbook</title>
</head>

<body>
	<img src="themes/default/images/litera-logo.png"/><br>&nbsp;<br>
	Generate Docbook tables...<p>
   	<form:form modelAttribute="tableRequest" method="post">
		<table align="center" border="0">
			<tr><td><b>title:</b>&nbsp;</td><td><form:input path="title" size="40"/></td></tr>
			<tr><td valign="top"><b>content:</b></td><td><form:textarea path="content" rows="10" cols="40"/></td></tr>
		</table>

	   	<p><input type="submit" value="Generate"/>
   	</form:form>
</body>

</html>