package com.study.dto;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

/**
 * 文本消息
 * @author 14043094
 *
 */
public class TextContent implements Serializable {
	private static final long serialVersionUID = 5970795886985894313L;
	@Expose
	private String content;

	public TextContent(String content) {
		super();
		this.content = content;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "TextContent [content=" + content + "]";
	}
}
