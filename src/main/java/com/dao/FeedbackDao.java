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

public class FeedbackDao {

	private Connection con;

	public FeedbackDao(Connection con) {
		this.con = con;
	}
	
	public boolean addFeedback(String answer01, String answer02, String answer03, int citizenId) {
		boolean f = false;

		try {
			String sql = "insert into feedback (answer01, answer02, answer03, citizenId) values (?, ?, ?, ?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, answer01);
			ps.setString(2, answer02);
			ps.setString(3, answer03);
			ps.setInt(4, citizenId);
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