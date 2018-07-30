package com.PRD.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.PRD.model.Blacklist;
import com.PRD.model.Company;
import com.PRD.model.UserMessage;
import com.PRD.service.UserMessageService;
import com.github.pagehelper.PageInfo;

@Controller
public class UserMessageController {
	
	@Autowired
	private UserMessageService userMessageService;
	
	@RequestMapping("/usermessagelist.do")
	public String selectUserMessage(HttpServletRequest request, HttpServletResponse response,
			UserMessage user, Integer pageNo,Integer pageSize){
		HttpSession session = request.getSession();
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
		UserMessage sessionuser=(UserMessage) session.getAttribute("userMessage");
		user.setUsing_Unit_Id(sessionuser.getUsing_Unit_Id());
		PageInfo<UserMessage> page=userMessageService.selectUsersByPage(user, pageNo, pageSize);
		session.setAttribute("usermessagepage", page);
		return "usermessage/usermessagelist.jsp";
	}
	@RequestMapping("/addum.do")
	public String addUserMessage(HttpServletRequest request, 
			HttpServletResponse response,UserMessage user){
		HttpSession session = request.getSession();
		UserMessage sessionUser= (UserMessage) session.getAttribute("userMessage");
		user.setUsing_Unit_Id(sessionUser.getUsing_Unit_Id());
		user.setFound_Id(sessionUser.getUser_Id());
		int a=userMessageService.addUser(user);
		if(a==1)
			session.setAttribute("message", "4");
		else if(a==0)
			session.setAttribute("message", "5");
		return "close.jsp";
	}
	@RequestMapping(value="/deleteum.do",method=RequestMethod.GET)
	public String deleteUserMessage(HttpServletRequest request, HttpServletResponse response,UserMessage user){
		HttpSession session = request.getSession();
		int a=userMessageService.deleteUser(user);
		if(a==1) 
			session.setAttribute("message", "1");
		else if(a==0)
			session.setAttribute("message", "0");
		return "usermessagelist.do";
	}
	@RequestMapping("/selectum.do")
	@ResponseBody
	public String selectUserMessage(HttpServletRequest request, HttpServletResponse response,UserMessage user){
		HttpSession session = request.getSession();
		user=userMessageService.selectUser(user);
		session.setAttribute("user", user);
		return "true";
	}
	@RequestMapping("/updateum.do")
	@ResponseBody
	public String updateUserMessage(HttpServletRequest request, HttpServletResponse response,UserMessage user){
		HttpSession session = request.getSession();
		int a=userMessageService.updateUser(user);
		if(a==1)
			session.setAttribute("message", "2");
		else if(a==0)
			session.setAttribute("message", "3");
		return "true";
	}
}
