<html>
	<body>
	    <%-- The method jspInit is overrided! --%>
		<%!
			public void jspInit() {
				ServletContext ctx = getServletContext();
				ctx.setAttribute("JspAppParameter", "AppParameterValueInJSP");
			}
		%>
		
		The jsp init parameters and the jspinit() method overriding page!
		<br>
		<br>
		
		<%
			ServletConfig sConfig = getServletConfig();
			String jspInitParam1 = sConfig.getInitParameter("jspParam1"); 
			out.println("The jsp init parameter: " + jspInitParam1);
			out.println("<br>");
			out.println("The app parameter in jsp: " + application.getAttribute("JspAppParameter"));
			
		%>
	</body>
</html>