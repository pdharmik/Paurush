package com.lexmark.service.impl.real.orderSuppliesAssetService;

import static com.lexmark.util.LangUtil.isEmpty;

import java.util.List;

import com.amind.data.service.ActionEvent;
import com.amind.data.service.IDataManager;
import com.amind.data.service.QueryObject;
import com.amind.session.Session;
import com.lexmark.contract.PageCountsContract;
import com.lexmark.domain.PageCounts;
import com.lexmark.service.impl.real.domain.AssetDetailsPageCountsDO;
import com.lexmark.service.impl.real.domain.OrderSuppliesAssetDetailsPageCountsDO;
import com.lexmark.util.LangUtil;

public class PageCountsService {

	private Session session;
	private PageCountsContract contract;
	private String searchExpression;
	private String childSearchExpression;
	private QueryObject pageCountsCriteria;

	public PageCountsService(PageCountsContract contract) {
		this.contract = contract;
	}

	public void checkRequiredFields() {
		if (isEmpty(contract.getAssetId())) {
			throw new IllegalArgumentException("Asset id is null or empty!");
		}
	}

	public void buildSearchExpression() {
		searchExpression = "[assetId] = '" + contract.getAssetId() + "'"; 
		
		childSearchExpression = "[name] NOT ~LIKE '*mono*' AND [name] ~<> 'Total Scans' AND [name] IS NOT NULL"; 
		
		pageCountsCriteria = new QueryObject(OrderSuppliesAssetDetailsPageCountsDO.class, ActionEvent.QUERY);
		pageCountsCriteria.addComponentSearchSpec(OrderSuppliesAssetDetailsPageCountsDO.class, searchExpression);
		
		pageCountsCriteria.addComponentSearchSpec(AssetDetailsPageCountsDO.class, childSearchExpression);
		
	}

	@SuppressWarnings("unchecked")
	public List<PageCounts> queryAndGetPageCounts() {
		IDataManager dataManager = session.getDataManager();
		List<OrderSuppliesAssetDetailsPageCountsDO> pageCountsDOs = ((List<OrderSuppliesAssetDetailsPageCountsDO>)dataManager.query(pageCountsCriteria));
		OrderSuppliesAssetDetailsPageCountsDO pageCountsDO = LangUtil.first(pageCountsDOs);
		return PageCountsServiceUtil.populatePageCounts(pageCountsDO);
	}

	public Session getSession() {
		if (session == null) {
			throw new IllegalStateException("Session has not been initialized!");
		}
		return session;
	}

	public void setSession(Session session) {
		if (session == null) {
			throw new IllegalArgumentException("Session can not be null");
		}
		this.session = session;
	}

}
