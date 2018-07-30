package com.PRD.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.PRD.service.VisitantMessageService;

@Controller
public class IndexController {
	
	@Autowired
	private VisitantMessageService visitantMessageService;
	
	@RequestMapping("/selectCount.do")
	@ResponseBody
	public String selectCount(HttpServletRequest request){
		Map<String, String> map = new HashMap<String, String>();
		map.put("hownum","0");
		map.put("time", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		List<Integer> list=new ArrayList<Integer>();
		list.add(visitantMessageService.selectCount(map)); 
		map.put("hownum", "1");
		list.add(visitantMessageService.selectCount(map)); 
		map.put("hownum", "2");
		list.add(visitantMessageService.selectCount(map));
		String str = JSONArray.toJSONString(list);
		return str;
	}

}
