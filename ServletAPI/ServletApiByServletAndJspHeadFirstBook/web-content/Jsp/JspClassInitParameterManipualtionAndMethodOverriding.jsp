<html>
	<body>
	    <%-- The method jspInit is overrided! --%>
		<%!
			public void jspInit() {
				ServletContext ctx = getServletContext();
				ctx.setAttribute("JspAppParameter", "AppParameterValueInJSP");
			}
		%>
		
		In this example we have set an application scope attribute in the jspInit() method.
		And have read the jsp init parameter from deployment descriptor (web.xml).  
		<br>
		<br>
		
		<%
			ServletConfig sConfig = getServletConfig();
			String jspInitParam1 = sConfig.getInitParameter("jspParam1"); 
			out.println("The jsp init parameter: " + jspInitParam1);
			out.println("<br>");
			out.println("The app parameter in jsp: " + application.getAttribute("JspAppParameter"));
		%>
		
		<br>
		<br>
		<% out.println("<a href=\"" + request.getContextPath() + "\">Home page</a>"); %>
		
	</body>
</html>