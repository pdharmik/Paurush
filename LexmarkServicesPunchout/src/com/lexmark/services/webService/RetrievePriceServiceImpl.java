package com.lexmark.services.webService;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import com.lexmark.contract.PriceContract;
import com.lexmark.domain.Price;
import com.lexmark.priceCalc.ZGS_CRM_ORDER_INPUT_ITEM_NO;
import com.lexmark.priceCalc.ZGS_CRM_ORDER_OUTPUT;
import com.lexmark.priceCalc.Zg_get_document_price_bndStub;
import com.lexmark.priceCalc.ZG_GET_DOCUMENT_PRICE_PortType;
import com.lexmark.priceCalc.ZG_GET_DOCUMENT_PRICE_Service;
import com.lexmark.priceCalc.ZG_GET_DOCUMENT_PRICE_ServiceLocator;
import com.lexmark.priceCalc.holders.ZGS_CRM_ORDER_INPUT_ITEM_NO_THolder;
import com.lexmark.priceCalc.holders.ZGS_CRM_ORDER_OUTPUT_THolder;
import com.lexmark.result.PriceResult;
import com.lexmark.services.api.RetrievePriceService;


/**
 * @author wipro
 * @version 2.1
 *
 */
public class RetrievePriceServiceImpl implements RetrievePriceService{
	private static final Logger LOGGER = LogManager.getLogger(RetrievePriceServiceImpl.class);
	private String userName; 
	private String password;
	private String endpointURL;

	@Override
	public PriceResult retrievePriceList(PriceContract contract)
			throws Exception {
		PriceResult priceResult = new PriceResult();
		List<Price> priceOutputList = new ArrayList<Price>();
		List<Price> priceInfoList = contract.getPriceList();
		
		int noOfItems = priceInfoList.size();//No of materials
		
		LOGGER.debug("START OF PRICE WEBSERVICE CALL:::");
		ZG_GET_DOCUMENT_PRICE_Service service = new ZG_GET_DOCUMENT_PRICE_ServiceLocator();
		ZG_GET_DOCUMENT_PRICE_PortType port = service.getzg_get_document_price_bnd((new URL(getEndpointURL())));
		
		Zg_get_document_price_bndStub stub = (Zg_get_document_price_bndStub)port;
		
		stub.setUsername(getUserName());
		stub.setPassword(getPassword());
		
		LOGGER.debug("Contract Number "+contract.getContractNumber());
		ZGS_CRM_ORDER_INPUT_ITEM_NO[] itemArr = new ZGS_CRM_ORDER_INPUT_ITEM_NO[noOfItems];
		int itemIndex = 0;
		for(Price priceItem : priceInfoList){			
			ZGS_CRM_ORDER_INPUT_ITEM_NO item = new ZGS_CRM_ORDER_INPUT_ITEM_NO();
			LOGGER.debug("ITEM NO::: "+priceItem.getContractLineItemId());
			if(priceItem.getContractLineItemId() != null){
				item.setITEM_NO(priceItem.getContractLineItemId());
				itemArr[itemIndex] = item;
				itemIndex++;
			}
			
		}
		
		ZGS_CRM_ORDER_OUTPUT[] outputArr = new ZGS_CRM_ORDER_OUTPUT[noOfItems];
		
		ZGS_CRM_ORDER_INPUT_ITEM_NO_THolder inputHolder = new ZGS_CRM_ORDER_INPUT_ITEM_NO_THolder(itemArr);
		ZGS_CRM_ORDER_OUTPUT_THolder outputHolder = new ZGS_CRM_ORDER_OUTPUT_THolder(outputArr);
		
		LOGGER.debug("BEFORE PRICE WEBSERVICE CALL:::");
	//	port.ZG_GET_DOCUMENT_PRICE(inputHolder, outputHolder, contract.getContractNumber());
		port.ZG_GET_DOCUMENT_PRICE(inputHolder, outputHolder, contract.getContractNumber());
		LOGGER.debug("AFTER PRICE WEBSERVICE CALL:::");
		
		if(outputHolder!=null && outputHolder.value.length >0)
		{
			int totalCount = outputHolder.value.length;
			
			for (int i=0;i<totalCount;i++)
			{
				ZGS_CRM_ORDER_OUTPUT outputval = outputHolder.value[i];
				Price price = new Price();
				for(Price priceItem : priceInfoList){
					if(priceItem.getContractLineItemId() != null){
						if(priceItem.getContractLineItemId().equalsIgnoreCase(outputval.getITEM_NO())){
							LOGGER.debug("Line Item Id "+outputval.getITEM_NO());
			                LOGGER.debug("price "+outputval.getRATE());
			                LOGGER.debug("currency "+outputval.getCURRENCY());
			                price.setContractLineItemId(outputval.getITEM_NO());
			                price.setPrice(outputval.getRATE().toString().trim());
			                price.setCurrency(outputval.getCURRENCY());
			    			priceOutputList.add(price);
						}
					}
					
				}
		    }
		}
		priceResult.setPriceOutputList(priceOutputList);
		
		return priceResult;
	}

	/**
	 * @return String 
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName 
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return String 
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password 
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return String 
	 */
	public String getEndpointURL() {
		return endpointURL;
	}

	/**
	 * @param endpointURL 
	 */
	public void setEndpointURL(String endpointURL) {
		this.endpointURL = endpointURL;
	}
	
	
}