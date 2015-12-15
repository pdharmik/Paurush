package com.lexmark.SiebelShared.debriefs;

public class AssetManagementStatus_PortTypeProxy implements com.lexmark.SiebelShared.debriefs.AssetManagementStatus_PortType {
  private String _endpoint = null;
  private com.lexmark.SiebelShared.debriefs.AssetManagementStatus_PortType assetManagementStatus_PortType = null;
  
  public AssetManagementStatus_PortTypeProxy() {
    _initAssetManagementStatus_PortTypeProxy();
  }
  
  public AssetManagementStatus_PortTypeProxy(String endpoint) {
    _endpoint = endpoint;
    _initAssetManagementStatus_PortTypeProxy();
  }
  
  private void _initAssetManagementStatus_PortTypeProxy() {
    try {
      assetManagementStatus_PortType = (new com.lexmark.SiebelShared.debriefs.LXKAssetManagementStatusWebServicesProviderAssetManagementStatusLocator()).getLXKAssetManagementStatus_webServices_provider_AssetManagementStatus_Port();
      if (assetManagementStatus_PortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)assetManagementStatus_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)assetManagementStatus_PortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (assetManagementStatus_PortType != null)
      ((javax.xml.rpc.Stub)assetManagementStatus_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.lexmark.SiebelShared.debriefs.AssetManagementStatus_PortType getAssetManagementStatus_PortType() {
    if (assetManagementStatus_PortType == null)
      _initAssetManagementStatus_PortTypeProxy();
    return assetManagementStatus_PortType;
  }
  
  public java.lang.String processAssetManagementStatus(com.lexmark.SiebelShared.debriefs.AssetManagementStatus assetManagementStatusRequest, java.lang.String debug) throws java.rmi.RemoteException{
    if (assetManagementStatus_PortType == null)
      _initAssetManagementStatus_PortTypeProxy();
    return assetManagementStatus_PortType.processAssetManagementStatus(assetManagementStatusRequest, debug);
  }
  
  
}