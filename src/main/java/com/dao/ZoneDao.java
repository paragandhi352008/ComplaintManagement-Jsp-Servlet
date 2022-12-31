/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.entity.EngineerMapping;
import com.entity.Role;
import com.entity.Zone;

public class ZoneDao {

	private Connection con;

	public ZoneDao(Connection con) {
		this.con = con;
	}

	public List<Zone> getAllZones() {

		List<Zone> list = new ArrayList<Zone>();
		Zone z = null;
		try {

			String sql = "select * from zone;";
			PreparedStatement ps = con.prepareStatement(sql);

			ResultSet rs = ps.executeQuery(sql);
			while (rs.next()) {
				z = new Zone();
				z.setZoneId(rs.getInt(1));
				z.setName(rs.getString(2));
				list.add(z);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
	
	public Zone getZoneByZoneId(int zoneId) {

		Zone z = null;
		try {

			String sql = "select * from zone where zoneId = ?;";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, zoneId);

			ResultSet rs = ps.executeQuery(sql);
			while (rs.next()) {
				z = new Zone();
				z.setZoneId(rs.getInt(1));
				z.setName(rs.getString(2));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return z;
	}

	public Zone getZoneByPinCode(String pinCode) {

		Zone z = null;
		try {

			String sql = "SELECT z.zoneId, z.name FROM zone z join pincode p on z.zoneId = p.zoneId  where p.name = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, pinCode);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				z = new Zone();
				z.setZoneId(rs.getInt(1));
				z.setName(rs.getString(2));

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return z;
	}

	public Zone getZoneByPinCode(int pinCode) {

		Zone z = null;
		try {

			String sql = "SELECT z.zoneId, z.name FROM zone z join pincode p on z.zoneId = p.zoneId  where p.name = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, pinCode);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				z = new Zone();
				z.setZoneId(rs.getInt(1));
				z.setName(rs.getString(2));

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return z;
	}
}