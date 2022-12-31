package com.staff.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.SubProblemDao;
import com.db.DbConnect;
import com.entity.SubProblem;

@WebServlet("/editSubProblem")
public class EditSubProblem extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {

			String subproblemIdString = req.getParameter("subproblem_id");
			int subproblemId = Integer.parseInt(subproblemIdString);
			String subproblemType = req.getParameter("subproblem_type");

			String problemType = req.getParameter("problem_type");

			SubProblem sp = new SubProblem(subproblemId, subproblemType);
			SubProblemDao dao = new SubProblemDao(DbConnect.getCon());

			HttpSession session = req.getSession();

			if (dao.checkDuplicateSubProblem(problemType, subproblemId)) {
				session.setAttribute("errorMsg", "Problem type already exists. Please enter new one.");
				resp.sendRedirect("staff/superAdmin/problems.jsp");
			} else {
				boolean f = dao.updateSubProblem(sp);
				if (f) {
					session.setAttribute("sucMsg", "Problem updated successfully.");
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
	}
}
