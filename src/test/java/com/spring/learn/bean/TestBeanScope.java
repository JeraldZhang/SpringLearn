package com.spring.learn.bean;

import com.spring.learn.base.UnitTestBase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.springframework.boot.test.context.SpringBootTest;

import org.junit.Assert;

@RunWith(BlockJUnit4ClassRunner.class)
@SpringBootTest
public class TestBeanScope extends UnitTestBase {

	public TestBeanScope() {
		super("classpath*:spring-beanscope.xml");
	}

	@Test
	public void Scope_Singleton() {
		var beanScopeSingletonFirst = super.getBean("beanScopeSingleton");
		var beanScopeSingletonSecond = super.getBean("beanScopeSingleton");
		var expected = beanScopeSingletonFirst.hashCode();
		var actual = beanScopeSingletonSecond.hashCode();
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void Scope_Prototype() {
		BeanScope beanScopePrototypeFirst = super.getBean("beanScopePrototype");
		BeanScope beanScopePrototypeSecond = super.getBean("beanScopePrototype");
		Assert.assertNotSame("Prototype is not equals to each other",beanScopePrototypeFirst.hashCode(), beanScopePrototypeSecond.hashCode());
	}
}
