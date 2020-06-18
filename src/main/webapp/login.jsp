<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
</head>
<body>
	<% 
		session.removeAttribute("uid");
		session.removeAttribute("uname");
		session.removeAttribute("otp");
		session.removeAttribute("otpemail");
		session.invalidate();
	%>

	<jsp:include page="header.jsp"></jsp:include>

	<div class="mx-5 px-5">
		<br>		
		<div class="text-center" style="color:rgb(67, 73, 113);"><h3><b>Login</b></h3></div>
		<br>
		<div class="form-group">
			<form action="LoginServlet" method="post">
					<label>Email</label>
					<input type="text" class="form-control" name="email"/>
					<br>
					<label>Password</label>
					<input type="password" class="form-control" name="password"/>
					<br>
					<input type="reset" class="btn btn-dark" style="width:49%;" name="Reset" value="Clear"/>
					<input type="submit" class="btn btn-dark" style="width:49%;" name="Login" value="Login"/>
			</form>
		</div>
	</div>
	
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>