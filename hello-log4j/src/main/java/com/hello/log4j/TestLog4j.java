package com.hello.log4j;

import org.apache.log4j.Logger;

/**
 * 1、如何取logger
 * 	 这个logger可以被几个log4j.logger管理
 * 2、用什么样的级别打印
 * @author tianye
 *
 */
public class TestLog4j {
	//用类对象来取得logger,就相当于是全类名
	private static final Logger logger = Logger.getLogger(TestLog4j.class);
	
	public static void main(String[] args) {
		logger.debug("debug");
		logger.info("info");
		logger.warn("warn");
		logger.error("error");
		
	}
}
