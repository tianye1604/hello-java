package com.tianye.hello.excel.enums;

/**
 * Created by tianshujian
 * Create Date: 2020/9/12 18:32
 * Description: ${DESCRIPTION}
 */
public enum BankIdTypeEnum {
	PRC_ID("1","PRC_ID"),
	;

	private String code;
	private String shangHaiBankValue;

	BankIdTypeEnum(String code, String shangHaiBankValue) {
		this.code = code;
		this.shangHaiBankValue = shangHaiBankValue;
	}

}
