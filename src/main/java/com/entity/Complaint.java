package com.entity;

public class Complaint {
	private int complaintId;
	private int statusId;
	private int citizenId;
	private int userId;
	private int zoneId;
	private int problemId;
	private int subProblemId;
	private String createdDateTime;
	private String notes;
	private String status;
	private String pre;
	private String date;
	private String complaintNo;
	private int createdBy;

	public int getComplaintId() {
		return complaintId;
	}

	public void setComplaintId(int complaintId) {
		this.complaintId = complaintId;
	}

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	public int getCitizenId() {
		return citizenId;
	}

	public void setCitizenId(int citizenId) {
		this.citizenId = citizenId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getZoneId() {
		return zoneId;
	}

	public void setZoneId(int zoneId) {
		this.zoneId = zoneId;
	}

	public int getProblemId() {
		return problemId;
	}

	public void setProblemId(int problemId) {
		this.problemId = problemId;
	}

	public int getSubProblemId() {
		return subProblemId;
	}

	public void setSubProblemId(int subProblemId) {
		this.subProblemId = subProblemId;
	}

	public String getCreatedDateTime() {
		return createdDateTime;
	}

	public void setCreatedDateTime(String createdDateTime) {
		this.createdDateTime = createdDateTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getPre() {
		return pre;
	}

	public void setPre(String pre) {
		this.pre = pre;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getComplaintNo() {
		return complaintNo;
	}

	public void setComplaintNo(String complaintNo) {
		this.complaintNo = complaintNo;
	}

	public int getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public Complaint(int complaintId, int statusId, int citizenId, int userId, int zoneId, int problemId,
			int subProblemId, String createdDateTime) {
		super();
		this.complaintId = complaintId;
		this.statusId = statusId;
		this.citizenId = citizenId;
		this.userId = userId;
		this.zoneId = zoneId;
		this.problemId = problemId;
		this.subProblemId = subProblemId;
		this.createdDateTime = createdDateTime;
	}

	public Complaint(int citizenId, int problemId, int subproblemId, String notes, String createdDateTime,
			int statusId) {
		this.citizenId = citizenId;
		this.problemId = problemId;
		this.subProblemId = subproblemId;
		this.notes = notes;
		this.createdDateTime = createdDateTime;
		this.statusId = statusId;

	}

	public Complaint() {
		super();
	}

}
