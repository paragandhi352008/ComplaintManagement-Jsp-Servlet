<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
		<p class="text-center fs-4">Engineer Dashboard</p>
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
							Pending Complaints<br><%=dao.countPendingComplaintsByStaffId(staff.getUserId())%>
						</p>
					</div>
				</div>
			</div>

			<div class="col-md-5">
				<div class="card paint-card">
					<div class="card-body text-center text-success">
						<i class="fas fa-user-md fa-3x"></i><br>
						<p class="fs-3 text-center">
							In-Progress Complaints<br><%=dao.countInProgressComplaintsByStaffId(staff.getUserId())%>
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
							Follow up Complaints<br><%=dao.countFollowUpComplaintsByStaffId(staff.getUserId())%>
						</p>
					</div>
				</div>
			</div>
			
				<div class="col-md-5">
				<div class="card paint-card">
					<div class="card-body text-center text-success">
						<i class="fas fa-user-md fa-3x"></i><br>
						<p class="fs-3 text-center">
							Reopen Complaints<br><%=dao.countReopenComplaintsByStaffId(staff.getUserId())%>
						</p>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>