package com.PRD.controller;

import java.util.HashMap;
import java.util.Map;

import javax.json.JsonArray;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.PRD.dao.RandomValidateCode;
import com.PRD.model.UserMessage;
import com.PRD.service.UserMessageService;
import com.PRD.service.VisitantMessageService;
import com.alibaba.fastjson.JSONArray;

@Controller
public class LoginController {
	
	@Autowired
	private UserMessageService userMessageService;
	
	
	
	@RequestMapping("/login.do")
	public String login(HttpServletRequest request,UserMessage user){
		UserMessage loginUser=userMessageService.selectUser(user);
		String str="";

		HttpSession session = request.getSession();
		if(loginUser==null){
			str="login.jsp";
			request.setAttribute("message", "用户名或密码错误");
		}else{
			str="index.jsp";
			session.setAttribute("userMessage", loginUser);
		}
		return str;
	}
	
	//登录页面生成验证码
	@RequestMapping("/getVerify.do")
	public void getVerify(HttpServletRequest request, HttpServletResponse response){
		 response.setContentType("image/jpeg");//设置相应类型,告诉浏览器输出的内容为图片  
	     response.setHeader("Pragma", "No-cache");//设置响应头信息，告诉浏览器不要缓存此内容  
	     response.setHeader("Cache-Control", "no-cache"); 
	     response.setDateHeader("Expire", 0); 
	     RandomValidateCode randomValidateCode = new RandomValidateCode(); 
	     try { 
	         randomValidateCode.getRandCode(request, response);//输出验证码图片方法  
	     } catch (Exception e) { 
	         e.printStackTrace(); 
	     } 
	}
	
	//登录页面校验验证码
    @RequestMapping("/checkVerify.do")
    @ResponseBody
    public String checkVerify(String inputStr, HttpSession session){
        //从session中获取随机数
        String random = (String) session.getAttribute("RANDOMVALIDATECODEKEY");
        if(random.equals(inputStr)){
            return "true";//验证码正确
        }else{
            return "false";//验证码错误
        }
    } 
}

