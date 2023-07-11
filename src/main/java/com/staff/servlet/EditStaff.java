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

@WebServlet("/editStaff")
public class EditStaff extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {

			String userIdString = req.getParameter("staff_id");
			int userId = Integer.parseInt(userIdString);
			String firstName = req.getParameter("staff_first_name");
			String lastName = req.getParameter("staff_last_name");
			String userName = req.getParameter("staff_user_name");
			String password = req.getParameter("staff_password");
			String address = req.getParameter("staff_address");
			String pinCodeString = req.getParameter("staff_pincode");
			int pinCode = Integer.parseInt(pinCodeString);
			String mobile = req.getParameter("staff_mobile");
			String email = req.getParameter("staff_email");
			String gender = req.getParameter("staff_gender");
			boolean isActive = true;
			String staffRoleIdString = req.getParameter("staff_role_id");
			int staffRoleId = Integer.parseInt(staffRoleIdString);

			Staff s = new Staff(userId, firstName, lastName, userName, password, address, pinCode, mobile, email,
					gender, isActive, staffRoleId);
			StaffDao dao = new StaffDao(DbConnect.getCon());

			HttpSession session = req.getSession();

			int roleId = s.getRoleId();

			boolean f = dao.updateStaff(s);

			if (f) {
				session.setAttribute("sucMsg", "Staff updated successfully.");
				System.out.println("staffRoleId is:" +staffRoleId);
				if (staffRoleId == 1) {
					resp.sendRedirect("staff/superAdmin/staff.jsp");
				} else if (staffRoleId == 2) {
					resp.sendRedirect("staff/engineer/index.jsp");
				} else if (staffRoleId == 3) {
					resp.sendRedirect("staff/engineerManager/index.jsp");
				} else if (staffRoleId == 4) {
					resp.sendRedirect("staff/operator/index.jsp");
				} else if (staffRoleId == 5) {
					resp.sendRedirect("staff/operationsManager/index.jsp");
				} else {
					resp.sendRedirect("staff/index.jsp");
				}
			} else {
				session.setAttribute("errorMsg", "Cannot delete problem with active references.");
				resp.sendRedirect("staff/superAdmin/staff.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}