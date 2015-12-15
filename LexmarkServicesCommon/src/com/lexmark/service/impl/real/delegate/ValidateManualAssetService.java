package com.lexmark.service.impl.real.delegate;

import static com.lexmark.util.LangUtil.isNotEmpty;
import static com.lexmark.util.LangUtil.notNull;
import static org.apache.commons.lang.StringUtils.isNotEmpty;

import java.util.List;

import com.amind.data.service.ActionEvent;
import com.amind.data.service.IDataManager;
import com.amind.data.service.QueryObject;
import com.amind.session.Session;
import com.lexmark.contract.ManualAssetContract;
import com.lexmark.service.impl.real.domain.InternalProductDo;

public class ValidateManualAssetService {

	private final ManualAssetContract contract;
	private String searchExpression;
	private QueryObject criteria;
	private Session session;

	public ValidateManualAssetService(ManualAssetContract contract) {
		if (contract == null) {
			throw new IllegalStateException("contract can not be null");
		}
		this.contract = contract;
	}

	public void checkRequiredFields() {
		String modelNumber = contract.getModelNumber();
		if (modelNumber == null) {
			throw new IllegalArgumentException("model number is null!");
		} else if (modelNumber.isEmpty()) {
			throw new IllegalArgumentException("model number is empty!");
		}
	}

	public void buildSearchExpression() {
		searchExpression = buildManualAssetSearchExpression();
		criteria = buildManualAssetCriteria();
	}

	public boolean queryAndGetResult() {
		List<InternalProductDo> assetList = queryInternalProductList(getSession().getDataManager());
		return isNotEmpty(assetList);
	}

	private QueryObject buildManualAssetCriteria() {
		QueryObject criteria = new QueryObject(InternalProductDo.class, ActionEvent.QUERY);
		criteria.setQueryString(searchExpression);
		criteria.setNumRows(1);
		return criteria;
	}

	private String buildManualAssetSearchExpression() {
		StringBuilder builder = new StringBuilder("[modelNumber]='");
		builder.append(contract.getModelNumber().trim().toUpperCase());
		builder.append("'");

		String productTli = contract.getProductTLI();
		if (isNotEmpty(productTli)) {
			builder.append(" AND [productTli]='");
			builder.append(productTli.trim().toUpperCase());
			builder.append("'");
		}

		return builder.toString();
	}

	@SuppressWarnings("unchecked")
	private List<InternalProductDo> queryInternalProductList(IDataManager dataManager) {
		List<InternalProductDo> assetList = dataManager.query(criteria);
		return notNull(assetList);
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
