package com.tianye.hello.excel.util;

import com.tianye.hello.excel.model.StyleModel;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;

/**
 * @Auther: Tian.shujian 18066629
 * @Date: 2019/4/27 18:57
 * @Description: 样式工具类
 */
public class StyleUtil {



	public static HSSFCellStyle createDefalutCellStyle(HSSFWorkbook workbook) {
		StyleModel styleModel = new StyleModel();
		styleModel.loadDefalutStyle();
		return createCellStyle(workbook,styleModel);
	}

	public static HSSFCellStyle createDefalutHeaderStyle(HSSFWorkbook workbook) {
		StyleModel styleModel = new StyleModel();
		styleModel.loadDefalutHeaderStyle();
		return createCellStyle(workbook,styleModel);
	}
	public static HSSFCellStyle createCellStyle(HSSFWorkbook workbook, StyleModel model) {
		HSSFCellStyle cellStyle = workbook.createCellStyle();
		//1.设置字体
		HSSFFont font = workbook.createFont();
		//font.setFontHeight((short)12);//这个设置字体会很大
		font.setFontHeightInPoints(model.getFontSize());//这才是我们平常在Excel设置字体的值
		font.setFontName(model.getFontName());//字体：宋体、华文行楷等等
		cellStyle.setBorderBottom(model.getBrderBottom()); // 下边框
		cellStyle.setBorderLeft(model.getBrderLeft());// 左边框
		cellStyle.setBorderTop(model.getBrderTop());// 上边框
		cellStyle.setBorderRight(model.getBrderRight());// 右边框
		cellStyle.setAlignment(HorizontalAlignment.LEFT);
		return cellStyle;
	}

	/**
	 * 设置字体样式
	 * @param workbook
	 * @param name
	 * @param height
	 * @return
	 */
	private static HSSFFont setFontStyle(HSSFWorkbook workbook, String name, short height) {
		//1.设置字体
		HSSFFont font = workbook.createFont();
		//font.setFontHeight((short)12);//这个设置字体会很大
		font.setFontHeightInPoints(height);//这才是我们平常在Excel设置字体的值
		font.setFontName(name);//字体：宋体、华文行楷等等
		return font;
	}

	/**
	 * 设置单元格样式
	 * @param cellStyle
	 * @param border
	 */
	private static void setBorderStyle(HSSFCellStyle cellStyle, BorderStyle border) {
		cellStyle.setBorderBottom(border); // 下边框
		cellStyle.setBorderLeft(border);// 左边框
		cellStyle.setBorderTop(border);// 上边框
		cellStyle.setBorderRight(border);// 右边框
		//border的常用参数如下
		//		BorderStyle.NONE 无边框
		//		BorderStyle.THIN 细边框
		//		BorderStyle.MEDIUM 中等粗边框
		//		BorderStyle.THICK 粗边框
		//其余的我也描述不清是什么形状，有兴趣的到时可以直接测试
	}

}
