package com.PRD.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.PRD.model.Blacklist;
import com.PRD.model.Company;
import com.PRD.model.Department;
import com.PRD.model.Employees;
import com.PRD.model.UserMessage;
import com.PRD.model.UsingSite;
import com.PRD.service.CompanyService;
import com.PRD.service.DepartmentService;
import com.PRD.service.EmployeesService;
import com.PRD.service.UsingSiteService;
import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageInfo;

@Controller
public class EmployeesController {
	
	@Autowired
	private EmployeesService employeesService;
	@Autowired
	private CompanyService companyService;
	@Autowired
	private DepartmentService departmentService;
	@Autowired
	private UsingSiteService usingSiteService;
		
	@RequestMapping("/employeeslist.do")
	public String selectEmployeess(HttpServletRequest request, HttpServletResponse response,Employees employees, Integer pageNo,Integer pageSize){
		HttpSession session = request.getSession();
		session.setAttribute("selectemployees", employees);
		String a=(String) session.getAttribute("message");
		if(a=="1"){
			session.setAttribute("message", "删除成功");
		}else if(a=="0"){
			session.setAttribute("message", "删除失败");
		}else if(a=="2"){
			session.setAttribute("message", "修改成功");
		}else if(a=="3"){
			session.setAttribute("message", "修改成功");
		}else if(a=="4"){
			session.setAttribute("message", "添加成功");
		}else if(a=="5"){
			session.setAttribute("message", "添加失败");
		}else{
			session.removeAttribute("message");
		}
		UserMessage user=(UserMessage) session.getAttribute("userMessage");
		UsingSite usingsite=new UsingSite();
		usingsite.setUsing_Unit_Id(user.getUsing_Unit_Id());
		List<UsingSite> usingsiteList = usingSiteService.selectUsingSites(usingsite);
		session.setAttribute("usingsiteList", usingsiteList);
		Company com=new Company();
		com.setUsing_Unit_Id(user.getUsing_Unit_Id());
		List<Company> comList=companyService.selectCompanysResultMap(com);
		session.setAttribute("comlist", comList);
		PageInfo<Employees> page=employeesService.selectEmployeessResultMapByPage(employees, pageNo, pageSize);
		session.setAttribute("emppage", page);
		return "employees/employeeslist.jsp";
	}
	@RequestMapping("/deleteemp.do")
	public String deleteEmployees(HttpServletRequest request, HttpServletResponse response,Employees employees){
		int a = employeesService.deleteEmployees(employees);
		if(a==1){
			request.setAttribute("message", "1");
		}else{
			request.setAttribute("message", "0");
		}
		return "employeeslist.do";
	}
	@RequestMapping("/selectemp.do")
	@ResponseBody
	public String selectEmployees(HttpServletRequest request, HttpServletResponse response,Employees employees){
		HttpSession session = request.getSession();
		Employees emp = employeesService.selectEmployeesResultMap(employees);
		session.setAttribute("employees", emp);
		return "true";
	}
	@RequestMapping("/updateemp.do")
	@ResponseBody
	public String updateEmployees(HttpServletRequest request, HttpServletResponse response,Employees employees){
		HttpSession session = request.getSession();
		Department dep=employees.getDepartment();
		dep.setCompany_Id(employees.getCompany().getCompany_Id());
		dep=departmentService.selectDepartment(dep);
		employees.setDepartment_Id(dep.getDepartment_Id());
		int a=employeesService.updateEmployees(employees);
		String str="";
		if(a==1){
			str="true";
		}else{
			str="false";
		}
		return str;
	}
	@RequestMapping("/addemp.do")
	public String addEmployees(HttpServletRequest request, HttpServletResponse response,Employees employees){
		HttpSession session = request.getSession();
		UserMessage user=(UserMessage) session.getAttribute("userMessage");
		Company com=employees.getCompany();
		com=companyService.selectCompany(com);
		Department dep=employees.getDepartment();
		dep.setCompany_Id(com.getCompany_Id());
		dep=departmentService.selectDepartment(dep);
		employees.setDepartment_Id(dep.getDepartment_Id());
		int a = employeesService.addEmployees(employees);
		return "close.jsp";
	}
	@RequestMapping("/selectecd.do")
	@ResponseBody
	public String selectEcd(HttpServletRequest request, HttpServletResponse response,Department department){
		HttpSession session = request.getSession();
		List<Department> deplist = departmentService.selectDepartments(department);
		String str=JSONArray.toJSONString(deplist);
		return str;
	}
}
