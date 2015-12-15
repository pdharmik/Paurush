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
import com.lexmark.contract.AccountPayableListContract;
import com.lexmark.contract.api.MdmSearchContractBase;
import com.lexmark.domain.AccountPayable;
import com.lexmark.domain.GenericAddress;
import com.lexmark.result.AccountPayableListResult;
import com.lexmark.service.api.SiebelCrmServiceException;
import com.lexmark.service.impl.real.AmindCrmSessionHandle;
import com.lexmark.service.impl.real.domain.AccountPayableDo;
import com.lexmark.service.impl.real.util.AmindServiceUtil;
import com.lexmark.util.LangUtil;
import org.apache.logging.log4j.Logger;

/**
 * @author vpetruchok
 * @version 1.0, 2012-09-13
 */
public class AccountPayableListService {
    
   // private static final Log logger = LogFactory.getLog(AccountPayableListService.class);
	 private static final Logger logger = LogManager.getLogger(AccountPayableListService.class);
    private static final Class<?> DO_CLASS = AccountPayableDo.class;
    private static final Map<String, String> FIELD_MAP = PartnerPaymentUtil.LXK_COMPANY_CODES_FIELD_MAP; 
    private Session session;
    private AmindCrmSessionHandle crmSessionHandle;

    public AccountPayableListService(Session session, AmindCrmSessionHandle crmSessionHandle) {
        this.session = session;
        this.crmSessionHandle = crmSessionHandle;
    }

    public AccountPayableListResult retrieveAccountPayableList(AccountPayableListContract contract) {
        logger.debug("[IN] retrieveAccountPayableList");
        try {
            checkRequiredFields(contract);
            
            String searchExpression = buildSearchExpression(contract, FIELD_MAP);
            QueryObject queryObject = AmindServiceUtil.newQueryObject(DO_CLASS, searchExpression, contract.getIncrement(), contract.getStartRecordNumber()); 
            
            if (contract.hasSortCriteria()) {
                queryObject.setSortString(AmindServiceUtil.buildSortString(contract.getSortCriteria(), FIELD_MAP));
            }
            
            List<AccountPayableDo> doList = querySiebel(this.logger, this.session, queryObject, contract);
            int recordsTotalCount = processRecordsTotalCount(contract, this.crmSessionHandle, this.session, searchExpression);
            List<AccountPayable> accountPayableList = toAccountPayableList(doList);
            return new AccountPayableListResult(accountPayableList, recordsTotalCount);
        } finally {
           logger.debug("[OUT] retrieveAccountPayableList");
        }        
    }
    
    private static void checkRequiredFields(MdmSearchContractBase contract) {
        if (!isSearchByMdm(contract)) {
            throw new SiebelCrmServiceException("No mdmId  or mdmLevel specified");
        }
    }
    
    private String buildSearchExpression(AccountPayableListContract contract, Map<String, String> fieldMap) {
        StringBuilder expr = new StringBuilder();
        expr.append(mdmSearchExpr(contract, fieldMap));
        expr.append(filterCriteriaExpr(contract, fieldMap));
        expr.append(" AND ([LXK SAP Vendor Class] = 'SAP Vendor' AND  [LXK SAP Vendor Account Type] = 'Vendor')");
        return expr.toString();
    }
    
    static String filterCriteriaExpr(MdmSearchContractBase contract, Map<String, String> fieldMap) {
        return  contract.hasFilterCriteria() 
                  ?  AmindServiceUtil.buildCriteria(contract.getFilterCriteria(), fieldMap, true, true)
                  : "";
    }
    
    private static String mdmSearchExpr(MdmSearchContractBase contract, Map<String, String> fieldMap) {
        return AmindServiceUtil.buildMdmSearchExpressionForMdmLevel(contract.getMdmId(), contract.getMdmLevel(), fieldMap, false, FIELD_MAP.get("mdmLevel"));  
    } 
    
    private int processRecordsTotalCount(MdmSearchContractBase contract, AmindCrmSessionHandle crmSessionHandle, Session session, String searchExpression) {
        int count = 0;
        if (contract.isNewQueryIndicator()) {
            logger.debug("[IN] Count Method");
            count = countRecords(session, searchExpression);
            crmSessionHandle.setAccountPayableCount(count);
            logger.debug("[OUT] Count Method");
        } else {
            count = crmSessionHandle.getAccountPayableCount(); 
        }
        return count;
    }
    
    static int countRecords(Session session, String searchExpression) {
        SiebelBusinessServiceProxy businessServiceProxy = session.getSiebelBusServiceProxy();
        return AmindServiceUtil.getTotalCount(AccountPayableDo.BO, AccountPayableDo.BC, searchExpression, businessServiceProxy);
    }
    
    
    private List<AccountPayable> toAccountPayableList(List<AccountPayableDo> doList) {
        List<AccountPayable> result = new ArrayList<AccountPayable>();
        for (AccountPayableDo itemDo : LangUtil.notNull(doList))  {
            AccountPayable item = new AccountPayable();
            
            item.setVendorId(itemDo.getVendorId());
            item.setVendorName(itemDo.getVendorName());
            item.setCompanyCode(itemDo.getCompanyCode());
            item.setCountry(itemDo.getCompanyCodeDescription());
            item.setPayeeName(itemDo.getPayeeName());
            
            GenericAddress payeeAddress = new GenericAddress();
            payeeAddress.setAddressLine1(itemDo.getAddressLine1());
            payeeAddress.setAddressLine2(itemDo.getAddressLine2());
            payeeAddress.setCity(itemDo.getCity());
            payeeAddress.setState(itemDo.getState());
            payeeAddress.setProvince(itemDo.getProvince());
            payeeAddress.setCountry(itemDo.getCountry());
            payeeAddress.setPostalCode(itemDo.getPostalCode());
            item.setPayeeAddress(payeeAddress);
            
            result.add(item);
        }
        return result;
    }
}
