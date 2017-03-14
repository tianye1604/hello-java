package com.hello.factory.study;

import com.hello.factory.entity.Moveable;
import com.hello.factory.entity.Plane;

public class PlaneFactory extends VihecleFactory {

	public Moveable create() {
		return new Plane();
	}

}
