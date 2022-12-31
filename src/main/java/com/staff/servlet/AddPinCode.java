package com.staff.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.PinCodeDao;
import com.dao.ProblemDao;
import com.dao.SubProblemDao;
import com.db.DbConnect;

@WebServlet("/addPinCode")
public class AddPinCode extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String pinCodeName = req.getParameter("pincode_name");
			int zoneId = Integer.parseInt(req.getParameter("zone_id"));

			PinCodeDao dao = new PinCodeDao(DbConnect.getCon());

			HttpSession session = req.getSession();
			try {
				if (dao.checkDuplicatePinCode(pinCodeName)) {
					session.setAttribute("errorMsg", "Pincode name already exists. Please enter new one.");
					resp.sendRedirect("staff/superAdmin/pincodes.jsp");
				} else {
					boolean f = dao.addPinCode(pinCodeName, zoneId);
					if (f) {
						session.setAttribute("sucMsg", "Pincode added successfully.");
						resp.sendRedirect("staff/superAdmin/pincodes.jsp");
					} else {
						session.setAttribute("errorMsg", "Something went on server.");
						resp.sendRedirect("staff/superAdmin/pincodes.jsp");
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
