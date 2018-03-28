<%@ page import="java.io.*" isErrorPage="true" info="handles errors"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Error Page JSP</title>
<link rel="stylesheet" href="styles/main.css" type="text/css" />
</head>
<body>
	<h1>Java Error</h1>
	<p>Sorry, Java has thrown an exception.</p>
	<p>To continue, click the Back button.</p>

	<h2>Details</h2>
	<p style="">
		<%
			if (exception != null) {
				exception.printStackTrace(new PrintWriter(out));
			}
		%>
	</p>
</body>
</html>