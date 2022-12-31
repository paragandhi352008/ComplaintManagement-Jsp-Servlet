package com.entity;

public class PinCode {
	private int pincodeId;
	private String name;
	private int zoneId;
	private String zoneName;

	public int getPincodeId() {
		return pincodeId;
	}

	public void setPincodeId(int pincodeId) {
		this.pincodeId = pincodeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getZoneId() {
		return zoneId;
	}

	public void setZoneId(int zoneId) {
		this.zoneId = zoneId;
	}

	public String getZoneName() {
		return zoneName;
	}

	public void setZoneName(String zoneName) {
		this.zoneName = zoneName;
	}

	public PinCode(String name, int zoneId, String zoneName) {
		super();
		this.name = name;
		this.zoneId = zoneId;
		this.zoneName = zoneName;
	}
	
	public PinCode(int pincodeId, String name) {
		super();
		this.pincodeId = pincodeId;
		this.name = name;
	}

	public PinCode(int pincodeId, String name, int zoneId, String zoneName) {
		super();
		this.pincodeId = pincodeId;
		this.name = name;
		this.zoneId = zoneId;
		this.zoneName = zoneName;
	}

	public PinCode() {
	}

}
