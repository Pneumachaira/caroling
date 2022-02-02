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
<title><c:out value="${playlist.name}"></c:out></title>
  <!-- Bootstrap -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" 
      rel="stylesheet" 
      integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" 
      crossorigin="anonymous">

</head>
<body>
    <div class="container"> <!-- Beginning of Container -->
        <h2><c:out value="${playlist.name}"></c:out></h2>
        <table class="table table-striped table-borderd mt-4 table-info">
        <tr>
        	<th>Songs</th>
        </tr>
	        <c:forEach var="song" items="${playlistSongs}">
	        	<tr>
	        		<td>
	        			<c:out value="${song.song.title}"></c:out>
	        		</td>
	        	</tr>
	        </c:forEach>
        </table>
        <c:forEach var="song" items="${playlistSongs}">
	        <table class="table table-striped table-borderd mt-4">
	        <tr>
	        	<th><c:out value="${song.song.title}"></c:out></th>
	        </tr>
		        <c:forEach var="lyric" items="${song.song.lyrics}">
		        	<tr>
		        		<td>
		        			<c:out value="${lyric.line}"></c:out>
		        		</td>
		        	</tr>
		        </c:forEach>
	        </table>
        </c:forEach>
        <a href="/">Back home!</a>
        <a href="/editplaylist/${playlist.id}">Edit playlist</a>
    </div> <!-- End of Container -->
</body>
</html>