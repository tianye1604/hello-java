package com.hello.factory.study;

import com.hello.factory.entity.Moveable;

/**
 * 交通工具抽象工厂
 * @author tianye
 *
 */
public abstract class VihecleFactory {
	public abstract Moveable create();
}
