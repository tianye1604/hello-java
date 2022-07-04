package com.hello.spring.framework.ioc.overview.injection;

import com.hello.spring.framework.repository.UserRepository;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DependencyInjectionDemo {

    public static void main(String[] args) {

        // 配置 XML 配置文件
        // 启动 Spring 应用上下文
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:dependency-injection-context.xml");
        // 依赖来源一：自定义 Bean
        UserRepository userRepository = beanFactory.getBean("userRepository", UserRepository.class);

//        System.out.println(userRepository.getUsers());

        System.out.println(userRepository.getBeanFactory());

        System.out.println(userRepository.getBeanFactory()==beanFactory);

//        System.out.println(beanFactory.getBean(BeanFactory.class));

        ObjectFactory userFactory = userRepository.getUserObjectFactory();

        System.out.println(userFactory.getObject());



    }


}
