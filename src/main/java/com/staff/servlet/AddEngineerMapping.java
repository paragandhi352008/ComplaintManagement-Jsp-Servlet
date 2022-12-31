package com.staff.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.EngineerMappingDao;
import com.db.DbConnect;

@WebServlet("/addEngineerMapping")
public class AddEngineerMapping extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			int userId = Integer.parseInt(req.getParameter("user_id"));
			int zoneId = Integer.parseInt(req.getParameter("zone_id"));
			int problemId = Integer.parseInt(req.getParameter("problem_id"));

			EngineerMappingDao dao = new EngineerMappingDao(DbConnect.getCon());

			HttpSession session = req.getSession();

			try {
				boolean f = dao.addEngineerMapping(userId, problemId, zoneId);
				if (f) {
					session.setAttribute("sucMsg", "Engineer Mapping added successfully.");
					session.setAttribute("mappedObj", f);
					resp.sendRedirect("staff/superAdmin/engineerMapping.jsp");
				} else {
					session.setAttribute("errorMsg", "Something went on server.");
					resp.sendRedirect("staff/superAdmin/engineerMapping.jsp");
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