package com.PRD.dao;

import java.util.List;

import com.PRD.model.Department;

public interface DepartmentDao {
	public int addDepartment(Department department);
	public int updateDepartment(Department department);
	public Department selectDepartment(Department department);
	public List<Department> selectDepartments(Department department);
	public int deleteDepartment(Department department);
}
