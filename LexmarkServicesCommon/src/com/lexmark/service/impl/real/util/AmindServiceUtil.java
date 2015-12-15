package com.lexmark.service.impl.real.util;

import static com.lexmark.util.LangUtil.isBlank;
import static com.lexmark.util.LangUtil.isEmpty;
import static com.lexmark.util.LangUtil.isNotBlank;
import static com.lexmark.util.LangUtil.isNotEmpty;
import static com.lexmark.util.LangUtil.trim;
import static java.lang.String.format;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import com.amind.common.service.SiebelBusinessServiceProxy;
import com.amind.data.service.ActionEvent;
import com.amind.data.service.ExecutionMode;
import com.amind.data.service.IDataManager;
import com.amind.data.service.QueryObject;
import com.amind.data.source.ISiebelDataSource;
import com.amind.session.Session;
import com.lexmark.domain.Account;
import com.lexmark.domain.ListOfValues;
import com.lexmark.domain.ServiceRequest;
import com.lexmark.service.api.SiebelCrmServiceException;
import com.lexmark.service.impl.real.AmindCrmSessionHandle;
import com.lexmark.service.impl.real.domain.CHLDo;
import com.lexmark.util.LangUtil;
import com.siebel.data.SiebelDataBean;
import com.siebel.data.SiebelException;
import com.siebel.data.SiebelPropertySet;


public class AmindServiceUtil {
	
	// TODO we can import constants from LexmarkConstants class
	public static final String GLOBAL_MDMLEVEL = "Global";
	public static final String DOMESTIC_MDMLEVEL = "Domestic";
	public static final String LEGAL_MDMLEVEL = "Legal";
	public static final String ACCOUNT_MDMLEVEL = "Account";
	public static final String SIEBEL_MDMLEVEL = "Siebel";
	
    private static final Logger logger  = LogManager.getLogger(AmindServiceUtil.class);

	// Utility class
	private AmindServiceUtil() {
	}
	
	/*
	 * @param mdmId
	 * @param mdmLevel
	 * @return
	 */
	public static String buildmdmSearchExpression(String mdmId, String mdmLevel,
			Map<String, String> fieldMap, boolean accountFlag, boolean includeMdmLevel) {

		StringBuilder expr = new StringBuilder();
		
		boolean countFlag = isNotEmpty(fieldMap);
		
		if (SIEBEL_MDMLEVEL.equals(mdmLevel)) {
			if(countFlag)
			{
				expr.append("[");
				expr.append(fieldMap.get("mdmLevel5AccountId"));
				expr.append("] = '");
			}
			else
			{
				expr.append("[mdmLevel5AccountId]='");
			}
		}
		else if (ACCOUNT_MDMLEVEL.equals(mdmLevel)) {
			if(countFlag)
			{
				expr.append("[");
				expr.append(fieldMap.get("mdmLevel4AccountId"));
				expr.append("] = '");
			}
			else
			{
				expr.append("[mdmLevel4AccountId]='");
			}
		}
		else if (LEGAL_MDMLEVEL.equals(mdmLevel)) {
			if(countFlag)
			{
				expr.append("[");
				expr.append(fieldMap.get("mdmLevel3AccountId"));
				expr.append("] = '");
			}
			else
			{
				expr.append("[mdmLevel3AccountId]='");
			}
		}
		else if (DOMESTIC_MDMLEVEL.equals(mdmLevel)) {
			if(countFlag)
			{
				expr.append("[");
				expr.append(fieldMap.get("mdmLevel2AccountId"));
				expr.append("] = '");
			}
			else
			{
				expr.append("[mdmLevel2AccountId]='");
			}
		}
		else if (GLOBAL_MDMLEVEL.equals(mdmLevel)) {
			if(countFlag)
			{
				expr.append("[");
				expr.append(fieldMap.get("mdmLevel1AccountId"));
				expr.append("] = '");
			}
			else
			{
				expr.append("[mdmLevel1AccountId]='");
			}
		}
		else {
	        throw new IllegalArgumentException("mdmLevel must be one of 'Global, Domestic, Legal, Account'.  Value was " + mdmLevel);
		}
		
		expr.append(mdmId);
		expr.append("'");
		
		// Not applicable for Global Contact Domain Object
		if(accountFlag)
		{
			if(countFlag)
			{
				expr.append(" AND [");
				expr.append(fieldMap.get("accountTransFlag"));
				expr.append("] = 'Y'");
			}
			else
			{
				expr.append(" AND [accountTransFlag] = 'Y'");
			}
		}
		if (includeMdmLevel) {
			expr.append(" AND [LXK SW Agreement Account MDM Level] ='Siebel'");
		}
		
    	return expr.toString();
	}
	public static String buildmdmSearchExpressionSR(String mdmId, String mdmLevel,
			Map<String, String> fieldMap, boolean accountFlag, boolean includeMdmLevel) {

		StringBuilder expr = new StringBuilder();
		
		boolean countFlag = isNotEmpty(fieldMap);
		expr.append("EXISTS( ");
		if (SIEBEL_MDMLEVEL.equals(mdmLevel)) {
			if(countFlag)
			{
				expr.append("[");
				expr.append(fieldMap.get("mdmLevel5AccountId"));
				expr.append("] = '");
			}
			else
			{
				expr.append("[mdmLevel5AccountId]='");
			}
		}
		else if (ACCOUNT_MDMLEVEL.equals(mdmLevel)) {
			if(countFlag)
			{
				expr.append("[");
				expr.append(fieldMap.get("mdmLevel4AccountId"));
				expr.append("] = '");
			}
			else
			{
				expr.append("[mdmLevel4AccountId]='");
			}
		}
		else if (LEGAL_MDMLEVEL.equals(mdmLevel)) {
			if(countFlag)
			{
				expr.append("[");
				expr.append(fieldMap.get("mdmLevel3AccountId"));
				expr.append("] = '");
			}
			else
			{
				expr.append("[mdmLevel3AccountId]='");
			}
		}
		else if (DOMESTIC_MDMLEVEL.equals(mdmLevel)) {
			if(countFlag)
			{
				expr.append("[");
				expr.append(fieldMap.get("mdmLevel2AccountId"));
				expr.append("] = '");
			}
			else
			{
				expr.append("[mdmLevel2AccountId]='");
			}
		}
		else if (GLOBAL_MDMLEVEL.equals(mdmLevel)) {
			if(countFlag)
			{
				expr.append("[");
				expr.append(fieldMap.get("mdmLevel1AccountId"));
				expr.append("] = '");
			}
			else
			{
				expr.append("[mdmLevel1AccountId]='");
			}
		}
		else {
	        throw new IllegalArgumentException("mdmLevel must be one of 'Global, Domestic, Legal, Account'.  Value was " + mdmLevel);
		}
		
		expr.append(mdmId);
		expr.append("'");
		
		// Not applicable for Global Contact Domain Object
		if(accountFlag)
		{
			if(countFlag)
			{
				expr.append(" AND [");
				expr.append(fieldMap.get("accountTransFlag"));
				expr.append("] = 'Y'");
			}
			else
			{
				expr.append(" AND [accountTransFlag] = 'Y'");
			}
		}
		if (includeMdmLevel) {
			expr.append(" AND [LXK SW Agreement Account MDM Level] ='Siebel'");
		}
		
    	return expr.toString();
	}
	
	
	public static String buildmdmSearchExpressionLocationTree(String mdmId, String mdmLevel,
			Map<String, String> fieldMap, boolean accountFlag, boolean includeMdmLevel) {

		StringBuilder expr = new StringBuilder();
		
		boolean countFlag = isNotEmpty(fieldMap);
		
		if (SIEBEL_MDMLEVEL.equals(mdmLevel)) {
			expr.append("[Owner Account Id]= '");
		}
		else if (ACCOUNT_MDMLEVEL.equals(mdmLevel)) {
			expr.append("[LXM MDM Account #]= '");
		}
		else if (LEGAL_MDMLEVEL.equals(mdmLevel)) {
			expr.append("[LXM MDM Legal Entity ID #]= '");
		}
		else if (DOMESTIC_MDMLEVEL.equals(mdmLevel)) {
			expr.append("[LXK Account Domestic DUNS Number]= '");
		}
		else if (GLOBAL_MDMLEVEL.equals(mdmLevel)) {
			expr.append("[LXK Account Global DUNS Number]= '");
		}
		else {
	        throw new IllegalArgumentException("mdmLevel must be one of 'Global, Domestic, Legal, Account'.  Value was " + mdmLevel);
		}
		
		expr.append(mdmId);
		expr.append("' ");
		
		// Not applicable for Global Contact Domain Object
		
		return expr.toString();
	}
	/**
	 * Added check, so that empty fieldMap values don't cause NullPointerException.
	 * @param argSearchMap
	 * @param fieldMap
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String buildSearchCriteria(Map<String, Object> argSearchMap,
			Map<String,String> fieldMap)
	{
		StringBuilder expr = new StringBuilder();
		expr.append(" AND ");
		expr.append("(");
		int searchConditionCount = 0;
		
		for (Entry<String, Object> searchMap: argSearchMap.entrySet()) {
		   String key = fieldMap.get(searchMap.getKey());
		   Object value = searchMap.getValue();
		   if(value != null && isNotEmpty(key))
		   {
	    		List<String> listValue = null;
	    		if(value instanceof String) {
	    			listValue = new ArrayList<String>(); 
	    			listValue.add((String)value);
	    		} else if(value instanceof List) {
	    			listValue = (List<String>) value;
	    		}
	    		if(listValue != null) {
	    			for(String oneValue : listValue) {
	    				if(isBlank(oneValue)) {
	    					continue;
	    				}
	    				
	    				oneValue = trim(oneValue);
	    				
	    				if(searchConditionCount > 0)
	    				{
	    					expr.append(" OR ");
	    				}
	    				expr.append("[");
	    				expr.append(key);
	    				expr.append("] ~ LIKE '");
	    				expr.append(oneValue);
	    				expr.append("*'");
	    				searchConditionCount++;
	    			}
	    		}

		   }
		}
		expr.append(")");
		if(searchConditionCount == 0) {
			return "";
		} 
		return expr.toString();
	}
	
	/**
	 * Added for creating unified search expressions.
	 * @param argFilterMap
	 * @param fieldMap
	 * @param quantifierOnBothSides
	 * @param caseInsensitive
	 * @return
	 */
	public static String buildCriteria(Map<String, Object> argFilterMap, Map<String,String> fieldMap,
										boolean quantifierOnBothSides, boolean caseInsensitive)
	{
	    String s = buildSimpleCriteria(argFilterMap, fieldMap, quantifierOnBothSides, caseInsensitive);
	    return isEmpty(s) ? "" : String.format(" AND (%s)", s);
	}
	

	/**
	 * Added to handle request with filter criteria as Pending SP acknowledgement LEX:AIR00063609
	 * @param argFilterMap
	 * @param fieldMap
	 * @param quantifierOnBothSides
	 * @param caseInsensitive
	 * @return
	 */
	public static String buildCriteriaForRequestFilter(Map<String, Object> argFilterMap, Map<String,String> fieldMap,
										boolean quantifierOnBothSides, boolean caseInsensitive)
	{
		StringBuilder expr = new StringBuilder();
		expr.append(" AND (");
		int searchConditionCount = 0;
		
		for (Entry<String, Object> filterMap: argFilterMap.entrySet()) {
			String key = fieldMap.get(filterMap.getKey());
			String value = (String) filterMap.getValue();
			
			if(isNotBlank(key) && isNotBlank(value))
			{
			   value = value.trim();
			   
			   if(searchConditionCount > 0)
			   {
				   expr.append(" AND "); 
			   }
			   expr.append("[");
			   expr.append(key);
			   expr.append("] ");
			   
			   if (caseInsensitive) {
				   expr.append("~");
			   }
			   
			   expr.append("LIKE '");
			   if(value.equals("Pending SP Acknowledgement")){
              	 
              	 if (quantifierOnBothSides)
					   expr.append("*");
				   expr.append(value);
				   expr.append("*'");
				   expr.append(" OR");
				   expr.append("[" + key.toString() +"] ");
				   expr.append(" is null");   
				   }
               else {
			   if (quantifierOnBothSides) {
				   expr.append("*");
			   }
			   
			   expr.append(value);
			   expr.append("*'");
               }
			   searchConditionCount++;
               
		   }
	}
	expr.append(")");
		if(searchConditionCount == 0) {
			return "";
		} 
		return expr.toString();
	}

	
	
		
	/**
	 *  Originally copied from {@link #buildCriteria(Map, Map, boolean, boolean)}
	 */
	public static String buildSimpleCriteria(Map<String, Object> argFilterMap, Map<String,String> fieldMap,
										boolean quantifierOnBothSides, boolean caseInsensitive)
	{
		StringBuilder expr = new StringBuilder();
//		expr.append(" AND (");
		int searchConditionCount = 0;
		
		for (Entry<String, Object> filterMap: argFilterMap.entrySet()) {
			String key = fieldMap.get(filterMap.getKey());
			String value = (String) filterMap.getValue();
			
			if(isNotBlank(key) && isNotBlank(value))
			{
			   value = value.trim();
			   
			   if(searchConditionCount > 0)
			   {
				   expr.append(" AND "); 
			   }
			   expr.append("[");
			   expr.append(key);
			   expr.append("] ");
			   
			   if (caseInsensitive) {
				   expr.append("~");
			   }
			   
			   expr.append("LIKE '");
			   
			   if (quantifierOnBothSides) {
				   expr.append("*");
			   }
			   
			   expr.append(value);
			   expr.append("*'");
			   searchConditionCount++;
		   }
	}
//	expr.append(")");
		if(searchConditionCount == 0) {
			return "";
		} 
		return expr.toString();
	}
	
	/**
	 * Queries for parent chain using CHL Node ID.
	 * @param chlNodeId
	 * @param dataManager
	 * @return
	 */
	public static CHLDo getParentChainFromCHLNodeId(String chlNodeId, IDataManager dataManager)
	{
		CHLDo chl = (CHLDo) dataManager.queryById(CHLDo.class,	chlNodeId);
		return chl;
	}
	
	/**
	 * Used for building really simple search expression in order to query BC using mdm params.
	 * @param mdmId
	 * @param mdmLevel
	 * @return
	 */
	public static String buildSimpleMdmSearchExpression (String mdmId, String mdmLevel)
	{
		StringBuilder expr = new StringBuilder();
		
		if (SIEBEL_MDMLEVEL.equals(mdmLevel)) 
		{
			expr.append("[mdmLevel5AccountId]='");
		}
		else if (ACCOUNT_MDMLEVEL.equals(mdmLevel)) 
		{
			expr.append("[mdmLevel4AccountId]='");
		}
		else if (LEGAL_MDMLEVEL.equals(mdmLevel)) 
		{
			expr.append("[mdmLevel3AccountId]='");
		}
		else if (DOMESTIC_MDMLEVEL.equals(mdmLevel)) 
		{
			expr.append("[mdmLevel2AccountId]='");
		}
		else if (GLOBAL_MDMLEVEL.equals(mdmLevel)) 
		{
			expr.append("[mdmLevel1AccountId]='");
		}
		else
		{
			throw new SiebelCrmServiceException("Incorrect MDM Level '" + mdmLevel + "' passed to search expression");
		}
		
		expr.append(mdmId);
		expr.append("'");
		
		return expr.toString();
	}
	
	public static String buildSortString(Map<String, Object> argSortMap, Map<String, String> fieldMap) {

		StringBuilder expr = new StringBuilder();
		if(LangUtil.isNotEmpty(argSortMap)){
			boolean firstSortRecord = false;
			
			for (Entry<String, Object> sortMap : argSortMap.entrySet()) {
				if (firstSortRecord) {
					expr.append(",");
				}
				String key = fieldMap.get(sortMap.getKey());
				if(isNotEmpty(key)) {
					String value = (String) sortMap.getValue();
					expr.append(key);
					expr.append("(");
					expr.append(value);
					expr.append(")");
					firstSortRecord = true;
				}
				
			}
		}
		return expr.toString();
   // "Sequence(ASCENDING)" : Sort Criteria Syntax
	}
	
	
	// Siebel fieldnames are used because count business service call does not perform mapping.
	public static String buildChlNodeExpression(String chlNodeId, IDataManager dataManager, boolean serviceRequest, String assetType)
	{
                logger.debug("[IN] entering buildChlNodeExpression");
                logger.debug("[IN] inputparam chlNodeId=" + chlNodeId);
                logger.debug("[IN] inputparam dataManager=" + dataManager);
                logger.debug("[IN] inputparam serviceRequest=" + serviceRequest);
                logger.debug("[IN] inputparam assetType=" + assetType);
	    
		StringBuilder expr = new StringBuilder();
		String parentChainStr = "[LXK SW Covered Asset CHL Parent Chain]";
		String ownerAccountId = serviceRequest ? "[Account Id]": "[Owner Account Id]";  
		String agreementType = "[LXK SW Agreement Type]";
		
		CHLDo chl = getParentChainFromCHLNodeId(chlNodeId, dataManager);

		if(chl == null) {
			throw new SiebelCrmServiceException("Chl Domain Object is null");
		}
		
		String topLevelAccountId = chl.getTopLevelAccountId();
		if (isNotEmpty(topLevelAccountId))
		{
			expr.append(ownerAccountId);
			expr.append("='");
			expr.append(topLevelAccountId);
			expr.append("'");
			expr.append(" AND ");
		}

		String parentChain = chl.getParentChain();
		
		if(isEmpty(parentChain)) {
			throw new SiebelCrmServiceException("No Parent Chain found using CHL Node ID: " + chlNodeId);
		}
		
		
		expr.append("EXISTS (");
		expr.append(parentChainStr);
		expr.append(" LIKE '");
		expr.append(parentChain);
		expr.append("*'");

		if(assetType != null && "MPS".equalsIgnoreCase(assetType))
		{
			expr.append(" AND ");
			expr.append(agreementType);
			expr.append(" = 'MPS Agreement'");
		}
		else if(assetType != null && "CSS".equalsIgnoreCase(assetType))
		{
			expr.append(" AND ");
			expr.append(agreementType);
			expr.append(" = 'CSS Agreement'");
		}
		
		expr.append(")");

		return expr.toString();
		
	}
	
	// Siebel fieldnames are used because count business service call does not perform mapping.
	public static String buildChlNodeExpression(String chlNodeId, 
	                                             IDataManager dataManager,
	                                             String parentChainField,
	                                             String ownerAccountIdField,
	                                             String assetTypeExpr)
	{
//		CHLDo chl = getParentChainFromCHLNodeId(chlNodeId, dataManager);
		
	    return 
	        new ChlNodeExprBuilder(dataManager, chlNodeId)
	           .topLevelAccountIdExpr(ownerAccountIdField)
	           .and().existsBegin() 
	              .parentChainExpr(parentChainField)
	              .assetTypeExpr(" AND ", assetTypeExpr)
               .existsEnd().toString();
	}
	
	
	public static String buildAssetTypeExpression(String assetType, String agreementTypeField) {
	    String value = "";
		if("MPS".equalsIgnoreCase(assetType)) {
		    value = "MPS Agreement";
		} else if ("CSS".equalsIgnoreCase(assetType)) {
			value  = "CSS Agreement";
		}
		return isEmpty(value) ? "": format("[%s] = '%s'", agreementTypeField, value);
	    
	}
	
	
	
	public static String buildMdmSearchExpressionForMdmLevel(String mdmId, String mdmLevel,
			Map<String, String> fieldMap, boolean accountFlag, String includeMdmLevel) {

		StringBuilder searchExpression = new StringBuilder();
		searchExpression.append(buildmdmSearchExpression(mdmId, mdmLevel, fieldMap, accountFlag, false));

		if (isNotEmpty(includeMdmLevel)) {
			searchExpression.append(" AND [");
			searchExpression.append(includeMdmLevel);
			searchExpression.append("] = 'Siebel'");
		}

		return searchExpression.toString();
	}
	
	public static String buildMdmSearchExpressionForMdmLevelSR(String mdmId, String mdmLevel,
			Map<String, String> fieldMap, boolean accountFlag, String includeMdmLevel) {

		StringBuilder searchExpression = new StringBuilder();
		
		searchExpression.append(buildmdmSearchExpressionSR(mdmId, mdmLevel, fieldMap, accountFlag, false));

		if (isNotEmpty(includeMdmLevel)) {
			searchExpression.append(" AND [");
			searchExpression.append(includeMdmLevel);
			searchExpression.append("] = 'Siebel')");
		}

		return searchExpression.toString();
	}
	
	/**
	 * Please, pay attention that to getTotalCount we use businessObject and businessComponent names, not integrationObject and integrationComponents names.
	 * 
	 */
	public static int getTotalCount(String businessObject , String businessComponent, String searchExpression, SiebelBusinessServiceProxy businessServiceProxy ) {

		SiebelPropertySet input = new SiebelPropertySet();
		input.setProperty("Business Object", businessObject);
		input.setProperty("Business Component", businessComponent);
		input.setProperty("Search Expression", searchExpression);
		SiebelPropertySet output = businessServiceProxy.InvokeMethod("LXK Service Web Utilities","GetTotalCount", input);
	
		if (output == null) {
			return 0;
		}
		String count = output.getProperty("Count");
		if (isNotEmpty(count)) {
			return Integer.parseInt(count);
		} else {
			return 0;
		}
	}
	
	public static int getBCTotalCount(String businessObject , String businessComponent, String searchExpression, SiebelBusinessServiceProxy businessServiceProxy ) {
    	
		SiebelPropertySet input = new SiebelPropertySet();
    	input.setProperty("BusObj Name", businessObject);
    	input.setProperty("BC Name", businessComponent);
    	input.setProperty("BC SearchSpec", searchExpression);
    	SiebelPropertySet output = businessServiceProxy.InvokeMethod("CUT eSales Order Entry Toolkit Service",
    			"GetBCCount", input);

    	if (output == null) {
    		return 0;
    	}
    	String count = output.getProperty("Count");
    	if (isNotEmpty(count)) {
    		return Integer.parseInt(count);
    	} else {
    		return 0;
    	}
    }
	
	
	// Method added for testing purpose of Service in Siebel  
	public static boolean isRecordExistEntitlement(String businessObject , String businessComponent, String searchExpression, SiebelBusinessServiceProxy businessServiceProxy) {
		SiebelPropertySet input = new SiebelPropertySet();
		input.setProperty("Business Object", businessObject);
		input.setProperty("Business Component", businessComponent);
		input.setProperty("Search Expression", searchExpression);
		SiebelPropertySet output = businessServiceProxy.InvokeMethod("LXK Service Web Utilities1","IsRecExist", input);
		 if (output == null) {
			return false;
		}
		String count = output.getProperty("isRecExist");
		if (LangUtil.isNotEmpty(count) && "True".equalsIgnoreCase(count)) {
			return Boolean.parseBoolean(count);
		} else {
			return false;
		}
	}
	
	/**
	 * 
	 */
	public static boolean isRecordExist(String businessObject , String businessComponent, String searchExpression, SiebelBusinessServiceProxy businessServiceProxy) {
		SiebelPropertySet input = new SiebelPropertySet();
		input.setProperty("Business Object", businessObject);
		input.setProperty("Business Component", businessComponent);
		input.setProperty("Search Expression", searchExpression);
		SiebelPropertySet output = businessServiceProxy.InvokeMethod("LXK Service Web Utilities","IsRecExist", input);
	
		if (output == null) {
			return false;
		}
		String count = output.getProperty("isRecExist");
		if (LangUtil.isNotEmpty(count) && "True".equalsIgnoreCase(count)) {
			return Boolean.parseBoolean(count);
		} else {
			return false;
		}
	}

	public static QueryObject buildBasicQueryObject(QueryObject criteria, int increment, int startRecordNumber) {
		criteria.setExecutionMode(ExecutionMode.BiDirectional);
		criteria.setNumRows(increment);
		criteria.setStartRowIndex(startRecordNumber);
		return criteria;
		
	}
	
    public static void detachSession(Session session) {
        if (session != null) {
        	logger.debug("[IN] Detach");
            session.detach();
            
            logger.debug("[OUT] Detach");
        }
    }
    
    public static void releaseSession(Session session) {
        if (session != null) {
        	logger.debug("[IN] releaseSession");
            session.release();
            
            logger.debug("[OUT] releaseSession");
        }
    }

    public static Session acquireSession(AmindCrmSessionHandle crmSessionHandle) throws InterruptedException {
        Session session = crmSessionHandle.acquire();
        if (session == null)
        {
            throw new SiebelCrmServiceException(
                    "handle not initialized.  Call globalService.initCrmSessionHandle() before calling this method");
        }
        return session;
    }
    
    public static Session acquireMultipleSession(AmindCrmSessionHandle crmSessionHandle) throws InterruptedException {
        Session session = crmSessionHandle.acquireMultiple();
       
        if (session == null) {
            throw new SiebelCrmServiceException("handle not initialized.  Call globalService.initCrmSessionHandle() before calling this method");
        }
        
        return session;
    }
    
    public static void releaseAmindCrmSessionHandle(AmindCrmSessionHandle amindCrmSessionHandle) {
        if (amindCrmSessionHandle != null) {
            amindCrmSessionHandle.release();
        }
    }
    
    public static void releaseAmindCrmMultipleSessionHandle(AmindCrmSessionHandle amindCrmSessionHandle, Session session) {
        if (amindCrmSessionHandle != null) {
            amindCrmSessionHandle.releaseMultipleSession(session);
        }
    }

	public static ListOfValues newListOfValues(String value) {
		ListOfValues lov = new ListOfValues();
		lov.setValue(value);
		return lov;
	}
	

    public static QueryObject newQueryObject(Class<?> doClass, String searchExpression, int increment, int startRecordNumber) {
        QueryObject qo = new QueryObject(doClass, ActionEvent.QUERY);
        qo.addComponentSearchSpec(doClass, searchExpression); 
        return buildBasicQueryObject(qo, increment, startRecordNumber);
    }
    
    public static StringBuilder buildAgreementMdmSearchExpression(String mdmId, String mdmLevel,Map<String,String> fieldMap ) {
		StringBuilder builder = new StringBuilder("[");
		if (SIEBEL_MDMLEVEL.equals(mdmLevel)) {
			builder.append(fieldMap.get("agreementMdmLevel5AccountId"));
		} else if (ACCOUNT_MDMLEVEL.equals(mdmLevel)) {
			builder.append(fieldMap.get("agreementMdmLevel4AccountId"));
		} else if (LEGAL_MDMLEVEL.equals(mdmLevel)) {
			builder.append(fieldMap.get("agreementMdmLevel3AccountId"));
		} else if (DOMESTIC_MDMLEVEL.equals(mdmLevel)) {
			builder.append(fieldMap.get("agreementMdmLevel2AccountId"));
		} else if (GLOBAL_MDMLEVEL.equals(mdmLevel)) {
			builder.append(fieldMap.get("agreementMdmLevel1AccountId"));
		} else {
			throw new IllegalArgumentException(
					"mdmLevel must be one of 'Global, Domestic, Legal, Account'.  Value was " + mdmLevel);
		}
		builder.append("] = '" + mdmId + "'");

		return builder;
	}

	public static StringBuilder buildVendorMdmSearchExpression(String mdmId, String mdmLevel,Map<String,String> fieldMap) {
		StringBuilder builder = new StringBuilder("[");
		if (SIEBEL_MDMLEVEL.equals(mdmLevel)) {
			builder.append(fieldMap.get("vendorMdmLevel5AccountId"));
		} else if (ACCOUNT_MDMLEVEL.equals(mdmLevel)) {
			builder.append(fieldMap.get("vendorMdmLevel4AccountId"));
		} else if (LEGAL_MDMLEVEL.equals(mdmLevel)) {
			builder.append(fieldMap.get("vendorMdmLevel3AccountId"));
		} else if (DOMESTIC_MDMLEVEL.equals(mdmLevel)) {
			builder.append(fieldMap.get("vendorMdmLevel2AccountId"));
		} else if (GLOBAL_MDMLEVEL.equals(mdmLevel)) {
			builder.append(fieldMap.get("vendorMdmLevel1AccountId"));
		} else {
			throw new IllegalArgumentException(
					"mdmLevel must be one of 'Global, Domestic, Legal, Account'.  Value was " + mdmLevel);
		}
		builder.append("] = '" + mdmId + "'");

		return builder;
	}
	
	public static void populatePaymentMethod(Account account,
			String paymentMethod) {
		if(!StringUtils.isEmpty(paymentMethod)) {
			if (paymentMethod.equalsIgnoreCase("Credit Card")) {
				account.setCreditNumberFlag(true);
				return;
			} else if (paymentMethod.equalsIgnoreCase("Purchase Order")) {
				account.setPoNumberFlag(true);
				return;
			} else if (paymentMethod.equalsIgnoreCase("P Card")) {
				account.setCreditNumberFlag(true);
				account.setPoNumberFlag(true);
				return;
			}
		}
		
	}
	
	public static void populatePaymentMethod(ServiceRequest serviceRequest,
			String paymentMethod) {
		if(!StringUtils.isEmpty(paymentMethod)) {
			if (paymentMethod.equalsIgnoreCase("Credit Card")) {
				serviceRequest.setCreditNumberFlag(true);
				return;
			} else if (paymentMethod.equalsIgnoreCase("Purchase Order")) {
				serviceRequest.setPoNumberFlag(true);
				return;
			} else if (paymentMethod.equalsIgnoreCase("P Card")) {
				serviceRequest.setCreditNumberFlag(true);
				serviceRequest.setPoNumberFlag(true);
				return;
			}
		}
		
	}
	
	public static boolean listContainsItemOrItemIsBlank(List<String> listOfString, String item) {

		if (LangUtil.isBlank(item))
			return true;

		for (String existingItem : listOfString) {
			if (existingItem.equalsIgnoreCase(item))
				return true;
		}

		return false;
	}
	
	
	
	public static String buildMultipleFilterCriteria(String fieldName,
			List<String> allValues, boolean quantifierOnBothSides,
			boolean caseInsensitive) {

		if (isBlank(fieldName) || isEmpty(allValues)) {
			return "";
		}

		StringBuilder criteria = new StringBuilder();

		criteria.append(" AND (");

		criteria.append("[");
		criteria.append(fieldName);
		criteria.append("]  ");

		if (caseInsensitive) {
			criteria.append("~");
		}

		criteria.append("LIKE '");

		if (quantifierOnBothSides) {
			criteria.append("*");
		}

		criteria.append(allValues.get(0));
		criteria.append("*'");

		for (int i = 1; i < allValues.size(); i++) {

			criteria.append(" OR ");

			criteria.append("[");
			criteria.append(fieldName);
			criteria.append("]  ");

			if (caseInsensitive) {
				criteria.append("~");
			}

			criteria.append("LIKE '");

			if (quantifierOnBothSides) {
				criteria.append("*");
			}

			criteria.append(allValues.get(i));
			criteria.append("*'");

		}

		criteria.append(")");

		return criteria.toString();
	}
	
	
	public static <T> List<T> removeNulls(List<T> oldList) {
		
		if(oldList == null || oldList.size() == 0) {
			return oldList;
		}
		
		List<T> removeItems = new ArrayList<T>();
		for (T item : oldList) {
			if(item == null) {
				removeItems.add(item);
			}
		}
		
		oldList.removeAll(removeItems);
		
		return oldList;
		
		/*List<T> newList = null;
		
		try {
			Class<?> className = Class.forName(oldList.getClass().getName());
			newList = (List<T>) className.newInstance();

			for (T item : oldList) {
				if (item != null) {
					newList.add(item);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return newList;*/
		
	}
	
	public static void getSiebelLogNumber(Session session) throws InterruptedException
	{
		Session dataSession = session;
		ISiebelDataSource datasource = dataSession.getDataSource();
		SiebelDataBean dataBean = (SiebelDataBean) getField(datasource, "dataBean");
		try 
		{
			  String dataBeanhandle = dataBean.detach();
			  dataBean.attach(dataBeanhandle);
			  String[] logNumbers = dataBeanhandle.split("\\.");
			  String log = logNumbers[logNumbers.length - 2];
			  int number = Integer.parseInt(log,16);
			  logger.info("Siebel Log Number for Session:" + number);
			
		 } 
		
		catch (SiebelException e) 
		{
			  // TODO Auto-generated catch block
			  e.printStackTrace();
		 }
	}
	
	 public static Object getField(Object target, String fieldName)
	    {
	    	Field targetField = null;
	    	Object resultTarget = null;
	        try
	        {
	        	targetField   = target.getClass().getDeclaredField(fieldName);
	            targetField.setAccessible(true);
	            resultTarget = targetField.get(target);
	        }
	        catch (NoSuchFieldException e)
	        {
	            throw new IllegalArgumentException("No such field, " + fieldName);
	        } catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
	        	throw e;
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				throw new RuntimeException("getField failed", e);
			}
	        return resultTarget;
	    }
	    
	 
	public static void deleteTempFileAfterDownload(InputStream inputStream, File file) {
		try {
			inputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (file.exists()) {
			try {
				file.delete();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
}
