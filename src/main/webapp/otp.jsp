<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Confirm OTP</title>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>

	<div class="mx-5 px-5">
		<br>		
		<div class="text-center" style="color:rgb(67, 73, 113);"><h3><b>OTP Confirmation</b></h3></div>
		<br>
		<div class="form-group">
			<form action="OTPConfirmationServlet" method="post">
					<label>Enter OTP Code</label>
					<input type="text" class="form-control" name="userotp"/>
					<br>
					<input type="submit" class="btn btn-dark" style="width:100%;" name="Login" value="Continue"/>
			</form>
		</div>
	</div>
	
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>