package com.lexmark.service.impl.real.requestService;

import static com.lexmark.service.impl.real.requestService.AmindRequestDataConversionUtil.convertServiceRequestDoToRequestList;
import static com.lexmark.service.impl.real.util.AmindServiceUtil.buildBasicQueryObject;
import static com.lexmark.service.impl.real.util.AmindServiceUtil.buildChlNodeExpression;
import static com.lexmark.service.impl.real.util.AmindServiceUtil.buildCriteria;
import static com.lexmark.service.impl.real.util.AmindServiceUtil.buildSearchCriteria;
import static com.lexmark.service.impl.real.util.AmindServiceUtil.buildSortString;
import static com.lexmark.service.impl.real.util.AmindServiceUtil.buildmdmSearchExpression;
import static com.lexmark.service.impl.real.util.AmindServiceUtil.getParentChainFromCHLNodeId;
import static com.lexmark.service.impl.real.util.AmindServiceUtil.getTotalCount;
import static com.lexmark.util.LangUtil.isEmpty;
import static com.lexmark.util.LangUtil.isNotEmpty;
import static com.lexmark.util.LangUtil.notNull;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.logging.log4j.LogManager;

import com.amind.common.service.SiebelBusinessServiceProxy;
import com.amind.data.service.ActionEvent;
import com.amind.data.service.IDataManager;
import com.amind.data.service.QueryObject;
import com.amind.session.Session;
import com.lexmark.contract.ServiceRequestListContract;
import com.lexmark.service.impl.real.domain.CHLDo;
import com.lexmark.service.impl.real.domain.ServiceRequest;
import org.apache.logging.log4j.Logger;

public class ServiceRequestListService {
	
	//private static final Log logger = LogFactory.getLog(ServiceRequestListService.class);
	private static final Logger logger = LogManager.getLogger(ServiceRequestListService.class);
	
	private final ServiceRequestListContract contract;
	private final String contactId;
	private final String mdmId;
	private final String mdmLevel;
	private final Map<String, Object> sortCriteria;

	private final Map<String, String> fieldMap;
	private final Session session;
	private IDataManager manager;
	private SiebelBusinessServiceProxy proxy;

	private String searchExpression;
	private int totalCount;

	public ServiceRequestListService(ServiceRequestListContract contract, Map<String, String> fieldMap,
			Session session) {
		if (contract == null) {
			throw new IllegalStateException("contract can not be null");
		}
		if (session == null) {
			throw new IllegalStateException("session can not be null");
		}
		this.contract = contract;
		this.contactId = contract.getContactID();
		this.mdmId = contract.getMdmID();
		this.mdmLevel = contract.getMdmLevel();
		this.sortCriteria = contract.getSortCriteria();

		this.fieldMap = fieldMap;
		this.session = session;
	}

	public List<com.lexmark.domain.ServiceRequest> queryAndGetResultList() {

		List<ServiceRequest> requestDoList;
		IDataManager dataManager = getManager();

		if (isNotEmpty(contactId)) {
			requestDoList = getServiceRequestListByContact(dataManager);
			if (isNotEmpty(requestDoList)) {
				totalCount = requestDoList.size();
			}
		} else {
			searchExpression = buildServiceRequestbyChlorMdm(dataManager);
			requestDoList = queryServiceRequestList(dataManager,searchExpression);
		}

		return convertServiceRequestDoToRequestList(requestDoList);
	}

	public int processTotalCount() {
		if (isEmpty(contactId)) {
			totalCount = getTotalCount("LXK SW Contracted Asset Service Request - Service Web",
					"LXK SW Contracted Asset Service Request - Service Web", searchExpression, getProxy());
		}
		return totalCount;
	}
	
	private List<ServiceRequest> getServiceRequestListByContact(IDataManager dataManager) {

		List<ServiceRequest> serviceRequestList;

		StringBuilder serviceSearchExpression = buildServiceRequestSearchExpression(dataManager);
		String contactSearchExpression = buildContactSearchExpression(contactId, serviceSearchExpression);

		serviceRequestList = queryServiceRequestListByContact(dataManager, contactSearchExpression,
				"First query for contactId");

		if (isNotEmpty(serviceRequestList)) {
			contactSearchExpression = buildContactSearchExpression(contactId, contactId,
					serviceSearchExpression);
			List<ServiceRequest> serviceContacts = queryServiceRequestListByContact(dataManager,
					contactSearchExpression, "Second query for contactId");

			if (isNotEmpty(serviceContacts)) {
				serviceRequestList.addAll(serviceContacts);
			}

			contactSearchExpression = buildContactSearchExpression(contactId, contactId, contactId,
					serviceSearchExpression);
			serviceContacts = queryServiceRequestListByContact(dataManager,
					contactSearchExpression, "Third query for contactId");

			if (isNotEmpty(serviceContacts)) {
				serviceRequestList.addAll(serviceContacts);
			}

			if (isNotEmpty(sortCriteria)) {
				Comparator<ServiceRequest> comparator = new AmindServiceRequestComparator(sortCriteria);
				Collections.sort(serviceRequestList, comparator);
			}
		}
		return notNull(serviceRequestList);
	}
	
	
	private String buildContactSearchExpression(String contactFavId, CharSequence searchExpression) {
		StringBuilder builder = new StringBuilder("[contactId] ='");
		builder.append(contactFavId);
		builder.append("'");
		builder.append(searchExpression);

		return builder.toString();
	}

	private String buildContactSearchExpression(String primaryId, String contactFavId,
			CharSequence searchExpression) {

		StringBuilder builder = new StringBuilder("[contactPrimaryId] ='");
		builder.append(primaryId);
		builder.append("' AND [contactId] <> '");
		builder.append(contactFavId);
		builder.append("'");
		builder.append(searchExpression);

		return builder.toString();
	}

	private String buildContactSearchExpression(String alternateId, String primaryId,
			String contactFavId, CharSequence searchExpression) {

		StringBuilder builder = new StringBuilder("[contactAlternateId] = '");
		builder.append(alternateId);
		builder.append("' AND [contactId] <>'");
		builder.append(contactFavId);
		builder.append("' AND [contactPrimaryId] <> '");
		builder.append(primaryId);
		builder.append("'");
		builder.append(searchExpression);

		return builder.toString();
	}
	
	@SuppressWarnings("unchecked")
	private List<ServiceRequest> queryServiceRequestListByContact(IDataManager dataManager, String contactSearchExpression, String transactionMessage) {

		List<ServiceRequest> result;

		QueryObject criteria = new QueryObject(ServiceRequest.class, ActionEvent.QUERY);
		criteria.addComponentSearchSpec(ServiceRequest.class, contactSearchExpression);
		if (contract.isNewQueryIndicator()) {
			result = dataManager.query(criteria);
		} else {
			result = dataManager.queryNext(criteria);
		}
		return notNull(result);
	}
	
	
	private QueryObject buildServiceRequestCriteria(String searchExpression, Class<?> doClass) {

		QueryObject criteria = new QueryObject(doClass, ActionEvent.QUERY);
		buildBasicQueryObject(criteria, contract.getIncrement(), contract.getStartRecordNumber());
		criteria.addComponentSearchSpec(doClass, searchExpression);
		if (isNotEmpty(sortCriteria)) {
			criteria.setSortString(buildSortString(sortCriteria, fieldMap));
		}
		return criteria;
	}

	private String buildServiceRequestbyChlorMdm(IDataManager dataManager) {

		String chlNodeId = contract.getChlNodeId();
		String assetType = contract.getAssetType();

		StringBuilder builder = new StringBuilder();

		if (isNotEmpty(chlNodeId)) {
			logger.debug("ChlNodeId specified, performing search using value: " + chlNodeId);
			builder.append(buildChlNodeExpression(chlNodeId, dataManager, true, assetType));
		} else {
			builder.append("EXISTS (");
			builder.append(buildmdmSearchExpression(mdmId, mdmLevel, fieldMap, false, true));

			if (isNotEmpty(assetType)) {
				if (assetType.equalsIgnoreCase("MPS")) {
					builder.append(" AND [LXK SW Agreement Type] = 'MPS Agreement'");
				} else if (assetType.equalsIgnoreCase("CSS")) {
					builder.append(" AND [LXK SW Agreement Type] = 'CSS Agreement'");
				}
			}

			builder.append(")");
		}
		builder.append(buildServiceRequestSearchExpression(dataManager));
		
		return builder.toString();
	}

	@SuppressWarnings("unchecked")
	private List<ServiceRequest> queryServiceRequestList(IDataManager dataManager, String searchExpression) {
		
		QueryObject criteria = buildServiceRequestCriteria(searchExpression, ServiceRequest.class);

		List<ServiceRequest> serviceListRequest;
		if (contract.isNewQueryIndicator()) {
			serviceListRequest = dataManager.query(criteria);
		} else {
			serviceListRequest = dataManager.queryNext(criteria);
		}

		return notNull(serviceListRequest);
	}
	
	
	private StringBuilder buildServiceRequestSearchExpression(IDataManager dataManager) {

		StringBuilder builder = new StringBuilder();
		builder.append(buildStatusSearchExpression(contract.getStatus()));

		Map<String, Object> searchCriteria = contract.getSearchCriteria();
		if (isNotEmpty(searchCriteria)) {
			builder.append(buildServiceRequestSearchCriteria(searchCriteria, dataManager));
		}

		Map<String, Object> filterCriteria = contract.getFilterCriteria();
		if (isNotEmpty(filterCriteria)) {
			builder.append(buildCriteria(filterCriteria, fieldMap, true, true));
		}

		return builder;
	}

	private StringBuilder buildServiceRequestSearchCriteria(Map<String, Object> searchCriteria,
			IDataManager dataManager) {

		StringBuilder builder = new StringBuilder();

		if (searchCriteria.containsKey("chlNodeId")) {
			String chlNodeId = (String) searchCriteria.get("chlNodeId");
			builder.append(buildSearchCriteria(searchCriteria, fieldMap));

			if (isNotEmpty(chlNodeId)) {
				builder.deleteCharAt(builder.length() - 1);
				CHLDo chl = getParentChainFromCHLNodeId(chlNodeId, dataManager);

				if (chl != null) {
					builder.append(" OR EXISTS ([LXK SW Covered Asset CHL Parent Chain] LIKE '");
					builder.append(chl.getParentChain());
					builder.append("*' ))");
				}
			}
		}
		return builder;
	}

	private StringBuilder buildStatusSearchExpression(String status) {
		StringBuilder builder = new StringBuilder();

		String statusStr = "[LXK SW Web SR Status]";
		String statusDateStr = "[LXK SW Web SR Status Date]";

		Calendar currentDate = GregorianCalendar.getInstance();
		currentDate.add(Calendar.DATE, -30);
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		String expiredDate = dateFormat.format(currentDate.getTime());

		if ("InProcess".equalsIgnoreCase(status)) {
			builder.append(" AND ");
			builder.append(statusStr);
			builder.append("= 'Inprocess'");
		} else {
			builder.append(" AND (");
			builder.append(statusDateStr);
			builder.append(" > ");
			builder.append(expiredDate);
			builder.append(") AND (");
			builder.append(statusStr);
			builder.append("= 'Inprocess' OR ");
			builder.append(statusStr);
			builder.append("= 'Submitted' OR ");
			builder.append(statusStr);
			builder.append("= 'Completed')");
		}

		return builder;
	}
	

	private IDataManager getManager() {
		if (manager == null) {
			manager = session.getDataManager();
		}
		return manager;
	}

	private SiebelBusinessServiceProxy getProxy() {
		if (proxy == null) {
			proxy = session.getSiebelBusServiceProxy();
		}
		return proxy;
	}
}
