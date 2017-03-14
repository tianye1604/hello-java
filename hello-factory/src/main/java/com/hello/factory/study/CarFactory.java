package com.hello.factory.study;

import com.hello.factory.entity.Car;
import com.hello.factory.entity.Moveable;

public class CarFactory extends VihecleFactory {

	@Override
	public Moveable create() {
		//此处可加权限控制
		return new Car();
	}

}
