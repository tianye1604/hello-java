package com.tianye.hello.excel.util;

import com.tianye.hello.excel.annotation.BankRequestField;

import java.lang.reflect.Field;
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
				if(!annotation.canNull() && Objects.isNull(value)) {
					throw new RuntimeException(annotation.desc() + ":" + name + "不能为空");
				}
				if (Objects.nonNull(value)) {
					filedMap.put(name,value);
				}
				continue;
			}
		}
		return filedMap;
	}
}
