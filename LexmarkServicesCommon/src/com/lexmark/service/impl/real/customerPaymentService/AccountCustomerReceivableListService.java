package com.lexmark.service.impl.real.customerPaymentService;

import static com.lexmark.service.impl.real.customerPaymentService.CustomerPaymentUtil.isSearchByMdm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.logging.log4j.LogManager;

import com.amind.common.service.SiebelBusinessServiceProxy;
import com.amind.data.service.ActionEvent;
import com.amind.data.service.QueryObject;
import com.amind.session.Session;
import com.lexmark.contract.AccountReceivableListContract;
import com.lexmark.contract.api.MdmSearchContractBase;
import com.lexmark.domain.AccountCustomerReceivable;
import com.lexmark.domain.CompanyCode;
import com.lexmark.domain.GenericAddress;
import com.lexmark.result.AccountCustomerReceivableListResult;
import com.lexmark.service.api.SiebelCrmServiceException;
import com.lexmark.service.impl.real.domain.AccountCustomerReceivableDo;
import com.lexmark.service.impl.real.domain.CompanyCodeDo;
import com.lexmark.service.impl.real.domain.CustomerSapAccountBusinessAddressDo;
import com.lexmark.service.impl.real.domain.CustomerSapAccountDo;
import com.lexmark.service.impl.real.util.AmindServiceUtil;
import com.lexmark.util.LangUtil;

import org.apache.logging.log4j.Logger;
import static com.lexmark.util.LangUtil.isNotEmpty;


/**
 * @author imdzeluri
 */
public class AccountCustomerReceivableListService {
    
    //private static final Log logger = LogFactory.getLog(AccountCustomerReceivableListService.class);
	private static final Logger logger = LogManager.getLogger(AccountCustomerReceivableListService.class);
    private static final Class<?> DO_CLASS = CustomerSapAccountDo.class;
    private static final Map<String, String> FIELD_MAP = CustomerPaymentUtil.LXK_COMPANY_CODES_FIELD_MAP;
    private static final Map<String, String> RECEIVABLE_LIST_FIELD_MAP = populateReceivableFieldMap();
    private Session session;

    public AccountCustomerReceivableListService(Session session) {
        this.session = session;
    }

    @SuppressWarnings("unchecked")
	public AccountCustomerReceivableListResult retrieveAccountReceivableList(AccountReceivableListContract contract) {
        logger.debug("[IN] retrieveAccounReceivalbeList");
        try {
            checkRequiredFields(contract);
            
            String searchExpression = buildSearchExpression(contract, FIELD_MAP, RECEIVABLE_LIST_FIELD_MAP);
            QueryObject queryObject = new QueryObject(DO_CLASS, ActionEvent.QUERY);
            queryObject.addComponentSearchSpec(DO_CLASS, searchExpression);
            if (contract.hasSortCriteria()) {
                queryObject.setSortString(AmindServiceUtil.buildSortString(contract.getSortCriteria(), RECEIVABLE_LIST_FIELD_MAP));
            }
          
			List<CustomerSapAccountDo> doList = this.session.getDataManager().query(queryObject);
			List<AccountCustomerReceivable> accountReceivableList = toAccountCustomerReceivableList(doList);
            return new AccountCustomerReceivableListResult(accountReceivableList, accountReceivableList.size());
        } finally {
           logger.debug("[OUT] retrieveAccountReceivableList");
        }        
    }
    
    private static void checkRequiredFields(MdmSearchContractBase contract) {
        if (!isSearchByMdm(contract)) {
            throw new SiebelCrmServiceException("No mdmId  or mdmLevel specified");
        }
    }
    
    private String buildSearchExpression(MdmSearchContractBase contract, Map<String, String> fieldMap, Map<String, String> receivableListFieldMap) {
        StringBuilder expr = new StringBuilder();
        expr.append(mdmSearchExpr(contract, fieldMap));
		expr.append(" AND [");
		expr.append("LXK MPS View Billing Report");
		expr.append("] = 'Y'");
		expr.append(filterCriteriaExpr(contract, receivableListFieldMap));
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
    
    static int countRecords(Session session, String searchExpression) {
        SiebelBusinessServiceProxy businessServiceProxy = session.getSiebelBusServiceProxy();
        return AmindServiceUtil.getTotalCount("LXK SW Account", "LXK SW Account", searchExpression, businessServiceProxy);
    }
    
    
    private List<AccountCustomerReceivable> toAccountCustomerReceivableList(List<CustomerSapAccountDo> doList) {
        List<AccountCustomerReceivable> result = new ArrayList<AccountCustomerReceivable>();
        
//        for (AccountCustomerReceivableDo accountCustomerReceivableDo : LangUtil.notNull(doList))  {
        //	for (CustomerSapAccountDo customerSapAccountDo : LangUtil.notNull(accountCustomerReceivableDo.getCustomerSapAccount())) {
        for (CustomerSapAccountDo customerSapAccountDo : LangUtil.notNull(doList)) {
        	
        	if(itemIsUnique(customerSapAccountDo, result)) {
					AccountCustomerReceivable item = new AccountCustomerReceivable();
					
					item.setSoldTo(customerSapAccountDo.getSoldTo());
					item.setBillTo(customerSapAccountDo.getBillTo());
					item.setCustomerNumber(customerSapAccountDo.getSalesOrg());
					item.setAccountName(customerSapAccountDo.getAccountName());
					
					for (CompanyCodeDo companyCodes :LangUtil.notNull(customerSapAccountDo.getCompanyCodeDo())) {
						List<CompanyCode> cCodes = new ArrayList<CompanyCode>();
				        CompanyCode cc = new CompanyCode();
						cc.setCompanyDescription(companyCodes.getCompanyDescription());
						cc.setCompanyValue(companyCodes.getValue());
						cCodes.add(cc);
						item.setCompanyCodes(cCodes);
					}
					
					GenericAddress billingAddress = new GenericAddress();
					
					for (CustomerSapAccountBusinessAddressDo customerSapAccountBusinessAddressDo : LangUtil.notNull(customerSapAccountDo.getCustomerSapAccountBusinessAddress())) {
						billingAddress = new GenericAddress();
						billingAddress.setAddressLine1(customerSapAccountBusinessAddressDo.getStreetAddress());
						billingAddress.setAddressLine2(customerSapAccountBusinessAddressDo.getStreetAddress2());
						billingAddress.setCity(customerSapAccountBusinessAddressDo.getCity());
						billingAddress.setState(customerSapAccountBusinessAddressDo.getState());
						billingAddress.setProvince(customerSapAccountBusinessAddressDo.getProvince());
						billingAddress.setCountry(customerSapAccountBusinessAddressDo.getCountry());
						billingAddress.setPostalCode(customerSapAccountBusinessAddressDo.getPostalCode());
					}
					item.setBillAddress(billingAddress);
					result.add(item);
        		}
			}
//        }
        return result;
    }
    
    
    private static Map<String, String> populateReceivableFieldMap() {
        Map<String, String> m = new HashMap<String, String>();
        m.put("accountName", "Name");
        return Collections.unmodifiableMap(m);
	}
    
    private boolean itemIsUnique(CustomerSapAccountDo customerSapAccountDo, List<AccountCustomerReceivable> result) {
    	
    	if(customerSapAccountDo == null) { 
    		return false;
    	}
    	for (AccountCustomerReceivable accountCustomerReceivable : result) {
			if(accountCustomerReceivable.getSoldTo().equalsIgnoreCase(customerSapAccountDo.getSoldTo())) {
				if(LangUtil.isNotEmpty(customerSapAccountDo.getCompanyCodeDo()) || customerSapAccountDo.getCompanyCodeDo() != null){
					for (CompanyCodeDo cdo : customerSapAccountDo.getCompanyCodeDo()) {
						if (!checkDuplicate(
								accountCustomerReceivable.getCompanyCodes(),
								cdo.getValue())) {
							CompanyCode cc = new CompanyCode();
							cc.setCompanyValue(cdo.getValue());
							cc.setCompanyDescription(cdo
									.getCompanyDescription());
							accountCustomerReceivable.getCompanyCodes().add(cc);
						}
					}
				}
				return false;
			}
		}
    	
    	return true;
	}

	private static boolean checkDuplicate( List<CompanyCode> result,String value) {
		for (CompanyCode cc : result) {
			if (value.equalsIgnoreCase(cc.getCompanyValue())) {
				return true;
			}
		}
		return false;
	}

}