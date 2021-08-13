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
	
</body>
</html>

<!-- 
public class basicCounter_jsp extends SomeSpecialHttpServlet {
	
	int incrementCount() {
		count = count + 1;
		return count;
	}

	int count=1;

	public void _jspService(HttpServletRequest request, HttpServletResponse response)throws java.io.IOException {
		PrintWriter out = response.getWriter();
		response.setContentType(“text/html”);
		out.write(“<html><body>”);
		out.write(“The page count is now:”);
		out.print(incrementCount());
		int count = 10;
		count++;
		out.write("Local variable \"count\": " + count);
		out.write(“"Instance variable \"count\": " + this.count”);
		out.write(“</body></html>”);
	}
	
}
 -->