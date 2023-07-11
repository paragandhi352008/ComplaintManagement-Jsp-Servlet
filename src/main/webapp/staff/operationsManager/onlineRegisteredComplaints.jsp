<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<%@ page import="java.util.List"%>
<%@ page import="com.dao.StaffDao"%>
<%@ page import="com.entity.Staff"%>
<%@ page import="com.dao.ZoneDao"%>
<%@ page import="com.entity.Zone"%>
<%@ page import="com.dao.PinCodeDao"%>
<%@ page import="com.entity.PinCode"%>
<%@ page import="com.dao.ComplaintDao"%>
<%@ page import="com.entity.Complaint"%>
<%@ page import="com.dao.ProblemDao"%>
<%@ page import="com.entity.Problem"%>
<%@ page import="com.dao.SubProblemDao"%>
<%@ page import="com.entity.SubProblem"%>
<%@ page import="com.dao.StatusDao"%>
<%@ page import="com.entity.Status"%>
<%@ page import="com.dao.CitizenDao"%>
<%@ page import="com.entity.Citizen"%>
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

.input-group-append {
	cursor: pointer;
}
</style>
</head>
<body>
	<%
	Staff staffRole = (Staff) session.getAttribute("staffObj");
	int roleId = staffRole.getRoleId();
	%>

	<%@ include file="navbar.jsp"%>


	<div class="container p-5">
		<div class="row">
			<div class="col-md-6 offset-md-3">
				<div class="card paint-card">
					<div class="card-body">
						<p class="fs-4 text-center">Online Registered Complaints</p>
						<br>
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

						<form action="onlineRegisteredComplaints.jsp" name="select_problem"
							method="post" onsubmit="return validate()">
							<%
							String fromDate = request.getParameter("from_date");
							String toDate = request.getParameter("to_date");
							String statusId = request.getParameter("status_id");
							%>

							<div class="row">

								<div class="col-md-6">
									<label class="form-label">From Date</label>
									<%
									if (fromDate == null) {
									%>
									<input type="date" name="from_date" class="form-control"
										value="">
									<%
									} else {
									%>
									<input type="date" name="from_date" class="form-control"
										value="<%=fromDate%>">
									<%
									}
									%>
								</div>
								<div class="col-md-6">
									<label class="form-label">To Date</label>
									<%
									if (toDate == null) {
									%>
									<input type="date" name="to_date" class="form-control" value="">
									<%
									} else {
									%>
									<input type="date" name="to_date" class="form-control"
										value="<%=toDate%>">
									<%
									}
									%>
								</div>


							</div>


							<br>
							<div class="text-center mt-8">

								<button id="sumbimt-btn" type="submit"
									class="btn bg-success text-white text-center col-md-6">Generate
									Report</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="container p-7">
		<div class="row">
			<div class="col-md-12">
				<div class="card paint-card">
					<div class="card-body">


						<table class="table table-hover" id="table">
							<thead>
								<tr>
									<th scope="col">Complaint No</th>
									<th scope="col">Citizen</th>
									<th scope="col">Problem</th>
									<th scope="col">Subproblem</th>
									<th scope="col">Date</th>
									<th scope="col">Status</th>
								</tr>
							</thead>
							<tbody>

								<%
								ComplaintDao cdao = new ComplaintDao(DbConnect.getCon());
								List<Complaint> complaint = cdao.getAllComplaintsCreatedByCitizen(fromDate, toDate);
								for (Complaint c : complaint) {
								%>
								<tr>
									<td><%=c.getComplaintNo()%></td>

									<%
									CitizenDao cidao = new CitizenDao(DbConnect.getCon());
									Citizen citizen = cidao.getCitizenById(c.getCitizenId());
									%>
									<td><%=citizen.getFirstName() + ' ' + citizen.getLastName()%></td>


									<%
									ProblemDao dao1 = new ProblemDao(DbConnect.getCon());
									Problem problem = dao1.getAllProblemById(c.getProblemId());
									%>
									<td><%=problem.getType()%></td>

									<%
									SubProblemDao spdao = new SubProblemDao(DbConnect.getCon());
									SubProblem subProblem = spdao.getAllSubProblemById(c.getSubProblemId());
									%>
									<td><%=subProblem.getType()%></td>

									<td><%=c.getDate()%></td>

									<%
									StatusDao dao2 = new StatusDao(DbConnect.getCon());
									Status status = dao2.getStatusById(c.getStatusId());
									%>
									<td><%=status.getName()%></td>
									<%
									}
									%>
								
							</tbody>
						</table>

					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script>
	$(document).ready(function() {
		$('#table').dataTable();
	});

	$('.input-group.date').datepicker({
		format : "dd.mm.yyyy"
	});
</script>
</html>

