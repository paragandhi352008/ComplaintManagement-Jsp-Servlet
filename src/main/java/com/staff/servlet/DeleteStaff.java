package com.staff.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.PinCodeDao;
import com.dao.StaffDao;
import com.dao.SubProblemDao;
import com.db.DbConnect;

@WebServlet("/deleteStaff")
public class DeleteStaff extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			int userId = Integer.parseInt(req.getParameter("userId"));
			StaffDao dao = new StaffDao(DbConnect.getCon());

			HttpSession session = req.getSession();
			try {

				boolean f = dao.deleteStaff(userId);
				if (f) {
					session.setAttribute("sucMsg", "Staff deleted successfully.");
					resp.sendRedirect("staff/superAdmin/staff.jsp");
				} else {
					session.setAttribute("errorMsg", "Something went on server.");
					resp.sendRedirect("staff/superAdmin/staff.jsp");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
