package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.entity.Problem;
import com.entity.SubProblem;

public class SubProblemDao {

	private Connection con;

	public SubProblemDao(Connection con) {
		super();
		this.con = con;
	}

	public boolean addSubProblem(String type, int problemId) {
		boolean f = false;

		try {
			String sql = "insert into subproblem (type, problemId) values (?, ?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, type);
			ps.setInt(2, problemId);
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

			String sql = "select sp.subproblemId, sp.type, p.type from subproblem sp join problem p  on sp.problemId = p.problemId order by sp.subproblemId desc";
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

	public SubProblem getAllSubProblemById(int subproblemId) {

		SubProblem p = null;
		try {

			String sql = "select sp.subproblemId, sp.type, p.type from ccms.subproblem sp join ccms.problem p on sp.problemId = p.problemId where sp.subproblemId= ?";
			;
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, subproblemId);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				p = new SubProblem();
				p.setSubproblemId(rs.getInt(1));
				p.setType(rs.getString(2));
				p.setProblemType(rs.getString(3));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return p;
	}

	public List<SubProblem> getAllSubProblemByProblemId(int problemId) {
		List<SubProblem> list = new ArrayList<SubProblem>();
		SubProblem p = null;
		try {

			String sql = "select * from subproblem where problemId= ?";
			;
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, problemId);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				p = new SubProblem();
				p.setSubproblemId(rs.getInt(1));
				p.setType(rs.getString(2));
				p.setProblemId(rs.getInt(3));
				list.add(p);
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

	public boolean deleteSubProblem(int subproblemId) {
		boolean f = false;

		try {
			String sql = "delete from subproblem where subproblemId=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, subproblemId);

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
