package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.entity.Complaint;
import com.entity.Problem;
import com.entity.SubProblem;

public class ComplaintDao {

	private Connection con;

	public ComplaintDao(Connection con) {
		super();
		this.con = con;
	}

	public boolean addComplaintByCitizen(int statusId, int citizenId, int problemId, int subproblemId,
			String createdDateTime, String notes, String pre, String date, String complaintNo) {

		boolean f = false;

		try {
			String sql = "insert into complaint "
					+ "(statusId, citizenId, problemId, subproblemId, createdDateTime, notes, pre, date, complaintNo) "
					+ "values (?, ?, ?,?,?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, statusId);
			ps.setInt(2, citizenId);
			ps.setInt(3, problemId);
			ps.setInt(4, subproblemId);
			ps.setString(5, createdDateTime);
			ps.setString(6, notes);
			ps.setString(7, pre);
			ps.setString(8, date);
			ps.setString(9, complaintNo);

			int i = ps.executeUpdate();
			if (i == 1) {
				f = true;
			}

		} catch (Exception e) {
			e.printStackTrace();

		}
		return f;
	}

	public boolean addComplaintByStaff(int statusId, int citizenId, int userId, int zoneId, int problemId,
			int subproblemId, String createdDateTime, String notes, String pre, String date, String complaintNo,
			int createdBy) {

		boolean f = false;

		try {
			String sql = "insert into complaint "
					+ "(statusId, citizenId, userId, zoneId, problemId, subproblemId, createdDateTime, notes, pre, "
					+ "date, complaintNo, createdBy) values (?, ?, ?,?,?,?,?,?,?, ? ,?, ?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, statusId);
			ps.setInt(2, citizenId);
			ps.setInt(3, userId);
			ps.setInt(4, zoneId);
			ps.setInt(5, problemId);
			ps.setInt(6, subproblemId);
			ps.setString(7, createdDateTime);
			ps.setString(8, notes);
			ps.setString(9, pre);
			ps.setString(10, date);
			ps.setString(11, complaintNo);
			ps.setInt(12, createdBy);

			int i = ps.executeUpdate();
			if (i == 1) {
				f = true;
			}

		} catch (Exception e) {
			e.printStackTrace();

		}
		return f;
	}

	public boolean assignNewComplaint(int userId, int zoneId, int statusId, int complaintId) {

		boolean f = false;

		try {
			String sql = "update complaint set userId = ?, zoneId = ?, statusId = ? where complaintId = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, userId);
			ps.setInt(2, zoneId);
			ps.setInt(3, statusId);
			ps.setInt(4, complaintId);

			int i = ps.executeUpdate();
			if (i == 1) {
				f = true;
			}

		} catch (Exception e) {
			e.printStackTrace();

		}
		return f;
	}

	public boolean editComplaintStatus(int statusId, int complaintId) {

		boolean f = false;

		try {
			String sql = "update complaint set statusId = ? where complaintId = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, statusId);
			ps.setInt(2, complaintId);

			int i = ps.executeUpdate();
			if (i == 1) {
				f = true;
			}

		} catch (Exception e) {
			e.printStackTrace();

		}
		return f;
	}

	public boolean checkDuplicateSubProblem(String subProblemType, int problemId) throws SQLException {
		Statement st = con.createStatement();
		int count = 0;
		ResultSet rs = st.executeQuery(
				"select * from subproblem where type='" + subProblemType + "' and problemId='" + problemId + "'");
		while (rs.next()) {
			count++;
		}

		if (count > 0) {
			return true;
		} else {
			return false;
		}

	}

	public List<SubProblem> getAllSubProblems() {

		List<SubProblem> list = new ArrayList<SubProblem>();
		SubProblem p = null;
		try {

			String sql = "select sp.subproblemId, sp.type, p.type from subproblem sp join problem p  on "
					+ "sp.problemId = p.problemId order by sp.subproblemId desc";
			PreparedStatement ps = con.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				p = new SubProblem();
				p.setSubproblemId(rs.getInt(1));
				p.setType(rs.getString(2));
				p.setProblemType(rs.getString(3));
				list.add(p);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<Complaint> getAllComplaintsCreatedByStaffId(int staffId) {

		List<Complaint> list = new ArrayList<Complaint>();
		Complaint c = null;
		try {

			String sql = "select * from complaint where createdBy = ? order by createdDateTime desc";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, staffId);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				c = new Complaint();
				c.setComplaintId(rs.getInt(1));
				c.setStatusId(rs.getInt(2));
				c.setCitizenId(rs.getInt(3));
				c.setUserId(rs.getInt(4));
				c.setZoneId(rs.getInt(5));
				c.setProblemId(rs.getInt(6));
				c.setSubProblemId(rs.getInt(7));
				c.setCreatedDateTime(rs.getString(8));
				c.setNotes(rs.getString(9));
				c.setPre(rs.getString(10));
				c.setDate(rs.getString(11));
				c.setComplaintNo(rs.getString(12));
				list.add(c);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<Complaint> getAllComplaintsCreatedByCitizenId(int citizenId) {

		List<Complaint> list = new ArrayList<Complaint>();
		Complaint c = null;
		try {

			String sql = "select * from complaint where citizenId = ? order by createdDateTime desc";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, citizenId);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				c = new Complaint();
				c.setComplaintId(rs.getInt(1));
				c.setStatusId(rs.getInt(2));
				c.setCitizenId(rs.getInt(3));
				c.setUserId(rs.getInt(4));
				c.setZoneId(rs.getInt(5));
				c.setProblemId(rs.getInt(6));
				c.setSubProblemId(rs.getInt(7));
				c.setCreatedDateTime(rs.getString(8));
				c.setNotes(rs.getString(9));
				c.setPre(rs.getString(10));
				c.setDate(rs.getString(11));
				c.setComplaintNo(rs.getString(12));
				list.add(c);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public Complaint getComplaintByComplaintId(int complaintId) {

		Complaint c = null;
		try {

			String sql = "select * from complaint where complaintId = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, complaintId);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				c = new Complaint();
				c.setComplaintId(rs.getInt(1));
				c.setStatusId(rs.getInt(2));
				c.setCitizenId(rs.getInt(3));
				c.setUserId(rs.getInt(4));
				c.setZoneId(rs.getInt(5));
				c.setProblemId(rs.getInt(6));
				c.setSubProblemId(rs.getInt(7));
				c.setCreatedDateTime(rs.getString(8));
				c.setNotes(rs.getString(9));
				c.setPre(rs.getString(10));
				c.setDate(rs.getString(11));
				c.setComplaintNo(rs.getString(12));

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return c;
	}

	public List<Complaint> getZoneWiseComplaints(String fromDate, String toDate, String zoneId) {

		List<Complaint> list = new ArrayList<Complaint>();
		Complaint c = null;
		try {

			String sql = "SELECT * FROM complaint where date between ? and ? and zoneId = ? order by createdDateTime desc";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, fromDate);
			ps.setString(2, toDate);
			ps.setString(3, zoneId);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				c = new Complaint();
				c.setComplaintId(rs.getInt(1));
				c.setStatusId(rs.getInt(2));
				c.setCitizenId(rs.getInt(3));
				c.setUserId(rs.getInt(4));
				c.setZoneId(rs.getInt(5));
				c.setProblemId(rs.getInt(6));
				c.setSubProblemId(rs.getInt(7));
				c.setCreatedDateTime(rs.getString(8));
				c.setNotes(rs.getString(9));
				c.setPre(rs.getString(10));
				c.setDate(rs.getString(11));
				c.setComplaintNo(rs.getString(12));
				list.add(c);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<Complaint> getAllDoneComplaintsByCitizenId(int citizenId) {

		List<Complaint> list = new ArrayList<Complaint>();
		Complaint c = null;
		try {

			String sql = "select * from complaint where citizenId = ?  and statusId = 1 order by createdDateTime desc";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, citizenId);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				c = new Complaint();
				c.setComplaintId(rs.getInt(1));
				c.setStatusId(rs.getInt(2));
				c.setCitizenId(rs.getInt(3));
				c.setUserId(rs.getInt(4));
				c.setZoneId(rs.getInt(5));
				c.setProblemId(rs.getInt(6));
				c.setSubProblemId(rs.getInt(7));
				c.setCreatedDateTime(rs.getString(8));
				c.setNotes(rs.getString(9));
				c.setPre(rs.getString(10));
				c.setDate(rs.getString(11));
				c.setComplaintNo(rs.getString(12));
				list.add(c);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<Complaint> getAllDoneComplaints() {

		List<Complaint> list = new ArrayList<Complaint>();
		Complaint c = null;
		try {

			String sql = "select * from complaint where statusId = 1 order by createdDateTime desc";
			PreparedStatement ps = con.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				c = new Complaint();
				c.setComplaintId(rs.getInt(1));
				c.setStatusId(rs.getInt(2));
				c.setCitizenId(rs.getInt(3));
				c.setUserId(rs.getInt(4));
				c.setZoneId(rs.getInt(5));
				c.setProblemId(rs.getInt(6));
				c.setSubProblemId(rs.getInt(7));
				c.setCreatedDateTime(rs.getString(8));
				c.setNotes(rs.getString(9));
				c.setPre(rs.getString(10));
				c.setDate(rs.getString(11));
				c.setComplaintNo(rs.getString(12));
				list.add(c);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	
	public List<Complaint> getAllComplaintsCreatedByCitizen() {

		List<Complaint> list = new ArrayList<Complaint>();
		Complaint c = null;
		try {

			String sql = "select * from complaint where createdBy is null and userId is null";
			PreparedStatement ps = con.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				c = new Complaint();
				c.setComplaintId(rs.getInt(1));
				c.setStatusId(rs.getInt(2));
				c.setCitizenId(rs.getInt(3));
				c.setUserId(rs.getInt(4));
				c.setZoneId(rs.getInt(5));
				c.setProblemId(rs.getInt(6));
				c.setSubProblemId(rs.getInt(7));
				c.setCreatedDateTime(rs.getString(8));
				c.setNotes(rs.getString(9));
				c.setPre(rs.getString(10));
				c.setDate(rs.getString(11));
				c.setComplaintNo(rs.getString(12));
				list.add(c);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<Complaint> getAllComplaintsByEngineerId(String fromDate, String toDate, String engineerId) {

		List<Complaint> list = new ArrayList<Complaint>();
		Complaint c = null;
		try {

			String sql = "SELECT * FROM complaint where date between ? and ? and  userId = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, fromDate);
			ps.setString(2, toDate);
			ps.setString(3, engineerId);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				c = new Complaint();
				c.setComplaintId(rs.getInt(1));
				c.setStatusId(rs.getInt(2));
				c.setCitizenId(rs.getInt(3));
				c.setUserId(rs.getInt(4));
				c.setZoneId(rs.getInt(5));
				c.setProblemId(rs.getInt(6));
				c.setSubProblemId(rs.getInt(7));
				c.setCreatedDateTime(rs.getString(8));
				c.setNotes(rs.getString(9));
				c.setPre(rs.getString(10));
				c.setDate(rs.getString(11));
				c.setComplaintNo(rs.getString(12));
				list.add(c);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<Complaint> getAllComplaintsByStatusId(String fromDate, String toDate, String statusId) {

		List<Complaint> list = new ArrayList<Complaint>();
		Complaint c = null;
		try {

			String sql = "SELECT * FROM complaint where date between ? and ? and  statusId = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, fromDate);
			ps.setString(2, toDate);
			ps.setString(3, statusId);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				c = new Complaint();
				c.setComplaintId(rs.getInt(1));
				c.setStatusId(rs.getInt(2));
				c.setCitizenId(rs.getInt(3));
				c.setUserId(rs.getInt(4));
				c.setZoneId(rs.getInt(5));
				c.setProblemId(rs.getInt(6));
				c.setSubProblemId(rs.getInt(7));
				c.setCreatedDateTime(rs.getString(8));
				c.setNotes(rs.getString(9));
				c.setPre(rs.getString(10));
				c.setDate(rs.getString(11));
				c.setComplaintNo(rs.getString(12));
				list.add(c);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<Complaint> getAllComplaintsCreatedByCitizen(String fromDate, String toDate) {

		List<Complaint> list = new ArrayList<Complaint>();
		Complaint c = null;
		try {

			String sql = "SELECT * FROM complaint where date between ? and ? and createdBy is null";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, fromDate);
			ps.setString(2, toDate);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				c = new Complaint();
				c.setComplaintId(rs.getInt(1));
				c.setStatusId(rs.getInt(2));
				c.setCitizenId(rs.getInt(3));
				c.setUserId(rs.getInt(4));
				c.setZoneId(rs.getInt(5));
				c.setProblemId(rs.getInt(6));
				c.setSubProblemId(rs.getInt(7));
				c.setCreatedDateTime(rs.getString(8));
				c.setNotes(rs.getString(9));
				c.setPre(rs.getString(10));
				c.setDate(rs.getString(11));
				c.setComplaintNo(rs.getString(12));
				list.add(c);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<Complaint> getAllComplaintsCreatedByOperator(String fromDate, String toDate) {

		List<Complaint> list = new ArrayList<Complaint>();
		Complaint c = null;
		try {

			String sql = "SELECT * FROM complaint where date between ? and ? and createdBy is not null";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, fromDate);
			ps.setString(2, toDate);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				c = new Complaint();
				c.setComplaintId(rs.getInt(1));
				c.setStatusId(rs.getInt(2));
				c.setCitizenId(rs.getInt(3));
				c.setUserId(rs.getInt(4));
				c.setZoneId(rs.getInt(5));
				c.setProblemId(rs.getInt(6));
				c.setSubProblemId(rs.getInt(7));
				c.setCreatedDateTime(rs.getString(8));
				c.setNotes(rs.getString(9));
				c.setPre(rs.getString(10));
				c.setDate(rs.getString(11));
				c.setComplaintNo(rs.getString(12));
				list.add(c);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public List<Complaint> getAllPendingComplaints24Hours() {

		List<Complaint> list = new ArrayList<Complaint>();
		Complaint c = null;
		try {

			String sql = "select * from complaint where createdDateTime<= now() - interval 24 hour and statusId = 2;";
			PreparedStatement ps = con.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				c = new Complaint();
				c.setComplaintId(rs.getInt(1));
				c.setStatusId(rs.getInt(2));
				c.setCitizenId(rs.getInt(3));
				c.setUserId(rs.getInt(4));
				c.setZoneId(rs.getInt(5));
				c.setProblemId(rs.getInt(6));
				c.setSubProblemId(rs.getInt(7));
				c.setCreatedDateTime(rs.getString(8));
				c.setNotes(rs.getString(9));
				c.setPre(rs.getString(10));
				c.setDate(rs.getString(11));
				c.setComplaintNo(rs.getString(12));
				list.add(c);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<Complaint> getAllPendingComplaintsAssignedToEngineer(int engineerId) {

		List<Complaint> list = new ArrayList<Complaint>();
		Complaint c = null;
		try {

			String sql = "SELECT * FROM ccms.complaint where userId = ? and statusId = 2";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, engineerId);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				c = new Complaint();
				c.setComplaintId(rs.getInt(1));
				c.setStatusId(rs.getInt(2));
				c.setCitizenId(rs.getInt(3));
				c.setUserId(rs.getInt(4));
				c.setZoneId(rs.getInt(5));
				c.setProblemId(rs.getInt(6));
				c.setSubProblemId(rs.getInt(7));
				c.setCreatedDateTime(rs.getString(8));
				c.setNotes(rs.getString(9));
				c.setPre(rs.getString(10));
				c.setDate(rs.getString(11));
				c.setComplaintNo(rs.getString(12));
				c.setCreatedBy(rs.getInt(13));
				list.add(c);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<Complaint> getAllFollowUpComplaints(String fromDate, String toDate) {

		List<Complaint> list = new ArrayList<Complaint>();
		Complaint c = null;
		try {

			String sql = "SELECT * FROM complaint WHERE date between ? and ? and statusId = 4";

			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, fromDate);
			ps.setString(2, toDate);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				c = new Complaint();
				c.setComplaintId(rs.getInt(1));
				c.setStatusId(rs.getInt(2));
				c.setCitizenId(rs.getInt(3));
				c.setUserId(rs.getInt(4));
				c.setZoneId(rs.getInt(5));
				c.setProblemId(rs.getInt(6));
				c.setSubProblemId(rs.getInt(7));
				c.setCreatedDateTime(rs.getString(8));
				c.setNotes(rs.getString(9));
				c.setPre(rs.getString(10));
				c.setDate(rs.getString(11));
				c.setComplaintNo(rs.getString(12));
				c.setCreatedBy(rs.getInt(13));
				list.add(c);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<Complaint> getAllFollowUpComplaints() {

		List<Complaint> list = new ArrayList<Complaint>();
		Complaint c = null;
		try {

			String sql = "SELECT * FROM complaint WHERE statusId = 4";

			PreparedStatement ps = con.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				c = new Complaint();
				c.setComplaintId(rs.getInt(1));
				c.setStatusId(rs.getInt(2));
				c.setCitizenId(rs.getInt(3));
				c.setUserId(rs.getInt(4));
				c.setZoneId(rs.getInt(5));
				c.setProblemId(rs.getInt(6));
				c.setSubProblemId(rs.getInt(7));
				c.setCreatedDateTime(rs.getString(8));
				c.setNotes(rs.getString(9));
				c.setPre(rs.getString(10));
				c.setDate(rs.getString(11));
				c.setComplaintNo(rs.getString(12));
				c.setCreatedBy(rs.getInt(13));
				list.add(c);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<Complaint> getAllInProgressComplaintsAssignedToEngineer(int engineerId) {

		List<Complaint> list = new ArrayList<Complaint>();
		Complaint c = null;
		try {

			String sql = "SELECT * FROM ccms.complaint where userId = ? and statusId = 3";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, engineerId);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				c = new Complaint();
				c.setComplaintId(rs.getInt(1));
				c.setStatusId(rs.getInt(2));
				c.setCitizenId(rs.getInt(3));
				c.setUserId(rs.getInt(4));
				c.setZoneId(rs.getInt(5));
				c.setProblemId(rs.getInt(6));
				c.setSubProblemId(rs.getInt(7));
				c.setCreatedDateTime(rs.getString(8));
				c.setNotes(rs.getString(9));
				c.setPre(rs.getString(10));
				c.setDate(rs.getString(11));
				c.setComplaintNo(rs.getString(12));
				c.setCreatedBy(rs.getInt(13));
				list.add(c);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<Complaint> getAllReopenComplaintsAssignedToEngineer(int engineerId) {

		List<Complaint> list = new ArrayList<Complaint>();
		Complaint c = null;
		try {

			String sql = "SELECT * FROM ccms.complaint where userId = ? and statusId = 6";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, engineerId);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				c = new Complaint();
				c.setComplaintId(rs.getInt(1));
				c.setStatusId(rs.getInt(2));
				c.setCitizenId(rs.getInt(3));
				c.setUserId(rs.getInt(4));
				c.setZoneId(rs.getInt(5));
				c.setProblemId(rs.getInt(6));
				c.setSubProblemId(rs.getInt(7));
				c.setCreatedDateTime(rs.getString(8));
				c.setNotes(rs.getString(9));
				c.setPre(rs.getString(10));
				c.setDate(rs.getString(11));
				c.setComplaintNo(rs.getString(12));
				c.setCreatedBy(rs.getInt(13));
				list.add(c);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<Complaint> getAllFollowUpComplaintsAssignedToEngineer(int engineerId) {

		List<Complaint> list = new ArrayList<Complaint>();
		Complaint c = null;
		try {

			String sql = "SELECT * FROM ccms.complaint where userId = ? and statusId = 4";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, engineerId);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				c = new Complaint();
				c.setComplaintId(rs.getInt(1));
				c.setStatusId(rs.getInt(2));
				c.setCitizenId(rs.getInt(3));
				c.setUserId(rs.getInt(4));
				c.setZoneId(rs.getInt(5));
				c.setProblemId(rs.getInt(6));
				c.setSubProblemId(rs.getInt(7));
				c.setCreatedDateTime(rs.getString(8));
				c.setNotes(rs.getString(9));
				c.setPre(rs.getString(10));
				c.setDate(rs.getString(11));
				c.setComplaintNo(rs.getString(12));
				c.setCreatedBy(rs.getInt(13));
				list.add(c);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<Complaint> getAllDoneComplaintsAssignedToEngineer(int engineerId) {

		List<Complaint> list = new ArrayList<Complaint>();
		Complaint c = null;
		try {

			String sql = "SELECT * FROM ccms.complaint where userId = ? and statusId = 1";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, engineerId);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				c = new Complaint();
				c.setComplaintId(rs.getInt(1));
				c.setStatusId(rs.getInt(2));
				c.setCitizenId(rs.getInt(3));
				c.setUserId(rs.getInt(4));
				c.setZoneId(rs.getInt(5));
				c.setProblemId(rs.getInt(6));
				c.setSubProblemId(rs.getInt(7));
				c.setCreatedDateTime(rs.getString(8));
				c.setNotes(rs.getString(9));
				c.setPre(rs.getString(10));
				c.setDate(rs.getString(11));
				c.setComplaintNo(rs.getString(12));
				c.setCreatedBy(rs.getInt(13));
				list.add(c);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<Problem> getProblemsBySearch(String type) {

		List<Problem> list = new ArrayList<Problem>();
		Problem p = null;
		try {

			String sql = "select * from problem where type like '%" + type + "%';";
			PreparedStatement ps = con.prepareStatement(sql);
//			ps.setString(1, type);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				p = new Problem();
				p.setProblemId(rs.getInt(1));
				p.setType(rs.getString(2));
				list.add(p);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public boolean updateSubProblem(SubProblem sp) {
		boolean f = false;

		try {
			String sql = "update subproblem set type=? where subproblemId=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, sp.getType());
			ps.setInt(2, sp.getSubproblemId());

			int i = ps.executeUpdate();
			if (i == 1) {
				f = true;
			}

		} catch (Exception e) {
			e.printStackTrace();

		}
		return f;
	}

	public boolean deleteComplaint(int complaintId) {
		boolean f = false;

		try {
			String sql = "delete from complaint where complaintId=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, complaintId);

			int i = ps.executeUpdate();
			if (i == 1) {
				f = true;
			}

		} catch (Exception e) {
			e.printStackTrace();

		}
		return f;
	}

	public boolean addComplaintByCitizen(Complaint complaint) {
		// TODO Auto-generated method stub
		return false;
	}

}
