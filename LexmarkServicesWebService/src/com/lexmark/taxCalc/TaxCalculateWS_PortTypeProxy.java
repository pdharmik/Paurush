package com.lexmark.taxCalc;

public class TaxCalculateWS_PortTypeProxy implements com.lexmark.taxCalc.TaxCalculateWS_PortType {
  private String _endpoint = null;
  private com.lexmark.taxCalc.TaxCalculateWS_PortType taxCalculateWS_PortType = null;
  
  public TaxCalculateWS_PortTypeProxy() {
    _initTaxCalculateWS_PortTypeProxy();
  }
  
  public TaxCalculateWS_PortTypeProxy(String endpoint) {
    _endpoint = endpoint;
    _initTaxCalculateWS_PortTypeProxy();
  }
  
  private void _initTaxCalculateWS_PortTypeProxy() {
    try {
      taxCalculateWS_PortType = (new com.lexmark.taxCalc.TaxCalculateWSLocator()).getLXKTaxCalculationWS_webservices_provider_taxCalculateWS_Port();
      if (taxCalculateWS_PortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)taxCalculateWS_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)taxCalculateWS_PortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (taxCalculateWS_PortType != null)
      ((javax.xml.rpc.Stub)taxCalculateWS_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.lexmark.taxCalc.TaxCalculateWS_PortType getTaxCalculateWS_PortType() {
    if (taxCalculateWS_PortType == null)
      _initTaxCalculateWS_PortTypeProxy();
    return taxCalculateWS_PortType;
  }
  
  public void calculateTax(java.lang.String debug, com.lexmark.taxCalc.TaxCalculationWSInput taxCalculationWSInput, com.lexmark.taxCalc.holders.TaxCalculationWSOutputHolder taxCalculationWSOutput, javax.xml.rpc.holders.StringHolder integrationResponse) throws java.rmi.RemoteException{
    if (taxCalculateWS_PortType == null)
      _initTaxCalculateWS_PortTypeProxy();
    taxCalculateWS_PortType.calculateTax(debug, taxCalculationWSInput, taxCalculationWSOutput, integrationResponse);
  }
  
  
}