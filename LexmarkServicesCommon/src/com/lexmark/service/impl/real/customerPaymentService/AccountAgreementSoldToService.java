package com.lexmark.service.impl.real.customerPaymentService;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.amind.data.service.ActionEvent;
import com.amind.data.service.QueryObject;
import com.amind.session.Session;
import com.lexmark.contract.AccountAgreementSoldToContract;
import com.lexmark.domain.PunchoutAccount;
import com.lexmark.result.AccountAgreementSoldToResult;
import com.lexmark.service.impl.real.domain.AccountAgreementSoldToDo;
import com.lexmark.util.LangUtil;


/**
 * @author imdzeluri
 */
public class AccountAgreementSoldToService {
    
	private static final Logger logger = LogManager.getLogger(AccountAgreementSoldToService.class);
    private static final Class<?> DO_CLASS = AccountAgreementSoldToDo.class;
    private Session session;

    public AccountAgreementSoldToService(Session session) {
        this.session = session;
    }

    @SuppressWarnings("unchecked")
	public AccountAgreementSoldToResult retrieveMPSB2BList(AccountAgreementSoldToContract contract) {
        logger.debug("[IN] retrieveAccountAgreementSoldTo");
        try {
            String searchExpression = "[LXK MPS B2B Flag]='Y'";
			if (LangUtil.isNotEmpty(contract.getAccountName())) {
				searchExpression += " AND [LXK MPS Account Name] LIKE '"+contract.getAccountName()+"*'";
			}
            QueryObject queryObject = new QueryObject(DO_CLASS, ActionEvent.QUERY);
            queryObject.addComponentSearchSpec(DO_CLASS, searchExpression);
            
			List<AccountAgreementSoldToDo> doList = this.session.getDataManager().query(queryObject);
            List<PunchoutAccount> mpsB2bList = toMpsB2bList(doList);
            return new AccountAgreementSoldToResult(mpsB2bList);
        } finally {
           logger.debug("[OUT] retrieveAccountAgreementSoldTo");
        }        
    }
    
    private List<PunchoutAccount> toMpsB2bList(List<AccountAgreementSoldToDo> doList) {
    	List<PunchoutAccount> result = new ArrayList<PunchoutAccount>();
    	
    	for (AccountAgreementSoldToDo mpsB2B : LangUtil.notNull(doList)) {
    		PunchoutAccount item = new PunchoutAccount(); 
    		item.setAccountId(mpsB2B.getAccountId());
    		item.setAgreementId(mpsB2B.getAgreementId());
    		item.setContactId(mpsB2B.getContactId());
    		item.setContractName(mpsB2B.getContractName().trim());
    		item.setContractNumber(mpsB2B.getContractNumber());
    		item.setSoldTo(mpsB2B.getSoldTo());
    		item.setSoldToType(mpsB2B.getSoldToType());
    		if (isRecordDistinct(item, result)) {
				result.add(item);
			}
		}
    	
		return result;
	}

	private boolean isRecordDistinct(PunchoutAccount item, List<PunchoutAccount> result) {
		for (PunchoutAccount punchoutAccount : LangUtil.notNull(result)) {
			if (item.getContractNumber() != null) {
				if (punchoutAccount.getContractNumber().equalsIgnoreCase(item.getContractNumber())) {
					return false;
				}
			}
		}
		return true;
	}
    
}
