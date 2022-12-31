<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<%@ page import="com.dao.DashboardDao"%>
<%@ page import="com.entity.Staff"%>
<%@ page import="com.db.DbConnect"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>CCMS</title>
<%@ include file="../../component/allCss.jsp"%>
<style type="text/css">
.paint-card {
	box-shadow: 0 0 10px 0 rgba(0, 0, 0, 0.3);
}
</style>
</head>
<body>
	<%@ include file="navbar.jsp"%>
	<div class="container p-5">
		<p class="text-center fs-4">Operations Manager Dashboard</p>
		<c:if test="${empty staffObj}">
			<c:redirect url="../staffLogin.jsp"></c:redirect>
		</c:if>
		<c:if test="${not empty sucMsg}">
			<p class="text-center text-success fs-4">${sucMsg}</p>
			<c:remove var="sucMsg" scope="session" />
		</c:if>

		<c:if test="${not empty errorMsg}">
			<p class="text-center text-danger fs-4">${errorMsg}</p>
			<c:remove var="errorMsg" scope="session" />
		</c:if>
		<br>
		<%
		Staff staff = (Staff) session.getAttribute("staffObj");
		DashboardDao dao = new DashboardDao(DbConnect.getCon());
		%>


		<div class="row">
			<div class="col-md-5 offset-1">
				<div class="card paint-card">
					<div class="card-body text-center text-success">
						<i class="fas fa-user-md fa-3x"></i><br>
						<p class="fs-3 text-center">
							Today's Complaints<br><%= dao.countTodaysComplaints() %>
						</p>
					</div>
				</div>
			</div>

			<div class="col-md-5">
				<div class="card paint-card">
					<div class="card-body text-center text-success">
						<i class="fas fa-user-md fa-3x"></i><br>
						<p class="fs-3 text-center">
							Last 7 days Complaints<br><%= dao.countLast7DaysComplaints() %>
						</p>
					</div>
				</div>
			</div>
			<div>
				<br>
			</div>

			<div class="col-md-5 offset-1">
				<div class="card paint-card">
					<div class="card-body text-center text-success">
						<i class="fas fa-user-md fa-3x"></i><br>
						<p class="fs-3 text-center">
							Last 30 days Complaints<br><%=dao.countLast30DaysComplaints() %>
						</p>
					</div>
				</div>
			</div>

			<div class="col-md-5">
				<div class="card paint-card">
					<div class="card-body text-center text-success">
						<i class="fas fa-user-md fa-3x"></i><br>
						<p class="fs-3 text-center">
							Total Complaints<br><%=dao.countTotalComplaints()%>
						</p>
					</div>
				</div>
			</div>

		</div>

	</div>
</body>
</html>