<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<%@ page import="com.dao.SubProblemDao"%>
<%@ page import="com.entity.SubProblem"%>
<%@ page import="com.db.DbConnect"%>
<%@ page import="com.dao.ProblemDao"%>
<%@ page import="com.entity.Problem"%>
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
		<div class="col-md-4 offset-md-4">
			<div class="card paint-card">
				<div class="card-body">
					<p class="fs-4 text-center">
						<span class="fa fa-3x fa-user-circle"></span> <br>Edit
						Subproblem
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
					int subProblemId = Integer.parseInt(request.getParameter("subproblemId"));
					SubProblemDao dao = new SubProblemDao(DbConnect.getCon());
					SubProblem sp = dao.getAllSubProblemById(subProblemId);
					%>

					<form action="../../editSubProblem" method="post"
						onsubmit="return validate()">


						<div class="mb-3">
							<label class="form-label">Subproblem Type</label> <input
								name="subproblem_type" type="text" class="form-control" required
								aria-describedby="emailHelp" value="<%=sp.getType()%>">
						</div>
						<input type="hidden" name="subproblem_id"
							value="<%=sp.getSubproblemId()%>">

						<div class="mb-3">
							<label for="problem_type">Problem Type</label> <select
								name="problem_id" name=problem_type class="form-select">
								<option value="<%=sp.getProblemId()%>"><%=sp.getProblemType()%></option>

							</select>
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