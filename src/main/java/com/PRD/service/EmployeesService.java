package com.PRD.service;

import java.util.List;

import com.PRD.model.Employees;
import com.github.pagehelper.PageInfo;

public interface EmployeesService {
	public int addEmployees(Employees employees);
	public int updateEmployees(Employees employees);
	public Employees selectEmployees(Employees employees);
	public PageInfo<Employees> selectEmployeessByPage(Employees employees,Integer pageNo,Integer pageSize);
	public int deleteEmployees(Employees employees);
	public List<Employees> selectEmployeess(Employees employees);
	public PageInfo<Employees> selectEmployeessResultMapByPage(Employees employees,Integer pageNo,Integer pageSize);
	public Employees selectEmployeesResultMap(Employees employees);
}
