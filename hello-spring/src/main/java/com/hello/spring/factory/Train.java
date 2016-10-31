package com.hello.spring.factory;

import org.springframework.stereotype.Component;

@Component
public class Train implements Moveable{

	public void run() {
		System.out.println("呜呜~~~");
	}
	
}
