package com.PRD.model;

public class City {
	//市级信息表
	private Integer city_Id;
	private String city_Name;
	private Integer provinces_Id;
	public Integer getCity_Id() {
		return city_Id;
	}
	public void setCity_Id(Integer city_Id) {
		this.city_Id = city_Id;
	}
	public String getCity_Name() {
		return city_Name;
	}
	public void setCity_Name(String city_Name) {
		this.city_Name = city_Name;
	}
	public Integer getProvinces_Id() {
		return provinces_Id;
	}
	public void setProvinces_Id(Integer provinces_Id) {
		this.provinces_Id = provinces_Id;
	}
	
}
