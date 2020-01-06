package com.study.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by tianshujian
 * Create Date: 2019/12/20 11:50
 * Description: ${DESCRIPTION}
 */
public class User implements Serializable {
	private Long id;
	private String name;
	private Integer age;
	private Boolean sex;
	private String nickName;
	private Date birthDay;
	private Double salary;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Boolean getSex() {
		return sex;
	}

	public void setSex(Boolean sex) {
		this.sex = sex;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public Date getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}
}