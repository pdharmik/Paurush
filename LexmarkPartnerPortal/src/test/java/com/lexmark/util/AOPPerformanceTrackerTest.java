package com.lexmark.util;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.lexmark.service.api.ProductImageService;


public class AOPPerformanceTrackerTest {
	private ApplicationContext appContext;
	private ProductImageService productImageService;
	//@Before
	public void setup() {
		appContext = new FileSystemXmlApplicationContext(
		        new String[]{"WebContent/WEB-INF/context/applicationContext.xml"});
		productImageService = (ProductImageService) appContext.getBean("productImageService");
	}
	
	@Test
	public void testAOP() {
		Assert.assertTrue(true);
	}
}
