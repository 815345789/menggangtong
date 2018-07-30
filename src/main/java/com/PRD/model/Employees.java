package com.PRD.model;

public class Employees {
	//单位信息 职员信息表
	private Integer employees_Id;
	private Integer department_Id;
	private String employees_Name;
	private String employees_Phone;
	private Integer employees_Plane;
	private String employees_Job;
	private Integer employees_Sex;
	private Company company;
	private Department department;
	public Integer getEmployees_Id() {
		return employees_Id;
	}
	public void setEmployees_Id(Integer employees_Id) {
		this.employees_Id = employees_Id;
	}
	public Integer getDepartment_Id() {
		return department_Id;
	}
	public void setDepartment_Id(Integer department_Id) {
		this.department_Id = department_Id;
	}
	public String getEmployees_Name() {
		return employees_Name;
	}
	public void setEmployees_Name(String employees_Name) {
		this.employees_Name = employees_Name;
	}
	
	public String getEmployees_Phone() {
		return employees_Phone;
	}
	public void setEmployees_Phone(String employees_Phone) {
		this.employees_Phone = employees_Phone;
	}
	public Integer getEmployees_Plane() {
		return employees_Plane;
	}
	public void setEmployees_Plane(Integer employees_Plane) {
		this.employees_Plane = employees_Plane;
	}
	public String getEmployees_Job() {
		return employees_Job;
	}
	public void setEmployees_Job(String employees_Job) {
		this.employees_Job = employees_Job;
	}
	
	public Integer getEmployees_Sex() {
		return employees_Sex;
	}
	public void setEmployees_Sex(Integer employees_Sex) {
		this.employees_Sex = employees_Sex;
	}
	
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	@Override
	public String toString() {
		return "Employees [employees_Id=" + employees_Id + ", department_Id="
				+ department_Id + ", employees_Name=" + employees_Name
				+ ", employees_Phone=" + employees_Phone + ", employees_Plane="
				+ employees_Plane + ", employees_Job=" + employees_Job
				+ ", employees_Sex=" + employees_Sex + ", company=" + company
				+ ", department=" + department + "]";
	}
	
	
	
}
