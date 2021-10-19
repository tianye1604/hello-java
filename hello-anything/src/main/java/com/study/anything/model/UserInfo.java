package com.study.anything.model;

import com.jd.org.msgpack.annotation.Message;

import java.io.Serializable;
@Message
public class UserInfo implements Serializable {
    private static final long serialVersionUID = -1067314328999364343L;
    private Long userId;
    private String bizUserPin;
    private String tenantId;
    private Long shopId;
    private Long merchantId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getBizUserPin() {
        return bizUserPin;
    }

    public void setBizUserPin(String bizUserPin) {
        this.bizUserPin = bizUserPin;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }
}
