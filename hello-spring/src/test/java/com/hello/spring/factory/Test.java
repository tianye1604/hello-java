package com.hello.spring.factory;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration 
@ComponentScan
public class Test {
	
	public static void main(String[] args) {
//		BeanFactory f = new ClassPathXmlApplicationContext("applicationContext.xml");
//		Object o = f.getBean("v");
//		Moveable m = (Moveable)o;
//		m.run();
		
		ApplicationContext context = new AnnotationConfigApplicationContext(Test.class);
		Moveable m = context.getBean(Car.class);
		m.run();
	}

}
