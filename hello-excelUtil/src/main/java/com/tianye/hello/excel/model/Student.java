package com.tianye.hello.excel.model;

import com.tianye.hello.excel.annotation.ExcelCell;

import java.io.Serializable;
import java.util.Date;

/**
 * @Auther: Tian.shujian 18066629
 * @Date: 2019/4/26 14:22
 * @Description:
 */
public class Student implements Serializable {

	@ExcelCell(index = 0,title = "身份证号")
	private String ID;
	@ExcelCell(index = 0,title = "姓名")
	private String name;
	@ExcelCell(index = 0,title = "年龄")
	private Integer age;
	@ExcelCell(index = 0,title = "性别")
	private String gender;
	@ExcelCell(index = 0,title = "电话号码")
	private String phone;
	@ExcelCell(index = 0,title = "地址")
	private String address;
	@ExcelCell(index = 0,title = "出生日期")
	private Date birthDay;

	public Student(String ID, String name, Integer age, String gender, String phone, String address,Date birthDay) {
		this.ID = ID;
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.phone = phone;
		this.address = address;
		this.birthDay = birthDay;
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

	public Date getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}
}
