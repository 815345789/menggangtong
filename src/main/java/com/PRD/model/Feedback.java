package com.PRD.model;

public class Feedback {
	private Integer feedback_Id;
	private Integer using_Unit_Id;
	private String feedback_Notes;
	public Integer getFeedback_Id() {
		return feedback_Id;
	}
	public void setFeedback_Id(Integer feedback_Id) {
		this.feedback_Id = feedback_Id;
	}
	public Integer getUsing_Unit_Id() {
		return using_Unit_Id;
	}
	public void setUsing_Unit_Id(Integer using_Unit_Id) {
		this.using_Unit_Id = using_Unit_Id;
	}
	public String getFeedback_Notes() {
		return feedback_Notes;
	}
	public void setFeedback_Notes(String feedback_Notes) {
		this.feedback_Notes = feedback_Notes;
	}
	@Override
	public String toString() {
		return "Feedback [feedback_Id=" + feedback_Id + ", using_Unit_Id="
				+ using_Unit_Id + ", feedback_Notes=" + feedback_Notes + "]";
	}
	

}
