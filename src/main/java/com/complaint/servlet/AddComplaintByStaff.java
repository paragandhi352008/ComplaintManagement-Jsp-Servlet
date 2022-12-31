package com.complaint.servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.ComplaintDao;
import com.db.DbConnect;

@WebServlet("/addComplaintByStaff")
public class AddComplaintByStaff extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			int userId = Integer.parseInt(req.getParameter("assign_to"));

			int zoneId = Integer.parseInt(req.getParameter("zone_id"));

			int citizenId = Integer.parseInt(req.getParameter("citizen_id"));
			int problemId = Integer.parseInt(req.getParameter("problem_id1"));
			int subproblemId = Integer.parseInt(req.getParameter("subproblem_id"));
			String notes = req.getParameter("complaint_notes");
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
			LocalDateTime localDateTime = LocalDateTime.now();
			int currentTime = localDateTime.getHour() + localDateTime.getMinute();
			String createdDateTime = dtf.format(localDateTime);
			String pre = "CMP";
			DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy/MM/dd");
			LocalDate localDate = LocalDate.now();
			String date = df.format(localDate);

			String complaintNo = pre + date.replace("/", "") + currentTime + citizenId + problemId;

			int createdBy = Integer.parseInt(req.getParameter("created_by"));

			// Status ID 2: Pending
			int statusId = 2;

			ComplaintDao dao = new ComplaintDao(DbConnect.getCon());

			HttpSession session = req.getSession();

			boolean f = dao.addComplaintByStaff(statusId, citizenId, userId, zoneId, problemId, subproblemId,
					createdDateTime, notes, pre, date, complaintNo, createdBy);

			if (f) {
				session.setAttribute("sucMsg", "Complaint registered successfully.");
				resp.sendRedirect("staff/operator/citizens.jsp");
			} else {
				session.setAttribute("errorMsg", "Something went wrong on server.");
				resp.sendRedirect("staff/operator/registerComplaint.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}