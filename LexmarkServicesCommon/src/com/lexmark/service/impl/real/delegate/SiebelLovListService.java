package com.lexmark.service.impl.real.delegate;

import static com.lexmark.service.impl.real.util.AmindGlobalConversionUtil.convertLOVDoToLOV;
import static com.lexmark.util.LangUtil.notNull;
import static org.apache.commons.lang.StringUtils.isEmpty;
import static org.apache.commons.lang.StringUtils.isNotEmpty;

import java.util.List;

import com.amind.data.service.IDataManager;
import com.amind.session.Session;
import com.lexmark.contract.SiebelLOVListContract;
import com.lexmark.service.impl.real.domain.ListOfValues;
import com.lexmark.util.LangUtil;

public class SiebelLovListService {

	private final SiebelLOVListContract contract;
	private final String language;
	private String searchExpression;
	private Session session;

	public SiebelLovListService(SiebelLOVListContract contract, String language) {
		if (contract == null) {
			throw new IllegalArgumentException("contract can not be null!");
		}
		this.contract = contract;
		this.language = language;
	}

	public void checkRequiredFields() {
		if (isEmpty(contract.getLovName())) {
			throw new IllegalArgumentException("No LOV type specified");
		}
	}

	public void buildSearchExpression() {
		String lovName = contract.getLovName();
		String parent = contract.getParentName();

		StringBuilder builder = new StringBuilder("[type]='");
		builder.append(lovName);
		builder.append("' AND [language]='");
		builder.append(language);
		builder.append("'");

		if (lovName.contains("STATE_ABBREV")) {
			builder.append(" AND [low] IS NULL ");
		}

		if (isNotEmpty(parent)) {
			builder.append("AND [parentName] = '");
			builder.append(parent);
			builder.append("'");
		} else {
			builder.append("AND [parentName] IS NULL ");
		}

		builder.append(" AND [active] = 'Y'");
		if(contract.getLovName().equalsIgnoreCase("LXK_SERVICE_ERR_CODE_1"))
		{
			builder.append(" AND  [LXK SW List of Values.High]= 'Portal'");
		}
		if(LangUtil.isNotEmpty(contract.getValue()))
		{
			builder.append(" AND  [LXK SW List of Values.Value]= '"+contract.getValue()+"'");
		}
		

		searchExpression = builder.toString();
	}

	public List<com.lexmark.domain.ListOfValues> queryAndGetResultList() {
		List<ListOfValues> lovDoList = querySiebelLovList(getSession().getDataManager());
		return convertLOVDoToLOV(lovDoList);
	}

	@SuppressWarnings("unchecked")
	private List<ListOfValues> querySiebelLovList(IDataManager dataManager) {
		List<ListOfValues> lovDoList = dataManager.queryBySearchExpression(ListOfValues.class,
				searchExpression);
		return notNull(lovDoList);
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
