package com.complaint.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.ProblemDao;
import com.dao.SubProblemDao;
import com.db.DbConnect;
import com.entity.Problem;
import com.entity.SubProblem;

@WebServlet("/registerComplaint")
public class RegisterComplaint extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			int problemId = Integer.parseInt(req.getParameter("problem_id"));

			ProblemDao dao = new ProblemDao(DbConnect.getCon());

			HttpSession session = req.getSession();

			Problem problem = dao.getAllProblemById(problemId);

			session.setAttribute("problemObj", problem);
			resp.sendRedirect("citizen/registerComplaint.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
