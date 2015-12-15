package com.lexmark.service.impl.real.orderSuppliesAssetService;

import static com.lexmark.util.LangUtil.isEmpty;

import java.util.List;

import com.amind.data.service.ActionEvent;
import com.amind.data.service.IDataManager;
import com.amind.data.service.QueryObject;
import com.amind.session.Session;
import com.lexmark.contract.AgreementSoldToNumberContract;
import com.lexmark.service.impl.real.domain.AgreementSoldToNumberDo;
import com.lexmark.service.impl.real.domain.SoldToNumberDo;
import com.lexmark.util.LangUtil;

public class AmindAgreementSoldToNumberService {

	private Session session;
	private AgreementSoldToNumberContract contract;
	private String agreementId;
	private String contractNumber;
	private QueryObject criteria;
	
	public AmindAgreementSoldToNumberService(AgreementSoldToNumberContract contract) {
		if(contract == null) {
			throw new IllegalStateException("contract can not be null!");
		}
		
		this.contract = contract;
		this.agreementId = contract.getAgreementId();
		this.contractNumber = contract.getContractNumber();
	}
	
	
	public void checkRequiredFields() {
		if (isEmpty(contract.getAgreementId()) || isEmpty(contract.getContractNumber())) {
			throw new IllegalArgumentException("Agreement id or contract number is null or empty!");
		}
	}
	
	
	public void buildSoldToNumberListSearchExpression() {
		StringBuilder builder = new StringBuilder();
		
		builder.append("[agreementId] = '");
		builder.append(agreementId);
		builder.append("'");
		
		StringBuilder childBuilder = new StringBuilder();
		childBuilder.append(" [LXK MPS Sales Contract #] = '");
		childBuilder.append(contractNumber);
		childBuilder.append("'");
		
		criteria = new QueryObject(AgreementSoldToNumberDo.class,ActionEvent.QUERY);
		criteria.addComponentSearchSpec(AgreementSoldToNumberDo.class,builder.toString());
		
		criteria.addComponentSearchSpec(SoldToNumberDo.class, childBuilder.toString());
	}
	
	
	public List<String> queryAndGetSoldToNumberList() {
		IDataManager dataManager = getSession().getDataManager();
		List<AgreementSoldToNumberDo> agreementSoldToNumbers = dataManager.query(criteria);
		return AgreementSoldToNumberUtil.convertSoldToNumberDoToSoldToNumberList(LangUtil.notNull(agreementSoldToNumbers));
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

}
