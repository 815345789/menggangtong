package com.PRD.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.PRD.model.Company;
import com.PRD.model.Department;
import com.PRD.model.UserMessage;
import com.PRD.model.UsingSite;
import com.PRD.model.VisitantMessage;
import com.PRD.service.CompanyService;
import com.PRD.service.DepartmentService;
import com.PRD.service.UsingSiteService;
import com.github.pagehelper.PageInfo;

@Controller
public class CompanyController {
	
	@Autowired
	private CompanyService companyService;
	@Autowired
	private DepartmentService departmentService;
	@Autowired
	private UsingSiteService usingSiteService;
	
	@RequestMapping("/companylist.do")
	public String selectCompanys(HttpServletRequest request, HttpServletResponse response,Company company, Integer pageNo,Integer pageSize){
		HttpSession session = request.getSession();
		UserMessage user=(UserMessage) session.getAttribute("userMessage");
		session.setAttribute("selectcom", company);
		UsingSite usingsite=new UsingSite();
		usingsite.setUsing_Unit_Id(user.getUsing_Unit_Id());
		List<UsingSite> usingsiteList = usingSiteService.selectUsingSites(usingsite);
		session.setAttribute("usingsiteList", usingsiteList);
		String a=(String) request.getAttribute("message");
		if(a=="1"){
			session.setAttribute("message", "删除成功");
		}else if(a=="0"){
			session.setAttribute("message", "删除失败");
		}else{
			session.removeAttribute("message");
		}
		company.setUsing_Unit_Id(user.getUsing_Unit_Id());
		PageInfo<Company> page=companyService.selectCompanysByPage(company, pageNo, pageSize);
		session.setAttribute("companypage", page);
		return "company/companylist.jsp";
	}
	@RequestMapping("/deletecom.do")
	public String deleteCompany(HttpServletRequest request, HttpServletResponse response,Company company){
		int a = companyService.deleteCompany(company);
		if(a==1){
			request.setAttribute("message", "1");
		}else{
			request.setAttribute("message", "0");
		}
		return "companylist.do";
	}
	@RequestMapping("/selectcom.do")
	@ResponseBody
	public String selectCompany(HttpServletRequest request, HttpServletResponse response,Company company){
		HttpSession session = request.getSession();
		Company com = companyService.selectCompanyResultMap(company);
		session.setAttribute("company", com);
		return "true";
	}
	
	@RequestMapping("/updatecom.do")
	@ResponseBody
	public String updateCompany(HttpServletRequest request, HttpServletResponse response,Company company){
		HttpSession session = request.getSession();
		int a=companyService.updateCompany(company);
		UserMessage user=(UserMessage) session.getAttribute("userMessage");
		Company com=new Company();
		com.setUsing_Unit_Id(user.getUsing_Unit_Id());
		PageInfo<Company> page=companyService.selectCompanysByPage(com, null, null);
		session.setAttribute("companypage", page);
		String str="";
		if(a==1){
			str="true";
		}else{
			str="false";
		}
		return str;
	}
	@RequestMapping("/addcom.do")
	public String addCompany(HttpServletRequest request, HttpServletResponse response,Company company){
		HttpSession session = request.getSession();
		UserMessage user=(UserMessage) session.getAttribute("userMessage");
		int a = companyService.addComDep(company);
		company=new Company();
		company.setUsing_Unit_Id(user.getUsing_Unit_Id());
		PageInfo<Company> page=companyService.selectCompanysByPage(company, null, null);
		session.setAttribute("companypage", page);
		return "close.jsp";
	}
	@RequestMapping("/deletedep.do")
	@ResponseBody
	public String deleteDepartment(HttpServletRequest request, HttpServletResponse response,Department department){
		HttpSession session = request.getSession();
		int a = departmentService.deleteDepartment(department);
		String str="";
		if(a==1){
			str="true";
		}else{
			str="false";
		}
		return str;
	}
	@RequestMapping("/adddep.do")
	@ResponseBody
	public String addDepartment(HttpServletRequest request, HttpServletResponse response,Department department){
		HttpSession session = request.getSession();
		int a = departmentService.addDepartment(department);
		Company com = new Company();
		com.setCompany_Id(department.getCompany_Id());
		com = companyService.selectCompanyResultMap(com);
		session.setAttribute("company", com);
		String str="";
		if(a==1){
			str="true";
		}else{
			str="false";
		}
		return str;
	}
}
