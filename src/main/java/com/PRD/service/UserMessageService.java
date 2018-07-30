package com.PRD.service;

import java.util.List;

import com.PRD.model.UserMessage;
import com.github.pagehelper.PageInfo;

public interface UserMessageService {
	public int addUser(UserMessage user);
	public int updateUser(UserMessage user);
	public UserMessage selectUser(UserMessage user);
	public PageInfo<UserMessage> selectUsersByPage(UserMessage user,
			Integer pageNo, Integer pageSize);
	public int deleteUser(UserMessage user);
}
