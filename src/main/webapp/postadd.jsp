<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Bio.Share - Post</title>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<br>
	<c:if test="${param.postid==null}">
		<h4>Add Post</h4>
		<br>
		<form action="AddPostServlet" method="post" enctype="multipart/form-data">
			<table>
				<tr>
				<td>Title *</td>
				<td><input type="text" name="title"/></td>
				</tr>		
				
				<tr>
				<td>Content *</td>
				<td><input type="text" name="content"/></td>
				</tr>	
				
				<tr>
				<td>Hash Tag *</td>
				<td><input type="text" name="hashtag"/></td>
				</tr>						
				
				<tr>
				<td>Photo</td>
				<td><input type="file" name="photo" accept="image/*"/></td>
				</tr>
				
				<tr>
				<td><input type="reset" name="Clear"/></td>
				<td><input type="submit" name="Post"/></td>
				</tr>	
			</table>
		</form>
	</c:if>
	
	<c:if test="${param.postid!=null}">
		<h4>Edit Post</h4>
		<br>
		
		<!-- FETCH THE POSTS ADDED BY CURRENT LOGGGED IN USER -->
		<sql:setDataSource driver="com.mysql.cj.jdbc.Driver" 
						   url="jdbc:mysql://localhost:3306/bioshare"
						   user="biosharer"
						   password="biosharer"
						   var="conn"/>
						   
		<sql:query var="resultset" dataSource="${conn}"> 
			select * from posts where postid = ?
			<sql:param>${param.postid}</sql:param>				
		</sql:query>
		
		
		<c:forEach items="${resultset.rows}" var="row">
			<form action="UpdatePostServlet" method="post">
				<table>
					<tr>
					<td>Title *</td>
					<td>
					<input type="text" name="postid" value="${row.postid}" hidden="true"/>
					<input type="text" name="title" value="${row.title}"/></td>
					</tr>		
					
					<tr>
					<td>Content *</td>
					<td><input type="text" name="content" value="${row.content}"/></td>
					</tr>	
					
					<tr>
					<td>Hash Tag *</td>
					<td><input type="text" name="hashtag" value="${row.hashtag}"/></td>
					</tr>		
					
					<tr>
					<td><input type="reset" name="Clear"/></td>
					<td><input type="submit" name="Update"/></td>
					</tr>	
				</table>
		</form>
		</c:forEach>
	</c:if>
	
	
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>