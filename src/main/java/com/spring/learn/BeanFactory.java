package com.spring.learn;

import org.springframework.beans.BeansException;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.StringUtils;

public class BeanFactory {
	public BeanFactory() {
	}
    public BeanFactory(String beanXmlpath) {
        springXmlpath = beanXmlpath;
    }

    private ClassPathXmlApplicationContext context;
	
	private String springXmlpath;
	
	public void before() {
		if (StringUtils.isEmpty(springXmlpath)) {
			springXmlpath = "classpath*:spring-*.xml";
		}
		try {
			context = new ClassPathXmlApplicationContext(springXmlpath.split("[,\\s]+"));
			context.start();
		} catch (BeansException e) {
			e.printStackTrace();
		}
	}
	
	public void after() {
		context.destroy();
	}
	
	@SuppressWarnings("unchecked")
	protected <T extends Object> T getBean(String beanId) {
		try {
			return (T)context.getBean(beanId);
		} catch (BeansException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	protected <T extends Object> T getBean(Class<T> clazz) {
		try {
			return context.getBean(clazz);
		} catch (BeansException e) {
			e.printStackTrace();
			return null;
		}
	}
}
