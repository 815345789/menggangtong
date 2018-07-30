package com.PRD.dao;

import java.util.List;

import com.PRD.model.Equipment;

public interface EquipmentDao {
	public int addEquipment(Equipment equipment);
	public int updateEquipment(Equipment equipment);
	public Equipment selectEquipment(Equipment equipment);
	public List<Equipment> selectEquipments(Equipment equipment);
	public int deleteEquipment(Equipment equipment);
}
