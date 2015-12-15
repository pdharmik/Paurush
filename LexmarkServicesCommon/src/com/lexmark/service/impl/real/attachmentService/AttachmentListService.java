package com.lexmark.service.impl.real.attachmentService;

import static com.lexmark.service.impl.real.attachmentService.AmindAttachmentConversionUtil.convertAttachmentDoToAttachmentList;
import static com.lexmark.service.impl.real.attachmentService.AmindAttachmentServiceUtil.checkIdentifier;
import static com.lexmark.service.impl.real.util.AmindServiceUtil.buildSortString;
import static com.lexmark.service.impl.real.util.AmindServiceUtil.getTotalCount;
import static com.lexmark.util.LangUtil.isNotEmpty;
import static com.lexmark.util.LangUtil.notNull;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.amind.common.service.SiebelBusinessServiceProxy;
import com.amind.data.service.ActionEvent;
import com.amind.data.service.IDataManager;
import com.amind.data.service.QueryObject;
import com.amind.session.Session;
import com.lexmark.contract.AttachmentContract;
import com.lexmark.domain.Attachment;
import com.lexmark.service.impl.real.domain.RequestAttachment;
import com.lexmark.service.impl.real.domain.ServiceRequestAttachmentDo;
import com.lexmark.service.impl.real.util.AmindServiceUtil;
import com.lexmark.util.LangUtil;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;


public class AttachmentListService {
	
	//protected static final Log logger = LogFactory.getLog(AttachmentListService.class);
	protected static final Logger logger = LogManager.getLogger(AttachmentListService.class);
	
	private final String identifier;
	private String searchExpression;
	private Session session;
	private AttachmentContract contract;
	private final Map<String, Object> providedFilterCriteria;
	private final Map<String, Object> providedSortCriteria;
	private  Map<String, String> filterMap;
	private  Map<String, String> sortMap;
	private QueryObject criteria;
	
	public AttachmentListService(AttachmentContract contract) {
		if(contract == null) {
			throw new IllegalStateException("contract can not be null");
		}
		identifier = contract.getIdentifier();
		this.contract = contract;
		
		this.providedFilterCriteria = contract.getFilterCriteria();
		this.providedSortCriteria = contract.getSortCriteria();
	}

	public void checkRequiredFields() {
		checkIdentifier(identifier);
	}

	public void buildSearchExpression() {
		StringBuilder builder = new StringBuilder("[parentId] = '");
		builder.append(identifier);
		builder.append("'");
		searchExpression = builder.toString();
	}

	@SuppressWarnings("unchecked")
	public List<Attachment> queryAndGetResultList() {
		IDataManager dataManager = getSession().getDataManager();
		List<ServiceRequestAttachmentDo> attachmentList = notNull((List<ServiceRequestAttachmentDo>) dataManager
				.queryBySearchExpression(ServiceRequestAttachmentDo.class, searchExpression));
		
		return convertAttachmentDoToAttachmentList(attachmentList);
	}
	
	
	
	public void buildSearchExpressionRequestAttachment() {
		StringBuilder builder = new StringBuilder("[LXK MPS LASP Id] = '");
		builder.append(identifier);
		builder.append("'");
		
		if(LangUtil.isNotEmpty(filterMap)){
			builder.append(buildFilterCriteria());
		}
		
		searchExpression = builder.toString();
		
		buildSearchCriteriaRequestAttachments();
	}
	
	private void buildSearchCriteriaRequestAttachments() {
		
		criteria = new QueryObject(RequestAttachment.class, ActionEvent.QUERY);
		AmindServiceUtil.buildBasicQueryObject(criteria, contract.getIncrement(), contract.getStartRecordNumber());
		criteria.addComponentSearchSpec(RequestAttachment.class, searchExpression);
		
		if (isNotEmpty(providedSortCriteria)) {
			criteria.setSortString(buildSortString(providedSortCriteria, sortMap));
		}

	}


	@SuppressWarnings("unchecked")
	public List<Attachment> queryAndGetResultRequestAttachment() {
		IDataManager dataManager = getSession().getDataManager();
		List<RequestAttachment> requestAttachmentList = new ArrayList<RequestAttachment>();
		
		if(contract.isNewQueryIndicator()) {
			requestAttachmentList = notNull((List<RequestAttachment>) dataManager.query(criteria));
		}
		else {
			requestAttachmentList = notNull((List<RequestAttachment>) dataManager.queryNext(criteria));
		}

		return AmindAttachmentConversionUtil.convertRequestAttachmentToAttachmentList(requestAttachmentList);
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
	
	

	private String buildFilterCriteria() {
		
		StringBuilder filterCriteria = new StringBuilder();
		
		if(providedFilterCriteria.containsKey("requestNumber")) {
			String fieldName = filterMap.get("requestNumber");
			String value = (String) providedFilterCriteria.get("requestNumber");
			
			filterCriteria.append(" AND [");
			filterCriteria.append(fieldName);
			filterCriteria.append("] LIKE '*");
			filterCriteria.append(value);
			filterCriteria.append("*'");
		}
		
		if(providedFilterCriteria.containsKey("status")) {
			String fieldName = filterMap.get("status");
			String value = (String) providedFilterCriteria.get("status");
			
			if("Submitted".equalsIgnoreCase(value)) {
				filterCriteria.append(" AND [");
				filterCriteria.append(fieldName);
				filterCriteria.append("] IS NULL");
			}
			else {
				filterCriteria.append(" AND [");
				filterCriteria.append(fieldName);
				filterCriteria.append("] ~LIKE '*");
				filterCriteria.append(value);
				filterCriteria.append("*'");
			}
		}
		
		if(providedFilterCriteria.containsKey("fileName")) {
			String fieldName = filterMap.get("fileName");
			String value = (String) providedFilterCriteria.get("fileName");
			
			filterCriteria.append(" AND [");
			filterCriteria.append(fieldName);
			filterCriteria.append("] ~LIKE '*");
			filterCriteria.append(value);
			filterCriteria.append("*'");
		}
		
		if(providedFilterCriteria.containsKey("type")) {
			String fieldName = filterMap.get("type");
			String value = (String) providedFilterCriteria.get("type");
			
			filterCriteria.append(" AND [");
			filterCriteria.append(fieldName);
			filterCriteria.append("] ~LIKE '*");
			filterCriteria.append(value);
			filterCriteria.append("*'");
		}
		
		return filterCriteria.toString();
	}

	private String convertDateToString(String date) {
		
		Format formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		String stringDate = formatter.format(date);
		
		return stringDate;
	}
	
	public int processTotalCount() {
		int totalCount = 0;
		SiebelBusinessServiceProxy proxy = getSession().getSiebelBusServiceProxy();
		totalCount = getTotalCount("LXK MPS Parter Portal Service Request Attachment", "LXK MPS Parter Portal Service Request Attachment",
				searchExpression, proxy);
		return totalCount;
	}

	public Map<String, String> getFilterMap() {
		return filterMap;
	}

	public void setFilterMap(Map<String, String> filterMap) {
		this.filterMap = filterMap;
	}

	public Map<String, String> getSortMap() {
		return sortMap;
	}

	public void setSortMap(Map<String, String> sortMap) {
		this.sortMap = sortMap;
	}
	
	
}
