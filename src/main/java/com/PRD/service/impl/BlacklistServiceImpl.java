package com.PRD.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PRD.dao.BlacklistDao;
import com.PRD.model.Blacklist;
import com.PRD.model.Company;
import com.PRD.service.BlacklistService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
@Service
public class BlacklistServiceImpl implements BlacklistService {
	@Autowired
	public BlacklistDao blacklistDao;

	@Override
	public int addBlacklist(Blacklist blacklist) {
		// TODO Auto-generated method stub
		return blacklistDao.addBlacklist(blacklist);
	}

	@Override
	public int updateBlacklist(Blacklist blacklist) {
		// TODO Auto-generated method stub
		return blacklistDao.updateBlacklist(blacklist);
	}

	@Override
	public Blacklist selectBlacklist(Blacklist blacklist) {
		// TODO Auto-generated method stub
		return blacklistDao.selectBlacklist(blacklist);
	}

	@Override
	public PageInfo<Blacklist> selectBlacklistsByPage(Blacklist blacklist,Integer pageNo,Integer pageSize) {
		pageNo = pageNo == null?1:pageNo;
		pageSize = pageSize == null?10:pageSize;
		PageHelper.startPage(pageNo, pageSize);
		List<Blacklist> list=blacklistDao.selectBlacklists(blacklist);
		PageInfo<Blacklist> page=new PageInfo<Blacklist>(list);
		return page;
	}

	@Override
	public int deleteBlacklist(Blacklist blacklist) {
		// TODO Auto-generated method stub
		return blacklistDao.deleteBlacklist(blacklist);
	}

	@Override
	public int addBiacklists(List<Blacklist> blists) {
		// TODO Auto-generated method stub
		return blacklistDao.addBiacklists(blists);
	}

}
