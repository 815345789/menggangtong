package com.PRD.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import sun.misc.BASE64Decoder;

import com.PRD.dao.Token;
import com.PRD.model.*;
import com.PRD.service.*;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

@Controller
public class apiController {
	
	@Autowired
	private UserMessageService userMessageService;
	@Autowired
	private CompanyService companyService;
	@Autowired
	private BlacklistService blacklistService;
	@Autowired
	private DepartmentService departmentService;
	@Autowired
	private EmployeesService employeesService;
	@Autowired
	private EquipmentService equipmentService;
	@Autowired
	private UsingSiteService usingSiteService;
	@Autowired
	private UsingUnitService usingUnitService;
	@Autowired
	private VisitantMessageService visitantMessageService;
	@Autowired
	private FeedbackService feedbackService;
	
	/**
	 * 登陆
	 * */
    @RequestMapping(value="/api/applogin.do",method=RequestMethod.GET )
    @ResponseBody
    public String appLogin(HttpServletRequest request,UserMessage user,String equipment){
    	HttpSession session = request.getSession();
    	String str="";		
		Map<String, String> newmap =new HashMap<String, String>(); 	
   		UserMessage loginUser=userMessageService.selectUser(user);
    	session.setAttribute("usermessage", loginUser);
    	session.setAttribute("equipment", equipment);
    	if(loginUser!=null){
    		UsingUnit usingUnit= new UsingUnit();
   			usingUnit.setUsing_Unit_Id(loginUser.getUsing_Unit_Id());
   			usingUnit = usingUnitService.selectUsingUnit(usingUnit);
   			String token=Token.getTokenString(session);
   			newmap.put("code", "1");
    		newmap.put("token", token);
    		newmap.put("user_name", loginUser.getUsermessage_name());
    		newmap.put("usingUnitName", usingUnit.getUsing_Unit_Name());
    	}else{
    		newmap.put("code", "0");
    	}
    	str=JSONArray.toJSONString(newmap);
    	return str;
    	
    }
	/**
	 * 上传登记数据
	 * */
    @RequestMapping(value="/api/addvisitentmessage.do",method=RequestMethod.POST )
    @ResponseBody
    public String addVisitantMessage(HttpServletRequest request,String json){
    	HttpSession session = request.getSession();
    	UserMessage user=(UserMessage) session.getAttribute("usermessage");
    	String equipment_Num=(String) session.getAttribute("equipment");
    	Map<String, String> newmap =new HashMap<String, String>();
    	String str="";
    	String path="/menggangtong/WebContent/headimg";

    	try {
    		VisitantMessage vm=new VisitantMessage();
    		JSONObject myJsonObject = JSONObject.parseObject(json);
    		File imageFile = new File(path+myJsonObject.getString("name")+".jsp"); 
    		OutputStream out = new FileOutputStream(imageFile);
    		String head=myJsonObject.getString("head");
    		BASE64Decoder decoder = new BASE64Decoder();
    		byte[] b=decoder.decodeBuffer(head);
    		for (int i = 0; i < b.length; i++) {
				if(b[i] < 0){
					b[i] +=256;
				}
			}
    	    out.write(b);
    		out.flush();
    		out.close();
    		vm.setVisitant_Headurl(imageFile.getPath());
    		vm.setVisitant_Address(myJsonObject.getString("address"));
    		vm.setVisitant_Birth(myJsonObject.getString("birth"));
    		vm.setVisitant_Cardno(myJsonObject.getString("cardno"));
    		vm.setVisitant_Entry_Time(myJsonObject.getString("entry_time"));
    		vm.setVisitant_Out_Time(myJsonObject.getString("out_time"));
    		vm.setVisitant_Isleave(myJsonObject.getIntValue("isLeave"));
    		vm.setVisitant_Name(myJsonObject.getString("name"));
    		vm.setVisitant_Nation(myJsonObject.getString("nation"));
    		vm.setVisitant_Police(myJsonObject.getString("police"));
    		vm.setVisitant_Sex((myJsonObject.getString("sex").equals("男")? 0:1));
    		vm.setVisitant_Num(myJsonObject.getIntValue("num"));
    		vm.setVisitant_Timestamp(myJsonObject.getString("timestamp"));
    		vm.setVisitant_Companyname(myJsonObject.getString("company_name"));
    		vm.setVisitant_Matter(myJsonObject.getString("matter"));
    		vm.setVisitant_Phone(myJsonObject.getString("phone"));
    		String company_Name = myJsonObject.getString("by_company");
    		String department_Name = myJsonObject.getString("by_department");
    		String employees_Name = myJsonObject.getString("by_name");
    		String employees_Phone = myJsonObject.getString("by_phone");
    		Company company = new Company();
    		company.setCompany_Name(company_Name);
    		company=companyService.selectCompany(company);
    		Department department = new Department();
    		department.setCompany_Id(company.getCompany_Id());
    		department.setDepartment_Name(department_Name);
    		department=departmentService.selectDepartment(department);
    		Employees employees = new Employees();
    		employees.setDepartment_Id(department.getDepartment_Id());
    		employees.setEmployees_Name(employees_Name);
    		employees.setEmployees_Phone(employees_Phone);
    		employees=employeesService.selectEmployees(employees);
    		vm.setVisitant_Surveyed_Id(employees.getEmployees_Id());
    		Equipment equipment = new Equipment();
    		equipment.setEquipment_Num(equipment_Num);
    		equipment = equipmentService.selectEquipment(equipment);
    		vm.setVisitant_unit_id(user.getUsing_Unit_Id());
    		vm.setUsing_Site_Id(equipment.getUsing_Site_Id());
    		vm.setVisitant_Found_Id(user.getUser_Id());
    		int a = visitantMessageService.addVisitantMessage(vm);
    		if(a==1){
    			newmap.put("code", "1");
    			newmap.put("message", "添加成功");
    		}else{
    			newmap.put("code", "0");
    			newmap.put("message", "添加失败");
    		}
		} catch (Exception e) {
			newmap.put("code", "2");
			newmap.put("message", "参数错误");
		}finally{
			str=JSONArray.toJSONString(newmap);
			return str;
		}
    }
	/**
	 * 上传被访人数据
	 * */
    @RequestMapping(value="/api/addemployees.do",method=RequestMethod.POST )
    @ResponseBody
    public String addEmployees(HttpServletRequest request,String json){
    	HttpSession session = request.getSession();
    	UserMessage user=(UserMessage) session.getAttribute("usermessage");
    	String equipment_Num=(String) session.getAttribute("equipment");
    	Map<String, String> newmap =new HashMap<String, String>();
    	String str="";
    	int b =3;
    	try {
    		JSONObject myJsonObject = JSONObject.parseObject(json);
    		Company company = new Company();
    		Department department = new Department();
    		Employees employees = new Employees();
    		Equipment equipment = new Equipment();
    		equipment.setEquipment_Num(equipment_Num);
    		equipment = equipmentService.selectEquipment(equipment);
    		company.setCompany_Address(myJsonObject.getString("address"));
    		company.setCompany_Name(myJsonObject.getString("company"));
    		company.setUsing_Site_Id(equipment.getUsing_Site_Id());
    		Company newcompany = new Company();
    		newcompany=companyService.selectCompany(company);
    		if(newcompany == null ){
    			companyService.addCompany(company);
    			newcompany=company;
    		}
    		department.setDepartment_Name(myJsonObject.getString("department"));
    		department.setCompany_Id(newcompany.getCompany_Id());
    		Department newdepartment = new Department();
    		newdepartment=departmentService.selectDepartment(department);
    		if(newdepartment == null){
    			departmentService.addDepartment(department);
    			newdepartment=department;
    		}
    		employees.setEmployees_Job(myJsonObject.getString("job"));
    		employees.setEmployees_Name(myJsonObject.getString("name"));
    		employees.setEmployees_Phone(myJsonObject.getString("phone"));
    		employees.setEmployees_Sex((myJsonObject.getString("sex").equals("男")? 0:1));
    		Employees newemployees = new Employees();
    		newemployees=employeesService.selectEmployees(employees);
    		if(newemployees == null){
    			employees.setDepartment_Id(newdepartment.getDepartment_Id());
    			b=employeesService.addEmployees(employees);
    		}else{
    			b=employeesService.updateEmployees(employees);
    			b=3;
    		}
    		if(b==1){
    			newmap.put("code", "1");
    			newmap.put("message", "添加成功");
    		}else if(b==0){
    			newmap.put("code", "0");
    			newmap.put("message", "添加失败");
    		}else if(b==3){
    			newmap.put("code", "3");
    			newmap.put("message", "修改成功");
    		}
		} catch (Exception e) {
			newmap.put("code", "2");
			newmap.put("message", "参数错误");
		}finally{
			str=JSONArray.toJSONString(newmap);
			return str;
		}
    }
	/**
	 * 获取登记记录
	 * */
    @RequestMapping(value="/api/selectvisitantmessage.do",method=RequestMethod.GET )
    @ResponseBody
    public String selectVisitantMessage(HttpServletRequest request,String json){
    	HttpSession session = request.getSession();
    	UserMessage user=(UserMessage) session.getAttribute("usermessage");
    	String equipment_Num=(String) session.getAttribute("equipment");
    	Map<String, Object> newmap =new HashMap<String, Object>();
    	String str="";
    	try {
    		Equipment equipment = new Equipment();
    		equipment.setEquipment_Num(equipment_Num);
    		equipment=equipmentService.selectEquipment(equipment);
    		JSONObject myJsonObject = JSONObject.parseObject(json);
    		VisitantMessage vm =new VisitantMessage();
    		vm.setVisitant_Name(myJsonObject.getString("name"));
    		vm.setVisitant_Entry_Time(myJsonObject.getString("time"));
    		vm.setVisitant_Cardno(myJsonObject.getString("cardno"));
    		vm.setUsing_Site_Id(equipment.getUsing_Site_Id());
    		List<VisitantMessage> vmlist=visitantMessageService.selectVisitantMessages(vm);
    		if(vmlist == null){
    			newmap.put("code", "0");
    			newmap.put("message", "没有数据");
    		}else{
    			newmap.put("code", "1");
    			newmap.put("message", "查询成功");
    			newmap.put("result", vmlist);
    			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    			newmap.put("time", df.format(new Date()));
    		}
		} catch (Exception e) {
			newmap.put("code", "2");
			newmap.put("message", "参数错误");
		}finally{
			str=JSONArray.toJSONString(newmap);
			return str;
		}
    }
	/**
	 * 访客签离
	 * */
    @RequestMapping(value="/api/updatevisitantmessage.do",method=RequestMethod.POST )
    @ResponseBody
    public String updateVisitantMessage(HttpServletRequest request,String json){
    	HttpSession session = request.getSession();
    	UserMessage user=(UserMessage) session.getAttribute("usermessage");
    	Map<String, Object> newmap =new HashMap<String, Object>();
    	String str="";
    	try {
    		JSONObject myJsonObject = JSONObject.parseObject(json);
    		VisitantMessage vm=new VisitantMessage();
    		vm.setVisitant_Cardno(myJsonObject.getString("cardno"));
    		vm.setVisitant_Timestamp(myJsonObject.getString("timestamp"));
    		vm.setVisitant_Out_Time(myJsonObject.getString("out_time"));
    		vm.setVisitant_Isleave(1);
    		int a=visitantMessageService.updateVisitantMessage(vm);
    		if(a==1){
    			newmap.put("code", "1");
    			newmap.put("message", "签离成功");
    		}else{
    			newmap.put("code", "0");
    			newmap.put("message", "签离失败");
    		}
		} catch (Exception e) {
			newmap.put("code", "2");
			newmap.put("message", "参数错误");
		}finally{
			str=JSONArray.toJSONString(newmap);
			return str;
		}
    }
    /**
	 * 上传设备数据
	 * */
    @RequestMapping(value="/api/addequipment.do",method=RequestMethod.POST )
    @ResponseBody
    public String addEquipment(HttpServletRequest request,String json){
    	HttpSession session = request.getSession();
    	UserMessage user=(UserMessage) session.getAttribute("usermessage");
    	Map<String, Object> newmap =new HashMap<String, Object>();
    	String str="";
    	int a=3;
    	try {
    		JSONObject myJsonObject = JSONObject.parseObject(json);
    		Equipment equipment=new Equipment();
    		equipment.setEquipment_Name(myJsonObject.getString("name"));
    		equipment.setEquipment_Num(myJsonObject.getString("num"));
    		UsingUnit uu=new UsingUnit();
    		uu.setUsing_Unit_Name(myJsonObject.getString("using_unit_name"));
    		uu=usingUnitService.selectUsingUnit(uu);
    		if(uu==null){
    			a=2;
    		}else{
    			equipment.setUsing_Unit_Id(uu.getUsing_Unit_Id());
    			a=equipmentService.addEquipment(equipment);
    		}
    		if(a==0){
    			newmap.put("code", "0");
    			newmap.put("message", "添加失败");
    		}else if(a==2){
    			newmap.put("code", "0");
    			newmap.put("message", "添加失败,没有该单位");
    		}else if(a==1){
    			newmap.put("code", "1");
    			newmap.put("message", "添加成功");
    		}
		} catch (Exception e) {
			newmap.put("code", "2");
			newmap.put("message", "参数错误");
		}finally{
			str=JSONArray.toJSONString(newmap);
			return str;
		}
    }
    /**
	 * 反馈信息
	 * */
    @RequestMapping(value="/api/addfeedback.do",method=RequestMethod.POST )
    @ResponseBody
    public String addFeedback(HttpServletRequest request,String json){
    	HttpSession session = request.getSession();
    	UserMessage user=(UserMessage) session.getAttribute("usermessage");
    	Map<String, Object> newmap =new HashMap<String, Object>();
    	String str="";
    	try {
    		JSONObject myJsonObject = JSONObject.parseObject(json);
    		Feedback feedback = new Feedback();
    		UsingUnit usingunit=new UsingUnit();
    		
    		feedback.setFeedback_Notes(myJsonObject.getString("feedback"));
    		usingunit.setUsing_Unit_Name(myJsonObject.getString("company_name"));
    		usingunit=usingUnitService.selectUsingUnit(usingunit);
    		if(usingunit != null){
    			feedback.setUsing_Unit_Id(usingunit.getUsing_Unit_Id());
    		}
    		int a =feedbackService.addFeedback(feedback);
    		if(a==1){
    			newmap.put("code", "1");
    			newmap.put("message", "添加成功");
    		}else{
    			newmap.put("code", "0");
    			newmap.put("message", "添加失败");
    		}
		} catch (Exception e) {
			newmap.put("code", "2");
			newmap.put("message", "参数错误");
		}finally{
			str=JSONArray.toJSONString(newmap);
			return str;
		}
    }
    /**
	 * 获取被访人列表
	 * */
    @RequestMapping(value="/api/selectemployees.do",method=RequestMethod.POST )
    @ResponseBody
    public String selectEmployees(HttpServletRequest request,String json){
    	HttpSession session = request.getSession();
    	UserMessage user=(UserMessage) session.getAttribute("usermessage");
    	String equipment_Num=(String) session.getAttribute("equipment");
    	Map<String, Object> newmap =new HashMap<String, Object>();
    	String str="";
    	try {
    		JSONObject myJsonObject = JSONObject.parseObject(json);
    		Equipment equipment = new Equipment();
    		equipment.setEquipment_Num(equipment_Num);
    		equipment=equipmentService.selectEquipment(equipment);
    		if(equipment!=null){
    			Company company = new Company();
        		company.setUsing_Site_Id(equipment.getUsing_Site_Id());
        		List<Company> comlist=companyService.selectCompanysResultMap(company);
        		List<ByEmployees> bemplist= new ArrayList<ByEmployees>();
        		for (int i = 0; i < comlist.size(); i++) {
					for (int j = 0; j < comlist.get(i).getDepList().size(); j++) {
						Employees emp = new Employees();
						emp.setDepartment_Id(comlist.get(i).getDepList().get(j).getDepartment_Id());;
						List<Employees> emplist=employeesService.selectEmployeess(emp);
						for (int k = 0; k < emplist.size(); k++) {
							ByEmployees bemp = new ByEmployees();
							bemp.setAddress(comlist.get(i).getCompany_Address());
							bemp.setCompany(comlist.get(i).getCompany_Name());
							bemp.setDepartment(comlist.get(i).getDepList().get(j).getDepartment_Name());
							bemp.setJob(emplist.get(k).getEmployees_Job());
							bemp.setName(emplist.get(k).getEmployees_Name());
							bemp.setPhone(emplist.get(k).getEmployees_Phone());
							bemp.setSex(emplist.get(k).getEmployees_Sex()==0? "男":"女");
							bemplist.add(bemp);
						}
					}
				}
        		newmap.put("code", "1");
    			newmap.put("message", "查询成功");
    			newmap.put("result", bemplist);
    			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    			newmap.put("time", df.format(new Date()));
    			System.out.println(bemplist);
    			
    		}else{
    			newmap.put("code", "0");
    			newmap.put("message", "查询失败，设备未定位");
    		}
		} catch (Exception e) {
			newmap.put("code", "2");
			newmap.put("message", "参数错误");
		}finally{
			str=JSONArray.toJSONString(newmap);
			return str;
		}
    }
    
    /**
	 * 批量上传被访人数据
	 * */
    @RequestMapping(value="/api/addemployeess.do",method=RequestMethod.POST )
    @ResponseBody
    public String addEmployeess(HttpServletRequest request,String json){
    	HttpSession session = request.getSession();
    	UserMessage user=(UserMessage) session.getAttribute("usermessage");
    	String equipment_Num=(String) session.getAttribute("equipment");
    	Map<String, String> newmap =new HashMap<String, String>();
    	String str="";
    	int a =3;
    	boolean b=true;
    	try {
    		JSONArray myJsonArray = JSONArray.parseArray(json);
    		for (int i = 0; i < myJsonArray.size(); i++) {
				JSONObject myJsonObject = myJsonArray.getJSONObject(i);
				Company company = new Company();
	    		Department department = new Department();
	    		Employees employees = new Employees();
	    		Equipment equipment = new Equipment();
	    		equipment.setEquipment_Num(equipment_Num);
	    		equipment = equipmentService.selectEquipment(equipment);
	    		company.setCompany_Address(myJsonObject.getString("address"));
	    		company.setCompany_Name(myJsonObject.getString("company"));
	    		company.setUsing_Site_Id(equipment.getUsing_Site_Id());
	    		Company newcompany = new Company();
	    		newcompany=companyService.selectCompany(company);
	    		if(newcompany == null ){
	    			companyService.addCompany(company);
	    			newcompany=company;
	    		}
	    		department.setDepartment_Name(myJsonObject.getString("department"));
	    		department.setCompany_Id(newcompany.getCompany_Id());
	    		Department newdepartment = new Department();
	    		newdepartment=departmentService.selectDepartment(department);
	    		if(newdepartment == null){
	    			departmentService.addDepartment(department);
	    			newdepartment=department;
	    		}
	    		employees.setEmployees_Job(myJsonObject.getString("job"));
	    		employees.setEmployees_Name(myJsonObject.getString("name"));
	    		employees.setEmployees_Phone(myJsonObject.getString("phone"));
	    		employees.setEmployees_Sex((myJsonObject.getString("sex").equals("男")? 0:1));
	    		Employees newemployees = new Employees();
	    		newemployees=employeesService.selectEmployees(employees);
	    		if(newemployees == null){
	    			employees.setDepartment_Id(newdepartment.getDepartment_Id());
	    			a=employeesService.addEmployees(employees);
	    		}else{
	    			a=employeesService.updateEmployees(employees);
	    			a=3;
	    		}
	    		if(a!=1||a!=3){
	    			b=false;
	    			break;
	    			
	    		}    		
			}
    		if(b==true){
    			newmap.put("code", "1");
    			newmap.put("message", "批量添加成功");
    		}else if(b==false){
    			newmap.put("code", "0");
    			newmap.put("message", "批量添加失败");
    		}
		} catch (Exception e) {
			newmap.put("code", "2");
			newmap.put("message", "参数错误");
		}finally{
			str=JSONArray.toJSONString(newmap);
			return str;
		}
    }
    /**
	 * 验证登陆
	 * */
    @RequestMapping(value="/api/apphavelogin.do",method=RequestMethod.GET )
    @ResponseBody
    public String appHaveLogin(HttpServletRequest request){
    	HttpSession session = request.getSession();
    	Map<String, String> newmap =new HashMap<String, String>();
    	String str="";
    	if(Token.isTokenStringVslid(request.getParameter(Token.TOKEN_STRING_NAME), session)){
    		newmap.put("code", "1");
    	}else{
    		newmap.put("code", "0");
    	}
    	str=JSONArray.toJSONString(newmap);
    	return str;
    	
    }
    /**
	 * 批量上传登记数据
	 * */
    @RequestMapping(value="/api/addvisitentmessages.do",method=RequestMethod.POST )
    @ResponseBody
    public String addVisitantMessages(HttpServletRequest request,String json){
    	HttpSession session = request.getSession();
    	UserMessage user=(UserMessage) session.getAttribute("usermessage");
    	String equipment_Num=(String) session.getAttribute("equipment");
    	Map<String, String> newmap =new HashMap<String, String>();
    	String str="";
    	String path="/menggangtong/WebContent/headimg";
        int a=-1;
    	try {
        	
        	List<VisitantMessage> vmList=new ArrayList<VisitantMessage>();
        	JSONArray myJsonArray = JSONArray.parseArray(json);
    		for (int i = 0; i < myJsonArray.size(); i++) {
    			VisitantMessage vm=new VisitantMessage();
        		JSONObject myJsonObject = myJsonArray.getJSONObject(i);
        		File imageFile = new File(path+myJsonObject.getString("name")+".jsp"); 
        		OutputStream out = new FileOutputStream(imageFile);
        		String head=myJsonObject.getString("head");
        		BASE64Decoder decoder = new BASE64Decoder();
        		byte[] b=decoder.decodeBuffer(head);
        		for (int k = 0; k < b.length; k++) {
    				if(b[k] < 0){
    					b[k] +=256;
    				}
    			}
        	    out.write(b);
        		out.flush();
        		out.close();
        		vm.setVisitant_Headurl(imageFile.getPath());
        		vm.setVisitant_Address(myJsonObject.getString("address"));
        		vm.setVisitant_Birth(myJsonObject.getString("birth"));
        		vm.setVisitant_Cardno(myJsonObject.getString("cardno"));
        		vm.setVisitant_Entry_Time(myJsonObject.getString("entry_time"));
        		vm.setVisitant_Out_Time(myJsonObject.getString("out_time"));
        		vm.setVisitant_Isleave(myJsonObject.getIntValue("isLeave"));
        		vm.setVisitant_Name(myJsonObject.getString("name"));
        		vm.setVisitant_Nation(myJsonObject.getString("nation"));
        		vm.setVisitant_Police(myJsonObject.getString("police"));
        		vm.setVisitant_Sex((myJsonObject.getString("sex").equals("男")? 0:1));
        		vm.setVisitant_Num(myJsonObject.getIntValue("num"));
        		vm.setVisitant_Timestamp(myJsonObject.getString("timestamp"));
        		vm.setVisitant_Companyname(myJsonObject.getString("company_name"));
        		vm.setVisitant_Matter(myJsonObject.getString("matter"));
        		vm.setVisitant_Phone(myJsonObject.getString("phone"));
        		String company_Name = myJsonObject.getString("by_company");
        		String department_Name = myJsonObject.getString("by_department");
        		String employees_Name = myJsonObject.getString("by_name");
        		String employees_Phone = myJsonObject.getString("by_phone");
        		Company company = new Company();
        		company.setCompany_Name(company_Name);
        		company=companyService.selectCompany(company);
        		Department department = new Department();
        		department.setCompany_Id(company.getCompany_Id());
        		department.setDepartment_Name(department_Name);
        		department=departmentService.selectDepartment(department);
        		Employees employees = new Employees();
        		employees.setDepartment_Id(department.getDepartment_Id());
        		employees.setEmployees_Name(employees_Name);
        		employees.setEmployees_Phone(employees_Phone);
        		employees=employeesService.selectEmployees(employees);
        		vm.setVisitant_Surveyed_Id(employees.getEmployees_Id());
        		Equipment equipment = new Equipment();
        		equipment.setEquipment_Num(equipment_Num);
        		equipment = equipmentService.selectEquipment(equipment);
        		vm.setVisitant_unit_id(user.getUsing_Unit_Id());
        		vm.setUsing_Site_Id(equipment.getUsing_Site_Id());
        		vm.setVisitant_Found_Id(user.getUser_Id());
        		vmList.add(vm);
    		}
    		a=visitantMessageService.addVisitantMessages(vmList);
    		if(a>=1){
    			newmap.put("code", "1");
    			newmap.put("message", "添加成功");
    		}else{
    			newmap.put("code", "0");
    			newmap.put("message", "添加失败");
    		}
		} catch (Exception e) {
			newmap.put("code", "2");
			newmap.put("message", "参数错误");
		}finally{
			str=JSONArray.toJSONString(newmap);
			return str;
		}
    }
    /**
	 * 批量访客签离
	 * */
    @RequestMapping(value="/api/updatevisitantmessages.do",method=RequestMethod.POST )
    @ResponseBody
    public String updateVisitantMessages(HttpServletRequest request,String json){
    	HttpSession session = request.getSession();
    	UserMessage user=(UserMessage) session.getAttribute("usermessage");
    	Map<String, Object> newmap =new HashMap<String, Object>();
    	String str="";
    	try {
    		JSONArray myJsonArray = JSONArray.parseArray(json);
    		List<VisitantMessage> vmList = new ArrayList<VisitantMessage>();
    		for (int i = 0; i < myJsonArray.size(); i++) {
    			JSONObject myJsonObject = myJsonArray.getJSONObject(i);
        		VisitantMessage vm=new VisitantMessage();
        		vm.setVisitant_Cardno(myJsonObject.getString("cardno"));
        		vm.setVisitant_Timestamp(myJsonObject.getString("timestamp"));
        		vm.setVisitant_Out_Time(myJsonObject.getString("out_time"));
        		vm.setVisitant_Isleave(1);
        		vmList.add(vm);	
			}
    		int a=visitantMessageService.updateVisitantMessages(vmList);
    		if(a>=1){
    			newmap.put("code", "1");
    			newmap.put("message", "签离成功");
    		}else{
    			newmap.put("code", "0");
    			newmap.put("message", "签离失败");
    		}
		} catch (Exception e) {
			newmap.put("code", "2");
			newmap.put("message", "参数错误");
		}finally{
			str=JSONArray.toJSONString(newmap);
			return str;
		}
    }
    
}

