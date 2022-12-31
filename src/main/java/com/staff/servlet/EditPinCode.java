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
import com.dao.SubProblemDao;
import com.db.DbConnect;
import com.entity.PinCode;
import com.entity.SubProblem;

@WebServlet("/editPinCode")
public class EditPinCode extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String pinCodeIdString = req.getParameter("pincode_id");
			int pinCodeId = Integer.parseInt(pinCodeIdString);
			String pinCodeName = req.getParameter("pincode_name");

			PinCode p = new PinCode(pinCodeId, pinCodeName);
			PinCodeDao dao = new PinCodeDao(DbConnect.getCon());

			HttpSession session = req.getSession();
			try {
				if (dao.checkDuplicatePinCode(pinCodeName)) {
					session.setAttribute("errorMsg", "Pin Code already exists. Please enter new one.");
					resp.sendRedirect("staff/superAdmin/pincodes.jsp");
				} else {
					boolean f = dao.updatePincode(p);
					if (f) {
						session.setAttribute("sucMsg", "Pin Code updated successfully.");
						resp.sendRedirect("staff/superAdmin/pincodes.jsp");
					} else {
						session.setAttribute("errorMsg", "Something went on server.");
						resp.sendRedirect("staff/superAdmin/pincodes.jsp");
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
