<%@ page import="com.example.web.jsp.Factorial" %>

<html>
<body>
The page count is:
<% out.println(com.example.web.jsp.Counter.getCount()); %> <!-- Full qualified name (package name + class name). -->
<br>
<% out.println(Factorial.getFactorial()); %> <!-- Just class name (see <%@ page %> directive with the import attribute above). -->
<br>
<br>
Refresh the page (F5)!
</body>
</html>