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

import com.entity.Role;

public class RoleDao {

	private Connection con;

	public RoleDao(Connection con) {
		this.con = con;
	}

	public List<Role> getAllRoles() {

		List<Role> list = new ArrayList<Role>();
		Role r = null;
		try {

			String sql = "select * from roles;";
			PreparedStatement ps = con.prepareStatement(sql);

			ResultSet rs = ps.executeQuery(sql);
			while (rs.next()) {
				r = new Role();
				r.setRoleId(rs.getInt(1));
				r.setType(rs.getString(2));
				list.add(r);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public List<Role> getAllRolesWithoutSuperAdmin() {

		List<Role> list = new ArrayList<Role>();
		Role r = null;
		try {

			String sql = "select * from roles where roleId<>1;";
			PreparedStatement ps = con.prepareStatement(sql);

			ResultSet rs = ps.executeQuery(sql);
			while (rs.next()) {
				r = new Role();
				r.setRoleId(rs.getInt(1));
				r.setType(rs.getString(2));
				list.add(r);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public Role getEngineerRole() {

		Role r = null;
		try {

			String sql = "select * from roles where roleId = 2;";
			PreparedStatement ps = con.prepareStatement(sql);

			ResultSet rs = ps.executeQuery(sql);
			while (rs.next()) {
				r = new Role();
				r.setRoleId(rs.getInt(1));
				r.setType(rs.getString(2));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return r;
	}
	
	public Role getOperatorRole() {

		Role r = null;
		try {

			String sql = "select * from roles where roleId = 4;";
			PreparedStatement ps = con.prepareStatement(sql);

			ResultSet rs = ps.executeQuery(sql);
			while (rs.next()) {
				r = new Role();
				r.setRoleId(rs.getInt(1));
				r.setType(rs.getString(2));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return r;
	}
}