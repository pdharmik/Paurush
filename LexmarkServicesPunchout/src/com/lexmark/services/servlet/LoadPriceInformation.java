package com.lexmark.services.servlet;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.googlecode.ehcache.annotations.Cacheable;
import com.lexmark.contract.AccountAgreementSoldToContract;
import com.lexmark.contract.HardwareCatalogContract;
import com.lexmark.contract.PriceContract;
import com.lexmark.domain.Bundle;
import com.lexmark.domain.HardwareCatalog;
import com.lexmark.domain.Price;
import com.lexmark.domain.PunchoutAccount;
import com.lexmark.result.AccountAgreementSoldToResult;
import com.lexmark.result.HardwareCatalogResult;
import com.lexmark.result.PriceResult;
import com.lexmark.service.api.CustomerPaymentsService;
import com.lexmark.service.api.OrderHardwareService;
import com.lexmark.services.api.RetrievePriceService;
import com.lexmark.services.util.ContractFactory;
import com.lexmark.services.util.ObjectDebugUtil;

public class LoadPriceInformation{
	private static Logger LOGGER = LogManager.getLogger(LoadPriceInformation.class);
	private List<PunchoutAccount> allAccountList=new LinkedList<PunchoutAccount>();
	/** Map for LoadPriceInformation */
	public static Map<String, Map<String,List<HardwareCatalog>>> allBundlePriceMap = new HashMap<String, Map<String,List<HardwareCatalog>>>();
	
	@Autowired
	private CustomerPaymentsService customerPaymentsService;
	@Autowired
    private OrderHardwareService orderHardwareService;	
	@Autowired 
	private RetrievePriceService retrievePriceService;
	
	/**
	 * @return List 
	 */
	@Cacheable(cacheName="retrieveAccountAgreementList", keyGeneratorName="myKeyGenerator")
	public List<PunchoutAccount> getAllAccountList(){
		LOGGER.debug("[ In getAllAccountList ]");
		
		try{
		
		AccountAgreementSoldToContract contract =ContractFactory.getAllSiebelAccountListContract();
		ObjectDebugUtil.printObjectContent(contract, LOGGER);
		AccountAgreementSoldToResult siebelAccountListResult= customerPaymentsService.retrieveMPSB2BList(contract);
		allAccountList=(Collections.unmodifiableList(siebelAccountListResult.getMpsB2bList()));
		LOGGER.debug(String.format("[ account list size is %s]",allAccountList.size()));
		}catch(Exception e){			
			LOGGER.debug(" [ Failed to load All Siebel Account List Contract]");
			
		}
		
		LOGGER.debug("[ Out getAllAccountList ]");
		return allAccountList;
	}
	
 
	  /**
	 * @return Map 
	 * @throws Exception 
	 */
	 @Cacheable(cacheName="retrievePriceList", keyGeneratorName="myKeyGenerator")
	 public Map<String, Map<String,List<HardwareCatalog>>> getAllBundlePriceMap() throws Exception{
		 allAccountList = getAllAccountList();
		 List<String> accountList = new ArrayList<String>();
		 Map<String,Set<String>> accountContractMap = new HashMap<String,Set<String>>();
		 Map<String,PunchoutAccount> accountPunchoutMap = new HashMap<String,PunchoutAccount>();	 
		 int acntCnt = 0;
		 
		 for(PunchoutAccount account:allAccountList){
			 acntCnt = acntCnt + 1;
			 PunchoutAccount punchoutAcct = new PunchoutAccount();
			 
			 if(accountList.isEmpty() && acntCnt == 6 && account.getAccountId()!=null)
			 {
				 accountList.add(account.getAccountId());
				 punchoutAcct.setAgreementId(account.getAgreementId());
				 punchoutAcct.setSoldTo(account.getSoldTo());
				 punchoutAcct.setContractNumber(account.getContractNumber());
				 accountPunchoutMap.put(account.getAccountId(), punchoutAcct);
				 LOGGER.debug("accountID "+account.getAccountId());
				 ObjectDebugUtil.printObjectContent(punchoutAcct, LOGGER);
			 }
			 /*if(accountList.isEmpty()){
				 if(account.getAccountId()!=null){
					 accountList.add(account.getAccountId());
					 punchoutAcct.setAgreementId(account.getAgreementId());
					 punchoutAcct.setSoldTo(account.getSoldTo());
					 punchoutAcct.setContractNumber(account.getContractNumber());
					 accountPunchoutMap.put(account.getAccountId(), punchoutAcct);
					 LOGGER.debug("accountID "+account.getAccountId());
					 ObjectDebugUtil.printObjectContent(punchoutAcct, LOGGER);
				 }
			 }else{
				 if(account.getAccountId()!=null){
					 if(!accountList.contains(account.getAccountId())){
						 accountList.add(account.getAccountId());
						 punchoutAcct.setAgreementId(account.getAgreementId());
						 punchoutAcct.setSoldTo(account.getSoldTo());
						 punchoutAcct.setContractNumber(account.getContractNumber());
						 accountPunchoutMap.put(account.getAccountId(), punchoutAcct);
						 LOGGER.debug("accountID "+account.getAccountId());
						 ObjectDebugUtil.printObjectContent(punchoutAcct, LOGGER);
					 }
				 }
			 }*/
		 }
		 
		 for(String accountId:accountList){			 
			 List<String> contractList = new ArrayList<String>();
			 for(PunchoutAccount account:allAccountList){				 
				 if(account.getAccountId().equalsIgnoreCase(accountId)){
					 contractList.add(account.getContractNumber());					 
				 }
			 }
			 Set<String> contractSet = new HashSet<String>(contractList);
			 accountContractMap.put(accountId, contractSet);
		 }
		 
		 Iterator<Entry<String, Set<String>>> i = accountContractMap.entrySet().iterator();
		 while(i.hasNext()){
			 Map<String,List<HardwareCatalog>> contractCatalogMap = new HashMap<String,List<HardwareCatalog>>();
			 Entry<String, Set<String>> entry = i.next();
			 String accountId = entry.getKey();
			 Set<String> contractSet = entry.getValue();
			 PunchoutAccount intPunchout = accountPunchoutMap.get(accountId);
			 Iterator<String> contractIt = contractSet.iterator();
			 while(contractIt.hasNext()){
				 String contractNumber = contractIt.next();
				 LOGGER.debug("Contract number loop and contract is "+contractNumber);
				 List<HardwareCatalog> hardwareCatalogList = new ArrayList<HardwareCatalog>();
				 HardwareCatalogContract contract = new HardwareCatalogContract();
				 /*CrmSessionHandle crmSessionHandle = globalService.initCrmSessionHandle(null);
				 PortalSessionUtil.setSiebelCrmSessionHandle(request, crmSessionHandle);
				 CrmSessionHandle crmSessionHandle1 = globalService.initCrmSessionHandle(PortalSessionUtil.getSiebelCrmSessionHandle(request));*/
				 contract.setAgreementId(intPunchout.getAgreementId());
				 //contract.setSessionHandle(crmSessionHandle1);
				 contract.setSoldToNumber(intPunchout.getSoldTo());
				 contract.setContractNumber(contractNumber);
				 contract.setEffectiveDate(new Date());
				 contract.setNewQueryIndicator(true);
				 ObjectDebugUtil.printObjectContent(contract,LOGGER);				 
				 HardwareCatalogResult hardwareCatalogResult = orderHardwareService.retrieveHardwareList(contract);
				 if(hardwareCatalogResult!=null){
					 HardwareCatalog hardwareCatalog = hardwareCatalogResult.getHardwareCatalog();
					 PriceResult bundlePriceResult = null;
					 List<Bundle> bundleList = hardwareCatalog.getBundleList();
						
						if(bundleList!=null && !(bundleList.isEmpty()) && bundleList.get(0)!=null){
							String contractNo = bundleList.get(0).getContractNumber()!= null ?bundleList.get(0).getContractNumber():"";
							PriceContract priceContract = ContractFactory.getHardwareBundlePriceContract(bundleList,contractNo);
							LOGGER.debug("************PRINTING PRICE CONTRACT***************");
							ObjectDebugUtil.printObjectContent(priceContract,LOGGER);
							try{
								/*Price call to SAP start*/
								bundlePriceResult = retrievePriceService.retrievePriceList(priceContract);
							}catch(Exception e){
								LOGGER.error("Exception occured");
							}
						}
						
						for(Bundle bundle:bundleList){
							if(bundlePriceResult!=null){
								for(Price priceOutputLine:bundlePriceResult.getPriceOutputList()){
									if(priceOutputLine.getContractLineItemId().equalsIgnoreCase(bundle.getContractLineItemId())){
										if(priceOutputLine.getPrice()!=null && priceOutputLine.getPrice()!=""){
											bundle.setPrice(new BigDecimal(priceOutputLine.getPrice()));
											bundle.setCurrency(priceOutputLine.getCurrency());
											LOGGER.debug("bundle.getContractLineItemId--->"+bundle.getContractLineItemId());
											LOGGER.debug("bundle price is "+priceOutputLine.getPrice());
											LOGGER.debug("bundle currency is "+priceOutputLine.getCurrency());
										}
									}
								}
							}
						}
						LOGGER.debug("Adding to hardware catalog list");
						hardwareCatalogList.add(hardwareCatalog);						
				 }
				 LOGGER.debug("hardware catalog list size "+hardwareCatalogList.size());
				 contractCatalogMap.put(contractNumber, hardwareCatalogList);
				 LOGGER.debug("catalog map size is "+contractCatalogMap.size());
			 }
			 LOGGER.debug("Adding to all bundle price map");
			 LOGGER.debug("catalog map size after loop is "+contractCatalogMap.size());
			 LOGGER.debug("Account id is "+accountId);
			 allBundlePriceMap.put(accountId, contractCatalogMap);
		 }
		 return allBundlePriceMap;
	 }

	
}
