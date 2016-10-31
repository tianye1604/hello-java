package com.hello.factory.study;

public class PlaneFactory extends VihecleFactory {

	public Moveable create() {
		return new Plane();
	}

}
