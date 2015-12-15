package com.lexmark.services.util;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.PortletSession;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import com.lexmark.domain.OrderPart;
import com.lexmark.domain.Part;
import com.lexmark.domain.Price;

public class PaymentUtil {
	private static Logger LOGGER = LogManager.getLogger(PaymentUtil.class);
	
	/**
	 * @param portletSession
	 * @param showPriceFlag
	 * @param creditFlag
	 * @param poFlag
	 * @param selectedPaymentType
	 * @param fromPage
	 */
	public static void setFinalCatalogFlags(PortletSession portletSession, String showPriceFlag,String creditFlag,String poFlag,String selectedPaymentType,String fromPage) {
		Map<String,Boolean> catalogFinalFlags=new HashMap<String,Boolean>();
		Boolean finalShowPriceFlag = false;
		Boolean finalCreditFlag = false;
		Boolean finalPOFlag = false;
		Boolean finalTaxCalcFlag = false;
		if(LOGGER.isDebugEnabled()){
			LOGGER.debug("selectedPaymentType "+selectedPaymentType);
			LOGGER.debug("poFlag "+poFlag);
			LOGGER.debug("creditFlag "+creditFlag);
		}
		if(selectedPaymentType.equalsIgnoreCase(ChangeMgmtConstant.PAYMENT_TYPE_PAY_NOW)||selectedPaymentType.equalsIgnoreCase(ChangeMgmtConstant.PAYMENT_TYPE_INDIRECT_INVOICE)){
			if(showPriceFlag.equalsIgnoreCase("Ship and Bill") || showPriceFlag.equalsIgnoreCase("Both")||showPriceFlag.equalsIgnoreCase("Indirect Billing")){
				finalShowPriceFlag = true;
				finalTaxCalcFlag=true;
				if(creditFlag.equalsIgnoreCase("true")){
					finalCreditFlag=true;
					finalPOFlag=true;
				}else{
					finalCreditFlag=false;
					finalPOFlag=true;
				}
			}else{
				finalShowPriceFlag=false;
				finalTaxCalcFlag=false;
				finalCreditFlag=false;
				finalPOFlag=true;
			}		
		}else if(selectedPaymentType.equalsIgnoreCase(ChangeMgmtConstant.HW_PAYMENT_TYPE_PAY_LATER) || selectedPaymentType.equalsIgnoreCase(ChangeMgmtConstant.CONSUMABLE_PAYMENT_TYPE_PAY_LATER)){
			if(showPriceFlag.equalsIgnoreCase("Delayed Purchase") || showPriceFlag.equalsIgnoreCase("Both")){
				finalShowPriceFlag=true;
				finalTaxCalcFlag=false;
				finalCreditFlag=false;
				finalPOFlag=true;
			}else{
				finalShowPriceFlag=false;
				finalTaxCalcFlag=false;
				finalCreditFlag=false;
				finalPOFlag=true;
			}
		}else if(selectedPaymentType.equalsIgnoreCase(ChangeMgmtConstant.HW_PAYMENT_TYPE_UBB) || selectedPaymentType.equalsIgnoreCase(ChangeMgmtConstant.CONSUMABLE_PAYMENT_TYPE_UBB)){			
			finalShowPriceFlag=false;
			finalTaxCalcFlag=false;
			finalCreditFlag=false;
			finalPOFlag=true;
		}
		catalogFinalFlags.put("finalShowPriceFlag",finalShowPriceFlag);
		catalogFinalFlags.put("finalTaxCalcFlag",finalTaxCalcFlag);
		catalogFinalFlags.put("finalCreditFlag",finalCreditFlag);
		catalogFinalFlags.put("finalPOFlag",finalPOFlag);
		if(LOGGER.isDebugEnabled()){
			LOGGER.debug("finalShowPriceFlag"+finalShowPriceFlag);
			LOGGER.debug("finalTaxCalcFlag"+finalTaxCalcFlag);
			LOGGER.debug("finalCreditFlag"+finalCreditFlag);
			LOGGER.debug("finalPOFlag"+finalPOFlag);
		}
		if(fromPage.equalsIgnoreCase("Hardware")){
			portletSession.setAttribute(ChangeMgmtConstant.HWFINALFLAGS, catalogFinalFlags ,PortletSession.APPLICATION_SCOPE);
		}else{
			portletSession.setAttribute(ChangeMgmtConstant.CATFINALFLAGS, catalogFinalFlags ,PortletSession.APPLICATION_SCOPE);
		}
		
	}
	
	/**
	 * @param unitPrice
	 * @param quantity
	 * @return
	 */
	public static String calculateTotalPrice(String unitPrice, String quantity)
	{
		BigDecimal unitPriceBig = new BigDecimal(unitPrice);
	    BigDecimal totalPrice = unitPriceBig.multiply(new BigDecimal(quantity));
				
		return totalPrice.toString();
	}
	
	/**
	 * @param lineItemInfo
	 * @param taxInfo
	 * @return
	 */
	public static Map<String, String> calculateTotalPriceWithTax(List<Object> lineItemInfo, List<Price> taxInfo)
	{
		LOGGER.debug("Inside calculateTotalPriceWithTax::");
		Map<String, String> priceMap = new HashMap<String, String>();
		BigDecimal priceTotal= new BigDecimal("0");
	    BigDecimal taxTotal = new BigDecimal("0");
	    for(Object item : lineItemInfo){
	    	if(item instanceof Part){
	    		Part part = (Part)item;
	    		if( part.getTotalPrice()!= null){
		    		if(!part.getTotalPrice().equals("0") || !part.getTotalPrice().equals("")){
		    			priceTotal = priceTotal.add(new BigDecimal(part.getTotalPrice())) ;
			    		LOGGER.debug("Price :: " + part.getUnitPrice());
		    		}
	    		}
	    	}else if(item instanceof OrderPart){
	    		OrderPart part = (OrderPart)item;
	    		if(part.getTotal()!= null){
		    		if(!part.getTotal().equals("0") || !part.getTotal().equals("")){
		    			priceTotal = priceTotal.add(new BigDecimal(part.getTotal())) ;
		    		}
	    		}
	    	}
	    }
	    for(Price taxItem : taxInfo){
		    	LOGGER.debug("Tax :: " + taxItem.getTax());
		    	if(!taxItem.getTax().equalsIgnoreCase("Unavailable")){
		    		taxTotal = taxTotal.add(new BigDecimal(taxItem.getTax()));
		    	}
	    }
	    
	    BigDecimal grandTotal = priceTotal.add(taxTotal);
	    if(LOGGER.isDebugEnabled()){
		    LOGGER.debug("Inside calculateTotalPriceWithTax Price Total::" + priceTotal.toString());
		    LOGGER.debug("Inside calculateTotalPriceWithTax TaxTotal::" + taxTotal.toString());
		    LOGGER.debug("Inside calculateTotalPriceWithTax GrandTotal::" + grandTotal.toString());
	    }
	    priceMap.put("totalPrice", priceTotal.toString());
	    if(taxTotal.toString().equalsIgnoreCase("0")){
	    	priceMap.put("totalTax", "Unavailable");
	    }else{
	    	priceMap.put("totalTax", taxTotal.toString());
	    }
    	priceMap.put("grandTotal", grandTotal.toString());
    					
		return priceMap;
	}
	
	/**
	 * @param bundlePriceMap
	 * @param acessoriesPriceMap
	 * @param suppliesPriceMap
	 * @return
	 */
	public static Map<String, String> calculateHardwareTotal(Map<String, String> bundlePriceMap,Map<String, String> acessoriesPriceMap,Map<String, String> suppliesPriceMap)
	{	
		LOGGER.debug("Inside calculateHardwareTotal::");
		BigDecimal priceTotal= new BigDecimal("0");
	    BigDecimal taxTotal = new BigDecimal("0");
	    BigDecimal grandTotal = new BigDecimal("0");
	    if(LOGGER.isDebugEnabled()){
		    LOGGER.debug("bundle grandtotal"+new BigDecimal(bundlePriceMap.get("grandTotal")));
		    LOGGER.debug("accessories grandtotal"+new BigDecimal(acessoriesPriceMap.get("grandTotal")));
		    LOGGER.debug("supplies grandtotal"+new BigDecimal(suppliesPriceMap.get("grandTotal")));
		    LOGGER.debug("bundle pricetotal"+new BigDecimal(bundlePriceMap.get("totalPrice")));
		    LOGGER.debug("accessories pricetotal"+new BigDecimal(acessoriesPriceMap.get("totalPrice")));
		    LOGGER.debug("supplies pricetotal"+new BigDecimal(suppliesPriceMap.get("totalPrice")));
		    LOGGER.debug("bundle taxtotal"+new BigDecimal(bundlePriceMap.get("totalTax")));
		    LOGGER.debug("accessories taxtotal"+new BigDecimal(acessoriesPriceMap.get("totalTax")));
		    LOGGER.debug("supplies taxtotal"+new BigDecimal(suppliesPriceMap.get("totalTax")));
	    }
	    grandTotal = grandTotal.add(new BigDecimal(bundlePriceMap.get("grandTotal"))).add(new BigDecimal(acessoriesPriceMap.get("grandTotal"))).add(new BigDecimal(suppliesPriceMap.get("grandTotal")));
	    priceTotal = priceTotal.add(new BigDecimal(bundlePriceMap.get("totalPrice"))).add(new BigDecimal(acessoriesPriceMap.get("totalPrice"))).add(new BigDecimal(suppliesPriceMap.get("totalPrice")));
	    taxTotal = taxTotal.add(new BigDecimal(bundlePriceMap.get("totalTax"))).add(new BigDecimal(acessoriesPriceMap.get("totalTax"))).add(new BigDecimal(suppliesPriceMap.get("totalTax")));
	    if(LOGGER.isDebugEnabled()){
		    LOGGER.debug("Inside calculateTotalPriceWithTax Price Total::" + priceTotal.toString());
		    LOGGER.debug("Inside calculateTotalPriceWithTax Tax Total::" + taxTotal.toString());
		    LOGGER.debug("Inside calculateTotalPriceWithTax Grand Total::" + grandTotal.toString());
	    }
	    Map<String, String> priceMap = new HashMap<String, String>();
	    priceMap.put("totalPrice", priceTotal.toString());
    	priceMap.put("totalTax", taxTotal.toString());
    	priceMap.put("grandTotal", grandTotal.toString());
    					
		return priceMap;
	}

}
