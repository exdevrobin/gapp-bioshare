<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register</title>
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
	<div class="text-center" style="color:rgb(67, 73, 113);"><h3><b>Registration</b></h3></div>
	<br>
		<div class="form-group">
			<form action="RegistrationServlet" method="post" enctype="multipart/form-data">
				<label>Name</label>
				<input type="text" class="form-control" name="username"/>
				<br>
				<label>Email</label>
				<input type="text" class="form-control" name="email"/>
				<br>
				<label>Password</label>
				<input type="password" class="form-control" name="password"/>
				<br>
				<label>Photo</label>
				<input type="file" class="form-control" name="photo" accept="image/*"/>
				<br>
				<input type="reset" class="btn btn-dark" style="width:49%;" value="Clear"/>
				<input type="submit" class="btn btn-dark" style="width:49%;" value="Register"/>
			</form>
		</div>
	</div>
	
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
