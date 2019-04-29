package com.tianye.hello.excel.model;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;

/**
 * @Auther: Tian.shujian 18066629
 * @Date: 2019/4/29 17:33
 * @Description: 默认样式模型
 */
public class DefalutStyleModel extends StyleModel {


	/**
	 * 加载style
	 */
	@Override
	public void loadStyle() {
		this.setFontName("宋体");
		this.setFontSize((short)12);
		this.setBrderBottom(BorderStyle.THIN);
		this.setBrderTop(BorderStyle.THIN);
		this.setBrderLeft(BorderStyle.THIN);
		this.setBrderRight(BorderStyle.THIN);
		this.setAlign(HorizontalAlignment.LEFT);
		this.setBold(false);
	}

	/**
	 * 加载表头style
	 */
	@Override
	public void loadHeaderStyle() {
		this.setFontName("黑体");
		this.setFontSize((short)16);
		this.setBrderBottom(BorderStyle.THIN);
		this.setBrderTop(BorderStyle.THIN);
		this.setBrderLeft(BorderStyle.THIN);
		this.setBrderRight(BorderStyle.THIN);
		this.setAlign(HorizontalAlignment.CENTER);
		this.setBold(true);
	}
}
