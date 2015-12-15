package com.lexmark.services.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import com.lexmark.contract.GlobalLegalEntityContract;
import com.lexmark.domain.GlobalAccount;
import com.lexmark.result.GlobalLegalEntityResult;
import com.lexmark.service.api.GlobalLegalEntityService;

public class AccountUtil {

	/*Change By Dhirendra on 16.12.2011*/
	private static String l_name="";
	private static String m_id="";
	private static Logger LOGGER = LogManager.getLogger(AccountUtil.class);

	/*----*/
	public static GlobalAccount retrieveAccountByLegalName(GlobalLegalEntityService globalLegalEntityService, String legalName) {
		List<GlobalAccount> entityList = globalLegalEntityService.getGlobalAccounts();
//		PerformanceTracker.endTracking();
		GlobalAccount selectedAccount = null;
		seperateLegalName(legalName);		
		for(GlobalAccount account: entityList) {
			
			if(account.getLegalName().trim().equalsIgnoreCase(l_name.trim())) {
				if(account.getDisplayMdmId() != null && account.getDisplayMdmId().trim().length()>0)
				{
					LOGGER.debug("Account : "+account.getDisplayMdmId()+" M_ID:"+m_id);
					LOGGER.debug("Account name:"+account.getLegalName()+" L_NAME:"+l_name);
					if(account.getDisplayMdmId().trim().equals(m_id.trim()))	
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


	/*Code added by Dhirendra Kumar*/
	public static void seperateLegalName(String legalName)
	{
		Pattern pattern = Pattern.compile("\\(");
		Matcher matcher = pattern.matcher(legalName);
		while(matcher.find())
		{
			l_name=legalName.substring(0, matcher.start()-1);
			m_id=legalName.length()-1 != matcher.start()+1?legalName.substring(matcher.start()+1, legalName.length()-1):"";
}
	}
	/*-----*/

	/**
	 * @author wipro
	 * @category CI5
	 * 
	 * this is for getting the account details from siebel
	 * for the selected customers from report admin
	 */
	public static List<GlobalAccount> getReportCustomersListByLegalName(GlobalLegalEntityService globalLegalEntityService, List<String> legalNameList) {
		List<GlobalAccount> accountList = globalLegalEntityService.getGlobalAccounts();   // coming from siebel...
		List<GlobalAccount> selectedAccountList = new ArrayList<GlobalAccount>();
		
		for(String legalName : legalNameList) {
			GlobalAccount selectedAccount = null;
			seperateLegalName(legalName);
			for(GlobalAccount account : accountList) {
				if(account.getLegalName().trim().equalsIgnoreCase(l_name.trim())) {
					if(account.getDisplayMdmId() != null && account.getDisplayMdmId().trim().length()>0)
					{
						if(account.getDisplayMdmId().trim().equals(m_id.trim()))	
						{
							selectedAccount = account;
							selectedAccountList.add(selectedAccount);
							break;
						}
					}
					else
					{
						selectedAccount = account;
						selectedAccountList.add(selectedAccount);
						break;
					}
				}
			}
		}

		LOGGER.info("#######returning siebel list: " + selectedAccountList.size());
		return selectedAccountList;
	}
	
	/**
	 * @author wipro
	 * @category CI5
	 * 
	 * @param cntrct
	 * @param gal
	 * @return - the account from siebel
	 * 
	 * this will get the legalname and display mdmid for the account
	 * for displaying on report definition page
	 * this code has been written to optimize performance while fetching customer details for a report
	 */
	public static GlobalLegalEntityResult getReportCustomer(GlobalLegalEntityContract cntrct, List<GlobalAccount> accountList) {
		GlobalLegalEntityResult accountResult = new GlobalLegalEntityResult();
		for(GlobalAccount account : accountList) {
			if(account.getMdmId().equals(cntrct.getMdmId()) && account.getMdmLevel().equals(cntrct.getMdmLevel())) {
				accountResult.setAccount(account);
				break;
			}
		}
		return accountResult;
	}
}
