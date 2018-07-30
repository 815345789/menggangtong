package com.PRD.dao;

import java.util.List;

import com.PRD.model.UsingSite;

public interface UsingSiteDao {
	public int addUsingSite(UsingSite usingSite);
	public int updateUsingSite(UsingSite usingSite);
	public UsingSite selectUsingSite(UsingSite usingSite);
	public List<UsingSite> selectUsingSites(UsingSite usingSite);
	public int deleteUsingSite(UsingSite usingSite);
}
