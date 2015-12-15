package com.lexmark.service.impl.real.requestService;

import static com.lexmark.service.impl.real.requestService.AmindRequestTypeConversionUtil.convertAccountContactTypeDetail;
import static com.lexmark.service.impl.real.requestService.AmindRequestTypeConversionUtil.convertRequestTypeDetail;
import static com.lexmark.service.impl.real.util.AmindServiceUtil.buildCriteria;
import static com.lexmark.service.impl.real.util.AmindServiceUtil.buildSearchCriteria;
import static com.lexmark.service.impl.real.util.AmindServiceUtil.buildSortString;
import static com.lexmark.util.LangUtil.isNotEmpty;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.amind.common.service.SiebelBusinessServiceProxy;
import com.amind.data.service.ActionEvent;
import com.amind.data.service.ExecutionMode;
import com.amind.data.service.QueryObject;
import com.amind.session.Session;
import com.lexmark.contract.RequestContract;
import com.lexmark.domain.ServiceRequest;
import com.lexmark.service.impl.real.AmindSiebelCrmService;
import com.lexmark.service.impl.real.domain.SupplyAccountContactDetailDo;
import com.lexmark.service.impl.real.domain.SupplyRequestDetailAttachmentDo;
import com.lexmark.service.impl.real.domain.SupplyRequestDetailDo;
import com.lexmark.util.LangUtil;
import com.siebel.data.SiebelPropertySet;

public class RequestTypeDetailService {
	private final RequestContract contract;
	private final Map<String, String> fieldMap;
	private String searchExpression;
	private String searchExpressionAccountContact;
	private QueryObject criteria;
	private QueryObject criteriaAccountContact;
	private Session session;
	//public static final Log logger = LogFactory.getLog(AmindSiebelCrmService.class);
	public static final Logger logger = LogManager.getLogger(AmindSiebelCrmService.class);
	private boolean madcServiceRequestFlag;

	public RequestTypeDetailService(RequestContract contract, Map<String, String> fieldMap) {
		if (contract == null) {
			throw new IllegalStateException("contract can not be null");
		}
		this.contract = contract;
		this.fieldMap = fieldMap;
		this.madcServiceRequestFlag = contract.isMadcServiceRequestFlag();
	}
	public RequestTypeDetailService(RequestContract contract) {
		if (contract == null) {
			throw new IllegalStateException("contract can not be null");
		}
		this.contract = contract;
		this.fieldMap = null;
	}
	
	public void checkRequiredFields() {
		String requestNumber = contract.getServiceRequestNumber();
		if (requestNumber == null) {
			throw new IllegalArgumentException("serviceRequestNumber is null");
		} else if (requestNumber.isEmpty()) {
			throw new IllegalArgumentException("serviceRequestNumber is empty");
		}
	}

	public void buildSearchExpression() {
		searchExpression = buildRequestTypeDetailSearchExpression();
		criteria = buildRequestTypeDetailCriteria();
	}
	
	public void buildSearchExpressionAccountContact() {
		searchExpressionAccountContact = buildRequestTypeDetailSearchExpression();
		criteriaAccountContact = buildRequestTypeDetailCriteriaAccountContact();
	}


	@SuppressWarnings("unchecked")
	public ServiceRequest queryAndGetRequestTypeDetail() {
		List<SupplyRequestDetailDo> detailList = getSession().getDataManager().query(criteria);
		if (LangUtil.isNotEmpty(detailList)) {
			String visibilityRole = contract.getVisibilityRole();
	        if ("Customer".equalsIgnoreCase(visibilityRole) 
	            || "Partner".equalsIgnoreCase(visibilityRole)) {
	            for (SupplyRequestDetailDo srd : LangUtil.notNull(detailList)) {
	            	retainAttachmentsByVisibilityRole(srd.getAttachments(), visibilityRole, "Both");
	            }
	        }
		}
		
		return convertRequestTypeDetail(first(detailList), madcServiceRequestFlag);
	}
	
	@SuppressWarnings("unchecked")
	public ServiceRequest queryAndGetAccountContactTypeDetail() {
		List<SupplyAccountContactDetailDo> detailList = getSession().getDataManager().query(criteriaAccountContact);
		return convertAccountContactTypeDetail(first(detailList),detailList);
	}

	private static <T> T first(Collection<T> coll) {
		if (LangUtil.isEmpty(coll)) {
	        return null;
	    }
	    
        return coll.iterator().next();
    }

    private String buildRequestTypeDetailSearchExpression() {
    	StringBuilder builder = new StringBuilder("[serviceRequestNumber] = '");
		builder.append(contract.getServiceRequestNumber());
		builder.append("'");

		Map<String, Object> searchCriteria = contract.getSearchCriteria();
		if (isNotEmpty(searchCriteria)) {
			builder.append(buildSearchCriteria(searchCriteria, fieldMap));
		}

		Map<String, Object> filterCriteria = contract.getFilterCriteria();
		if (isNotEmpty(filterCriteria)) {
			builder.append(buildCriteria(filterCriteria, fieldMap, true, true));
		}

		return builder.toString();
	}
    
   
	private QueryObject buildRequestTypeDetailCriteria() {
		QueryObject criteria = new QueryObject(SupplyRequestDetailDo.class, ActionEvent.QUERY);
		criteria.setExecutionMode(ExecutionMode.BiDirectional);
		criteria.setNumRows(contract.getIncrement());
		criteria.setStartRowIndex(contract.getStartRecordNumber());
		criteria.addComponentSearchSpec(SupplyRequestDetailDo.class, searchExpression);
		
		Map<String, Object> sortCriteria = contract.getSortCriteria();
		if (isNotEmpty(sortCriteria)) {
			criteria.setSortString(buildSortString(sortCriteria, fieldMap));
		}
		
		return criteria;
	}
	
	private QueryObject buildRequestTypeDetailCriteriaAccountContact() {
		
		QueryObject criteriaAccountContact = new QueryObject(SupplyAccountContactDetailDo.class, ActionEvent.QUERY);
		criteriaAccountContact.addComponentSearchSpec(SupplyAccountContactDetailDo.class, searchExpressionAccountContact);
		return criteriaAccountContact;
	}

	public Session getSession() {
		if (session == null) {
			throw new IllegalStateException("session has not set!");
		} else {
			return session;
		}
	}

	public void setSession(Session session) {
		if (session == null) {
			throw new IllegalArgumentException("session can not be null!");
		} else {
			this.session = session;
		}
	}
	
	/**
	 *  Retains only attachments with visibilityRole that is equal to either visibilityRole1 or visibilityRole2. 
	 */
	 private void retainAttachmentsByVisibilityRole(ArrayList<SupplyRequestDetailAttachmentDo> attachments, String visibilityRole1, String  visibilityRole2) {
	    if (LangUtil.isNotEmpty(attachments)) {
		    if (visibilityRole1 == null || visibilityRole2 == null) {
		        throw new IllegalArgumentException();
		    }
		    
		    for (Iterator<SupplyRequestDetailAttachmentDo> iter = attachments.iterator(); iter.hasNext();) {
		    	SupplyRequestDetailAttachmentDo attachment = iter.next();
		        String visibility = attachment.getVisibilityRole();
		        if (!(visibilityRole1.equalsIgnoreCase(visibility)
		            || visibilityRole2.equalsIgnoreCase(visibility))) {
		               iter.remove();
		        }
		    }
	    }
    }

	public SiebelPropertySet createChildSR() {
		SiebelPropertySet output = null;
		SiebelPropertySet input = new SiebelPropertySet();
		input.setProperty("SRNum", contract.getServiceRequestNumber());

		logger.debug("[IN] inputparam SR Number = " + input);

		SiebelBusinessServiceProxy sbsp = session.getSiebelBusServiceProxy();
		output = sbsp.InvokeMethod("LXK SD WM Siebel Connector BS", "WM_CreateChild_SR", input);

		logger.debug("[OUT] outparam Child SR Number = " + output);

		if (output == null) {
			return null;
		}

		return output;
	}
}
