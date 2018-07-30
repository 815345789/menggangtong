package com.PRD.service;

import java.util.List;

import com.PRD.model.UsingSite;
import com.github.pagehelper.PageInfo;

public interface UsingSiteService {
	public int addUsingSite(UsingSite usingSite);
	public int updateUsingSite(UsingSite usingSite);
	public UsingSite selectUsingSite(UsingSite usingSite);
	public PageInfo<UsingSite> selectUsingSitesByPage(UsingSite usingSite,Integer pageNo,Integer pageSize);
	public int deleteUsingSite(UsingSite usingSite);
	public List<UsingSite> selectUsingSites(UsingSite usingSite);
}
