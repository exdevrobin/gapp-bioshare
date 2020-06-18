<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit Post</title>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	
	<!-- FETCH THE POSTS ADDED BY CURRENT LOGGGED IN USER -->
	<sql:setDataSource driver="com.mysql.cj.jdbc.Driver" 
					   url="jdbc:mysql://localhost:3306/bioshare"
					   user="biosharer"
					   password="biosharer"
					   var="conn"/>
					   
	<sql:query var="resultset" dataSource="${conn}"> 
		select * from posts where postid = ?
		limit 1
		<sql:param>${param.postid}</sql:param>				
	</sql:query>
		
	<%--Add New Post--%>
	<c:forEach items="${resultset.rows}" var="row">
			<div class="m-2 p-5 btn-card">
				<div class="form-group">
					<form action="UpdatePostServlet" method="post">
						<input type="text" name="postid" value="${row.postid}" hidden="true"/>
						<label>Title</label>
						<input type="text" class="form-control" name="title" value="${row.title}"/>
						<br>
						<label>Content</label>
						<textarea class="form-control" name="content" rows="5" >${row.content}</textarea>
						<br>
						<label>Hash Tag</label>
						<input type="text" class="form-control" name="hashtag" value="${row.hashtag}"/>
						<br>
						<input type="reset" class="btn btn-dark" style="width:49%;" name="Clear" value="Update"/>
						<input type="submit" class="btn btn-dark" style="width:49%;" name="Update" value="Update"/>
					</form>
				</div>
			</div>
	</c:forEach>		
	
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>