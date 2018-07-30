package com.PRD.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.PRD.model.Blacklist;
import com.PRD.model.Employees;

public class EmployeesExcelUtils {
	//总行数
		private int totalRows = 0;
		//总条数
		private int totalCells =0;
		//错误信息接收器
		private String errorMsg;
		//构造方法
		public EmployeesExcelUtils(){}
		//获取总行数
		public int getTotalRows(){return totalRows;}
		//获取总列数
		public int getTotalCells(){return totalCells;}
		//获取错误信息
		public String getErrorInfo() { return errorMsg; }
		
		/**
		 * 验证excel文件
		 * */
		public boolean validateExcel(String filePath){
			if(filePath == null || !(WDWUtil.isExcel2003(filePath) || WDWUtil.isExcel2007(filePath))){
	            errorMsg = "文件名不是excel格式";
	            return false;
	        }
	        return true;
		}
		/**
		 * 读取excel文件 获取客户信息
		 * */
		public List<Employees> getEmployeesExcelInfo(String fileName, MultipartFile Mfile){
			//把spring文件上传的MultipartFile转换成CommonsMultipartFile类型
			CommonsMultipartFile cf= (CommonsMultipartFile)Mfile;
			File file = new  File("D:\\employeesupload");
			//创建一个目录 （它的路径名由当前 File 对象指定，包括任一必须的父路径。）
			if(!file.exists())
				file.mkdirs();
			//新建一个文件
			File file1 = new File("D:\\fileupload" + new Date().getTime() + ".xlsx");
			//将上传的文件写入新建的文件中
	        try {
	            cf.getFileItem().write(file1);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	      //初始化黑名单的集合
	        List<Employees> elists=new ArrayList<Employees>();
	      //初始化输入流
	        InputStream is = null;
	        try{
	            //验证文件名是否合格
	            if(!validateExcel(fileName)){
	                return null;
	            }
	            //根据文件名判断文件是2003版本还是2007版本
	            boolean isExcel2003 = true;
	            if(WDWUtil.isExcel2007(fileName)){
	                isExcel2003 = false;
	            }
	            //根据新建的文件实例化输入流
	            is = new FileInputStream(file1);
	            //根据excel里面的内容读取客户信息
	            elists=getEmployeesExcelValueInfo(is,isExcel2003);
	            is.close();
	        }catch(Exception e){
	        	e.printStackTrace();
	        }finally{
	        	if(is !=null)
	            {
	                try{
	                    is.close();
	                }catch(IOException e){
	                    is = null;
	                    e.printStackTrace();
	                }
	            }
	        }
	        return elists;
		}
		/**
		 * 根据excel版本读取黑名单信息
		 * 
		 * */
		public List<Employees> getEmployeesExcelValueInfo(InputStream is,boolean isExcel2003){
			List<Employees> elists = new ArrayList<Employees>();
			try {
				/** 根据版本选择创建Workbook的方式 */
	            Workbook wb = null;
	            //当excel是2003时
	            if(isExcel2003){
	                wb = new HSSFWorkbook(is);
	            }
	            else{//当excel是2007时
	                wb = new XSSFWorkbook(is);
	            }
	            //读取Excel里面客户的信息
	            elists=readEmployeesExcelValue(wb);
			} catch (Exception e) {
				// TODO: handle exception
			}
			return elists;
		}
		/**
		 * 读取黑名单信息
		 * */
		public List<Employees> readEmployeesExcelValue(Workbook wb){
			 //得到第一个shell
	        Sheet sheet=wb.getSheetAt(0);

	        //得到Excel的行数
	        this.totalRows=sheet.getPhysicalNumberOfRows();

	        //得到Excel的列数(前提是有行数)
	        if(totalRows>=1 && sheet.getRow(0) != null){
	            this.totalCells=sheet.getRow(0).getPhysicalNumberOfCells();
	        }
	        List<Employees> elists = new ArrayList<Employees>();
	        Employees employees;
	        for (int r = 1; r < totalRows; r++) {
	        	 Row row = sheet.getRow(r);
	        	 if (row == null) continue;
	        	 employees=new Employees();
	        	 try {
	        		 Thread.currentThread().sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
	        	 for (int c = 0; c < this.totalCells; c++) {
					Cell cell =row.getCell(c);
					if(cell != null){
						if(c==0){
							employees.setBlacklist_Name(cell.getStringCellValue());//姓名
						}else if(c==1){
							blacklist.setBlacklist_sex(cell.getStringCellValue().equals("男")?0:1);
						}else if(c==2){
							blacklist.setBlacklist_Nation(cell.getStringCellValue());
						}else if(c==3){
							if(cell.getCellType()==0){
								blacklist.setBlacklist_Cardno(""+new BigDecimal(cell.getNumericCellValue()).intValue());
							}else if(cell.getCellType()==1){
								blacklist.setBlacklist_Cardno(cell.getStringCellValue());
							}
						}else if(c==4){
							blacklist.setBlacklist_Phone(new BigDecimal(cell.getNumericCellValue()).intValue());
						}else if(c==5){
							blacklist.setBlacklist_Notes(cell.getStringCellValue());
						}
						
					}
					
				}
	        	 blists.add(blacklist);
			}
	        return blists;
		}
}
