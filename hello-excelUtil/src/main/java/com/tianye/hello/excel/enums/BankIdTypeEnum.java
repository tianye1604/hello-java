package com.tianye.hello.excel.enums;

import com.tianye.hello.util.enums.BankEnum;

/**
 * Created by tianshujian
 * Create Date: 2020/9/12 18:32
 * Description: ${DESCRIPTION}
 */
public enum BankIdTypeEnum implements BankEnum {
	PRC_ID("PRC_ID","身份证"),
	;

	private String name;
	private String desc;

	BankIdTypeEnum(String name, String desc) {
		this.name = name;
		this.desc = desc;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getDesc() {
		return desc;
	}
}
