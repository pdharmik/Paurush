package com.lexmark.service.impl.real.delegate;

import static com.lexmark.constants.LexmarkConstants.TARGET_SYSTEM_SIEBEL;
import static com.lexmark.service.impl.real.util.AmindServiceUtil.DOMESTIC_MDMLEVEL;
import static com.lexmark.service.impl.real.util.AmindServiceUtil.GLOBAL_MDMLEVEL;
import static com.lexmark.util.LangUtil.isNotEmpty;
import static com.lexmark.util.LangUtil.notNull;
import static org.apache.commons.lang.StringUtils.isEmpty;

import java.util.List;

import com.amind.data.service.IDataManager;
import com.amind.session.Session;
import com.lexmark.contract.DunsNumberContract;
import com.lexmark.domain.LexmarkTransaction;
import com.lexmark.service.impl.real.domain.GlobalLegalEntityDO;

public class DunsNumberService {

	private final String mdmId;
	private final String mdmLevel;
	private String searchExpression;
	private Session session;

	public DunsNumberService(DunsNumberContract contract) {
		if (contract == null) {
			throw new IllegalArgumentException("contract can not be null!");
		}
		mdmId = contract.getMdmId();
		mdmLevel = contract.getMdmLevel();
	}

	public void checkRequiredFields() {
		if (isEmpty(mdmId)) {
			throw new IllegalArgumentException("mdmId is null or empty");
		}
		if (isEmpty(mdmLevel)) {
			throw new IllegalArgumentException("mdmLevel is null or empty");
		}
	}

	public void buildSearchExpression() {
		StringBuilder builder = new StringBuilder("[mdmLevel3AccountId]= '");
		builder.append(mdmId);
		builder.append("' AND [accountLevel] = '");
		builder.append(mdmLevel);
		builder.append("'");
		searchExpression = builder.toString();
	}

	public String queryAndGetResult() {
		List<GlobalLegalEntityDO> dunsList = queryDunsList(getSession().getDataManager());
		String mdmNumber = "";
		if (isNotEmpty(dunsList)) {
			GlobalLegalEntityDO duns = dunsList.get(0);
			if (GLOBAL_MDMLEVEL.equalsIgnoreCase(mdmLevel)) {
				mdmNumber =  duns.getMdmLevel1AccountId();
			} else if (DOMESTIC_MDMLEVEL.equalsIgnoreCase(mdmLevel)) {
				mdmNumber = duns.getMdmLevel2AccountId();
			} else {
				mdmNumber = duns.getDunsNumber();
			}
		} 
		return mdmNumber;
	}

	@SuppressWarnings("unchecked")
	private List<GlobalLegalEntityDO> queryDunsList(IDataManager dataManager) {
		List<GlobalLegalEntityDO> dunsList = dataManager.queryBySearchExpression(GlobalLegalEntityDO.class,
				searchExpression);
		return notNull(dunsList);
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
