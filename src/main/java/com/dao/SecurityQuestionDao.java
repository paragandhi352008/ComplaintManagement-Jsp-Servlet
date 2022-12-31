package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.entity.SubProblem;
import com.entity.PinCode;
import com.entity.Problem;
import com.entity.SecurityQuestion;

public class SecurityQuestionDao {

	private Connection con;

	public SecurityQuestionDao(Connection con) {
		super();
		this.con = con;
	}

	public List<SecurityQuestion> getAllSecurityQuestions() {

		List<SecurityQuestion> list = new ArrayList<SecurityQuestion>();
		SecurityQuestion p = null;
		try {

			String sql = "select * from security_question order by questionsId desc";
			PreparedStatement ps = con.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				p = new SecurityQuestion();
				p.setQuestionsId(rs.getInt(1));
				p.setQuestionsName(rs.getString(2));
				
				list.add(p);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public SecurityQuestion getSecurityQuestionByQuestionId(int questionId) {

		SecurityQuestion sq = null;
		
		try {

			String sql = "select * from security_question where questionsId = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, questionId);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				sq = new SecurityQuestion();
				sq.setQuestionsId(rs.getInt(1));
				sq.setQuestionsName(rs.getString(2));

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sq;
	}

	public PinCode getAllPinCodeById(int pincodeId) {

		PinCode p = null;
		try {

			String sql = "select p.pincodeId, p.name, z.name from pincode p join zone z  on p.zoneId = z.zoneId where p.pincodeId = ?";
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

	
}
