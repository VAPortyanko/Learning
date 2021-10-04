<html>
	<body>
		Welcome to our page!
		<% if (request.getParameter("userName") == null || request.getParameter("userName").equals("")) { %>
		<jsp:forward page="FormPage.jsp" />
		<% } %>
		Hello ${param.userName}
		
		<br><br>
        <a href="${pageContext.request.contextPath}/">Home page</a>	
	</body>
</html>