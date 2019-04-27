package com.tianye.hello.excel.util;

import com.tianye.hello.excel.annotation.ExcelCell;
import com.tianye.hello.excel.model.CellModel;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.hssf.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Auther: Tian.shujian 18066629
 * @Date: 2019/4/26 14:28
 * @Description:
 */
public class ExcelUtil {

	private static Logger LG = LoggerFactory.getLogger(ExcelUtil.class);

	/**
	 * 利用JAVA的反射机制，将放置在JAVA集合中并且符号一定条件的数据以EXCEL 的形式输出到指定IO设备上<br>
	 * 用于单个sheet
	 *
	 * @param <T>
	 * @param headers 表格属性列名数组
	 * @param dataset 需要显示的数据集合,集合中一定要放置符合javabean风格的类的对象。此方法支持的
	 *                javabean属性的数据类型有基本数据类型及String,Date,String[],Double[]
	 * @param out     与输出设备关联的流对象，可以将EXCEL文档导出到本地文件或者网络中
	 */
	public static <T> void exportExcel(List<String> headers, Collection<T> dataset, OutputStream out) {
		// 声明一个工作薄
		HSSFWorkbook workbook = new HSSFWorkbook();
		String sheetName = "学生信息";
		// 生成一个表格
		createSheet(workbook, headers, dataset, sheetName);
		try {
			workbook.write(out);
		} catch (IOException e) {
			LG.error(e.toString(), e);
		}
	}

	/**
	 *  // 生成一个表格sheet,并导出数据
	 * @param workbook
	 * @param headers
	 * @param dataset
	 * @param sheetName
	 * @param <T>
	 */
	public static <T> void createSheet(HSSFWorkbook workbook, List<String> headers, Collection<T> dataset, String sheetName) {
		HSSFSheet sheet = workbook.createSheet(sheetName);
		Integer startIndex = 0;
		if(!CollectionUtils.isEmpty(headers)){
			writeHeader2Sheet(sheet,headers,startIndex);
			startIndex = headers.size();
		}
		if(!CollectionUtils.isEmpty(dataset)) {
			writeData2Sheet(sheet, dataset,startIndex);
		}
	}

	/**
	 * 	填写sheet页的表头
	 * @param sheet
	 * @param headers
	 * @param startIndex
	 */
	public static void writeHeader2Sheet(HSSFSheet sheet,List<String> headers, Integer startIndex) {
		Integer rowIndex =  startIndex == null ? 0 : startIndex;
		for(Integer i=0; i<headers.size();i++){
			HSSFRow row = sheet.createRow(rowIndex);
			HSSFCell cell = row.createCell(0);
			setCellValue(cell,headers.get(i));
		}
	}


	/**
	 * 每个sheet的写入
	 * @param sheet
	 * @param dataset
	 * @param startIndex
	 * @param <T>
	 */
	public static <T> void writeData2Sheet(HSSFSheet sheet, Collection<T> dataset,Integer startIndex)  {
		Integer rowIndex =  startIndex == null ? 0 : startIndex;
		List<CellModel> fields = null;
		//遍历集合数据,产生数据行
		Iterator<T> it = dataset.iterator();
		Boolean hasTitle = false;
		while (it.hasNext()) {
			T t = it.next();
			if(null == fields) {
				fields = getCellModelList(t.getClass());
			}
			HSSFRow row = sheet.createRow(rowIndex);
			rowIndex ++;
			Integer collStartIndex = 0;   //列,开始位置
			try {
				if(!hasTitle) {
					String[] titles = getCellTitles(fields);
					writeTitle2Row(row,titles,collStartIndex);
					hasTitle = true;
					continue;
				}
				writeData2Row(row, fields, t, collStartIndex);
			} catch (IllegalAccessException e) {
				LG.error("填充数据到row异常,t={}",t.toString(),e);
			}

		}

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
	 *  填充数据到row
	 * @param row
	 * @param fields
	 * @param t
	 * @param startIndex
	 * @param <T>
	 * @throws IllegalAccessException
	 */
	public static <T> void writeData2Row(HSSFRow row, List<CellModel> fields, T t, Integer startIndex) throws IllegalAccessException {
		Integer collIndex = startIndex == null ? 0 : startIndex;
		for(int i = 0; i<fields.size(); i++){
			HSSFCell cell = row.createCell(collIndex);
			Field field = fields.get(i).getField();
			field.setAccessible(true);
			Object value = field.get(t);
			setCellValue(cell,value);
			collIndex++;
		}
	}
	/**
	 *  填充标题
	 * @param row
	 * @param titles
	 * @param startIndex
	 */
	public static void writeTitle2Row( HSSFRow row,String[] titles,Integer startIndex) {
		int collIndex = startIndex == null ? 0 : startIndex;
		for(int i = 0; i < titles.length; i++) {
			HSSFCell cell = row.createCell(collIndex);
			HSSFRichTextString text = new HSSFRichTextString(titles[i]);
			cell.setCellValue(text);
			collIndex++ ;
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
		} else if (value instanceof Float) {
			float fValue = (Float) value;
			cell.setCellValue(fValue);
		} else if (value instanceof Double) {
			double dValue = (Double) value;
			cell.setCellValue(dValue);
		} else if (value instanceof Long) {
			long longValue = (Long) value;
			cell.setCellValue(longValue);
		} else if (value instanceof Boolean) {
			boolean bValue = (Boolean) value;
			cell.setCellValue(bValue);
		} else if (value instanceof Date) {
			Date date = (Date) value;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			textValue = sdf.format(date);
		}else {
			// 其它数据类型都当作字符串简单处理
			textValue =  value.toString();
		}
		if (textValue != null) {
			HSSFRichTextString richString = new HSSFRichTextString(textValue);
			cell.setCellValue(richString);
		}
	}

	/**
	 * 根据annotation的seq排序后的栏位
	 * @param clazz
	 * @return
	 */
	private static List<CellModel> getCellModelList(Class<?> clazz) {
		Field[] fieldsArr = clazz.getDeclaredFields();
		List<CellModel> fields = new ArrayList<>();
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
