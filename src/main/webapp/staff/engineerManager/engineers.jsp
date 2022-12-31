<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.List"%>
<%@ page import="com.dao.StaffDao"%>
<%@ page import="com.entity.Staff"%>
<%@ page import="com.dao.ZoneDao"%>
<%@ page import="com.entity.Zone"%>
<%@ page import="com.db.DbConnect"%>
<!DOCTYPE html>
<html>
<head>

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
	<div class="container p-3">
		<p class="text-center fs-3">Engineers</p>
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
		<div>
			<a href="addEngineer.jsp" class="btn btn-success">Add Engineer</a> <br>
			<br>
		</div>
		<div class="col-md-12">
			<div class="card paint-card">
				<div class="card-body">
					<table class="table table-hover" id="table">
						<thead>
							<tr>
								<th scope="col">First Name</th>
								<th scope="col">Last Name</th>
								<th scope="col">Mobile</th>
								<th scope="col">Zone</th>
								<th scope="col">Action</th>
							</tr>
						</thead>
						<tbody>
							<%
							StaffDao dao = new StaffDao(DbConnect.getCon());
							List<Staff> list = dao.getAllEngineersWithZone();
							for (Staff s : list) {
							%>
							<tr>
								<td><%=s.getFirstName()%></td>
								<td><%=s.getLastName()%></td>
								<td><%=s.getMobile()%></td>
								<%
								ZoneDao zdao = new ZoneDao(DbConnect.getCon());
								Zone zone = zdao.getZoneByPinCode(s.getPinCode());
								%>
								<td><%=zone.getName()%></td>
								<td><a href="editStaff.jsp?userId=<%=s.getUserId()%>"
									class="btn btn-success btn-sm">Edit</a> <a
									href="../../deleteStaff?userId=<%=s.getUserId()%>"
									class="btn btn-sm btn-danger"
									onclick="return confirm('Are you sure you want to delete?')">Delete</a></td>
							</tr>
							<%
							}
							%>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</body>
<script>
	$(document).ready(function() {
		$('#table').dataTable();
	});
</script>
</html>

