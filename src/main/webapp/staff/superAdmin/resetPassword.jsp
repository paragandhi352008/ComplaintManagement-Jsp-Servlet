<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.List"%>
<%@ page import="com.dao.StaffDao"%>
<%@ page import="com.entity.Staff"%>
<%@ page import="com.dao.PinCodeDao"%>
<%@ page import="com.entity.PinCode"%>
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
							<br>Reset Password
						</p>
						<c:if test="${not empty sucMsg}">
							<p class="text-center text-success fs-4">${sucMsg}</p>
							<c:remove var="sucMsg" scope="session" />
						</c:if>

						<c:if test="${not empty errorMsg}">
							<p class="text-center text-danger fs-4">${errorMsg}</p>
							<c:remove var="errorMsg" scope="session" />
						</c:if>
						<form action="../../resetPasswordByStaff" name="reset_password" method="post"
							onsubmit="return validate()">

							<input type="hidden" name="staff_id" value="${staffObj.userId}">
							<input type="hidden" name="staff_email" value="${staffObj.email}">
							<input type="hidden" name="staff_role_id" value="${staffObj.roleId}" >

							<div class="mb-3">
								<label class="form-label">Current Password</label> <input
									name="current_password" type="text" class="form-control"
									id="staff_user_name" required aria-describedby="emailHelp"
									placeholder="Enter current password">
							</div>

							<div class="mb-3">
								<label class="form-label">New Password</label> <input
									name="new_password" type="password" class="form-control"
									id="password" required placeholder="Enter new password">
							</div>

							<div class="mb-3">
								<label class="form-label">Re-enter new Password</label> <input
									name="reenter_new_password" type="password"
									class="form-control" id="password" required
									placeholder="Re-enter new password">
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
			var current_password = document.reset_password.current_password.value;
			var new_password = document.reset_password.new_password.value;
			var reenter_new_password = document.reset_password.reenter_new_password.value;

			if (new_password.length <= 6) {
				alert("New password length must be greater than 6")
				return false;
			}
			if (reenter_new_password.length <= 6) {
				alert("Reenter new password length must be greater than 6")
				return false;
			}
			if (new_password != reenter_new_password) {
				alert("New password and reenter new password must match")
				return false;
			}

			return true;
		}
	</script>
</body>
</html>