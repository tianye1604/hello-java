package com.hello.spring.framework.ioc.overview.lookup;

import com.hello.spring.framework.annotation.Super;
import com.hello.spring.framework.domain.User;
import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

public class DependencyLookupDemo {

    public static void main(String[] args) {

        // 配置 XML 配置文件
        // 启动 Spring 应用上下文
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:beanFactoryTest.xml");
        lookupInRealTime(beanFactory);
        lookupInLazy(beanFactory);

        // 按类型查找
        lookupByType(beanFactory);
        // 根据 Bean 名称 + 类型查找
        lookupByBeanNameAndType(beanFactory);

        // 按照类型查找集合对象
        lookupCollectionByType(beanFactory);

        // 通过注解查找
        lookupByAnnotationType(beanFactory);

    }



    private static void lookupByAnnotationType(BeanFactory beanFactory) {
        if(beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String,User> users = (Map)listableBeanFactory.getBeansWithAnnotation(Super.class);
            System.out.println("查找标注 @Super 所有的集合对象：" + users);
        }
    }

    private static void lookupCollectionByType(BeanFactory beanFactory) {
        if(beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String,User> users = listableBeanFactory.getBeansOfType(User.class);
            System.out.println("查找所有的 User 集合对象：" + users);
        }
    }
    private static void lookupByBeanNameAndType(BeanFactory beanFactory) {
        User user = beanFactory.getBean("user",User.class);
        System.out.println("根据 Bean 名称 + 类型查找：" + user);
    }

    private static void lookupByType(BeanFactory beanFactory) {
        User user = beanFactory.getBean(User.class);
        System.out.println("类型查找：" + user);
    }

    private static void lookupInRealTime(BeanFactory beanFactory) {
        User user = (User)beanFactory.getBean("user");
        System.out.println("实时查找：" + user);
    }

    private static void lookupInLazy(BeanFactory beanFactory) {
        // ObjectFactory 不会生成新的 bean ，但是改为 FactoryBean 的话，情况就不一样了
        ObjectFactory<User> objectFactory = (ObjectFactory<User>)beanFactory.getBean("objectFactory");
        User user = objectFactory.getObject();
        System.out.println("延迟查找：" + user);
    }
}
