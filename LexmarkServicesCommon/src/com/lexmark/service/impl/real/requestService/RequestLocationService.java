package com.lexmark.service.impl.real.requestService;

import static com.lexmark.util.LangUtil.isEmpty;
import static com.lexmark.util.LangUtil.isNotEmpty;
import static java.lang.String.format;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.amind.data.service.ActionEvent;
import com.amind.data.service.IDataManager;
import com.amind.data.service.QueryObject;
import com.amind.session.Session;
import com.lexmark.contract.LocationReportingHierarchyContract;
import com.lexmark.service.api.SiebelCrmServiceException;
import com.lexmark.service.impl.real.domain.CHLDo;
import com.lexmark.service.impl.real.domain.RequestLocationDo;
import com.lexmark.service.impl.real.util.AmindServiceUtil;

public class RequestLocationService {
    
	private final Session session;
	private final LocationReportingHierarchyContract contract;
	private final Map<String, String> fieldMap;
	private String searchExpression;
	private QueryObject criteria;
	
	public RequestLocationService(LocationReportingHierarchyContract contract, Map<String,String>fieldMap, Session session){
		this.contract = contract;
		this.fieldMap = fieldMap;
		this.session = session;
	}

	public void checkRequiredFields() {
		if (isEmpty(contract.getMdmId())) {
			throw new IllegalArgumentException("mdmId is null or empty!");
		} else if (isEmpty(contract.getMdmLevel())) {
			throw new IllegalArgumentException("mdmLevel is null or empty!");
		}
	}
	public void buildSearchExpression(){
		searchExpression =  buildRequestLocationSearchExpression();
		criteria = buildRequestLocationCriteria();
	}
	
	private String buildRequestLocationSearchExpression(){
		StringBuilder result = new StringBuilder();

		if (isNotEmpty(contract.getChlNodeId())) {
		    IDataManager dataManager = session.getDataManager();
		    CHLDo chl = AmindServiceUtil.getParentChainFromCHLNodeId(contract.getChlNodeId(), dataManager);
	        if(chl == null) {
	            throw new SiebelCrmServiceException("Chl Domain Object is null for CHL Node ID: " + contract.getChlNodeId());
	        }		    
	        if(isEmpty(chl.getParentChain())) {
	            throw new SiebelCrmServiceException("No Parent Chain found using CHL Node ID: " + contract.getChlNodeId());
	        }		    
			result.append(" [chlNodeId] LIKE '");
			result.append(chl.getParentChain());
			result.append("*'");
		} else {
			if(contract.isVendorFlag()) {
				result.append("((" + AmindServiceUtil.buildVendorMdmSearchExpression(contract.getMdmId(), contract.getMdmLevel(),fieldMap));
				result.append(String.format(" AND [%s] = 'Siebel'", fieldMap.get("vendorMdmLevel")));
			}else {
				result.append("((" + AmindServiceUtil.buildmdmSearchExpression(contract.getMdmId(), contract.getMdmLevel(),fieldMap, false, false));
				if("315000554".equals(contract.getMdmId()))
				{
					result.append("  AND (([Account Id] IS NOT NULL " );
						
				}
				else
				{
					result.append("  OR (( " + AmindServiceUtil.buildAgreementMdmSearchExpression(contract.getMdmId(), contract.getMdmLevel(),fieldMap));
				}
				//result.append("  OR (( " + AmindServiceUtil.buildAgreementMdmSearchExpression(contract.getMdmId(), contract.getMdmLevel(),fieldMap));
				result.append(" )))) "); 
			}
			result.append(buildDateSearchspec());

		}
		
		return result.toString();
	}
	
	private String buildDateSearchspec() {
		StringBuilder result = new StringBuilder();
		Map<String, Object> filterCriteria = copyOf(contract.getFilterCriteria()); 
		String startDate = (String) filterCriteria.remove("serviceRequest.startDate");
		String endDate = (String) filterCriteria.remove("serviceRequest.endDate");

		if (isNotEmpty(startDate)) {
			result.append(format(" AND ([%s] >= '%s')", fieldMap.get("serviceRequest.startDate"), startDate));
		}
		if (isNotEmpty(endDate)) {
			result.append(format(" AND ([%s] <= '%s')", fieldMap.get("serviceRequest.endDate"), endDate));
		}
		return result.toString();
	}
	
    private static Map<String, Object> copyOf(Map<String, Object> filterCriteria) {
        if (isEmpty(filterCriteria)) {
           return new HashMap<String, Object>();
        }
        return new HashMap(filterCriteria);
    }

	
	private QueryObject buildRequestLocationCriteria() {
		QueryObject criteria = new QueryObject(RequestLocationDo.class, ActionEvent.QUERY);
		criteria.addComponentSearchSpec(RequestLocationDo.class, searchExpression);
		return criteria;
	}

	@SuppressWarnings("unchecked")
	public List<RequestLocationDo> queryAndGetRequestLocations() {
		IDataManager dataManager = session.getDataManager();
		List<RequestLocationDo> result = dataManager.query(criteria);
		return result;
	}
}
