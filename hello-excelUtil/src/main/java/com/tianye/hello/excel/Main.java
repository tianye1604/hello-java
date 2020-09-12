package com.tianye.hello.excel;

import com.tianye.hello.excel.model.EnterpriseRegisterDTO;
import com.tianye.hello.excel.model.Student;
import com.tianye.hello.excel.util.ExcelUtil;
import com.tianye.hello.excel.util.RequestFieldUtil;

import java.io.*;
import java.util.*;

/**
 * @Auther: Tian.shujian 18066629
 * @Date: 2019/4/26 14:24
 * @Description:
 */
public class Main {

	public static void main(String[] args) throws IOException, IllegalAccessException {

//		String[] head = {"身份证号","姓名","年龄","性别","电话号码","地址","出生日期"};
//
//		List studentList = new ArrayList<Student>();
//		studentList.add(new Student("1111","aaa",11,"男","110","北京市西城区",new Date()));
//		studentList.add(new Student("2222","bbbb",12,"男","1101111","北京市朝阳区八里庄",new Date()));
//		studentList.add(new Student("3333","cccc",13,"男","1102222","北京市朝阳区八里庄慈云寺",new Date()));
//		studentList.add(new Student("4444","dddd",14,"男","110333333","北京市朝阳区八里庄慈云寺苏宁易购",new Date()));
//		studentList.add(new Student("5555","eeee",15,"男","110111111123233","北京市朝阳区八里庄慈云寺苏宁易购14层",new Date()));
//		File f=new File("E:/temp/学生信息表4.xls");
//		OutputStream out =new FileOutputStream(f);
//		ExcelUtil.exportExcel(studentList,"学生嘻嘻", out);

		EnterpriseRegisterDTO registerDTO = new EnterpriseRegisterDTO();
		registerDTO.setEnterpriseName("北京银行");
		registerDTO.setBankLicense("546466555");
		registerDTO.setBankcode("110110");
		registerDTO.setBankcardNo("123456789");
		registerDTO.setContact("田书建");
		registerDTO.setIdCardType("PRC_ID");

		Map<String,Object> result = RequestFieldUtil.toFieldMap(registerDTO);


		System.out.println(result);
	}
}
