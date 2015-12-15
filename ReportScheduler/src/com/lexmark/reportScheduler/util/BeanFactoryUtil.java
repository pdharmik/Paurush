package com.lexmark.reportScheduler.util;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

public class BeanFactoryUtil {
	public static Object getBean(String name) {
		ClassPathResource res = new ClassPathResource("applicationContext.xml");
		XmlBeanFactory factory = new XmlBeanFactory(res);
		return factory.getBean(name);
	}
}
