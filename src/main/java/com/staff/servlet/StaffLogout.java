package com.staff.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.entity.Citizen;

@WebServlet("/staffLogout")
public class StaffLogout extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			HttpSession session = req.getSession();
			session.removeAttribute("staffObj");
			session.setAttribute("sucMsg", "Staff logout successfully.");
			resp.sendRedirect("staffLogin.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
