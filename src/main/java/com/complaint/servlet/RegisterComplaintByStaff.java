package com.complaint.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.CitizenDao;
import com.dao.EngineerMappingDao;
import com.dao.ProblemDao;
import com.dao.StaffDao;
import com.dao.ZoneDao;
import com.db.DbConnect;
import com.entity.Citizen;
import com.entity.EngineerMapping;
import com.entity.Problem;
import com.entity.Staff;
import com.entity.Zone;

@WebServlet("/registerComplaintByStaff")
public class RegisterComplaintByStaff extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			// Get parameter values from .jsp file.
			int citizenId = Integer.parseInt(req.getParameter("citizen_id"));
			int problemId = Integer.parseInt(req.getParameter("problem_id"));
			String pinCode = req.getParameter("citizen_pincode");

			ProblemDao dao = new ProblemDao(DbConnect.getCon());
			CitizenDao dao2 = new CitizenDao(DbConnect.getCon());

			HttpSession session = req.getSession();

			Problem problem = dao.getAllProblemById(problemId);
			Citizen citizen = dao2.getCitizenById(citizenId);

			ZoneDao dao4 = new ZoneDao(DbConnect.getCon());
			Zone zone = dao4.getZoneByPinCode(pinCode);
			EngineerMappingDao dao5 = new EngineerMappingDao(DbConnect.getCon());
			EngineerMapping em = dao5.getEngineerByProblemIdAndZoneId(problemId, zone.getZoneId());
			StaffDao dao6 = new StaffDao(DbConnect.getCon());
			Staff engineer = dao6.getEngineerById(em.getUserId());

			session.setAttribute("problemObj", problem);
			session.setAttribute("citizenObj", citizen);
			session.setAttribute("engineerObj", engineer);
			session.setAttribute("zoneObj", zone);

			resp.sendRedirect("staff/operator/registerComplaint.jsp?citizenId=" + citizenId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
