<%@ page import="by.pva.servletapi.jsp.Factorial" %>

<html>
<body>
The page count is:
<%
	out.println(by.pva.servletapi.jsp.Counter.getCount());
%> <!-- Full qualified name (package name + class name). -->
<br>
<% out.println(Factorial.getFactorial()); %> <!-- Just class name (see <%@ page %> directive with the import attribute above). -->
<br>
<br>
Refresh the page (F5)!
<br>
<br>
<% out.println("<a href=\"" + request.getContextPath() + "\">Home page</a>"); %>
</body>
</html>