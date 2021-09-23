<html>
<body>
	<!-- The servlet instance method -->
	<%!int incrementCount() {  
		count = count + 1;
		return count;
	}%>
	
	<!-- The servlet instance variable -->
	<%!int count = 1;%>
	
	The page count is now:
	<%=incrementCount()%>

	<br>
	<br>

	<% int count = 9;%>
	<% count++;%>
	<% out.println("Local variable \"count\": " + count);%>
	<br>
	<% out.println("Instance variable \"count\": " + this.count);%>
	<br>
	<br>
	Refresh the page (F5)!
	
	<br>
	<br>
	<% out.println("<a href=\"" + request.getContextPath() + "\">Home page</a>"); %>
	
</body>
</html>

<!-- The jsp above will be translated into something like this:

public class basicCounter_jsp extends SomeSpecialHttpServlet {
	
	int incrementCount() {
		count = count + 1;
		return count;
	}

	int count=1;

	public void _jspService(HttpServletRequest request, HttpServletResponse response)throws java.io.IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("€œtext/html");
		out.write("<html><body>");
		out.write("€œThe page count is now:€");
		out.print(incrementCount());
		int count = 10;
		count++;
		out.write("Local variable \"count\": " + count);
		out.write(€œ"Instance variable \"count\": " + this.count€);
		out.write("€œ</body></html>"€);
	}
	
}
 -->