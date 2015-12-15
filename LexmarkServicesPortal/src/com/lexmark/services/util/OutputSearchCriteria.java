package com.lexmark.services.util;

import java.util.Map;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class OutputSearchCriteria {
	
	private static Logger logger = LogManager.getLogger(OutputSearchCriteria.class);
	/**
	 * 
	 * @param methodName 
	 * @param searchCriterias 
	 * @param filterCriteria 
	 * @param sortCriteria 
	 */
	public static void printSearchCriterias(String methodName, 
			Map<String, Object> searchCriterias,  
			Map<String, Object> filterCriteria, 
			Map<String, Object> sortCriteria){
		   logger.debug("===========print out search criterias Start");
		for(String propertieName: searchCriterias.keySet()) {
    		if(searchCriterias.get(propertieName)==null) {
    			continue;
    		}
    		String conditionValue = (String) searchCriterias.get(propertieName);
    		logger.debug("==========="+methodName+"searchCriterias:propertieName=["+propertieName+"]value=["+conditionValue+"]");
		}
		for(String propertieName: filterCriteria.keySet()) {
    		if(filterCriteria.get(propertieName)==null) {
    			continue;
    		}
    		String conditionValue = (String) filterCriteria.get(propertieName);
    		logger.debug("==========="+methodName+"filterCriteria:propertieName=["+propertieName+"]value=["+conditionValue+"]");
		}
		for(String propertieName: sortCriteria.keySet()) {
    		if(sortCriteria.get(propertieName)==null) {
    			continue;
    		}
    		String conditionValue = (String) sortCriteria.get(propertieName);
    		logger.debug("============"+methodName+"sortCriteria:propertieName=["+propertieName+"]value=["+conditionValue+"]");
		}
		logger.debug("==============print out search criterias End");
	}
}
