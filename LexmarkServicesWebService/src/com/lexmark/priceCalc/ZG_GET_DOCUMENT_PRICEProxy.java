package com.lexmark.priceCalc;

public class ZG_GET_DOCUMENT_PRICEProxy implements com.lexmark.priceCalc.ZG_GET_DOCUMENT_PRICE_PortType {
  private String _endpoint = null;
  private com.lexmark.priceCalc.ZG_GET_DOCUMENT_PRICE_PortType zG_GET_DOCUMENT_PRICE_PortType = null;
  
  public ZG_GET_DOCUMENT_PRICEProxy() {
    _initZG_GET_DOCUMENT_PRICEProxy();
  }
  
  public ZG_GET_DOCUMENT_PRICEProxy(String endpoint) {
    _endpoint = endpoint;
    _initZG_GET_DOCUMENT_PRICEProxy();
  }
  
  private void _initZG_GET_DOCUMENT_PRICEProxy() {
    try {
      zG_GET_DOCUMENT_PRICE_PortType = (new com.lexmark.priceCalc.ZG_GET_DOCUMENT_PRICE_ServiceLocator()).getzg_get_document_price_bnd();
      if (zG_GET_DOCUMENT_PRICE_PortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)zG_GET_DOCUMENT_PRICE_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)zG_GET_DOCUMENT_PRICE_PortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (zG_GET_DOCUMENT_PRICE_PortType != null)
      ((javax.xml.rpc.Stub)zG_GET_DOCUMENT_PRICE_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.lexmark.priceCalc.ZG_GET_DOCUMENT_PRICE_PortType getZG_GET_DOCUMENT_PRICE_PortType() {
    if (zG_GET_DOCUMENT_PRICE_PortType == null)
      _initZG_GET_DOCUMENT_PRICEProxy();
    return zG_GET_DOCUMENT_PRICE_PortType;
  }
  
  public void ZG_GET_DOCUMENT_PRICE(com.lexmark.priceCalc.holders.ZGS_CRM_ORDER_INPUT_ITEM_NO_THolder IT_ITEMS, com.lexmark.priceCalc.holders.ZGS_CRM_ORDER_OUTPUT_THolder IT_OUTPUT, java.lang.String TRANSACTION_ID) throws java.rmi.RemoteException{
    if (zG_GET_DOCUMENT_PRICE_PortType == null)
      _initZG_GET_DOCUMENT_PRICEProxy();
    zG_GET_DOCUMENT_PRICE_PortType.ZG_GET_DOCUMENT_PRICE(IT_ITEMS, IT_OUTPUT, TRANSACTION_ID);
  }
  
  
}