package com.PRD.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.PRD.dao.CompanyDao;
import com.PRD.dao.DepartmentDao;
import com.PRD.model.Company;
import com.PRD.model.Department;
import com.PRD.model.VisitantMessage;
import com.PRD.service.CompanyService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
@Service
@Transactional
public class CompanyServiceImpl implements CompanyService {
	
	@Autowired
	private CompanyDao companyDao;
	@Autowired
	private DepartmentDao departmentDao;

	@Override
	public int addCompany(Company company) {
		// TODO Auto-generated method stub
		return companyDao.addCompany(company);
	}

	@Override
	public int updateCompany(Company company) {
		// TODO Auto-generated method stub
		return companyDao.updateCompany(company);
	}

	@Override
	public Company selectCompany(Company company) {
		// TODO Auto-generated method stub
		return companyDao.selectCompany(company);
	}

	@Override
	public PageInfo<Company> selectCompanysByPage(Company company,
			Integer pageNo, Integer pageSize) {
		pageNo = pageNo == null?1:pageNo;
		pageSize = pageSize == null?10:pageSize;
		PageHelper.startPage(pageNo, pageSize);
		List<Company> list=companyDao.selectCompanys(company);
		PageInfo<Company> page=new PageInfo<Company>(list);
		return page;
	}

	@Override
	public int deleteCompany(Company company) {
		// TODO Auto-generated method stub
		return companyDao.deleteCompany(company);
	}

	@Override
	public Company selectCompanyResultMap(Company company) {
		// TODO Auto-generated method stub
		return companyDao.selectCompanyResultMap(company).get(0);
	}

	@Override
	public int addComDep(Company company) {
		companyDao.addCompany(company);
		for (int i = 0; i < company.getDepList().size(); i++) {
			Department dep = company.getDepList().get(i);
			dep.setCompany_Id(company.getCompany_Id());
			departmentDao.addDepartment(dep);
		}
		return 1;
	}

	@Override
	public List<Company> selectCompanysResultMap(Company company) {
		// TODO Auto-generated method stub
		return companyDao.selectCompanyResultMap(company);
	}

}
