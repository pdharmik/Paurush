package com.lexmark.service.impl.real.deviceService;


import static com.lexmark.util.LangUtil.isNotEmpty;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lexmark.domain.Account;
import com.lexmark.domain.AccountContact;
import com.lexmark.domain.Asset;
import com.lexmark.domain.CHLNode;
import com.lexmark.domain.Entitlement;
import com.lexmark.domain.EntitlementServiceDetail;
import com.lexmark.domain.GenericAddress;
import com.lexmark.domain.ServiceRequest;
import com.lexmark.service.impl.real.domain.AccountAsset;
import com.lexmark.service.impl.real.domain.AccountAssetDetailDo;
import com.lexmark.service.impl.real.domain.AccountAssetFavorites;
import com.lexmark.service.impl.real.domain.AccountAssetReadingDo;
import com.lexmark.service.impl.real.domain.AssetBase;
import com.lexmark.service.impl.real.domain.AssetLocation;
import com.lexmark.service.impl.real.domain.AssetMeasurementCharacteristicsBCDo;
import com.lexmark.service.impl.real.domain.AssetReadingLatestBcDo;
import com.lexmark.service.impl.real.domain.CHLAccountCHLDo;
import com.lexmark.service.impl.real.domain.CHLAccountDo;
import com.lexmark.service.impl.real.domain.CHLDo;
import com.lexmark.service.impl.real.domain.ConsumableAssetTypeDo;
import com.lexmark.service.impl.real.domain.ContractedAndEntitledAssetDo;
import com.lexmark.service.impl.real.domain.EntitlementTemplateServiceDetailsDo;
import com.lexmark.service.impl.real.domain.RequestLocationDo;
import com.lexmark.service.impl.real.domain.ServiceAgreementProductDo;
import com.lexmark.service.impl.real.util.AssetConversionUtil;
import com.lexmark.service.impl.real.util.ChlNodeNameComparator;
import com.lexmark.util.LangUtil;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

/**
 * @see com.lexmark.service.impl.real.AmindContractedDeviceService
 * 
 * @author vpetruchok
 * @version 1.0, Mar 6, 2012
 */
public class AmindContractedDeviceDataConversionUtil {

	//private static Log logger = LogFactory.getLog(AmindContractedDeviceDataConversionUtil.class);
	private static Logger logger = LogManager.getLogger(AmindContractedDeviceDataConversionUtil.class);
    private AmindContractedDeviceDataConversionUtil() {
    }

    public static List<CHLNode> convertCHLIOtoCHLNode(List<CHLDo> chldoList)
    {
        List<CHLNode> nodeList = new ArrayList<CHLNode>();
        for(CHLDo chlDo : chldoList)
        {
            CHLNode chlNode = new CHLNode();
            chlNode.setCHLNodeId(chlDo.getId());
            chlNode.setChlNodeName(chlDo.getName());
            chlNode.setChlParentId(chlDo.getParentAccountId());
            chlNode.setHasChild(chlDo.isHasChild());
            nodeList.add(chlNode);
        }

        // Sort each list
        sortChlNodeList(nodeList);

        return nodeList;

    }

    private static final ChlNodeNameComparator CHL_NODE_NAME_COMPARATOR = new ChlNodeNameComparator();

    private static void sortChlNodeList(List<CHLNode> nodeList) {
        if (isNotEmpty(nodeList)) {
        	Collections.sort(nodeList, CHL_NODE_NAME_COMPARATOR);
        }
    }

    public static Asset convertAccountAssetDetailDoToAsset(AccountAssetDetailDo item, List<AccountAssetFavorites> favoriteList) {
        Asset asset = new Asset();

        asset.setAssetId(item.getAssetId());
        asset.setSerialNumber(item.getSerialNumber());
        asset.setAssetTag(item.getAssetTag());
        asset.setModelNumber(item.getModelNumber());
        asset.setIpAddress(item.getIpAddress());
        asset.setAssetType(item.getAssetType());
        asset.setHostName(item.getHostName());
        asset.setDeviceTag(item.getDeviceTagCustomer());
        asset.setProductTLI(item.getProductTLI());
        asset.setDeviceName(item.getDeviceName());
        asset.setAssetCostCenter(item.getCustomerCostCode());
        asset.setInstallDate(item.getInstallDate());
        asset.setChlNodeId(item.getHierarchyNodeId());
        asset.setChlNodeValue(item.getHierarchyNodeValue());
        asset.setConsumableAssetFlag(item.isConsumableAssetFlag());
        
        
        GenericAddress physicalLocationAddress = new GenericAddress();
        physicalLocationAddress.setPhysicalLocation1(item.getPhysicalLocation1());
        physicalLocationAddress.setPhysicalLocation2(item.getPhysicalLocation2());
        physicalLocationAddress.setPhysicalLocation3(item.getPhysicalLocation3());
        physicalLocationAddress.setCounty(item.getCounty());
        physicalLocationAddress.setDistrict(item.getDistrict());
        physicalLocationAddress.setOfficeNumber(item.getOfficeNumber());
        asset.setPhysicalLocationAddress(physicalLocationAddress);
        
        asset.setProductLine(item.getProductLine());
        asset.setDevicePhase(item.getDevicePhase());
        asset.setMacAddress(item.getMacAddress());

        Account account = new Account();
        asset.setAccount(account);
        account.setAccountId(item.getAccountId());
        account.setAccountName(item.getAccountName());

        AccountContact contact = new AccountContact();
        if(item.getContactId() != null && !item.getContactId().equalsIgnoreCase("No match row Id"))
        {
            contact.setContactId(item.getContactId());
            contact.setFirstName(item.getContactFirstName());
            contact.setLastName(item.getContactLastName());
            contact.setEmailAddress(item.getContactEmailAddress());
            contact.setWorkPhone(item.getContactWorkPhone());
            contact.setMiddleName(item.getContactMiddleName());
            contact.setAlternatePhone(item.getContactAlternatePhone());
        }
        asset.setAssetContact(contact);
  
        ServiceRequest serviceRequest = new  ServiceRequest();
        serviceRequest.setCostCenter(item.getCustomerCostCode());
        serviceRequest.setAddtnlDescription(item.getSpecialInstructions());
        asset.setServiceRequest(serviceRequest);
 
        AssetConversionUtil.populateInstallAddress(asset, item);
        asset.getInstallAddress().setStoreFrontName(item.getStoreFrontName()); 

        Entitlement entitlement = new Entitlement();
        List<ServiceAgreementProductDo> entitlementList = item.getEntitlements();

        if (isNotEmpty(entitlementList))
        {
            List <EntitlementServiceDetail> entitlementServiceDetails = new ArrayList<EntitlementServiceDetail>();

            ServiceAgreementProductDo entitlementDo = null;

            for(ServiceAgreementProductDo entitlementProductDO : entitlementList)
            {
                Date today = Calendar.getInstance().getTime();
                if(entitlementProductDO.getStartDate().before(today)
                        && entitlementProductDO.getEndDate().after(today))
                {
                    entitlementDo = entitlementProductDO;
                    break;  
                }
            }
            ArrayList<EntitlementTemplateServiceDetailsDo> serviceDetailsList =  new ArrayList<EntitlementTemplateServiceDetailsDo>();

            if(entitlementDo != null)
            {
                entitlement.setEntitlementId(entitlementDo.getId());
                entitlement.setEntitlementName(entitlementDo.getName());

                serviceDetailsList = entitlementDo.getServiceDetails();
            }

            for(EntitlementTemplateServiceDetailsDo serviceDetailDo : LangUtil.notNull(serviceDetailsList)) 
            {
                if(serviceDetailDo.isCustomerShowFlag())
                {
                     EntitlementServiceDetail serviceDetail = new EntitlementServiceDetail();
                     serviceDetail.setServiceDetailId(serviceDetailDo.getId());
                     serviceDetail.setServiceDetailDescription(serviceDetailDo.getDescription());
                     serviceDetail.setPrimaryFlag(serviceDetailDo.getPrimaryFlag() == null ? false : serviceDetailDo.getPrimaryFlag());
                     entitlementServiceDetails.add(serviceDetail);
                 }
            }

            entitlement.setServiceDetails(entitlementServiceDetails);
            asset.setEntitlement(entitlement);
        }

        asset.setColorCapableFlag(false);
        List<AssetMeasurementCharacteristicsBCDo> measurementCharacteristics = item.getMeterReads();
        convertMeasurementCharacteristicList(asset, measurementCharacteristics);

        for(AccountAssetFavorites favorite : LangUtil.notNull(favoriteList))
        {
            if(favorite.getAssetId().equals(item.getAssetId()))
            {
                asset.setUserFavoriteFlag(true);
                break;
             }
        }

        return asset;
    }


    private static final String NAME_COLOR = "Color";
    private static final String NAME_LTPC = "LTPC";

    public static void convertMeasurementCharacteristicList(Asset asset,
            List<AssetMeasurementCharacteristicsBCDo> measurementCharacteristics) {

        if (measurementCharacteristics == null || asset == null) {
            return;
        }
        Date nameMaxDate = null;
        Date ltpcMaxDate = null;
        for (AssetMeasurementCharacteristicsBCDo measurementCharacteristic: measurementCharacteristics) {
            if (measurementCharacteristic == null || measurementCharacteristic.getAssetReading() == null) {
                continue;
            }
            List<AssetReadingLatestBcDo> readingList = measurementCharacteristic.getAssetReading();
            if (NAME_COLOR.equals(measurementCharacteristic.getName())) {
                asset.setColorCapableFlag(true);
                for (AccountAssetReadingDo reading : readingList) {
                    Date readingDate = reading.getTimestamp();
                    if (readingDate == null) {
                        continue;
                    }
                    if (nameMaxDate == null || (nameMaxDate.before(readingDate))) {
                        nameMaxDate = readingDate;
                        asset.setLastColorPageCount(reading.getReading());
                        asset.setLastColorPageReadDate(readingDate);
                    }
                }
            }
            else if (NAME_LTPC.equals(measurementCharacteristic.getName())) {
                for (AccountAssetReadingDo reading : readingList) {
                    Date readingDate = reading.getTimestamp();
                    if (readingDate == null) {
                        continue;
                    }
                    if (ltpcMaxDate == null || (readingDate != null && ltpcMaxDate.before(readingDate))) {
                        ltpcMaxDate = readingDate;
                        asset.setLastPageCount(reading.getReading());
                        asset.setLastPageReadDate(readingDate);
                    }
                }
            }
        }
    }


    public static List<Asset> convertAssetDoToAssetList(List<? extends AssetBase> assetBaseList, Set<String> favoriteSet) {
        List<Asset> portalAssetList = new ArrayList<Asset>();
            for (AssetBase assetBase : LangUtil.notNull(assetBaseList)) {
                Asset asset = toAsset(assetBase, favoriteSet);
                portalAssetList.add(asset);
            }
//        }

        return portalAssetList;
    }

    static Asset toAsset(AssetBase assetBase, Set<String> favoriteSet) {
        Asset asset = new Asset();
        asset.setAssetId(assetBase.getAssetId());
        asset.setAssetTag(assetBase.getAssetTag());
        asset.setDeviceTag(assetBase.getDeviceTagCustomer());
        asset.setHostName(assetBase.getHostName());
        asset.setIpAddress(assetBase.getIpAddress());
        asset.setProductLine(assetBase.getProductLine());
        asset.setProductTLI(assetBase.getProductTLI());
        asset.setSerialNumber(assetBase.getSerialNumber());
        asset.setModelNumber(assetBase.getMachineTypeModel());
        asset.setDevicePhase(assetBase.getDevicePhase());
        asset.setMacAddress(assetBase.getMacAddress());
        asset.setAssetCostCenter(assetBase.getAssetCostCenter());
        AssetConversionUtil.populateInstallAddress(asset, assetBase);
        
        AccountContact accountContact = new AccountContact();
        if (assetBase instanceof ContractedAndEntitledAssetDo) 
        {
        	ContractedAndEntitledAssetDo accAsset = (ContractedAndEntitledAssetDo)assetBase;
        	accountContact.setEmailAddress(accAsset.getContactEmailAddress());
            accountContact.setFirstName(accAsset.getContactFirstName());
            accountContact.setLastName(accAsset.getContactLastName());
            accountContact.setWorkPhone(accAsset.getContactWorkPhone());
            processAgreementType(accAsset);
            asset.setConsumableAssetFlag(processEntitlmentType(accAsset));
            
        	Account acc = new Account();
        	acc.setAccountId(accAsset.getOwnerAccountId());
        	acc.setAccountName(accAsset.getAccountName());
        	asset.setAccount(acc);
			if (LangUtil.isBlank(accAsset.getDescriptionLocalLang()) // changes here for 13403
					&& LangUtil.isBlank(accAsset.getProductLine())) {
				asset.setDescriptionLocalLang(accAsset.getMdmModel());
			} else {
				asset.setDescriptionLocalLang(accAsset.getProductLine());
				if (LangUtil.isNotBlank(accAsset.getDescriptionLocalLang())) {
					asset.setDescriptionLocalLang(accAsset
							.getDescriptionLocalLang());
				}
			}
			GenericAddress physicalLocationAddress = new GenericAddress();
            physicalLocationAddress.setPhysicalLocation1(accAsset.getPhysicalLocation1());
            physicalLocationAddress.setPhysicalLocation2(accAsset.getPhysicalLocation2());
            physicalLocationAddress.setPhysicalLocation3(accAsset.getPhysicalLocation3());
            asset.setPhysicalLocationAddress(physicalLocationAddress);
            
            asset.setLbsAddressFlag(populateLbsAddressFlag(assetBase));
        	
        	/*address.setBuilding(accAsset.getPhysicalLocation1());
        	address.setOffice(accAsset.getPhysicalLocation3());
        	address.setFloor(accAsset.getPhysicalLocation2());*/
        }
        else if (assetBase instanceof AccountAsset)
        {
            AccountAsset accasset = (AccountAsset) assetBase;
            accountContact.setEmailAddress(accasset.getContactEmailAddress());
            accountContact.setFirstName(accasset.getContactFirstName());
            accountContact.setLastName(accasset.getContactLastName());
            accountContact.setWorkPhone(accasset.getContactWorkPhone());
            processAgreementType(accasset);
            asset.setConsumableAssetFlag(accasset.isConsumableAssetFlag());
        }
        else if (assetBase instanceof AccountAssetFavorites)
        {
            AccountAssetFavorites accasset = (AccountAssetFavorites) assetBase;
            accountContact.setEmailAddress(accasset.getEmailAddress());
            accountContact.setFirstName(accasset.getFirstName());
            accountContact.setLastName(accasset.getLastName());
            accountContact.setWorkPhone(accasset.getWorkPhone());
            asset.setUserFavoriteFlag(true);
            asset.setConsumableAssetFlag(accasset.isConsumableAssetFlag());
            
            Account acc = new Account();
            acc.setAccountId(accasset.getOwnerAccountId());
            acc.setAccountName(accasset.getAccountName());
            asset.setAccount(acc);            
        }
        

        if (favoriteSet != null && favoriteSet.contains(assetBase.getAssetId()))
        {
            asset.setUserFavoriteFlag(true);
        }
        asset.setAssetContact(accountContact);
        return asset;
    }

    private static boolean populateLbsAddressFlag(AssetBase assetBase) {
    	if ("Y".equalsIgnoreCase(assetBase.getLbsAddressFlag())) {
			return true;
		} else {
			return false;
		}
	}

	private static Boolean processEntitlmentType (ContractedAndEntitledAssetDo asset) {
       Boolean flag = Boolean.FALSE;

        for(ConsumableAssetTypeDo type :  LangUtil.notNull(asset.getType()))
        {
        	if(type != null && type.getEntitlementStatus() != null && type.getEntitlementStatus().equalsIgnoreCase("Valid"))
        	{
            	if(type.getConsumableType() != null)
            	{ 
            		if(type.getConsumableType().startsWith("Consumable"))
            		{
            			flag = Boolean.TRUE;
            			break;
            		}
            	}
        	}

        }
        return flag;
    }
    private static void processAgreementType(AccountAsset accountAsset) {
        String agreementType = accountAsset.getAgreementType();
        if ("MPS Agreement".equals(agreementType)) {
            accountAsset.setAssetType("UnManaged");
        } else if ("CSS Agreement".equals(agreementType)) {
            accountAsset.setAssetType("Managed");
        } else {
            // do nothing
        }
    }


    /**
     * Converts a CHLIODo list into a hierarchial list of CHLNodes sorted by name at each level
     * CHL1
     * 		ChildNode1
     * 			ChildChildNode1
     * 			ChildChildNode2
     * 		ChildNode2
     * CHL2
     * 		ChildNode1
     * 		ChildNode2
     *
     * @param chlDoList
     * @return Hierarchial list of nodes sorted on name
     */
    public static List<CHLNode> convertCHLDoToCHLNode(List<CHLAccountDo> chlDoList, String rootNodeId) {
        List<CHLNode> nodeList = new ArrayList<CHLNode>();

        if (chlDoList == null) {
            return new ArrayList<CHLNode>();
        }
        // Convert to CHLNode and add to map
        float loadFactor = 0.75f;
        int initialCapacity = (int)((chlDoList.size() + 1) / loadFactor);

        CHLNode node = null;
        for (CHLAccountDo chlioDo : chlDoList)
        {
            Map<String, CHLNode> chlNodeMap = new HashMap<String, CHLNode>(initialCapacity, loadFactor);
            for(CHLAccountCHLDo child : chlioDo.getChlList())
            {
                node = chlDoToCHLNode(child);
                chlNodeMap.put(node.getCHLNodeId(), node);
            }

            // Iterate over map entries, adding children to parents and capturing parent nodes
            CHLNode parentNode = null;
            Map<String,CHLNode> nodeMap = new HashMap<String,CHLNode>();
            String parentId = null;
            for (Entry<String, CHLNode> entry : chlNodeMap.entrySet()) {
                node = entry.getValue();
                parentId = node.getChlParentId();
                if (parentId != null && parentId.length() > 0) {
                    // child node
                    parentNode = chlNodeMap.get(parentId);
                    if (parentNode != null)
                    {
                        parentNode.addChildNode(node);
                    }
                }
                else
                {
                    nodeMap.put(node.getCHLNodeId(),node);
                }
            }
            for(Entry<String, CHLNode> entry : nodeMap.entrySet())
            {
                nodeList.add(entry.getValue());
            }

            // Sort each list
            sortChlNodeList(nodeList);
            for (Entry<String, CHLNode> entry : chlNodeMap.entrySet()) {
                node = entry.getValue();
                sortChlNodeList(entry.getValue().getChildNodeList());

            }
            }

        return nodeList;
    }

    private static CHLNode chlDoToCHLNode(CHLAccountCHLDo chlDo)
    {
        CHLNode node = new CHLNode();
        node.setCHLNodeId(chlDo.getId());
        node.setChlNodeName(chlDo.getName());
        node.setChlParentId(chlDo.getParentAccountId());
        return node;
    }

   public static List<GenericAddress> assetLocationDotoGenericAddress(List<AssetLocation> assetLocationList)
    {
        List<GenericAddress> addressList = new ArrayList<GenericAddress>();
        for (AssetLocation assetLocation : assetLocationList) {
        	logger.debug("Country from aMind service" + assetLocation.getCountry());
            GenericAddress genericAddress = new GenericAddress();
            genericAddress.setAddressName(assetLocation.getAddressName());
            genericAddress.setCity(assetLocation.getCity());
            genericAddress.setState(assetLocation.getState());
            genericAddress.setAddressLine1(assetLocation.getStreetAddress());
            genericAddress.setAddressLine2(assetLocation.getStreetAddress2());
            genericAddress.setPostalCode(assetLocation.getPostalCode());
            genericAddress.setProvince(assetLocation.getProvince());
            genericAddress.setCountry(assetLocation.getCountry());
            genericAddress.setCounty(assetLocation.getCounty());
            genericAddress.setDistrict(assetLocation.getDistrict());
    		genericAddress.setOfficeNumber(assetLocation.getOfficeNumber());
            addressList.add(genericAddress);
        }
        return addressList;
    }
    
    public static List<GenericAddress> requesLocationDotoGenericAddress(List<RequestLocationDo> requestLRocationList)
    {
        List<GenericAddress> addressList = new ArrayList<GenericAddress>();
        for (RequestLocationDo requestLocation : requestLRocationList) {
            GenericAddress genericAddress = new GenericAddress();
            genericAddress.setAddressName(requestLocation.getAddressName());
            genericAddress.setCity(requestLocation.getCity());
            genericAddress.setState(requestLocation.getState());
            genericAddress.setAddressLine1(requestLocation.getAddressLine1());
            genericAddress.setAddressLine2(requestLocation.getAddressLine2());
            genericAddress.setPostalCode(requestLocation.getPostalCode());
            genericAddress.setProvince(requestLocation.getProvince());
            genericAddress.setCountry(requestLocation.getCountry());
            genericAddress.setCounty(requestLocation.getCounty());
            genericAddress.setDistrict(requestLocation.getDistrict());
            genericAddress.setOfficeNumber(requestLocation.getOfficeNumber());
            addressList.add(genericAddress);
        }
        return addressList;
    }

}
