package com.PRD.service.impl;

import java.util.List;









import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.PRD.dao.UserMessageDao;
import com.PRD.model.Company;
import com.PRD.model.UserMessage;
import com.PRD.service.UserMessageService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
@Transactional
public class UserMessageServiceImpl implements UserMessageService {
	
	@Autowired
	public UserMessageDao usermessageDao;
	
	@Override
	public int addUser(UserMessage user) {
		return usermessageDao.addUser(user);
	}

	@Override
	public int updateUser(UserMessage user) {
		// TODO Auto-generated method stub
		return usermessageDao.updateUser(user);
	}

	@Override
	public UserMessage selectUser(UserMessage user) {
		// TODO Auto-generated method stub
		return usermessageDao.selectUser(user);
	}

	@Override
	public PageInfo<UserMessage> selectUsersByPage(UserMessage user,
			Integer pageNo, Integer pageSize) {
		pageNo = pageNo == null?1:pageNo;
		pageSize = pageSize == null?10:pageSize;
		PageHelper.startPage(pageNo, pageSize);
		List<UserMessage> list=usermessageDao.selectUsers(user);
		PageInfo<UserMessage> page=new PageInfo<UserMessage>(list);
		return page;
	}

	@Override
	public int deleteUser(UserMessage user) {
		// TODO Auto-generated method stub
		return usermessageDao.deleteUser(user);
	}

}
