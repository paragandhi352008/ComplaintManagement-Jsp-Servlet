package com.entity;

public class Staff {

	private int userId;
	private String firstName;
	private String lastName;
	private String userName;
	private String password;
	private String address;
	private int pinCode;
	private String mobile;
	private String email;
	private String gender;
	private boolean isActive;
	private int roleId;
	private String roleName;
	private int zoneId;
	private String zoneName;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getPinCode() {
		return pinCode;
	}

	public void setPinCode(int pinCode) {
		this.pinCode = pinCode;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getZoneName() {
		return zoneName;
	}

	public void setZoneName(String zoneName) {
		this.zoneName = zoneName;
	}

	public int getZoneId() {
		return zoneId;
	}

	public void setZoneId(int zoneId) {
		this.zoneId = zoneId;
	}

	public Staff(String firstName, String lastName, String userName, String password, String address, int pinCode,
			String mobile, String email, String gender, boolean isActive, int roleId) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
		this.address = address;
		this.pinCode = pinCode;
		this.mobile = mobile;
		this.email = email;
		this.gender = gender;
		this.isActive = isActive;
		this.roleId = roleId;
	}

	public Staff(int userId, String firstName, String lastName, String userName, String password, String address,
			int pinCode, String mobile, String email, String gender, boolean isActive, int roleId) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
		this.address = address;
		this.pinCode = pinCode;
		this.mobile = mobile;
		this.email = email;
		this.gender = gender;
		this.isActive = isActive;
		this.roleId = roleId;
	}

	public Staff() {

	}

	public Staff(String mobile) {
		super();
		this.mobile = mobile;
	}

	public Staff(int userId, String firstName, String lastName, String userName, String password, String address,
			int pinCode, String mobile, String email, String gender, boolean isActive, int roleId, String roleName) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
		this.address = address;
		this.pinCode = pinCode;
		this.mobile = mobile;
		this.email = email;
		this.gender = gender;
		this.isActive = isActive;
		this.roleId = roleId;
		this.roleName = roleName;
	}

	// For AddStaff servlet
	public Staff(String firstName, String lastName, String userName, String password, String address, int pinCode,
			String mobile, String email, String gender, String roleName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
		this.address = address;
		this.pinCode = pinCode;
		this.mobile = mobile;
		this.email = email;
		this.gender = gender;
		this.roleName = roleName;
	}
	
	public Staff(String firstName, String lastName, String userName, String password, String address, int pinCode,
			String mobile, String email, String gender, int roleId) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
		this.address = address;
		this.pinCode = pinCode;
		this.mobile = mobile;
		this.email = email;
		this.gender = gender;
		this.roleId = roleId;
	}

	// For Engineer Mapping servlet
	public Staff(int userId, String firstName, String lastName, String userName, String password, String address,
			int pinCode, String mobile, String email, boolean isActive, int roleId, int zoneId, String zoneName) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
		this.address = address;
		this.pinCode = pinCode;
		this.mobile = mobile;
		this.email = email;
		this.isActive = isActive;
		this.roleId = roleId;
		this.zoneId = zoneId;
		this.zoneName = zoneName;
	}

	public Staff(int userId, String firstName, String lastName, String userName, String password, String address,
			int pinCode, String mobile, String email, Boolean isActive, int roleId) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
		this.address = address;
		this.pinCode = pinCode;
		this.mobile = mobile;
		this.email = email;
		this.isActive = isActive;
		this.roleId = roleId;
	}
}