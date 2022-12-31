package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.entity.PinCode;
import com.entity.Problem;

public class PinCodeDao {

	private Connection con;

	public PinCodeDao(Connection con) {
		super();
		this.con = con;
	}

	public boolean addPinCode(String name, int zoneId) {
		boolean f = false;

		try {
			String sql = "insert into pincode (name, zoneId) values (?, ?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, name);
			ps.setInt(2, zoneId);
			int i = ps.executeUpdate();
			if (i == 1) {
				f = true;
			}

		} catch (Exception e) {
			e.printStackTrace();

		}
		return f;
	}

	public boolean checkDuplicatePinCode(String pinCodeName) throws SQLException {
		Statement st = con.createStatement();
		int count = 0;
		ResultSet rs = st.executeQuery("select * from pincode where name='" + pinCodeName + "'");
		while (rs.next()) {
			count++;
		}

		if (count > 0) {
			return true;
		} else {
			return false;
		}

	}

	public List<PinCode> getAllPinCodes() {

		List<PinCode> list = new ArrayList<PinCode>();
		PinCode p = null;
		try {

			String sql = "select p.pincodeId, p.name, z.name from pincode p join zone z  on p.zoneId = z.zoneId "
					+ "order by p.pincodeId desc";
			PreparedStatement ps = con.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				p = new PinCode();
				p.setPincodeId(rs.getInt(1));
				p.setName(rs.getString(2));
				p.setZoneName(rs.getString(3));
				list.add(p);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public PinCode getAllPinCodeById(int pincodeId) {

		PinCode p = null;
		try {

			String sql = "select p.pincodeId, p.name, z.name from pincode p join zone z  on p.zoneId = z.zoneId where "
					+ "p.pincodeId = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, pincodeId);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				p = new PinCode();
				p.setPincodeId(rs.getInt(1));
				p.setName(rs.getString(2));
				p.setZoneName(rs.getString(3));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return p;
	}

	public boolean updatePincode(PinCode sp) {
		boolean f = false;

		try {
			String sql = "update pincode set name=? where pincodeId=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, sp.getName());
			ps.setInt(2, sp.getPincodeId());

			int i = ps.executeUpdate();
			if (i == 1) {
				f = true;
			}

		} catch (Exception e) {
			e.printStackTrace();

		}
		return f;
	}

	public boolean deletePinCode(int pincodeId) {
		boolean f = false;

		try {
			String sql = "delete from pincode where pincodeId=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, pincodeId);

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
