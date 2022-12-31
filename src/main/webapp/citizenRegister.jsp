<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.dao.PinCodeDao"%>
<%@ page import="com.entity.PinCode"%>
<%@ page import="com.dao.SecurityQuestionDao"%>
<%@ page import="com.entity.SecurityQuestion"%>
<%@ page import="java.util.List"%>
<%@ page import="com.db.DbConnect"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>CCMS</title>
<%@ include file="component/allCss.jsp"%>
<style type="text/css">
.paint-card {
	box-shadow: 0 0 10px 0 rgba(0, 0, 0, 0.3);
}
</style>
</head>
<body>
	<c:if test="${empty staffObj}">
		<%@ include file="component/navbar.jsp"%>
	</c:if>
	<c:if test="${not empty staffObj}">
		<%@ include file="staff/operator/navbar.jsp"%>
	</c:if>
	<div class="container p-5">
		<div class="row">
			<div class="col-md-6 offset-md-3">
				<div class="card paint-card">
					<div class="card-body">
						<p class="fs-4 text-center">
							<span class="fa fa-3x fa-user-circle"></span> <br>Citizen
							Sign Up
						</p>
						<c:if test="${not empty sucMsg}">
							<p class="text-center text-success fs-4">${sucMsg}</p>
							<c:remove var="sucMsg" scope="session" />
						</c:if>

						<c:if test="${not empty errorMsg}">
							<p class="text-center text-danger fs-4">${errorMsg}</p>
							<c:remove var="errorMsg" scope="session" />
						</c:if>
						<form action="citizenRegister" name="citizen_register"
							method="post" onsubmit="return validate()">

							<div class="mb-3">
								<label class="form-label">First Name</label> <input
									name="citizen_first_name" type="text" class="form-control"
									id="user_name" required aria-describedby="emailHelp"
									placeholder="Enter First name" />
							</div>
							<div class="mb-3">
								<label class="form-label">Last Name</label> <input
									name="citizen_last_name" type="text" class="form-control"
									id="last_name" required aria-describedby="emailHelp"
									placeholder="Enter Last name" />
							</div>

							<div class="mb-3">
								<label class="form-label">User Name</label> <input
									name="citizen_user_name" type="text" class="form-control"
									id="user_name" required aria-describedby="emailHelp"
									placeholder="Enter User name" />
							</div>

							<div class="mb-3">
								<label class="form-label">Password</label> <input
									name="citizen_password" type="password" class="form-control"
									id="password" required aria-describedby="emailHelp"
									placeholder="Enter Password" />
							</div>

							<div class="mb-3">
								<label class="form-label">Address</label>
								<textarea name="citizen_address" class="form-control"
									id="address" rows="3" required placeholder="Enter Home address"></textarea>

							</div>

							<div class="mb-3">
								<label class="form-label">Email address</label> <input
									name="citizen_email" type="email" class="form-control"
									id="email" required aria-describedby="emailHelp"
									placeholder="Enter email" />
							</div>


							<div class="mb-3">
								<label class="form-label">Select Gender</label> <br> <input
									type="radio" id="gender" name="citizen_gender" value="male"
									required>Male <input type="radio" id="gender"
									name="citizen_gender" value="famale" />Female

							</div>

							<div class="mb-3">
								<label class="form-label">Mobile</label> <input
									name="citizen_mobile" type="number" class="form-control"
									id="mobile" required aria-describedby="emailHelp"
									placeholder="Enter Mobile number" />
							</div>

							<div class="form-group">
								<label for="staff_role">Pin Code</label> <select id="inputState"
									name=citizen_pincode class="form-select">
									<option selected disabled value="">--select--</option>
									<%
									PinCodeDao dao = new PinCodeDao(DbConnect.getCon());
									List<PinCode> list = dao.getAllPinCodes();
									for (PinCode p : list) {
									%>
									<option><%=p.getName()%></option>
									<%
									}
									%>
								</select>
							</div>


							<br>
							<div class="form-group">
								<label for="staff_role">Security Question</label> <select
									id="inputState" name=citizen_security_question_id
									class="form-select">
									<option selected disabled value="">--select--</option>
									<%
									SecurityQuestionDao dao2 = new SecurityQuestionDao(DbConnect.getCon());
									List<SecurityQuestion> list1 = dao2.getAllSecurityQuestions();
									for (SecurityQuestion sq : list1) {
									%>
									<option value="<%=sq.getQuestionsId()%>"><%=sq.getQuestionsName()%></option>
									<%
									}
									%>
								</select>
							</div>
							<br>
							<div class="mb-3">
								<label class="form-label">Answer</label> <input
									name="citizen_security_answer" class="form-control" id="mobile"
									required aria-describedby="emailHelp"
									placeholder="Enter Answer" />
							</div>

							<c:if test="${not empty staffObj}">
								<input type="hidden" name="staff" value="citizenByStaff">
							</c:if>

							<button id="sumbimt-btn" type="submit"
								class="btn bg-success text-white col-md-12">Submit</button>
							<c:remove var="staffObj" scope="session" />
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script>
		function validate() {
			var firstname = document.citizen_register.citizen_first_name.value;
			var lastname = document.citizen_register.citizen_last_name.value;
			var firstname = document.citizen_register.citizen_first_name.value;
			var username = document.citizen_register.citizen_user_name.value;
			var password = document.citizen_register.citizen_password.value;
			var address = document.citizen_register.citizen_address.value;
			var pincode = document.citizen_register.citizen_pincode.value;
			var mobile = document.citizen_register.citizen_mobile.value;
			var email = document.citizen_register.citizen_email.value;
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
			if (pincode.length >= 7) {
				alert("Pincode length must be exactly 6")
				return false;
			}
			if (mobile.length >= 11) {
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