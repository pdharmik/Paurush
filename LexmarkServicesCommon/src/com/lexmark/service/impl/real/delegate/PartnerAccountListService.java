package com.lexmark.service.impl.real.delegate;

import static com.lexmark.service.impl.real.util.AmindPartnerDataManagerUtil.convertAccountListDoToPortalAccountList;
import static com.lexmark.service.impl.real.util.AmindServiceUtil.buildMdmSearchExpressionForMdmLevel;
import static org.apache.commons.lang.StringUtils.isEmpty;

import java.util.List;

import com.amind.data.service.ActionEvent;
import com.amind.data.service.IDataManager;
import com.amind.data.service.QueryObject;
import com.amind.session.Session;
import com.lexmark.contract.PartnerAccountListContract;
import com.lexmark.result.PartnerAccountListResult;
import com.lexmark.service.impl.real.domain.PartnerAccountDo;
import com.lexmark.service.impl.real.util.AmindPartnerDataManagerUtil;

public class PartnerAccountListService {

	private final String mdmId;
	private final String mdmLevel;
	private String searchExpression;
	private Session session;
	private boolean massUploadflag;
	private boolean massUploadInstallDebriefFlag;

	public PartnerAccountListService(PartnerAccountListContract contract) {
		if (contract == null) {
			throw new IllegalArgumentException("contract can not be null!");
		}
		mdmId = contract.getMdmId();
		mdmLevel = contract.getMdmLevel();
		massUploadflag = contract.isMassUploadFlag();
		massUploadInstallDebriefFlag =  contract.isMassUploadInstallDebriefFlag();
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
		searchExpression = buildMdmSearchExpressionForMdmLevel(mdmId, mdmLevel, null,
				false, "mdmLevel");
		if(massUploadflag) {
			searchExpression = searchExpression + " AND EXISTS ([partnerSetUpType] = 'Mass Upload Consumables' AND [partnerSetUpFlag] ='Y' ) AND [partnerFlag] = 'Y' ";
		} else if(massUploadInstallDebriefFlag) {
			searchExpression = searchExpression + " AND EXISTS ([partnerSetUpType] = 'Mass Upload Install Debrief' AND [partnerSetUpFlag] ='Y') AND [partnerFlag] = 'Y' ";
		}
		
		//searchExpression = searchExpression + " AND [LXK SD Functional Classification]='Partner'";
	}

	@SuppressWarnings("unchecked")
	public PartnerAccountListResult queryAndGetResultList() {
		IDataManager dataManager = getSession().getDataManager();
		QueryObject searchQueryObject = new QueryObject(PartnerAccountDo.class, ActionEvent.QUERY);
		searchQueryObject.addComponentSearchSpec(PartnerAccountDo.class, searchExpression);
		List<PartnerAccountDo> partnerAccountDoList = dataManager.query(searchQueryObject);
		
		PartnerAccountListResult result = new PartnerAccountListResult();
		
		result.setAccountList(convertAccountListDoToPortalAccountList(partnerAccountDoList, false));
		result.setPartnerTypeList(AmindPartnerDataManagerUtil.getPartnerTypeList(partnerAccountDoList));
		
		return result;
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
