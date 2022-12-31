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
<%@ page import="com.dao.PinCodeDao"%>
<%@ page import="com.entity.PinCode"%>
<%@ page import="com.entity.SubProblem"%>
<%@ page import="com.dao.CitizenDao"%>
<%@ page import="com.entity.Citizen"%>
<%@ page import="com.dao.ComplaintDao"%>
<%@ page import="com.entity.Complaint"%>
<%@ page import="com.dao.EngineerMappingDao"%>
<%@ page import="com.entity.EngineerMapping"%>
<%@ page import="com.dao.StatusDao"%>
<%@ page import="com.entity.Status"%>
<%@ page import="com.dao.ZoneDao"%>
<%@ page import="com.entity.Zone"%>
<%@ page import="com.dao.StaffDao"%>
<%@ page import="com.entity.Staff"%>
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
<%@ include file="navbar.jsp"%>
<div class="container p-5">
	<div class="row">
		<div class="col-md-12">
			<div class="card paint-card">
				<div class="card-body">
					<p class="fs-4 text-center">New Complaints regietered by
						Citizen</p>
					<br>
					<c:if test="${not empty sucMsg}">
						<p class="text-center text-success fs-4">${sucMsg}</p>
						<c:remove var="sucMsg" scope="session" />
					</c:if>

					<c:if test="${not empty errorMsg}">
						<p class="text-center text-danger fs-4">${errorMsg}</p>
						<c:remove var="errorMsg" scope="session" />
					</c:if>

					<%
					int complaintId = Integer.parseInt(request.getParameter("complaintId"));
					ComplaintDao dao = new ComplaintDao(DbConnect.getCon());
					Complaint complaint = dao.getComplaintByComplaintId(complaintId);

					CitizenDao cdao = new CitizenDao(DbConnect.getCon());
					Citizen citizen = cdao.getCitizenById(complaint.getCitizenId());
					ProblemDao pdao = new ProblemDao(DbConnect.getCon());
					Problem problem = pdao.getAllProblemById(complaint.getProblemId());
					SubProblemDao spdao = new SubProblemDao(DbConnect.getCon());
					SubProblem subProblem = spdao.getAllSubProblemById(complaint.getSubProblemId());

					ZoneDao zdao = new ZoneDao(DbConnect.getCon());
					Zone zone = zdao.getZoneByPinCode(citizen.getPinCode());
					EngineerMappingDao emdao = new EngineerMappingDao(DbConnect.getCon());
					EngineerMapping engineerMapping = emdao.getEngineerByProblemIdAndZoneId(complaint.getProblemId(), zone.getZoneId());
					StaffDao sdao = new StaffDao(DbConnect.getCon());
					Staff engineer = sdao.getEngineerById(engineerMapping.getUserId());
					%>



					<form action="../../editComplaintStatus" name="select_problem"
						method="post" onsubmit="return validate()">
						
						<input type="hidden" name="staff_id" value="${staffObj.userId}">
						
						<div class="row">

							<input type="hidden" name="zone_id" value="<%=zone.getZoneId()%>">
							<input type="hidden" name="complaint_id"
								value="<%=complaint.getComplaintId()%>">


							<div class="col-md-6">
								<label class="form-label">Complaint Number</label> <input
									type="text" class="form-control" disabled
									value=<%=complaint.getComplaintNo()%>>
							</div>
							<div class="col-md-6">
								<label class="form-label">Mobile Number</label> <input
									type="text" class="form-control" disabled
									value=<%=citizen.getMobile()%>>
							</div>



							<div class="col-md-6 mt-3">
								<label class="form-label">First Name</label> <input type="text"
									class="form-control" disabled value=<%=citizen.getFirstName()%>>
							</div>
							<div class="col-md-6 mt-3">
								<label class="form-label">Last Name</label> <input type="text"
									class="form-control" disabled value=<%=citizen.getLastName()%>>
							</div>

							<div class="col-md-6 mt-3">
								<label class="form-label">Select Gender</label> <br>
								<%
								if (citizen.getGender().equalsIgnoreCase("Male")) {
								%>
								<input type="radio" id="gender" name="staff_gender" value="Male"
									required checked>Male <input type="radio" id="gender"
									name="staff_gender" value="Female" required>Female
								<%
								} else if (citizen.getGender().equalsIgnoreCase("Female")) {
								%>
								<input type="radio" id="gender" name="staff_gender" value="Male"
									required>Male <input type="radio" id="gender"
									name="staff_gender" value="Female" required checked>Female
								<%
								}
								%>
							</div>

							<div class="col-md-6 mt-3">
								<label class="form-label">Email</label> <input type="text"
									class="form-control" disabled value=<%=citizen.getEmail()%>>
							</div>

							<div class="col-md-6 mt-3">
								<label class="form-label">Address</label>
								<textarea name="citizen_address" class="form-control"
									id="address" rows="3" required disabled><%=citizen.getAddress()%></textarea>
							</div>

							<div class="col-md-6 mt-3">
								<label class="form-label">Notes</label>
								<textarea name="complaint_notes" class="form-control"
									id="address" rows="3" disabled><%=complaint.getNotes()%></textarea>

							</div>


							<div class="col-md-6 mt-3">
								<label class="form-label" for="staff_role">Pin Code</label> <select
									id="inputState" name=citizen_pincode class="form-select"
									disabled>
									<option><%=citizen.getPinCode()%></option>
								</select>
							</div>

							<div class="col-md-6 mt-3">
								<label class="form-label" for="problem_type">Problem
									Type</label> <select name="problem_id" name=problem_type disabled
									class="form-select">
									<option><%=problem.getType()%></option>


								</select>
							</div>


							<div class="col-md-6 mt-3">
								<label class="form-label" for="problem_type">Subproblem
									Type</label> <select name="problem_id" name=problem_type disabled
									class="form-select">
									<option><%=subProblem.getType()%></option>

								</select>

							</div>

							<div class="col-md-6 mt-3">
								<label class="form-label" for="problem_type">Status </label> <select
									name="status_id" name=problem_type class="form-select">

									<option>--select--</option>
									<%
									StatusDao stdao = new StatusDao(DbConnect.getCon());
									Status status = stdao.getFollowUpStatus();
									%>
									<option value="<%=status.getStatusId()%>"><%=status.getName()%></option>


								</select>
							</div>

						</div>

						<div class="text-center mt-3">

							<button id="sumbimt-btn" type="submit"
								class="btn bg-success text-white text-center col-md-3">Assignment</button>
							<!-- 					<button type="reset" class="btn btn-success col-md-6"
								value="Reset Button"></button> -->
						</div>
					</form>

				</div>


				<br>

			</div>
		</div>
	</div>
</div>
</div>

</body>
</html>