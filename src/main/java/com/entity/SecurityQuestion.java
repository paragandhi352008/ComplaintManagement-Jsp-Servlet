package com.entity;

public class SecurityQuestion {
	private int questionsId;
	private String questionsName;

	public int getQuestionsId() {
		return questionsId;
	}

	public void setQuestionsId(int questionsId) {
		this.questionsId = questionsId;
	}

	public String getQuestionsName() {
		return questionsName;
	}

	public void setQuestionsName(String questionsName) {
		this.questionsName = questionsName;
	}

	public SecurityQuestion(int questionsId, String questionsName) {
		super();
		this.questionsId = questionsId;
		this.questionsName = questionsName;
	}

	public SecurityQuestion() {
		super();
	}

}
