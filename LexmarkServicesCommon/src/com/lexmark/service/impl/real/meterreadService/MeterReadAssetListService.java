package com.lexmark.service.impl.real.meterreadService;

import static com.lexmark.service.impl.real.meterreadService.AmindContractedMeterReadConversionUtil.convertMeterReadListtoAssetList;
import static com.lexmark.service.impl.real.util.AmindServiceUtil.buildChlNodeExpression;
import static com.lexmark.service.impl.real.util.AmindServiceUtil.buildCriteria;
import static com.lexmark.service.impl.real.util.AmindServiceUtil.buildSearchCriteria;
import static com.lexmark.service.impl.real.util.AmindServiceUtil.buildSortString;
import static com.lexmark.service.impl.real.util.AmindServiceUtil.getParentChainFromCHLNodeId;
import static com.lexmark.util.LangUtil.isEmpty;
import static com.lexmark.util.LangUtil.isNotEmpty;
import static com.lexmark.util.LangUtil.notNull;
import static org.apache.commons.lang.StringUtils.isNotEmpty;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.logging.log4j.LogManager;

import com.amind.data.service.ActionEvent;
import com.amind.data.service.ExecutionMode;
import com.amind.data.service.IDataManager;
import com.amind.data.service.QueryObject;
import com.amind.session.Session;
import com.lexmark.contract.MeterReadAssetListContract;
import com.lexmark.domain.Asset;
import com.lexmark.service.impl.real.domain.AccountAssetFavorites;
import com.lexmark.service.impl.real.domain.CHLDo;
import com.lexmark.service.impl.real.domain.MeterReadAsset;
import com.lexmark.service.impl.real.domain.MeterReadBase;
import com.lexmark.util.LangUtil;
import com.siebel.data.SiebelPropertySet;
import org.apache.logging.log4j.Logger;


public class MeterReadAssetListService {
	
	public static final String GLOBAL_MDMLEVEL = "Global";
	public static final String LEGAL_MDMLEVEL = "Legal";
	public static final String SIEBEL_MDMLEVEL = "Siebel";
	public static final String DOMESTIC_MDMLEVEL = "Domestic";
	public static final String ACCOUNT_MDMLEVEL = "Account";
	
	//private static final Log logger = LogFactory.getLog(MeterReadAssetListService.class);
	private static final Logger logger = LogManager.getLogger(MeterReadAssetListService.class);
	private final MeterReadAssetListContract contract;
	private final Map<String, String> boFieldMap;
	private final Map<String, String> favFieldMap;
	private final boolean favFlag;
	private String searchExpression;
	private QueryObject criteria;
	private String favSearchExpression;
	private Set<String> favoriteSet;
	private Session session;
	private String meterReadType;
	private Set<String> meterReadFavSet;
	private Session chldSession; 
	
	public MeterReadAssetListService(MeterReadAssetListContract contract, Map<String, String> boFieldMap,
			Map<String, String> favFieldMap) {
		if (contract == null) {
			throw new IllegalStateException("contract can not be null");
		}
		this.contract = contract;
		this.favFlag = contract.isFavoriteFlag();
		this.boFieldMap = boFieldMap;
		this.favFieldMap = favFieldMap;
		this.meterReadType = contract.getMeterReadType();
	}

	public void checkRequiredFields() {
		String mdmId = contract.getMdmID();
		if (favFlag) {
			if (mdmId == null) {
				throw new IllegalArgumentException("mdmId is null");
			} else if (mdmId.isEmpty())
				throw new IllegalArgumentException("mdmId is empty");
		}
		String chlNodeId = contract.getChlNodeId();
		if (chlNodeId == null) {
			if (mdmId == null) {
				throw new IllegalArgumentException("mdmId and chlNodeId are null");
			} else if (mdmId.isEmpty()) {
				throw new IllegalArgumentException("mdmId is empty and chlNodeId is null");
			}
		} else if (chlNodeId.isEmpty()) {
			if (mdmId == null) {
				throw new IllegalArgumentException("mdmId is null and chlNodeId is empty");
			} else if (mdmId.isEmpty()) {
				throw new IllegalArgumentException("mdmId and chlNodeId are empty");
			}
		}
		
		if ((isEmpty(chlNodeId) || favFlag) && isNotEmpty(mdmId)) {
			if(isEmpty(contract.getEntitlementEndDate())) {
				throw new IllegalArgumentException("Entitlement end date is null or empty!");
			}
		}
		
	}

	public void buildSearchExpression() {
		IDataManager dataManager = getSession().getDataManager();
		
		searchExpression = buildMeterReadAssetSearchExpression(dataManager, chldSession.getDataManager());
		favSearchExpression = "([Operating Status]= 'New' OR [Operating Status]= 'Created' OR [Operating Status] = 'Active')"
				+ " AND " + buildFavoriteMeterReadAssetSearchExpression();
		criteria = buildMeterReadAssetCriteria();
	}

	// newQuery == true
	// favFlag == false
	public Set<String> queryAndGetFavoriteSet() {
		favoriteSet = new HashSet<String>();
		IDataManager dataManager = getSession().getDataManager();

		List<AccountAssetFavorites> favoriteList = queryFavoriteMeterReadAssetList(dataManager);

		if (favoriteList != null) {
			for (AccountAssetFavorites acDo : favoriteList) {
				favoriteSet.add(acDo.getAssetId());
			}
		}
		return favoriteSet;
	}

	public List<Asset> queryAndGetResultList() {
		IDataManager dataManager = getSession().getDataManager();
		meterReadFavSet = null;
		List<MeterReadBase> meterReadList;
		meterReadList = queryMeterReadAssetList(dataManager);
		if (!favFlag) {
			meterReadFavSet = queryAndGetFavoriteSet();
		}
		return convertMeterReadListtoAssetList(meterReadList, meterReadFavSet);
	
		
	}

	public int processTotalCount() {
		SiebelPropertySet input = new SiebelPropertySet();
		input.setProperty("Search Expression", searchExpression);

		if (favFlag) {
			input.setProperty("Business Component", "LXK SW Contact Favorite Assets_POC");
			input.setProperty("Business Object", "LXK SW Contact Favorite Assets_POC");
		} else {
			input.setProperty("Business Component", "LXK SW Contracted Asset - Service Web");
			input.setProperty("Business Object", "LXK SW Contracted Asset - Service Web");
		}
		SiebelPropertySet output = getSession().getSiebelBusServiceProxy().InvokeMethod(
				"LXK Service Web Utilities", "GetTotalCount", input);

		return Integer.parseInt(output.getProperty("Count"));
	}

	private String buildMeterReadAssetSearchExpression(IDataManager dataManager, IDataManager chldDataManger ) {

		StringBuilder builder = new StringBuilder();
		Map<String, String> fieldMap = favFlag ? favFieldMap : boFieldMap;
		String chlNodeId = contract.getChlNodeId();
		String mdmId = contract.getMdmID();
		String mdmLevel = contract.getMdmLevel();
		int numofDays = contract.getNumofDays();

		if (isNotEmpty(chlNodeId) && !favFlag) {
			logger.debug("ChlNodeId specified, performing search using value: " + chlNodeId);
			builder.append(buildChlNodeExpression(chlNodeId, chldDataManger, false, null));
		} else if (isNotEmpty(mdmId)) {
			
			builder.append("([Operating Status]= 'New' OR [Operating Status]= 'Created' OR [Operating Status] = 'Active')");
			if (contract.isAlliancePartner()) {

				builder.append(buildmdmSearchExpressionAllMeterReads(mdmId,
						mdmLevel));
			} else {
				builder.append(buildmdmSearchExpressionAllMeterReadsnonAlliancePartner(
						mdmId, mdmLevel));
			}
		}

		if (LangUtil.isNotEmpty(meterReadType)) {
			if (meterReadType.equalsIgnoreCase("Missing Manual")) {
				builder.append(" AND [Meter Type] = 'Manual'");
			} else if (meterReadType.equalsIgnoreCase("Missing Automated")) {
				builder.append(" AND [Meter Type] = 'Electronic'");
			} else if (meterReadType.equalsIgnoreCase("Manual")) {
				builder.append(" AND [Meter Type] = 'Manual'");
			} else if (meterReadType.equalsIgnoreCase("Missing All")) {
				builder.append(" AND (([Meter Type] = 'Manual' AND NOT EXISTS ([Timestamp] > Today() - 7)) OR ([Meter Type] = 'Electronic' AND NOT EXISTS ([Timestamp] > Today() - 7)))");
			}
		}

		if (numofDays > 0) {
			if (!meterReadType.equalsIgnoreCase("Missing All")) {
				builder.append(" AND NOT EXISTS ([Timestamp] > Today() - ");
				builder.append(numofDays);
				builder.append(")");
			}
		} else {
			if (meterReadType.equalsIgnoreCase("Missing Manual")) {
				builder.append(" AND NOT EXISTS ([Timestamp] > Today() - 30)");
			} else if (meterReadType.equalsIgnoreCase("Missing Automated")) {
				builder.append(" AND NOT EXISTS ([Timestamp] > Today() - 7)");
			}
		}
	

		

		Map<String, Object> searchCriteria = contract.getSearchCriteria();
		if (isNotEmpty(searchCriteria)) {
			builder.append(buildSearchCriteria(searchCriteria, fieldMap));
			// specially handling chlNodeId (not case insensitive need to query
			// additional data first)
			if (searchCriteria.containsKey("chlNodeId")) {
				chlNodeId = (String) searchCriteria.get("chlNodeId");
				if (isNotEmpty(chlNodeId)) {
					// FIXME (pkozlov) this line looks strange and error-prone
					// I think we need to fix it
					builder.deleteCharAt(builder.length() - 1);
					CHLDo chl = getParentChainFromCHLNodeId(chlNodeId, chldDataManger);

					if (chl != null) {
						String parentChain = chl.getParentChain();

						if (isNotEmpty(parentChain)) {
							builder.append(" OR  EXISTS ( [LXK SW Covered Asset CHL Parent Chain] LIKE '");
							builder.append(parentChain);
							builder.append("*' ))");
						} else {
							logger.warn("No Parent Chain found for CHL Node ID: " + chlNodeId);
						}
					}
				}
			}
		}

		Map<String, Object> filterCriteria = contract.getFilterCriteria();
		if (isNotEmpty(filterCriteria)) {
			
			if(filterCriteria.containsKey("installAddress.province")) {
				String fieldName = fieldMap.get("installAddress.province");
				String value = (String) filterCriteria.get("installAddress.province");
				
				builder.append(" AND [");
				builder.append(fieldName);
				builder.append("] = '");
				builder.append(value);
				builder.append("'");
				
				filterCriteria.remove("installAddress.province");
			}
			
			builder.append(buildCriteria(filterCriteria, fieldMap, true, true));
		}

//		builder.append(" AND [Meter Type] = 'Manual'");

		return builder.toString();
	}

	private String buildFavoriteMeterReadAssetSearchExpression() {
		StringBuilder builder = new StringBuilder("[Contact Id] = '");
		builder.append(contract.getContactId());
		builder.append("'");
		builder.append(" AND [LXK SW Asset Favorite Flag] = 'Y'");
		
		builder.append(buildCriteria(contract.getFilterCriteria(), favFieldMap, true, true)); //Filter
		
		builder.append(buildmdmSearchExpressionFavorites(contract.getMdmID(),contract.getMdmLevel()));
		return builder.toString();
	}

	private QueryObject buildMeterReadAssetCriteria() {

		Map<String, String> fieldMap = favFlag ? favFieldMap : boFieldMap;
		Class<?> doClass = favFlag ? AccountAssetFavorites.class : MeterReadAsset.class;
		searchExpression = favFlag ? favSearchExpression : searchExpression;

		QueryObject criteria = new QueryObject(doClass, ActionEvent.QUERY);
		criteria.addComponentSearchSpec(doClass, searchExpression);
		criteria.setStartRowIndex(contract.getStartRecordNumber());
		criteria.setNumRows(contract.getIncrement());
		criteria.setExecutionMode(ExecutionMode.BiDirectional);

		Map<String, Object> sortCriteria = contract.getSortCriteria();
		if (isNotEmpty(sortCriteria)) {
			criteria.setSortString(buildSortString(sortCriteria, fieldMap));
		}

		return criteria;
	}

	@SuppressWarnings("unchecked")
	private List<AccountAssetFavorites> queryFavoriteMeterReadAssetList(IDataManager dataManager) {
		QueryObject queryObject = new QueryObject(AccountAssetFavorites.class,ActionEvent.QUERY);
		queryObject.addComponentSearchSpec(AccountAssetFavorites.class, favSearchExpression);
		List<AccountAssetFavorites> favoriteList = dataManager.query(queryObject);
		return notNull(favoriteList);
	}

	@SuppressWarnings("unchecked")
	private List<MeterReadBase> queryMeterReadAssetList(IDataManager dataManager) {
		List<MeterReadBase> meterReadList;
	
		if (contract.isNewQueryIndicator()) {
			meterReadList = dataManager.query(criteria);
		} else {
			meterReadList = dataManager.query(criteria);  //changed queryNext to query as Query Next is not pulling any data. Defect 16830 
		}
		return notNull(meterReadList);
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

	public Session getChldSession() {
		if (chldSession == null) {
			throw new IllegalStateException("chldSession has not set!");
		} else {
			return chldSession;
		}
		//return chldSession;
	}

	public void setChldSession(Session chldSession) {
		if (chldSession == null) {
			throw new IllegalArgumentException("chldSession can not be null!");
		} else {
			this.chldSession = chldSession;
		}
		//this.chldSession = chldSession;
	}

		public Set<String> getFavoriteSet() {
		return favoriteSet;
	}

	public void setFavoriteSet(Set<String> favoriteSet) {
		this.favoriteSet = favoriteSet;
	}
	
	private Object buildmdmSearchExpressionFavorites(String mdmId,String mdmLevel) {

		StringBuilder expr = new StringBuilder();

		if (SIEBEL_MDMLEVEL.equals(mdmLevel)) {
			{
				expr.append(" AND (EXISTS (");
				expr.append(" [LXK SW Agreement Account Id] = '");
				expr.append(mdmId + "') ");
				expr.append(" OR EXISTS (");
				expr.append("([LXK MPS CSS Account Id] ='");
				expr.append(mdmId + "')");
				expr.append(" AND [LXK MPS CSS Ent Type] <> 'Warranty' AND [LXK MPS CSS Ent End Date] >= '");
				expr.append(contract.getEntitlementEndDate());
				expr.append("')");
				expr.append(" OR EXISTS ( [LXK MPS Account Id] = '");
				expr.append(mdmId + "'))");
			}
		}

		else if (LEGAL_MDMLEVEL.equals(mdmLevel)) {
			{
				expr.append(" AND (EXISTS (");
				expr.append(" ([LXK SW Agreement Account LEGAL MDM ID] = '");
				expr.append(mdmId + "')) ");
				expr.append(" OR EXISTS (");
				expr.append("[LXK MPS CSS Legal] ='");
				expr.append(mdmId + "'");
				expr.append(" AND [LXK MPS CSS Ent Type] <> 'Warranty' AND [LXK MPS CSS Ent End Date] >= '");
				expr.append(contract.getEntitlementEndDate());
				expr.append("')");
				expr.append(" OR EXISTS ([LXK MPS Legal] = '");
				expr.append(mdmId + "' ) )");
			}
		}

		else if (GLOBAL_MDMLEVEL.equals(mdmLevel)) {
			{
				expr.append(" AND (EXISTS (");
				expr.append(" ([LXK MPS Ent Global DUNS] = '");
				expr.append(mdmId + "') ");
				expr.append(" ) OR EXISTS ([LXK SW Agreement Account Global DUNS] = '");
				expr.append(mdmId + "' ) ");
				expr.append(" OR EXISTS ( ([LXK MPS CSS Global DUNS] = '");
				expr.append(mdmId + "')");
				expr.append(" AND [LXK MPS CSS Ent Type] <> 'Warranty' AND [LXK MPS CSS Ent End Date] >= '");
				expr.append(contract.getEntitlementEndDate() + "'");
				expr.append(" ) )");
			}
		} 
		
		else if(DOMESTIC_MDMLEVEL.equalsIgnoreCase(mdmLevel)) {
			expr.append(" AND (EXISTS (");
			expr.append("([LXK MPS Domestic DUNS] = '");
			expr.append(mdmId + "'))");
			expr.append(" OR EXISTS (");
			expr.append("[LXK SW Agreement Account Domestic DUNS] ='");
			expr.append(mdmId + "')");
			expr.append(" OR EXISTS (([LXK MPS CSS Domestic DUNS] = '");
			expr.append(mdmId);
			expr.append("')");
			expr.append(" AND [LXK MPS CSS Ent Type] <> 'Warranty'");
			expr.append(" AND [LXK MPS CSS Ent End Date] >= '");
			expr.append(contract.getEntitlementEndDate());
			expr.append("'))");
		}
		
		else if(ACCOUNT_MDMLEVEL.equalsIgnoreCase(mdmLevel)) {
			expr.append(" AND (EXISTS (");
			expr.append("([LXK SW Agreement Account MDM Account] = '");
			expr.append(mdmId + "'))");
			expr.append(" OR EXISTS (");
			expr.append("[LXK MPS CSS MDM] ='");
			expr.append(mdmId + "'");
			expr.append(" AND [LXK MPS CSS Ent Type] <> 'Warranty' AND [LXK MPS CSS Ent End Date] >= '");
			expr.append(contract.getEntitlementEndDate());
			expr.append("')");
			expr.append(" OR EXISTS ([LXK MPS MDM] = '");
			expr.append(mdmId);
			expr.append("'))");
		}
		
		else {
			throw new IllegalArgumentException("mdmLevel must be one of 'Global, Domestic, Legal, Account or Siebel'.  Value was " + mdmLevel);
		}

		return expr.toString();
	}

//builder.append(buildmdmSearchExpressionAllMeterReads(mdmId, mdmLevel));
	private Object buildmdmSearchExpressionAllMeterReadsnonAlliancePartner(String mdmId,String mdmLevel) {

		StringBuilder expr = new StringBuilder();

		if (SIEBEL_MDMLEVEL.equals(mdmLevel)) {
			{
				expr.append(" AND ");
				expr.append(" [Owner Account Id] = '");
				expr.append(mdmId + "'");
				
				
				
			}
		}

		else if (LEGAL_MDMLEVEL.equals(mdmLevel)) {
			expr.append(" AND ");
			expr.append(" [LXM MDM Legal Entity ID #] = '");
			expr.append(mdmId + "'");
			
		}

		else if (GLOBAL_MDMLEVEL.equals(mdmLevel)) {
			{
				expr.append(" AND ");
				expr.append(" [LXK Account Global DUNS Number] = '");
				expr.append(mdmId + "'");
				
			}
		} 
		
		else if(DOMESTIC_MDMLEVEL.equalsIgnoreCase(mdmLevel)) {
			expr.append(" AND ");
			expr.append(" [LXK Account Domestic DUNS Number] = '");
			expr.append(mdmId + "'");
			
		}
		
		else if(ACCOUNT_MDMLEVEL.equalsIgnoreCase(mdmLevel)) {
			expr.append(" AND ");
			expr.append(" [LXM MDM Account #] = '");
			expr.append(mdmId + "'");
			
		}
		
		else {
			throw new IllegalArgumentException("mdmLevel must be one of 'Global, Domestic, Legal, Account or Siebel'.  Value was " + mdmLevel);
		}
		expr.append(" AND");
		expr.append("([LXK MPS Agree Status] ='");
		expr.append("Active'");
		expr.append(" OR [LXK MPS Agree Status] = 'Current'");
		expr.append(")");
		expr.append("AND [LXK MPS Asset Life Cycle] <> 'Unassigned'");
		return expr.toString();
	}

	private Object buildmdmSearchExpressionAllMeterReads(String mdmId,String mdmLevel) {

		StringBuilder expr = new StringBuilder();

		if (SIEBEL_MDMLEVEL.equals(mdmLevel)) {
			{
				expr.append(" AND (EXISTS (");
				expr.append(" [LXK SW Agreement Account Id] = '");
				expr.append(mdmId + "') ");
				expr.append(" OR EXISTS (");
				expr.append("([LXK MPS CSS Account Id] ='");
				expr.append(mdmId + "')");
				expr.append(" AND [LXK MPS CSS Entitlement Type] <> 'Warranty' AND [LXK MPS CSS Entitlement End Date] >= '");
				expr.append(contract.getEntitlementEndDate());
				expr.append("')");
				expr.append(" OR EXISTS ( [LXK MPS Account Id] = '");
				expr.append(mdmId + "'))");
			}
		}

		else if (LEGAL_MDMLEVEL.equals(mdmLevel)) {
			{
				expr.append(" AND (EXISTS (");
				expr.append(" ([LXK SW Agreement Account LEGAL MDM ID] = '");
				expr.append(mdmId + "')) ");
				expr.append(" OR EXISTS (");
				expr.append("[LXK MPS CSS Legal] ='");
				expr.append(mdmId + "'");
				expr.append(" AND [LXK MPS CSS Entitlement Type] <> 'Warranty' AND [LXK MPS CSS Entitlement End Date] >= '");
				expr.append(contract.getEntitlementEndDate());
				expr.append("')");
				expr.append(" OR EXISTS ([LXK MPS Legal] = '");
				expr.append(mdmId + "' ) )");
			}
		}

		else if (GLOBAL_MDMLEVEL.equals(mdmLevel)) {
			{
				expr.append(" AND (EXISTS (");
				expr.append(" ([LXK MPS Ent Global DUNS] = '");
				expr.append(mdmId + "') ");
				expr.append(" ) OR EXISTS ([LXK SW Agreement Account Global DUNS] = '");
				expr.append(mdmId + "' ) ");
				expr.append(" OR EXISTS ( ([LXK MPS CSS Global Duns] = '");
				expr.append(mdmId + "')");
				expr.append(" AND [LXK MPS CSS Entitlement Type] <> 'Warranty' AND [LXK MPS CSS Entitlement End Date] >= '");
				expr.append(contract.getEntitlementEndDate() + "'");
				expr.append(" ) )");
			}
		} 
		
		else if(DOMESTIC_MDMLEVEL.equalsIgnoreCase(mdmLevel)) {
			expr.append(" AND (EXISTS (");
			expr.append("([LXK MPS Domestic DUNS] = '");
			expr.append(mdmId + "'))");
			expr.append(" OR EXISTS (");
			expr.append("[LXK SW Agreement Account Domestic DUNS] ='");
			expr.append(mdmId + "')");
			expr.append(" OR EXISTS (([LXK MPS CSS Domestic Duns] = '");
			expr.append(mdmId);
			expr.append("')");
			expr.append(" AND [LXK MPS CSS Entitlement Type] <> 'Warranty'");
			expr.append(" AND [LXK MPS CSS Entitlement End Date] >= '");
			expr.append(contract.getEntitlementEndDate());
			expr.append("'))");
		}
		
		else if(ACCOUNT_MDMLEVEL.equalsIgnoreCase(mdmLevel)) {
			expr.append(" AND (EXISTS (");
			expr.append("([LXK SW Agreement Account MDM Account] = '");
			expr.append(mdmId + "'))");
			expr.append(" OR EXISTS (");
			expr.append("[LXK MPS CSS MDM] ='");
			expr.append(mdmId + "'");
			expr.append(" AND [LXK MPS CSS Entitlement Type] <> 'Warranty' AND [LXK MPS CSS Entitlement End Date] >= '");
			expr.append(contract.getEntitlementEndDate());
			expr.append("')");
			expr.append(" OR EXISTS ([LXK MPS MDM] = '");
			expr.append(mdmId);
			expr.append("'))");
		}
		
		else {
			throw new IllegalArgumentException("mdmLevel must be one of 'Global, Domestic, Legal, Account or Siebel'.  Value was " + mdmLevel);
		}

		return expr.toString();
	}

	public Set<String> getMeterReadFavSet() {
		return meterReadFavSet;
	}

	public void setMeterReadFavSet(Set<String> meterReadFavSet) {
		this.meterReadFavSet = meterReadFavSet;
	}
	
	
	
}
