package com.staff.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.ProblemDao;
import com.db.DbConnect;

@WebServlet("/deleteProblem")
public class DeleteProblem extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			int problemId = Integer.parseInt(req.getParameter("problemId"));
			ProblemDao dao = new ProblemDao(DbConnect.getCon());

			HttpSession session = req.getSession();
			try {

				boolean f = dao.deleteProblem(problemId);
				if (f) {
					session.setAttribute("sucMsg", "Problem deleted successfully.");
					resp.sendRedirect("staff/superAdmin/problems.jsp");
				} else {
					session.setAttribute("errorMsg", "Cannot delete problem with active references.");
					resp.sendRedirect("staff/superAdmin/problems.jsp");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}