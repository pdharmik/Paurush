package com.lexmark.webServices.warrantyClaimsServiceRequest.client1;

public class WarrantyClaimsServiceRequestWS_PortTypeProxy implements com.lexmark.webServices.warrantyClaimsServiceRequest.client1.WarrantyClaimsServiceRequestWS_PortType {
  private String _endpoint = null;
  private com.lexmark.webServices.warrantyClaimsServiceRequest.client1.WarrantyClaimsServiceRequestWS_PortType warrantyClaimsServiceRequestWS_PortType = null;
  
  public WarrantyClaimsServiceRequestWS_PortTypeProxy() {
    _initWarrantyClaimsServiceRequestWS_PortTypeProxy();
  }
  
  public WarrantyClaimsServiceRequestWS_PortTypeProxy(String endpoint) {
    _endpoint = endpoint;
    _initWarrantyClaimsServiceRequestWS_PortTypeProxy();
  }
  
  private void _initWarrantyClaimsServiceRequestWS_PortTypeProxy() {
    try {
      warrantyClaimsServiceRequestWS_PortType = (new com.lexmark.webServices.warrantyClaimsServiceRequest.client1.WarrantyClaimsServiceRequestWSLocator()).getServiceRequest_warrantyClaimsServiceRequestWS_Port();
      if (warrantyClaimsServiceRequestWS_PortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)warrantyClaimsServiceRequestWS_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)warrantyClaimsServiceRequestWS_PortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (warrantyClaimsServiceRequestWS_PortType != null)
      ((javax.xml.rpc.Stub)warrantyClaimsServiceRequestWS_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.lexmark.webServices.warrantyClaimsServiceRequest.client1.WarrantyClaimsServiceRequestWS_PortType getWarrantyClaimsServiceRequestWS_PortType() {
    if (warrantyClaimsServiceRequestWS_PortType == null)
      _initWarrantyClaimsServiceRequestWS_PortTypeProxy();
    return warrantyClaimsServiceRequestWS_PortType;
  }
  
  public java.lang.String debriefWarrantyClaim(java.lang.String debug, java.lang.String synchOrAsynch, com.lexmark.webServices.warrantyClaimsServiceRequest.client1.DebriefClaimsServiceRequestInput debriefClaimsServiceRequestInput) throws java.rmi.RemoteException{
    if (warrantyClaimsServiceRequestWS_PortType == null)
      _initWarrantyClaimsServiceRequestWS_PortTypeProxy();
    return warrantyClaimsServiceRequestWS_PortType.debriefWarrantyClaim(debug, synchOrAsynch, debriefClaimsServiceRequestInput);
  }
  
  public void updateWarrantyClaim(java.lang.String debug, java.lang.String synchOrAsynch, com.lexmark.webServices.warrantyClaimsServiceRequest.client1.UpdateClaimsServiceRequestInput updateClaimsServiceRequestInput, javax.xml.rpc.holders.StringHolder status, javax.xml.rpc.holders.StringHolder claimNumber, javax.xml.rpc.holders.StringHolder claimId) throws java.rmi.RemoteException{
    if (warrantyClaimsServiceRequestWS_PortType == null)
      _initWarrantyClaimsServiceRequestWS_PortTypeProxy();
    warrantyClaimsServiceRequestWS_PortType.updateWarrantyClaim(debug, synchOrAsynch, updateClaimsServiceRequestInput, status, claimNumber, claimId);
  }
  
  public void createWarrantyClaim(java.lang.String debug, java.lang.String synchOrAsynch, com.lexmark.webServices.warrantyClaimsServiceRequest.client1.CreateClaimsServiceRequestInput createClaimsServiceRequestInput, javax.xml.rpc.holders.StringHolder claimNumber, javax.xml.rpc.holders.StringHolder claimId) throws java.rmi.RemoteException{
    if (warrantyClaimsServiceRequestWS_PortType == null)
      _initWarrantyClaimsServiceRequestWS_PortTypeProxy();
    warrantyClaimsServiceRequestWS_PortType.createWarrantyClaim(debug, synchOrAsynch, createClaimsServiceRequestInput, claimNumber, claimId);
  }
  
  
}