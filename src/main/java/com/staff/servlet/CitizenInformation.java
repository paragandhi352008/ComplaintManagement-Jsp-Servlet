package com.staff.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.CitizenDao;
import com.dao.StaffDao;
import com.db.DbConnect;
import com.entity.Citizen;
import com.entity.Staff;

@WebServlet("/citizenInformation")
public class CitizenInformation extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {

			String citizenMobile = req.getParameter("citizen_mobile");

			CitizenDao dao = new CitizenDao(DbConnect.getCon());

			HttpSession session = req.getSession();

			Citizen citizen = dao.getCitizenByMobile(citizenMobile);

			if (citizen == null) {
				session.setAttribute("newCitizenObj", citizenMobile);
				session.setAttribute("errorMsg", "Citizen not available with this mobile. Please create new one.");
				resp.sendRedirect("citizenRegister.jsp");

			} else {
				session.setAttribute("mobileObj", citizen);
				resp.sendRedirect("staff/operator/registerComplaint.jsp");

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}