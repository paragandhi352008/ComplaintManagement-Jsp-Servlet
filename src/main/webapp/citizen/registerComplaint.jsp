<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.List"%>
<%@ page import="com.dao.RoleDao"%>
<%@ page import="com.entity.Role"%>
<%@ page import="com.dao.ProblemDao"%>
<%@ page import="com.entity.Problem"%>
<%@ page import="com.entity.Citizen"%>
<%@ page import="com.dao.SubProblemDao"%>
<%@ page import="com.entity.SubProblem"%>
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
<%@ include file="navbar.jsp"%>
<div class="container p-5">
	<div class="row">
		<div class="col-md-6 offset-md-3">
			<div class="card paint-card">
				<div class="card-body">
					<p class="fs-4 text-center">
						<span class="fa fa-3x fa-user-circle"></span> <br>Register
						Complaint
					</p>
					<c:if test="${not empty sucMsg}">
						<p class="text-center text-success fs-4">${sucMsg}</p>
						<c:remove var="sucMsg" scope="session" />
					</c:if>

					<c:if test="${not empty errorMsg}">
						<p class="text-center text-danger fs-4">${errorMsg}</p>
						<c:remove var="errorMsg" scope="session" />
					</c:if>

					<c:if test="${empty problemObj}">

						<form action="../registerComplaint" name="select_problem"
							method="post" onsubmit="return validate()">

							<div class="mb-3">
								<label for="problem_type">Problem Type</label> <select
									name="problem_id" class="form-select">
									<option>--select--</option>
									<%
									ProblemDao dao1 = new ProblemDao(DbConnect.getCon());
									List<Problem> list1 = dao1.getAllProblemWithSubProblemAndMapped();
									for (Problem p1 : list1) {
									%>
									<option value="<%=p1.getProblemId()%>"><%=p1.getType()%></option>
									<%
									}
									%>
								</select>
							</div>
							<div class="mb-3">
								<button id="sumbimt-btn" type="submit"
									class="btn bg-success text-white col-me-5">Add</button>
							</div>
						</form>
					</c:if>

					<c:if test="${not empty problemObj}">

						<form action="../addComplaintByCitizen" name="select_problem"
							method="post" onsubmit="return validate()">
							<%
							Citizen c = (Citizen) session.getAttribute("citizenObj");
							%>

							<input type="hidden" name="citizen_id" value="<%=c.getId()%>">

							<div class="mb-3">
								<label for="problem_type">Problem Type</label> <select
									name="problem_id2" class="form-select" disabled>
									<%
									Problem p = (Problem) session.getAttribute("problemObj");
									ProblemDao dao2 = new ProblemDao(DbConnect.getCon());
									%>
									<option value="<%=p.getProblemId()%>"><%=p.getType()%></option>
								</select> <input type="hidden" name="problem_id1"
									value="<%=p.getProblemId()%>">
							</div>

							<div class="mb-3">
								<button id="sumbimt-btn" type="submit"
									class="btn bg-success text-white col-me-5" disabled>Add</button>
							</div>

							<div class="mb-3">
								<label for="problem_type">Subproblem Type</label> <select
									name="subproblem_id" class="form-select">
									<option selected disabled value="">--select--</option>
									<%
									SubProblemDao dao3 = new SubProblemDao(DbConnect.getCon());
									List<SubProblem> list = dao3.getAllSubProblemByProblemId(p.getProblemId());
									for (SubProblem sp : list) {
									%>
									<option value="<%=sp.getSubproblemId()%>"><%=sp.getType()%></option>
									<%
									}
									%>
								</select>
							</div>

							<div class="mb-3">
								<label class="form-label">Notes</label>
								<textarea name="complaint_notes" class="form-control"
									id="address" rows="3" required
									placeholder="Enter additional information regarding problem."></textarea>
							</div>

							<button id="sumbimt-btn" type="submit"
								class="btn bg-success text-white col-md-6">Register</button>
						</form>
						<c:remove var="problemObj" scope="session" />
					</c:if>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>