package com.staff.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.ProblemDao;
import com.db.DbConnect;
import com.entity.Problem;

@WebServlet("/updateProblem")
public class UpdateProblem extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
		String problemType = req.getParameter("problem_type");
		String problemIdString = req.getParameter("problemId");
		int problemId = Integer.parseInt(problemIdString);
		
		Problem p = new Problem(problemId, problemType);
		ProblemDao dao = new ProblemDao(DbConnect.getCon());

		HttpSession session = req.getSession();
		
			if (dao.checkDuplicateProblem(problemType)) {
				session.setAttribute("errorMsg", "Problem type already exists. Please enter new one.");
				resp.sendRedirect("staff/superAdmin/problems.jsp");
			} else {
				boolean f = dao.updateProblem(p);
				if (f) {
					session.setAttribute("sucMsg", "Problem updated successfully.");
					resp.sendRedirect("staff/superAdmin/problems.jsp");
				} else {
					session.setAttribute("errorMsg", "Something went on server.");
					resp.sendRedirect("staff/superAdmin/problems.jsp");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
