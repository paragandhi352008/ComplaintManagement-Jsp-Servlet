/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.citizen.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.CitizenDao;
import com.db.DbConnect;
import com.entity.Citizen;

@WebServlet("/forgotPassword")
public class ForgotPassword extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String citizenEmail = req.getParameter("citizen_email");
			int questionsId = Integer.parseInt(req.getParameter("citizen_security_question_id"));
			String answer = req.getParameter("citizen_security_answer");

			HttpSession session = req.getSession();

			CitizenDao dao = new CitizenDao(DbConnect.getCon());

			Citizen citizen = dao.getCitizenByEmailSecurityQuestionAndPassword(citizenEmail, questionsId, answer);

			if (citizen == null) {
				session.setAttribute("errorMsg", "Invalid email, security question or answer");
				resp.sendRedirect("citizenLogin.jsp");

			} else {
				session.setAttribute("citizenObj", citizen);
				session.setAttribute("forgotPasswordObj", "forgot password");
				resp.sendRedirect("citizen/resetPassword.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
