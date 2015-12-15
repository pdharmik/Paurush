package com.lexmark.services.util;

import java.lang.reflect.Method;
import java.util.Map;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import com.lexmark.contract.ServiceRequestListContract;
import com.lexmark.framework.logging.LEXLogger;

public class ObjectDebugUtil {
	private static Logger logger = LogManager.getLogger("");

	public static void main(String[] args) {
		ServiceRequestListContract contract = new ServiceRequestListContract();
		contract.setAccountId("12322");
		//logger.setLevel(Level.ALL);
		printObjectContent(contract, logger);
	}
	
	/**.
	 * This method prints complete object hierarchy. 
	 * @param object Parent Object 
	 * @param logger Logger instance
	 * @author Sagar sarkar
	 */
	@SuppressWarnings("unchecked")
	public static void printMultiObjectContent(Object object, Logger logger){
		logger.debug("printMultiObjectContent Started ************************************************************ ");
		if(object != null){
			Class a = object.getClass();
			Class[] classes = a.getClasses();
			for(Class clazz:classes){
				printObjectContent(clazz,  logger);
			}
		}
		logger.debug("printMultiObjectContent ended ************************************************************ ");
	}

	public static void printObjectContent(Object object, Logger logger) {
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
			logger.debug(log);

		} catch (Exception e) {
			logger.debug("???????????????????? Exception" + e.getMessage());
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
	
	/**
	 * This method prints the Contract object, but printObjectContent resulted in ClassCastException
	 * for History Contracts, so this method is called from Device Mgmt specifically
	 * @param object
	 * @param logger
	 */
	public static void printObjectContentForDM(Object object, Logger logger) {
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
						
						for(Map.Entry<String, Object> entry: map.entrySet()){
							
							value=value + "["+ entry.getKey() + "=" + entry.getValue().toString() + "]";
						}
						
						/*for (String propertieName : map.keySet()) {
							// if (map.get(propertieName) == null) {
							// continue;
							// }
							value = value + "[" + propertieName + "="
									+ (String) map.get(propertieName) + "]";
							// logger.info("Map--" + value);
						}*/
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
			logger.debug(log);

		} catch (Exception e) {
			logger.debug("???????????????????? Exception" + e.getMessage());
		}
	}
	
	/**
	 * This method prints the Contract object, but printObjectContent resulted in ClassCastException
	 * for History Contracts, so this method is called from Device Mgmt specifically
	 * @param object
	 * @param logger
	 */
	public static void printObjectContentForDM(Object object, LEXLogger logger) {
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
						
						for(Map.Entry<String, Object> entry: map.entrySet()){
							
							value=value + "["+ entry.getKey() + "=" + entry.getValue().toString() + "]";
						}
						
						/*for (String propertieName : map.keySet()) {
							// if (map.get(propertieName) == null) {
							// continue;
							// }
							value = value + "[" + propertieName + "="
									+ (String) map.get(propertieName) + "]";
							// logger.info("Map--" + value);
						}*/
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
			logger.debug(log);

		} catch (Exception e) {
			logger.debug("???????????????????? Exception" + e.getMessage());
		}
	}
}