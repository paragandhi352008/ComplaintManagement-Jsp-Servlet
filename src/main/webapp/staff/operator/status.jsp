<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<%@ page import="java.util.List"%>
<%@ page import="com.dao.CitizenDao"%>
<%@ page import="com.entity.Citizen"%>
<%@ page import="com.dao.ComplaintDao"%>
<%@ page import="com.entity.Complaint"%>
<%@ page import="com.dao.EngineerMappingDao"%>
<%@ page import="com.entity.EngineerMapping"%>
<%@ page import="com.dao.StatusDao"%>
<%@ page import="com.entity.Status"%>
<%@ page import="com.dao.ProblemDao"%>
<%@ page import="com.entity.Problem"%>
<%@ page import="com.dao.StaffDao"%>
<%@ page import="com.entity.Staff"%>
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
	<div class="container p-5">
		<p class="text-center fs-3">Complaint Status</p>
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

		<div class="col-md-12">
			<div class="card paint-card">
				<div class="card-body">


					<table class="table table-hover" id="table">
						<thead>
							<tr>
								<th scope="col">Number</th>
								<th scope="col">Citizen</th>
								<th scope="col">Problem</th>
								<th scope="col">Assign To</th>
								<th scope="col">Date</th>
								<th scope="col">Status</th>
								<th scope="col">Action</th>
							</tr>
						</thead>
						<tbody>

							<%
							Staff staff = (Staff) session.getAttribute("staffObj");
							ComplaintDao dao = new ComplaintDao(DbConnect.getCon());
							List<Complaint> list = dao.getAllComplaintsCreatedByStaffId(staff.getUserId());
							for (Complaint c : list) {
							%>
							<tr>
								<td><%=c.getComplaintNo()%></td>

								<%
								CitizenDao cdao = new CitizenDao(DbConnect.getCon());
								Citizen citizen = cdao.getCitizenById(c.getCitizenId());
								%>
								<td><%=citizen.getFirstName() + ' ' + citizen.getLastName()%></td>

								<%
								ProblemDao dao1 = new ProblemDao(DbConnect.getCon());
								Problem problem = dao1.getAllProblemById(c.getProblemId());
								%>
								<td><%=problem.getType()%></td>

								<%
								EngineerMappingDao emdao = new EngineerMappingDao(DbConnect.getCon());
								EngineerMapping engineerMapping = emdao.getEngineerByProblemIdAndZoneId(c.getProblemId(), c.getZoneId());
								StaffDao sdao = new StaffDao(DbConnect.getCon());
								Staff engineer = sdao.getEngineerById(engineerMapping.getUserId());
								%>
								<td><%=engineer.getFirstName() + ' ' + engineer.getLastName()%></td>

								<td><%=c.getDate()%></td>
								<%
								StatusDao dao2 = new StatusDao(DbConnect.getCon());
								Status status = dao2.getStatusById(c.getStatusId());
								%>
								<td><%=status.getName()%></td>
								<td><a
									href="viewComplaint.jsp?complaintId=<%=c.getComplaintId()%>"
									class="btn btn-success btn-sm">View</a> <a
									href="../../deleteComplaint?complaintId=<%=c.getComplaintId()%>"
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

