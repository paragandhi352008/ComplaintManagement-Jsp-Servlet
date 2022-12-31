package com.complaint.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.ComplaintDao;
import com.dao.StaffDao;
import com.db.DbConnect;

@WebServlet("/editComplaintStatus")
public class EditComplaintStatus extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {

			int complaintId = Integer.parseInt(req.getParameter("complaint_id"));
			int statusId = Integer.parseInt(req.getParameter("status_id"));
			int staffLoginId = Integer.parseInt(req.getParameter("staff_id"));

			StaffDao sdao = new StaffDao(DbConnect.getCon());
			int roleId = sdao.getStaffRoleByStaffId(staffLoginId);

			ComplaintDao dao = new ComplaintDao(DbConnect.getCon());

			HttpSession session = req.getSession();

			boolean f = dao.editComplaintStatus(statusId, complaintId);

			if (f) {
				session.setAttribute("sucMsg", "Complaint status updated successfully.");
				if (roleId == 4) {
					resp.sendRedirect("staff/operator/reopenComplaints.jsp");
				} else if (roleId == 5)
					resp.sendRedirect("staff/operationsManager/newComplaints.jsp");
			} else {
				session.setAttribute("errorMsg", "Something went wrong on server.");
				resp.sendRedirect("staff/engineer/pendingComplaints.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}