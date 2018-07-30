package com.PRD.dao;

import java.util.List;

import com.PRD.model.UsingUnit;

public interface UsingUnitDao {
	public int addUsingUnit(UsingUnit usingUnit);
	public int updateUsingUnit(UsingUnit usingUnit);
	public UsingUnit selectUsingUnit(UsingUnit usingUnit);
	public List<UsingUnit> selectUsingUnits(UsingUnit usingUnit);
	public int deleteUsingUnit(UsingUnit usingUnit);
}
