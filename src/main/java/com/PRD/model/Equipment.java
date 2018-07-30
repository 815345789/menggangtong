package com.PRD.model;

public class Equipment {
	//设备信息表
	private Integer equipment_Id;
	private String equipment_Name;
	private String equipment_Num;
	private Integer using_Unit_Id;
	private Integer using_Site_Id;
	public Integer getEquipment_Id() {
		return equipment_Id;
	}
	public void setEquipment_Id(Integer equipment_Id) {
		this.equipment_Id = equipment_Id;
	}
	public String getEquipment_Name() {
		return equipment_Name;
	}
	public void setEquipment_Name(String equipment_Name) {
		this.equipment_Name = equipment_Name;
	}
	public String getEquipment_Num() {
		return equipment_Num;
	}
	public void setEquipment_Num(String equipment_Num) {
		this.equipment_Num = equipment_Num;
	}
	public Integer getUsing_Unit_Id() {
		return using_Unit_Id;
	}
	public void setUsing_Unit_Id(Integer using_Unit_Id) {
		this.using_Unit_Id = using_Unit_Id;
	}
	public Integer getUsing_Site_Id() {
		return using_Site_Id;
	}
	public void setUsing_Site_Id(Integer using_Site_Id) {
		this.using_Site_Id = using_Site_Id;
	}
	
	
}
