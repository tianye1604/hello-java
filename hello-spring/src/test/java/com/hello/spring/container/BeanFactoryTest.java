package com.hello.spring.container;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

/**
 * @Auther: Tian.shujian 18066629
 * @Date: 2019/3/4 17:09
 * @Description:
 */
public class BeanFactoryTest {

	@Test
	public void testSimpleLoad() {
		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanFactoryTest.xml"));
		MyTestBean bean = (MyTestBean) bf.getBean("myTestBean");
		Assert.assertEquals("testStr",bean.getTestStr());
	}
}
