<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.dao.RoleDao"%>
<%@ page import="com.entity.Role"%>
<%@ page import="com.db.DbConnect"%>
<%@ page import="java.util.List"%>
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
							<span class="fa fa-3x fa-user-circle"></span> <br>Contact Us
						</p>
						<c:if test="${not empty sucMsg}">
							<p class="text-center text-success fs-4">${sucMsg}</p>
							<c:remove var="sucMsg" scope="session" />
						</c:if>

						<c:if test="${not empty errorMsg}">
							<p class="text-center text-danger fs-4">${errorMsg}</p>
							<c:remove var="errorMsg" scope="session" />
						</c:if>


						<div class="card">

							<div class="card-body">

								<h5 class="card-title">Comprehensive Complaint Management
									System</h5>
								<br>
								<h6 class="card-title">Address:</h6>
								<p class="card-text">
									Gujarat Government Narmada Bhavan <br>3rd Floor, C-Block,
									Jail Rd <br>Anandpura, Vadodara <br>Gujarat, 390001
								</p>

								<br>
								<h6 class="card-title">Email ID:</h6>
								<p class="card-text">admin@ccms.com</p>

							</div>
						</div>
					</div>

					<br>

				</div>
			</div>
		</div>
	</div>
</body>
</html>