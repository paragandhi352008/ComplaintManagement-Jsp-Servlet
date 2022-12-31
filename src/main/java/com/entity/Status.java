package com.entity;

public class Status {
	
	private int statusId;
	private String name;
	
	public int getStatusId() {
		return statusId;
	}
	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Status(int statusId, String name) {
		super();
		this.statusId = statusId;
		this.name = name;
	}
	public Status() {
		super();
	}

	

}
