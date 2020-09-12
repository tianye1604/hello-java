package com.tianye.hello.excel.annotation;

import com.tianye.hello.util.enums.BankEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by tianshujian
 * Create Date: 2020/9/12 17:16
 * Description: ${DESCRIPTION}
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface RequestField {

	/**
	 * 名称
	 */
	String name() default "";

	/***
	 * 是否可空，默认可空
	 **/
	boolean canNull() default true;

	/***
	 * 描述
	 **/
	String desc() default "";

	/***
	 * 是否为枚举
	 **/
	boolean isEnum() default false;

	/***
	 * 属性取值范围
	 **/
	String[] valueArea() default {};

	/***
	 * 最大长度值
	 **/
	int maxLength() default 50;

	Class<? extends BankEnum> valueType() default BankEnum.class;

}
