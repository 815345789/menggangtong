package com.PRD.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.PRD.model.Blacklist;


public interface BlacklistDao {
	public int addBlacklist(Blacklist blacklist);
	public int updateBlacklist(Blacklist blacklist);
	public Blacklist selectBlacklist(Blacklist blacklist);
	public List<Blacklist> selectBlacklists(Blacklist blacklist);
	public int deleteBlacklist(Blacklist blacklist);
	public int addBiacklists(@Param("blists")List<Blacklist> blists);
}
