package com.PRD.dao;

import java.util.List;

import com.PRD.model.Employees;

public interface EmployeesDao {
	public int addEmployees(Employees employees);
	public int updateEmployees(Employees employees);
	public Employees selectEmployees(Employees employees);
	public List<Employees> selectEmployeess(Employees employees);
	public int deleteEmployees(Employees employees);
	public List<Employees> selectEmployeessResultMap(Employees employees);
	public Employees selectEmployeesResultMap(Employees employees);
}
