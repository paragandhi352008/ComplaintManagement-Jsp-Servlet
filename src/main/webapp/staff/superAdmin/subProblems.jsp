<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<%@ page import="java.util.List"%>
<%@ page import="com.dao.SubProblemDao"%>
<%@ page import="com.entity.SubProblem"%>
<%@ page import="com.db.DbConnect"%>
<!DOCTYPE html>
<html>
<head>

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
		<p class="text-center fs-3">Subproblems</p>
		<c:if test="${empty staffObj}">
			<c:redirect url="../staffLogin.jsp"></c:redirect>
		</c:if>
		<c:if test="${not empty sucMsg}">
			<p class="text-center text-success fs-4">${sucMsg}</p>
			<c:remove var="sucMsg" scope="session" />
		</c:if>

		<c:if test="${not empty errorMsg}">
			<p class="text-center text-danger fs-4">${errorMsg}</p>
			<c:remove var="errorMsg" scope="session" />
		</c:if>
		<br>
		<div>
			<a href="addSubProblem.jsp" class="btn btn-success">Add Subproblem</a> <br>
			<br>
		</div>
		<div class="col-md-12">
			<div class="card paint-card">
				<div class="card-body">


					<table class="table table-hover" id="table" >
						<thead>
							<tr>
								<th scope="col">Subproblem</th>
								<th scope="col">Problem</th>
								<th scope="col">Action</th>
							</tr>
						</thead>
						<tbody>
							<%
							SubProblemDao dao = new SubProblemDao(DbConnect.getCon());
							List<SubProblem> list = dao.getAllSubProblems();
							for (SubProblem p : list) {
							%>
							<tr>
								<td><%=p.getType()%></td>
								<td><%=p.getProblemType()%></td>
								<td><a
									href="editSubProblem.jsp?subproblemId=<%=p.getSubproblemId()%>"
									class="btn btn-success btn-sm">Edit</a> <a
									href="../../deleteSubProblem?subproblemId=<%=p.getSubproblemId()%>"
									class="btn btn-sm btn-danger" onclick="return confirm('Are you sure you want to delete?')">Delete</a></td>
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
$(document).ready(function(){
    $('#table').dataTable();
});
</script>
</html>

