package com.lexmark.service.impl.real.deviceService;

import static com.lexmark.service.impl.real.deviceService.AmindContractedDeviceDataConversionUtil.assetLocationDotoGenericAddress;
import static com.lexmark.util.LangUtil.isNotEmpty;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.logging.log4j.LogManager;

import com.amind.data.service.ActionEvent;
import com.amind.data.service.IDataManager;
import com.amind.data.service.QueryObject;
import com.amind.session.Session;
import com.lexmark.contract.LocationReportingHierarchyContract;
import com.lexmark.result.AssetLocationsResult;
import com.lexmark.service.api.SiebelCrmServiceException;
import com.lexmark.service.impl.real.domain.AssetLocation;
import com.lexmark.service.impl.real.domain.CHLDo;
import com.lexmark.service.impl.real.domain.MeterReadAssetLocation;
import com.lexmark.service.impl.real.domain.VendorAssetLocationDo;
import com.lexmark.service.impl.real.util.AmindServiceUtil;
import com.lexmark.util.LangUtil;
import org.apache.logging.log4j.Logger;


/**
 * @author vpetruchok
 * @version 1.0, 2012-10-21
 */
public class AssetLocationService {
    
   // private static Log logger = LogFactory.getLog(AssetLocationService.class);
	 private static Logger logger = LogManager.getLogger(AssetLocationService.class);
    private final Session session;
    private static final Map<String, String> FIELD_MAP = fieldMap();    
    private static final Map<String, String> ENTITLEMENT_FIELD_MAP = entitlementFieldMap();    
    private static final Map<String, String> VENDOR_FIELD_MAP = vendorFieldMap();    
    
    private static Map<String, String> fieldMap() { 
        Map<String, String> m = new HashMap<String, String>();
        m.put("mdmLevel1AccountId", "LXK SW Agreement Account Global DUNS");
        m.put("mdmLevel2AccountId", "LXK SW Agreement Account Domestic DUNS");
        m.put("mdmLevel3AccountId", "LXK SW Agreement Account LEGAL MDM ID");
        m.put("mdmLevel4AccountId", "LXK SW Agreement Account MDM Account");
        m.put("mdmLevel5AccountId", "LXK SW Agreement Account Id");
        m.put("agreementParentChain", "LXK SW Covered Asset CHL Parent Chain");
        return Collections.unmodifiableMap(m);
    }    
    
    private static Map<String, String> entitlementFieldMap() {
        Map<String, String> m = new HashMap<String, String>();
        m.put("mdmLevel1AccountId", "LXK MPS Ent Global DUNS");
        m.put("mdmLevel2AccountId", "LXK MPS Domestic DUNS");
        m.put("mdmLevel3AccountId", "LXK MPS Legal");
        m.put("mdmLevel4AccountId", "LXK MPS MDM");
        m.put("mdmLevel5AccountId", "LXK MPS Account Id");
        m.put("entitlementType", "LXK SW Entitlement Type");
        m.put("entitlementStartDate", "LXK MPS Entitlement Start Date");
        m.put("entitlementEndDate", "LXK MPS Entitlement End Date");
        m.put("entitlementParentChain", "LXK MPS Parent Chain");
        return Collections.unmodifiableMap(m);
    }    
    
    private static Map<String, String> vendorFieldMap() {
        Map<String, String> m = new HashMap<String, String>();
        m.put("mdmLevel1AccountId", "LXK MPS Vendor Account Domestic DUNS Number");
        m.put("mdmLevel2AccountId", "LXK MPS Vendor Account Global DUNS Number");
        m.put("mdmLevel3AccountId", "LXK MPS Vendor Account Legal Entity Id");
        m.put("mdmLevel4AccountId", "LXK MPS Vendor Account MDM #");
        m.put("mdmLevel5AccountId", "LXK MPS Vendor Account Id");
        return Collections.unmodifiableMap(m);
    }    
    
    public AssetLocationService(Session session) {
        super();
        this.session = session;
    }

    public AssetLocationsResult retrieveAssetLocations(LocationReportingHierarchyContract contract) {
        logger.debug("[IN] retrieveAssetLocations");
        checkRequiredFields(contract);
        
        AssetLocationsResult result = new AssetLocationsResult();
        try {
            if(!isSearchByEntitlementEndDate(contract)) {
            	result = queryWithOutEntitlementDate(contract);
            }else{
            	result = queryWithEntitlementDate(contract);
            }

        }
        finally {
           logger.debug("[OUT] retrieveAssetLocations");
        }
        return result;
    }    
    
    @SuppressWarnings("unchecked")
    private AssetLocationsResult queryWithOutEntitlementDate(LocationReportingHierarchyContract contract) {
		AssetLocationsResult result = new AssetLocationsResult();
		IDataManager dataManager = session.getDataManager();
			
		QueryObject criteria = null;
		criteria = new QueryObject(MeterReadAssetLocation.class, ActionEvent.QUERY);
		//building expression
		StringBuilder expr = new StringBuilder();
		CHLDo chl = AmindServiceUtil.getParentChainFromCHLNodeId(contract.getChlNodeId(), dataManager);
		expr.append("EXISTS (");
        if (isNotEmpty(contract.getChlNodeId()))
		{
			expr.append(" [chlNodeId] LIKE '");
			expr.append(chl.getParentChain());
			expr.append("*'");
		}
		else
		{
			expr.append(AmindServiceUtil.buildmdmSearchExpression(contract.getMdmId(), contract.getMdmLevel(),FIELD_MAP,false,true));
		}
		expr.append(")");
		criteria.addComponentSearchSpec(MeterReadAssetLocation.class, expr.toString());
		
        List<AssetLocation> locationList = dataManager.query(criteria);

			//conversion
		if (locationList != null && !locationList.isEmpty()) {
			result.setAddressList(assetLocationDotoGenericAddress(locationList));
		}
		return result;
    }
    
    private AssetLocationsResult queryWithEntitlementDate(LocationReportingHierarchyContract contract) {
    	AssetLocationsResult result = new AssetLocationsResult();
    	IDataManager dataManager = session.getDataManager();
        StringBuilder expr = new StringBuilder();
        QueryObject criteria = null;
        boolean secondQuerySiebel;
        if (isNotEmpty(contract.getChlNodeId()))
        {
        	criteria = new QueryObject(AssetLocation.class, ActionEvent.QUERY);
            CHLDo chl = AmindServiceUtil.getParentChainFromCHLNodeId(contract.getChlNodeId(), dataManager);
        	expr.append("EXISTS (");
            expr.append(" [chlNodeId] LIKE '");
            expr.append(chl.getParentChain());
            expr.append("*' ) ");
            expr.append(" OR EXISTS ((");
            expr.append(" [entitlementParentChain] LIKE '");
            expr.append(chl.getParentChain());
            expr.append("*'))");
            criteria.addComponentSearchSpec(AssetLocation.class, expr.toString());
            secondQuerySiebel =true;
        }
        else
        {
            if (contract.isVendorFlag()) {
            	criteria = new QueryObject(VendorAssetLocationDo.class, ActionEvent.QUERY);
            	expr.append(AmindServiceUtil.buildmdmSearchExpression(contract.getMdmId(), contract.getMdmLevel(), VENDOR_FIELD_MAP, false, false));
            	criteria.addComponentSearchSpec(VendorAssetLocationDo.class, expr.toString());
            	secondQuerySiebel =true;
            } else{
            	criteria = new QueryObject(AssetLocation.class, ActionEvent.QUERY);

            	expr.append("([Operating Status]= 'New' OR [Operating Status]= 'Created' OR [Operating Status] = 'Active') ");
            	expr.append("AND ");
            	expr.append(AmindServiceUtil.buildmdmSearchExpressionLocationTree(contract.getMdmId(), contract.getMdmLevel(), ENTITLEMENT_FIELD_MAP, false, false));
            	expr.append("AND ([LXK MPS Agree Status] = 'Active' OR [LXK MPS Agree Status]='Current') ");
            	
				if (LangUtil.isNotEmpty(contract.getPageName())
						&& "Order Supplies".equalsIgnoreCase(contract
								.getPageName())) {
				expr.append("AND( EXISTS ([LXK SW Entitlement Type] LIKE 'Consumable*' "); 
				
				expr.append(String.format("AND [LXK MPS Entitlement End Date] >= '%s'", contract.getEntitlementEndDate()));
				expr.append("))");	
				}
            	criteria.addComponentSearchSpec(AssetLocation.class, expr.toString());
            	secondQuerySiebel =false;
         	}
        }
        
        List<AssetLocation> locationList = new ArrayList<AssetLocation>();
        locationList = dataManager.query(criteria);
        
		if (secondQuerySiebel) {
			StringBuilder expression = new StringBuilder();
			expression.append(" EXISTS ");
			expression.append("(");
			expression.append(AmindServiceUtil.buildmdmSearchExpression(
					contract.getMdmId(), contract.getMdmLevel(), FIELD_MAP,
					false, true));
			expression.append(")");
			criteria.addComponentSearchSpec(AssetLocation.class,
					expression.toString());

			List<AssetLocation> assetLocations = dataManager.query(criteria);
			if (locationList != null) {
				locationList.addAll(LangUtil.notNull(assetLocations));
			} else {
				locationList = assetLocations;
			}
		}
        
        if (locationList != null && !locationList.isEmpty()) {
            result.setAddressList(assetLocationDotoGenericAddress(locationList));
        }
    	return result;
    }
    
    private static void checkRequiredFields(LocationReportingHierarchyContract contract) {
        if (!(isSearchByChlNodeId(contract)
       		   || isSearchByMdmWithVendoFlag(contract)
        	    || isSearchByMdmAndMdmLevel(contract)))
        {
            throw new SiebelCrmServiceException("No chlNodeId or (mdmId, mdmLevel) specified");
        }
    }


	private static boolean isSearchByChlNodeId(LocationReportingHierarchyContract contract) {
		return isNotEmpty(contract.getChlNodeId());
	}
	
	private static boolean isSearchByEntitlementEndDate( LocationReportingHierarchyContract contract) {
		return isNotEmpty(contract.getEntitlementEndDate());
	}
	
	private static boolean isSearchByMdmWithVendoFlag( LocationReportingHierarchyContract contract) {
		return isNotEmpty(contract.getMdmId())
				&& isNotEmpty(contract.getMdmLevel())
				 && contract.isVendorFlag();
	}
	
	private static boolean isSearchByMdmAndMdmLevel( LocationReportingHierarchyContract contract) {
		return isNotEmpty(contract.getMdmId())
				&& isNotEmpty(contract.getMdmLevel());
	}
}
