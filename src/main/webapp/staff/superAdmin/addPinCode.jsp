<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<%@ page import="com.dao.ZoneDao"%>
<%@ page import="com.entity.Zone"%>
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
						<span class="fa fa-3x fa-user-circle"></span> <br>Add Pin
						Code
					</p>
					<c:if test="${not empty sucMsg}">
						<p class="text-center text-success fs-4">${sucMsg}</p>
						<c:remove var="sucMsg" scope="session" />
					</c:if>

					<c:if test="${not empty errorMsg}">
						<p class="text-center text-danger fs-4">${errorMsg}</p>
						<c:remove var="errorMsg" scope="session" />
					</c:if>
					<form action="../../addPinCode" method="post"
						onsubmit="return validate()">

						<div class="mb-3">
							<label class="form-label">Pin Code Name</label> <input
								name="pincode_name" type="text" class="form-control" required
								aria-describedby="emailHelp" placeholder="Enter Pin Code name">
						</div>


						<div class="mb-3">
							<label for="problem_type">Zone Name</label> <select
								name="zone_id" name=problem_type class="form-select" required>
								<option selected disabled value="">--select--</option>
								<%
								ZoneDao dao = new ZoneDao(DbConnect.getCon());
								List<Zone> list = dao.getAllZones();
								for (Zone z : list) {
								%>
								<option value="<%=z.getZoneId()%>"><%=z.getName()%></option>
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
		var firstname = document.add_problem.problem_type.value;
		if (firstname.length >= 5) {
			alert("First name length must be less than 15")
			return false;
		}

		return true;
	}
</script>
</body>
</html>