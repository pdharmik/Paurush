package com.lexmark.service.impl.real.delegate;

import static com.lexmark.service.impl.real.requestService.AmindRequestDataConversionUtil.convertCatalogAccountToAccountList;
import static com.lexmark.service.impl.real.util.AmindServiceUtil.buildmdmSearchExpression;
import static com.lexmark.util.LangUtil.isEmpty;
import static com.lexmark.util.LangUtil.notNull;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.logging.log4j.LogManager;

import com.amind.common.service.SiebelBusinessServiceProxy;
import com.amind.data.service.ActionEvent;
import com.amind.data.service.IDataManager;
import com.amind.data.service.QueryObject;
import com.amind.session.Session;
import com.lexmark.contract.SiebelAccountListContract;
import com.lexmark.domain.Account;
import com.lexmark.result.AccountFlagResult;
import com.lexmark.service.impl.real.domain.AgreementRelatedAccountsDO;
import com.lexmark.service.impl.real.domain.CatalogEntitlementAccountDo;
import com.lexmark.service.impl.real.util.AmindServiceUtil;
import com.lexmark.util.LangUtil;
import org.apache.logging.log4j.Logger;


public class AccountFlagService {

	//protected static final Log logger = LogFactory
	//		.getLog(SiebelAgreementListService.class);
	protected static final Logger logger = LogManager
			.getLogger(SiebelAgreementListService.class);
	private static final Map<String, String> FIELD_MAP = populateFieldMap();
	private static final Map<String, String> FIELD_MAPBOBC = populateFieldMapBOBC();
	
	private static final Map<String, String> CHILD_FIELD_MAP = populateChildFieldMap();

	private final String mdmId;
	private final String mdmLevel;
	private StringBuilder searchExpression = new StringBuilder();
	private StringBuilder searchExpressionBOBC = new StringBuilder();
	
	private QueryObject criteria;
	private Session session;
	private SiebelAccountListContract contract;
	private String contractNumber;
	private boolean partnerPortal;

	public AccountFlagService(SiebelAccountListContract contract) {
		if (contract == null) {
			throw new IllegalStateException("contract can not be null");
		}
		mdmId = contract.getMdmId();
		mdmLevel = contract.getMdmLevel();
		this.contract = contract;
		contractNumber = contract.getContractNumber();
		partnerPortal = contract.isPartnerPortal();
	}

	public void checkRequiredFields() {
		if (isEmpty(mdmId)) {
			throw new IllegalArgumentException("mdmId is null or empty!");
		}
		if (isEmpty(mdmLevel)) {
			throw new IllegalArgumentException("mdmLevel is null or empty!");
		}
	}

	public void buildSearchExpression() {
		
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date date = new Date();
		dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		searchExpressionBOBC.append("([Operating Status]= 'New' OR [Operating Status]= 'Created' OR [Operating Status] = 'Active') AND ");
		searchExpressionBOBC.append(""
					+ buildmdmSearchExpression(mdmId, mdmLevel, FIELD_MAPBOBC, false,
							false));
			searchExpressionBOBC.append("AND ([LXK MPS Agree Status] = 'Active' OR [LXK MPS Agree Status]='Current') AND(EXISTS ([LXK SW Entitlement Type] LIKE 'Consumable*' AND [LXK MPS Entitlement End Date] >= '"+dateFormat.format(date)+"'))");


		searchExpression.append("EXISTS ("
				+ buildmdmSearchExpression(mdmId, mdmLevel, FIELD_MAP, false,
						false));

		searchExpression.append(" AND [LXK SW MDM Account Level] ='Siebel') AND [Parent Agreement Id] IS NOT NULL AND [LXK C Agreement Header Status] = 'Current' "
				+ "AND [LXK MPS Catalog Flag]='N' AND ([Entitlement Type] = 'Consumables Mgmt Services' OR [Entitlement Type] = 'Consumables Mgmt Supply' OR [Entitlement Type] = 'Consumables Return' OR [Entitlement Type] = 'Consumables Mgmt Supply MICR')");
	
	
	}

	public void buildSearchExpressionForAccountList() {
		StringBuilder searchExpressionForAccountList = new StringBuilder();
		String mdmSearchExpression = "EXISTS ("
				+ buildmdmSearchExpression(mdmId, mdmLevel, FIELD_MAP, false,
						false);
		searchExpressionForAccountList.append(mdmSearchExpression);
		//searchExpressionForAccountList.append("");
		StringBuilder sortSpec = new StringBuilder();
		if((contract.isHardwareFlag() == contract.isAgreementFlag()))    // for either flag true or false
		{
			searchExpressionForAccountList                              // for hardware only
			.append(" AND [LXK SW MDM Account Level] ='Siebel') AND EXISTS([LXK MPS Portal Visibility Flag] ='Y' OR [LXK MPS Portal Visibility Flag] IS NULL) AND [Parent Agreement Id] IS NOT NULL AND" +
					" [LXK C Agreement Header Status] = 'Current' "
					+ "AND ([Entitlement Type] = 'HW Install Project Based' OR [Entitlement Type] = 'HW Install BAU'" +
					" OR [Entitlement Type] = 'HW Order Project Based' OR [Entitlement Type] = 'HW Order BAU')");
		}
		else if (contract.isHardwareFlag()) {  							// for hardware only
			searchExpressionForAccountList
					.append(" AND [LXK SW MDM Account Level] ='Siebel') AND EXISTS([LXK MPS Portal Visibility Flag] ='Y' OR [LXK MPS Portal Visibility Flag] IS NULL) AND [Parent Agreement Id] IS NOT NULL AND" +
							" [LXK C Agreement Header Status] = 'Current' "
							+ "AND ([Entitlement Type] = 'HW Install Project Based' OR [Entitlement Type] = 'HW Install BAU'" +
							" OR [Entitlement Type] = 'HW Order Project Based' OR [Entitlement Type] = 'HW Order BAU')");
		} else {														// for consumables only
			searchExpressionForAccountList
					.append(" AND [LXK SW MDM Account Level] ='Siebel') AND [Parent Agreement Id] IS NOT NULL AND [LXK C Agreement Header Status] = 'Current' "
							+ "AND [LXK MPS Catalog Flag]='Y' AND ([Entitlement Type] = 'Consumables Mgmt Services' OR [Entitlement Type] = 'Consumables Mgmt Supply') AND EXISTS([LXK MPS Supply Material Status Explicit] <> 'Inactive')");
		}
		
		buildChildSearchExp(sortSpec, searchExpressionForAccountList);
	}
	
	private void buildChildSearchExp(StringBuilder sortSpec, StringBuilder searchExpressionForAccountList) {
		String chldSearchExp="";
		if("Global".equalsIgnoreCase(contract.getMdmLevel()))   //l1 level login
		{
			chldSearchExp = "[Global Ultimate DUNS]='"+mdmId+"' AND [LXK SW MDM Account Level] ='Siebel'";
		}
		else if("Domestic".equalsIgnoreCase(contract.getMdmLevel()))   //l2 level login
		{
			
			chldSearchExp = "[Domestic Ultimate DUNS]='"+mdmId+"' AND [LXK SW MDM Account Level] ='Siebel'";
		}
		else if("Legal".equalsIgnoreCase(contract.getMdmLevel()))   //l3 level login
		{
			
			chldSearchExp = "[LXM MDM Legal Entity ID #]='"+mdmId+"' AND [LXK SW MDM Account Level] ='Siebel'";
		}
		else if("Account".equalsIgnoreCase(contract.getMdmLevel()))   //l4 level login
		{
			
			chldSearchExp = "[LXM MDM Account #]='"+mdmId+"' AND [LXK SW MDM Account Level] ='Siebel'";
		}
		sortSpec.append("LXK MPS Expedite Service(DESCENDING)");
		criteria = new QueryObject(CatalogEntitlementAccountDo.class,
				ActionEvent.QUERY);
		criteria.addComponentSearchSpec(CatalogEntitlementAccountDo.class,
				searchExpressionForAccountList.toString());
		criteria.addComponentSearchSpec(AgreementRelatedAccountsDO.class,
				chldSearchExp.toString());
		criteria.setSortString(sortSpec.toString());
	}

	public void buildSearchExpressionForEntitlementFlags() {
		StringBuilder searchExpressionForAccountList = new StringBuilder();
		String mdmSearchExpression = "EXISTS ("
				+ buildmdmSearchExpression(mdmId, mdmLevel, FIELD_MAP, false,
						false);
		searchExpressionForAccountList.append(mdmSearchExpression);
		StringBuilder sortSpec = new StringBuilder();
		if (contract.isHardwareFlag()) {
			searchExpressionForAccountList
			.append(" AND [LXK SW MDM Account Level] ='Siebel') AND [Parent Agreement Id] IS NOT NULL AND" +
					" [LXK C Agreement Header Status] = 'Current' "
					+ "AND ([Entitlement Type] = 'HW Install Project Based' OR [Entitlement Type] = 'HW Install BAU'" +
					" OR [Entitlement Type] = 'HW Order Project Based' OR [Entitlement Type] = 'HW Order BAU')");
		} else {
			searchExpressionForAccountList
			.append(" AND [LXK SW MDM Account Level] ='Siebel') AND [Parent Agreement Id] IS NOT NULL AND [LXK C Agreement Header Status] = 'Current' "
					+ "AND [LXK MPS Catalog Flag]='Y' AND ([Entitlement Type] = 'Consumables Mgmt Services' OR [Entitlement Type] = 'Consumables Mgmt Supply')");
		}
		
		buildChildSearchExp(sortSpec, searchExpressionForAccountList);
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
		}
		this.session = session;
	}

	public AccountFlagResult queryAndGetResult() {
		AccountFlagResult result = new AccountFlagResult();
		SiebelBusinessServiceProxy businessServiceProxy = session
				.getSiebelBusServiceProxy();
		
		if(partnerPortal)
		{
			result.setAssetEntitlementFlag(AmindServiceUtil.isRecordExist(
				"LXK MPS Entitlement - Portal", "LXK MPS Entitlement - Portal",
				searchExpression.toString(), businessServiceProxy));
		}
		else{
			result.setAssetEntitlementFlag(AmindServiceUtil.isRecordExist(
					"LXK MPS Contracted Consumable Only Asset List", "LXK MPS Contracted Consumable Only Asset List",
					searchExpressionBOBC.toString(), businessServiceProxy));
			}
		
		buildSearchExpressionForEntitlementFlags();
		IDataManager dataManager = getSession().getDataManager();
		List<CatalogEntitlementAccountDo> entitlementDoList = dataManager
				.query(criteria);
		if (LangUtil.isNotEmpty(entitlementDoList)) {
			result.setCatalogEntitlementFlag(true);
			result.setAccountList(convertCatalogAccountToAccountList(notNull(entitlementDoList), contractNumber));
		} else {
			result.setCatalogEntitlementFlag(false);
		}
		return result;
	}

	public List<Account> queryAndGetResultCatalogAgreement() {
		IDataManager dataManager = getSession().getDataManager();
		List<Account> result = convertCatalogAccountToAccountList(notNull(dataManager
				.query(criteria)), contractNumber);
		return result;
	}

	private static Map<String, String> populateFieldMap() {
		Map<String, String> fieldMap = new HashMap<String, String>();
		fieldMap.put("mdmLevel1AccountId", "Global Ultimate DUNS");
		fieldMap.put("mdmLevel2AccountId", "Domestic Ultimate DUNS");
		fieldMap.put("mdmLevel3AccountId", "LXM MDM Legal Entity ID #");
		fieldMap.put("mdmLevel4AccountId", "LXM MDM Account #");
		fieldMap.put("mdmLevel5AccountId", "LXK Account Id");
		return fieldMap;
	}
	
	private static Map<String, String> populateFieldMapBOBC() {
		Map<String, String> fieldMap = new HashMap<String, String>();
		fieldMap.put("mdmLevel1AccountId", "LXK Account Global DUNS Number");
		fieldMap.put("mdmLevel2AccountId", "LXK Account Domestic DUNS Number");
		fieldMap.put("mdmLevel3AccountId", "LXM MDM Legal Entity ID #");		
		fieldMap.put("mdmLevel4AccountId", "LXM MDM Account #");    				
		fieldMap.put("mdmLevel5AccountId", "Owner Account Id");				
		return fieldMap;
	}
	private static Map<String, String> populateChildFieldMap() {
		Map<String, String> fieldMap = new HashMap<String, String>();
		fieldMap.put("mdmLevel1AccountId", "Global Ultimate DUNS");
		fieldMap.put("mdmLevel2AccountId", "Domestic Ultimate DUNS");
		fieldMap.put("mdmLevel3AccountId", "LXM MDM Legal Entity ID #");
		fieldMap.put("mdmLevel4AccountId", "LXM MDM Account #");
		fieldMap.put("mdmLevel5AccountId", "Id");
		return fieldMap;
	}

}
