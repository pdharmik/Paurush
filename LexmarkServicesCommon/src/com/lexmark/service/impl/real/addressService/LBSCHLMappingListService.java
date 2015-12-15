package com.lexmark.service.impl.real.addressService;
/**	 
 * @author David Tsamalashvili 
 */	
import static com.lexmark.service.impl.real.addressService.AmindAddressConversionUtil.convertLBSCHLMapping;
import static com.lexmark.util.LangUtil.notNull;

import java.util.List;

import com.amind.data.service.ActionEvent;
import com.amind.data.service.IDataManager;
import com.amind.data.service.QueryObject;
import com.amind.session.Session;
import com.lexmark.contract.LBSCHLContract;
import com.lexmark.result.LBSCHLListResult;
import com.lexmark.service.impl.real.domain.LBSCHLMappingDo;
import com.lexmark.service.impl.real.domain.LBSFloorPlanAssetDo;
import com.lexmark.util.LangUtil;

public class LBSCHLMappingListService {
	private final LBSCHLContract contract;
	private Session session;
	private Session favoritesSession;
	private QueryObject chlCriteria;

	public LBSCHLMappingListService(LBSCHLContract contract) {
		if (contract == null) {
			throw new IllegalStateException("contract can not be null!");
		}
		this.contract = contract;
	}

	public void checkLBSCHLRequiredFields() {
		String childId = contract.getChildID();
		if (LangUtil.isEmpty(childId)) {
			throw new IllegalArgumentException("Child Id is Null");
		} 
	}

	public Session getSession() {
		if (session == null) {
			throw new IllegalStateException("Session has not set!");
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
	
	public Session getFavoritesSession() {
		if (favoritesSession == null) {
			throw new IllegalStateException("favorites session has not been set!");
		} else {
			return favoritesSession;
		}
	}

	public void setFavoritesSession(Session favoritesSession) {
		if (favoritesSession == null) {
			throw new IllegalStateException("favorites session can not be null!");
		} else {
			this.favoritesSession = favoritesSession;
		}
	}

	/**	 
	 * @author David Tsamalashvili 
	 */	
	public void buildLBSCHLSearchExpression() {
		StringBuilder builder = new StringBuilder();
		String childId = contract.getChildID();

		builder.append("[Id] = '");
		builder.append(childId);
		builder.append("'");

		chlCriteria = buildLBSCHLCriteria(builder.toString(),
				LBSCHLMappingDo.class, false);
	}

	private QueryObject buildLBSCHLCriteria(String searchExpression,
			Class<?> doClass, boolean paginationFlag) {

		QueryObject criteria = new QueryObject(doClass, ActionEvent.QUERY);
		criteria.addComponentSearchSpec(doClass, searchExpression);
		
		return criteria;
	}
	
	public LBSCHLListResult queryAndGetLBSCHLList() {
		IDataManager dataManager = getSession().getDataManager();
		
		List<LBSCHLMappingDo> lbsCHLDo = queryLBSCHLList(dataManager);
		LBSCHLListResult assetLBSList = convertLBSCHLMapping(lbsCHLDo);

		return assetLBSList;
	}
	
	@SuppressWarnings("unchecked")
	private List<LBSCHLMappingDo> queryLBSCHLList(IDataManager dataManager) {
		List<LBSCHLMappingDo> lbsCHLMappingDoList;

//		if (contract.isNewQueryIndicator()) {
			lbsCHLMappingDoList = dataManager.query(chlCriteria);
//		} else {
//			lbsCHLMappingDoList = dataManager.queryNext(chlCriteria);
//		}
		return notNull(lbsCHLMappingDoList);
	}
	
}
