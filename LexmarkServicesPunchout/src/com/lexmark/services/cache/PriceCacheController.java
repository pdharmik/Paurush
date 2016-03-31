package com.lexmark.services.cache;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lexmark.contract.PriceContract;
import com.lexmark.domain.Price;
import com.lexmark.result.PriceResult;
import com.lexmark.services.api.RetrievePriceService;
import com.lexmark.services.util.ContractFactory;
import com.lexmark.services.util.ObjectDebugUtil;

@Controller
@RequestMapping("VIEW")
public class PriceCacheController {
	
	@Autowired
	private CacheManager cacheManager;
	
	@Autowired
	private RetrievePriceService retrievePriceService;
	
	private static Logger LOGGER = LogManager.getLogger(PriceCacheController.class);
	
	/**
	 * @param priceContract
	 * @return Map<ContractLineItemId,Price>
	 */
	public  Map<String, Price> getPrice(PriceContract priceContract){
		LOGGER.debug(" Get price ");
		String contractNumber=priceContract.getContractNumber();
		List<Price> priceList=priceContract.getPriceList();
		Map<String, Price> finalPriceMap=new HashMap<>();
		
		Map<String, Price> priceMapCache=getFromCache(contractNumber);
		Set<String> priceNotInCache=new HashSet<>();// This will contain the contract lineID's which is not there in cache.
		
		for(Price price:priceList){
			String contractLineId=price.getContractLineItemId();
			Price priceFromCache=priceMapCache!=null?priceMapCache.get(contractLineId):null;
			if(priceFromCache==null){
				//Price not there in cache..
				priceNotInCache.add(contractLineId);				
			}else{
				finalPriceMap.put(contractLineId, priceFromCache);
			}
		}
		
		// Check if there is any price remaining then do service call
		Map<String,Price> wsPrice=null;
		if(priceNotInCache.size()>0){
			wsPrice=doPriceCall(priceNotInCache,contractNumber);
		}
		if(wsPrice!=null){
			finalPriceMap.putAll(wsPrice);
			updateCache(wsPrice,contractNumber);
		}
		return finalPriceMap;
	}
	/**
	 * @param retrievePriceService
	 * @param contractLineItems
	 * @param contractNumber
	 * @return
	 * @throws Exception 
	 */
	private  Map<String,Price>  doPriceCall(Set<String> contractLineItems,String contractNumber) {
				PriceContract priceContract =ContractFactory.getPriceContract(contractLineItems, contractNumber);
				ObjectDebugUtil.printObjectContent(priceContract, LOGGER);
				PriceResult result=null;
				try{
					result=retrievePriceService.retrievePriceList(priceContract);
				}catch(Exception e ){
					LOGGER.debug(" Exception occued in retrieving price "+e.getMessage());
				}
				Map<String, Price> priceMap=new HashMap<>();
				if(result!=null){
					List<Price> priceList=result.getPriceOutputList();
					for(Price price:priceList){
						priceMap.put(price.getContractLineItemId(), price);
					}
				}
				
				return priceMap;
	}
	
	private void updateCache(Map<String,Price> priceMap,String contractNumber){
		LOGGER.debug("In updating cache");
		Cache bundleList=cacheManager.getCache("retrievePriceList");
		Element element=bundleList.get(contractNumber);
		// Map<contractNumber,Map<contractLineItemId,Price>>
		 if(element==null){
	        	//Initially it will be null
			 	Map<String,Map<String,Price>> prices= new HashMap<String,Map<String,Price>>();
			 	prices.put(contractNumber, priceMap);
	        	element=new Element(contractNumber,prices);
	        	bundleList.put(element);
	        	
	    }else{
	    	Map<String,Map<String,Price>> prices=(Map<String,Map<String,Price>>)element.getObjectValue();
	        Map<String,Price> contractPrice=prices.get(contractNumber);
	        contractPrice.putAll(priceMap);
	        
	    }
		 getFromCache(contractNumber);
		 
        
	}
	
	private Map<String, Price> getFromCache(String contractNumber){
		Cache bundleList=cacheManager.getCache("retrievePriceList");
		Element element=bundleList.get(contractNumber);
		if(element==null){
			LOGGER.error("element is null");
			return null;
		}
		// Map<contractNumber,Map<contractLineItemId,Price>>
        Map<String,Map<String,Price>> prices=(Map<String,Map<String,Price>>)element.getObjectValue();
        return prices.get(contractNumber);
	}
}
