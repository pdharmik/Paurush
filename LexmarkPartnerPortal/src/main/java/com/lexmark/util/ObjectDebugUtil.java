package com.lexmark.util;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.lexmark.contract.ServiceRequestListContract;
import com.lexmark.domain.Activity;
import com.lexmark.domain.PartLineItem;

import org.aspectj.lang.JoinPoint;
import com.lexmark.framework.logging.LEXLogger;

public class ObjectDebugUtil {
	private static Logger logger = LogManager.getLogger("");

	public static void outputObjectContect(String prefix, Object object){
		try{
			JAXBContext context = JAXBContext.newInstance(object.getClass());
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.setProperty(Marshaller.JAXB_FRAGMENT, false);
			
			File f = new File(object.getClass().toString()+".xml");
			logger.debug("XML FILE WILL BE CREATED UNDER FOLDER "+System.getProperty("user.home"));
			String path = System.getProperty("user.home");
			FileOutputStream fops = new FileOutputStream(path+"\\"+prefix+"_"+f);
			//marshaller.marshal(object,logger.info);
			marshaller.marshal(object,fops);
		}catch(Exception e){
			logger.debug("Exception in creating XML File");
		}
		
	}
	//AOP only
	public static void outputObjextContect2XML(JoinPoint joinPoint){
		try{
			Object[] args=joinPoint.getArgs();
			Object arg = args[0];
			Object arg1 = joinPoint.getSignature().getDeclaringType();
			JAXBContext context = JAXBContext.newInstance(arg.getClass());
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.setProperty(Marshaller.JAXB_FRAGMENT, false);
			
			File f = new File(arg.getClass().toString()+".xml");
			logger.info("File will be created under folder "+System.getProperty("user.home"));
			String path = System.getProperty("user.home");
			FileOutputStream fops = new FileOutputStream(path+"\\"+f);
			//marshaller.marshal(object,logger.info);
			marshaller.marshal(arg,fops);
		}catch(Exception e){
			logger.debug("Exception in creating File");
		}
		
	}
	
	public static void printObjectContent(Object object, Logger logger) {
		try {
			Class a = object.getClass();
			Method[] methods = a.getMethods();
			StringBuffer log = new StringBuffer("");
			log.append("\r\n[START " + a.getName()+ "]\r\n");
			for (Method method : methods) {
				String value = "";
				String type = "";
				if (method.getName().startsWith("get")) {
					Object obj = method.invoke(object);
					type = method.getGenericReturnType().toString();
					if (type.contains("java.util.Map")) {
						Map<String, Object> map = (Map<String, Object>) obj;
						for (String propertieName : map.keySet()) {
							// if (map.get(propertieName) == null) {
							// continue;
							// }
							value = value + "[" + propertieName + "="
									+ map.get(propertieName).toString() + "]";
							// logger.info("Map--" + value);
						}
					} else {
						if (obj != null) {
							value = obj.toString();
						}
					}
					log.append(type + " " + method.getName() + "()=" + value);
					log.append("\r\n");
					
				}
				if (method.getName().startsWith("is")) {
					Object obj = method.invoke(object);
					type = method.getGenericReturnType().toString();
					if (type.contains("java.util.Map")) {
						Map<String, Object> map = (Map<String, Object>) obj;
						for (String propertieName : map.keySet()) {
							if(map.get(propertieName)!= null){
								value = value + "[" + propertieName + "="
								+  map.get(propertieName).toString() + "]";
						
							}
							
						}
					} else {
						if (obj != null) {
							value = obj.toString();
						}
					}
					log.append(type + " " + method.getName() + "()=" + value);
					log.append("\r\n");
					
				}
			}
			log.append("================END " + a.getName()+ "================\r\n");
			logger.debug(log);

		} catch (Exception e) {
			logger.debug("================ printObjectContent Exception" + e.getMessage());
		}
	}
	
	public static void printObjectContent(Object object, LEXLogger logger) {
		try {
			Class a = object.getClass();
			Method[] methods = a.getMethods();
			StringBuffer log = new StringBuffer("");
			log.append("*********START " + a.getName()+ "*********\r\n");
			for (Method method : methods) {
				String value = "";
				String type = "";
				if (method.getName().startsWith("get")) {
					Object obj = method.invoke(object);
					type = method.getGenericReturnType().toString();
					if (type.contains("java.util.Map")) {
						Map<String, Object> map = (Map<String, Object>) obj;
						for (String propertieName : map.keySet()) {
							if(map.get(propertieName)!= null){
								value = value + "[" + propertieName + "="
								+  map.get(propertieName).toString() + "]";
						
							}
							
						}
					} else {
						if (obj != null) {
							value = obj.toString();
						}
					}
					log.append(type + " " + method.getName() + "()=" + value);
					log.append("\r\n");
					
				}
				if (method.getName().startsWith("is")) {
					Object obj = method.invoke(object);
					type = method.getGenericReturnType().toString();
					if (type.contains("java.util.Map")) {
						Map<String, Object> map = (Map<String, Object>) obj;
						for (String propertieName : map.keySet()) {
							if(map.get(propertieName)!= null){
								value = value + "[" + propertieName + "="
								+  map.get(propertieName).toString() + "]";
						
							}
							
						}
					} else {
						if (obj != null) {
							value = obj.toString();
						}
					}
					log.append(type + " " + method.getName() + "()=" + value);
					log.append("\r\n");
					
				}
			}
			log.append("*********END " + a.getName()+ "*********\r\n");
			logger.logContract(log);

		} catch (Exception e) {
			logger.debug("???????????????????? Exception" + e.getMessage());
		}
	}
}