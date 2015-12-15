package com.lexmark.service.impl.real.delegate;

import static com.lexmark.service.impl.real.util.AmindServiceUtil.GLOBAL_MDMLEVEL;
import static com.lexmark.service.impl.real.util.AmindServiceUtil.LEGAL_MDMLEVEL;
import static com.lexmark.service.impl.real.util.AmindServiceUtil.SIEBEL_MDMLEVEL;
import static com.lexmark.util.LangUtil.isNotEmpty;
import static com.lexmark.util.LangUtil.notNull;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.logging.log4j.LogManager;

import com.amind.data.service.ActionEvent;
import com.amind.data.service.IDataManager;
import com.amind.data.service.QueryObject;
import com.amind.session.Session;
import com.lexmark.contract.LegalNameContract;
import com.lexmark.service.impl.real.domain.GlobalLegalEntityDO;
import org.apache.logging.log4j.Logger;

public class LegalNameService {
	//private static final Log logger = LogFactory.getLog(LegalNameService.class);
	private static final Logger logger = LogManager.getLogger(LegalNameService.class);
	private final String legalMdmId;
	private final String mdmLevel;
	private String searchExpression;
	private Session session;

	public LegalNameService(LegalNameContract contract) {
		if (contract == null) {
			throw new IllegalArgumentException("contract can not be null!");
		}
		legalMdmId = contract.getLegalMdmId();
		mdmLevel = contract.getMdmLevel();
	}

	public void checkRequiredFields() {
		if (legalMdmId == null) {
			throw new IllegalArgumentException("legalMdmId is null");
		} else if (legalMdmId.isEmpty()) {
			throw new IllegalArgumentException("legalMdmId is empty");
		}
		if (mdmLevel == null) {
			throw new IllegalArgumentException("mdmLevel is null");
		} else if (mdmLevel.isEmpty()) {
			throw new IllegalArgumentException("mdmLevel is empty");
		}
	}

	public void buildSearchExpression() {
		StringBuilder builder = new StringBuilder();

		if (GLOBAL_MDMLEVEL.equals(mdmLevel)) {
			builder.append("[mdmLevel1AccountId] = '");
		} else if (LEGAL_MDMLEVEL.equals(mdmLevel)) {
			builder.append("[mdmLevel3AccountId] = '");
		} else if (SIEBEL_MDMLEVEL.equals(mdmLevel)) {
			builder.append("[mdmLevel5AccountId] = '");
		} else {
			throw new IllegalArgumentException("Incorrect mdmLevel passed to retrieveGlobalLegalEntity: "
					+ mdmLevel);
		}

		builder.append(legalMdmId);
		builder.append("'");

		searchExpression = builder.toString();
	}

	public String queryAndGetResult() {
		List<GlobalLegalEntityDO> globalList = queryLegalName(getSession().getDataManager());
		String globalName = "";
		if (isNotEmpty(globalList)) {
			globalName =  globalList.get(0).getName();
		} else {
			logger.debug("Global Account with mdmId: '" + legalMdmId + "' and mdmLevel: '" + mdmLevel
					+ "' not found");
		}
		return globalName;
	}

	@SuppressWarnings("unchecked")
	private List<GlobalLegalEntityDO> queryLegalName(IDataManager dataManager) {
		QueryObject query = new QueryObject(GlobalLegalEntityDO.class, ActionEvent.QUERY);
		query.setNumRows(1);
		query.setQueryString(searchExpression);

		List<GlobalLegalEntityDO> globalList = dataManager.query(query);
		return notNull(globalList);
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
