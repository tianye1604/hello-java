package com.hello.factory.entity;


public class Plane implements Moveable{

	@Override
	public void run() {
		System.out.println("扇着翅膀飞了……");
	}

}
