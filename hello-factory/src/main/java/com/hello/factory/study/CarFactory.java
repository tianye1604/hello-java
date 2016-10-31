package com.hello.factory.study;

public class CarFactory extends VihecleFactory {

	@Override
	public Moveable create() {
		//此处可加权限控制
		return new Car();
	}

}
