package com.lexmark.service.impl.real.delegate;

import static com.lexmark.service.impl.real.util.AmindGlobalConversionUtil.convertPartnerDirectAccountDoListToAccountList;
import static org.apache.commons.lang.StringUtils.isEmpty;

import java.util.List;

import com.amind.data.service.IDataManager;
import com.amind.session.Session;
import com.lexmark.contract.FSEAccountListContract;
import com.lexmark.domain.Account;
import com.lexmark.service.impl.real.domain.PartnerDirectAccountDo;

public class FSEAccountListService {

	private final String siebelEmployeeId;
	private String searchExpression;
	private Session session;

	public FSEAccountListService(FSEAccountListContract contract) {
		if (contract == null) {
			throw new IllegalArgumentException("contract can not be null");
		}
		siebelEmployeeId = contract.getSiebelEmployeeId();
	}

	public void checkRequiredFields() {
		if (isEmpty(siebelEmployeeId)) {
			throw new IllegalArgumentException("Siebel Employee Id is null or empty");
		}
	}

	public void buildSearchExpression() {
		StringBuilder builder = new StringBuilder();
		builder.append("[employeeId] = '");
		builder.append(siebelEmployeeId);
		builder.append("' AND [primaryFlag] = 'Y'");
		searchExpression = builder.toString();
	}

	@SuppressWarnings("unchecked")
	public List<Account> queryAndGetResultList() {
		IDataManager dataManager = getSession().getDataManager();
		List<PartnerDirectAccountDo> directAccountList = dataManager.queryBySearchExpression(
				PartnerDirectAccountDo.class, searchExpression);
		return convertPartnerDirectAccountDoListToAccountList(directAccountList);
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
