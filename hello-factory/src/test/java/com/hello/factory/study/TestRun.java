package com.hello.factory.study;

import com.hello.factory.entity.Moveable;
import org.junit.Test;

public class TestRun {


	@Test
	public void testRun() {
		
		VihecleFactory factory = new CarFactory();
//		VihecleFactory factory = new PlaneFactory();
		Moveable m = factory.create();
		m.run();

	}

}
