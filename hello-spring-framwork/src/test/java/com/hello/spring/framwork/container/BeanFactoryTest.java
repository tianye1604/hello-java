package com.hello.spring.framwork.container;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

/**
 * @Auther: Tian.shujian 18066629
 * @Date: 2019/3/4 17:09
 * @Description:
 *	读取配置文件beanFactoryTest.xml
 *	根据beanFactoryTest.xml中的配置找到对应的类的配置,并实例化
 *	调用实例化后的实例
 *
 *	为了完成这个简单的过程,至少需要3个类
 *	ConfigReader: 用于读取及验证配置文件.
 *					我们要用配置文件里面的东西,当然首先要做的就是读取,然后放置在内存中.
 *	RelectionUtil: 用于根据配置文件中的配置进行反射实例化.
 *					比如在上例中beanFactoryTest.xml出现的<bean id="myTestBean" class="com.hello.spring.MyTestBean"/>
 *					我们就可以根据com.hello.spring.container.MyTestBean进行实例化
 *	App: 用户完成整个逻辑的串联
 */
public class BeanFactoryTest {

	@Test
	public void testSimpleLoad() {
		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanFactoryTest.xml"));
		MyTestBean bean = (MyTestBean) bf.getBean("myTestBean");
		Assert.assertEquals("testStr",bean.getTestStr());
	}
}
