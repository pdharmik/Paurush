package com.lexmark.service.impl.real.partnerPaymentService;

import static com.lexmark.util.LangUtil.isNotEmpty;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import org.apache.commons.logging.Log;
import org.apache.logging.log4j.Logger;

import com.amind.data.service.IDataManager;
import com.amind.data.service.QueryObject;
import com.amind.session.Session;
import com.lexmark.contract.api.MdmSearchContractBase;

/**
 * @author vpetruchok
 * @version 1.0, 2012-10-09
 */
public class PartnerPaymentUtil {
    
    private PartnerPaymentUtil() {
    }
    
    static final Map<String, String>  LXK_COMPANY_CODES_FIELD_MAP = lxkCompanyCodesFieldMap(); 
    
    private static Map<String, String> lxkCompanyCodesFieldMap() {
        Map<String, String> m = new HashMap<String, String>();
        m.put("mdmLevel1AccountId", "LXK Partner Global DUNS");
        m.put("mdmLevel2AccountId", "LXK Partner Dom DUNS");
        m.put("mdmLevel3AccountId", "LXK SAP Vendor MDM Id");
        m.put("mdmLevel4AccountId", "LXK SAP Vendor MDM Account#");
        m.put("mdmLevel5AccountId", "LXK Partner DUNS");        
//        m.put("mdmLevel", "");
        return Collections.unmodifiableMap(m);
    }

    @SuppressWarnings("unchecked")
    static <T> List<T> querySiebel(Logger logger, Session session, QueryObject criteria, MdmSearchContractBase contract) {
        logger.debug("[IN] querySiebel(...)");
        logger.debug("[IN] criteria componentSearchSpecMap = " + criteria.getComponentSearchSpecMap());
        logger.debug("[IN] criteria sortingString =" + criteria.getSortString());
        IDataManager dataManager =  session.getDataManager();
        List<T> assetList = null;
        if (contract.isNewQueryIndicator()) {
            assetList = dataManager.query(criteria);
        } else {
            assetList = dataManager.queryNext(criteria);
        }
        logger.debug("[OUT] querySiebel(...)");
        return assetList;
    }    
    

    static boolean isSearchByMdm(MdmSearchContractBase contract) {
        return isNotEmpty(contract.getMdmId()) && isNotEmpty(contract.getMdmLevel());
    } 
    
}
