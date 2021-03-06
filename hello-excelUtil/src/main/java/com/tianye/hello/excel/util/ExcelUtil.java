package com.tianye.hello.excel.util;

import com.tianye.hello.excel.annotation.ExcelCell;
import com.tianye.hello.excel.model.CellModel;
import com.tianye.hello.excel.model.StyleModel;
import com.tianye.hello.util.CommonUtils;
import com.tianye.hello.util.DateUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.CellType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Auther: Tian.shujian 18066629
 * @Date: 2019/4/26 14:28
 * @Description: 导出Excel的工具类
 */
public class ExcelUtil {

	private static Logger LG = LoggerFactory.getLogger(ExcelUtil.class);

	/**
	 *  导出excel到网络流
	 * @param response
	 * @param dataset
	 * @param fileName
	 * @param <T>
	 * @throws IOException
	 */
	public static <T> void  exportExcel(HttpServletResponse response, Collection<T> dataset, String fileName) throws IOException {
		// 告诉浏览器用什么软件可以打开此文件
		response.setHeader("content-Type", "application/vnd.ms-excel");
		// 下载文件的默认名称
		response.setHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode(fileName, "utf-8"));
		//获取输出流
		OutputStream out = response.getOutputStream();
		//导出数据
		ExcelUtil.exportExcel(dataset, out);
	}

	public static String getFileName(Date start,Date end, String wechatName, String serviceName) {
		String startDate = DateUtils.getFormatDate(start,DateUtils.SIMPLE_DATE_PATTERN);
		String endDate = DateUtils.getFormatDate(end,DateUtils.SIMPLE_DATE_PATTERN);
		String nowStamp = DateUtils.getNowStampStr();
		StringBuilder builder = new StringBuilder();
		return builder.append(wechatName)
				.append("-")
				.append(serviceName)
				.append("-")
				.append(startDate)
				.append("-")
				.append(endDate)
				.append("_")
				.append(nowStamp)
				.append(".xls")
				.toString();
	}
	/**
	 *  导入dataset数据到Excel,并将excel输出至输出流
	 * @param dataset
	 * @param out
	 * @param <T>
	 */
	public static <T> void exportExcel( Collection<T> dataset, OutputStream out) {
		exportExcel(null,dataset,null,out);
	}

	/**
	 * 导入dataset数据到Excel,并将excel输出至输出流
	 * @param dataset
	 * @param sheetName
	 * @param out
	 * @param <T>
	 */
	public static <T> void exportExcel( Collection<T> dataset,String sheetName, OutputStream out) {
		exportExcel(null,dataset,sheetName,out);
	}

	/**
	 * 导入headers,dataset数据到Excel,并将excel输出至输出流
	 * 适用于导出单sheet
	 * @param headers
	 * @param dataset
	 * @param out
	 * @param <T>
	 */
	public static <T> void exportExcel(List<String> headers, Collection<T> dataset,String sheetName, OutputStream out) {
		// 声明一个工作薄
		HSSFWorkbook workbook = new HSSFWorkbook();
		// 生成一个表格
		HSSFSheet sheet = createSheet(workbook,sheetName);
		//将数据写入表格
		Integer rowNum  = 0;
		if(!CommonUtils.isEmpty(headers)){
			rowNum = writeHeader2Sheet(sheet,headers);
		}
		rowNum =  writeData2Sheet(sheet,dataset,null, rowNum>0,rowNum);
		LG.info("excel记录数为:{}行",rowNum);
		//将工作表导出
		exportExcel(workbook,out);
	}


	/**
	 *  文件输出
	 * @param workbook 填充好内容的workbook
	 * @param out
	 */
	public static<T>  void exportExcel (HSSFWorkbook workbook, OutputStream out) {

		try {
			workbook.write(out);
		} catch (IOException e) {
			LG.error("导出excel异常",e);
		}
		try{
			out.flush();
			out.close();
		}catch (IOException e) {
			LG.error("输出流关闭异常",e);
		}
	}



	/**
	 * 生成一个表格sheet
	 * @param workbook
	 * @param sheetName
	 * @param <T>
	 */
	public static <T> HSSFSheet createSheet(HSSFWorkbook workbook, String sheetName) {
		String name = CommonUtils.isEmpty(sheetName) ? "sheet1" : sheetName;
		HSSFSheet sheet = workbook.createSheet(name);
		return sheet;
	}

	/**
	 * 	填写sheet页的表头
	 * @param sheet
	 * @param headers
	 */
	private static Integer writeHeader2Sheet(HSSFSheet sheet, List<String> headers) {
		return writeHeader2Sheet(sheet,headers,null,null);
	}

	/**
	 * 	填写sheet页的表头
	 * @param sheet
	 * @param headers
	 * @param styleModel
	 * @param startIndex
	 */
	public static Integer writeHeader2Sheet(HSSFSheet sheet, List<String> headers, StyleModel styleModel, Integer startIndex) {
		Integer rowIndex =  startIndex == null ? 0 : startIndex;
		if(CommonUtils.isEmpty(sheet) || CommonUtils.isEmpty(headers)){
			LG.error("sheet的写入参数有误,sheet={},headers={}",sheet,headers);
			return rowIndex;
		}
		HSSFWorkbook workbook = sheet.getWorkbook();
		HSSFCellStyle style = CommonUtils.isEmpty(styleModel) ? StyleUtil.createDefalutHeaderStyle(workbook) : StyleUtil.createCellStyle(workbook,styleModel);
		HSSFRow row = sheet.createRow(rowIndex);
		rowIndex++;

		for(Integer i=0; i<headers.size();i++){
			HSSFCell cell = row.createCell(i);
			setCellValue(cell,headers.get(i));
			cell.setCellStyle(style);
			sheet.autoSizeColumn(i);
		}
		return rowIndex;
	}


	/**
	 * sheet的写入数据
	 * 利用JAVA的反射机制，将放置在JAVA集合中并且符号一定条件的数据以EXCEL 的形式输出到指定IO设备上<br>
	 * @param sheet
	 * @param dataset
	 * @param startIndex
	 * @param <T>
	 */
	public static <T> Integer writeData2Sheet(HSSFSheet sheet, Collection<T> dataset, StyleModel styleModel, Boolean hasHeader, Integer startIndex)  {
		Integer rowIndex =  startIndex == null ? 0 : startIndex;
		if(CommonUtils.isEmpty(sheet) || CommonUtils.isEmpty(dataset)){
			LG.error("sheet的写入参数有误,sheet={},dataset={}",sheet,dataset);
			return rowIndex;
		}
		Boolean hasTitle = CommonUtils.isEmpty(hasHeader) ? false : hasHeader;
		List<CellModel> fields = null;
		HSSFWorkbook workbook = sheet.getWorkbook();
		HSSFCellStyle style = CommonUtils.isEmpty(styleModel) ? StyleUtil.createDefalutCellStyle(workbook) : StyleUtil.createCellStyle(workbook,styleModel);

		//遍历集合数据,产生数据行
		Iterator<T> it = dataset.iterator();
		while (it.hasNext()) {
			T t = it.next();
			if(null == fields) {
				fields = getCellModelList(t.getClass());
			}
			if(!hasTitle) {
				String[] titles = getCellTitles(fields);
				rowIndex = writeHeader2Sheet(sheet,Arrays.asList(titles),null,rowIndex);
				hasTitle = true;
			}
			try {
				HSSFRow row = sheet.createRow(rowIndex);
				HSSFCellStyle dataStyle = CommonUtils.isEmpty(style) ? StyleUtil.createDefalutCellStyle(workbook) : style;
				writeData2Row(row, fields, t,dataStyle);
				rowIndex ++;
			} catch (IllegalAccessException e) {
				LG.error("填充数据到row异常,t={}",t.toString(),e);
			}
			for ( int i = startIndex, isize = fields.size(); i<isize; i++) {
				//自动调整列宽
				sheet.autoSizeColumn(i);//i为第几列，需要全文都单元格居中的话，需要遍历所有的列数
			}

		}
		return rowIndex;

	}

	/**
	 * 获取标题列表
	 * @param fields
	 * @return
	 */
	private static String[] getCellTitles(List<CellModel> fields) {
		String[] titles = new String[fields.size()];
		for(Integer i = 0; i<fields.size(); i++ ){
			titles[i] = fields.get(i).getTitle();
		}
		return titles;
	}

	/**
	 * 填充数据到row
	 * @param row
	 * @param fields
	 * @param t
	 * @param style
	 * @param <T>
	 * @throws IllegalAccessException
	 */
	public static <T> void writeData2Row(HSSFRow row, List<CellModel> fields, T t, HSSFCellStyle style) throws IllegalAccessException {
		for(int i = 0; i<fields.size(); i++){
			HSSFCell cell = row.createCell(i);
			Field field = fields.get(i).getField();
			field.setAccessible(true);
			Object value = field.get(t);
			setCellValue(cell,value);
			cell.setCellStyle(style);
		}
	}

	/**
	 *  填充表格数据
	 * @param cell
	 * @param value
	 */
	private static void setCellValue(HSSFCell cell, Object value) {
		String textValue = null;
		if (value instanceof Integer) {
			int intValue = (Integer) value;
			cell.setCellValue(intValue);
			cell.setCellType(CellType.NUMERIC);
		} else if (value instanceof Float) {
			float fValue = (Float) value;
			cell.setCellValue(fValue);
			cell.setCellType(CellType.NUMERIC);
		} else if (value instanceof Double) {
			double dValue = (Double) value;
			cell.setCellValue(dValue);
			cell.setCellType(CellType.NUMERIC);
		} else if (value instanceof Long) {
			long longValue = (Long) value;
			cell.setCellValue(longValue);
			cell.setCellType(CellType.NUMERIC);
		} else if (value instanceof Boolean) {
			boolean bValue = (Boolean) value;
			cell.setCellValue(bValue);
			cell.setCellType(CellType.BOOLEAN);
		} else if (value instanceof Date) {
			Date date = (Date) value;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			textValue = sdf.format(date);
		}else {
			// 其它数据类型都当作字符串简单处理
			textValue =  value.toString();
		}
		if (textValue != null) {
			HSSFRichTextString richString = new HSSFRichTextString(textValue);
			cell.setCellValue(richString);
			cell.setCellType(CellType.STRING);
		}
	}

	/**
	 * 根据annotation的seq排序后的栏位
	 * @param clazz
	 * @return
	 */
	private static List<CellModel> getCellModelList(Class<?> clazz) {
		Field[] fieldsArr = clazz.getDeclaredFields();
		List<CellModel> fields = new ArrayList<CellModel>();
		for (Field filed : fieldsArr) {
			ExcelCell ec = filed.getAnnotation(ExcelCell.class);
			if(ec == null) {
				// 没有Excell Annotation 视为不汇入
				continue;
			}
			int id = ec.index();
			String title = ec.title();
			fields.add(new CellModel(filed,id,title));
		}
		Collections.sort(fields);
		return fields;
	}


}
