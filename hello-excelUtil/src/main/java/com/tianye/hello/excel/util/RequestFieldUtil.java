package com.tianye.hello.excel.util;

import com.tianye.hello.excel.annotation.BankRequestField;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by tianshujian
 * Create Date: 2020/9/12 17:35
 * Description: ${DESCRIPTION}
 */
public class RequestFieldUtil {


	public static <T> Map toFieldMap(T obj) throws IllegalAccessException {
		Class<?> clazz = obj.getClass();
		Field[] fieldsArr = clazz.getDeclaredFields();
		Map<String,Object> filedMap = new HashMap<>();
		for (Field field : fieldsArr) {
			BankRequestField annotation = field.getAnnotation(BankRequestField.class);
			if(annotation != null) {
				// 没有Excell Annotation 视为不汇入
				String name = annotation.name();
				field.setAccessible(true);
				Object value = field.get(obj);
				checkValueLegality(annotation,value);
				if (Objects.nonNull(value)) {
					filedMap.put(name,value);
				}
				continue;
			}
		}
		return filedMap;
	}

	private static void checkValueLegality(BankRequestField annotation, Object value) {
		checkValueCanNull(annotation,value);
		checkValueArea(annotation,value);
	}

	/***
	 * @Description 检查取值是否可空
	 * @Author tianshujian
	 * @Date 18:52 2020/9/12
	 **/
	private static void checkValueCanNull(BankRequestField annotation, Object value) {
		if(!annotation.canNull() && Objects.isNull(value)) {
			throw new RuntimeException(annotation.desc() + ":" + annotation.name() + "不能为空");
		}
	}

	/***
	 * @Description 检查取值范围的合法性
	 * @Author tianshujian
	 * @Date 18:52 2020/9/12
	 **/
	private static void checkValueArea(BankRequestField annotation, Object value) {

		boolean isValueFit = annotation.isEnum() ? false : true;
		if (annotation.isEnum() && Objects.nonNull(value)) {
			String[] values = annotation.valueArea();
			for (String s : values) {
				if(s.equals(value)) {
					isValueFit = true;
				}
			}
		}

		if(!isValueFit) {
			throw new RuntimeException(annotation.desc() + ":" + annotation.name() + "=" + value + ",不在合法取值范围，取值范围为：" + Arrays.toString(annotation.valueArea()) );
		}
	}
}
