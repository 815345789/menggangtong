package com.PRD.dao;

import java.util.List;

import com.PRD.model.Provinces;

public interface ProvincesDao {
	public Provinces selectProvinces(Provinces provinces);
	public List<Provinces> selectProvincess(Provinces provinces);
}
