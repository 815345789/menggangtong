package com.PRD.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.PRD.model.Company;
import com.PRD.model.UserMessage;
import com.PRD.model.UsingSite;
import com.PRD.service.UsingSiteService;
import com.github.pagehelper.PageInfo;

@Controller
public class UsingSiteController {
	
	@Autowired
	private UsingSiteService usingSiteService;
	
	@RequestMapping("/usingsitelist.do")
	public String selectUsingSites(HttpServletRequest request, 
			HttpServletResponse response,UsingSite usingSite, Integer pageNo,
			Integer pageSize){
		HttpSession session = request.getSession();
		UserMessage user=(UserMessage) session.getAttribute("userMessage");
		usingSite.setUsing_Unit_Id(user.getUsing_Unit_Id());
		PageInfo<UsingSite> page = usingSiteService.selectUsingSitesByPage(usingSite, pageNo, pageSize);
		session.setAttribute("usingsitepage", page);
		return "usingsite/usingsitelist.jsp";
	}
	
	@RequestMapping("/deleteus.do")
	public String deleteUsingSite(HttpServletRequest request, 
			HttpServletResponse response,UsingSite usingSite){
		HttpSession session = request.getSession();
		int a=usingSiteService.deleteUsingSite(usingSite);
		if(a==1){
			return "usingsitelist.do";
		}else{
			request.setAttribute("message", "删除失败");
			return "error.jsp";
		}
		
	}
	
	@RequestMapping("/addus.do")
	public String addUsingSite(HttpServletRequest request, 
			HttpServletResponse response,UsingSite usingSite){
		HttpSession session = request.getSession();
		UserMessage user=(UserMessage) session.getAttribute("userMessage");
		usingSite.setUsing_Unit_Id(user.getUsing_Unit_Id());
		int a=usingSiteService.addUsingSite(usingSite);
		if(a==1){
			return "close.jsp";
		}else{
			request.setAttribute("message", "添加失败");
			return "error.jsp";
		}
	}
	
	@RequestMapping("/selectus.do")
	@ResponseBody
	public String selectUsingSite(HttpServletRequest request, 
			HttpServletResponse response,UsingSite usingSite){
		HttpSession session = request.getSession();
		usingSite=usingSiteService.selectUsingSite(usingSite);
		session.setAttribute("usingsite", usingSite);
		return "true";
	}
	
	@RequestMapping("/updateus.do")
	@ResponseBody
	public String updateUsingSite(HttpServletRequest request, 
			HttpServletResponse response,UsingSite usingSite){
		HttpSession session = request.getSession();
		int a=usingSiteService.updateUsingSite(usingSite);
		if(a==1){
			return "true";
		}else{
			return "false";
		}
	}
}
