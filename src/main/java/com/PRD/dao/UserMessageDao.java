package com.PRD.dao;

import java.util.List;

import com.PRD.model.UserMessage;

public interface UserMessageDao {
	public int addUser(UserMessage user);
	public int updateUser(UserMessage user);
	public UserMessage selectUser(UserMessage user);
	public List<UserMessage> selectUsers(UserMessage user);
	public int deleteUser(UserMessage user);
}
