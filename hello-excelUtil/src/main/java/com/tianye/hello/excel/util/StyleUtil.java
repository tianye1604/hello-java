package com.tianye.hello.excel.util;

import com.tianye.hello.excel.model.StyleModel;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

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
		font.setBold(model.getBold());
		cellStyle.setFont(font);
		cellStyle.setBorderBottom(model.getBrderBottom()); // 下边框
		cellStyle.setBorderLeft(model.getBrderLeft());// 左边框
		cellStyle.setBorderTop(model.getBrderTop());// 上边框
		cellStyle.setBorderRight(model.getBrderRight());// 右边框
		cellStyle.setAlignment(model.getAlign());
		return cellStyle;
	}


}
