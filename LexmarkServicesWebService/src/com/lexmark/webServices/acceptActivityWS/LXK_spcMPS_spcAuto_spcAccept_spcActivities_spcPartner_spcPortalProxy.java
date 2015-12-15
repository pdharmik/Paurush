package com.lexmark.webServices.acceptActivityWS;

public class LXK_spcMPS_spcAuto_spcAccept_spcActivities_spcPartner_spcPortalProxy implements com.lexmark.webServices.acceptActivityWS.LXK_spcMPS_spcAuto_spcAccept_spcActivities_spcPartner_spcPortal_PortType {
  private String _endpoint = null;
  private com.lexmark.webServices.acceptActivityWS.LXK_spcMPS_spcAuto_spcAccept_spcActivities_spcPartner_spcPortal_PortType lXK_spcMPS_spcAuto_spcAccept_spcActivities_spcPartner_spcPortal_PortType = null;
  
  public LXK_spcMPS_spcAuto_spcAccept_spcActivities_spcPartner_spcPortalProxy() {
    _initLXK_spcMPS_spcAuto_spcAccept_spcActivities_spcPartner_spcPortalProxy();
  }
  
  public LXK_spcMPS_spcAuto_spcAccept_spcActivities_spcPartner_spcPortalProxy(String endpoint) {
    _endpoint = endpoint;
    _initLXK_spcMPS_spcAuto_spcAccept_spcActivities_spcPartner_spcPortalProxy();
  }
  
  private void _initLXK_spcMPS_spcAuto_spcAccept_spcActivities_spcPartner_spcPortalProxy() {
    try {
      lXK_spcMPS_spcAuto_spcAccept_spcActivities_spcPartner_spcPortal_PortType = (new com.lexmark.webServices.acceptActivityWS.LXK_spcMPS_spcAuto_spcAccept_spcActivities_spcPartner_spcPortal_ServiceLocator()).getLXK_spcMPS_spcAuto_spcAccept_spcActivities_spcPartner_spcPortal();
      if (lXK_spcMPS_spcAuto_spcAccept_spcActivities_spcPartner_spcPortal_PortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)lXK_spcMPS_spcAuto_spcAccept_spcActivities_spcPartner_spcPortal_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)lXK_spcMPS_spcAuto_spcAccept_spcActivities_spcPartner_spcPortal_PortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (lXK_spcMPS_spcAuto_spcAccept_spcActivities_spcPartner_spcPortal_PortType != null)
      ((javax.xml.rpc.Stub)lXK_spcMPS_spcAuto_spcAccept_spcActivities_spcPartner_spcPortal_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.lexmark.webServices.acceptActivityWS.LXK_spcMPS_spcAuto_spcAccept_spcActivities_spcPartner_spcPortal_PortType getLXK_spcMPS_spcAuto_spcAccept_spcActivities_spcPartner_spcPortal_PortType() {
    if (lXK_spcMPS_spcAuto_spcAccept_spcActivities_spcPartner_spcPortal_PortType == null)
      _initLXK_spcMPS_spcAuto_spcAccept_spcActivities_spcPartner_spcPortalProxy();
    return lXK_spcMPS_spcAuto_spcAccept_spcActivities_spcPartner_spcPortal_PortType;
  }
  
  public com.lexmark.webServices.acceptActivityWS.Update_Activities_Output update_Activities(com.lexmark.webServices.acceptActivityWS.Update_Activities_Input update_Activities_Input) throws java.rmi.RemoteException{
    if (lXK_spcMPS_spcAuto_spcAccept_spcActivities_spcPartner_spcPortal_PortType == null)
      _initLXK_spcMPS_spcAuto_spcAccept_spcActivities_spcPartner_spcPortalProxy();
    return lXK_spcMPS_spcAuto_spcAccept_spcActivities_spcPartner_spcPortal_PortType.update_Activities(update_Activities_Input);
  }
  
  
}