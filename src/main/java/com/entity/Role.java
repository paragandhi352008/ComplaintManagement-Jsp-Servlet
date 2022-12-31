package com.entity;

public class Role {

    private int roleId;
    private String Type;
    
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public String getType() {
		return Type;
	}
	public void setType(String type) {
		Type = type;
	}
	public Role(int roleId, String type) {
		super();
		this.roleId = roleId;
		Type = type;
	}
	public Role() {
		// TODO Auto-generated constructor stub
	}

  

}
