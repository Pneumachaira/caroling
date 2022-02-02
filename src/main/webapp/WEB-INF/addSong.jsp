<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
   <!-- c:out ; c:forEach ; c:if -->
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
   <!-- Formatting (like dates) -->
 <%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
   <!-- form:form -->
 <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>  
   <!-- for rendering errors on PUT routes -->
 <%@ page isErrorPage="true" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Song</title>
  <!-- Bootstrap -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" 
      rel="stylesheet" 
      integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" 
      crossorigin="anonymous">

</head>
<body>
    <div class="container"> <!-- Beginning of Container -->
	    <h2>Add a song!</h2>
	    <form:form action="/songs" method="POST" modelAttribute="song">
	    	<form:errors path="title" class="text-danger" />
	    	<form:label path="title">Title</form:label>
	    	<form:input path="title" /><br/>
	    	<form:errors path="style" class="text-danger"/>
	    	<form:radiobutton path="style" value="secular" />Secular
	    	<form:radiobutton path="style" value="religious" />Religious<br/>
	    	<input type="submit" value="Submit" class="btn btn-primary">
	    </form:form>
    	<a href="/">Back home!</a>
    </div> <!-- End of Container -->
</body>
</html>