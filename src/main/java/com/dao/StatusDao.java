package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.entity.Problem;
import com.entity.Status;

public class StatusDao {

	private Connection con;

	public StatusDao(Connection con) {
		super();
		this.con = con;
	}

	public Status getStatusById(int statusId) {

		Status p = null;
		try {

			String sql = "select * from status where statusId = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, statusId);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				p = new Status();
				p.setStatusId(rs.getInt(1));
				p.setName(rs.getString(2));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return p;
	}

	public List<Status> getInProgressDoneStatus() {

		List<Status> list = new ArrayList<Status>();
		Status s = null;
		try {

			String sql = "select * from status where statusId in (1,3) ";
			PreparedStatement ps = con.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				s = new Status();
				s.setStatusId(rs.getInt(1));
				s.setName(rs.getString(2));
				list.add(s);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public List<Status> getAllStatus() {

		List<Status> list = new ArrayList<Status>();
		Status s = null;
		try {

			String sql = "select * from status order by statusId desc";
			PreparedStatement ps = con.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				s = new Status();
				s.setStatusId(rs.getInt(1));
				s.setName(rs.getString(2));
				list.add(s);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public Status getFollowUpStatus() {

		Status s = null;
		try {

			String sql = "select * from status where statusId = 4";
			PreparedStatement ps = con.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				s = new Status();
				s.setStatusId(rs.getInt(1));
				s.setName(rs.getString(2));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return s;
	}
	
	public Status getPendingStatus() {

		Status s = null;
		try {

			String sql = "select * from status where statusId = 4";
			PreparedStatement ps = con.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				s = new Status();
				s.setStatusId(rs.getInt(1));
				s.setName(rs.getString(2));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return s;
	}
	
	
	public Status getReopenStatus() {

		Status s = null;
		try {

			String sql = "select * from status where statusId = 6";
			PreparedStatement ps = con.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				s = new Status();
				s.setStatusId(rs.getInt(1));
				s.setName(rs.getString(2));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return s;
	}
	
	


}
