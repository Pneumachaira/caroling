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
<title>Edit Playlist</title>
  <!-- Bootstrap -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" 
      rel="stylesheet" 
      integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" 
      crossorigin="anonymous">

</head>
<body>
    <div class="container"> <!-- Beginning of Container -->
        <h2><c:out value="${playlist.name}"></c:out></h2>
	    <form action="/playlists/${playlist.id}" method="POST">
	    	<input type="hidden" name="_method" value="PUT" >
	    	<label>Name</label>
	    	<input name="name" value="${playlist.name}" />
	    	<input type="submit" value="Submit" class="btn btn-primary">
	    </form>
	    <table class="table table-striped table-borderd mt-4">
        <tr>
        	<th>Songs</th>
        </tr>
	        <c:forEach var="song" items="${playlist.songs}">
	        	<tr>
	        		<td>
	        			<c:out value="${song.title}"></c:out>
	        		</td>
	        	</tr>
	        </c:forEach>
        </table>
    	<form:form action="/playlists/${playlist.id}" method="POST" modelAttribute="playlistSong">
    		<form:errors path="song" class="text-danger"/>
    		<form:hidden path="playlist" value="${playlist.id}" />
    		<form:select path="song">
    			<c:forEach var="song" items="${songs}">
    				<form:option value="${song.id}"><c:out value="${song.title}"></c:out></form:option>
    			</c:forEach>
    		</form:select>
    		<input type="submit" value="Add song">
    	</form:form>
    	<a href="/playlists/${playlist.id}">View playlist</a> | 
    	<a href="/">Back home!</a>
    </div> <!-- End of Container -->
</body>
</html>