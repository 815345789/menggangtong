package com.PRD.service;

import java.util.List;

import com.PRD.model.Department;
import com.github.pagehelper.PageInfo;

public interface DepartmentService {
	public int addDepartment(Department department);
	public int updateDepartment(Department department);
	public Department selectDepartment(Department department);
	public List<Department> selectDepartments(Department department);
	public int deleteDepartment(Department department);
}
