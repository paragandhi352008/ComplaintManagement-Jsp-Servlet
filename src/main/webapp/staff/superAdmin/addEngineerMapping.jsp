<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.List"%>
<%@ page import="com.dao.RoleDao"%>
<%@ page import="com.entity.Role"%>
<%@ page import="com.dao.PinCodeDao"%>
<%@ page import="com.entity.PinCode"%>
<%@ page import="com.dao.ProblemDao"%>
<%@ page import="com.entity.Problem"%>
<%@ page import="com.dao.StaffDao"%>
<%@ page import="com.entity.Staff"%>
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
						<span class="fa fa-3x fa-user-circle"></span> <br>Add
						Engineer Mapping
					</p>
					<c:if test="${not empty sucMsg}">
						<p class="text-center text-success fs-4">${sucMsg}</p>
						<c:remove var="sucMsg" scope="session" />
					</c:if>

					<c:if test="${not empty errorMsg}">
						<p class="text-center text-danger fs-4">${errorMsg}</p>
						<c:remove var="errorMsg" scope="session" />
					</c:if>

					<%
					int userId = Integer.parseInt(request.getParameter("userId"));
					StaffDao dao = new StaffDao(DbConnect.getCon());
					Staff s = dao.getEngineerById(userId);
					%>

					<form action="../../addEngineerMapping" name="add_staff"
						method="post" onsubmit="return validate()">

						<div class="mb-3">
							<label class="form-label">Engineer Name</label> <input
								name="engineer_name" type="text" class="form-control"
								id="user_name" required aria-describedby="emailHelp" disabled
								value="<%=s.getFirstName() + ' ' + s.getLastName()%>">
						</div>
						<input type="hidden" name="user_id" value="<%=s.getUserId()%>">

						<div class="mb-3">
							<label class="form-label">Zone Name</label> <input
								name="zone_name" type="text" class="form-control" id="zone_name"
								required aria-describedby="emailHelp" disabled
								value="<%=s.getZoneName()%>">
						</div>
						<input type="hidden" name="zone_id" value="<%=s.getZoneId()%>">

						<div class="mb-3">
							<label for="problem_type">Problem Type</label> <select
								name="problem_id" name=problem_type class="form-select">
								<option>--select--</option>
								<%
								ProblemDao dao1 = new ProblemDao(DbConnect.getCon());
								List<Problem> list = dao1.getAllProblems();
								for (Problem p : list) {
								%>
								<option value="<%=p.getProblemId()%>"><%=p.getType()%></option>
								<%
								}
								%>

							</select>
						</div>
						<br>
						<button id="sumbimt-btn" type="submit"
							class="btn bg-success text-white col-md-12">Submit</button>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>
<script>
	function validate() {
		var firstname = document.add_staff.staff_first_name.value;
		var lastname = document.add_staff.staff_last_name.value;
		var firstname = document.add_staff.staff_first_name.value;
		var username = document.add_staff.staff_user_name.value;
		var password = document.add_staff.staff_password.value;
		var address = document.add_staff.staff_address.value;
		var pincode = document.add_staff.staff_pincode.value;
		var mobile = document.add_staff.staff_mobile.value;
		var email = document.add_staff.staff_email.value;
		if (firstname.length >= 15) {
			alert("First name length must be less than 15")
			return false;
		}
		if (lastname.length >= 15) {
			alert("Last name length must be less than 15")
			return false;
		}
		if (username.length >= 15) {
			alert("Username length must be less than 15")
			return false;
		}
		if (password.length >= 15) {
			alert("Password length must be less than 15")
			return false;
		}
		if (address.length >= 25) {
			alert("Address length must be less than 25")
			return false;
		}
		if (pincode.length != 6) {
			alert("Pincode length must be exactly 6")
			return false;
		}
		if (mobile.length != 10) {
			alert("Mobile number length must be exactly 10")
			return false;
		}
		if (email.length >= 20) {
			alert("Email address length must be less than 20")
			return false;
		}

		return true;
	}
</script>
</body>
</html>