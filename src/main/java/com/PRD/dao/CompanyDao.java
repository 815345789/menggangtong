package com.PRD.dao;

import java.util.List;

import com.PRD.model.Company;


public interface CompanyDao {
	public int addCompany(Company company);
	public int updateCompany(Company company);
	public Company selectCompany(Company company);
	public List<Company> selectCompanys(Company company);
	public int deleteCompany(Company company);
	public List<Company> selectCompanyResultMap(Company company);
}
