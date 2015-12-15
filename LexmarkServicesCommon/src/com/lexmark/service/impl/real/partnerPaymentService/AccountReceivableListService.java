package com.lexmark.service.impl.real.partnerPaymentService;

import static com.lexmark.service.impl.real.partnerPaymentService.PartnerPaymentUtil.isSearchByMdm;
import static com.lexmark.service.impl.real.partnerPaymentService.PartnerPaymentUtil.querySiebel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.logging.log4j.LogManager;

import com.amind.common.service.SiebelBusinessServiceProxy;
import com.amind.data.service.QueryObject;
import com.amind.session.Session;
import com.lexmark.contract.AccountReceivableListContract;
import com.lexmark.contract.api.MdmSearchContractBase;
import com.lexmark.domain.AccountReceivable;
import com.lexmark.domain.GenericAddress;
import com.lexmark.result.AccountReceivableListResult;
import com.lexmark.service.api.SiebelCrmServiceException;
import com.lexmark.service.impl.real.AmindCrmSessionHandle;
import com.lexmark.service.impl.real.domain.AccountReceivableDo;
import com.lexmark.service.impl.real.util.AmindServiceUtil;
import com.lexmark.util.LangUtil;
import org.apache.logging.log4j.Logger;

/**
 * @author vpetruchok
 * @version 1.0, 2012-10-09
 */
public class AccountReceivableListService {
    
    //private static final Log logger = LogFactory.getLog(AccountReceivableListService.class);
	private static final Logger logger = LogManager.getLogger(AccountReceivableListService.class);
    private static final Class<?> DO_CLASS = AccountReceivableDo.class;
    private static final Map<String, String> FIELD_MAP = PartnerPaymentUtil.LXK_COMPANY_CODES_FIELD_MAP;
    private Session session;
    private AmindCrmSessionHandle crmSessionHandle;

    public AccountReceivableListService(Session session, AmindCrmSessionHandle crmSessionHandle) {
        this.session = session;
        this.crmSessionHandle = crmSessionHandle;
    }

    public AccountReceivableListResult retrieveAccountReceivableList(AccountReceivableListContract contract) {
        logger.debug("[IN] retrieveAccounReceivalbeList");
        try {
            checkRequiredFields(contract);
            
            String searchExpression = buildSearchExpression(contract, FIELD_MAP);
            QueryObject queryObject = AmindServiceUtil.newQueryObject(DO_CLASS, searchExpression, contract.getIncrement(), contract.getStartRecordNumber()); 
            
            if (contract.hasSortCriteria()) {
                queryObject.setSortString(AmindServiceUtil.buildSortString(contract.getSortCriteria(), FIELD_MAP));
            }
            
            List<AccountReceivableDo> doList = querySiebel(this.logger, this.session, queryObject, contract);
            int recordsTotalCount = processRecordsTotalCount(contract, this.crmSessionHandle, this.session, searchExpression);
            List<AccountReceivable> accountReceivableList = toAccountReceivableList(doList);
            return new AccountReceivableListResult(accountReceivableList, recordsTotalCount);
        } finally {
           logger.debug("[OUT] retrieveAccountReceivableList");
        }        
    }
    
    private static void checkRequiredFields(MdmSearchContractBase contract) {
        if (!isSearchByMdm(contract)) {
            throw new SiebelCrmServiceException("No mdmId  or mdmLevel specified");
        }
    }
    
    private String buildSearchExpression(MdmSearchContractBase contract, Map<String, String> fieldMap) {
        StringBuilder expr = new StringBuilder();
        expr.append(mdmSearchExpr(contract, fieldMap));
        expr.append(filterCriteriaExpr(contract, fieldMap));
        expr.append(" AND ([LXK SAP Vendor Class] = 'SAP Vendor' AND  [LXK SAP Vendor Account Type] = 'Payee')");
        return expr.toString();
    }
    
    static String filterCriteriaExpr(MdmSearchContractBase contract, Map<String, String> fieldMap) {
        return  contract.hasFilterCriteria() 
                  ?  AmindServiceUtil.buildCriteria(contract.getFilterCriteria(), fieldMap, true, true)
                  : "";
    }
    
    static String mdmSearchExpr(MdmSearchContractBase contract, Map<String, String> fieldMap) {
        return AmindServiceUtil.buildMdmSearchExpressionForMdmLevel(contract.getMdmId(), contract.getMdmLevel(), fieldMap, false, FIELD_MAP.get("mdmLevel"));  
    } 
    
    
    private int processRecordsTotalCount(MdmSearchContractBase contract, AmindCrmSessionHandle crmSessionHandle, Session session, String searchExpression) {
        int count = 0;
        if (contract.isNewQueryIndicator()) {
            logger.debug("[IN] Count Method");
            count = countRecords(session, searchExpression);
            crmSessionHandle.setAccountReceivableCount(count);
            logger.debug("[OUT] Count Method");
        } else {
            count = crmSessionHandle.getAccountReceivableCount(); 
        }
        return count;
    }
    
    static int countRecords(Session session, String searchExpression) {
        SiebelBusinessServiceProxy businessServiceProxy = session.getSiebelBusServiceProxy();
        return AmindServiceUtil.getTotalCount(AccountReceivableDo.BO, AccountReceivableDo.BC, searchExpression, businessServiceProxy);
    }
    
    private List<AccountReceivable> toAccountReceivableList(List<AccountReceivableDo> doList) {
        List<AccountReceivable> result = new ArrayList<AccountReceivable>();
        for (AccountReceivableDo itemDo : LangUtil.notNull(doList))  {
            AccountReceivable item = new AccountReceivable();
            
//            item.setVendorId(itemDo.getVendorId());
//            item.setVendorName(itemDo.getVendorName());
            item.setCompanyCode(itemDo.getCompanyCode());
//            item.setCompanyCodeDescription(itemDo.getCompanyCodeDescription());
//            item.setPayeeName(itemDo.getPayeeName());
            item.setAccountName(itemDo.getPayeeName());
            item.setSoldToNumber(itemDo.getSoldToNumber());
//            
            GenericAddress ga = new GenericAddress();
            ga.setAddressLine1(itemDo.getAddressLine1());
            ga.setAddressLine2(itemDo.getAddressLine2());
            ga.setCity(itemDo.getCity());
            ga.setState(itemDo.getState());
            ga.setProvince(itemDo.getProvince());
            ga.setCountry(itemDo.getCountry());
            ga.setPostalCode(itemDo.getPostalCode());
            item.setBillAddress(ga);
            result.add(item);
        }
        return result;
    }
}
