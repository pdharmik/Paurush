package com.lexmark.service.impl.real.customerPaymentService;

import static com.lexmark.util.LangUtil.isNotEmpty;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;

import com.amind.data.service.IDataManager;
import com.amind.data.service.QueryObject;
import com.amind.session.Session;
import com.lexmark.contract.api.MdmSearchContractBase;

/**
 * @author Imdzeluri
 */
public class CustomerPaymentUtil {
    
    private CustomerPaymentUtil() {
    }
    
    static final Map<String, String>  LXK_COMPANY_CODES_FIELD_MAP = lxkCompanyCodesFieldMap(); 
    
    private static Map<String, String> lxkCompanyCodesFieldMap() {
        Map<String, String> m = new HashMap<String, String>();
        m.put("mdmLevel1AccountId", "LXK SW Account Global DUNS Number");
        m.put("mdmLevel2AccountId", "LXK SW Account Domestic DUNS Number");
        m.put("mdmLevel3AccountId", "LXK SW MDM Legal Entity ID #");
        m.put("mdmLevel4AccountId", "LXK SW MDM Account#");
        m.put("mdmLevel5AccountId", "LXK Parent Row Id");        
//        m.put("mdmLevel", "");
        return Collections.unmodifiableMap(m);
    }

    @SuppressWarnings("unchecked")
    static <T> List<T> querySiebel(Log logger, Session session, QueryObject criteria, MdmSearchContractBase contract) {
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
