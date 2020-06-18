<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Bio.Share - Login</title>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>

	<div class="mx-5 px-5">
		<div class="text-center" style="color:white;"><h3><b>Change Password</b></h3></div>
		<br>
		<div class="form-group">
			<form action="ChangePasswordServlet" method="post">
					<label>Old Password</label>
					<input type="password" class="form-control" name="oldpassword"/>
					<br>
					<label>New Password</label>
					<input type="password" class="form-control" name="newpassword"/>
					<br>
					<label>Retype New Password</label>
					<input type="password" class="form-control" name="retypenewpassword"/>
					<br>
					<input type="reset" class="btn btn-dark" style="width:49%;" name="Reset" value="Clear"/>
					<input type="submit" class="btn btn-dark" style="width:49%;" name="Login" value="Change"/>
			</form>
		</div>
	</div>
	
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>