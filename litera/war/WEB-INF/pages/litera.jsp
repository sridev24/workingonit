<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
   	<link href="themes/default/stylesheet/litera.css" rel="stylesheet" type="text/css"></link>
   	<title>litera</title>
</head>

<body>
	<img src="themes/default/images/litera-logo.png"/><br>&nbsp;<br>
   	<form:form name="form" action="document.htm">
   	   	<form:input path="url" size="50"/>&nbsp;&nbsp;<input type="submit" value="Generate"/>
   	</form:form>
</body>

</html>