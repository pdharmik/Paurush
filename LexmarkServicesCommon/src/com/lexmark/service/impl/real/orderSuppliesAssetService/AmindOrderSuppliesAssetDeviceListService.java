package com.lexmark.service.impl.real.orderSuppliesAssetService;

import static com.lexmark.service.impl.real.deviceService.AmindContractedDeviceServiceUtil.checkRequiredFields;
import static com.lexmark.service.impl.real.orderSuppliesAssetService.AmindOrderSuppliesAssetConversionUtil.toAssetList;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.logging.log4j.LogManager;

import com.amind.common.service.SiebelBusinessServiceProxy;
import com.amind.data.service.ActionEvent;
import com.amind.data.service.IDataManager;
import com.amind.data.service.QueryObject;
import com.amind.session.Session;
import com.lexmark.contract.AssetListContract;
import com.lexmark.domain.Asset;
import com.lexmark.result.AssetListResult;
import com.lexmark.service.impl.real.AmindCrmSessionHandle;
import com.lexmark.service.impl.real.domain.AccountAssetFavorites;
import com.lexmark.service.impl.real.domain.AssetBase;
import com.lexmark.service.impl.real.domain.ContractedAndEntitledDeviceDo;
import com.lexmark.service.impl.real.util.AmindServiceUtil;
import com.lexmark.util.LangUtil;
import org.apache.logging.log4j.Logger;

/**
 * See MPS_OrderManagement_Consumables_Assets_Orders_<...>.docx .
 * 
 * 
 * @author vpetruchok
 * @version 1.0, 2012-05-21
 */
public class AmindOrderSuppliesAssetDeviceListService {

	//private static Log logger = LogFactory
	//		.getLog(AmindOrderSuppliesAssetDeviceListService.class);
	private static Logger logger = LogManager
	.getLogger(AmindOrderSuppliesAssetDeviceListService.class);
														
//	private static final Class<?> DO_CLASS_STANDARD = ContractedAndEntitledAssetDo.class; /*																			 */
	private static final Class<?> DO_CLASS_STANDARD = ContractedAndEntitledDeviceDo.class; 	
	private static final Class<?> DO_CLASS_FAVORITE = AccountAssetFavorites.class; /*																 */
//	private static final String DO_CLASS_STANDARD_BO = ContractedAndEntitledAssetDo.BO;
//	private static final String DO_CLASS_STANDARD_BC = ContractedAndEntitledAssetDo.BC;
	private static final String DO_CLASS_STANDARD_BO = ContractedAndEntitledDeviceDo.BO;
	private static final String DO_CLASS_STANDARD_BC = ContractedAndEntitledDeviceDo.BC;
	private static final String DO_CLASS_FAVORITE_BO = AccountAssetFavorites.BO;
	private static final String DO_CLASS_FAVORITE_BC = AccountAssetFavorites.BC;

	private static final Map<String, String> FIELD_MAP = fieldMap();
	private static final Map<String, String> FAVORITE_FIELD_MAP = favoriteFieldMap();
	private final Session session;
	private final Session chldSession;
	private final AmindCrmSessionHandle crmSessionHandle;
	private String favoriteSearchExpression;
	private String allAssetSearchExpression;
	private AssetListContract contract;
	private QueryObject criteria;
	private Session totalCountSession;
	private Session favoritesSession;
	private ExecutorService executor;
	
	public static final String GLOBAL_MDMLEVEL = "Global";
	public static final String DOMESTIC_MDMLEVEL = "Domestic";
	public static final String LEGAL_MDMLEVEL = "Legal";
	public static final String ACCOUNT_MDMLEVEL = "Account";
	public static final String SIEBEL_MDMLEVEL = "Siebel";

	public AmindOrderSuppliesAssetDeviceListService(Session session, Session chldSession,
			AmindCrmSessionHandle crmSessionHandle) {
		this.session = session;
		this.chldSession= chldSession;
		this.crmSessionHandle = crmSessionHandle;
	}

	private static Map<String, String> fieldMap() {
		Map<String, String> m = new HashMap<String, String>();
		m.putAll(OrderSuppliesAssetSiebelFieldMap.allFieldMap()); // retrieveAllDevice
																	// filter/sort
																	// criteria
		// m.put("mdmLevel1AccountId", "LXK MPS Ent Global DUNS");
		// m.put("mdmLevel1AccountId", "LXK MPS Domestic DUNS");
		// m.put("mdmLevel2AccountId", "LXK MPS Domestic DUNS");
		// m.put("mdmLevel3AccountId", "LXK SW Agreement Account LEGAL MDM ID");
		// m.put("mdmLevel4AccountId", "LXK SW Agreement Account MDM Account");
		// m.put("mdmLevel5AccountId", "LXK SW Agreement Account Id");

		m.put("mdmLevel1AccountId", "LXK MPS Account Global DUNS Number");
		m.put("mdmLevel2AccountId", "LXK MPS Account Domestic DUNS Number");
		m.put("mdmLevel3AccountId", "LXK MPS  Account MDM Legal Entity ID #");
		m.put("mdmLevel4AccountId", "LXK MPS Account MDM Account #");
		m.put("mdmLevel5AccountId", "LXK MPS Owner Account Id");
		m.put("mdmLevel", "LXK SW Agreement Account Id");
		return Collections.unmodifiableMap(m);
	}

	private static Map<String, String> favoriteFieldMap() {
		Map<String, String> m = new HashMap<String, String>();
		m.putAll(OrderSuppliesAssetSiebelFieldMap.allFavoriteFieldMap()); // retrieveAllDevice
																			// filter/sort
																			// criteria
		m.put("mdmLevel1AccountId", "LXK SW Agreement Account Global DUNS");
		m.put("mdmLevel2AccountId", "LXK SW Agreement Account Domestic DUNS");
		m.put("mdmLevel3AccountId", "LXK SW Agreement Account LEGAL MDM ID");
		m.put("mdmLevel4AccountId", "LXK SW Agreement Account MDM Account");
		m.put("mdmLevel5AccountId", "LXK SW Agreement Account Id");
		m.put("mdmLevel", "LXK SW Agreement Account MDM Level");
		return Collections.unmodifiableMap(m);
	}

	public AssetListResult retrieveDeviceList(AssetListContract contract)
			throws InterruptedException, ExecutionException {
		this.contract = contract;

		checkRequiredFields(contract);

		final IDataManager dataManager = this.session.getDataManager();
		final boolean favoriteFlag = contract.isFavoriteFlag();
		buildAssetSearchExpression(dataManager, this.chldSession);
		buildQueryCriteria();

		executor = Executors.newFixedThreadPool(3);

		Future<List<AssetBase>> assetListFuture = executor
				.submit(new Callable<List<AssetBase>>() {
					@Override
					public List<AssetBase> call() throws Exception {
						return queryAssetList(dataManager);
					}
				});

		Future<Set<String>> favoritesFuture = executor
				.submit(new Callable<Set<String>>() {
					@Override
					public Set<String> call() throws Exception {
						if (!favoriteFlag) {
							return processFavoriteSet();
						} else {
							return new HashSet<String>();
						}
					}
				});

		Future<Integer> totalCountFuture = executor
				.submit(new Callable<Integer>() {
					@Override
					public Integer call() throws Exception {
						return processRecordsTotalCount();
					}
				});

		List<AssetBase> assetList = assetListFuture.get();
		Set<String> favoriteSet = favoritesFuture.get();
		int count = totalCountFuture.get();

		executor.shutdown();

		List<Asset> accountAssets = toAssetList(assetList, favoriteSet);

		AssetListResult result = new AssetListResult();
		result.setAccountAssets(accountAssets);
		result.setTotalCount(count);
		return result;
	}

	private void buildQueryCriteria() {
		Class<?> doClass = DO_CLASS_STANDARD;
		Map<String, String> doClassFieldMap = FIELD_MAP;
		if (contract.isFavoriteFlag()) {
			doClass = DO_CLASS_FAVORITE;
			doClassFieldMap = FAVORITE_FIELD_MAP;
			criteria = new QueryObject(doClass, ActionEvent.QUERY);
			criteria.addComponentSearchSpec(doClass,
					this.favoriteSearchExpression);
		} else {
			criteria = new QueryObject(doClass, ActionEvent.QUERY);
			criteria.addComponentSearchSpec(doClass,
					this.allAssetSearchExpression);
		}
		AmindServiceUtil.buildBasicQueryObject(criteria,
				contract.getIncrement(), contract.getStartRecordNumber());
		if (hasSortCriteria(contract)) {
			criteria.setSortString(AmindServiceUtil.buildSortString(
					contract.getSortCriteria(), doClassFieldMap));
		}
	}

	private void buildAssetSearchExpression(IDataManager dataManager, Session chldSession) {
		this.favoriteSearchExpression = AmindOrderSuppliesAssetSearchExpressionUtil
				.buildFavoriteSearchExpression(contract, dataManager,
						FAVORITE_FIELD_MAP);

		if (!contract.isFavoriteFlag()) {
			StringBuilder allSearchBuilder = new StringBuilder();
			
			this.allAssetSearchExpression = AmindOrderSuppliesAssetSearchExpressionUtil
					.buildAssetSearchExpression(contract, chldSession, FIELD_MAP);
			
			
			
			//  Filter Criteria
			allSearchBuilder.append(AmindOrderSuppliesAssetSearchExpressionUtil.filterCriteriaExpr(contract, FIELD_MAP));
         
			this.allAssetSearchExpression += allSearchBuilder.toString();
		}
	}

	private String appendMdmLevelSearchSpec(String mdmLevel, String mdmId) {
		StringBuilder expr = new StringBuilder();
		if (SIEBEL_MDMLEVEL.equals(mdmLevel)) {
			expr.append(LangUtil.isEmpty(contract.getChlNodeId()) ? " OR " : " AND ");
//			expr.append("EXISTS ([LXK SW Agreement Account Id] = '");
			expr.append("EXISTS ([LXK MPS Account Id] = '");
			expr.append(mdmId);
			expr.append("')");
			if(LangUtil.isEmpty(contract.getChlNodeId())){
				expr.append(")");
			}
//			expr.append(" AND EXISTS ([LXK MPS CSS Account Id] ='");
//			expr.append(mdmId);
//			expr.append("'");
		}

		else if (LEGAL_MDMLEVEL.equals(mdmLevel)) {
			expr.append(LangUtil.isEmpty(contract.getChlNodeId()) ? " OR " : " AND ");
//			expr.append("EXISTS ([LXK SW Agreement Account LEGAL MDM ID] = '");
			expr.append("EXISTS ([LXK MPS Legal] = '");
			expr.append(mdmId);
			expr.append("')");
			if(LangUtil.isEmpty(contract.getChlNodeId())){
				expr.append(")");
			}
//			expr.append(" AND EXISTS ([LXK MPS CSS Legal] ='");
//			expr.append(mdmId);
//			expr.append("'");
		}

		else if (GLOBAL_MDMLEVEL.equals(mdmLevel)) {
			expr.append(LangUtil.isEmpty(contract.getChlNodeId()) ? " OR " : " AND ");
//			expr.append("EXISTS ([LXK SW Agreement Account Global DUNS] = '");
			expr.append("EXISTS ([LXK MPS Ent Global DUNS] = '");
			expr.append(mdmId);
			expr.append("')");
			if(LangUtil.isEmpty(contract.getChlNodeId())){
				expr.append(")");
			}
//			expr.append(" AND EXISTS ([LXK MPS CSS Global DUNS] ='");
//			expr.append(mdmId);
//			expr.append("'");
		}

		else if (DOMESTIC_MDMLEVEL.equals(mdmLevel)) {
			expr.append(LangUtil.isEmpty(contract.getChlNodeId()) ? " OR " : " AND ");
//			expr.append("EXISTS ([LXK SW Agreement Account Domestic DUNS] = '");
			expr.append("EXISTS ([LXK MPS Domestic DUNS] = '");
			expr.append(mdmId);
			expr.append("')");
			if(LangUtil.isEmpty(contract.getChlNodeId())){
				expr.append(")");
			}
//			expr.append(" AND EXISTS ([LXK MPS CSS Domestic DUNS] ='");
//			expr.append(mdmId);
//			expr.append("'");
		}

		else if (ACCOUNT_MDMLEVEL.equals(mdmLevel)) {
			expr.append(LangUtil.isEmpty(contract.getChlNodeId()) ? " OR " : " AND ");
//			expr.append("EXISTS ([LXK SW Agreement Account MDM Account] = '");
			expr.append("EXISTS ([LXK MPS MDM] = '");
			expr.append(mdmId);
			expr.append("')");
			if(LangUtil.isEmpty(contract.getChlNodeId())){
				expr.append(")");
			}
//			expr.append(" AND EXISTS ([LXK MPS CSS MDM] ='");
//			expr.append(mdmId);
//			expr.append("'");
		}

		else {
			throw new IllegalArgumentException("mdmLevel must be one of 'Global, Legal, Siebel'.  Value was "+ mdmLevel);
		}

		return expr.toString();

	}

	private boolean hasSortCriteria(AssetListContract contract) {
		return (contract.getSortCriteria() != null && !contract
				.getSortCriteria().isEmpty());
	}

	@SuppressWarnings("unchecked")
	private List<AssetBase> queryAssetList(IDataManager dataManager) {
		logger.debug("[IN] queryAssetList(...)");
		logger.debug("[IN] criteria componentSearchSpecMap = "
				+ criteria.getComponentSearchSpecMap());
		logger.debug("[IN] criteria sortingString =" + criteria.getSortString());
		List<AssetBase> assetList = null;
		if (contract.isNewQueryIndicator()) {
			logger.debug("[IN] Device List for Non-bookmarked");
			assetList = dataManager.query(criteria);
			logger.debug("[OUT] Device List for Non-bookmarked");
		} else {
			assetList = dataManager.queryNext(criteria);

		}
		logger.debug("[OUT] queryAssetList(...)");
		return assetList;
	}

	private int processRecordsTotalCount() {
		int count = 0;
		if (contract.isNewQueryIndicator()) {
			logger.debug("[IN] Count Method");
			count = countRecords(contract.isFavoriteFlag());
			crmSessionHandle.setConsumableAssetCount(count);
			logger.debug("[OUT] Count Method");
		} else {
			count = crmSessionHandle.getConsumableAssetCount();
		}

		return count;
	}

	private int countRecords(boolean favoriteFlag) {
		String searchExpression = "";
		String businessObject = DO_CLASS_STANDARD_BO;
		String businessComponent = DO_CLASS_STANDARD_BC;
		if (favoriteFlag) {
			businessObject = DO_CLASS_FAVORITE_BO;
			businessComponent = DO_CLASS_FAVORITE_BC;
			searchExpression = this.favoriteSearchExpression;
		} else {
			searchExpression = this.allAssetSearchExpression;
		}
		SiebelBusinessServiceProxy businessServiceProxy = getTotalCountSession()
				.getSiebelBusServiceProxy();
		return AmindServiceUtil.getTotalCount(businessObject,
				businessComponent, searchExpression, businessServiceProxy);
	}

	/**
	 * @see com.lexmark.service.impl.real.deviceService.AmindContractedDeviceServiceUtil#processFavoriteSet(AssetListContract,
	 *      AmindCrmSessionHandle, IDataManager, Class, Log)
	 */
	@SuppressWarnings("unchecked")
	public <T extends AssetBase> Set<String> processFavoriteSet() {
		Set<String> assetSet = Collections.EMPTY_SET;
		Class doClass = DO_CLASS_FAVORITE;
		QueryObject criteria = new QueryObject(doClass, ActionEvent.QUERY);
		criteria.addComponentSearchSpec(doClass, this.favoriteSearchExpression);

		List<T> favoriteList = getFavoritesSession().getDataManager().query(
				criteria);
		crmSessionHandle.setConsumableAssetFavoriteSet(uniqueAssetIds(LangUtil
				.notNull(favoriteList)));
		assetSet = crmSessionHandle.getConsumableAssetFavoriteSet();

		return assetSet;
	}

	public Set<String> uniqueAssetIds(List<? extends AssetBase> favoriteList) {
		Set<String> favoriteSet = new HashSet<String>();
		for (AssetBase assetBase : favoriteList) {
			favoriteSet.add(assetBase.getAssetId());
		}
		return favoriteSet;
	}

	public Session getTotalCountSession() {
		if (totalCountSession == null) {
			throw new IllegalStateException(
					"total count session has not been set!");
		} else {
			return totalCountSession;
		}
	}

	public void setTotalCountSession(Session totalCountSession) {
		if (totalCountSession == null) {
			throw new IllegalStateException(
					"total count session can not be null!");
		} else {
			this.totalCountSession = totalCountSession;
		}
	}

	public Session getFavoritesSession() {
		if (favoritesSession == null) {
			throw new IllegalStateException(
					"favorites session has not been set!");
		} else {
			return favoritesSession;
		}
	}

	public void setFavoritesSession(Session favoritesSession) {
		this.favoritesSession = favoritesSession;
	}
	
	public void shutDownExecutor() {
    	if (executor!=null && !executor.isShutdown()) {
    		executor.shutdown();
    	}
    }
}
