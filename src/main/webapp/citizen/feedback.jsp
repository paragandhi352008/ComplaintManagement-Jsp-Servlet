<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.List"%>
<%@ page import="com.dao.RoleDao"%>
<%@ page import="com.entity.Role"%>
<%@ page import="com.dao.ProblemDao"%>
<%@ page import="com.dao.SubProblemDao"%>
<%@ page import="com.entity.Problem"%>
<%@ page import="com.entity.SubProblem"%>
<%@ page import="com.entity.Citizen"%>
<%@ page import="com.dao.ComplaintDao"%>
<%@ page import="com.entity.Complaint"%>
<%@ page import="com.dao.StatusDao"%>
<%@ page import="com.entity.Status"%>
<%@ page import="com.dao.FeedbackDao"%>
<%@ page import="com.entity.Feedback"%>
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
<body>
	<c:if test="${empty forgotPasswordObj}">
		<%@ include file="navbar.jsp"%>
	</c:if>
	<c:if test="${not empty forgotPasswordObj}">
		<%@ include file="../component/navbar.jsp"%>
		<input type="hidden" name="new_password_obj" value="new_password">

	</c:if>
	<div class="container p-5">
		<div class="row">
			<div class="col-md-7 offset-md-2">
				<div class="card paint-card">
					<div class="card-body">
						<p class="fs-4 text-center">
							<br>Feedback
						</p>
						<c:if test="${not empty sucMsg}">
							<p class="text-center text-success fs-4">${sucMsg}</p>
							<c:remove var="sucMsg" scope="session" />
						</c:if>

						<c:if test="${not empty errorMsg}">
							<p class="text-center text-danger fs-4">${errorMsg}</p>
							<c:remove var="errorMsg" scope="session" />
						</c:if>
						<form action="../feedback" name="reset_password" method="post"
							onsubmit="return validate()">

							<%
							Citizen c = (Citizen) session.getAttribute("citizenObj");
							%>

							<input type="hidden" name="citizen_id" value="<%=c.getId()%>">
							<input type="hidden" name="citizen_email"
								value="<%=c.getEmail()%>">

							<c:if test="${empty forgotPasswordObj}">
							</c:if>

							<div class="mb-3">
								<label class="form-label">How satisfied are you with the
									handling of your complaint?</label>
								<textarea name="answer_01" class="form-control" id="address"
									rows="3" required placeholder="Enter your feedback."></textarea>
							</div>

							<div class="mb-3">
								<label class="form-label">How satisfied are you that the
									staff who dealt with your complaint were helpful and polite? </label>
								<textarea name="answer_02" class="form-control" id="address"
									rows="3" required placeholder="Enter your feedback."></textarea>
							</div>

							<div class="mb-3">
								<label class="form-label">Please provide suggestions to
									improve our services.</label>
								<textarea name="answer_03" class="form-control" id="address"
									rows="3" required placeholder="Enter your feedback."></textarea>
							</div>
							<br>

							<button id="sumbimt-btn" type="submit"
								class="btn bg-success text-white col-md-12">Submit</button>
							<c:remove var="forgotPasswordObj" scope="session" />
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>