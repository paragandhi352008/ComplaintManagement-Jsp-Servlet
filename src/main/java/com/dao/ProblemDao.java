package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.entity.Problem;

public class ProblemDao {

	private Connection con;

	public ProblemDao(Connection con) {
		super();
		this.con = con;
	}

	public boolean addProblem(String type) {
		boolean f = false;

		try {
			String sql = "insert into problem(type) values (?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, type);

			int i = ps.executeUpdate();
			if (i == 1) {
				f = true;
			}

		} catch (Exception e) {
			e.printStackTrace();

		}
		return f;
	}

	public boolean checkDuplicateProblem(String problemType) throws SQLException {
		Statement st = con.createStatement();
		int count = 0;
		ResultSet rs = st.executeQuery("select * from problem where type='" + problemType + "'");
		while (rs.next()) {
			count++;
		}

		if (count > 0) {
			return true;
		} else {
			return false;
		}

	}

	public List<Problem> getAllProblems() {

		List<Problem> list = new ArrayList<Problem>();
		Problem p = null;
		try {

			String sql = "select * from problem order by problemId desc";
			PreparedStatement ps = con.prepareStatement(sql);

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

	public List<Problem> getAllProblemsForEngineerMapping() {

		List<Problem> list = new ArrayList<Problem>();
		Problem p = null;
		try {

//			String sql = "select * from problem p where p.problemId not in (select m.problemId from mapping m) order by problemId desc";
			String sql = "select * from problem p where p.problemId not in (select m.problemId from  mapping m, mapping m1 where m.problemId = m.problemId having count(m.zoneId) <> 4) order by problemId desc";
			PreparedStatement ps = con.prepareStatement(sql);

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

	public Problem getAllProblemById(int problemId) {

		Problem p = null;
		try {

			String sql = "select * from problem where problemId = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, problemId);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				p = new Problem();
				p.setProblemId(rs.getInt(1));
				p.setType(rs.getString(2));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return p;
	}

	public List<Problem> getAllProblemWithSubProblemAndMapped() {

		List<Problem> list = new ArrayList<Problem>();
		Problem p = null;
		try {

			String sql = "select * from problem where problemId in (select problemId from subproblem) and problemId in (select problemId from mapping)";
			PreparedStatement ps = con.prepareStatement(sql);

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

	public Problem getProblemByEngineer(int userId) {

		Problem p = null;
		try {

			String sql = "select p.type FROM mapping m join problem p on m.problemId = p.problemId where m.engineerId = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, userId);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				p = new Problem();
				p.setProblemId(rs.getInt(1));
				p.setType(rs.getString(2));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return p;
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

	public boolean updateProblem(Problem p) {
		boolean f = false;

		try {
			String sql = "update problem set type=? where problemId=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, p.getType());
			ps.setInt(2, p.getProblemId());

			int i = ps.executeUpdate();
			if (i == 1) {
				f = true;
			}

		} catch (Exception e) {
			e.printStackTrace();

		}
		return f;
	}

	public boolean deleteProblem(int problemId) {
		boolean f = false;

		try {
			String sql = "delete from problem where problemId=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, problemId);

			int i = ps.executeUpdate();
			if (i == 1) {
				f = true;
			}

		} catch (Exception e) {
			e.printStackTrace();

		}
		return f;
	}

}
