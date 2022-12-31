package com.entity;

public class Citizen {
	private int id;
	private String firstName;
	private String lastName;
	private String userName;
	private String password;
	private String address;
	private int pinCode;
	private String mobile;
	private String email;
	private String gender;
	private int questionId;
	private String answer;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public Citizen(int id, String firstName, String lastName, String userName, String password, String address,
			int pinCode, String mobile, String email, String gender) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
		this.address = address;
		this.pinCode = pinCode;
		this.mobile = mobile;
		this.email = email;
		this.gender = gender;
	}

	public Citizen(String firstName, String lastName, String userName, String password, String address, int pinCode,
			String mobile, String email, String gender) {
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
	}

	public Citizen(String firstName) {
		super();
		this.firstName = firstName;
	}

	public Citizen() {
		super();
	}

	public Citizen(String firstName, String lastName, String userName, String password, String address, int pinCode,
			String mobile, String email, String gender, int questionId, String answer) {
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
		this.questionId = questionId;
		this.answer = answer;
	}

	public Citizen(int id, String firstName, String lastName, String userName, String password, String address,
			int pinCode, String mobile, String email, String gender, int questionId, String answer) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
		this.address = address;
		this.pinCode = pinCode;
		this.mobile = mobile;
		this.email = email;
		this.gender = gender;
		this.questionId = questionId;
		this.answer = answer;
	}

	public Citizen(int id, String firstName, String lastName, String userName, String address,
			int pinCode, String email, String gender, int questionId, String answer) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.address = address;
		this.pinCode = pinCode;
		this.email = email;
		this.gender = gender;
		this.questionId = questionId;
		this.answer = answer;
	}
}
