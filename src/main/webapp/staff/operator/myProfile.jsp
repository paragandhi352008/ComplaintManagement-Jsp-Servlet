<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.List"%>
<%@ page import="com.dao.RoleDao"%>
<%@ page import="com.entity.Role"%>
<%@ page import="com.dao.PinCodeDao"%>
<%@ page import="com.entity.PinCode"%>
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
<%
Staff staff = (Staff) session.getAttribute("staffObj");
int userId = staff.getUserId();
int roleId = staff.getRoleId();
StaffDao dao = new StaffDao(DbConnect.getCon());
Staff s = dao.getUserById(userId);
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



					<form action="../../editStaff" name="add_staff" method="post"
						onsubmit="return validate()">

						<div class="mb-3">
							<label class="form-label">First Name</label> <input
								name="staff_first_name" type="text" class="form-control"
								id="user_name" required aria-describedby="emailHelp"
								value="<%=s.getFirstName()%>">
						</div>
						<div class="mb-3">
							<label class="form-label">Last Name</label> <input
								name="staff_last_name" type="text" class="form-control"
								id="last_name" required aria-describedby="emailHelp"
								value="<%=s.getLastName()%>">
						</div>

						<div class="mb-3">
							<label class="form-label">User Name</label> <input
								name="staff_user_name" type="text" class="form-control"
								id="user_name" required aria-describedby="emailHelp"
								value="<%=s.getUserName()%>">
						</div>

						<div class="mb-3">
							<label class="form-label">Password</label> <input
								name="staff_password" type="password" class="form-control"
								id="password" required value="<%=s.getPassword()%>">
						</div>

						<div class="mb-3">
							<label class="form-label">Address</label>
							<textarea name="staff_address" class="form-control" id="address"
								rows="3" required><%=s.getAddress()%></textarea>

						</div>

						<div class="mb-3">
							<label class="form-label">Email address</label> <input
								name="staff_email" type="email" class="form-control" id="email"
								required aria-describedby="emailHelp" disabled
								value="<%=s.getEmail()%>">
						</div>


						<div class="mb-3">
							<label class="form-label">Select Gender</label> <br>
							<%
							if (s.getGender().equalsIgnoreCase("Male")) {
							%>
							<input type="radio" id="gender" name="staff_gender" value="Male"
								required checked>Male <input type="radio" id="gender"
								name="staff_gender" value="Female" required>Female
							<%
							} else if (s.getGender().equalsIgnoreCase("Female")) {
							%>
							<input type="radio" id="gender" name="staff_gender" value="Male"
								required>Male <input type="radio" id="gender"
								name="staff_gender" value="Female" required checked>Female
							<%
							}
							%>

						</div>

						<div class="mb-3">
							<label class="form-label">Mobile</label> <input
								name="staff_mobile" type="number" class="form-control"
								id="mobile" required aria-describedby="emailHelp"
								value="<%=s.getMobile()%>">
						</div>

						<div class="form-group">
							<label for="staff_role">Pin Code</label> <select id="inputState"
								name=staff_pincode class="form-select">
								<option><%=s.getPinCode()%></option>

							</select>
						</div>


						<div class="form-group">
							<label for="staff_role">Role</label> <select id="inputState"
								name=staff_role_id class="form-select">
								<option value="<%=s.getRoleId()%>"><%=s.getRoleName()%></option>

							</select> <input type="hidden" name="staff_id" value="<%=s.getUserId()%>">
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