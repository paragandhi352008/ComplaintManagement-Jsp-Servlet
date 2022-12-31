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

@WebServlet("/resetPasswordByCitizen")
public class ResetPassword extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			int citizenId = Integer.parseInt(req.getParameter("citizen_id"));
			String citizenEmail = req.getParameter("citizen_email");
			String currentPassword = req.getParameter("current_password");
			String newPassword = req.getParameter("new_password");
			String newPasswordObj = req.getParameter("new_password_obj");

			HttpSession session = req.getSession();

			Citizen citizen;
			CitizenDao dao = new CitizenDao(DbConnect.getCon());

			// Reset password after login
			if (newPasswordObj == null) {
				citizen = dao.getCitizenByEmailAndPassword(citizenEmail, currentPassword);
			} else { 
				// Reset password with forgot password
				citizen = dao.getCitizenByEmail(citizenEmail);
			}

			if (citizen == null) {
				session.setAttribute("errorMsg", "Invalid current password.");
				resp.sendRedirect("citizen/resetPassword.jsp");
			} else {
				boolean f = dao.resetPassword(newPassword, citizenId);
				if (f) {
					session.setAttribute("sucMsg", "Password reset successfully.");
					session.setAttribute("citizenObj", citizen);
					resp.sendRedirect("citizen/index.jsp");
				} else {
					session.setAttribute("errorMsg", "Something went wrong on server.");
					resp.sendRedirect("citizen/resetPassword.jsp");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
