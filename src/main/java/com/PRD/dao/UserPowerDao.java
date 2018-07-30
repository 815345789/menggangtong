package com.PRD.dao;

import java.util.List;

import com.PRD.model.UserPower;

public interface UserPowerDao {
	public int addUserPower(UserPower userPower);
	public int updateUserPower(UserPower userPower);
	public UserPower selectUserPower(UserPower userPower);
	public List<UserPower> selectUserPowers(UserPower userPower);
	public int deleteUserPower(UserPower userPower);
}
