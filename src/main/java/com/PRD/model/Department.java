package com.PRD.model;

public class Department {
	//单位信息 部门信息表
	private Integer department_Id;
	private Integer company_Id;
	private String department_Name;
	private String department_Notes;
	public Integer getDepartment_Id() {
		return department_Id;
	}
	public void setDepartment_Id(Integer department_Id) {
		this.department_Id = department_Id;
	}
	public Integer getCompany_Id() {
		return company_Id;
	}
	public void setCompany_Id(Integer company_Id) {
		this.company_Id = company_Id;
	}
	public String getDepartment_Name() {
		return department_Name;
	}
	public void setDepartment_Name(String department_Name) {
		this.department_Name = department_Name;
	}
	public String getDepartment_Notes() {
		return department_Notes;
	}
	public void setDepartment_Notes(String department_Notes) {
		this.department_Notes = department_Notes;
	}
	@Override
	public String toString() {
		return "Department [department_Id=" + department_Id + ", company_Id="
				+ company_Id + ", department_Name=" + department_Name
				+ ", department_Notes=" + department_Notes + "]";
	}
	
}
