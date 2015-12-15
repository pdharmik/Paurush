package com.lexmark.services.webService.om;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.rpc.holders.StringHolder;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import com.lexmark.contract.PriceContract;
import com.lexmark.domain.Price;
import com.lexmark.priceCalc.ZGS_CRM_ORDER_OUTPUT;
import com.lexmark.result.PriceResult;
import com.lexmark.services.api.om.RetrieveTonerPriceService;
import com.lexmark.tonerPurchasePriceCalc.ZGRFC_PRICING_COND_READ;
import com.lexmark.tonerPurchasePriceCalc.ZGRFC_PRICING_COND_READ_API_BNStub;
import com.lexmark.tonerPurchasePriceCalc.ZGRFC_PRICING_COND_READ_API_Service;
import com.lexmark.tonerPurchasePriceCalc.ZGRFC_PRICING_COND_READ_API_ServiceLocator;
import com.lexmark.tonerPurchasePriceCalc.ZGSV_COMT_PRODUCT_ID;
import com.lexmark.tonerPurchasePriceCalc.ZGSV_PRCT_COND_RATE;
import com.lexmark.tonerPurchasePriceCalc.holders.ZGTTV_PRCT_COND_RATEHolder;


public class RetrieveTonerPriceServiceImpl implements RetrieveTonerPriceService{
	private static final Logger LOGGER = LogManager.getLogger(RetrievePriceServiceImpl.class);
	private String userName; 
	private String password;
	private String address;

	@Override
	public PriceResult retrieveTonerPriceList(PriceContract contract)
			throws Exception {
		PriceResult priceResult = new PriceResult();
		List<Price> priceOutputList = new ArrayList<Price>();
		List<Price> priceInfoList = contract.getPriceList();
		
		int noOfItems = priceInfoList.size();//No of materials
		
		LOGGER.debug("START OF PRICE WEBSERVICE CALL:::");
		ZGRFC_PRICING_COND_READ_API_Service service = new ZGRFC_PRICING_COND_READ_API_ServiceLocator();
		ZGRFC_PRICING_COND_READ port = service.getZGRFC_PRICING_COND_READ_API_BN((new URL(getAddress())));
		
		ZGRFC_PRICING_COND_READ_API_BNStub stub = (ZGRFC_PRICING_COND_READ_API_BNStub)port;
		
		stub.setUsername(getUserName());
		stub.setPassword(getPassword());
		
		//LOGGER.debug("Contract Number "+contract.getContractNumber());
		
		ZGSV_COMT_PRODUCT_ID[] itemArr = new ZGSV_COMT_PRODUCT_ID[noOfItems];
		int itemIndex = 0;
		for(Price priceItem : priceInfoList){			
			ZGSV_COMT_PRODUCT_ID item = new ZGSV_COMT_PRODUCT_ID();
			LOGGER.debug("PART NO::: "+priceItem.getPartNumber());
			item.setPRODUCT_ID(priceItem.getPartNumber());
			itemArr[itemIndex] = item;
			itemIndex++;
		}
		
		ZGSV_PRCT_COND_RATE[] outputArr = new ZGSV_PRCT_COND_RATE[noOfItems];
		ZGTTV_PRCT_COND_RATEHolder outputHolder = new ZGTTV_PRCT_COND_RATEHolder(outputArr);
		StringHolder o_ERROR_MSG = new StringHolder();
		
		LOGGER.debug("BEFORE PRICE WEBSERVICE CALL:::");
		
		if(contract.getContractNumber()!=null && !contract.getContractNumber().equals("")){
			LOGGER.debug("Contract Number in toner Price service::: "+contract.getContractNumber());
			port.ZGRFC_PRICING_COND_READ_API(itemArr, "", contract.getContractNumber(), outputHolder, o_ERROR_MSG);
		}else if(contract.getPoNumber()!=null && !contract.getPoNumber().equals("")){
			LOGGER.debug("PO Number in toner Price service::: "+contract.getPoNumber());
			port.ZGRFC_PRICING_COND_READ_API(itemArr, contract.getPoNumber(), "", outputHolder, o_ERROR_MSG);
		}else{
			int totalCount = priceInfoList.size();
			
			for (int i=0;i<totalCount;i++){
				Price price = new Price();
				for(Price priceItem : priceInfoList){
		                price.setPartNumber(priceItem.getPartNumber());
		                price.setPrice("");
		                price.setCurrency("");
		    			priceOutputList.add(price);
				}
		    }			
			priceResult.setPriceOutputList(priceOutputList);
			return priceResult;
		}
		
		LOGGER.debug("AFTER PRICE WEBSERVICE CALL:::");
		
		if(outputHolder!=null && outputHolder.value.length >0)
		{
			int totalCount = outputHolder.value.length;
			
			for (int i=0;i<totalCount;i++)
			{
				ZGSV_PRCT_COND_RATE outputval = outputHolder.value[i];
				Price price = new Price();
				for(Price priceItem : priceInfoList){
					if(priceItem.getPartNumber().equalsIgnoreCase(outputval.getPRODUCT_ID())){
						LOGGER.debug("Part Number "+outputval.getPRODUCT_ID());
		                LOGGER.debug("price "+outputval.getRATE());
		                LOGGER.debug("currency "+outputval.getCURRENCY());
		                price.setPartNumber(outputval.getPRODUCT_ID());
		                price.setPrice(outputval.getRATE().toString().trim());
		                price.setCurrency(outputval.getCURRENCY());
		    			priceOutputList.add(price);
					}
					
				}
		    }
		}
		priceResult.setPriceOutputList(priceOutputList);
		
		return priceResult;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	
	
	
}