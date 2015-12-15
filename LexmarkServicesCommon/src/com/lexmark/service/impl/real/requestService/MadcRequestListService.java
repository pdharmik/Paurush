package com.lexmark.service.impl.real.requestService;

import static com.lexmark.service.impl.real.requestService.AmindMadcRequestConversionUtil.convertMadcRequestList;

import java.util.List;
import java.util.Map;

import com.amind.common.service.SiebelBusinessServiceProxy;
import com.amind.data.service.ActionEvent;
import com.amind.data.service.ExecutionMode;
import com.amind.data.service.QueryObject;
import com.amind.session.Session;
import com.lexmark.contract.RequestListContract;
import com.lexmark.domain.ServiceRequest;
import com.lexmark.service.impl.real.domain.MadcRequestDo;
import com.lexmark.service.impl.real.util.AmindServiceUtil;
import com.lexmark.util.LangUtil;

public class MadcRequestListService {
	private String searchExpression;
	private QueryObject criteria;
	private Session session;
	private Session chldSession;  // for chldNodeID
	private Session totalCountSession;
    private RequestListContract contract;
	private final String mdmId;
	private final String mdmLevel;
	private final String contactId;
	private final String chlNodeId;
	private final int startRecordNumber;
	private final int increment;
	private final Map<String, Object> filterCriteria;
	private final Map<String, Object> sortCriteria;
	private final Map<String, String> fieldMap;
	
	public MadcRequestListService(RequestListContract contract, Map<String, String> fieldMap) {
		if (contract == null) {
			throw new IllegalStateException("contract can not be null");
		}
		this.contract = contract;
		this.fieldMap = fieldMap;
		
		contactId = contract.getContactId();
		chlNodeId = contract.getChlNodeId();
		mdmId = contract.getMdmId();
		mdmLevel = contract.getMdmLevel();
		startRecordNumber = contract.getStartRecordNumber();
		increment = contract.getIncrement();
		sortCriteria = contract.getSortCriteria();
		filterCriteria = contract.getFilterCriteria();
	}
	
	public void checkRequiredFields() {
		if (!filterCriteria.containsKey("assetId")) {
			throw new IllegalArgumentException("assetId is required in filter criteria!");
		}
	}
	
	public void buildSearchExpression() {
		searchExpression = buildMadcRequestSearchExpression();
		criteria = buildMadcRequestCriteria();
	}
	
	private QueryObject buildMadcRequestCriteria() {
		QueryObject criteria = new QueryObject(MadcRequestDo.class, ActionEvent.QUERY);
		criteria.setExecutionMode(ExecutionMode.BiDirectional);
		criteria.setNumRows(increment);
		criteria.setStartRowIndex(startRecordNumber);
		criteria.addComponentSearchSpec(MadcRequestDo.class, searchExpression);

//		if (isNotEmpty(sortCriteria)) {
//			if(sortCriteria.containsKey("serviceRequestNumber")) {
//				Map<String, String> fieldMapaReplacement = new HashMap<String, String>();
//				fieldMapaReplacement.putAll(fieldMap);
//				fieldMapaReplacement.put("serviceRequestNumber", "Created");
//				criteria.setSortString(buildSortString(sortCriteria, Collections.unmodifiableMap(fieldMapaReplacement)));
//			}
//			else {
//				criteria.setSortString(buildSortString(sortCriteria, fieldMap));
//			}
//		}

		return criteria;
	}

	private String buildMadcRequestSearchExpression() {
		StringBuilder builder = new StringBuilder();
		
		if(filterCriteria.containsKey("assetId")) {
			builder.append("[" + fieldMap.get("assetId") + "] = '" + filterCriteria.get("assetId") + "'");
			filterCriteria.remove("assetId");
		}
		
		if (filterCriteria.containsKey("webStatus")) {
			String fieldName = fieldMap.get("webStatus");
			List<String> values = (List<String>) filterCriteria.get("webStatus");

			if(LangUtil.isNotEmpty(values) && values != null)
			{
				String webStatusFilterExpr = siebelFilterExpr(fieldName, values);
				if (LangUtil.isNotEmpty(webStatusFilterExpr)) {
     				builder.append(" AND ").append(webStatusFilterExpr);
				}
			}

			filterCriteria.remove("webStatus");
		}
		if("Move".equalsIgnoreCase(contract.getServiceType()))
		{
			builder.append("AND ([LXK C Covered Services]='HW BAU Install Move' or [LXK C Covered Services]='HW MADC Move')");
		}
		else if("Decommission".equalsIgnoreCase(contract.getServiceType())){
			builder.append("AND ([LXK C Covered Services]='HW BAU Install Decommission' or [LXK C Covered Services]='HW MADC Decommission')");
		}
		else if("Change".equalsIgnoreCase(contract.getServiceType())){
			builder.append(" AND ([LXK C Covered Services]='HW Install Change' or [LXK C Covered Services]='HW MADC Change' or [LXK C Covered Services]='HW BAU Install' or [LXK C Covered Services]='HW MADC Install' or [LXK C Covered Services]='HW BAU Install/Decommission' or [LXK C Covered Services]='HW MADC Install/Decommission')");
		}
		return builder.toString();
	}
	
	public static String siebelFilterExpr(String fieldName, List<String> values) {
		boolean firstFlag = true;
		StringBuilder webStatusSearchSpec = new StringBuilder();
		webStatusSearchSpec.append("(");
		for (String webStatusValue : values) {
			if (firstFlag) {
				if ("Submitted".equals(webStatusValue)) {
					webStatusSearchSpec.append("[");
					webStatusSearchSpec.append(fieldName);
					webStatusSearchSpec.append("] = '" + webStatusValue + "'");
				} else {
					webStatusSearchSpec.append("[" + fieldName + "] = '"
							+ webStatusValue + "'");			
				}
				firstFlag = false;
			} else {
				webStatusSearchSpec.append(" OR ");
				if ("Submitted".equals(webStatusValue)) {
					webStatusSearchSpec.append("[");
					webStatusSearchSpec.append(fieldName);
					webStatusSearchSpec.append("] = '" + webStatusValue + "'");
				}else{
					webStatusSearchSpec.append("[" + fieldName + "] = '"
							+ webStatusValue + "'");
				}

			}
		}
		webStatusSearchSpec.append(")");

		return webStatusSearchSpec.toString();
	}
	
	
	public int processTotalCount() {
		int totalCount = 0;
		SiebelBusinessServiceProxy proxy = getTotalCountSession().getSiebelBusServiceProxy();
		totalCount = AmindServiceUtil.getBCTotalCount("LXK LBS MADC Request", "LXK LBS MADC Request",
				searchExpression, proxy);
		return totalCount;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<ServiceRequest> queryAndGetMadcRequestList() {
		List<MadcRequestDo> madcRequestList = getSession().getDataManager().query(criteria);
		return convertMadcRequestList(madcRequestList);
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
	
	public Session getChldSession() {
		if (chldSession == null) {
			throw new IllegalStateException("chldSession has not set!");
		} else {
			return chldSession;
		}
	}

	public void setChldSession(Session chldSession) {
		this.chldSession = chldSession;
	}



	public Session getTotalCountSession() {
		if (totalCountSession == null) {
			throw new IllegalStateException("total count session has not been set!");
		} else {
			return totalCountSession;
		}
	}

	public void setTotalCountSession(Session totalCountSession) {
		if (totalCountSession == null) {
			throw new IllegalStateException("total count session can not be null!");
		} else {
			this.totalCountSession = totalCountSession;
		}
	}
	
}
