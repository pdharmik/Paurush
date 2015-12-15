package com.lexmark.service.impl.real.partnerOrderService;


import static com.lexmark.util.LangUtil.isEmpty;
import static com.lexmark.util.LangUtil.isNotEmpty;
import static java.lang.String.format;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.lexmark.contract.source.OrderListContract;
import com.lexmark.service.impl.real.util.AmindServiceUtil;
import com.lexmark.util.LangUtil;
/**
 *
 * @author vpetruchok
 * @version 1.0, 2012-05-04
 */
public class OrderServiceUtil { 

    private OrderServiceUtil () {
    }

    public static String buildOrderListSearchExpression(OrderListContract contract, FieldCriteria fieldCriteria)
    {
        Map<String, String> allFieldMap = fieldCriteria.allFieldMap() ;
        Map<String, String> singlevaluedFieldMap = fieldCriteria.fieldMap();
        Map<String, String> multivaluedFieldMap = fieldCriteria.multiValuedFieldMap();
        
        StringBuilder expr = new StringBuilder();
        expr.append(" EXISTS (");
        expr.append(mdmSearchExpr(contract, allFieldMap));
//        expr.append(mpsStatusExpr());   Commented by sankha for LEX:AIR00072765
        expr.append(conditionalmpsStatusExpr(contract)); //Added by sankha for LEX:AIR00072765
     
        // Filter Criteria
        Map<String, Object> multivaluedCriteria =  filterByCommonKeys(contract.getFilterCriteria(), multivaluedFieldMap);
        if (isNotEmpty(multivaluedCriteria)) {
            String filterStatus = AmindServiceUtil.buildSimpleCriteria(multivaluedCriteria, multivaluedFieldMap, true, true);
            expr.append(" AND " + filterStatus + "");
        }
        expr.append(contractStatusExprWithExists(contract, allFieldMap));
        expr.append(filterSingleValuedCriteriaExpr(contract, singlevaluedFieldMap));
        expr.append(statusExpr(contract));
        expr.append(mpsRequestExpr());
        expr.append(" AND [Order Type] LIKE 'Consumable*'");
        return expr.toString();
    }
    
    public static String buildOrderItemSearchExpression(OrderListContract contract, Map<String,String> orderItemFieldMap)
    {
        StringBuilder expr = new StringBuilder();
        expr.append(AmindServiceUtil.buildmdmSearchExpression(contract.getMdmId(),
        		contract.getMdmLevel(),
        		orderItemFieldMap, false, false));
      
        Map<String,Object> contractFilterMap = contract.getFilterCriteria();
       if(LangUtil.isNotEmpty(contractFilterMap))
        {
        	String statusValue = (String) contractFilterMap.get("status");
        	if(LangUtil.isNotBlank(statusValue)){
        		expr.append( " AND [" + orderItemFieldMap.get("status") + "] = '" + statusValue + "'" );
        	}
        }
        return expr.toString();
    }
    private static String mpsRequestExpr() {
    	return " AND [LXK MPS SR Flag] ='MPS'";
    }

    /* Added by sankha for LEX:AIR00072765 start */
    private static String conditionalmpsStatusExpr(OrderListContract contract){
    	
    	StringBuilder searchSpec = new StringBuilder();
    	
    	if("Open".equalsIgnoreCase(contract.getStatus())){
    		
    		searchSpec.append(" AND ([LXK MPS Status] = 'Routed' OR [LXK MPS Status] = 'Order Accepted' " +
    		" OR [LXK MPS Status] = 'Shipped'" +
    		" OR [LXK MPS Status] = 'Back Ordered'" +
    		" OR [LXK MPS Status] = 'Ship Pending'" +
    		" OR [LXK MPS Status] = 'In Transit') ");
    		return searchSpec.toString();
    	}
    	else if("Closed".equalsIgnoreCase(contract.getStatus())){
//    		searchSpec.append("AND ([LXK MPS Status] = 'Routed' OR [LXK MPS Status] = 'Order Accepted' " +
//    		" OR [LXK MPS Status] = 'Shipped' OR [LXK MPS Status] = 'Delivered' " +
//    		" OR [LXK MPS Status] = 'Back Ordered'" +
//    		" OR [LXK MPS Status] = 'Cancelled' OR [LXK MPS Status] = 'Ship Pending')");
    		searchSpec.append(" AND [LXK MPS Status] = 'Delivered' ");
    		return searchSpec.toString();
    	}
    	else{
    		
    		searchSpec.append(" AND ([LXK MPS Status] = 'Routed' OR [LXK MPS Status] = 'Order Accepted' " +
    	    		" OR [LXK MPS Status] = 'Shipped' OR [LXK MPS Status] = 'Delivered' " +
    	    		" OR [LXK MPS Status] = 'Back Ordered'" +
    	    		" OR [LXK MPS Status] = 'Ship Pending' OR [LXK MPS Status] = 'In Transit') ");
    		return searchSpec.toString();
    	}
    }
    /* Added by sankha for LEX:AIR00072765 end */
    private static String statusExpr(OrderListContract contract) {
    	StringBuilder searchSpec = new StringBuilder();
//    	searchSpec.append("AND ([Status] <> 'Ready for Order' OR " +
//        		"[Status] <> 'Pending' OR [Status] <> 'Assignment Failed'");
//    	
    	
		if ("Show All".equalsIgnoreCase(contract.getStatus())) {
			searchSpec
					.append("AND ([Status] = 'Request for Shipment' OR "
							+ "[Status] = 'Back Ordered' OR [Status] = 'Order Accepted' OR [Status] = 'Shipped' OR [Status] = 'In Transit' OR [Status] = 'Ship Pending' OR [Status] = 'Delivered' ");
			if (contract.isMassUploadRequest()) {
				searchSpec.append(" OR [Status]<>'Complete'");
			} else {
				searchSpec.append(" OR [Status] = 'Complete'");
			}
		} else if ("Open".equalsIgnoreCase(contract.getStatus())) {
			searchSpec
					.append("AND ([Status] = 'Request for Shipment' OR "
							+ "[Status] = 'Back Ordered' OR [Status] = 'Order Accepted' OR [Status] = 'Shipped' OR [Status] = 'In Transit' OR [Status] = 'Ship Pending'");

		}

		else if ("Closed".equalsIgnoreCase(contract.getStatus())) {
			searchSpec
					.append(" AND ([Status]='Delivered' OR [Status] ='Complete'");
		}
    	searchSpec.append(")");
    	return searchSpec.toString();

//    	return " AND ([Status] <> 'Ready for Order' OR " +
//        		"[Status] <> 'Pending' OR [Status] <> 'Assignment Failed')";
        
		
        //change here or 12103
    }

    static boolean isSearchByMdm(OrderListContract contract) {
        return isNotEmpty(contract.getMdmId()) && isNotEmpty(contract.getMdmLevel());
    }

    static String mdmSearchExpr(OrderListContract contract, Map<String, String> fieldMap) {
        StringBuilder searchspec = new StringBuilder();
        searchspec.append(AmindServiceUtil.buildMdmSearchExpressionForMdmLevel(contract.getMdmId(), contract.getMdmLevel(), fieldMap, false, "LXK MPS Account Level")); 
   	    return searchspec.toString();
    }

    // Now the search exp is same for all status. Still keeping the status conditions as is for future reference/use. 
    static String contractStatusExprWithExists(OrderListContract contract, Map<String, String> fieldMap){
    	 String statusField = fieldMap.get("readyForBilling");  
    	 String statusExpr = ""; 
//   		statusExpr = "AND [LXK MPS Ready for Billing] IS NULL)";
    	 if ("Open".equalsIgnoreCase(contract.getStatus())) {
    		 statusExpr = "AND " + contractStatusExpr("Open",statusField);
    	 }else if ("Closed".equalsIgnoreCase(contract.getStatus())) {
    		 statusExpr = "AND " + contractStatusExpr("Closed",statusField);
    	 }
    	 else
         {
         	statusExpr = ")";
         }
    	 return statusExpr;
    }
    
    static String contractStatusExpr(String status, String statusField) {
        String statusExpr = ""; 
        if ("Open".equalsIgnoreCase(status)) {
            statusExpr = "[siebel status] IS NULL)".replace("siebel status",  statusField);
        } else if ("Closed".equalsIgnoreCase(status)) {
            statusExpr = "[siebel status] IS NOT NULL) ".replace("siebel status",  statusField); 
        } 
        return statusExpr;
    }

    static String filterSingleValuedCriteriaExpr(OrderListContract contract, Map<String, String> singlevaluedFieldMap) {
        if (isEmpty(contract.getFilterCriteria())) {
            return "";
        }
        
        Map<String, Object> filterCriteria = copyOf(contract.getFilterCriteria()); 
        String startDate = (String) filterCriteria.remove("order.startDate");
        String endDate = (String) filterCriteria.remove("order.endDate");
        
        Map<String, Object> singlevaluedCriteria = filterByCommonKeys(filterCriteria, singlevaluedFieldMap);
  

        StringBuilder sb = new StringBuilder();
        if (isNotEmpty(startDate)) {
            sb.append(format(" AND ([LXK MPS Created] >= '%s')", startDate));
        }
        if (isNotEmpty(endDate)) {
            sb.append(format(" AND ([LXK MPS Created] <= '%s')", endDate));
        }
  
          if (isNotEmpty(singlevaluedCriteria)) {
            String s = AmindServiceUtil.buildSimpleCriteria(singlevaluedCriteria, singlevaluedFieldMap, true, true);
            if (isNotEmpty(s)) {
                sb.append(format(" AND (%s) ", s));
            }
        }

        return  sb.toString();
    }

    
    private static Map<String, Object> copyOf(Map<String, Object> filterCriteria) {
        if (isEmpty(filterCriteria)) {
           return new HashMap<String, Object>();
        }
        return new HashMap(filterCriteria);
    }

    static Map<String, Object> filterByCommonKeys(Map<String, Object> filterCriteria, Map<String, String> fieldMap) {
        if (isEmpty(filterCriteria) 
              || isEmpty(fieldMap)) { 
          return null;
        }
        Map<String, Object> m =  new HashMap<String, Object>(filterCriteria);
        Set<String> keySet = m.keySet();
        keySet.retainAll(fieldMap.keySet());
        return m;
    }
}
