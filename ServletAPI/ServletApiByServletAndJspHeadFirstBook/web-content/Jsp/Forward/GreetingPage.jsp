<html>
	<body>
		Welcome to our page!
		<% if (request.getParameter("userName") == null || request.getParameter("userName").equals("")) { %>
		<jsp:forward page="FormPage.jsp" />
		<% } %>
		Hello ${param.userName}
	</body>
</html>