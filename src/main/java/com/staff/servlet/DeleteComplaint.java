package com.staff.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.ComplaintDao;
import com.dao.PinCodeDao;
import com.dao.SubProblemDao;
import com.db.DbConnect;

@WebServlet("/deleteComplaint")
public class DeleteComplaint extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			int pincodeId = Integer.parseInt(req.getParameter("complaintId"));
			ComplaintDao dao = new ComplaintDao(DbConnect.getCon());

			HttpSession session = req.getSession();
			try {

				boolean f = dao.deleteComplaint(pincodeId);
				if (f) {
					session.setAttribute("sucMsg", "Complaint deleted successfully.");
					resp.sendRedirect("staff/operator/status.jsp");
				} else {
					session.setAttribute("errorMsg", "Something went on server.");
					resp.sendRedirect("staff/operator/status.jsp");
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}