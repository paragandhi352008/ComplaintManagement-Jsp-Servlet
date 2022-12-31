package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DashboardDao {

	private Connection con;

	public DashboardDao(Connection con) {
		super();
		this.con = con;
	}

	public int countPendingComplaintsByCitizenId(int citizenId) {

		int i = 0;
		try {

			String sql = "select * from complaint where citizenId = ? and statusId = 2";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, citizenId);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}

	public int countTotalComplaintsByCitizenId(int citizenId) {

		int i = 0;
		try {

			String sql = "select * from complaint where citizenId = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, citizenId);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}

	public int countPendingComplaintsByStaffId(int staffId) {

		int i = 0;
		try {

			String sql = "select * from complaint where userId = ? and statusId = 2";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, staffId);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}

	public int countPendingComplaints() {

		int i = 0;
		try {

			String sql = "select * from complaint where statusId = 2";
			PreparedStatement ps = con.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}

	public int countDoneComplaintsByStaffId(int staffId) {

		int i = 0;
		try {

			String sql = "select * from complaint where userId = ? and statusId = 1";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, staffId);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}

	public int countDoneComplaints() {

		int i = 0;
		try {

			String sql = "select * from complaint where statusId = 1";
			PreparedStatement ps = con.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}

	public int countInProgressComplaintsByStaffId(int staffId) {

		int i = 0;
		try {

			String sql = "select * from complaint where userId = ? and statusId = 3";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, staffId);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}

	public int countInProgressComplaints() {

		int i = 0;
		try {

			String sql = "select * from complaint where statusId = 3";
			PreparedStatement ps = con.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}

	public int countFollowUpComplaintsByStaffId(int staffId) {

		int i = 0;
		try {

			String sql = "select * from complaint where userId = ? and statusId = 4";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, staffId);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}

	public int countFollowUpComplaints() {

		int i = 0;
		try {

			String sql = "select * from complaint where statusId = 4";
			PreparedStatement ps = con.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}

	public int countReopenComplaintsByStaffId(int staffId) {

		int i = 0;
		try {

			String sql = "select * from complaint where userId = ? and statusId = 6";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, staffId);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}

	public int countReopenComplaints() {

		int i = 0;
		try {

			String sql = "select * from complaint where statusId = 6";
			PreparedStatement ps = con.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}

	public int countTodaysComplaintsByOperator(int staffId) {

		int i = 0;
		try {

			String sql = "SELECT * FROM complaint where date = cast(Date(Now()) as Date) and createdBy = ?;";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, staffId);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}

	public int countTodaysComplaints() {

		int i = 0;
		try {

			String sql = "SELECT * FROM complaint where date = cast(Date(Now()) as Date);";
			PreparedStatement ps = con.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}

	public int countOperators() {

		int i = 0;
		try {

			String sql = "select * from users where roleId=4;";
			PreparedStatement ps = con.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}
	
	public int countEngineers() {

		int i = 0;
		try {

			String sql = "select * from users where roleId=2;";
			PreparedStatement ps = con.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}

	public int countLast7DaysComplaintsByOperator(int staffId) {

		int i = 0;
		try {

			String sql = "select * from complaint where date > now() - INTERVAL 7 day and createdBy = ?;";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, staffId);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}

	public int countLast7DaysComplaints() {

		int i = 0;
		try {

			String sql = "select * from complaint where date > now() - INTERVAL 7 day;";
			PreparedStatement ps = con.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}

	public int countLast30DaysComplaintsByOperator(int staffId) {

		int i = 0;
		try {

			String sql = "select * from complaint where date > now() - INTERVAL 30 day and createdBy = ?;";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, staffId);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}

	public int countLast30DaysComplaints() {

		int i = 0;
		try {

			String sql = "select * from complaint where date > now() - INTERVAL 30 day;";
			PreparedStatement ps = con.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}

	public int countTotalComplaintsByOperator(int staffId) {

		int i = 0;
		try {

			String sql = "select * from complaint where createdBy = ?;";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, staffId);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}

	public int countTotalComplaints() {

		int i = 0;
		try {

			String sql = "select * from complaint;";
			PreparedStatement ps = con.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}
}
