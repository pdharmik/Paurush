package com.lexmark.service.impl.real.delegate;

import static com.lexmark.service.impl.real.util.AmindGlobalConversionUtil.convertEntityDOtoGlobalAccount;

import java.util.List;

import com.amind.data.service.IDataManager;
import com.amind.session.Session;
import com.lexmark.domain.GlobalAccount;
import com.lexmark.service.impl.real.domain.GlobalLegalEntityDO;

public class GlobalLegalEntityListService {

	private static final String SEARCH_EXPRESSION = "[accountLevel] = 'Global' OR ([accountLevel]='Legal' AND [mdmLevel1AccountId] IS NULL)";
	private Session session;

	@SuppressWarnings("unchecked")
	public List<GlobalAccount> queryAndGetResultList() {
		IDataManager dataManager = getSession().getDataManager();

		List<GlobalLegalEntityDO> globalList = dataManager.queryBySearchExpression(GlobalLegalEntityDO.class,
				SEARCH_EXPRESSION);
		return convertEntityDOtoGlobalAccount(globalList);
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
