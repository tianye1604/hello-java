package com.hello.spring.factory;

import org.springframework.stereotype.Component;

@Component
public class Plane implements Moveable{

	public void run() {
		System.out.println("扇着翅膀飞了……");
	}

}
