<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
	<head>
		<title>JSTL example</title>
	</head>
	<body>
		<c:out value="${'<b>This is a <c:out> and <c:forEach> examples </b>'}"/>
		
		<br>
		
		<c:forEach var = "i" begin = "1" end = "5">
           Item <c:out value = "${i}"/><br>
        </c:forEach>
        
        <br>
        <c:out value="Read more about the JSTL 2.0 - "/><a href = "https://jakarta.ee/specifications/tags/2.0/">https://jakarta.ee/specifications/tags/2.0/</a>
        
        <br><br>
        <a href="${pageContext.request.contextPath}/">Home page</a>		
	</body>
</html>