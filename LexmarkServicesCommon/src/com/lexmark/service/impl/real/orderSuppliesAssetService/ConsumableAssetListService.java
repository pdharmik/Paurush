package com.lexmark.service.impl.real.orderSuppliesAssetService;

import static com.lexmark.service.impl.real.deviceService.AmindContractedDeviceDataConversionUtil.convertAssetDoToAssetList;
import static com.lexmark.service.impl.real.orderSuppliesAssetService.OrderSuppliesAssetSiebelFieldMap.accountvaluedFieldMap;
import static com.lexmark.service.impl.real.orderSuppliesAssetService.OrderSuppliesAssetSiebelFieldMap.allFavoriteFieldMap;
import static com.lexmark.service.impl.real.orderSuppliesAssetService.OrderSuppliesAssetSiebelFieldMap.allFieldMap;
import static com.lexmark.service.impl.real.orderSuppliesAssetService.OrderSuppliesAssetSiebelFieldMap.contractedvaluedFieldMap;
import static com.lexmark.service.impl.real.orderSuppliesAssetService.OrderSuppliesAssetSiebelFieldMap.favoriteAccountvaluedFieldMap;
import static com.lexmark.service.impl.real.orderSuppliesAssetService.OrderSuppliesAssetSiebelFieldMap.favoriteContractedvaluedFieldMap;
import static com.lexmark.util.LangUtil.isEmpty;
import static com.lexmark.util.LangUtil.isNotEmpty;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.logging.log4j.LogManager;

import com.amind.common.service.SiebelBusinessServiceProxy;
import com.amind.data.service.ActionEvent;
import com.amind.data.service.QueryObject;
import com.amind.session.Session;
import com.lexmark.contract.AssetListContract;
import com.lexmark.result.AssetListResult;
import com.lexmark.service.api.SiebelCrmServiceException;
import com.lexmark.service.impl.real.AmindCrmSessionHandle;
import com.lexmark.service.impl.real.domain.AccountAssetFavorites;
import com.lexmark.service.impl.real.domain.AssetBase;
import com.lexmark.service.impl.real.domain.CHLDo;
import com.lexmark.service.impl.real.domain.ConsumableAssetBase;
import com.lexmark.service.impl.real.util.AmindServiceUtil;
import com.lexmark.util.LangUtil;
import org.apache.logging.log4j.Logger;


public class ConsumableAssetListService {

	//private static Log logger = LogFactory.getLog(AssetListService.class);
	private static Logger logger = LogManager.getLogger(AssetListService.class);
	private static final Class<?> DO_CLASS = ConsumableAssetBase.class;
	private static final Class<?> FAVORITE_DO_CLASS = AccountAssetFavorites.class;
	private static final Map<String, String> ACCOUNT_VALUED_FIELD_MAP = accountvaluedFieldMap();
	private static final Map<String, String> CONTRACTED_VALUED_FIELD_MAP = contractedvaluedFieldMap();
	private static final Map<String, String> ALL_FIELD_MAP = allFieldMap();
	private static final Map<String, String> FAVORITE_FIELD_MAP = allFavoriteFieldMap();
	private static final Map<String, String> FAVORITE_ACCOUNT_VALUED_FIELD_MAP = favoriteAccountvaluedFieldMap();
	private static final Map<String, String> FAVORITE_CONTRACTED_VALUED_FIELD_MAP = favoriteContractedvaluedFieldMap();

	private Session session;
	private AmindCrmSessionHandle crmSessionHandle;
	private AssetListContract contract;
	private boolean favoriteFlag;

	public ConsumableAssetListService(Session session,
			AmindCrmSessionHandle crmSessionHandle, AssetListContract contract) {
		this.session = session;
		this.crmSessionHandle = crmSessionHandle;
		this.contract = contract;
	}

	public AssetListResult retrieveConsumableAssetList() {
		logger.debug("[IN] retrieveConsumableAssetList");
		// checkRequiredFields(contract);

		AssetListResult assetListResult = new AssetListResult();
		favoriteFlag = contract.isFavoriteFlag();
		Set<String> favoriteSet = new HashSet<String>();

		try

		{
			// String searchExpression = buildAssetSearchExpression();
			String searchExpression = search();
			QueryObject criteria = buildQueryObject(searchExpression);
			List<ConsumableAssetBase> assetList = queryAndGetAssetList(criteria);
			 int count = processRecordsTotalCount(searchExpression);
			 logger.debug("total Count:" + count);
			/*
			 * if(!favoriteFlag) { favoriteSet = processFavoriteSet(); }
			 */

			// assetListResult.setTotalCount(count);
			// assetListResult.setAccountAssets(convertAssetDoToAssetList(assetList,
			// favoriteSet));
		} finally {
			logger.debug("[OUT] retrieveConsumableAssetList");
		}
		return assetListResult;
	}

	private String search() {
		StringBuilder expr = new StringBuilder();

	    expr.append("  ([Operating Status]= 'New' OR [Operating Status]= 'Created' OR  [Operating Status] = 'Active' ) AND " +
      "  ([LXK Account Domestic DUNS Number] = '315000554' )" +"  AND "+
      " EXISTS ( [LXK SW Entitlement Type] <> 'Warranty' AND [LXK MPS Entitlement End Date] >= '10/27/2012 05:30:00')");
		return expr.toString();
	}

	private QueryObject buildQueryObject(String searchExpression) {
		QueryObject criteria = null;
		if (favoriteFlag) {
			criteria = new QueryObject(FAVORITE_DO_CLASS, ActionEvent.QUERY);
			criteria.addComponentSearchSpec(FAVORITE_DO_CLASS, searchExpression);
			criteria.setSortString(AmindServiceUtil.buildSortString(
					contract.getSortCriteria(), FAVORITE_FIELD_MAP));
		} else {
			criteria = new QueryObject(DO_CLASS, ActionEvent.QUERY);
			criteria.addComponentSearchSpec(DO_CLASS, searchExpression);
			criteria.setSortString(AmindServiceUtil.buildSortString(
					contract.getSortCriteria(), ALL_FIELD_MAP));
		}

		AmindServiceUtil.buildBasicQueryObject(criteria,
				contract.getIncrement(), contract.getStartRecordNumber());

		return criteria;
	}

	private String buildAssetSearchExpression() {

		StringBuilder expr = new StringBuilder();
		expr.append("(:status= 'New' OR :status= 'Created' OR :status = 'Active')"
				.replaceAll(":status", "[Operating Status]"));
		expr.append(filterCriteriaExpr());
		expr.append(searchCriteriaExpr());
		if (favoriteFlag) {
			expr.append(" AND (");
			expr.append(favoriteListSearchExpression());
			expr.append(")");
			expr.append(" AND (");
			expr.append(buildSearchExpressionForMDMFavorite());
			expr.append(")");
		} else if (isSearchByChlNodeId()) {
			expr.append(buildCHLNodeExpression());

		} else if (isSearchByMdmId()) {
			expr.append(" AND ");
			expr.append("((");
			expr.append(buildSearchExpressionForMDM());
			expr.append("))");
		} else {
			throw new SiebelCrmServiceException(
					"No Contact Id, CHLNodeId or mdmId specified for retrieveDeviceList");
		}

		return expr.toString();
	}

	private StringBuilder buildCHLNodeExpression() {
		CHLDo chl = AmindServiceUtil.getParentChainFromCHLNodeId(
				contract.getChlNodeId(), session.getDataManager());
		StringBuilder expr = new StringBuilder();
		if (chl == null) {
			throw new SiebelCrmServiceException("Chl Domian Object is null");
		}
		String parentChain = chl.getParentChain();
		if (isEmpty(parentChain)) {
			throw new SiebelCrmServiceException(
					"No Parent Chain found using CHL Node ID: "
							+ contract.getChlNodeId());

		}

		expr.append(" AND ");
		String topLevelAccountId = chl.getTopLevelAccountId();
		if (isNotEmpty(topLevelAccountId)) {
			expr.append("[Owner Account Id]");
			expr.append("='");
			expr.append(topLevelAccountId);
			expr.append("'");
			expr.append(" AND ");
		}

		expr.append("EXISTS ((");
		expr.append("[LXK SW Covered Asset CHL Parent Chain]");
		expr.append(" LIKE '");
		expr.append(parentChain);
		expr.append("*' )");

		expr.append(" OR ");
		expr.append("EXISTS (");
		expr.append("[LXK MPS Parent Chain]");
		expr.append(" LIKE '");
		expr.append(parentChain);
		expr.append("*'");
		expr.append(" AND ( [LXK SW Entitlement Type] <> 'Warranty' "
				+ "AND [LXK MPS Entitlement End Date] >= '"
				+ contract.getEntitlementEndDate() + "')))");
		return expr;
	}

	@SuppressWarnings("unchecked")
	private List<ConsumableAssetBase> queryAndGetAssetList(QueryObject criteria) {
		List<ConsumableAssetBase> assetList = null;
		if (contract.isNewQueryIndicator()) {
			assetList = session.getDataManager().query(criteria);
		} else {
			assetList = session.getDataManager().queryNext(criteria);
		}
		return assetList;
	}

	private String buildSearchExpressionForMDM() {
		StringBuilder searchSpec = new StringBuilder();
		searchSpec.append("EXISTS (");
		// Contracted
		searchSpec.append(AmindServiceUtil.buildMdmSearchExpressionForMdmLevel(
				contract.getMdmId(), contract.getMdmLevel(),
				CONTRACTED_VALUED_FIELD_MAP, false,
				"LXK SW Agreement Account MDM Level"));
		searchSpec.append(")  ");
		searchSpec.append("OR ");
		searchSpec.append("((");
		// Entitled
		searchSpec
				.append("EXISTS ([LXK SW Entitlement Type] <> 'Warranty' AND [LXK MPS Entitlement End Date] >= '"
						+ contract.getEntitlementEndDate() + "'");
		searchSpec.append("  AND  ");
		searchSpec.append(AmindServiceUtil.buildMdmSearchExpressionForMdmLevel(
				contract.getMdmId(), contract.getMdmLevel(),
				ACCOUNT_VALUED_FIELD_MAP, false, "LXK MPS Ent Account Level"));
		searchSpec.append(")))");
		return searchSpec.toString();
	}

	private String buildSearchExpressionForMDMFavorite() {
		StringBuilder searchSpec = new StringBuilder();
		searchSpec.append(" EXISTS (");
		// Contracted
		searchSpec.append(AmindServiceUtil.buildMdmSearchExpressionForMdmLevel(
				contract.getMdmId(), contract.getMdmLevel(),
				FAVORITE_CONTRACTED_VALUED_FIELD_MAP, false,
				"LXK SW Agreement Account MDM Level"));
		searchSpec.append(") ");
		searchSpec.append("OR ");
		searchSpec.append("((");
		searchSpec
				.append("EXISTS ([LXK MPS Ent Type] <> 'Warranty' AND [LXK MPS Ent End Date] >= '"
						+ contract.getEntitlementEndDate() + "'");
		searchSpec.append("  AND  ");
		searchSpec.append(AmindServiceUtil.buildMdmSearchExpressionForMdmLevel(
				contract.getMdmId(), contract.getMdmLevel(),
				FAVORITE_ACCOUNT_VALUED_FIELD_MAP, false,
				"LXK MPS Ent MDM Level"));
		searchSpec.append(")))");
		return searchSpec.toString();
	}

	private int processRecordsTotalCount(String searchExpression) {
		int count = 0;
		if (contract.isNewQueryIndicator()) {
			logger.debug("[IN] Count Method");
			count = countRecords(session, searchExpression);
			crmSessionHandle.setDeviceCount(count);
			logger.debug("[OUT] Count Method");
		} else {
			count = crmSessionHandle.getDeviceCount();
		}

		return count;
	}

	private int countRecords(Session session, String searchExpression) {
		String businessObject = "LXK MPS Contracted Consumable Asset List_POC";
		String businessComponent = "LXK MPS Contracted Consumable Asset List_POC";
		String favoriteBusinessObject = "LXK SW Contact Favorite Assets";
		String favoriteBusinessComponent = "LXK SW Contact Favorite Assets";

		SiebelBusinessServiceProxy businessServiceProxy = session
				.getSiebelBusServiceProxy();
		int count = 0;
		if (favoriteFlag) {
			count = AmindServiceUtil.getTotalCount(favoriteBusinessObject,
					favoriteBusinessComponent, searchExpression,
					businessServiceProxy);
		} else {
			count = AmindServiceUtil.getTotalCount(businessObject,
					businessComponent, searchExpression, businessServiceProxy);
		}
		return count;

	}

	private boolean isSearchByMdmId() {
		return isNotEmpty(contract.getMdmId());
	}

	private boolean isSearchByChlNodeId() {
		return isNotEmpty(contract.getChlNodeId());
	}

	public String favoriteListSearchExpression() {

		String s = String.format(
				"[LXK SW Asset Favorite Flag] = 'Y' AND [Contact Id] = '%s'",
				contract.getContactId());
		return s;
	}

	private String searchCriteriaExpr() {
		String searchSpec = "";
		if (isNotEmpty(contract.getSearchCriteria())) {
			searchSpec = contract.isFavoriteFlag() ? AmindServiceUtil
					.buildSearchCriteria(contract.getSearchCriteria(),
							FAVORITE_FIELD_MAP) : AmindServiceUtil
					.buildSearchCriteria(contract.getSearchCriteria(),
							ALL_FIELD_MAP);
		}

		return searchSpec;
	}

	private String filterCriteriaExpr() {
		String filterSearchSpec = "";
		if (isNotEmpty(contract.getFilterCriteria())) {
			filterSearchSpec = favoriteFlag ? AmindServiceUtil.buildCriteria(
					contract.getFilterCriteria(), FAVORITE_FIELD_MAP, true,
					true) : AmindServiceUtil.buildCriteria(
					contract.getFilterCriteria(), ALL_FIELD_MAP, true, true);
		}
		return filterSearchSpec;

	}

	private static void checkRequiredFields(AssetListContract contract) {
		if (contract == null) {
			throw new IllegalArgumentException("Contract is null");
		}

		String contactId = contract.getContactId();
		if (contactId == null) {
			throw new IllegalArgumentException("Contact Id is null");
		} else if (contactId.isEmpty()) {
			throw new IllegalArgumentException("Contact Id is Empty");
		}

		if (!contract.isFavoriteFlag()) {
			String mdmId = contract.getMdmId();
			if (mdmId == null) {
				throw new IllegalArgumentException("MDM Id is null");
			} else if (mdmId.isEmpty()) {
				throw new IllegalArgumentException("MDM Id is empty");
			}
			String mdmLevel = contract.getMdmLevel();
			if (mdmLevel == null) {
				throw new IllegalArgumentException("MDM Level is null");
			} else if (mdmLevel.isEmpty()) {
				throw new IllegalArgumentException("MDM Level is empty");
			}
		}

		if (isEmpty(contract.getChlNodeId())
				&& isEmpty(contract.getEntitlementEndDate())) {
			throw new IllegalArgumentException(
					"[mdm-search] EntitlementEndDate is null or empty");
		}
	}

	// public static List<Asset> convertConsumableAssetDoToAssetList(List<?
	// extends AssetBase> assetBaseList, Set<String> favoriteSet) {
	// List<Asset> portalAssetList = new ArrayList<Asset>();
	// for (ConsumableAssetBase assetBase : LangUtil.notNull(assetBaseList)) {
	// Asset asset = toAsset(assetBase, favoriteSet);
	// portalAssetList.add(asset);
	// }
	// // }
	//
	// return portalAssetList;
	// }
	//
	// private static Asset toAsset(AssetBase assetBase, Set<String>
	// favoriteSet) {
	// Asset asset = new Asset();
	// asset.setAssetId(assetBase.getAssetId());
	// asset.setAssetTag(assetBase.getAssetTag());
	// asset.setDeviceTag(assetBase.getDeviceTagCustomer());
	// asset.setHostName(assetBase.getHostName());
	// asset.setIpAddress(assetBase.getIpAddress());
	// asset.setProductLine(assetBase.getProductLine());
	// asset.setProductTLI(assetBase.getProductTLI());
	// asset.setSerialNumber(assetBase.getSerialNumber());
	// asset.setModelNumber(assetBase.getMachineTypeModel());
	// asset.setDevicePhase(assetBase.getDevicePhase());
	// asset.setMacAddress(assetBase.getMacAddress());
	//
	// populateInstallAddress(asset, assetBase);
	//
	// return asset;
	// }
	//
	// public static void populateInstallAddress(Asset asset,
	// ConsumableAssetBase assetBase) {
	// GenericAddress installAddress = new GenericAddress();
	// installAddress.setAddressId(assetBase.getAddressId());
	// installAddress.setAddressName(assetBase.getAddressName());
	// installAddress.setAddressLine1(assetBase.getAddress1());
	// installAddress.setAddressLine2(assetBase.getAddress2());
	// installAddress.setAddressLine3(assetBase.getAddress3());
	// installAddress.setCity(assetBase.getInstallCity());
	// installAddress.setState(assetBase.getInstallState());
	// installAddress.setCountry(assetBase.getInstallCountry());
	// installAddress.setPostalCode(assetBase.getInstallPostalCode());
	// installAddress.setProvince(assetBase.getInstallProvince());
	//
	// // installAddress.setStoreFrontName(assetBase.getStoreFrontName());
	// asset.setInstallAddress(installAddress);
	// }

}
