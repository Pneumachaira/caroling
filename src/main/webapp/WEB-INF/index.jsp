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
<title>Caroling Songs</title>
  <!-- Bootstrap -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" 
      rel="stylesheet" 
      integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" 
      crossorigin="anonymous">

</head>
<body>
    <div class="container"> <!-- Beginning of Container -->
        <h1>Merry Christmas!</h1>
        <table class="table table-striped table-borderd mt-4">
        <tr>
        	<th>Playlists</th>
        </tr>
	        <c:forEach var="playlist" items="${playlists}">
	        	<tr>
	        		<td>
	        			<a href="/playlists/${playlist.id}"><c:out value="${playlist.name}"></c:out></a>
	        		</td>
	        	</tr>
	        </c:forEach>
        </table>
        <table class="table table-striped table-borderd mt-4">
        <tr>
        	<th>Songs</th>
        </tr>
	        <c:forEach var="song" items="${songs}">
	        	<tr>
	        		<td>
	        			<a href="/songs/${song.id}"><c:out value="${song.title}"></c:out></a>
	        		</td>
	        	</tr>
	        </c:forEach>
        </table>
        <a href="/newsong">Add song</a> | 
        <a href="newplaylist">Add playlist</a>
    </div> <!-- End of Container -->
</body>
</html>