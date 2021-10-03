<%@ taglib prefix="mine" uri="DiceFunctions" %>
<html>

	<head>
		<title>Dice roll test</title>
	</head>
	
	<body>
 
		Dice roll result: ${mine:rollIt()}
					
		<br><br>
		<a href="${pageContext.request.contextPath}/">Home page</a>					
	</body>

</html>