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

@WebServlet("/editCitizen")
public class EditCitizen extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {

			int citizenId = Integer.parseInt(req.getParameter("citizen_id"));
			String firstName = req.getParameter("citizen_first_name");
			String lastName = req.getParameter("citizen_last_name");
			String userName = req.getParameter("citizen_user_name");
			String address = req.getParameter("citizen_address");
			String pinCodeString = req.getParameter("citizen_pincode");
			int pinCode = Integer.parseInt(pinCodeString);
			String email = req.getParameter("citizen_email");
			String gender = req.getParameter("citizen_gender");
			int questionsId = Integer.parseInt(req.getParameter("citizen_security_question_id"));
			String answer = req.getParameter("citizen_security_answer");

			Citizen citizen = new Citizen(citizenId, firstName, lastName, userName, address, pinCode, email, gender,
					questionsId, answer);

			CitizenDao dao = new CitizenDao(DbConnect.getCon());

			HttpSession session = req.getSession();

			boolean f = dao.updateCitizen(citizen);

			if (f) {

				session.setAttribute("sucMsg", "Profile updated successfully.");
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