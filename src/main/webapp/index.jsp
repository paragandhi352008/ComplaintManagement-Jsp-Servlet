<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page errorPage="errorPage.jsp"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="com.db.DbConnect"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>CCMS</title>
<%@ include file="component/allCss.jsp"%>
</head>
<body>
	<%@ include file="component/navbar.jsp"%>

	<div class="container p-4">

		<p class="text-center fs-2">Comprehensive Complaint Management
			System</p>
		<br>
		<div class="row"></div>

		<div class="card text-left">

			<div class="card-body">
				<h5 class="card-title">About CCMS</h5>
				<p class="card-text">The CCMS - Comprehensive Complaint
					Management System is an online complaint registration system which
					registers complaint of citizen and assign to respective engineer to
					resolve.</p>
				<p>The system keeps track of whole complaint with proper status.
					Users of system are Citizen, Operator, Operations Manager,
					Engineer, Engineer Manager, Super Admin.</p>
				It also provides various reports, dashboard, follow-up facility.
			</div>

		</div>

		<br>
		<div class="container p-6">
			<div class="row">
				<div class="col-md-4">
					<div class="card">

						<div class="card-body">
							<h5 class="card-title">To register complaint at call center:</h5>
							<p class="card-text">Contact our dedicated call center by
								dialing 123945 from landline or mobile between 24*7 to raise
								complaint.</p>
							<a href="#" class="btn primary-background text-white">Read
								more</a>
						</div>
					</div>
				</div>
				<div class="col-md-4">
					<div class="card">

						<div class="card-body">
							<h5 class="card-title">To register complaint online through
								website:</h5>
							<p class="card-text">Login to application then click on the
								"Register" option of Complaint Registration and enter detail of
								your complaint.</p>
							<a href="#" class="btn primary-background text-white">Read
								more</a>
						</div>
					</div>
				</div>

				<div class="col-md-4">
					<div class="card">

						<div class="card-body">
							<h5 class="card-title">To reopen complaints:</h5>
							<p class="card-text">Login to application then click on
								Reopen or Contact our dedicated call center by dialing 123945
								from landline or mobile between 24*7 to reopen complaint.</p>
							<a href="#" class="btn primary-background text-white">Read
								more</a>
						</div>
					</div>
				</div>


			</div>
		</div>

	</div>
</body>
</html>