package com.PRD.service;

import java.util.List;

import com.PRD.model.Feedback;
import com.github.pagehelper.PageInfo;

public interface FeedbackService {
	public int addFeedback(Feedback feedback);
	public int updateFeedback(Feedback feedback);
	public Feedback selectFeedback(Feedback feedback);
	public PageInfo<Feedback> selectFeedbacksByPages(Feedback feedback,Integer pageNo,Integer pageSize);
	public int deleteFeedback(Feedback feedback);
}
