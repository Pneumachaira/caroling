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
<title>Edit Song</title>
  <!-- Bootstrap -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" 
      rel="stylesheet" 
      integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" 
      crossorigin="anonymous">

</head>
<body>
    <div class="container"> <!-- Beginning of Container -->
    <h2><c:out value="${song.title}"></c:out></h2>
		<form:form action="/songs" method="POST" modelAttribute="song">
		<input type="hidden" name="_method" value="PUT">
			<form:input type="hidden" path="id" value="${song.id}" />
	    	<form:errors path="title" class="text-danger" />
	    	<form:input path="title" value="${song.title}"/><br/>
    	<form:radiobutton path="style" value="secular" />Secular
    	<form:radiobutton path="style" value="religious" />Religious<br/>
	    	<input type="submit" value="Edit title/style" class="btn btn-warning">
    	</form:form>
    	<table class="table table-striped table-borderd mt-4">
        <tr>
        	<th>Lyrics</th>
        	<th>Actions</th>
        </tr>
	        <c:forEach var="lyric" items="${song.lyrics}">
	        	<tr>
	        		<td>
	        			<c:out value="${lyric.line}"></c:out>
	        		</td>
	        		<td>
	        			<form action="/songs/${song.id}/${lyric.id}" method="POST">
		        			<input type="hidden" name="_method" value="DELETE">
		        			<input type="submit" value="Delete lyric" class="btn btn-danger btn-sm">
	        			</form>
	        		</td>
	        	</tr>
	        </c:forEach>
        </table>
    	<form action="/songs/${song.id}" method="POST">
    		<label>Song lyric: </label>
    		<input type="text" name="line" autofocus>
    		<input type="submit" value="Add lyric">
    	</form>
    	<form action="/songs/${song.id}" method="POST">
  			<input type="hidden" name="_method" value="DELETE">
  			<input type="submit" value="Delete song" class="btn btn-danger mt-4 mb-4">
    	</form>
    	<a href="/songs/${song.id}">View song</a> | 
    	<a href="/">Back home!</a>
    </div> <!-- End of Container -->
</body>
</html>