package com.PRD.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PRD.dao.UsingUnitDao;
import com.PRD.model.UsingUnit;
import com.PRD.service.UsingUnitService;
@Service
public class UsingUnitServiceImpl implements UsingUnitService {
	@Autowired
	private UsingUnitDao usingUnitDao;

	@Override
	public UsingUnit selectUsingUnit(UsingUnit usingUnit) {
		// TODO Auto-generated method stub
		return usingUnitDao.selectUsingUnit(usingUnit);
	}

}
