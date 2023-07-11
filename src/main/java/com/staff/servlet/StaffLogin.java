/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.staff.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.StaffDao;
import com.db.DbConnect;
import com.entity.Staff;

@WebServlet("/staffLogin")
public class StaffLogin extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String staffEmail = req.getParameter("staff_user_name");
			String staffPassword = req.getParameter("staff_password");
			String staffRoleIdString = req.getParameter("staff_role");
			int staffRoleId = Integer.parseInt(staffRoleIdString);

			HttpSession session = req.getSession();

			StaffDao dao = new StaffDao(DbConnect.getCon());

			Staff staff = dao.getStaffByEmailPasswordAndRole(staffEmail, staffPassword, staffRoleId);
			System.out.println("From staff login is:" +staff);
			if (staff == null) {
				session.setAttribute("errorMsg", "Invalid username or password.");
				resp.sendRedirect("staffLogin.jsp");
				System.out.println("I am in if loop");

			} else {
				session.setAttribute("staffObj", staff);
				if (staffRoleId == 1) {
					resp.sendRedirect("staff/superAdmin/index.jsp");
				} else if (staffRoleId == 2) {
					resp.sendRedirect("staff/engineer/index.jsp");
				} else if (staffRoleId == 3) {
					resp.sendRedirect("staff/engineerManager/index.jsp");
				} else if (staffRoleId == 4) {
					resp.sendRedirect("staff/operator/index.jsp");
				} else {
					resp.sendRedirect("staff/operationsManager/index.jsp");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
