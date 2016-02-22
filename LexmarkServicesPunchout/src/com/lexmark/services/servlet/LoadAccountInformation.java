package com.lexmark.services.servlet;


import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import com.lexmark.contract.AccountAgreementSoldToContract;
import com.lexmark.domain.PunchoutAccount;
import com.lexmark.result.AccountAgreementSoldToResult;
import com.lexmark.service.api.CustomerPaymentsService;
import com.lexmark.services.util.ContractFactory;
import com.lexmark.services.util.ControllerUtil;
import com.lexmark.services.util.ObjectDebugUtil;

public class LoadAccountInformation{
	
	@Autowired
	private CustomerPaymentsService customerPaymentsService;
	
	private Map<String,List<PunchoutAccount>> globalAccountMap= new HashMap<String, List<PunchoutAccount>>();
	
	
	private static Logger LOGGER = LogManager.getLogger(LoadAccountInformation.class);
	
	public String acntType;
	
	/**
	 * Init Account Info
	 */
	private void initAccountInformation(String siebelValue){
		LOGGER.debug("[ In initAccountInformation ]");
		LOGGER.debug("siebelValue------------->"+siebelValue);
		 List<PunchoutAccount> allAccountList=null;
		String accName = ControllerUtil.portalSiebelLocalization("ARIBA_ACCOUNTS",siebelValue);
		try{
		AccountAgreementSoldToContract contract =ContractFactory.getAllSiebelAccountListContract();	
		contract.setAccountName(accName);
		ObjectDebugUtil.printObjectContent(contract, LOGGER);
		AccountAgreementSoldToResult siebelAccountListResult= customerPaymentsService.retrieveMPSB2BList(contract);
		if(siebelAccountListResult.getMpsB2bList()!=null && siebelAccountListResult.getMpsB2bList().size()!=0){
			LOGGER.debug("INSIDE IF :SIZE IS NOT ZERO");
		allAccountList=(Collections.unmodifiableList(siebelAccountListResult.getMpsB2bList()));
		globalAccountMap.put(siebelValue, allAccountList); 
		}
		LOGGER.debug(String.format("[ account list size is %s]",allAccountList.size()));
		}catch(Exception e){			
			LOGGER.debug(" [ Failed to load All Siebel Account List Contract]");
			
		}
		LOGGER.debug("[ Out initAccountInformation ]");
		
	}
	
  	/**
	 * @return List 
	 */
	 public synchronized List<PunchoutAccount> getAllAccountList(String siebelValue){
		 LOGGER.debug("Siebel Value"+siebelValue);
		 List<PunchoutAccount> allAccountList=null;
		 if(globalAccountMap.size()>0){
			 allAccountList=globalAccountMap.get(siebelValue);
		 }
		 if(allAccountList != null){
			 LOGGER.debug("all account list size is "+allAccountList.size());
			return allAccountList;
		 }
		 
		initAccountInformation(siebelValue);
		allAccountList=globalAccountMap.get(siebelValue);
		LOGGER.debug("Init account information call " + allAccountList.size());
		 return allAccountList;
	 }
	


	
}
