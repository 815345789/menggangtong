package com.PRD.model;

public class AllVisitantMessage {
	private VisitantMessage visitantMessage;
	private Company company;
	private Department department;
	private Employees employees;
	public VisitantMessage getVisitantMessage() {
		return visitantMessage;
	}
	public void setVisitantMessage(VisitantMessage visitantMessage) {
		this.visitantMessage = visitantMessage;
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
	public Employees getEmployees() {
		return employees;
	}
	public void setEmployees(Employees employees) {
		this.employees = employees;
	}
	
}
