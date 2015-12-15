package com.lexmark.service.impl.real.delegate;

import static com.lexmark.service.impl.real.requestService.AmindRequestDataConversionUtil.convertAccountDoListToAccount;
import static com.lexmark.service.impl.real.util.AmindServiceUtil.buildmdmSearchExpression;
import static com.lexmark.util.LangUtil.isNotEmpty;
import static com.lexmark.util.LangUtil.notNull;

import java.util.List;

import com.amind.data.service.ActionEvent;
import com.amind.data.service.IDataManager;
import com.amind.data.service.QueryObject;
import com.amind.session.Session;
import com.lexmark.contract.SiebelAccountListContract;
import com.lexmark.domain.Account;
import com.lexmark.service.impl.real.domain.AccountByVendorIdDo;
import com.lexmark.service.impl.real.domain.AccountDo;

public class SiebelAccountListService {

	private final String mdmId;
	private final String mdmLevel;
	private String searchExpression;
	private Session session;
	private SiebelAccountListContract contract;

	public SiebelAccountListService(SiebelAccountListContract contract) {
		if(contract == null) {
			throw new IllegalStateException("contract can not be null");
		}
		mdmId = contract.getMdmId();
		mdmLevel = contract.getMdmLevel();
		this.contract = contract;
	}

	public void checkRequiredFields() {
	    if (!isMdmSearch()) {
			throw new IllegalArgumentException("Not given vendorId or mdmId/mdmLevel");
	    }
	}

    private boolean isAccountByVendorIdDoQuery() {
        return isMdmSearch() && contract.isVendorFlag();
    }
    
    private boolean isAccountDoQuery() {
        return isMdmSearch() && !contract.isVendorFlag();
    }
    
	private boolean isMdmSearch() {
        return isNotEmpty(contract.getMdmId()) && isNotEmpty(contract.getMdmLevel());
    }

    public void buildSearchExpression() {
        if (isAccountDoQuery()) {
            searchExpression = buildmdmSearchExpression(mdmId, mdmLevel, null,
                    true, false) + " AND [mdmLevel] ='Siebel' AND ([functionalClassification] = 'DFM' OR [functionalClassification] = 'CSS')";
            
        } else if (isAccountByVendorIdDoQuery()) {
            searchExpression = buildmdmSearchExpression(mdmId, mdmLevel, null,
                    false, false) + " AND [partnerFlag] = 'Y'";
        } else {
            throw new IllegalStateException("Cannot build searchExpression: not given vendorId or mdmId/mdmLevel criteria");
        }
        if(contract.isLbsFlag())
        {
        	searchExpression += " AND [LXK LBS Flag]= 'Y'";
        }
        if (contract.isAgreementFlag()) {
            searchExpression += " AND [agreementName] IS NOT NULL";
        }
       
    }
    
 
	public List<Account> queryAndGetResultList() {
		List<AccountDo> accountDoList = querySiebelAccountList(getSession().getDataManager());
		return convertAccountDoListToAccount(accountDoList);
	}

	@SuppressWarnings("unchecked")
	private List<AccountDo> querySiebelAccountList(IDataManager dataManager) {
		Class<?> doClass ;
		if(isAccountByVendorIdDoQuery()) {
			doClass = AccountByVendorIdDo.class;
		} else  {
			doClass = AccountDo.class;
		}
        
        QueryObject queryReq = new QueryObject(doClass, ActionEvent.QUERY);
        queryReq.setQueryString(searchExpression);
        queryReq.addComponentSearchSpec(doClass, searchExpression);
		
		List<AccountDo> accountDoList = dataManager
				.query(queryReq);
		return notNull(accountDoList);
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
}
