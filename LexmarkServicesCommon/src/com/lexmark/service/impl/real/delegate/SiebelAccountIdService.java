package com.lexmark.service.impl.real.delegate;

import static com.lexmark.service.impl.real.util.AmindServiceUtil.GLOBAL_MDMLEVEL;
import static com.lexmark.service.impl.real.util.AmindServiceUtil.DOMESTIC_MDMLEVEL;
import static com.lexmark.service.impl.real.util.AmindServiceUtil.LEGAL_MDMLEVEL;
import static com.lexmark.service.impl.real.util.AmindServiceUtil.ACCOUNT_MDMLEVEL;
import static com.lexmark.service.impl.real.util.AmindServiceUtil.SIEBEL_MDMLEVEL;


import static com.lexmark.util.LangUtil.isNotEmpty;
import static org.apache.commons.lang.StringUtils.isEmpty;

import java.util.List;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import com.amind.data.service.IDataManager;
import com.amind.session.Session;
import com.lexmark.contract.SiebelAccountIdContract;
import com.lexmark.service.impl.real.domain.GlobalLegalEntityDO;

public class SiebelAccountIdService {
	private static Logger logger = LogManager.getLogger(SiebelAccountIdService.class);

	private final String mdmId;
	private final String mdmLevel;
	private String searchExpression;
	private Session session;

	public SiebelAccountIdService(SiebelAccountIdContract contract) {
		if (contract == null) {
			throw new IllegalArgumentException("contract can not be null");
		}
		mdmId = contract.getMdmId();
		mdmLevel = contract.getMdmLevel();
	}

	public void checkRequiredFields() {
		if (isEmpty(mdmId)) {
			throw new IllegalArgumentException("Mdm Id is null or empty");
		}
		if (isEmpty(mdmLevel)) {
			throw new IllegalArgumentException("Mdm Level is null or empty");
		}
	}

	public void buildSearchExpression() {
		StringBuilder builder = new StringBuilder();


		
		if (GLOBAL_MDMLEVEL.equals(mdmLevel)) {
			builder.append("[mdmLevel1AccountId] = '");
		} else if (DOMESTIC_MDMLEVEL.equals(mdmLevel)) {
			builder.append("[mdmLevel2AccountId] = '");
		}else if (LEGAL_MDMLEVEL.equals(mdmLevel)) {
			builder.append("[mdmLevel3AccountId] = '");
		} else if (ACCOUNT_MDMLEVEL.equals(mdmLevel)) {
			builder.append("[mdmLevel4AccountId] = '");}
		else if (SIEBEL_MDMLEVEL.equals(mdmLevel)) {
				builder.append("[mdmLevel5AccountId] = '");}
		
		builder.append(mdmId);
		builder.append("' AND [accountLevel] = '");
		builder.append(mdmLevel);
		builder.append("'");

		searchExpression = builder.toString();
		
		logger.info(" searchExpression >>>>>>>>>>>>>>>>>>>>>>>>>::::: "+searchExpression.toString());
	}

	@SuppressWarnings("unchecked")
	public String queryAndGetResult() {
		IDataManager dataManager = getSession().getDataManager();
		String partnerId = "";
		List<GlobalLegalEntityDO> partnerAccountDoList = dataManager.queryBySearchExpression(
				GlobalLegalEntityDO.class, searchExpression);
		if (isNotEmpty(partnerAccountDoList)) {
			partnerId =  partnerAccountDoList.get(0).getId();
		} 
		return partnerId;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		if (session == null) {
			throw new IllegalArgumentException("session can not be null!");
		}
		this.session = session;
	}
}
