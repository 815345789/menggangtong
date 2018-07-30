package com.PRD.service.impl;

import java.util.List;






import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PRD.dao.UsingSiteDao;
import com.PRD.model.Company;
import com.PRD.model.UsingSite;
import com.PRD.service.UsingSiteService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class UsingSiteServiceImpl implements UsingSiteService {
	
	@Autowired
	public UsingSiteDao usingSiteDao;

	@Override
	public int addUsingSite(UsingSite usingSite) {
		// TODO Auto-generated method stub
		return usingSiteDao.addUsingSite(usingSite);
	}

	@Override
	public int updateUsingSite(UsingSite usingSite) {
		// TODO Auto-generated method stub
		return usingSiteDao.updateUsingSite(usingSite);
	}

	@Override
	public UsingSite selectUsingSite(UsingSite usingSite) {
		// TODO Auto-generated method stub
		return usingSiteDao.selectUsingSite(usingSite);
	}

	@Override
	public PageInfo<UsingSite> selectUsingSitesByPage(UsingSite usingSite,
			Integer pageNo,Integer pageSize) {
		pageNo = pageNo == null?1:pageNo;
		pageSize = pageSize == null?10:pageSize;
		PageHelper.startPage(pageNo, pageSize);
		List<UsingSite> list=usingSiteDao.selectUsingSites(usingSite);
		PageInfo<UsingSite> page=new PageInfo<UsingSite>(list);
		return page;
	}

	@Override
	public int deleteUsingSite(UsingSite usingSite) {
		// TODO Auto-generated method stub
		return usingSiteDao.deleteUsingSite(usingSite);
	}

	@Override
	public List<UsingSite> selectUsingSites(UsingSite usingSite) {
		// TODO Auto-generated method stub
		return usingSiteDao.selectUsingSites(usingSite);
	}

}
