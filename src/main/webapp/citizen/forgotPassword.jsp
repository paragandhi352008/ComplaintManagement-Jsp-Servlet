<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.dao.SecurityQuestionDao"%>
<%@ page import="com.entity.SecurityQuestion"%>
<%@ page import="com.db.DbConnect"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>CCMS</title>
<%@ include file="../component/allCss.jsp"%>
<style type="text/css">
.paint-card {
	box-shadow: 0 0 10px 0 rgba(0, 0, 0, 0.3);
}
</style>
</head>
<body>
	<%@ include file="../component/navbar.jsp"%>
	<div class="container p-5">
		<div class="row">
			<div class="col-md-6 offset-md-3">
				<div class="card paint-card">
					<div class="card-body">
						<p class="fs-4 text-center">
							<span class="fa fa-3x fa-user-circle"></span> <br>Forgot
							Password?
						</p>
						<c:if test="${not empty sucMsg}">
							<p class="text-center text-success fs-4">${sucMsg}</p>
							<c:remove var="sucMsg" scope="session" />
						</c:if>

						<c:if test="${not empty errorMsg}">
							<p class="text-center text-danger fs-4">${errorMsg}</p>
							<c:remove var="errorMsg" scope="session" />
						</c:if>
						<form action="../forgotPassword" method="post"
							onsubmit="return validate()">


							<div class="mb-3">
								<label class="form-label">Email</label> <input
									name="citizen_email" type="text" class="form-control"
									id="staff_user_name" required aria-describedby="emailHelp"
									placeholder="Enter Email">
							</div>

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

							<div class="mb-3">
								<label class="form-label">Answer</label> <input
									name="citizen_security_answer" class="form-control" id="mobile"
									required aria-describedby="emailHelp"
									placeholder="Enter Answer" />
							</div>

							<button id="sumbimt-btn" type="submit"
								class="btn bg-success text-white col-md-12">Submit</button>

							<div class="mb-3 mt-3">
								<span>Don't have an account? <a
									href="../citizenRegister.jsp">Sign up</a></span>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>