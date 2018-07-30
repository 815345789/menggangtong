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
import com.PRD.model.Equipment;
import com.PRD.model.UserMessage;
import com.PRD.model.UsingSite;
import com.PRD.service.EquipmentService;
import com.PRD.service.UsingSiteService;
import com.github.pagehelper.PageInfo;

@Controller
public class EquipmentController {
	@Autowired
	private EquipmentService equipmentService;
	@Autowired
	private UsingSiteService usingSiteService;
	
	@RequestMapping("/equipmentlist.do")
	public String selectEquipments(HttpServletRequest request, HttpServletResponse response,Equipment equipment, Integer pageNo,Integer pageSize){
		HttpSession session = request.getSession();
		UserMessage user=(UserMessage) session.getAttribute("userMessage");
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
		equipment.setUsing_Unit_Id(user.getUsing_Unit_Id());
		PageInfo<Equipment> page=equipmentService.selectEquipmentsByPage(equipment, pageNo, pageSize);
		session.setAttribute("equipmentpage", page);
		return "equipment/equipmentlist.jsp";
	}
	@RequestMapping("/deleteequip.do")
	public String deleteEquipment(HttpServletRequest request, HttpServletResponse response,Equipment equipment){
		int a = equipmentService.deleteEquipment(equipment);
		if(a==1){
			request.setAttribute("message", "1");
		}else{
			request.setAttribute("message", "0");
		}
		return "equipmentlist.do";
	}
	@RequestMapping("/selectequip.do")
	@ResponseBody
	public String selectEquipment(HttpServletRequest request, HttpServletResponse response,Equipment equipment){
		HttpSession session = request.getSession();
		equipment = equipmentService.selectEquipment(equipment);
		session.setAttribute("equipment", equipment);
		return "true";
	}
	
	@RequestMapping("/updateequip.do")
	@ResponseBody
	public String updateEquipment(HttpServletRequest request, HttpServletResponse response,Equipment equipment){
		HttpSession session = request.getSession();
		int a=equipmentService.updateEquipment(equipment);
		String str="";
		if(a==1){
			str="true";
		}else{
			str="false";
		}
		return str;
	}
}
