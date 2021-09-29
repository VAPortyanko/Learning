<html>
	<head>
		<title>JSP standart actions</title>
	</head>
	<body>
		<div>
			<jsp:useBean id="person" class="by.pva.servletapi.jsp.standartactions.Person" scope="request" />
			<span style="font-weight: bold">1) jsp:useBean + jsp:getProperty standart actions allow to get the been property and use it in a jsp:</span>
			<br>
			Person name: <jsp:getProperty name="person" property="name" />
		</div>

		<br>
		
		<div>
			<jsp:useBean id="newPerson" class="by.pva.servletapi.jsp.standartactions.Person" scope="request" />
			<span style="font-weight: bold">2) If a Servelt Container can't find a bean it create new one:</span> 
			<br>
			newPerson name: <jsp:getProperty name="newPerson" property="name" />
		</div>	
		
		<br>
		
		<div>
			<span style="font-weight: bold">3) The standart action jsp:setProperty allow us to set beean properties</span>
			<br>
			<jsp:setProperty name="newPerson" property="name" value="Fred"/>
			newPerson name: <jsp:getProperty name="newPerson" property="name" />
		</div>
		
		<br>
		
		<div>
			<span style="font-weight: bold">
			4) The standart action jsp:setProperty that reside outside of the jsp:useBean will ALWAYS change the property!
			   Put jsp:setProperty inside of the jsp:useBean to set the property value only if it is a new bean.
			</span>
			
			<br>
			
			<jsp:useBean id="newPerson2" class="by.pva.servletapi.jsp.standartactions.Person" scope="request">
				<jsp:setProperty name="newPerson2" property="name" value="Kelvin"/>
			</jsp:useBean>
			newPerson2 name: <jsp:getProperty name="newPerson2" property="name" />
		</div>
		
		<br>
		
		<div>
			<span style="font-weight: bold">5) The class inheritance example</span>
			<br>
			<jsp:useBean id="dog" type="by.pva.servletapi.jsp.standartactions.Animal" class="by.pva.servletapi.jsp.standartactions.Dog" scope="request" />
			Animal name: <jsp:getProperty name="dog" property="name" />
		</div>
		
		<br>
		<% out.println("<a href=\"" + request.getContextPath() + "\">Home page</a>"); %>
		
	</body>
</html>