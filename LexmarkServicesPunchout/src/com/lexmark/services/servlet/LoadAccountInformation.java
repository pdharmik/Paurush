package com.lexmark.services.servlet;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.lexmark.contract.AccountAgreementSoldToContract;
import com.lexmark.domain.PunchoutAccount;
import com.lexmark.result.AccountAgreementSoldToResult;
import com.lexmark.service.api.CustomerPaymentsService;
import com.lexmark.services.util.ContractFactory;
import com.lexmark.services.util.ObjectDebugUtil;

public class LoadAccountInformation implements ApplicationContextAware{
	
	@Autowired
	private CustomerPaymentsService customerPaymentsService;
	
	
	private List<PunchoutAccount> allAccountList=new LinkedList<PunchoutAccount>();
	
	
	private static Logger LOGGER = LogManager.getLogger(LoadAccountInformation.class);
	
	public String acntType;
	
	/**
	 * Init Account Info
	 */
	private void initAccountInformation(){
		LOGGER.debug("[ In initAccountInformation ]");
		
		try{
		
		AccountAgreementSoldToContract contract =ContractFactory.getAllSiebelAccountListContract();
		LOGGER.debug("acntType:::"+acntType);
		//contract.setAccountName(acntType);
		ObjectDebugUtil.printObjectContent(contract, LOGGER);
		AccountAgreementSoldToResult siebelAccountListResult= customerPaymentsService.retrieveMPSB2BList(contract);
		if(siebelAccountListResult.getMpsB2bList()!=null && siebelAccountListResult.getMpsB2bList().size()!=0){
			LOGGER.debug("INSIDE IF :SIZE IS NOT ZERO");
		allAccountList=(Collections.unmodifiableList(siebelAccountListResult.getMpsB2bList()));
		}
		LOGGER.debug(String.format("[ account list size is %s]",allAccountList.size()));
		}catch(Exception e){			
			LOGGER.debug(" [ Failed to load All Siebel Account List Contract]");
			
		}
		
		
		for(PunchoutAccount acc:allAccountList){
			LOGGER.debug(acc);
		}
		LOGGER.debug("[ Out initAccountInformation ]");
		
	}
	
  	/**
	 * @return List 
	 */
	 public synchronized List<PunchoutAccount> getAllAccountList(){
		 if(allAccountList!=null && !allAccountList.isEmpty()){
			 return allAccountList;
		 }
		 initAccountInformation();	
		 return allAccountList;
	 }

	/**
	 * forcr refresh 
	 */
	public void forceRefresh(){
		LOGGER.debug(" [ In force Refresh ]");
		initAccountInformation();
		LOGGER.debug(" [ Out force Refresh ]");
	} 

	 /**
		 * @param arg0 
		 * @throws BeansException 
		 */
	public void setApplicationContext(ApplicationContext arg0)
			throws BeansException {
		LOGGER.debug("[ In setApplicationContext Event ] ");
		initAccountInformation();
		LOGGER.debug("[ Out setApplicationContext Event ] ");
		
	}

	
}
