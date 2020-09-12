package com.tianye.hello.excel.model;

import com.tianye.hello.excel.annotation.RequestField;
import com.tianye.hello.excel.enums.BankIdTypeEnum;

import java.io.Serializable;

/**
 * Created by tianshujian
 * Create Date: 2020/9/10 14:40
 * Description: ${DESCRIPTION}
 */
public class EnterpriseRegisterDTO implements Serializable {
	private static final long serialVersionUID = 1275493602989079698L;

	//企业名称 必填
	@RequestField(name="enterpriseName",desc = "企业名称",canNull = false)
	private String enterpriseName;

	//开户银行许可证核准号
	@RequestField(name="bankLicense",desc = "开户银行许可证核准号",canNull = false)
	private String bankLicense;

	//组织机构代码
	@RequestField(name="orgNo",desc = "组织机构代码",canNull = true)
	private String orgNo;

	//营业执照编号
	@RequestField(name="businessLicense",desc = "营业执照编号",canNull = true)
	private String businessLicense;

	//税务登记号
	@RequestField(name="taxNo",desc = "税务登记号",canNull = true)
	private String taxNo;

	//统一社会信用代码（可替代营业执照编号、税务登记号、组织机构代码此三证），统一社会信用代码和三证信息两者必须有一个传入
	@RequestField(name="unifiedCode",desc = "统一社会信用代码",canNull = true)
	private String unifiedCode;

	//机构信用代码
	@RequestField(name="creditCode",desc = "机构信用代码",canNull = true)
	private String creditCode;

	//法人姓名 必填
	@RequestField(name="legal",desc = "法人姓名",canNull = true)
	private String legal;

	//证件类型 必填
	@RequestField(name="idCardType",desc = "证件类型",canNull = true,isEnum = true,valueType = BankIdTypeEnum.class)
	private String idCardType;

	//法人证件号 必填
	@RequestField(name="legalIdCardNo",desc = "法人证件号",canNull = true)
	private String legalIdCardNo;

	//企业联系人 必填
	@RequestField(name="contact",desc = "企业联系人",canNull = true)
	private String contact;

	//联系人手机号 必填
	@RequestField(name="contactPhone",desc = "联系人手机号",canNull = true)
	private String contactPhone;

	//企业对公账号 必填
	@RequestField(name="bankcardNo",desc = "企业对公账号",canNull = true)
	private String bankcardNo;

	//银行编码 必填
	@RequestField(name="bankcode",desc = "银行编码",canNull = true)
	private String bankcode;

	//短信验证码下发请求流水号
	@RequestField(name="codeRequestNo",desc = "短信验证码下发请求流水号",canNull = true)
	private String codeRequestNo;


	public String getEnterpriseName() {
		return enterpriseName;
	}

	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}

	public String getBankLicense() {
		return bankLicense;
	}

	public void setBankLicense(String bankLicense) {
		this.bankLicense = bankLicense;
	}

	public String getOrgNo() {
		return orgNo;
	}

	public void setOrgNo(String orgNo) {
		this.orgNo = orgNo;
	}

	public String getBusinessLicense() {
		return businessLicense;
	}

	public void setBusinessLicense(String businessLicense) {
		this.businessLicense = businessLicense;
	}

	public String getTaxNo() {
		return taxNo;
	}

	public void setTaxNo(String taxNo) {
		this.taxNo = taxNo;
	}

	public String getUnifiedCode() {
		return unifiedCode;
	}

	public void setUnifiedCode(String unifiedCode) {
		this.unifiedCode = unifiedCode;
	}

	public String getCreditCode() {
		return creditCode;
	}

	public void setCreditCode(String creditCode) {
		this.creditCode = creditCode;
	}

	public String getLegal() {
		return legal;
	}

	public void setLegal(String legal) {
		this.legal = legal;
	}

	public String getIdCardType() {
		return idCardType;
	}

	public void setIdCardType(String idCardType) {
		this.idCardType = idCardType;
	}

	public String getLegalIdCardNo() {
		return legalIdCardNo;
	}

	public void setLegalIdCardNo(String legalIdCardNo) {
		this.legalIdCardNo = legalIdCardNo;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public String getBankcardNo() {
		return bankcardNo;
	}

	public void setBankcardNo(String bankcardNo) {
		this.bankcardNo = bankcardNo;
	}

	public String getBankcode() {
		return bankcode;
	}

	public void setBankcode(String bankcode) {
		this.bankcode = bankcode;
	}

	public String getCodeRequestNo() {
		return codeRequestNo;
	}

	public void setCodeRequestNo(String codeRequestNo) {
		this.codeRequestNo = codeRequestNo;
	}
}
