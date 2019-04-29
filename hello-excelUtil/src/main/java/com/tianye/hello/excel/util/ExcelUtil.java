package com.tianye.hello.excel.util;

import com.tianye.hello.excel.annotation.ExcelCell;
import com.tianye.hello.excel.model.CellModel;
import com.tianye.hello.excel.model.StyleModel;
import org.apache.commons.codec.binary.StringUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.CellType;
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
		HSSFSheet sheet = createSheet(workbook,sheetName);
		Integer startIndex = 0;
		Boolean hasHeader = false;
		if(!CollectionUtils.isEmpty(headers)){
			HSSFCellStyle style = StyleUtil.createDefalutHeaderStyle(workbook);
			writeHeader2Sheet(sheet,headers,style,startIndex);
			for ( int i = startIndex, isize = headers.size(); i<isize; i++) {
				//自动调整列宽
				sheet.autoSizeColumn(i);//i为第几列，需要全文都单元格居中的话，需要遍历所有的列数
			}

			startIndex++;
			hasHeader = true;
		}

		if(!CollectionUtils.isEmpty(dataset)) {
			HSSFCellStyle style = StyleUtil.createDefalutCellStyle(workbook);
			writeData2Sheet(sheet, dataset,style,hasHeader,startIndex);
		}
		try {
			workbook.write(out);
		} catch (IOException e) {
			LG.error(e.toString(), e);
		}
	}

	/**
	 *  // 生成一个表格sheet,并导出数据
	 * @param workbook
	 * @param sheetName
	 * @param <T>
	 */
	public static <T> HSSFSheet createSheet(HSSFWorkbook workbook,String sheetName) {
		String name = CommonUtils.isEmpty(sheetName) ? "sheet1" : sheetName;
		HSSFSheet sheet = workbook.createSheet(name);
		return sheet;
	}

	/**
	 * 	填写sheet页的表头
	 * @param sheet
	 * @param headers
	 * @param startIndex
	 */
	public static void writeHeader2Sheet(HSSFSheet sheet,List<String> headers,HSSFCellStyle style, Integer startIndex) {
		Integer rowIndex =  startIndex == null ? 0 : startIndex;
		HSSFRow row = sheet.createRow(rowIndex);
		for(Integer i=0; i<headers.size();i++){
			HSSFCell cell = row.createCell(i);
			setCellValue(cell,headers.get(i));
			cell.setCellStyle(style);
		}
	}


	/**
	 * 每个sheet的写入
	 * @param sheet
	 * @param dataset
	 * @param startIndex
	 * @param <T>
	 */
	public static <T> void writeData2Sheet(HSSFSheet sheet, Collection<T> dataset,HSSFCellStyle style,Boolean hasHeader,Integer startIndex)  {
		Integer rowIndex =  startIndex == null ? 0 : startIndex;
		Boolean hasTitle = CommonUtils.isEmpty(hasHeader) ? false : hasHeader;
		List<CellModel> fields = null;

		//遍历集合数据,产生数据行
		Iterator<T> it = dataset.iterator();
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
					writeTitle2Row(row,titles,style,collStartIndex);
					hasTitle = true;
					continue;
				}
				writeData2Row(row, fields, t,style, collStartIndex);
			} catch (IllegalAccessException e) {
				LG.error("填充数据到row异常,t={}",t.toString(),e);
			}
			for ( int i = startIndex, isize = fields.size(); i<isize; i++) {
				//自动调整列宽
				sheet.autoSizeColumn(i);//i为第几列，需要全文都单元格居中的话，需要遍历所有的列数
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
	 * 填充数据到row
	 * @param row
	 * @param fields
	 * @param t
	 * @param style
	 * @param startIndex
	 * @param <T>
	 * @throws IllegalAccessException
	 */
	public static <T> void writeData2Row(HSSFRow row, List<CellModel> fields, T t, HSSFCellStyle style,Integer startIndex) throws IllegalAccessException {
		Integer collIndex = startIndex == null ? 0 : startIndex;
		for(int i = 0; i<fields.size(); i++){
			HSSFCell cell = row.createCell(collIndex);
			Field field = fields.get(i).getField();
			field.setAccessible(true);
			Object value = field.get(t);
			setCellValue(cell,value);
			cell.setCellStyle(style);
			collIndex++;
		}
	}

	/**
	 * 填充标题
	 * @param row
	 * @param titles
	 * @param style
	 * @param startIndex
	 */
	public static void writeTitle2Row( HSSFRow row,String[] titles,HSSFCellStyle style,Integer startIndex) {

		int collIndex = startIndex == null ? 0 : startIndex;
		for(int i = 0; i < titles.length; i++) {
			HSSFCell cell = row.createCell(collIndex);
			HSSFRichTextString text = new HSSFRichTextString(titles[i]);
			cell.setCellValue(text);
			cell.setCellStyle(style);
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
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
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
