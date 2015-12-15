package com.lexmark.service.impl.real.addressService;
/**	 
 * @author David Tsamalashvili 
 */	
import static com.lexmark.service.impl.real.addressService.AmindAddressConversionUtil.convertLBSAsset;
import static com.lexmark.service.impl.real.util.AmindServiceUtil.buildBasicQueryObject;
import static com.lexmark.service.impl.real.util.AmindServiceUtil.buildCriteria;
import static com.lexmark.service.impl.real.util.AmindServiceUtil.buildSortString;
import static com.lexmark.util.LangUtil.isNotEmpty;
import static com.lexmark.util.LangUtil.notNull;

import java.util.List;
import java.util.Map;

import com.amind.data.service.ActionEvent;
import com.amind.data.service.IDataManager;
import com.amind.data.service.QueryObject;
import com.amind.session.Session;
import com.lexmark.contract.LBSAssetListContract;
import com.lexmark.result.LBSFloorPlanListResult;
import com.lexmark.service.impl.real.domain.AddressDo;
import com.lexmark.service.impl.real.domain.LBSFloorPlanAssetDo;
import com.lexmark.util.LangUtil;

public class LBSFloorPlanAssetListService {
	private final LBSAssetListContract contract;
	private final Map<String,String> fieldMap;
	private Session session;
	private Session favoritesSession;
	private QueryObject lbsCriteria;

	public LBSFloorPlanAssetListService(LBSAssetListContract contract, Map<String, String> addressFieldMap) {
		if (contract == null) {
			throw new IllegalStateException("contract can not be null!");
		}
		this.contract = contract;
		this.fieldMap = addressFieldMap;
	}

	public void checkLBSRequiredFields() {
		List<String> assetId = contract.getAssetIds();
		if (LangUtil.isEmpty(assetId)) {
			throw new IllegalArgumentException("Asset Id Id is Null");
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
	public void buildLBSAssetSearchExpression() {
		StringBuilder builder = new StringBuilder();
		List<String> assetId = contract.getAssetIds();

		builder.append("[Id] = '");
		builder.append(assetId.get(0));
		builder.append("'");

		if (assetId.size() > 1) {
			for (int i = 1; i < assetId.size(); i++) {
				builder.append(" OR [Id] = '");
				builder.append(assetId.get(i));
				builder.append("'");
			}
		}

		Map<String, Object> filterCriteria = contract.getFilterCriteria();

		if (isNotEmpty(filterCriteria)) {
			String filter = buildCriteria(filterCriteria, fieldMap, true, true);
			builder.append(filter);
		}

		lbsCriteria = buildLBSAssetCriteria(builder.toString(),
				LBSFloorPlanAssetDo.class, false);
	}

	private QueryObject buildLBSAssetCriteria(String searchExpression,
			Class<?> doClass, boolean paginationFlag) {

		QueryObject criteria = new QueryObject(doClass, ActionEvent.QUERY);
		if (paginationFlag) {
			buildBasicQueryObject(criteria, contract.getIncrement(),
					contract.getStartRecordNumber());
		}
		criteria.addComponentSearchSpec(doClass, searchExpression);

		Map<String, Object> sortCriteria = contract.getSortCriteria();
		if (isNotEmpty(sortCriteria)) {
			if (doClass.equals(AddressDo.class)) {
				criteria.setSortString(buildSortString(sortCriteria, fieldMap));
			}
		}
		return criteria;
	}
	
	public LBSFloorPlanListResult queryAndGetLBSAssetResultList() {
		IDataManager dataManager = getSession().getDataManager();

		List<LBSFloorPlanAssetDo> lbsAssetDo = queryLBSAssetList(dataManager);
		LBSFloorPlanListResult assetLBSList = convertLBSAsset(lbsAssetDo);

		return assetLBSList;
	}
	
	@SuppressWarnings("unchecked")
	private List<LBSFloorPlanAssetDo> queryLBSAssetList(IDataManager dataManager) {
		List<LBSFloorPlanAssetDo> lbsAssetDoList;

		if (contract.isNewQueryIndicator()) {
			lbsAssetDoList = dataManager.query(lbsCriteria);
		} else {
			lbsAssetDoList = dataManager.queryNext(lbsCriteria);
		}
		return notNull(lbsAssetDoList);
	}
	
}
