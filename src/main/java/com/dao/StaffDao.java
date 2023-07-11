/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.entity.Staff;
import com.entity.SubProblem;
import com.entity.Citizen;
import com.entity.PinCode;
import com.entity.Role;
import com.entity.SecurityQuestion;

public class StaffDao {

	private Connection con;

	public StaffDao(Connection con) {
		this.con = con;
	}

	public boolean checkDuplicateUser(Staff user, String email, String mobile) throws SQLException {
		Statement st = con.createStatement();
		int count = 0, count1 = 0;
		ResultSet rs = st.executeQuery("select * from users where email='" + email + "'");
		while (rs.next()) {
			count++;
		}
		ResultSet rs1 = st.executeQuery("select * from users where mobile='" + mobile + "'");
		while (rs1.next()) {
			count1++;
		}
		if (count > 0 || count1 > 0) {
			return true;
		} else {
			return false;
		}

	}

	// method to insert user to data base:
	public boolean saveuser(Staff user) {
		boolean f = false;
		try {
			// user -->database

			String query = "insert into users(firstName,lastName, userName, password, address, pinCode, mobile, email, gender, isActive, roleId) values (?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement pstmt = this.con.prepareStatement(query);
			pstmt.setString(1, user.getFirstName());
			pstmt.setString(2, user.getLastName());
			pstmt.setString(3, user.getUserName());
			pstmt.setString(4, user.getPassword());
			pstmt.setString(5, user.getAddress());
			pstmt.setInt(6, user.getPinCode());
			pstmt.setString(7, user.getMobile());
			pstmt.setString(8, user.getEmail());
			pstmt.setString(9, user.getGender());
			pstmt.setBoolean(10, true);
			pstmt.setInt(11, user.getRoleId());

			pstmt.executeUpdate();
			f = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return f;

	}

	// get user by useremail and userpassword:
	public Staff getStaffByEmailPasswordAndRole(String email, String password, int roleId) {
		Staff staff = null;

		try {

			String query = "select * from users where email =? and password=? and roleId=?";
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, email);
			pstmt.setString(2, password);
			pstmt.setInt(3, roleId);

			ResultSet set = pstmt.executeQuery();

			if (set.next()) {
				staff = new Staff();

//	             set to user object
				staff.setUserId(set.getInt("userId"));
				staff.setFirstName(set.getString("firstName"));
				staff.setLastName(set.getString("lastName"));
				staff.setUserName(set.getString("userName"));
				staff.setPassword(set.getString("password"));
				staff.setAddress(set.getString("address"));
				staff.setPinCode(set.getInt("pinCode"));
				staff.setMobile(set.getString("mobile"));
				staff.setEmail(set.getString("email"));
				staff.setGender(set.getString("gender"));
				staff.setActive(set.getBoolean("isActive"));
				staff.setRoleId(set.getInt("roleId"));

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Staff is:" + staff);
		return staff;
	}

	public Staff getStaffByStaffId(int staffId) {
		Staff staff = null;

		try {

			String query = "select * from users where userId=?";
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, staffId);

			ResultSet set = pstmt.executeQuery();

			if (set.next()) {
				staff = new Staff();

//	             set to user object
				staff.setUserId(set.getInt("userId"));
				staff.setFirstName(set.getString("firstName"));
				staff.setLastName(set.getString("lastName"));
				staff.setUserName(set.getString("userName"));
				staff.setPassword(set.getString("password"));
				staff.setAddress(set.getString("address"));
				staff.setPinCode(set.getInt("pinCode"));
				staff.setMobile(set.getString("mobile"));
				staff.setEmail(set.getString("email"));
				staff.setGender(set.getString("gender"));
				staff.setActive(set.getBoolean("isActive"));
				staff.setRoleId(set.getInt("roleId"));

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return staff;
	}

	public Staff getStaffByEmailAndPassword(String email, String password) {
		Staff staff = null;

		try {

			String query = "select * from users where email =? and password=?";
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, email);
			pstmt.setString(2, password);

			ResultSet set = pstmt.executeQuery();

			if (set.next()) {
				staff = new Staff();

//	             set to user object
				staff.setUserId(set.getInt("userId"));
				staff.setFirstName(set.getString("firstName"));
				staff.setLastName(set.getString("lastName"));
				staff.setUserName(set.getString("userName"));
				staff.setPassword(set.getString("password"));
				staff.setAddress(set.getString("address"));
				staff.setPinCode(set.getInt("pinCode"));
				staff.setMobile(set.getString("mobile"));
				staff.setEmail(set.getString("email"));
				staff.setGender(set.getString("gender"));
				staff.setActive(set.getBoolean("isActive"));
				staff.setRoleId(set.getInt("roleId"));

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return staff;
	}

	public int getStaffRoleByEmail(String email) {
		int roleId = 0;
		try {

			String sql = "select roleId from users where email = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, email);

			ResultSet set = pstmt.executeQuery();
			if (set.next()) {
				String roleIdString = set.getString("roleId");
				roleId = Integer.parseInt(roleIdString);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return roleId;
	}

	public int getStaffRoleByStaffId(int staffId) {
		int roleId = 0;
		try {

			String sql = "select roleId from users where userId = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, staffId);

			ResultSet set = pstmt.executeQuery();
			if (set.next()) {
				String roleIdString = set.getString("roleId");
				roleId = Integer.parseInt(roleIdString);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return roleId;
	}

	public boolean resetPassword(String password, int userId) {

		boolean f = false;
		try {

			String sql = "update users set password = ? where userId=?";
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

	// get all users
	public List<Staff> getAllUsers() {
		List<Staff> list = new ArrayList<Staff>();
		try {
			PreparedStatement ps = con.prepareStatement("select * from users;");
			ResultSet set = ps.executeQuery();
			while (set.next()) {
				int userId = set.getInt("userId");
				String firstName = set.getString("firstName");
				String lastName = set.getString("lastName");
				String userName = set.getString("userName");
				String password = set.getString("password");
				String address = set.getString("address");
				int pinCode = set.getInt("pinCode");
				String mobile = set.getString("mobile");
				String email = set.getString("email");
				String gender = set.getString("gender");
				Boolean isActive = set.getBoolean("isActive");
				int roleId = set.getInt("roleId");

				Staff u = new Staff(userId, firstName, lastName, userName, password, address, pinCode, mobile, email,
						gender, isActive, roleId);

				list.add(u);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// fetch all users
		return list;
	}

	public List<Staff> getUserByRoleId(int roleId) {
		List<Staff> list = new ArrayList<Staff>();
		try {
			PreparedStatement ps = con.prepareStatement("select * from users where roleId=?");
			ps.setInt(1, roleId);
			ResultSet set = ps.executeQuery();
			while (set.next()) {
				int userId = set.getInt("userId");
				String firstName = set.getString("firstName");
				String lastName = set.getString("lastName");
				String userName = set.getString("userName");
				String password = set.getString("password");
				String address = set.getString("address");
				int pinCode = set.getInt("pinCode");
				String mobile = set.getString("mobile");
				String email = set.getString("email");
				String gender = set.getString("gender");
				Boolean isActive = set.getBoolean("isActive");

				Staff u = new Staff(userId, firstName, lastName, userName, password, address, pinCode, mobile, email,
						gender, isActive, roleId);

				list.add(u);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// fetch all users
		return list;
	}

	public List<Staff> getAllUsersWithoutSuperAdmin() {
		List<Staff> list = new ArrayList<Staff>();
		try {

			String sql = "select u.*, r.Type from users u join roles r  on u.roleId = r.roleId where u.roleId<>1 order by u.userId desc";
			PreparedStatement ps = con.prepareStatement(sql);

			ResultSet set = ps.executeQuery();
			while (set.next()) {
				int userId = set.getInt("userId");
				String firstName = set.getString("firstName");
				String lastName = set.getString("lastName");
				String userName = set.getString("userName");
				String password = set.getString("password");
				String address = set.getString("address");
				int pinCode = set.getInt("pinCode");
				String mobile = set.getString("mobile");
				String email = set.getString("email");
				String gender = set.getString("gender");
				Boolean isActive = set.getBoolean("isActive");
				int roleId = set.getInt("roleId");
				String roleName = set.getString("Type");

				Staff u = new Staff(userId, firstName, lastName, userName, password, address, pinCode, mobile, email,
						gender, isActive, roleId, roleName);

				list.add(u);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// fetch all users
		return list;
	}

	public List<Staff> getAllEngineersWithZonePendingMapping() {
		List<Staff> list = new ArrayList<Staff>();
		try {

			String sql = "select u.*, z.zoneId, z.name from users u join pincode p on u.pinCode = p.name join zone z on p.zoneId = z.zoneId where u.roleId=2 and u.userId not in (select engineerId from mapping) order by u.userId desc";
			PreparedStatement ps = con.prepareStatement(sql);

			ResultSet set = ps.executeQuery();
			while (set.next()) {
				int userId = set.getInt("userId");
				String firstName = set.getString("firstName");
				String lastName = set.getString("lastName");
				String userName = set.getString("userName");
				String password = set.getString("password");
				String address = set.getString("address");
				int pinCode = set.getInt("pinCode");
				String mobile = set.getString("mobile");
				String email = set.getString("email");
				Boolean isActive = set.getBoolean("isActive");
				int roleId = set.getInt("roleId");
				int zoneId = set.getInt("zoneId");
				String zoneName = set.getString("name");

				Staff u = new Staff(userId, firstName, lastName, userName, password, address, pinCode, mobile, email,
						isActive, roleId, zoneId, zoneName);

				list.add(u);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// fetch all users
		return list;
	}

	public List<Staff> getAllEngineersWithZone() {
		List<Staff> list = new ArrayList<Staff>();
		try {

			String sql = "select u.*, z.zoneId, z.name from users u join pincode p on u.pinCode = p.name join zone z on p.zoneId = z.zoneId where u.roleId=2 and u.userId in (select engineerId from mapping) order by u.userId desc";
			PreparedStatement ps = con.prepareStatement(sql);

			ResultSet set = ps.executeQuery();
			while (set.next()) {
				int userId = set.getInt("userId");
				String firstName = set.getString("firstName");
				String lastName = set.getString("lastName");
				String userName = set.getString("userName");
				String password = set.getString("password");
				String address = set.getString("address");
				int pinCode = set.getInt("pinCode");
				String mobile = set.getString("mobile");
				String email = set.getString("email");
				Boolean isActive = set.getBoolean("isActive");
				int roleId = set.getInt("roleId");
				int zoneId = set.getInt("zoneId");
				String zoneName = set.getString("name");

				Staff u = new Staff(userId, firstName, lastName, userName, password, address, pinCode, mobile, email,
						isActive, roleId, zoneId, zoneName);

				list.add(u);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// fetch all users
		return list;
	}

	public List<Staff> getAllOperators() {
		List<Staff> list = new ArrayList<Staff>();
		try {

			String sql = "select * from users where roleId = 4 order by userId desc";
			PreparedStatement ps = con.prepareStatement(sql);

			ResultSet set = ps.executeQuery();
			while (set.next()) {
				int userId = set.getInt("userId");
				String firstName = set.getString("firstName");
				String lastName = set.getString("lastName");
				String userName = set.getString("userName");
				String password = set.getString("password");
				String address = set.getString("address");
				int pinCode = set.getInt("pinCode");
				String mobile = set.getString("mobile");
				String email = set.getString("email");
				Boolean isActive = set.getBoolean("isActive");
				int roleId = set.getInt("roleId");

				Staff u = new Staff(userId, firstName, lastName, userName, password, address, pinCode, mobile, email,
						isActive, roleId);

				list.add(u);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// fetch all users
		return list;
	}

	public Staff getEngineerById(int userId) {

		Staff s = null;
		try {

			String sql = "select u.*, z.zoneId, z.name from users u join pincode p on u.pinCode = p.name join zone z on p.zoneId = z.zoneId where u.userId = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, userId);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				s = new Staff();
				s.setUserId(rs.getInt("userId"));
				s.setFirstName(rs.getString("firstName"));
				s.setLastName(rs.getString("lastName"));
				s.setUserName(rs.getString("userName"));
				s.setPassword(rs.getString("password"));
				s.setAddress(rs.getString("address"));
				s.setPinCode(rs.getInt("pinCode"));
				s.setMobile(rs.getString("mobile"));
				s.setEmail(rs.getString("email"));
				s.setActive(true);
				s.setRoleId(rs.getInt("roleId"));
				s.setZoneId(rs.getInt("zoneId"));
				s.setZoneName(rs.getString("name"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// fetch all users
		return s;
	}

//	public List<Staff> getAllEngineersByZoneId(int zoneId) {
//		List<Staff> list = new ArrayList<Staff>();
//		try {
//
//			String sql = "select u.*, z.name from users u join pincode p on u.pinCode = p.name join zone z \n"
//					+ "on p.zoneId = z.zoneId\n"
//					+ " where u.roleId=2 and z.zoneId=? order by u.userId desc";
//			PreparedStatement ps = con.prepareStatement(sql);
//			ps.setInt(1, request.getParameter("zoneId"));
//			
//			ResultSet set = ps.executeQuery();
//			while (set.next()) {
//				int userId = set.getInt("userId");
//				String firstName = set.getString("firstName");
//				String lastName = set.getString("lastName");
//				String userName = set.getString("userName");
//				String password = set.getString("password");
//				String address = set.getString("address");
//				int pinCode = set.getInt("pinCode");
//				String mobile = set.getString("mobile");
//				String email = set.getString("email");
//				String gender = set.getString("gender");
//				String roleName = set.getString("Type");
//				String zoneName = set.getString("name");
//				Staff u = new Staff(userId ,firstName, lastName, userName, password, address, pinCode, mobile, email,
//						gender, roleName, zoneName);
//
//				list.add(u);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		// fetch all users
//		return list;
//	}

	public Staff getUserById(int userId) {

		Staff s = null;
		try {

			String sql = "select u.*, r.Type from users u join roles r  on u.roleId = r.roleId where u.userId = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, userId);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				s = new Staff();
				s.setUserId(rs.getInt("userId"));
				s.setFirstName(rs.getString("firstName"));
				s.setLastName(rs.getString("lastName"));
				s.setUserName(rs.getString("userName"));
				s.setPassword(rs.getString("password"));
				s.setAddress(rs.getString("address"));
				s.setPinCode(rs.getInt("pinCode"));
				s.setMobile(rs.getString("mobile"));
				s.setEmail(rs.getString("email"));
				s.setGender(rs.getString("gender"));
				s.setActive(true);
				s.setRoleId(rs.getInt("roleId"));
				s.setRoleName(rs.getString("Type"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// fetch all users
		return s;
	}

	public boolean updateStaff(Staff s) {
		boolean f = false;

		try {
			String sql = "update users set firstname=?, lastName=?, userName=?, password=?, address=?,"
					+ "pinCode=?, mobile=?, gender=? where userId=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, s.getFirstName());
			ps.setString(2, s.getLastName());
			ps.setString(3, s.getUserName());
			ps.setString(4, s.getPassword());
			ps.setString(5, s.getAddress());
			ps.setInt(6, s.getPinCode());
			ps.setString(7, s.getMobile());
			ps.setString(8, s.getGender());
			ps.setInt(9, s.getUserId());

			int i = ps.executeUpdate();
			if (i == 1) {
				f = true;
			}

		} catch (Exception e) {
			e.printStackTrace();

		}
		return f;
	}

	public boolean deleteStaff(int userId) {
		boolean f = false;

		try {
			String sql = "delete from users where userId=?";
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
}
