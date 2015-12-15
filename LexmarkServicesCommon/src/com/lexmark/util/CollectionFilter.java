package com.lexmark.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import com.lexmark.domain.Pagination;
import com.lexmark.util.DateLocalizationUtil;

public class CollectionFilter {
	private static final Logger  logger = LogManager.getLogger(CollectionFilter.class);
	private Locale locale;
	
	public CollectionFilter(Locale locale) {
		this.locale = locale;
	}
	
	
    /**
     * Fitler the collection on a single property.  FilterCriteria    name:abc
     *
     * @param collection the collection to be filtered.
     * @param filterCriteria the property to filter.
     */
    public List filter(List collection, String filterCriteria)
    {
        List<String> filterCriteriaList = new ArrayList<String>(1);
        filterCriteriaList.add(filterCriteria);

            return filter(collection, filterCriteriaList);
    }

    public List filter(List collection, List<String> filterCriteriaList)
    {
    	return filter(collection, filterCriteriaList, null);
    }
    
    public List filter(List collection, List<String> filterCriteriaList, List<String> searchCriteriaList)
    {
       	if(collection == null) return null;
    	List result = new ArrayList();
    	if((filterCriteriaList == null || filterCriteriaList.size() == 0 )&& 
    		(searchCriteriaList == null || searchCriteriaList.size() == 0)) {
    		result.addAll(collection);
    		return result;
    	}
    	Map<String, Object> searchCriteriaMap = getCriteriaMap(searchCriteriaList);
    	Map<String, Object> filterCriteriaMap = getCriteriaMap(filterCriteriaList);
        
    	if(logger.isDebugEnabled()) {
    		logger.debug("Search Criteria is" + searchCriteriaMap);
    		logger.debug("Filter Criteria is" + filterCriteriaMap);
    	}
    	
        return filter(collection, filterCriteriaMap, searchCriteriaMap);
    }
    
    public List filter(List collection, Pagination pagination) {
    	return filter(collection, pagination.getFilterCriteria(), pagination.getSearchCriteria());
    }
    
    public List filter(List list, Map<String, Object> filterCriteriaMap, Map<String, Object> searchCriteriaMap)
    {
    	List result = new ArrayList();
    	
    	for(int i=0; i< list.size(); i++) {
    		if(filter(list.get(i), searchCriteriaMap, false) &&
    				filter(list.get(i), filterCriteriaMap, true)) {
    			result.add(list.get(i));
    		}  
    	}
    	return result;
    }
    
    public List filter(List list, Map<String, Object> criteriaMap, boolean fuzzy)
    {
    	List result = new ArrayList();
    	for(int i=0; i< list.size(); i++) {
    		if(filter(list.get(i), criteriaMap, fuzzy)) {
    			result.add(list.get(i));
    		} 
    	}
    	return result;
    }
    //added for LEX:AIR00059709
    public List filterAccounts(List list, Map<String, Object> criteriaMap, boolean fuzzy)
    {
    	List result = new ArrayList();
    	for(int i=0; i< list.size(); i++) {
    		if(filterAccounts(list.get(i), criteriaMap, fuzzy)) {
    			result.add(list.get(i));
    		} 
    	}
    	return result;
    }
    
    private Map<String, Object> getCriteriaMap(List<String> filterCriteriaList) {
    	Map<String, Object> map = new HashMap<String, Object>();
    	if(filterCriteriaList == null) {
    		return map;
    	}
    	for(String criteria: filterCriteriaList) {
    		if(criteria == null || criteria.indexOf(":")<0) {
    			continue;
    		}
    		String propertieName = criteria.substring(0, criteria.indexOf(":"));
    		String conditionValue = criteria.substring(criteria.indexOf(":")+1);
    		map.put(propertieName, conditionValue);
    	}
    	return map;
    }
    
    
    private boolean filter(Object object, Map<String, Object> criteriaMap, boolean fuzzy) {
    	
    	boolean result=true;
    	if(criteriaMap == null || criteriaMap.size()==0) {
    		return true;
    	}
    	
    	for(String propertieName: criteriaMap.keySet()) {
    		if(criteriaMap.get(propertieName)==null) {
    			continue;
    		}
    		Object conditionObject = criteriaMap.get(propertieName);
    		List<String> listValue = null;
    		if(conditionObject instanceof String) {
    			listValue = new ArrayList<String>(); 
    			listValue.add((String)conditionObject);
    		} else if(conditionObject instanceof List) {
    			listValue = (List<String>) conditionObject;
    		}
    		if(listValue == null) {
    			return false;
    		}
    		boolean oneKeyResult = false;
    		for(String conditionValue  : listValue) {
    			if(conditionValue != null) {
    				conditionValue = conditionValue.trim();
    			}
    			Object property = null;
    			try
    			{
    				String  propertyNameWithoutSelfClassName = 
    					getPropertyNameWithoutSelfClassName(object, propertieName);
    				
    				property = PropertyUtils.getProperty(object, propertyNameWithoutSelfClassName);
    			} catch(Exception e)
    			{
    				throw new IllegalArgumentException("Could not retrieve comparable value for '"
    						+ property + "' from " + object + ": " + e);
    			}
    			if(property == null) {
    				 continue;
    			}

    			if(property instanceof String) {
    				String value = ((String) property).toLowerCase();
    				
    				if(fuzzy) {
    					if(value.indexOf(conditionValue.toLowerCase())<0) {
    						continue;
    					}
    				} else {
    					if(value.indexOf(conditionValue.toLowerCase())!=0) {
    						continue;
    					}
    				}

    			} else  if(property instanceof Date){
    				String date = DateLocalizationUtil.formatDateLocale((Date)property, locale);
    				if(fuzzy) {
    					if(date.indexOf(conditionValue)<0) {
    						continue;
    					}
    				} else {
    					if(!date.equalsIgnoreCase(conditionValue)) {
    						continue;
    					}
    				}
    			}  else {
    				String value = property.toString().toLowerCase();
    				if(fuzzy) {
    					if(value.indexOf(conditionValue.toLowerCase())<0) {
    						continue;
    					}
    				} else {
    					if(value.indexOf(conditionValue.toLowerCase())!=0) {
    						continue;
    					}
    				}
    			}
    			oneKeyResult = true;
    			break;
    		}
    		
    		if(oneKeyResult == false) {
    			return false;
    		}
    	}
    	return result;
    }
    
   // added for  LEX:AIR00059709 
 private boolean filterAccounts(Object object, Map<String, Object> criteriaMap, boolean fuzzy) {
    
    	boolean result=true;
    	if(criteriaMap == null || criteriaMap.size()==0) {
    		return true;
    	}
    	   	for(String propertieName: criteriaMap.keySet()) {
    		if(criteriaMap.get(propertieName)==null) {
    			continue;
    		}
    		Object conditionObject = criteriaMap.get(propertieName);
    		List<String> listValue = null;
    		if(conditionObject instanceof String) {
    			listValue = new ArrayList<String>(); 
    			listValue.add((String)conditionObject);
    		} else if(conditionObject instanceof List) {
    			listValue = (List<String>) conditionObject;
    		}
    		if(listValue == null) {
    			return false;
    		}
    		boolean oneKeyResult = false;
    		for(String conditionValue  : listValue) {
    			if(conditionValue != null) {
    				conditionValue = conditionValue.trim();
    			}
    			Object property = null;
    			try
    			{
    				String  propertyNameWithoutSelfClassName = 
    					getPropertyNameWithoutSelfClassName(object, propertieName);
    				
    				property = PropertyUtils.getProperty(object, propertyNameWithoutSelfClassName);
    			} catch(Exception e)
    			{
    				throw new IllegalArgumentException("Could not retrieve comparable value for '"
    						+ property + "' from " + object + ": " + e);
    			}
    			if(property == null) {
    				 continue;
    			}

    			if(property instanceof String) {
    				String value = ((String) property).toLowerCase();
    				
    				if(fuzzy) {
    					
    					if(value.indexOf(conditionValue.toLowerCase())<0){
    						continue;
    					}
    				} else {
    					int val=value.indexOf(" "+conditionValue.toLowerCase());
    				 	if(value.indexOf(conditionValue.toLowerCase())!=0) {
    						if( value.indexOf(" "+conditionValue.toLowerCase())<0){
    					        
    						continue;
    						}
    					}
    				}

    			}  else {
    				String value = property.toString().toLowerCase();
    				if(fuzzy) {
    					if(value.indexOf(conditionValue.toLowerCase())<0) {
    						
    						continue;
    					}
    				} else {
    					
    					int val=value.indexOf(" "+conditionValue.toLowerCase());
    					if(value.indexOf(conditionValue.toLowerCase())!=0 ){
    						if( value.indexOf(" "+conditionValue.toLowerCase())<0){
    					
    						continue;
    						}
    					}
    				}
    			}
    			oneKeyResult = true;
    			break;
    		}
    		
    		if(oneKeyResult == false) {
    			return false;
    		}
    	}
    	return result;
    }
    private static String  getPropertyNameWithoutSelfClassName(Object object,  String propertyName) {
    	if(propertyName != null && propertyName.indexOf(".") > 0 && object != null) {
    		String firstLevelProperty = propertyName.substring(0, propertyName.indexOf("."));
    		String objectName = object.getClass().getSimpleName();
    		if(objectName.equals(firstLevelProperty)) {
    			return propertyName.substring(propertyName.indexOf(".") + 1);
    		}
    	}
    	return propertyName;
    }

	public Map<String, Object> updateFilterCriteriaForLOV(Map<String, Object>filterCriteria, List<String>lovAttributeList) {
		Map<String, Object> updatedFilter = new HashMap<String, Object>();
		for (String key : filterCriteria.keySet()) {
			if (lovAttributeList.contains(key)) {
				updatedFilter.put(key + ".value", filterCriteria.get(key));
			} else {
				updatedFilter.put(key, filterCriteria.get(key));
			}
		}
		return updatedFilter;
	}
}
