package com.citizen.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.entity.Citizen;
import com.dao.CitizenDao;
import com.dao.FeedbackDao;
import com.db.DbConnect;

@WebServlet("/feedback")
public class Feedback extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {

			String answer01 = req.getParameter("answer_01");
			String answer02 = req.getParameter("answer_02");
			String answer03 = req.getParameter("answer_03");
			int citizenId = Integer.parseInt(req.getParameter("citizen_id"));

			FeedbackDao dao = new FeedbackDao(DbConnect.getCon());

			HttpSession session = req.getSession();

			boolean f = dao.addFeedback(answer01, answer02, answer03, citizenId);
			if (f) {
				session.setAttribute("sucMsg", "Feedback submitted successfully.");
				resp.sendRedirect("citizen/index.jsp");
			} else {
				session.setAttribute("errorMsg", "Something went wrong on server.");
				resp.sendRedirect("citizen/index.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}