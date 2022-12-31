<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.List"%>
<%@ page import="com.dao.RoleDao"%>
<%@ page import="com.entity.Role"%>
<%@ page import="com.dao.PinCodeDao"%>
<%@ page import="com.entity.PinCode"%>
<%@ page import="com.dao.CitizenDao"%>
<%@ page import="com.entity.Citizen"%>
<%@ page import="com.dao.SecurityQuestionDao"%>
<%@ page import="com.entity.SecurityQuestion"%>
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
<%
Citizen citizen = (Citizen) session.getAttribute("citizenObj");
int userId = citizen.getId();
CitizenDao dao = new CitizenDao(DbConnect.getCon());
Citizen c = dao.getCitizenById(userId);
%>

<%@ include file="navbar.jsp"%>

<div class="container p-5">
	<div class="row">
		<div class="col-md-6 offset-md-3">
			<div class="card paint-card">
				<div class="card-body">
					<p class="fs-4 text-center">
						<span class="fa fa-3x fa-user-circle"></span> <br>Edit
						Profile
					</p>
					<c:if test="${not empty sucMsg}">
						<p class="text-center text-success fs-4">${sucMsg}</p>
						<c:remove var="sucMsg" scope="session" />
					</c:if>

					<c:if test="${not empty errorMsg}">
						<p class="text-center text-danger fs-4">${errorMsg}</p>
						<c:remove var="errorMsg" scope="session" />
					</c:if>



					<form action="../editCitizen" name="add_staff" method="post"
						onsubmit="return validate()">
						
						<input type="hidden" name="citizen_id" value="<%=c.getId()%>">

						<div class="mb-3">
							<label class="form-label">First Name</label> <input
								name="citizen_first_name" type="text" class="form-control"
								id="user_name" required aria-describedby="emailHelp"
								value="<%=c.getFirstName()%>">
						</div>
						<div class="mb-3">
							<label class="form-label">Last Name</label> <input
								name="citizen_last_name" type="text" class="form-control"
								id="last_name" required aria-describedby="emailHelp"
								value="<%=c.getLastName()%>">
						</div>

						<div class="mb-3">
							<label class="form-label">User Name</label> <input
								name="citizen_user_name" type="text" class="form-control"
								id="user_name" required aria-describedby="emailHelp"
								value="<%=c.getUserName()%>">
						</div>

						<div class="mb-3">
							<label class="form-label">Address</label>
							<textarea name="citizen_address" class="form-control"
								id="address" rows="3" required><%=c.getAddress()%></textarea>

						</div>

						<div class="mb-3">
							<label class="form-label">Email address</label> <input
								name="citizen_email" type="email" class="form-control"
								id="email" required aria-describedby="emailHelp" disabled
								value="<%=c.getEmail()%>">
						</div>


						<div class="mb-3">
							<label class="form-label">Select Gender</label> <br>
							<%
							if (c.getGender().equalsIgnoreCase("Male")) {
							%>
							<input type="radio" id="gender" name="citizen_gender"
								value="Male" required checked>Male <input type="radio"
								id="gender" name="citizen_gender" value="Female" required>Female
							<%
							} else if (c.getGender().equalsIgnoreCase("Female")) {
							%>
							<input type="radio" id="gender" name="citizen_gender"
								value="Male" required>Male <input type="radio"
								id="gender" name="citizen_gender" value="Female" required
								checked>Female
							<%
							}
							%>

						</div>
						
						<div class="mb-3">
							<label class="form-label">Mobile</label> <input
								name="citizen_mobile" type="number" class="form-control"
								id="mobile" required aria-describedby="emailHelp" disabled
								value="<%=c.getMobile()%>">
						</div>

						<div class="mb-3">
							<label for="citizen_role">Pin Code</label> <select
								id="inputState" name=citizen_pincode class="form-select">
								<option><%=c.getPinCode()%></option>

							</select>
						</div>
						
						<input type="hidden" name="citizen_security_question_id" value="<%=c.getQuestionId()%>">

						<div class="mb-3">
							<label for="citizen_role">Security Question</label> <select
								id="inputState"
								class="form-select">
								<%
								SecurityQuestionDao sqdao = new SecurityQuestionDao(DbConnect.getCon());
								SecurityQuestion sq = sqdao.getSecurityQuestionByQuestionId(c.getQuestionId());
								%>
								<option selected disabled value="<%=c.getQuestionId()%>"><%=sq.getQuestionsName()%></option>
							</select>
						</div>

						<div class="mb-3">
							<label class="form-label">Answer</label> <input
								name="citizen_security_answer" class="form-control" id="mobile"
								required aria-describedby="emailHelp" value="<%=c.getAnswer()%>">
						</div>
						<br>
						<button id="sumbimt-btn" type="submit"
							class="btn bg-success text-white col-md-12">Update</button>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>
<script>
	function validate() {
		var firstname = document.add_staff.citizen_first_name.value;
		var lastname = document.add_staff.citizen_last_name.value;
		var firstname = document.add_staff.citizen_first_name.value;
		var username = document.add_staff.citizen_user_name.value;
		var address = document.add_staff.citizen_address.value;
		var pincode = document.add_staff.citizen_pincode.value;
		var mobile = document.add_staff.citizen_mobile.value;
		var email = document.add_staff.citizen_email.value;
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
		if (address.length >= 25) {
			alert("Address length must be less than 25")
			return false;
		}
		if (pincode.length != 6) {
			alert("Pincode length must be exactly 6")
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