package com.PRD.service;

import java.util.List;

import com.PRD.model.Company;
import com.PRD.model.VisitantMessage;
import com.github.pagehelper.PageInfo;

public interface CompanyService {
	public int addCompany(Company company);
	public int updateCompany(Company company);
	public Company selectCompany(Company company);
	public PageInfo<Company> selectCompanysByPage(Company company,Integer pageNo,Integer pageSize);
	public int deleteCompany(Company company);
	public Company selectCompanyResultMap(Company company);
	public int addComDep(Company company);
	public List<Company> selectCompanysResultMap(Company company);
}
