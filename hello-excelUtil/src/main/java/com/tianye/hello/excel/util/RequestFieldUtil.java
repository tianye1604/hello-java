package com.tianye.hello.excel.util;

import com.tianye.hello.excel.annotation.RequestField;
import com.tianye.hello.util.EnumUtil;
import com.tianye.hello.util.enums.BankEnum;

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
			RequestField annotation = field.getAnnotation(RequestField.class);
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

	private static void checkValueLegality(RequestField annotation, Object value) {
		checkValueCanNull(annotation,value);
//		checkValueArea(annotation,value);
		checkValueType(annotation,value);
		checkValueLength(annotation,value);
	}

	private static void checkValueType(RequestField annotation, Object value) {
		if (annotation.isEnum() && Objects.nonNull(value)) {
			Class<? extends BankEnum> bankEnum = annotation.valueType();
			BankEnum be = EnumUtil.getByName(value.toString(),bankEnum);
			if(Objects.isNull(be)) {
				throw new RuntimeException(annotation.desc() + ":" + annotation.name() + "=" + value + ",不在合法取值范围，取值范围为：" + Arrays.toString(EnumUtil.getNames(bankEnum)) );
			}
		}
	}

	/***
	 * @Description 检查取值长度
	 * @Author tianshujian
	 * @Date 19:09 2020/9/12
	 **/
	private static void checkValueLength(RequestField annotation, Object value) {
		if(Objects.nonNull(value) && value instanceof  String) {
			if(value.toString().length() > annotation.maxLength()) {
				throw new RuntimeException(annotation.desc() + ":" + annotation.name() + "=" + value + ",长度超出约定界限，约定界限为：" + annotation.maxLength() );
			}
		}
	}

	/***
	 * @Description 检查取值是否可空
	 * @Author tianshujian
	 * @Date 18:52 2020/9/12
	 **/
	private static void checkValueCanNull(RequestField annotation, Object value) {
		if(!annotation.canNull() && Objects.isNull(value)) {
			throw new RuntimeException(annotation.desc() + ":" + annotation.name() + "不能为空");
		}
	}

	/***
	 * @Description 检查取值范围的合法性
	 * @Author tianshujian
	 * @Date 18:52 2020/9/12
	 **/
	private static void checkValueArea(RequestField annotation, Object value) {

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
