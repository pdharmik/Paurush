package com.lexmark.service.impl.real.addressService;

import static com.lexmark.service.impl.real.addressService.AmindAddressConversionUtil.convertAddressDoListToGenericAddress;
import static com.lexmark.service.impl.real.addressService.AmindAddressConversionUtil.convertLBSAddress;
import static com.lexmark.service.impl.real.addressService.AmindAddressConversionUtil.convertLBSBuildingTypeList;
import static com.lexmark.service.impl.real.addressService.AmindAddressConversionUtil.convertLBSLocation;
import static com.lexmark.service.impl.real.addressService.AmindAddressConversionUtil.convertLBSLocationForTwoCalls;
import static com.lexmark.service.impl.real.util.AmindServiceUtil.buildBasicQueryObject;
import static com.lexmark.service.impl.real.util.AmindServiceUtil.buildCriteria;
import static com.lexmark.service.impl.real.util.AmindServiceUtil.buildSortString;
import static com.lexmark.service.impl.real.util.AmindServiceUtil.buildmdmSearchExpression;
import static com.lexmark.util.LangUtil.isNotEmpty;
import static com.lexmark.util.LangUtil.notNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.amind.common.service.SiebelBusinessServiceProxy;
import com.amind.data.service.ActionEvent;
import com.amind.data.service.IDataManager;
import com.amind.data.service.QueryObject;
import com.amind.session.Session;
import com.lexmark.contract.AddressListContract;
import com.lexmark.domain.GenericAddress;
import com.lexmark.result.AddressListResult;
import com.lexmark.service.impl.real.domain.AddressDo;
import com.lexmark.service.impl.real.domain.FavoriteAddressDo;
import com.lexmark.service.impl.real.domain.LBSAddress;
import com.lexmark.service.impl.real.domain.LBSLocationBuildingType;
import com.lexmark.service.impl.real.domain.LBSLocationDo;
import com.lexmark.service.impl.real.util.AmindServiceUtil;
import com.lexmark.util.LangUtil;

public class AddressListService {
	private final AddressListContract contract;
	private final boolean favFlag;
	private final boolean cleanseAddress;
	private final Map<String,String> fieldMap;
	private final Map<String,String> lbsFieldMap;
	private Session session;
	private String favSearchExpression;
	private String allSearchExpression;
	private QueryObject favCriteria;
	private QueryObject allCriteria;
	private QueryObject lbsCriteria;
	private QueryObject lbsLocationCriteria;
	private QueryObject lbsLocationCriteriaSecondCall;
	private QueryObject lbsLocationCriteriaThirdCall;
	private Session favoritesSession;
	private String lbsQueryType;

	public AddressListService(AddressListContract contract, Map<String, String> addressFieldMap, Map<String, String> lbsFieldMap) {
		if (contract == null) {
			throw new IllegalStateException("contract can not be null!");
		}
		this.contract = contract;
		this.cleanseAddress = contract.isCleansedAddress();
		this.favFlag = contract.isFavoriteFlag();
		this.fieldMap = addressFieldMap;
		this.lbsFieldMap = lbsFieldMap;
	}
	
	public void checkRequiredFields() {
		checkFavoriteAddressFields();
		if (!favFlag) {
			checkAllAddressFields();
		}
	}

	public void buildSearchExpression() {
		favSearchExpression = buildFavoriteAddressSearchExpression();
		if (!favFlag) {
			allSearchExpression = buildAllAddressSearchExpression();
			allCriteria = buildAddressCriteria(allSearchExpression, AddressDo.class, true);
			favCriteria = buildAddressCriteria(favSearchExpression, FavoriteAddressDo.class, false);
		} else {
			favCriteria = buildAddressCriteria(favSearchExpression, FavoriteAddressDo.class, true);
		}
	}

	public List<GenericAddress> queryAndGetResultList() {
		IDataManager dataManager = getSession().getDataManager();
		List<GenericAddress> addressList = new ArrayList<GenericAddress>();
		if (favFlag) {
			List<FavoriteAddressDo> favAddressDoList = queryFavoriteAddressList(dataManager);
			addressList = convertAddressDoListToGenericAddress(favAddressDoList, null);
		} else {
			List<FavoriteAddressDo> favAddressDoList = queryFavoriteAddressList(getFavoritesSession().getDataManager());
			List<AddressDo> addressDoList = queryAllAddressList(dataManager);
			addressList =  convertAddressDoListToGenericAddress(addressDoList, favAddressDoList);
		}
		return addressList;
	}
	
	public int processTotalCount() {
		SiebelBusinessServiceProxy proxy = getSession().getSiebelBusServiceProxy();
		int totalCount = 0;
		if (favFlag) {
			totalCount = AmindServiceUtil.getBCTotalCount("LXK SW Contact Favorite Addresses", "LXK SW Contact Favorite Addresses",
					favSearchExpression, proxy);
		} else {
			totalCount = AmindServiceUtil.getBCTotalCount("LXK Service Address - Service Web", "LXK Service Address - Service Web",
					allSearchExpression, proxy);
		}
		return totalCount;
	}

	@SuppressWarnings("unchecked")
	private List<FavoriteAddressDo> queryFavoriteAddressList(IDataManager dataManager) {
		List<FavoriteAddressDo> favoriteAddressDoList;

		if (contract.isNewQueryIndicator()) {
			favoriteAddressDoList = dataManager.query(favCriteria);
		} else {
			favoriteAddressDoList = dataManager.queryNext(favCriteria);
		}
		return notNull(favoriteAddressDoList);
	}
	
	@SuppressWarnings("unchecked")
	private List<AddressDo> queryAllAddressList(IDataManager dataManager) {
		List<AddressDo> addressDoList;

		if (contract.isNewQueryIndicator()) {
			addressDoList = dataManager.query(allCriteria);
		} else {
			addressDoList = dataManager.queryNext(allCriteria);
		}
		return notNull(addressDoList);
	}

	private String buildFavoriteAddressSearchExpression() {
		StringBuilder builder = new StringBuilder("[Contact Id] = '");
		builder.append(contract.getContactId());
		builder.append("'");
		builder.append("AND [LXK Status] <> 'Marked For Deletion' ");
		
		Map<String, Object> filterCriteria = contract.getFilterCriteria();
		if (isNotEmpty(filterCriteria)) {
			String filter = buildCriteria(filterCriteria, lbsFieldMap, true, true);
			builder.append(filter);
		}
		return builder.toString();
	}
/* Commented by sankha for 	LEX:AIR00075927
	private String buildAllAddressSearchExpression() {
		StringBuilder builder = new StringBuilder();
		builder.append(buildmdmSearchExpression(contract.getMdmId(),
				contract.getMdmLevel(), fieldMap, true, false));

		Map<String, Object> filterCriteria = contract.getFilterCriteria();
		if (isNotEmpty(filterCriteria)) {
			String filter = buildCriteria(filterCriteria, fieldMap, true, true);
			builder.append(filter);
		}
		return builder.toString();
	}
*/	
	/* Added by sankha for LEX:AIR00075927 */
	private String buildAllAddressSearchExpression() {
		StringBuilder builder = new StringBuilder();
		String markedForDeletion = "Marked For Deletion";
		String inactive = "Inactive";
		builder.append(buildmdmSearchExpression(contract.getMdmId(),
				contract.getMdmLevel(), fieldMap, true, false));
		builder.append(" AND [LXK R Account Address Status] <> '");
		builder.append(markedForDeletion + "'");
		builder.append(" AND [LXK R Account Address Status] <> '");
		builder.append(inactive + "'");
		
		if(contract.isLbsFlag()){
			builder.append(" AND [LXK LBS Flag]='Y'");			
		}
		
		Map<String, Object> filterCriteria = contract.getFilterCriteria();
		if (isNotEmpty(filterCriteria)) {
			String filter = buildCriteria(filterCriteria, fieldMap, true, true);
			builder.append(filter);
		}
		if(cleanseAddress)
		 {
			builder.append(" AND [LXK Trillium Error Message] = 'Exact match' ");
		 }
		return builder.toString();
	}
	/* End LEX:AIR00075927 */
	
	private QueryObject buildAddressCriteria(String searchExpression, Class<?> doClass, boolean paginationFlag) {

		QueryObject criteria = new QueryObject(doClass, ActionEvent.QUERY);
		if(paginationFlag) {
			buildBasicQueryObject(criteria, contract.getIncrement(), contract.getStartRecordNumber());	
		}
		criteria.addComponentSearchSpec(doClass, searchExpression);

		Map<String, Object> sortCriteria = contract.getSortCriteria();
		if (isNotEmpty(sortCriteria)) {
			if (doClass.equals(AddressDo.class)) {
				criteria.setSortString(buildSortString(sortCriteria, fieldMap));
			} else if (doClass.equals(FavoriteAddressDo.class)) {
				criteria.setSortString(buildSortString(sortCriteria,
						lbsFieldMap));
			}
		}

		return criteria;
	}


	private void checkFavoriteAddressFields() {
		String contactId = contract.getContactId();
		if (contactId == null) {
			throw new IllegalArgumentException("Contract Id is null");
		} else if (contactId.isEmpty()) {
			throw new IllegalArgumentException("Contract Id is Empty");
		}
	}
	
	public void checkLBSRequiredFields() {
		String mdmId = contract.getMdmId();
		if (mdmId == null) {
			throw new IllegalArgumentException("MDM Id is null");
		} else if (mdmId.isEmpty()) {
			throw new IllegalArgumentException("MDM Id is empty");
		}
	}

	private void checkAllAddressFields() {
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
	/* Added by David Tsamalashvili for LBS Address Filter */
	public void buildLBSAddressSearchExpression() {
		StringBuilder builder = new StringBuilder();
		builder.append(buildmdmSearchExpression(contract.getMdmId(),contract.getMdmLevel(), fieldMap, true, false));

		if (contract.isLbsFlag()) {
			builder.append(" AND [LXK LBS Flag]='Y'");
			builder.append(" AND [LXK R Account Address Status] <> 'Marked For Deletion' AND [LXK R Account Address Status] <> 'Inactive'");
			lbsQueryType = "Country";
			
			if (contract.getState() != null && contract.getCountry() != null) {
				builder.append(" AND [Country]='");
				builder.append(contract.getCountry());
				builder.append("'");
				builder.append(" AND [State]='");
				builder.append(contract.getState());
				builder.append("'");
				lbsQueryType = "City";
			}
			else if (contract.getProvince() != null && contract.getCountry() != null) {
				builder.append(" AND [Country]='");
				builder.append(contract.getCountry());
				builder.append("'");
				builder.append(" AND [Province]='");
				builder.append(contract.getProvince());
				builder.append("'");
				lbsQueryType = "City";
			}
			else if (contract.getCounty() != null && contract.getCountry() != null) {
				builder.append(" AND [Country]='");
				builder.append(contract.getCountry());
				builder.append("'");
				builder.append(" AND [County]='");
				builder.append(contract.getCounty());
				builder.append("'");
				lbsQueryType = "City";
			}
			else if (contract.getDistrict() != null && contract.getCountry() != null) {
				builder.append(" AND [Country]='");
				builder.append(contract.getCountry());
				builder.append("'");
				builder.append(" AND [District]='");
				builder.append(contract.getDistrict());
				builder.append("'");
				lbsQueryType = "City";
			}
			else if (contract.getCountry() != null ) {
				builder.append(" AND  [Country]='");
				builder.append(contract.getCountry());
				builder.append("'");
				lbsQueryType = "State";
			}
			
		}

		Map<String, Object> filterCriteria = contract.getFilterCriteria();
		if (isNotEmpty(filterCriteria)) {
			String filter = buildCriteria(filterCriteria, fieldMap, true, true);
			builder.append(filter);
		}

		lbsCriteria = buildLBSAddressCriteria(builder.toString(),AddressDo.class, false);
	}
	
	/**	 
	 * @author David Tsamalashvili 
	 */
	/* Added by David Tsamalashvili for LBS Location Filter */
	public void buildLBSLocationSearchExpression() {
		StringBuilder builder = new StringBuilder();
		StringBuilder builderForSecondCall = new StringBuilder();	
		StringBuilder builderForThirdCall = new StringBuilder();
		String type = contract.getLocationType();
		String parentId = contract.getLocationId();
		String addressId = contract.getLbsAddressId();
		String checkCountry = contract.getCountry();
		String checkState = contract.getState();
		String checkCity = contract.getCity();
		String checkProvince = contract.getProvince();
		String checkCounty = contract.getCounty();
		
		boolean isFirstCall = contract.isFirstLoccationCall();
		
		builder.append(buildmdmSearchExpression(contract.getMdmId(), contract.getMdmLevel(), lbsFieldMap, false, false));
		builder.append(" AND [LXK LBS Status]='Active'");	
		//For Second Call - NEW
		builderForSecondCall.append(buildmdmSearchExpression(contract.getMdmId(), contract.getMdmLevel(), lbsFieldMap, false, false));
		builderForSecondCall.append(" AND [LXK LBS Status]='Active' AND [LXK LBS Location Type]='Building'");
		//For Third Call
		builderForThirdCall.append(buildmdmSearchExpression(contract.getMdmId(), contract.getMdmLevel(), lbsFieldMap, false, false));
		builderForThirdCall.append(" AND [LXK LBS Status]='Active' AND [LXK LBS Location Type]='Zone'");
		
		if(contract.isLbsFlag()){
			
			//Case: Country/State/City are selected.
			if ((isNotEmpty(checkCountry) && (isNotEmpty(checkState) || isNotEmpty(checkProvince) || isNotEmpty(checkCounty) || isNotEmpty(contract.getDistrict())) 
					&& isNotEmpty(checkCity)) || (isNotEmpty(checkCountry) && isNotEmpty(checkCity))) {
				if (isFirstCall && LangUtil.isEmpty(parentId)) {
					builder.append(" AND [LXK LBS Location Type] = 'Site'");
					builder.append(" AND [LXK LBS Country]='");
					builder.append(contract.getCountry());
					builder.append("'");
					if (contract.getState() != null) {
						builder.append(" AND [LXK LBS State]='");
						builder.append(contract.getState());
						builder.append("'");
					}
					builder.append(" AND [LXK LBS City]='");
					builder.append(contract.getCity());
					builder.append("'");
						builderForSecondCall.append(" AND [LXK LBS Location Type] = 'Building'");
						builderForSecondCall.append(" AND [LXK LBS Country]='");
						builderForSecondCall.append(contract.getCountry());
						builderForSecondCall.append("'");
						if (contract.getState() != null) {
							builderForSecondCall.append(" AND [LXK LBS State]='");
							builderForSecondCall.append(contract.getState());
							builderForSecondCall.append("'");
						}
						builderForSecondCall.append(" AND [LXK LBS City]='");
						builderForSecondCall.append(contract.getCity());
						builderForSecondCall.append("'");
					builderForThirdCall.append(" AND [LXK LBS Location Type] = 'Zone'");
					builderForThirdCall.append(" AND [LXK LBS Country]='");
					builderForThirdCall.append(contract.getCountry());
					builderForThirdCall.append("'");
					if (contract.getState() != null) {
						builderForThirdCall.append(" AND [LXK LBS State]='");
						builderForThirdCall.append(contract.getState());
						builderForThirdCall.append("'");
					}
					builderForThirdCall.append(" AND [LXK LBS City]='");
					builderForThirdCall.append(contract.getCity());
					builderForThirdCall.append("'");
					

					lbsLocationCriteriaSecondCall = buildLBSLocationCriteria(builderForSecondCall.toString(),LBSLocationDo.class, false);
					lbsLocationCriteriaThirdCall = buildLBSLocationCriteria(builderForThirdCall.toString(),LBSLocationDo.class, false);
				} else {

					if (!LangUtil.isEmpty(type) && !LangUtil.isEmpty(parentId)) {
						if ("Site".equalsIgnoreCase(type)) {
							builder.append(" AND [LXK LBS Location Id]='");
							builder.append(parentId);
							builder.append("'");
							builder.append(" AND [LXK LBS Belongs To Type] = 'Site'");
						} else {
							builder.append(" AND [LXK LBS Belongs To Id]='");
							builder.append(parentId);
							builder.append("'");
							builder.append(" AND [LXK LBS Location Type] = '");
							builder.append(type);
							builder.append("'");
						}

						builder.append(" AND [LXK LBS Country]='");
						builder.append(contract.getCountry());
						builder.append("'");
						if (contract.getState() != null) {
							builder.append(" AND [LXK LBS State]='");
							builder.append(contract.getState());
							builder.append("'");
						}
						if (contract.getCounty() != null) {
							builder.append(" AND [LXK LBS County]='");
							builder.append(contract.getCounty());
							builder.append("'");
						}
						if (contract.getProvince() != null) {
							builder.append(" AND [LXK LBS Province]='");
							builder.append(contract.getProvince());
							builder.append("'");
						}
						builder.append(" AND [LXK LBS City]='");
						builder.append(contract.getCity());
						builder.append("'");
					}

				}

			}
			
			//Case: Select With Address.
			if (!isNotEmpty(checkCountry) && !isNotEmpty(checkState) && !isNotEmpty(checkCity)) {
				if (isFirstCall && !LangUtil.isEmpty(addressId)) {
					builder.append(" AND [LXK LBS Location Type] = 'Site'");
					builder.append(" AND [LXK LBS Address Id] = '");
					builder.append(addressId);
					builder.append("'");
						builderForSecondCall.append(" AND [LXK LBS Address Id]='");
						builderForSecondCall.append(addressId);
						builderForSecondCall.append("'");
					builderForThirdCall.append(" AND [LXK LBS Address Id]='");
					builderForThirdCall.append(addressId);
					builderForThirdCall.append("'");							
								
					lbsLocationCriteriaSecondCall = buildLBSLocationCriteria(builderForSecondCall.toString(),LBSLocationDo.class, false);
					lbsLocationCriteriaThirdCall = buildLBSLocationCriteria(builderForThirdCall.toString(),LBSLocationDo.class, false);
					
				} else if (!isFirstCall && !LangUtil.isEmpty(addressId)
						&& !LangUtil.isEmpty(parentId)) {
					builder.append(" AND [LXK LBS Belongs To Id] = '");
					builder.append(parentId);
					builder.append("'");
					builder.append(" AND [LXK LBS Location Type] = '");
					builder.append(type);
					builder.append("'");
					builder.append(" AND [LXK LBS Address Id] = '");
					builder.append(addressId);
					builder.append("'");
				}
			}
		
		}

		Map<String, Object> filterCriteria = contract.getFilterCriteria();
		if (isNotEmpty(filterCriteria)) {
			String filter = buildCriteria(filterCriteria, lbsFieldMap, true, true);
			builder.append(filter);
		}

		lbsLocationCriteria = buildLBSLocationCriteria(builder.toString(),LBSLocationDo.class, false);
	}
	
	public void buildBuildingTypeSearchSpec() {
		StringBuilder builder = new StringBuilder();
		builder.append(buildmdmSearchExpression(contract.getMdmId(), contract.getMdmLevel(), lbsFieldMap, false, false));
		builder.append(" AND [LXK LBS Status]='Active' AND [LXK LBS Location Type]='Building'");
		
		lbsLocationCriteria = buildLBSLocationCriteria(builder.toString(),LBSLocationDo.class, false);
	}
	
	/**	 
	 * @author David Tsamalashvili 
	 */
	public List<LBSAddress> queryAndGetLBSResultList() {
		IDataManager dataManager = getSession().getDataManager();

		List<AddressDo> addressDoList = queryLBSAddressList(dataManager);
		List<LBSAddress> addressLBSList = convertLBSAddress(addressDoList,lbsQueryType);

		return addressLBSList;
	}
	
	/**	 
	 * @author David Tsamalashvili 
	 */
	public AddressListResult queryAndGetLBSLocationResultList() {
		IDataManager dataManager = getSession().getDataManager();
		List<LBSLocationDo> lbsLocationDo = null;
		List<LBSLocationDo> lbsLocationDoSecondCall = null;
		List<LBSLocationDo> lbsLocationDoThirdCall = null;
		AddressListResult addressLBSList = null;
		boolean isFirstCall = contract.isFirstLoccationCall();	
		
		String lbsLocationqueryType = checkLbsType(contract.getLbsAddressId());
			if(isFirstCall){
				lbsLocationDo = queryLBSLocationList(dataManager);
				lbsLocationDoSecondCall = queryLBSLocationListSecondCall(dataManager);
				lbsLocationDoThirdCall = queryLBSLocationListThirdCall(dataManager);
				addressLBSList= convertLBSLocationForTwoCalls(lbsLocationDo, lbsLocationDoSecondCall, lbsLocationDoThirdCall, contract.isFirstLoccationCall(), lbsLocationqueryType);
			}else{
				lbsLocationDo = queryLBSLocationList(dataManager);
				addressLBSList= convertLBSLocation(lbsLocationDo, contract.getLocationType());				
			}

		return addressLBSList;
	}
	
	public List<LBSLocationBuildingType> queryAndGetLBSBuildingTypes() {
		IDataManager dataManager = getSession().getDataManager();
		List<LBSLocationDo> lbsLocationDo;
		lbsLocationDo = queryLBSLocationList(dataManager);
		return convertLBSBuildingTypeList(lbsLocationDo);
	}
	
	/**	 
	 * @author David Tsamalashvili 
	 */		
	private String checkLbsType(String value) {
		String type = "";
				if(!LangUtil.isEmpty(value)){
					type = "withAddress";
				}
		return type;
	}
		
	/**	 
	 * @author David Tsamalashvili 
	 */
	@SuppressWarnings("unchecked")
	private List<AddressDo> queryLBSAddressList(IDataManager dataManager) {
		List<AddressDo> lbsAddressDoList;

		if (contract.isNewQueryIndicator()) {
			lbsAddressDoList = dataManager.query(lbsCriteria);
		} else {
			lbsAddressDoList = dataManager.queryNext(lbsCriteria);
		}
		return notNull(lbsAddressDoList);
	}
	
	/**	 
	 * @author David Tsamalashvili 
	 */
	@SuppressWarnings("unchecked")
	private List<LBSLocationDo> queryLBSLocationList(IDataManager dataManager) {
		List<LBSLocationDo> lbsLocationDoList;

		if (contract.isNewQueryIndicator()) {
			lbsLocationDoList = dataManager.query(lbsLocationCriteria);				
		} else {
			lbsLocationDoList = dataManager.queryNext(lbsLocationCriteria);				
		}
		return notNull(lbsLocationDoList);
	}
	
	/**	 
	 * @author David Tsamalashvili 
	 */
	@SuppressWarnings("unchecked")
	private List<LBSLocationDo> queryLBSLocationListSecondCall(IDataManager dataManager) {
		List<LBSLocationDo> lbsLocationDoListSecondCall;

		if (contract.isNewQueryIndicator()) {
			lbsLocationDoListSecondCall = dataManager.query(lbsLocationCriteriaSecondCall);				
		} else {
			lbsLocationDoListSecondCall = dataManager.queryNext(lbsLocationCriteriaSecondCall);				
		}
		return notNull(lbsLocationDoListSecondCall);
	}
	
	/**	 
	 * @author David Tsamalashvili 
	 */
	@SuppressWarnings("unchecked")
	private List<LBSLocationDo> queryLBSLocationListThirdCall(IDataManager dataManager) {
		List<LBSLocationDo> lbsLocationDoListThirdCall;

		if (contract.isNewQueryIndicator()) {
			lbsLocationDoListThirdCall = dataManager.query(lbsLocationCriteriaThirdCall);				
		} else {
			lbsLocationDoListThirdCall = dataManager.queryNext(lbsLocationCriteriaThirdCall);				
		}
		return notNull(lbsLocationDoListThirdCall);
	}

	/**	 
	 * @author David Tsamalashvili 
	 */
	private QueryObject buildLBSAddressCriteria(String searchExpression, Class<?> doClass, boolean paginationFlag) {

		QueryObject criteria = new QueryObject(doClass, ActionEvent.QUERY);
		if(paginationFlag) {
			buildBasicQueryObject(criteria, contract.getIncrement(), contract.getStartRecordNumber());	
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
	
	/**	 
	 * @author David Tsamalashvili 
	 */
	private QueryObject buildLBSLocationCriteria(String searchExpression, Class<?> doClass, boolean paginationFlag) {

		QueryObject criteria = new QueryObject(doClass, ActionEvent.QUERY);
		if(paginationFlag) {
			buildBasicQueryObject(criteria, contract.getIncrement(), contract.getStartRecordNumber());	
		}
		criteria.addComponentSearchSpec(doClass, searchExpression);

		Map<String, Object> sortCriteria = contract.getSortCriteria();
		if (isNotEmpty(sortCriteria)) {
			if (doClass.equals(LBSLocationDo.class)) {
				criteria.setSortString(buildSortString(sortCriteria, fieldMap));
			}
		}
		
		return criteria;
	}
}
