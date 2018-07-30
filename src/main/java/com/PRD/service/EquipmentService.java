package com.PRD.service;

import java.util.List;

import com.PRD.model.Equipment;
import com.github.pagehelper.PageInfo;

public interface EquipmentService {
	public int addEquipment(Equipment equipment);
	public int updateEquipment(Equipment equipment);
	public Equipment selectEquipment(Equipment equipment);
	public PageInfo<Equipment> selectEquipmentsByPage(Equipment equipment,Integer pageNo,Integer pageSize);
	public int deleteEquipment(Equipment equipment);
}
