<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<style>
#topheader .navbar-nav li>a {
	text-transform: capitalize;
	color: #FFFFFF;
	transition: background-color .2s, color .2s; &: hover , & : focus { 
		 background-color : #333;
	color: #fff;
}

}
#topheader .navbar-nav li.active>a {
	font-weight: bolder;
	font-style: italic;
	color: #fff;
}
</style>
<html>
<nav class="navbar navbar-expand-lg navbar-dark bg-success"
	id="topheader">
	<div class="container-fluid">
		<a class="navbar-brand" href="index.jsp"><img
			src="../img/icon.png" alt="" width="30" height="24"
			class="d-inline-block align-text-top"> CCMS</a>
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
			data-bs-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<c:if test="${not empty citizenObj}">

			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav me-auto mb-2 mb-lg-0">


					<li class="nav-item"><a class="nav-link active"
						aria-current="page" href="index.jsp">Home</a></li>
					<li class="nav-item"><a class="nav-link"
						href="registerComplaint.jsp">Register Complaint</a></li>
					<li class="nav-item"><a class="nav-link" href="status.jsp">Status</a></li>
					<li class="nav-item"><a class="nav-link"
						href="reopenComplaint.jsp">Re-open</a></li>
					<li class="nav-item"><a class="nav-link" href="feedback.jsp">Feedback</a></li>
				</ul>

			</div>

			<div class="dropdown">
				<button class="btn btn-success dropdown-toggle" type="button"
					id="dropdownMenuButton1" data-bs-toggle="dropdown"
					aria-expanded="false">
					<i class="fa-solid fa-circle-user"></i> ${citizenObj.firstName}
					${citizenObj.lastName}
				</button>
				<ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
					<li><a class="dropdown-item" href="myProfile.jsp">My
							Profile</a></li>
					<li><a class="dropdown-item" href="resetPassword.jsp">Change
							Password</a></li>
					<li><a class="dropdown-item" href="../citizenLogout">Logout</a></li>
				</ul>
			</div>
		</c:if>
	</div>
</nav>

<script>
	$('#topheader .navbar-nav a').on('click', function() {
		$('#topheader .navbar-nav').find('li.active').removeClass('active');
		$(this).parent('li').addClass('active');
	});
</script>
</html>
