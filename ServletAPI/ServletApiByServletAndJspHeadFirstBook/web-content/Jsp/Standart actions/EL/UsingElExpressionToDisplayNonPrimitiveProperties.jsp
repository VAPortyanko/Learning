<html>

	<head>
		<title>Form to jsp parameter transfer</title>
	</head>
	
	<body>

		<jsp:useBean id="dogOwner"
		             class="by.pva.servletapi.jsp.standartactions.el.DogOwner"
		             type="by.pva.servletapi.jsp.standartactions.el.DogOwner">
		</jsp:useBean>
 
		Dog's name is:(${requestScope.dogOwner.dog.name})
		<br>
		<%= ((by.pva.servletapi.jsp.standartactions.el.DogOwner) request.getAttribute("dogOwner")).getDog().getName() %>
					
	</body>

</html>