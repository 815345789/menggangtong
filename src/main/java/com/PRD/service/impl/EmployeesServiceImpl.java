package com.PRD.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.PRD.dao.EmployeesDao;
import com.PRD.model.Department;
import com.PRD.model.Employees;
import com.PRD.service.EmployeesService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
@Service
@Transactional
public class EmployeesServiceImpl implements EmployeesService {
	
	@Autowired
	public EmployeesDao employeesDao;

	@Override
	public int addEmployees(Employees employees) {
		// TODO Auto-generated method stub
		return employeesDao.addEmployees(employees);
	}

	@Override
	public int updateEmployees(Employees employees) {
		// TODO Auto-generated method stub
		return employeesDao.updateEmployees(employees);
	}

	@Override
	public Employees selectEmployees(Employees employees) {
		// TODO Auto-generated method stub
		return employeesDao.selectEmployees(employees);
	}

	@Override
	public PageInfo<Employees> selectEmployeessByPage(Employees employees,
			Integer pageNo, Integer pageSize) {
		pageNo = pageNo == null?1:pageNo;
		pageSize = pageSize == null?10:pageSize;
		PageHelper.startPage(pageNo, pageSize);
		List<Employees> list=employeesDao.selectEmployeess(employees);
		PageInfo<Employees> page=new PageInfo<Employees>(list);
		return page;
	}

	@Override
	public int deleteEmployees(Employees employees) {
		// TODO Auto-generated method stub
		return employeesDao.deleteEmployees(employees);
	}

	@Override
	public List<Employees> selectEmployeess(Employees employees) {
		// TODO Auto-generated method stub
		return employeesDao.selectEmployeess(employees);
	}

	@Override
	public PageInfo<Employees> selectEmployeessResultMapByPage(
			Employees employees, Integer pageNo, Integer pageSize) {
		pageNo = pageNo == null?1:pageNo;
		pageSize = pageSize == null?10:pageSize;
		PageHelper.startPage(pageNo, pageSize);
		List<Employees> list=employeesDao.selectEmployeessResultMap(employees);
		PageInfo<Employees> page=new PageInfo<Employees>(list);
		return page;
	}

	@Override
	public Employees selectEmployeesResultMap(Employees employees) {
		// TODO Auto-generated method stub
		return employeesDao.selectEmployeesResultMap(employees);
	}

}
