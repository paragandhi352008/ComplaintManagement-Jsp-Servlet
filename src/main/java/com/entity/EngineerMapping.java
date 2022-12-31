package com.entity;

public class EngineerMapping {

	private int problemId;
	private int zoneId;
	private int userId;

	public int getProblemId() {
		return problemId;
	}

	public void setProblemId(int problemId) {
		this.problemId = problemId;
	}

	public int getZoneId() {
		return zoneId;
	}

	public void setZoneId(int zoneId) {
		this.zoneId = zoneId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public EngineerMapping(int userId, int problemId, int zoneId) {
		super();
		this.userId = userId;
		this.problemId = problemId;
		this.zoneId = zoneId;

	}

	public EngineerMapping() {
		super();
	}
}