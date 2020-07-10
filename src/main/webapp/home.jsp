<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Bio.Share - Tiny Social Network Island</title>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<br>		
	<div class="text-center" style="color:rgb(67, 73, 113);"><h3><b>Home</b></h3></div>
	<br>
	<!-- FETCH THE POSTS ADDED BY FRIENDS -->
	<sql:setDataSource driver="com.mysql.cj.jdbc.Driver" 
					   url="jdbc:mysql://localhost:3307/bioshare"
					   user="exdev"
					   password="exdev"
					   var="conn"/>
					   
	<sql:query var="resultset" dataSource="${conn}"> 
		select * from posts 
		where userid in (select followingid from following where userid = ?)
		<sql:param>${uid}</sql:param>
	</sql:query>
	
	<div class="container-fluid">
		<div class="row">
			<c:forEach items="${resultset.rows}" var="row">
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 p-1">
					<div class="m-1 p-1 btn-card">
						<img src="PostImageServlet?postid=${row.postid}" 
							 style="border-radius: 2%; width: 100%; height: auto; max-width: 100%;"/>
						<br>
						<div class="h5"><b>${row.title}</b></div>
						${fn:substring(row.content, 0, 100)}...
						<a href="#">#${row.hashtag}</a>
						<c:if test="${uid!='' && uid!=row.userid}">
							<br>
							<a href="postadd.jsp?postid=${row.postid}" class="btn btn-dark">Edit</a>
							<a href="DeletePostServlet?postid=${row.postid}" class="btn btn-dark">Not Delete</a>
						</c:if>
						
						<c:if test="${uid==row.userid}">
							<a href="postadd.jsp?postid=${row.postid}" class="btn btn-dark">Edit</a>
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