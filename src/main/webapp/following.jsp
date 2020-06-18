<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Bio.Share - Friends List</title>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<br>		
	<div class="text-center" style="color:rgb(67, 73, 113);"><h3><b>Following</b></h3></div>
	<br>
	<!-- FETCH THE USERS FOLLOWED BY CURRENT LOGGGED IN USER -->
	<sql:setDataSource driver="com.mysql.cj.jdbc.Driver" 
					   url="jdbc:mysql://localhost:3306/bioshare"
					   user="biosharer"
					   password="biosharer"
					   var="conn"/>
					   
	<sql:query var="userlist" dataSource="${conn}"> 
		select * from users
		where userid in (select followingid from following where userid=?)
		<sql:param>${uid}</sql:param>
	</sql:query>
	
	<div class="align-center">
		<div class="container-fluid">
			<div class="row">
				<c:forEach items="${userlist.rows}" var="userrecord">
					<div class="text-center col-lg-6 col-md-6 col-sm-6 col-xs-6 p-1">
						 <div class="m-1 p-1 btn-card">
							<br>
							<a href="profile.jsp?usrid=${userrecord.userid}">
								<img src="UserImageServlet?userid=${userrecord.userid}" 
									 height="200px" 
									 width="200px" 
									 style="border-radius: 50%;"/>	 
							</a>
							<div class="h5"><b>${userrecord.username}</b></div>
							<div class="h6">${userrecord.email}</div>
							<a href="UnfollowServlet?followid=${userrecord.userid}" class="btn btn-dark">UnFollow</a>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
	
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>