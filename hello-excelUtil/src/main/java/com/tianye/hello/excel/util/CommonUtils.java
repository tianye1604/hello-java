package com.tianye.hello.excel.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

/**
 * @author chen shi(18066693)
 * @date 2018年8月4日
 */
@SuppressWarnings("rawtypes")
public class CommonUtils {
	private static Logger LOGGER = LoggerFactory.getLogger(CommonUtils.class);

	private CommonUtils() {
	}

	/**
	 * 正则表达式：验证邮箱
	 */
	public static final String REGEX_EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";

	/**
	 * 判断是否是邮箱
	 * 
	 * @param email
	 * @return
	 */
	public static boolean isEmail(String email) {
		return Pattern.matches(REGEX_EMAIL, email);
	}

	/**
	 * 取某个范围内的随机数
	 */
	public static String getRandom(int min, int max) {
		Random random = new Random();
		int s = random.nextInt(max) % (max - min + 1) + min;
		return String.valueOf(s);

	}

	/**
	 * 时间戳转yyyy-MM-dd HH:mm:ss
	 * 
	 * @param seconds
	 * @return
	 */
	public static String timeStamp2Date(String seconds) {
		if (CommonUtils.isEmpty(seconds)) {
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(new Date(Long.valueOf(seconds)));
	}

	/**
	 * 判断字符串是否全为数字 判断字符串是否全为数字
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str) {
		if (str == null) {
			return false;
		}
		int sz = str.length();
		for (int i = 0; i < sz; i++) {
			if (Character.isDigit(str.charAt(i)) == false) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 判断字符串是否为空
	 * 
	 * @param value
	 * @return
	 */
	public static boolean isEmpty(String value) {
		if (value == null || value.trim().length() == 0 || "null".equals(value)) {
			return true;
		}
		return false;
	}

	/**
	 * 判断字符串非空
	 * 
	 * @param value
	 * @return
	 */
	public static boolean isNotEmpty(String value) {
		return !isEmpty(value);
	}

	/**
	 * 判断List是否为空
	 * 
	 * @param value
	 * @return
	 */
	public static boolean isEmpty(List value) {
		if (null == value || value.isEmpty()) {
			return true;
		}
		return false;
	}

	/**
	 * 判断Object是否为空
	 * 
	 * @param value
	 * @return
	 */
	public static boolean isEmpty(Object value) {
		if (value == null) {
			return true;
		}
		return false;
	}

	/**
	 * 判断Map是否为空
	 * 
	 * @param value
	 * @return
	 */
	public static boolean isEmpty(Map value) {
		if (value == null || value.isEmpty()) {
			return true;
		}
		return false;
	}

	/**
	 * (获取uuid)
	 */
	public static String getUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}

	/**
	 * 字符串进行URLEncode编码)  
	 */
	public static String getURLEncodeStr(String str) {
		try {
			if (!isEmpty(str)) {
				return URLEncoder.encode(str, "UTF-8");
			}
			return str;
		} catch (Exception ex) {
			LOGGER.error("getURLEncodeStr str is {} , err is {}", str, ex.getMessage(), ex);
		}
		return str;
	}

	/**
	 * (获取uuid，将“-”替换为“replaceStr”)
	 * 
	 * @param replaceStr
	 * @return: String   
	 */
	public static String getUUIDReplace(String replaceStr) {
		return UUID.randomUUID().toString().replace("-", replaceStr);
	}

	/**
	 * 获取传入日期是星期几
	 * 
	 * @param dt
	 * @return
	 */
	public static String getWeekOfDate(Date dt) {
		String[] weekDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0)
			w = 0;
		return weekDays[w];
	}

	/**
	 * 检测手机号是否正确(专属客服二期这跟前端用的一个正则表达式
	 * 
	 * @param mobile
	 * @return
	 */
	public static boolean isMobile(String mobile) {
		return Pattern.matches("^1\\d{2}\\d{4}\\d{4}$", mobile);
	}




	/**
	 * 向左补0
	 * 
	 * @param str 需要比对的的字符串
	 * @param strLength,预期长度,如果不满足向左补0
	 * @return
	 */
	public static String padLeft(String str, int strLength) {
		String newStr = str;
		int strLen = newStr.length();
		while (strLen < strLength) {
			StringBuilder sb = new StringBuilder();
			sb.append("0").append(newStr);// 左补0
			newStr = sb.toString();
			strLen = newStr.length();
		}
		return newStr;
	}

	/**
	 * 向右补0
	 * 
	 * @param str 需要比对的的字符串
	 * @param strLength,预期长度,如果不满足向左补0
	 * @return
	 */
	public static String padRight(String str, int strLength) {
		String newStr = str;
		int strLen = newStr.length();
		while (strLen < strLength) {
			StringBuilder sb = new StringBuilder();
			sb.append(newStr).append("0");// 右补0
			newStr = sb.toString();
			strLen = newStr.length();
		}
		return str;
	}

	/**
	 * 将list分成小list
	 * 
	 * @param list,需要处理的大list
	 * @param listCountLimit,每个小list里需要有多少个数量
	 * @return list类型的小list,需要调用方自己去处理
	 */
	public static List splitListByCount(List list, int listCountLimit) {
		List newList = new ArrayList<>();
		if (list.size() <= listCountLimit) // 如果需要处理的list的数量小于每组的数量
		{
			newList.add(list);
			return newList;
		}
		for (int i = 0; i <= list.size() / listCountLimit; i++) {
			if (list.size() > listCountLimit * (i + 1)) {
				newList.add(list.subList(i * listCountLimit, listCountLimit * (i + 1)));
			} else {
				List subList = list.subList(i * listCountLimit, list.size());
				if (!subList.isEmpty()) {
					newList.add(subList);
				}
			}
		}
		return newList;
	}
}
