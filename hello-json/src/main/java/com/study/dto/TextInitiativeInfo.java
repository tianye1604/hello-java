package com.study.dto;

import com.google.gson.annotations.Expose;

/**
 * 主动发送的文本消息
 * @author 14043094
 *
 */
public class TextInitiativeInfo extends BaseInitiativeInfo{
	@Expose
	private TextContent text;

	public TextContent getText() {
		return text;
	}

	public void setText(TextContent text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return "TextInitiativeInfo [" + super.toString() + "text=" + String.valueOf(text) + "]";
	}

	/**
	 * 常量
	 * @author 14043094
	 *
	 */
	public interface Constants {
		/**
		 * 文本属性名  content
		 */
		String FIELDNAME_CONTENT = "text";
	}
}
