package com.hello.factory.spring;

import java.util.HashMap;
import java.util.Map;

public class ClassPathXmlApplicationContext extends BeanFactory {
	
	private Map<String,Object> container = new HashMap<String,Object>();
	
	public ClassPathXmlApplicationContext(String filePath){
		
	}

	@Override
	public Object getBaen(String id) {
		return null;
	}

}
