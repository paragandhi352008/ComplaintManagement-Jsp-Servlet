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

import com.dao.CitizenDao;
import com.dao.StaffDao;
import com.db.DbConnect;
import com.entity.Citizen;
import com.entity.Staff;

@WebServlet("/resetPasswordByStaff")
public class ResetPassword extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			int staffId = Integer.parseInt(req.getParameter("staff_id"));
			String staffEmail = req.getParameter("staff_email");
			String currentPassword = req.getParameter("current_password");
			String newPassword = req.getParameter("new_password");
			HttpSession session = req.getSession();

			StaffDao dao = new StaffDao(DbConnect.getCon());

			Staff staff = dao.getStaffByEmailAndPassword(staffEmail, currentPassword);

			int roleId = dao.getStaffRoleByEmail(staffEmail);

			if (staff == null) {
				session.setAttribute("errorMsg", "Invalid current password.");
				resp.sendRedirect("staff/resetPassword.jsp");

			} else {
				boolean f = dao.resetPassword(newPassword, staffId);
				if (f) {
					session.setAttribute("sucMsg", "Password reset successfully.");
					session.setAttribute("staffObj", staff);

					if (roleId == 1) {
						resp.sendRedirect("staff/superAdmin/index.jsp");
					} else if (roleId == 2) {
						resp.sendRedirect("staff/engineer/index.jsp");
					} else if (roleId == 3) {
						resp.sendRedirect("staff/engineerManager/index.jsp");
					} else if (roleId == 4) {
						resp.sendRedirect("staff/operator/index.jsp");
					} else if (roleId == 5) {
						resp.sendRedirect("staff/operationsManager/index.jsp");
					} else {
						resp.sendRedirect("staff/index.jsp");
					}
				} else {
					session.setAttribute("errorMsg", "Something went wrong on server.");
					resp.sendRedirect("staff/resetPassword.jsp");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}