package com.entity;

public class Feedback {
	
	private int feedbackId;
    private String answer01;
    private String answer02;
    private String answer03;
    private int citizenId;
    
	public int getFeedbackId() {
		return feedbackId;
	}
	public void setFeedbackId(int feedbackId) {
		this.feedbackId = feedbackId;
	}
	public String getAnswer01() {
		return answer01;
	}
	public void setAnswer01(String answer01) {
		this.answer01 = answer01;
	}
	public String getAnswer02() {
		return answer02;
	}
	public void setAnswer02(String answer02) {
		this.answer02 = answer02;
	}
	public String getAnswer03() {
		return answer03;
	}
	public void setAnswer03(String answer03) {
		this.answer03 = answer03;
	}
	public int getCitizenId() {
		return citizenId;
	}
	public void setCitizenId(int citizenId) {
		this.citizenId = citizenId;
	}
	public Feedback(String answer01, String answer02, String answer03, int citizenId) {
		super();
		this.answer01 = answer01;
		this.answer02 = answer02;
		this.answer03 = answer03;
		this.citizenId = citizenId;
	}
    
    

}
