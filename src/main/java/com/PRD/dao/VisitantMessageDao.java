package com.PRD.dao;

import java.util.List;
import java.util.Map;

import com.PRD.model.UserMessage;
import com.PRD.model.VisitantMessage;

public interface VisitantMessageDao {
	public int selectCount(Map<String, String> map);
	public List<VisitantMessage> selectVisitantMessages(VisitantMessage visitantMessage);
	public VisitantMessage selectVisitantMessage(VisitantMessage visitantMessage);
	public int addVisitantMessage(VisitantMessage visitantMessage);
	public int deleteVisitantMessage(VisitantMessage visitantMessage);
	public int updateVisitantMessage(VisitantMessage visitantMessage);
	public int addVisitantMessages(List<VisitantMessage> list);
	public int updateVisitantMessages(List<VisitantMessage> list);
}
