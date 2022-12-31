package com.entity;

public class Problem {
	
	private int problemId;
	private String type;

	public Problem(int problemId, String type) {
		super();
		this.problemId = problemId;
		this.type = type;
	}

	public Problem(String type) {
		super();
		this.type = type;
	}
	
	public Problem(int problemId) {
		super();
		this.problemId = problemId;
	}

	public Problem() {
	}
	
	public int getProblemId() {
		return problemId;
	}

	public void setProblemId(int problemId) {
		this.problemId = problemId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
