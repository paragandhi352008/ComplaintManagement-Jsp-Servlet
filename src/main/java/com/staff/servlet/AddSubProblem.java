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
import com.dao.SubProblemDao;
import com.db.DbConnect;

@WebServlet("/addSubProblem")
public class AddSubProblem extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {

			String subProblemType = req.getParameter("subproblem_type");
			int problemId = Integer.parseInt(req.getParameter("problem_id"));

			SubProblemDao dao = new SubProblemDao(DbConnect.getCon());

			HttpSession session = req.getSession();
			try {
				if (dao.checkDuplicateSubProblem(subProblemType, problemId)) {
					session.setAttribute("errorMsg", "Subproblem type already exists. Please enter new one.");
					resp.sendRedirect("staff/superAdmin/subProblems.jsp");
				} else {
					boolean f = dao.addSubProblem(subProblemType, problemId);
					if (f) {
						session.setAttribute("sucMsg", "Subproblem added successfully.");
						resp.sendRedirect("staff/superAdmin/subProblems.jsp");
					} else {
						session.setAttribute("errorMsg", "Something went on server.");
						resp.sendRedirect("staff/superAdmin/subProblems.jsp");
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
