package com.staff.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.EngineerMappingDao;
import com.db.DbConnect;

@WebServlet("/deleteEngineerMapping")
public class DeleteEngineerMapping extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			int userId = Integer.parseInt(req.getParameter("userId"));
			EngineerMappingDao dao = new EngineerMappingDao(DbConnect.getCon());

			HttpSession session = req.getSession();
			try {

				boolean f = dao.deleteMapping(userId);
				if (f) {
					session.setAttribute("sucMsg", "Engineer Mapping deleted successfully.");
					resp.sendRedirect("staff/superAdmin/engineerMapping.jsp");
				} else {
					session.setAttribute("errorMsg", "Something went on server.");
					resp.sendRedirect("staff/superAdmin/engineerMapping.jsp");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
