package com.tianye.hello.util.enums;

/**
 * Created by tianshujian
 * Create Date: 2020/9/12 20:38
 * Description: ${DESCRIPTION}
 */
public enum BankUserRoleEnum implements BankEnum{

	GUARANTEECORP("GUARANTEECORP","担保机构"),
	COLLABORATOR("COLLABORATOR","合作机构"),
	SUPPLIER("SUPPLIER","供应商"),
	CONSUMER("CONSUMER","消费者"),
	MERCHANT("MERCHANT","商家"),
	NORMAL("NORMAL","混合角色"),
	PLATFORM_MARKETING("PLATFORM_MARKETING","平台营销款账户"),
	PLATFORM_PROFIT("PLATFORM_PROFIT","平台分润账户"),
	PLATFORM_INCOME("PLATFORM_INCOME","平台收入账户"),
	PLATFORM_FUNDS_TRANSFER("PLATFORM_FUNDS_TRANSFER","平台总账户"),
	PLATFORM_URGENT("PLATFORM_URGENT","平台垫资账户"),
	PLATFORM_MIDDLE_ACCOUNT("PLATFORM_MIDDLE_ACCOUNT","平台交易中间户"),
	;

	private String name;

	private String desc;

	BankUserRoleEnum(String name, String desc) {
		this.name = name;
		this.desc = desc;
	}

	public String getName() {
		return name;
	}

	public String getDesc() {
		return desc;
	}
}
