package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.entity.Citizen;

public class CitizenDao {

	private Connection con;

	public CitizenDao(Connection con) {
		super();
		this.con = con;
	}

	public boolean CitizenRegister(Citizen citizen) {
		boolean f = false;
		try {
			String sql = "insert into citizen(firstName,lastName, userName, password, address, pinCode, mobile, email,"
					+ " gender, securityQuestionId, securityAnswer) values (?,?,?,?,?,?,?,?,?, ?, ?)";
			PreparedStatement ps = con.prepareStatement(sql);

			ps.setString(1, citizen.getFirstName());
			ps.setString(2, citizen.getLastName());
			ps.setString(3, citizen.getUserName());
			ps.setString(4, citizen.getPassword());
			ps.setString(5, citizen.getAddress());
			ps.setInt(6, citizen.getPinCode());
			ps.setString(7, citizen.getMobile());
			ps.setString(8, citizen.getEmail());
			ps.setString(9, citizen.getGender());
			ps.setInt(10, citizen.getQuestionId());
			ps.setString(11, citizen.getAnswer());

			int i = ps.executeUpdate();

			if (i == 1) {
				f = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return f;

	}

	public boolean updateCitizen(Citizen c) {
		boolean f = false;

		try {
			String sql = "update citizen set firstname=?, lastName=?, userName=?, address=?,"
					+ "pinCode=?, gender=?, securityQuestionId = ?, securityAnswer = ? where citizenId=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, c.getFirstName());
			ps.setString(2, c.getLastName());
			ps.setString(3, c.getUserName());
			ps.setString(4, c.getAddress());
			ps.setInt(5, c.getPinCode());
			ps.setString(6, c.getGender());
			ps.setInt(7, c.getQuestionId());
			ps.setString(8, c.getAnswer());
			ps.setInt(9, c.getId());

			int i = ps.executeUpdate();
			if (i == 1) {
				f = true;
			}

		} catch (Exception e) {
			e.printStackTrace();

		}
		return f;
	}

	public boolean checkDuplicateCitizen(Citizen citizen, String email, String mobile) throws SQLException {
		Statement st = con.createStatement();
		int count = 0, count1 = 0;
		ResultSet rs = st.executeQuery("select * from citizen where email='" + email + "'");
		while (rs.next()) {
			count++;
		}
		ResultSet rs1 = st.executeQuery("select * from citizen where mobile='" + mobile + "'");
		while (rs1.next()) {
			count1++;
		}
		if (count > 0 || count1 > 0) {
			return true;
		} else {
			return false;
		}

	}

	public Citizen getCitizenByEmailAndPassword(String email, String password) {
		Citizen citizen = null;

		try {

			String query = "select * from citizen where email =? and password=?";
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, email);
			pstmt.setString(2, password);

			ResultSet set = pstmt.executeQuery();

			if (set.next()) {
				citizen = new Citizen();

//	             set to user object
				citizen.setId(set.getInt("citizenId"));
				citizen.setFirstName(set.getString("firstName"));
				citizen.setLastName(set.getString("lastName"));
				citizen.setUserName(set.getString("userName"));
				citizen.setPassword(set.getString("password"));
				citizen.setAddress(set.getString("address"));
				citizen.setPinCode(set.getInt("pinCode"));
				citizen.setMobile(set.getString("mobile"));
				citizen.setEmail(set.getString("email"));
				citizen.setGender(set.getString("gender"));

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return citizen;
	}

	public Citizen getCitizenByEmail(String email) {
		Citizen citizen = null;

		try {

			String query = "select * from citizen where email =?";
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, email);

			ResultSet set = pstmt.executeQuery();

			if (set.next()) {
				citizen = new Citizen();

//	             set to user object
				citizen.setId(set.getInt("citizenId"));
				citizen.setFirstName(set.getString("firstName"));
				citizen.setLastName(set.getString("lastName"));
				citizen.setUserName(set.getString("userName"));
				citizen.setPassword(set.getString("password"));
				citizen.setAddress(set.getString("address"));
				citizen.setPinCode(set.getInt("pinCode"));
				citizen.setMobile(set.getString("mobile"));
				citizen.setEmail(set.getString("email"));
				citizen.setGender(set.getString("gender"));

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return citizen;
	}

	// get all users
	public List<Citizen> getAllCitizens() {
		List<Citizen> list = new ArrayList<Citizen>();
		try {
			PreparedStatement ps = con.prepareStatement("select * from citizen;");
			ResultSet set = ps.executeQuery();
			while (set.next()) {
				int citizenId = set.getInt("citizenId");
				String firstName = set.getString("firstName");
				String lastName = set.getString("lastName");
				String userName = set.getString("userName");
				String password = set.getString("password");
				String address = set.getString("address");
				int pinCode = set.getInt("pinCode");
				String mobile = set.getString("mobile");
				String email = set.getString("email");
				String gender = set.getString("gender");
				int securityQuestionId = set.getInt("securityQuestionId");
				String securityAnswer = set.getString("securityAnswer");

				Citizen u = new Citizen(citizenId, firstName, lastName, userName, password, address, pinCode, mobile,
						email, gender, securityQuestionId, securityAnswer);

				list.add(u);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// fetch all users
		return list;
	}

	public Citizen getCitizenByEmailSecurityQuestionAndPassword(String email, int questionId, String answer) {
		Citizen citizen = null;

		try {

			String query = "select * from citizen where email =? and securityQuestionId = ? and securityAnswer=?";
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, email);
			pstmt.setInt(2, questionId);
			pstmt.setString(3, answer);

			ResultSet set = pstmt.executeQuery();

			if (set.next()) {
				citizen = new Citizen();

//	             set to user object
				citizen.setId(set.getInt("citizenId"));
				citizen.setFirstName(set.getString("firstName"));
				citizen.setLastName(set.getString("lastName"));
				citizen.setUserName(set.getString("userName"));
				citizen.setPassword(set.getString("password"));
				citizen.setAddress(set.getString("address"));
				citizen.setPinCode(set.getInt("pinCode"));
				citizen.setMobile(set.getString("mobile"));
				citizen.setEmail(set.getString("email"));
				citizen.setGender(set.getString("gender"));
				citizen.setQuestionId(set.getInt("securityQuestionId"));
				citizen.setAnswer(set.getString("securityAnswer"));

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return citizen;
	}

	public Citizen getCitizenByMobile(String mobile) {
		Citizen citizen = null;

		try {

			String query = "select * from citizen where mobile =?";
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, mobile);

			ResultSet set = pstmt.executeQuery();

			if (set.next()) {
				citizen = new Citizen();

//	             set to user object
				citizen.setId(set.getInt("citizenId"));
				citizen.setFirstName(set.getString("firstName"));
				citizen.setLastName(set.getString("lastName"));
				citizen.setUserName(set.getString("userName"));
				citizen.setPassword(set.getString("password"));
				citizen.setAddress(set.getString("address"));
				citizen.setPinCode(set.getInt("pinCode"));
				citizen.setMobile(set.getString("mobile"));
				citizen.setEmail(set.getString("email"));
				citizen.setGender(set.getString("gender"));

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return citizen;
	}

	public boolean resetPassword(String password, int userId) {

		boolean f = false;
		try {

			String sql = "update citizen set password = ? where citizenId=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, password);
			ps.setInt(2, userId);

			int i = ps.executeUpdate();

			if (i == 1) {
				f = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return f;

	}

	public Citizen getCitizenById(int citizenId) {

		Citizen c = null;
		try {

			String sql = "select * from citizen where citizenId = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, citizenId);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				c = new Citizen();
				c.setId(rs.getInt("citizenId"));
				c.setFirstName(rs.getString("firstName"));
				c.setLastName(rs.getString("lastName"));
				c.setUserName(rs.getString("userName"));
				c.setPassword(rs.getString("password"));
				c.setAddress(rs.getString("address"));
				c.setPinCode(rs.getInt("pinCode"));
				c.setMobile(rs.getString("mobile"));
				c.setEmail(rs.getString("email"));
				c.setGender(rs.getString("gender"));
				c.setQuestionId(rs.getInt("securityQuestionId"));
				c.setAnswer(rs.getString("securityAnswer"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// fetch all users
		return c;
	}
}