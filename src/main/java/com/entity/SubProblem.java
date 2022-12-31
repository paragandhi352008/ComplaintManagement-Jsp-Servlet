package com.entity;

public class SubProblem {
	private int subproblemId;
	private String type;
	private int problemId;
	private String problemType;

	public int getSubproblemId() {
		return subproblemId;
	}

	public void setSubproblemId(int subproblemId) {
		this.subproblemId = subproblemId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public String getProblemType() {
		return problemType;
	}

	public void setProblemType(String problemType) {
		this.problemType = problemType;
	}

	public int getProblemId() {
		return problemId;
	}

	public void setProblemId(int problemId) {
		this.problemId = problemId;
	}

	public SubProblem(int subproblemId, String type, int problemId) {
		super();
		this.subproblemId = subproblemId;
		this.type = type;
		this.problemId = problemId;
	}

	public SubProblem(int subproblemId, String type) {
		super();
		this.type = type;
		this.subproblemId = subproblemId;
	}
	
	public SubProblem(int problemId, String type, String problemType) {
		super();
		this.problemId = problemId;
		this.type = type;
		this.problemType = problemType;
	}
	
	public SubProblem() {
	}

}
