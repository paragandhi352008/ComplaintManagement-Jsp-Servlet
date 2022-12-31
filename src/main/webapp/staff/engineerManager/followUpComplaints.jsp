<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<%@ page import="java.util.List"%>
<%@ page import="com.dao.RoleDao"%>
<%@ page import="com.entity.Role"%>
<%@ page import="com.dao.ProblemDao"%>
<%@ page import="com.dao.SubProblemDao"%>
<%@ page import="com.entity.Problem"%>
<%@ page import="com.dao.StaffDao"%>
<%@ page import="com.entity.Staff"%>
<%@ page import="com.entity.SubProblem"%>
<%@ page import="com.entity.Citizen"%>
<%@ page import="com.dao.ComplaintDao"%>
<%@ page import="com.entity.Complaint"%>
<%@ page import="com.dao.StatusDao"%>
<%@ page import="com.entity.Status"%>
<%@ page import="com.db.DbConnect"%>
<%@ page import="java.util.List"%>
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
		<p class="text-center fs-4">Follow-up Complaints</p>
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
								<th scope="col">Complaint Number</th>
								<th scope="col">Engineer</th>
								<th scope="col">Problem</th>
								<th scope="col">Subproblem</th>
								<th scope="col">Created Date</th>
							</tr>
						</thead>
						<tbody>

							<%
							Staff staff = (Staff) session.getAttribute("staffObj");
							ComplaintDao dao = new ComplaintDao(DbConnect.getCon());
							List<Complaint> list = dao.getAllFollowUpComplaints();

							for (Complaint c : list) {
							%>
							<tr>
								<td><%=c.getComplaintNo()%></td>
								
							<%
								StaffDao sdao = new StaffDao(DbConnect.getCon());
								Staff assignTo = sdao.getEngineerById(c.getUserId());
								%>
								<td><%=assignTo.getFirstName() + ' ' + assignTo.getLastName()%></td>
							

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