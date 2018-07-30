package com.PRD.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PRD.dao.FeedbackDao;
import com.PRD.model.Company;
import com.PRD.model.Feedback;
import com.PRD.service.FeedbackService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
@Service
public class FeedbackServiceImpl implements FeedbackService {
	@Autowired
	private FeedbackDao feedbackDao;

	@Override
	public int addFeedback(Feedback feedback) {
		// TODO Auto-generated method stub
		return feedbackDao.addFeedback(feedback);
	}

	@Override
	public int updateFeedback(Feedback feedback) {
		// TODO Auto-generated method stub
		return feedbackDao.updateFeedback(feedback);
	}

	@Override
	public Feedback selectFeedback(Feedback feedback) {
		// TODO Auto-generated method stub
		return feedbackDao.selectFeedback(feedback);
	}

	@Override
	public PageInfo<Feedback> selectFeedbacksByPages(Feedback feedback,
			Integer pageNo, Integer pageSize) {
		pageNo = pageNo == null?1:pageNo;
		pageSize = pageSize == null?10:pageSize;
		PageHelper.startPage(pageNo, pageSize);
		List<Feedback> list=feedbackDao.selectFeedbacks(feedback);
		PageInfo<Feedback> page=new PageInfo<Feedback>(list);
		return page;
	}

	@Override
	public int deleteFeedback(Feedback feedback) {
		// TODO Auto-generated method stub
		return feedbackDao.deleteFeedback(feedback);
	}

}
