package com.lexmark.util;

import com.lexmark.domain.GlobalAccount;
import com.lexmark.service.api.GlobalLegalEntityService;


import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class AccountUtil {
	protected static Logger logger = LogManager.getLogger(AccountUtil.class);

	private static class AccountData {
		   public String l_name;
		   public String m_id;
		  }
	
	 public static GlobalAccount retrieveAccountByLegalName(GlobalLegalEntityService globalLegalEntityService, String legalName) {
		 List<GlobalAccount> entityList = globalLegalEntityService.getGlobalAccounts();
			//		PerformanceTracker.endTracking();
			GlobalAccount selectedAccount = null;
		  AccountData data = seperateLegalName(legalName);
		  logger.info(data.l_name); //Example
		  for(GlobalAccount account: entityList) {
				
				if(account.getLegalName().trim().equalsIgnoreCase(data.l_name.trim())) {
					if(account.getDisplayMdmId() != null && account.getDisplayMdmId().trim().length()>0)
					{
						logger.debug("Account : "+account.getDisplayMdmId()+" M_ID:"+data.m_id);
						logger.debug("Account name:"+account.getLegalName()+" L_NAME:"+data.l_name);
						if(account.getDisplayMdmId().trim().equals(data.m_id.trim()))	
						{
							selectedAccount = account;
							break;
						}
					}
					else
					{
						selectedAccount = account;
						break;
					}
				}
			}
		  return selectedAccount;
		 }
	 
	private static AccountData seperateLegalName(String legalName) {
		  AccountData data = new AccountData();

			Pattern pattern = Pattern.compile("\\(");
			Matcher matcher = pattern.matcher(legalName);
			while(matcher.find())
			{
				data.l_name=legalName.substring(0, matcher.start()-1);
				data.m_id=legalName.length()-1 != matcher.start()+1?legalName.substring(matcher.start()+1, legalName.length()-1):"";
				//logger.info("########inside while block....mdmid/name: " + l_name + "//" + m_id);
			}
		return data;
	}
}

