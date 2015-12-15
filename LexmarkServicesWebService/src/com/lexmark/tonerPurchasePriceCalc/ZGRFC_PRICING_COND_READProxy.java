package com.lexmark.tonerPurchasePriceCalc;

public class ZGRFC_PRICING_COND_READProxy implements com.lexmark.tonerPurchasePriceCalc.ZGRFC_PRICING_COND_READ {
  private String _endpoint = null;
  private com.lexmark.tonerPurchasePriceCalc.ZGRFC_PRICING_COND_READ zGRFC_PRICING_COND_READ = null;
  
  public ZGRFC_PRICING_COND_READProxy() {
    _initZGRFC_PRICING_COND_READProxy();
  }
  
  public ZGRFC_PRICING_COND_READProxy(String endpoint) {
    _endpoint = endpoint;
    _initZGRFC_PRICING_COND_READProxy();
  }
  
  private void _initZGRFC_PRICING_COND_READProxy() {
    try {
      zGRFC_PRICING_COND_READ = (new com.lexmark.tonerPurchasePriceCalc.ZGRFC_PRICING_COND_READ_API_ServiceLocator()).getZGRFC_PRICING_COND_READ_API_BN();
      if (zGRFC_PRICING_COND_READ != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)zGRFC_PRICING_COND_READ)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)zGRFC_PRICING_COND_READ)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (zGRFC_PRICING_COND_READ != null)
      ((javax.xml.rpc.Stub)zGRFC_PRICING_COND_READ)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.lexmark.tonerPurchasePriceCalc.ZGRFC_PRICING_COND_READ getZGRFC_PRICING_COND_READ() {
    if (zGRFC_PRICING_COND_READ == null)
      _initZGRFC_PRICING_COND_READProxy();
    return zGRFC_PRICING_COND_READ;
  }
  
  public void ZGRFC_PRICING_COND_READ_API(com.lexmark.tonerPurchasePriceCalc.ZGSV_COMT_PRODUCT_ID[] IT_PRODUCT_ID, java.lang.String IV_PROVIDER_ORDER, java.lang.String IV_SALES_CONTRACT, com.lexmark.tonerPurchasePriceCalc.holders.ZGTTV_PRCT_COND_RATEHolder OT_PRICE, javax.xml.rpc.holders.StringHolder o_ERROR_MSG) throws java.rmi.RemoteException{
    if (zGRFC_PRICING_COND_READ == null)
      _initZGRFC_PRICING_COND_READProxy();
    zGRFC_PRICING_COND_READ.ZGRFC_PRICING_COND_READ_API(IT_PRODUCT_ID, IV_PROVIDER_ORDER, IV_SALES_CONTRACT, OT_PRICE, o_ERROR_MSG);
  }
  
  
}