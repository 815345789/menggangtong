package com.PRD.dao;

import java.util.List;

import com.PRD.model.Feedback;


public interface FeedbackDao {
	public int addFeedback(Feedback feedback);
	public int updateFeedback(Feedback feedback);
	public Feedback selectFeedback(Feedback feedback);
	public List<Feedback> selectFeedbacks(Feedback feedback);
	public int deleteFeedback(Feedback feedback);
}
