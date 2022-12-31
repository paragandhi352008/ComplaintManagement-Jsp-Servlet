package com.staff.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.entity.Citizen;
import com.entity.Staff;
import com.dao.CitizenDao;
import com.dao.StaffDao;
import com.db.DbConnect;

@WebServlet("/addStaff")
public class AddStaff extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {

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

			Staff staff = new Staff(firstName, lastName, userName, password, address, pinCode, mobile, email, gender,
					isActive, staffRoleId);

			StaffDao dao = new StaffDao(DbConnect.getCon());

			HttpSession session = req.getSession();

			int staffLoginId = Integer.parseInt(req.getParameter("staff_id"));
			
			Staff staff1 = dao.getStaffByStaffId(staffLoginId);
			int loggedInRoleId  = staff1.getRoleId();   

			if (dao.checkDuplicateUser(staff, email, mobile)) {
				session.setAttribute("errorMsg",
						"Email address or Mobile number already exists. Please enter new one.");
				resp.sendRedirect("staff/superAdmin/addStaff.jsp");
			} else {
				boolean f = dao.saveuser(staff);
				session.setAttribute("sucMsg", "Staff registered successfully.");
				if (f) {
					if (loggedInRoleId == 1) {
						resp.sendRedirect("staff/superAdmin/staff.jsp");
					} else if (loggedInRoleId == 3) {
						resp.sendRedirect("staff/engineerManager/engineers.jsp");
					} else if (loggedInRoleId == 5) {
						resp.sendRedirect("staff/operationsManager/index.jsp");
					} else {
						resp.sendRedirect("staff/index.jsp");
					}

				} else {
					session.setAttribute("errorMsg", "Something went wrong on server.");
					resp.sendRedirect("staff/superAdmin/staff.jsp");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}