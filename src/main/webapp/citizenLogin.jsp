<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>CCMS</title>
<%@ include file="component/allCss.jsp"%>
<style type="text/css">
.paint-card {
	box-shadow: 0 0 10px 0 rgba(0, 0, 0, 0.3);
}
</style>
</head>
<body>
	<%@ include file="component/navbar.jsp"%>
	<div class="container p-5">
		<div class="row">
			<div class="col-md-6 offset-md-3">
				<div class="card paint-card">
					<div class="card-body">
						<p class="fs-4 text-center">
							<span class="fa fa-3x fa-user-circle"></span> <br>Citizen
							Login
						</p>
						<c:if test="${not empty sucMsg}">
							<p class="text-center text-success fs-4">${sucMsg}</p>
							<c:remove var="sucMsg" scope="session" />
						</c:if>

						<c:if test="${not empty errorMsg}">
							<p class="text-center text-danger fs-4">${errorMsg}</p>
							<c:remove var="errorMsg" scope="session" />
						</c:if>
						<form action="citizenLogin" method="post"
							onsubmit="return validate()">


							<div class="mb-3">
								<label class="form-label">User Name</label> <input
									name="citizen_user_name" type="text" class="form-control"
									id="user_name" required aria-describedby="emailHelp"
									placeholder="Enter User name">
							</div>

							<div class="mb-3">
								<label class="form-label">Password</label> <input
									name="citizen_password" type="password" class="form-control"
									id="password" required placeholder="Enter Password">
							</div>
							
							<span><a href="citizen/forgotPassword.jsp">Forgot Password?</a></span>

							<br><br>

							<button id="sumbimt-btn" type="submit"
								class="btn bg-success text-white col-md-12">Submit</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script>
		function validate() {
			var username = document.citizen_register.citizen_user_name.value;
			var password = document.citizen_register.citizen_password.value;
			if (username.length >= 15) {
				alert("Username length must be less than 15")
				return false;
			}
			if (password.length >= 15) {
				alert("Password length must be less than 15")
				return false;
			}
			return true;
		}
	</script>
</body>
</html>