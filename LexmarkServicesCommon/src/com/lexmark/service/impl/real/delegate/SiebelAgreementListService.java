package com.lexmark.service.impl.real.delegate;

import static com.lexmark.service.impl.real.requestService.AmindRequestDataConversionUtil.convertAccountDoListToAccount;
import static com.lexmark.service.impl.real.util.AmindServiceUtil.buildmdmSearchExpression;
import static com.lexmark.util.LangUtil.isEmpty;
import static com.lexmark.util.LangUtil.isNotEmpty;
import static com.lexmark.util.LangUtil.notNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.logging.log4j.LogManager;

import com.amind.data.service.ActionEvent;
import com.amind.data.service.IDataManager;
import com.amind.data.service.QueryObject;
import com.amind.session.Session;
import com.lexmark.contract.AssetContract;
import com.lexmark.contract.SiebelAccountListContract;
import com.lexmark.domain.Account;
import com.lexmark.service.impl.real.domain.AccountDo;
import com.lexmark.service.impl.real.domain.AccountEntitlementDo;
import com.lexmark.service.impl.real.domain.AccountServiceAgreementDo;
import com.lexmark.service.impl.real.domain.AgreementFlagsDo;
import com.lexmark.service.impl.real.domain.OrderSuppliesAssetDetailDo;
import com.lexmark.service.impl.real.util.AmindServiceUtil;

import org.apache.logging.log4j.Logger;


public class SiebelAgreementListService {

	//protected static final Log logger = LogFactory.getLog(SiebelAgreementListService.class);
	protected static final Logger logger = LogManager.getLogger(SiebelAgreementListService.class);
	private static final Map<String,String> FIELD_MAP = populateFieldMap();
	
	private final String mdmId;
	private final String mdmLevel;
	private StringBuilder searchExpression = new StringBuilder();
	private	QueryObject criteria;
	private Session session;
	private SiebelAccountListContract contract;

	public SiebelAgreementListService(SiebelAccountListContract contract) {
		if (contract == null) {
			throw new IllegalStateException("contract can not be null");
		}
		mdmId = contract.getMdmId();
		mdmLevel = contract.getMdmLevel();
		this.contract = contract;
	}

	public void checkRequiredFields() {
		boolean ok = isMdmSearch(this.contract) || isAccountSearch(this.contract);
		if (!ok) {
			throw new IllegalArgumentException("neither mdmLevel/mdmId nor accountId provided");
		}
	}

	private static boolean isMdmSearch(SiebelAccountListContract contract) {
        return isNotEmpty(contract.getMdmId()) && isNotEmpty(contract.getMdmLevel());
    }
	
	private static boolean isAccountSearch(SiebelAccountListContract contract) {
        return isNotEmpty(contract.getAccountId());
    }
	
	public void buildSearchExpression() {
		if (isMdmSearch(contract)) {
			searchExpression.append(buildmdmSearchExpression(mdmId, mdmLevel,
					FIELD_MAP, false, false));
			searchExpression.append(" AND [LXK SW MDM Account Level] ='Siebel'");
		} else if (isAccountSearch(contract)) {
			searchExpression.append(String.format("[id]='%s'", contract.getAccountId()));
		} else {
			throw new AssertionError();
		}
		searchExpression.append(" AND [LXK MPS Primary Service Agreement Name] IS NOT NULL ");
//		List<OrderSuppliesAssetDetailDo> list = queryBySearchExpression(dataManager, OrderSuppliesAssetDetailDo.class, tempStr,
//                contract, "query by Asset Id");
//		criteria = new QueryObject(AgreementFlagsDo.class, ActionEvent.QUERY);
//		criteria.addComponentSearchSpec(AgreementFlagsDo.class, searchExpression.toString());
		searchExpression.append( " AND [Service Agreement.Agreement Status]='Current' " +
		"AND ( [Service Agreement.LXK MPS EntlmntCriteria]='YES' OR [Service Agreement.LXK MPS SupplyCtlg] = 'YES' " +
		"OR [Service Agreement.LXK MPS SupplyCatalog_Expedite] = 'YES' OR [Service Agreement.LXK MPS EntitlementCriteria_Expedite] = 'YES') AND ([Agreement Entitlement.LXK MPS Catalog Flag] = 'Y' AND [Agreement Entitlement.LXK MPS Entitlement Status] = 'Valid'"
		+ "AND [Agreement Entitlement.LXK MPS Type] IS NULL) ");
//		criteria.addComponentSearchSpec(AccountServiceAgreementDo.class, searchspecForAgreement);
//		String searchspecForEntitlement = "";
//		criteria.addComponentSearchSpec(AccountEntitlementDo.class, searchspecForEntitlement);
	}

	public List<Account> queryAndGetResultList() {
		IDataManager dataManager = getSession().getDataManager();
		//List<AgreementFlagsDo> agreementDoList = dataManager.query(criteria);
		List<AgreementFlagsDo> agreementDoList = queryBySearchExpression(dataManager, AgreementFlagsDo.class,searchExpression);    // changing query to queryByWxpression to satisfy search on grand child IC as per email by Siebel "Defect 18129"
		return convertAccountDoListToAccount(notNull(agreementDoList));
	}
	
	 @SuppressWarnings("unchecked")
	    private static <T> List<T> queryBySearchExpression(IDataManager dataManager, Class<T> class1, StringBuilder searchExpression) {
	        List<T> list = null;
	        list = dataManager.queryBySearchExpression(class1, searchExpression.toString());
	        return list;
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
	
	private static Map<String, String> populateFieldMap() {
		Map<String, String> fieldMap = new HashMap<String, String>();
		fieldMap.put("mdmLevel1AccountId", "Global Ultimate DUNS");
		fieldMap.put("mdmLevel2AccountId", "Domestic Ultimate DUNS");
		fieldMap.put("mdmLevel3AccountId", "LXM MDM Legal Entity ID #");
		fieldMap.put("mdmLevel4AccountId", "LXM MDM Account #");
		fieldMap.put("mdmLevel5AccountId", "Id");
		return fieldMap;
	}

}
