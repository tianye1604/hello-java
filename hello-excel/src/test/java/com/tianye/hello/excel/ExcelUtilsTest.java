package com.tianye.hello.excel;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @Auther: Tian.shujian 18066629
 * @Date: 2019/4/16 10:47
 * @Description:
 */
public class ExcelUtilsTest {

	public static void main(String[] args) {
		List studentList = new ArrayList<Student>();
		studentList.add(new Student("1111","aaa",11,"男","110","北京市西城区").toJSONArray());
		studentList.add(new Student("2222","bbbb",12,"男","1101111","北京市朝阳区八里庄").toJSONArray());
		studentList.add(new Student("3333","cccc",13,"男","1102222","北京市朝阳区八里庄慈云寺").toJSONArray());
		studentList.add(new Student("4444","dddd",14,"男","110333333","北京市朝阳区八里庄慈云寺苏宁易购").toJSONArray());
		studentList.add(new Student("5555","eeee",15,"男","110111111123233","北京市朝阳区八里庄慈云寺苏宁易购14层").toJSONArray());
		String[] head = {"身份证号","姓名","年龄","性别","手机号","地址"};
		HSSFWorkbook workbook = ExcelUtils.expExcel((JSONArray) JSON.toJSON(head), JSONArray.parseArray(JSON.toJSONString(studentList)));

		ExcelUtils.outFile(workbook,"E:/tmpe/demo.xlsx");
	}


	static class Student{

		private String ID;
		private String name;
		private Integer age;
		private String gender;
		private String phone;
		private String address;

		public JSONArray toJSONArray() {
			String[] array = {ID,name,String.valueOf(age),gender,phone,address};
			return (JSONArray)JSON.toJSON(array);
		}

		public Student(String ID, String name, Integer age, String gender, String phone, String address) {
			this.ID = ID;
			this.name = name;
			this.age = age;
			this.gender = gender;
			this.phone = phone;
			this.address = address;
		}

		public String getID() {
			return ID;
		}

		public void setID(String ID) {
			this.ID = ID;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Integer getAge() {
			return age;
		}

		public void setAge(Integer age) {
			this.age = age;
		}

		public String getGender() {
			return gender;
		}

		public void setGender(String gender) {
			this.gender = gender;
		}

		public String getPhone() {
			return phone;
		}

		public void setPhone(String phone) {
			this.phone = phone;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}
	}
}