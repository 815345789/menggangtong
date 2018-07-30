package com.PRD.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.PRD.dao.BlackExcelUtils;
import com.PRD.model.Blacklist;
import com.PRD.model.Company;
import com.PRD.model.UserMessage;
import com.PRD.service.BlacklistService;
import com.github.pagehelper.PageInfo;

@Controller
public class BlacklistController {
	
	@Autowired
	private BlacklistService blacklistService;
	
	@RequestMapping("/blacklists.do")
	public String selectBlacklists(HttpServletRequest request, HttpServletResponse response,Blacklist blacklist, Integer pageNo,Integer pageSize){
		HttpSession session = request.getSession();
		session.setAttribute("selectblack", blacklist);
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
		blacklist.setUsing_Unit_Id(user.getUsing_Unit_Id());
		PageInfo<Blacklist> page=blacklistService.selectBlacklistsByPage(blacklist, pageNo, pageSize);
		session.setAttribute("blistspage", page);
		return "blacklist/blacklistlist.jsp";
	}
	
	@RequestMapping(value="/deleteblack.do",method=RequestMethod.POST)
	public String deleteBlacklist(HttpServletRequest request, HttpServletResponse response,Blacklist blacklist){
		HttpSession session = request.getSession();
		int a=blacklistService.deleteBlacklist(blacklist);
		if(a==1) 
			session.setAttribute("message", "1");
		else if(a==0)
			session.setAttribute("message", "0");
		return "blacklists.do";
	}
	@RequestMapping("/addblack.do")
	public String addBlacklist(HttpServletRequest request, HttpServletResponse response,Blacklist blacklist){
		HttpSession session = request.getSession();
		int a=blacklistService.addBlacklist(blacklist);
		if(a==1)
			session.setAttribute("message", "4");
		else if(a==0)
			session.setAttribute("message", "5");
		return "close.jsp";
	}
	@RequestMapping("/selectblack.do")
	@ResponseBody
	public String selectBlacklist(HttpServletRequest request, HttpServletResponse response,Blacklist blacklist){
		HttpSession session = request.getSession();
		blacklist=blacklistService.selectBlacklist(blacklist);
		session.setAttribute("blacklist", blacklist);
		return "true";
	}
	@RequestMapping("/updateblack.do")
	@ResponseBody
	public String updateBlacklist(HttpServletRequest request, HttpServletResponse response,Blacklist blacklist){
		HttpSession session = request.getSession();
		int a=blacklistService.updateBlacklist(blacklist);
		if(a==1)
			session.setAttribute("message", "2");
		else if(a==0)
			session.setAttribute("message", "3");
		return "true";
	}
	@RequestMapping("/addblacks.do")
	public String addBlacklists(HttpServletRequest request,
            @RequestParam("file") MultipartFile file){
		BlackExcelUtils blackreadExcel=new BlackExcelUtils();
		HttpSession session = request.getSession();
		int a = 0;
		if(!file.isEmpty()){
			List<Blacklist> blists = blackreadExcel.getBlackExcelInfo(file.getOriginalFilename(), file);
			if(blists==null){
				request.setAttribute("message", blackreadExcel.getErrorInfo());
			}else{
				a=blacklistService.addBiacklists(blists);
			}
		}
		if(a>=1){
			session.setAttribute("message", "4");
		}else{
			session.setAttribute("message", "5");
		}
		return "close.jsp";
	}
}
