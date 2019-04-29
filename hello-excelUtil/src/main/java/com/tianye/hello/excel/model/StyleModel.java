package com.tianye.hello.excel.model;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;

/**
 * @Auther: Tian.shujian 18066629
 * @Date: 2019/4/27 19:06
 * @Description: 样式模型
 */
public abstract class StyleModel {
	//border的常用参数如下
	//		BorderStyle.NONE 无边框
	//		BorderStyle.THIN 细边框
	//		BorderStyle.MEDIUM 中等粗边框
	//		BorderStyle.THICK 粗边框
	private BorderStyle brderBottom;// 下边框
	private BorderStyle brderLeft;// 左边框
	private BorderStyle brderTop;// 上边框
	private BorderStyle brderRight;// 右边框

	private String fontName; //字体名称
	private short fontSize; //字体大小
	private Boolean bold;	//字体加粗


	/**
	 *  GENERAL,
	 *  LEFT,
	 *  CENTER,
	 *  RIGHT,
	 *  FILL,
	 *  JUSTIFY,
	 *  CENTER_SELECTION,
	 *  DISTRIBUTED;
	 */
	private HorizontalAlignment align;


	/**
	 *  加载style
	 */
	public abstract void loadStyle();

	/**
	 *  加载表头style
	 */
	public abstract void loadHeaderStyle();



	public BorderStyle getBrderBottom() {
		return brderBottom;
	}

	public void setBrderBottom(BorderStyle brderBottom) {
		this.brderBottom = brderBottom;
	}

	public BorderStyle getBrderLeft() {
		return brderLeft;
	}

	public void setBrderLeft(BorderStyle brderLeft) {
		this.brderLeft = brderLeft;
	}

	public BorderStyle getBrderTop() {
		return brderTop;
	}

	public void setBrderTop(BorderStyle brderTop) {
		this.brderTop = brderTop;
	}

	public BorderStyle getBrderRight() {
		return brderRight;
	}

	public void setBrderRight(BorderStyle brderRight) {
		this.brderRight = brderRight;
	}

	public String getFontName() {
		return fontName;
	}

	public void setFontName(String fontName) {
		this.fontName = fontName;
	}

	public short getFontSize() {
		return fontSize;
	}

	public void setFontSize(short fontSize) {
		this.fontSize = fontSize;
	}

	public HorizontalAlignment getAlign() {
		return align;
	}

	public void setAlign(HorizontalAlignment align) {
		this.align = align;
	}

	public Boolean getBold() {
		return bold;
	}

	public void setBold(Boolean bold) {
		this.bold = bold;
	}
}
