/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.entity.EngineerMapping;
import com.entity.PinCode;

public class EngineerMappingDao {

	private Connection con;

	public EngineerMappingDao(Connection con) {
		this.con = con;
	}

	public boolean addEngineerMapping(int engineerId, int problemId, int zoneId) {
		boolean f = false;

		try {
			String sql = "insert into mapping (engineerId, problemId, zoneId) values (?, ?, ?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, engineerId);
			ps.setInt(2, problemId);
			ps.setInt(3, zoneId);
			int i = ps.executeUpdate();
			if (i == 1) {
				f = true;
			}

		} catch (Exception e) {
			e.printStackTrace();

		}
		return f;
	}

	public boolean deleteMapping(int userId) {
		boolean f = false;

		try {
			String sql = "delete from mapping where engineerId=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, userId);

			int i = ps.executeUpdate();
			if (i == 1) {
				f = true;
			}

		} catch (Exception e) {
			e.printStackTrace();

		}
		return f;
	}

	public EngineerMapping getEngineerByProblemIdAndZoneId(int problemId, int zoneId) {

		EngineerMapping em = null;
		try {

			String sql = "select engineerId from mapping where problemId = ? and zoneId = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, problemId);
			ps.setInt(2, zoneId);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				em = new EngineerMapping();
				em.setUserId(rs.getInt(1));

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return em;
	}
}
