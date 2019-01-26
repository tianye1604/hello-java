package com.study.dto;

import com.google.gson.annotations.Expose;

import java.io.Serializable;


/**
 * 主动给用户发送的消息体
 * @author 14043094
 *
 */
public class BaseInitiativeInfo implements Serializable {
	private static final long serialVersionUID = 2569104601985501858L;
	@Expose
	private String touser;
	
	@Expose
	private String msgtype;
	
	@Expose
	private String cardId;
	
    /**
     * 是否是关键词回复, 0 : 不是 ; 1 : 是
     */
	@Expose(serialize = false)
    private Integer keywordsReply;

	public String getTouser() {
		return touser;
	}

	public void setTouser(String touser) {
		this.touser = touser;
	}

	public String getMsgtype() {
		return msgtype;
	}

	public void setMsgtype(String msgtype) {
		this.msgtype = msgtype;
	}

	public Integer getKeywordsReply() {
		return keywordsReply;
	}

	public void setKeywordsReply(Integer keywordsReply) {
		this.keywordsReply = keywordsReply;
	}

	@Override
	public String toString() {
		return "BaseInitiativeInfo [touser=" + touser + ", msgtype=" + msgtype
				+ ", keywordsReply=" + keywordsReply + "]";
	}

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

}
