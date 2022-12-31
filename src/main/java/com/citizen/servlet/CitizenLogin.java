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

@WebServlet("/citizenLogin")
public class CitizenLogin extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			// Get parameter values from .jsp file
			String citizenEmail = req.getParameter("citizen_user_name");
			String citizenPassword = req.getParameter("citizen_password");

			HttpSession session = req.getSession();

			CitizenDao dao = new CitizenDao(DbConnect.getCon());

			Citizen citizen = dao.getCitizenByEmailAndPassword(citizenEmail, citizenPassword);

			if (citizen == null) {
				session.setAttribute("errorMsg", "Invalid username or password.");
				resp.sendRedirect("citizenLogin.jsp");

			} else {
				// redirection
				session.setAttribute("citizenObj", citizen);
				resp.sendRedirect("citizen/index.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
