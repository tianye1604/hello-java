package com.hello.factory.study;

public class Test {

	public static void main(String[] args) {
		
//		VihecleFactory factory = new CarFactory();
		VihecleFactory factory = new PlaneFactory();
		Moveable m = factory.create();
		m.run();

	}

}
