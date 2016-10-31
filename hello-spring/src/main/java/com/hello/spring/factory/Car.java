package com.hello.spring.factory;

import org.springframework.stereotype.Component;

@Component
public class Car implements Moveable{
	
	public void run() {
		System.out.println("冒着烟……");
	}
}
