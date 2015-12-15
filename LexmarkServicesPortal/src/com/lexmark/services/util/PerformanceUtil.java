package com.lexmark.services.util;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Properties;
//import java.util.concurrent.Callable;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//import java.util.concurrent.FutureTask;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

//import com.lexmark.exceptionimpl.checked.LGSCheckedException;
import com.lexmark.util.PropsFileLoadUtility;

public class PerformanceUtil {
	/*@Autowired
	private ExecutorService executorService;*/
	private static Logger LOGGER = LogManager.getLogger(PerformanceUtil.class);	
		
	/*public void calcTime(final Logger logger,final String message,final long startTime,final long endTime,final String logFrom,Object contract)
	throws LGSCheckedException
	{
		try{
			createFutureTask(logger, message,startTime,endTime,logFrom,contract);
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}	
	}
	
	private void createFutureTask(final Logger logger,final String message,final long startTime,final long endTime,final String logFrom,final Object contract) {
		ExecutorService executor = Executors.newFixedThreadPool(1);
		FutureTask<String> future= new FutureTask<String>(
                new Callable<String>()
                {                	
                    public String call()
                    {                    	
                    	writeLogger(logger,message,startTime,endTime,logFrom,contract);
                    	return "success";
                    	//return writeLogger(logger, message,startTime,logFrom);
                    }
                });
		executor.execute(future);		
		if(future.isDone())
		{
			executor.shutdown();
		}
	}
	
	public static void calcTime(Logger logger,String message,long startTime,long endTime,String logFrom,Object contract){							
		Properties props = PropsFileLoadUtility.getConfigurationFile(PerformanceConstant.PERF_FILE_PATH);			
		Boolean dbFlag = Boolean.parseBoolean(props.getProperty(PerformanceConstant.PERF_DB_FLAG));			
		if(!dbFlag){
			writeLoggerProp(logger,message,startTime,endTime,logFrom,contract);			
		}else{
			writeLoggerDB(logger,message,startTime,endTime,logFrom,contract);
		}
	}
	
	private static void writeLoggerDB(Logger logger,String message,long startTime,long endTime,String logFrom,Object contract){
		Properties props = PropsFileLoadUtility.getConfigurationFile(PerformanceConstant.PERF_FILE_PATH);
	}*/
	/**
	 * 
	 * @param logger 
	 * @param message 
	 * @param startTime 
	 * @param endTime 
	 * @param logFrom 
	 * @param contract 
	 */
	public static void calcTime(Logger logger,String message,long startTime,long endTime,String logFrom,Object contract){
		Properties props = PropsFileLoadUtility.getConfigurationFile(PerformanceConstant.PERF_FILE_PATH);
		Boolean debugFlag = Boolean.parseBoolean(props.getProperty(PerformanceConstant.PERF_DEBUG));
		Boolean errorFlag = Boolean.parseBoolean(props.getProperty(PerformanceConstant.PERF_ERROR));
		Double debugTimeLimit = Double.parseDouble(props.getProperty(PerformanceConstant.DEBUG_TIME_LIMIT));
		Double errorTimeLimit = Double.parseDouble(props.getProperty(PerformanceConstant.ERROR_TIME_LIMIT));
		LOGGER.debug("Inside write logger");
		if(debugFlag){
				LOGGER.debug("Logger is enabled");
				double perfTime = (endTime - startTime)/1000.0;
				if(perfTime>debugTimeLimit && perfTime<errorTimeLimit){
					StringBuffer log = new StringBuffer("");
					log.append("WARNING: The call is taking more than "+debugTimeLimit+" Seconds ");
					log.append(message);
					log.append("==>:");
					log.append(perfTime);
					log.append(" FROM SYSTEM: ");
					log.append(logFrom);
					logger.error(log);
					Boolean debugContractFlag = Boolean.parseBoolean(props.getProperty(PerformanceConstant.PERF_DEBUG_CONTRACT));
					if(debugContractFlag){
						printErrorObjectContent(contract, logger);
					}
			}
		}
		
		if(errorFlag){				
				double perfTime = (endTime - startTime)/1000.0;
				if(perfTime>errorTimeLimit){
					StringBuffer log = new StringBuffer("");
					log.append("ERROR: The call is taking more than "+errorTimeLimit+" Seconds ");
					log.append(message);
					log.append("==>:");
					log.append(perfTime);
					log.append(" FROM SYSTEM: ");
					log.append(logFrom);
					logger.error(log);
					Boolean errorContractFlag = Boolean.parseBoolean(props.getProperty(PerformanceConstant.PERF_ERROR_CONTRACT));
					if(errorContractFlag){
						printErrorObjectContent(contract, logger);
					}
				}
		}		
	}
	/**
	 * 
	 * @param object 
	 * @param logger 
	 */
	public static void printErrorObjectContent(Object object, Logger logger) {
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
			logger.error(log);

		} catch (Exception e) {
			logger.error("???????????????????? Exception" + e.getMessage());
		}
	}
	
}