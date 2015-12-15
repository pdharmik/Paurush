package com.lexmark.service.impl.real.delegate;

import static com.lexmark.service.impl.real.util.AmindGlobalConversionUtil.convertPartnerAgreementDoList;
import static com.lexmark.service.impl.real.util.AmindServiceUtil.buildMdmSearchExpressionForMdmLevel;
import static org.apache.commons.lang.StringUtils.isEmpty;

import java.util.List;

import com.amind.data.service.IDataManager;
import com.amind.session.Session;
import com.lexmark.contract.PartnerAgreementListContract;
import com.lexmark.domain.PartnerAgreement;
import com.lexmark.service.impl.real.domain.PartnerAgreementDo;

public class PartnerAgreementListService {

	private final String mdmId;
	private final String mdmLevel;
	private String searchExpression;
	private Session session;

	public PartnerAgreementListService(PartnerAgreementListContract contract) {
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
		searchExpression = buildMdmSearchExpressionForMdmLevel(mdmId, mdmLevel, null,
				false, "mdmLevel");
	}

	@SuppressWarnings("unchecked")
	public List<PartnerAgreement> queryAndGetResultList() {
		IDataManager dataManager = getSession().getDataManager();
		List<PartnerAgreementDo> agreementList = dataManager.queryBySearchExpression(
				PartnerAgreementDo.class, searchExpression);
		return convertPartnerAgreementDoList(agreementList);
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
