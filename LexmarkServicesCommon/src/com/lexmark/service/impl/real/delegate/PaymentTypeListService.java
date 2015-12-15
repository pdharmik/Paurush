package com.lexmark.service.impl.real.delegate;

import static com.lexmark.util.LangUtil.isEmpty;
import static com.lexmark.util.LangUtil.isNotEmpty;
import static com.lexmark.util.LangUtil.isNotBlank;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.amind.data.service.ActionEvent;
import com.amind.data.service.IDataManager;
import com.amind.data.service.QueryObject;
import com.amind.session.Session;
import com.lexmark.contract.PaymentListContract;
import com.lexmark.result.PaymentTypeListResult;
import com.lexmark.service.impl.real.domain.HardwareSapCatalogDo;
import com.lexmark.service.impl.real.domain.OrderPartDo;
import com.lexmark.service.impl.real.domain.SuppliesCatalogDo;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class PaymentTypeListService {

	//protected static final Log logger = LogFactory
	//		.getLog(SiebelAgreementListService.class);
	protected static final Logger logger = LogManager
			.getLogger(SiebelAgreementListService.class);
	private QueryObject criteria;
	private Session session;
	private String agreementId;
	private String soldToNumber;
	private boolean hardwareFlag;
	private String contractNumber;
	
	public PaymentTypeListService (PaymentListContract contract) {
		if (contract == null) {
			throw new IllegalStateException("contract can not be null");
		}
		agreementId = contract.getAgreementId();
		soldToNumber = contract.getSoldToNumber();
		this.hardwareFlag = contract.isHardwareFlag();
		this.contractNumber = contract.getContractNumber();
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
		}
		this.session = session;
	}
	
	public void checkRequiredFields() {
		if (isEmpty(agreementId)) {
			throw new IllegalArgumentException("agreementId is null or empty!");
		}
		if (isEmpty(soldToNumber)) {
			throw new IllegalArgumentException("soldToNumber is null or empty!");
		}
	}
	
	public void buildSearchExpression() {
		StringBuilder searchExpression = new StringBuilder();
		StringBuilder childExpression = new StringBuilder();
		searchExpression
				.append("[agreementId] ='");
		searchExpression.append(agreementId);
		searchExpression.append("'");
		
		if (!hardwareFlag) {
			searchExpression.append(" AND EXISTS ([LXK MPS Sold To #] ='");
			searchExpression.append(soldToNumber);
			searchExpression.append("')");
			
			if(isNotBlank(contractNumber)) {
				searchExpression.append(" AND EXISTS ([LXK MPS Contract #] = '");
				searchExpression.append(contractNumber);
				searchExpression.append("')");
			}
			
			searchExpression.append("AND [LXK MPS Display On Portal]='Y'");
			childExpression.append("[LXK MPS SAP Contract #]='");
			childExpression.append(contractNumber);
			childExpression.append("'");
			childExpression.append(" AND [LXK MPS SAP Sold To #] ='");
			childExpression.append(soldToNumber);	
			childExpression.append("'");
			criteria = new QueryObject(OrderPartDo.class,
					ActionEvent.QUERY);
			
			criteria.addComponentSearchSpec(OrderPartDo.class, searchExpression.toString());
			criteria.addComponentSearchSpec(SuppliesCatalogDo.class, childExpression.toString());
		} else {
			searchExpression.append(" AND [sapSoldTo] ='");
			searchExpression.append(soldToNumber);
			searchExpression.append("'");
			
			if(isNotBlank(contractNumber)) {
				searchExpression.append(" AND [sapContract] = '");
				searchExpression.append(contractNumber);
				searchExpression.append("'");
			}
			searchExpression.append("AND [LXK MPS Display On Portal]='Y' ");
			
			criteria = new QueryObject(HardwareSapCatalogDo.class,
					ActionEvent.QUERY);
			
			criteria.addComponentSearchSpec(HardwareSapCatalogDo.class, searchExpression.toString());
		}
		
	}
	
	public PaymentTypeListResult queryAndGetResult() {
		IDataManager dataManager = getSession().getDataManager();
		
		PaymentTypeListResult res = new PaymentTypeListResult();
		ArrayList<String> paymentTypes = new ArrayList<String>();
		
		if (!hardwareFlag) {
			List<OrderPartDo> resultOrderParts = dataManager.query(criteria);
			
			if (isNotEmpty(resultOrderParts)) {
				for (OrderPartDo orderPartDo : resultOrderParts) {
					if(orderPartDo != null && orderPartDo.getSuppliesCatalog() != null) {
						for (SuppliesCatalogDo supplyCatalogDo : orderPartDo.getSuppliesCatalog()) {
							String paymentType = supplyCatalogDo
									.getBillingModel();
							if (!paymentTypeExist(paymentTypes, paymentType)) {
								paymentTypes.add(paymentType);
							}
						}
					}

				}
			}
			res.setPaymentType(paymentTypes);
		}
		
		else {
			List<HardwareSapCatalogDo> result = dataManager.query(criteria);
			if (isNotEmpty(result)) {
				for (HardwareSapCatalogDo pickSapCatalogDo : result) {
					String paymentType = pickSapCatalogDo.getBillingModel();
					if (!paymentTypeExist(paymentTypes, paymentType)) {
						paymentTypes.add(paymentType);
					}
				}
			}
			res.setPaymentType(paymentTypes);
		}
		return res;
	}
	
	private boolean paymentTypeExist(List<String> list, String paymentType) {		
		for (String paymType : list) {
			if(paymType.equalsIgnoreCase(paymentType)){
				return true;
			}
		}		
		return false;
	}
	
}
