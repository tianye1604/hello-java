package com.tianye.hello.excel.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Auther: Tian.shujian 18066629
 * @Date: 2019/4/26 15:59
 * @Description: 导出excel注解,标识顺序和标题
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ExcelCell {
	/**
	 *  顺序
	 * @return index
	 */
	int index();

	/**
	 * 当值为null时要显示的值 default StringUtils.EMPTY
	 *
	 * @return defaultValue
	 */
	String defaultValue() default "";

	/**
	 *  标题
	 * @return title
	 */
	String title() default "";

}
