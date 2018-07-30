package com.PRD.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.PRD.dao.VisitantMessageDao;
import com.PRD.model.VisitantMessage;
import com.PRD.service.VisitantMessageService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
@Transactional
public class VisitantMessageServiceImpl implements VisitantMessageService {
	
	@Autowired
	public VisitantMessageDao visitantMessageDao;
	
	@Override
	public int selectCount(Map<String, String> map) {
		return visitantMessageDao.selectCount(map);
	}

	@Override
	public PageInfo<VisitantMessage> selectVisitantMessagesByPage(
			VisitantMessage visitantMessage, Integer pageNo, Integer pageSize) {
		pageNo = pageNo == null?1:pageNo;
		pageSize = pageSize == null?10:pageSize;
		PageHelper.startPage(pageNo, pageSize);
		List<VisitantMessage> list=visitantMessageDao.selectVisitantMessages(visitantMessage);
		PageInfo page=new PageInfo<>(list);
		return page;
	}

	@Override
	public VisitantMessage selectVisitantMessage(VisitantMessage visitantMessage) {
		// TODO Auto-generated method stub
		return visitantMessageDao.selectVisitantMessage(visitantMessage);
	}

	@Override
	public int addVisitantMessage(VisitantMessage visitantMessage) {
		// TODO Auto-generated method stub
		return visitantMessageDao.addVisitantMessage(visitantMessage);
	}

	@Override
	public int deleteVisitantMessage(VisitantMessage visitantMessage) {
		// TODO Auto-generated method stub
		return visitantMessageDao.deleteVisitantMessage(visitantMessage);
	}

	@Override
	public int updateVisitantMessage(VisitantMessage visitantMessage) {
		// TODO Auto-generated method stub
		return visitantMessageDao.updateVisitantMessage(visitantMessage);
	}

	@Override
	public List<VisitantMessage> selectVisitantMessages(
			VisitantMessage visitantMessage) {
		// TODO Auto-generated method stub
		return visitantMessageDao.selectVisitantMessages(visitantMessage);
	}

	@Override
	public int addVisitantMessages(List<VisitantMessage> list) {
		// TODO Auto-generated method stub
		return visitantMessageDao.addVisitantMessages(list);
	}

	@Override
	public int updateVisitantMessages(List<VisitantMessage> list) {
		// TODO Auto-generated method stub
		return visitantMessageDao.updateVisitantMessages(list);
	}

}
