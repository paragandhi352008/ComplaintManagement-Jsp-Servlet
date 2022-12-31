package com.complaint.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.ComplaintDao;
import com.db.DbConnect;

@WebServlet("/assignNewComplaints")
public class AssignNewComplaints extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			int userId = Integer.parseInt(req.getParameter("assign_to"));

			int zoneId = Integer.parseInt(req.getParameter("zone_id"));

			int statusId = 2;

			int complaintId = Integer.parseInt(req.getParameter("complaint_id"));

			ComplaintDao dao = new ComplaintDao(DbConnect.getCon());

			HttpSession session = req.getSession();

			boolean f = dao.assignNewComplaint(userId, zoneId, statusId, complaintId);

			if (f) {
				session.setAttribute("sucMsg", "New complaint assigned successfully.");
				resp.sendRedirect("staff/operationsManager/newComplaints.jsp");
			} else {
				session.setAttribute("errorMsg", "Something went wrong on server.");
				resp.sendRedirect("staff/operationsManager/newComplaints.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}