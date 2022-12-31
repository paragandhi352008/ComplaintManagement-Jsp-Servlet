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
							<span class="fa fa-3x fa-user-circle"></span> <br>Register
							Via Phone
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

								<h5 class="card-title">CCMS Call Center</h5>
								<br>
								<p class="card-text">Contact our dedicated call center by
									dialing 155303 from landline or mobile between 24*7 to raise
									complaint.</p>
								<p class="card-text">Team will register your complaint and
									give you complaint number for your reference. You can
									follow-up/reopen complaint in future with given complaint
									number.</p>

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