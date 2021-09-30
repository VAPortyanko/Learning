<html>
	
	<head>
		<title>Form to jsp parameter transfer</title>
	</head>
	
	<body>

		<jsp:useBean id="person"
		             type="by.pva.servletapi.jsp.standartactions.Employee"
		             class="by.pva.servletapi.jsp.standartactions.Employee">
		    
		    <!-- block #1 
			<jsp:setProperty name="person" property="name"  param="name" />
			<jsp:setProperty name="person" property="empId" param="empId" />
			-->
			
			<!-- block #2    
			<jsp:setProperty name="person" property="name"/>
			<jsp:setProperty name="person" property="empId"/>
			-->

			<!-- block #3 -->
			<jsp:setProperty name="person" property="*"/>
			
			<!-- If the names of the parameters are the same as the names of the members of the class, 
			then all three blocks above are equivalent  --> 
						
		</jsp:useBean>
		
		<div>Person is: <jsp:getProperty name = "person" property = "name"  /></div>
		<div>ID is:     <jsp:getProperty name = "person" property = "empId" /></div>

	</body>

</html>