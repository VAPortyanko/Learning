<html>

	<head>
		<title>Form to jsp parameter transfer</title>
	</head>
	
	<body>
 
		<!-- Using the dot (.) operator to access properties and map values -->
		Dog's name is: ${dogOwner.dog.name} (breed is ${dogOwner.dog.breed})
		<br><br>
		Parents of the dog:
		<ul>
  			<li>mother: ${dogOwner.dog.parents.mother.name} (breed is ${dogOwner.dog.parents.mother.breed})</li>
  			<li>father: ${dogOwner.dog.parents.father.name} (breed is ${dogOwner.dog.parents.mother.breed})</li>
		</ul> 
		
		<br><br>
		
		<!-- The [] operator is like the dot only way better -->
		Dog's name is: ${dogOwner["dog"]["name"]} (breed is ${dogOwner["dog"]["breed"]})
		<br><br>
		Parents of the dog:
		<ul>
  			<li>mother: ${dogOwner["dog"]["parents"]["mother"]["name"]} (breed is ${dogOwner["dog"]["parents"]["mother"]["breed"]})</li>
  			<li>mother: ${dogOwner["dog"]["parents"]["father"]["name"]} (breed is ${dogOwner["dog"]["parents"]["father"]["breed"]})</li>
		</ul> 		
			
		<br>			
		Dog from the array: ${dogArray["0"]["name"]} 
		<br>			
		Dog from the list: ${dogArray["1"]["name"]} 
		
		<br><br>
		<!-- The EL implicit objects -->
		Dog name from the "requestScope" implicit object: ${requestScope.dogOwner.dog.name}
		<br>
		Host name from the "header" implicit object: ${header["host"]}
							
		<br><br>						
		<a href="${pageContext.request.contextPath}/">Home page</a>					
							
	</body>

</html>