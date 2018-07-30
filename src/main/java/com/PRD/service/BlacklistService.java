package com.PRD.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.PRD.model.Blacklist;
import com.github.pagehelper.PageInfo;

public interface BlacklistService {
	public int addBlacklist(Blacklist blacklist);
	public int updateBlacklist(Blacklist blacklist);
	public Blacklist selectBlacklist(Blacklist blacklist);
	public PageInfo<Blacklist> selectBlacklistsByPage(Blacklist blacklist,Integer pageNo,Integer pageSize);
	public int deleteBlacklist(Blacklist blacklist);
	public int addBiacklists(@Param("blists")List<Blacklist> blists);
}
