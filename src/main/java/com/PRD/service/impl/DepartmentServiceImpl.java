package com.PRD.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.PRD.dao.DepartmentDao;
import com.PRD.model.Company;
import com.PRD.model.Department;
import com.PRD.service.DepartmentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
@Service
@Transactional
public class DepartmentServiceImpl implements DepartmentService {
	
	@Autowired
	public DepartmentDao departmentDao;

	@Override
	public int addDepartment(Department department) {
		// TODO Auto-generated method stub
		return departmentDao.addDepartment(department);
	}

	@Override
	public int updateDepartment(Department department) {
		// TODO Auto-generated method stub
		return departmentDao.updateDepartment(department);
	}

	@Override
	public Department selectDepartment(Department department) {
		// TODO Auto-generated method stub
		return departmentDao.selectDepartment(department);
	}

	@Override
	public List<Department> selectDepartments(Department department) {
		return departmentDao.selectDepartments(department);
	}

	@Override
	public int deleteDepartment(Department department) {
		// TODO Auto-generated method stub
		return departmentDao.deleteDepartment(department);
	}

}
