<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	
	<link rel="stylesheet" href="style.css"/>
	<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous"/>
	<link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
	
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
  	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  	<script>
	  	$(document).ready(function(){
	  	  $('[data-toggle="tooltip"]').tooltip();
  		});
	  	
	  	$(document).ready(function(){
	  	  $("#newpost").click(function(){
	  	    $("#newpostarea").slideToggle();
	  	  });
	  	});
  	</script>
	<style>
		.layout-sidebars
		{
			background:black;
		}
		.bio-nav-color
		{
			background-color: rgb(35, 35, 35);
		}
	
		body
		{
			font-family: sans-serif;
			height:100%;
			background-color: rgb(219, 215, 244);
		}
	
		.headerarea
		{
			background-color: rgb(67, 73, 113);
			width:100%;
		}
	
		.btn-dark
		{
			background-color: rgb(67, 73, 113);
			color: rgb(255, 255, 255);
			border-radius: 5%;
			font-weight:bold;
		}
	
		.btn-dark:hover,
		.btn-dark:active
		{
			background-color: rgb(67, 73, 113);
			color: rgb(219, 215, 244);
			border-color: rgb(219, 215, 244);
		}
	
		.form-control
		{
			font-size: large;
			font-family: sans-serif;
			border-color: rgb(67, 73, 113);
		}
	
		.no-pad-y
		{
			padding-left: 0;
			padding-right:0;
		}
	
		.pos-relative
		{
			position:relative;
			top:0px;
		}
		
		.btn-card
		{
			border-radius: 10px;
			border-color: rgb(67, 73, 113);
		}
		
		.btn-round
		{
			color: rgb(67, 73, 113);
			background-color: rgb(219, 215, 244);
			border-radius: 50%;
		}
		
		.btn-round:hover,
		.btn-round:active
		{
			background-color: rgb(67, 73, 113);
			color: rgb(219, 215, 244);
			border-color: rgb(219, 215, 244);
		}
	</style>
</head>
<body>
<div class = "container-fluid no-pad-y">
	<div class = "row no-gutters">
		<%-- LEFT AD BAR --%>
		<div class = "col-lg-4 col-md-4 col-sm-0 col-xs-0 layout-sidebars"></div>
		
		<%-- MIDDLE CONTENT BAR --%>
		<div class = "col-lg-4 col-md-4 col-sm-12 col-xs-12">
			<div class="headerarea">
				<nav class="navbar navbar-nav mx-auto">
					<!-- Header Links -->
						<div class="d-flex">
							<div class="p-1"><a href="home.jsp?usrid=${uid}" class="btn btn-round fa fa-home fa-2x p-3" data-toggle="tooltip" data-placement="left" title="Home"></a></div>
							
							<c:if test="${uid==null}"> <%-- IF USER IS NOT LOGGED IN --%>								
								<div class="p-1"><a href="login.jsp" class="btn btn-round fa fa-sign-in fa-2x p-3" data-toggle="tooltip" data-placement="bottom" title="Login"></a></div>
								<div class="p-1"><a href="register.jsp" class="btn btn-round fa fa-user-plus fa-2x p-3" data-toggle="tooltip" data-placement="bottom" title="Register"></a></div>
							</c:if>
							
							<c:if test="${uid!=null}">
								<div class="p-1"><a href="profile.jsp?usrid=${uid}" class="btn btn-round fa fa-user fa-2x p-3" data-toggle="tooltip" data-placement="bottom" title="Profile"></a></div>
								<div class="p-1"><a href="following.jsp" class="btn btn-round fa fa-user-secret fa-2x p-3" data-toggle="tooltip" data-placement="bottom" title="Following"></a></div>
								<div class="p-1"><a href="findusers.jsp" class="btn btn-round fa fa-search fa-2x p-3" data-toggle="tooltip" data-placement="bottom" title="Search Users"></a></div>
								<div class="p-1"><a href="login.jsp" class="btn btn-round fa fa-sign-out fa-2x p-3" data-toggle="tooltip" data-placement="right" title="Logout" style="color:red;"></a></div>
							</c:if>
						</div>
					</nav>
				</div>
		<%--ALL BOOTSTRAP TAGS ENDS IN FOOTER --%>
</body>
</html>