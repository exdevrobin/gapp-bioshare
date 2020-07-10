<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Profile</title>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<br>		
	<div class="text-center" style="color:rgb(67, 73, 113);"><h3><b>Profile</b></h3></div>
	<br>
	<!-- FETCH THE POSTS ADDED BY CURRENT LOGGGED IN USER -->
	<sql:setDataSource driver="com.mysql.cj.jdbc.Driver" 
					   url="jdbc:mysql://localhost:3307/bioshare"
					   user="exdev"
					   password="exdev"
					   var="conn"/>

	<sql:query var="userresultset" dataSource="${conn}"> 
		select * from users where userid = ?
		limit 1
		<sql:param>${param.usrid}</sql:param>				
	</sql:query>

	<%--User Picture, name ,email --%>
	<div class="m-2 p-1 btn-card">
		<div class="text-center">
			<c:forEach items="${userresultset.rows}" var="user">
				<br>
				<img src="UserImageServlet?userid=${user.userid}" 
					 height="300px" 
					 width="300px"
					 style="border-radius: 50%;"/>
				<br><br>
				<div class="text-center h3"><b>${user.username}</b></div>
				<div class="text-center h5"><b>${user.email}</b></div>
				<div class="text-center h6"><b>n Followers</b></div>
			</c:forEach>	
		</div>
	</div>	   	   
	
	<hr>
	
	<%--Add New Post--%>
	<div id="newpost" class="btn btn-primary">Add New Post</div>
	<div id="newpostarea" style="display:none;">
		<c:if test="${uid==param.usrid}">
			<div class="m-2 p-5 btn-card">
				<div class="form-group">
					<form action="AddPostServlet" method="post" enctype="multipart/form-data">
						<label>Title</label>
						<input type="text" class="form-control" name="title"/>
						<br>
						<label>Content</label>
						<textarea class="form-control" name="content" rows="5"></textarea>
						<br>
						<label>Hash Tag</label>
						<input type="text" class="form-control" name="hashtag"/>
						<br>
						<label>Photo</label>
						<input type="file" class="form-control" name="photo" accept="image/*"/>
						<br>
						<input type="reset" class="btn btn-dark" style="width:49%;" name="Clear" value="Clear"/>
						<input type="submit" class="btn btn-dark" style="width:49%;" name="Post" value="Post"/>
					</form>
				</div>
			</div>
			<hr>
		</c:if>
	</div>
	
	<%-- User Posts --%>	   
	<sql:query var="resultset" dataSource="${conn}"> 
		select * from posts where userid = ?
		<sql:param>${param.usrid}</sql:param>				
	</sql:query>
	
	<div class="container-fluid">
		<div class="row">
			<c:forEach items="${resultset.rows}" var="row">
				<hr>
				<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6 p-1">
					<div class="m-1 p-1 btn-card">
						<img src="PostImageServlet?postid=${row.postid}" 
							 style="border-radius: 2%; width: 100%; height: auto; max-width: 100%;"/>
						<br>
						<div class="h6"><b>${row.title}</b></div>
						<div class="">${fn:substring(row.content, 0, 100)} ...</div>
						<div class=""><a href="#">#${row.hashtag}</a></div>
						<c:if test="${uid==param.usrid}">
							<a href="postedit.jsp?postid=${row.postid}" class="btn btn-dark">Edit</a>
							<a href="DeletePostServlet?postid=${row.postid}" class="btn btn-dark">Delete</a>
						</c:if>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
	
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>