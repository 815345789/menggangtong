package com.PRD.dao;

import java.util.List;

import com.PRD.model.FollowUpMessage;

public interface FollowUpMessageDao {
	public int addFollowUpMessage(FollowUpMessage followUpMessage);
	public int updateFollowUpMessage(FollowUpMessage followUpMessage);
	public FollowUpMessage selectFollowUpMessage(FollowUpMessage followUpMessage);
	public List<FollowUpMessage> selectFollowUpMessages(FollowUpMessage followUpMessage);
	public int deleteFollowUpMessage(FollowUpMessage followUpMessage);
}
