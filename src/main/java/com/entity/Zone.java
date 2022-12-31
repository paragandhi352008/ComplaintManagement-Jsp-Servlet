package com.entity;

public class Zone {

	private int zoneId;
	private String name;

	public int getZoneId() {
		return zoneId;
	}

	public void setZoneId(int zoneId) {
		this.zoneId = zoneId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Zone(String name) {
		super();
		this.name = name;
	}

	public Zone(int zoneId, String name) {
		super();
		this.zoneId = zoneId;
		this.name = name;
	}

	public Zone() {
		// TODO Auto-generated constructor stub
	}

}
