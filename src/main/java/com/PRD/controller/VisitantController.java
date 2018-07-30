package com.PRD.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.PRD.model.AllVisitantMessage;
import com.PRD.model.Company;
import com.PRD.model.Department;
import com.PRD.model.Employees;
import com.PRD.model.UserMessage;
import com.PRD.model.VisitantMessage;
import com.PRD.service.CompanyService;
import com.PRD.service.DepartmentService;
import com.PRD.service.EmployeesService;
import com.PRD.service.VisitantMessageService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
public class VisitantController {
	
	@Autowired
	private VisitantMessageService visitantMessageService;
	@Autowired
	private CompanyService companyService;
	@Autowired
	private DepartmentService departmentService;
	@Autowired
	private EmployeesService employeesService;
	
	@RequestMapping("/visitantList.do")
	public String visitantList(HttpServletRequest request, HttpServletResponse response,VisitantMessage visitantMessage, Integer pageNo,Integer pageSize){
		HttpSession session = request.getSession();
		String a = (String) session.getAttribute("message");
		if(a=="0"){
			session.setAttribute("message", "删除成功");
		}else if(a=="1"){
			session.setAttribute("message", "删除失败");
		}else{
			session.removeAttribute("message");
		}	
		session.setAttribute("selectvm", visitantMessage);
		UserMessage user=(UserMessage) session.getAttribute("userMessage");
		visitantMessage.setVisitant_unit_id(user.getUsing_Unit_Id());
		PageInfo<VisitantMessage> pagev = visitantMessageService.selectVisitantMessagesByPage(visitantMessage, pageNo, pageSize);
		List<VisitantMessage> list = pagev.getList();
		List<AllVisitantMessage> avmList = new ArrayList<AllVisitantMessage>();
		if(list != null){
			for (int i = 0; i < list.size(); i++) {
				AllVisitantMessage avm=selectAVM(list.get(i));
				avmList.add(avm);
			}
		}
		pageNo = pageNo == null?1:pageNo;
		pageSize = pageSize == null?10:pageSize;
		PageHelper.startPage(pageNo, pageSize);
		PageInfo<AllVisitantMessage> page=new PageInfo<AllVisitantMessage>(avmList);
		session.setAttribute("allVisitantPage", page);
		return "visitant/visitantlist.jsp";
	}
	
	@RequestMapping("/deletevm.do")
	public String deleteVisitantMessage(HttpServletRequest request, HttpServletResponse response,VisitantMessage visitantMessage){
		int a = visitantMessageService.deleteVisitantMessage(visitantMessage);
		HttpSession session = request.getSession();
		if(a==1){
			session.setAttribute("message", "0");
		}else{
			session.setAttribute("message", "1");
		}
		return "visitantList.do";
		
	}
	
	@RequestMapping("/selectavm.do")
	@ResponseBody
	public String selectVisitantMessage(HttpServletRequest request, HttpServletResponse response,VisitantMessage visitantMessage){
		VisitantMessage vm=visitantMessageService.selectVisitantMessage(visitantMessage);
		HttpSession session=request.getSession();
		session.setAttribute("allvisitantmessage", selectAVM(vm));
		return "true";
	}
	
	public AllVisitantMessage selectAVM(VisitantMessage vm){
		Employees emp=new Employees();
		Department dep=new Department();
		Company com=new Company();
		AllVisitantMessage avm=new AllVisitantMessage();
		vm.setVisitant_Cardno(vm.getVisitant_Cardno().replaceAll("(?<=[\\d]{3})\\d(?=[\\d]{4})", "*"));
		vm.setVisitant_Phone(vm.getVisitant_Phone().replaceAll("(?<=[\\d]{3})\\d(?=[\\d]{4})", "*"));
		System.out.println(vm.getVisitant_Address().indexOf("市"));
		if(vm.getVisitant_Address().indexOf("市") != -1){
			vm.setVisitant_Address(vm.getVisitant_Address().substring(1,vm.getVisitant_Address().indexOf("市")));
		}
		avm.setVisitantMessage(vm);
		emp.setEmployees_Id(vm.getVisitant_Surveyed_Id());
		emp=employeesService.selectEmployees(emp);
		emp.setEmployees_Phone(emp.getEmployees_Phone().replaceAll("(?<=[\\d]{3})\\d(?=[\\d]{4})", "*"));
		avm.setEmployees(emp);
		dep.setDepartment_Id(emp.getDepartment_Id());
		dep=departmentService.selectDepartment(dep);
		avm.setDepartment(dep);
		com.setCompany_Id(dep.getCompany_Id());
		com=companyService.selectCompany(com);
		avm.setCompany(com);
		return avm;
	}
}
