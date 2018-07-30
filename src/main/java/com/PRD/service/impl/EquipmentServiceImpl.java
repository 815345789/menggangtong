package com.PRD.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PRD.dao.EquipmentDao;
import com.PRD.model.Company;
import com.PRD.model.Equipment;
import com.PRD.service.EquipmentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
@Service
public class EquipmentServiceImpl implements EquipmentService {
	
	@Autowired
	private EquipmentDao equipmentDao;

	@Override
	public int addEquipment(Equipment equipment) {
		// TODO Auto-generated method stub
		return equipmentDao.addEquipment(equipment);
	}

	@Override
	public int updateEquipment(Equipment equipment) {
		// TODO Auto-generated method stub
		return equipmentDao.updateEquipment(equipment);
	}

	@Override
	public Equipment selectEquipment(Equipment equipment) {
		// TODO Auto-generated method stub
		return equipmentDao.selectEquipment(equipment);
	}

	@Override
	public PageInfo<Equipment> selectEquipmentsByPage(Equipment equipment,
			Integer pageNo, Integer pageSize) {
		pageNo = pageNo == null?1:pageNo;
		pageSize = pageSize == null?10:pageSize;
		PageHelper.startPage(pageNo, pageSize);
		List<Equipment> list=equipmentDao.selectEquipments(equipment);
		PageInfo<Equipment> page=new PageInfo<Equipment>(list);
		return page;
	}

	@Override
	public int deleteEquipment(Equipment equipment) {
		// TODO Auto-generated method stub
		return equipmentDao.updateEquipment(equipment);
	}

}
