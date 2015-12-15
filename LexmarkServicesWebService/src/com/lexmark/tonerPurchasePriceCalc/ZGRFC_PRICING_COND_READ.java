/**
 * ZGRFC_PRICING_COND_READ.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.tonerPurchasePriceCalc;

public interface ZGRFC_PRICING_COND_READ extends java.rmi.Remote {
    public void ZGRFC_PRICING_COND_READ_API(com.lexmark.tonerPurchasePriceCalc.ZGSV_COMT_PRODUCT_ID[] IT_PRODUCT_ID, java.lang.String IV_PROVIDER_ORDER, java.lang.String IV_SALES_CONTRACT, com.lexmark.tonerPurchasePriceCalc.holders.ZGTTV_PRCT_COND_RATEHolder OT_PRICE, javax.xml.rpc.holders.StringHolder o_ERROR_MSG) throws java.rmi.RemoteException;
}
