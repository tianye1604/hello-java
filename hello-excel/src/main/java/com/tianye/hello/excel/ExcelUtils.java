package com.tianye.hello.excel;

import com.alibaba.fastjson.JSONArray;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;

import java.io.*;

/**
 * @Auther: Tian.shujian 18066629
 * @Date: 2019/4/16 09:44
 * @Description:
 */
public class ExcelUtils {


	/**
	 *  创建excel并填入数据
	 * @param head 数据头
	 * @param body 主体数据
	 * @return HSSFWorkbook
	 */
	public static HSSFWorkbook expExcel(JSONArray head, JSONArray body) {
		//创建一个excel工作薄
		HSSFWorkbook workbook = new HSSFWorkbook();
		//创建一个sheet工作表
		HSSFSheet sheet = workbook.createSheet("学生信息");

		//创建第0行表头,再在这行里创建单元格,并赋值
		HSSFRow row = sheet.createRow(0);
		HSSFCell cell = null;

		HSSFCellStyle cellStyle = workbook.createCellStyle();
		setBorderStyle(cellStyle, BorderStyle.THIN);
		cellStyle.setFont(setFontStyle(workbook,"黑体",(short)14));
		cellStyle.setAlignment(HorizontalAlignment.CENTER);
		for(int i = 0; i<head.size();i++) {
			cell =  row.createCell(i);
			cell.setCellValue(head.getString(i));//设置值
			cell.setCellStyle(cellStyle);
		}

		HSSFCellStyle cellStyle2 = workbook.createCellStyle();
		setBorderStyle(cellStyle2, BorderStyle.THIN);
		cellStyle2.setFont(setFontStyle(workbook,"宋体",(short)12));
		cellStyle2.setAlignment(HorizontalAlignment.CENTER);
		//将主体数据填入Excel中
		for(int i = 0,isize = body.size(); i<isize;i++) {
			row = sheet.createRow(i + 1);
			JSONArray stuInfo = body.getJSONArray(i);
			for (int j = 0,jsize = stuInfo.size();j < jsize; j++ ) {
				cell = row.createCell(j);
				cell.setCellValue(stuInfo.getString(j));//设置值

				cell.setCellType(CellType.NUMERIC);
				cell.setCellStyle(cellStyle2);
			}
		}
		for ( int i = 0, isize = head.size(); i<isize; i++) {
			//自动调整列宽
			sheet.autoSizeColumn(i);//i为第几列，需要全文都单元格居中的话，需要遍历所有的列数
		}
		return workbook;
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
	 *  文件输出
	 * @param workbook 填充好内容的workbook
	 * @param path 存放的位置
	 */
	public static void outFile (HSSFWorkbook workbook,String path) {
		OutputStream os = null;

		try {
			os = new FileOutputStream(new File(path));
			workbook.write(os);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try{
			os.close();
		}catch (IOException e) {
			e.printStackTrace();
		}
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
