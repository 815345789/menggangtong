package com.PRD.service;

import java.util.List;
import java.util.Map;

import com.PRD.model.VisitantMessage;
import com.github.pagehelper.PageInfo;

public interface VisitantMessageService {
	public int selectCount(Map<String, String> map);
	public PageInfo<VisitantMessage> selectVisitantMessagesByPage(VisitantMessage visitantMessage,Integer pageNo,Integer pageSize);
	public VisitantMessage selectVisitantMessage(VisitantMessage visitantMessage);
	public int addVisitantMessage(VisitantMessage visitantMessage);
	public int deleteVisitantMessage(VisitantMessage visitantMessage);
	public int updateVisitantMessage(VisitantMessage visitantMessage);
	public List<VisitantMessage> selectVisitantMessages(VisitantMessage visitantMessage);
	public int addVisitantMessages(List<VisitantMessage> list);
	public int updateVisitantMessages(List<VisitantMessage> list);
}
