package com.staff.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.PinCodeDao;
import com.dao.SubProblemDao;
import com.db.DbConnect;

@WebServlet("/deletePinCode")
public class DeletePinCode extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			int pincodeId = Integer.parseInt(req.getParameter("pincodeId"));
			PinCodeDao dao = new PinCodeDao(DbConnect.getCon());

			HttpSession session = req.getSession();
			try {

				boolean f = dao.deletePinCode(pincodeId);
				if (f) {
					session.setAttribute("sucMsg", "Pin Code deleted successfully.");
					resp.sendRedirect("staff/superAdmin/pincodes.jsp");
				} else {
					session.setAttribute("errorMsg", "Something went on server.");
					resp.sendRedirect("staff/superAdmin/pincodes.jsp");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
