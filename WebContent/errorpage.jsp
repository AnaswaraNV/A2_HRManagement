<%@ page import="java.io.*" isErrorPage="true" info="handles errors"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Error Page JSP</title>
<link rel="stylesheet" href="styles/main.css" type="text/css" />
</head>
<body>
	<div class="container">
	<h1>Java Error</h1>
	<form action="index.html" method="post">
		<div class="form-group">
  			<p>Sorry, Java has thrown an exception.</p>
			<p>To continue, click the reload button.</p>
	  		<input type="submit" name="Reload" value="Reload" class="btn btn-secondary" /><br/>
	  		<br/> <br/>
       </div>
	</form>
	
	</div>
</body>
</html>