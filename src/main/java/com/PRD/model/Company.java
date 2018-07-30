package com.PRD.model;

import java.util.List;

public class Company {
	//单位信息 企业信息表
	private Integer company_Id;
	private String company_Name;
	private String company_Abbreviation;
	private String company_Address;
	private Integer using_Site_Id;
	private String company_Notes;
	private Integer using_Unit_Id;
	private List<Department> depList;
	public Integer getCompany_Id() {
		return company_Id;
	}
	public void setCompany_Id(Integer company_Id) {
		this.company_Id = company_Id;
	}
	public String getCompany_Name() {
		return company_Name;
	}
	public void setCompany_Name(String company_Name) {
		this.company_Name = company_Name;
	}
	public String getCompany_Abbreviation() {
		return company_Abbreviation;
	}
	public void setCompany_Abbreviation(String company_Abbreviation) {
		this.company_Abbreviation = company_Abbreviation;
	}
	public String getCompany_Address() {
		return company_Address;
	}
	public void setCompany_Address(String company_Address) {
		this.company_Address = company_Address;
	}
	public Integer getUsing_Site_Id() {
		return using_Site_Id;
	}
	public void setUsing_Site_Id(Integer using_Site_Id) {
		this.using_Site_Id = using_Site_Id;
	}
	public String getCompany_Notes() {
		return company_Notes;
	}
	public void setCompany_Notes(String company_Notes) {
		this.company_Notes = company_Notes;
	}
	public Integer getUsing_Unit_Id() {
		return using_Unit_Id;
	}
	public void setUsing_Unit_Id(Integer using_Unit_Id) {
		this.using_Unit_Id = using_Unit_Id;
	}
	public List<Department> getDepList() {
		return depList;
	}
	public void setDepList(List<Department> depList) {
		this.depList = depList;
	}
	@Override
	public String toString() {
		return "Company [company_Id=" + company_Id + ", company_Name="
				+ company_Name + ", company_Abbreviation="
				+ company_Abbreviation + ", company_Address=" + company_Address
				+ ", using_Site_Id=" + using_Site_Id + ", company_Notes="
				+ company_Notes + ", using_Unit_Id=" + using_Unit_Id
				+ ", depList=" + depList + "]";
	}
	
}
