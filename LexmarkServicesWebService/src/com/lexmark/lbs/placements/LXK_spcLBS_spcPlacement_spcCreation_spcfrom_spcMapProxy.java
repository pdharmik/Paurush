package com.lexmark.lbs.placements;

public class LXK_spcLBS_spcPlacement_spcCreation_spcfrom_spcMapProxy implements com.lexmark.lbs.placements.LXK_spcLBS_spcPlacement_spcCreation_spcfrom_spcMap_PortType {
  private String _endpoint = null;
  private com.lexmark.lbs.placements.LXK_spcLBS_spcPlacement_spcCreation_spcfrom_spcMap_PortType lXK_spcLBS_spcPlacement_spcCreation_spcfrom_spcMap_PortType = null;
  
  public LXK_spcLBS_spcPlacement_spcCreation_spcfrom_spcMapProxy() {
    _initLXK_spcLBS_spcPlacement_spcCreation_spcfrom_spcMapProxy();
  }
  
  public LXK_spcLBS_spcPlacement_spcCreation_spcfrom_spcMapProxy(String endpoint) {
    _endpoint = endpoint;
    _initLXK_spcLBS_spcPlacement_spcCreation_spcfrom_spcMapProxy();
  }
  
  private void _initLXK_spcLBS_spcPlacement_spcCreation_spcfrom_spcMapProxy() {
    try {
      lXK_spcLBS_spcPlacement_spcCreation_spcfrom_spcMap_PortType = (new com.lexmark.lbs.placements.LXK_spcLBS_spcPlacement_spcCreation_spcfrom_spcMap_ServiceLocator()).getLXK_spcLBS_spcPlacement_spcCreation_spcfrom_spcMap();
      if (lXK_spcLBS_spcPlacement_spcCreation_spcfrom_spcMap_PortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)lXK_spcLBS_spcPlacement_spcCreation_spcfrom_spcMap_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)lXK_spcLBS_spcPlacement_spcCreation_spcfrom_spcMap_PortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (lXK_spcLBS_spcPlacement_spcCreation_spcfrom_spcMap_PortType != null)
      ((javax.xml.rpc.Stub)lXK_spcLBS_spcPlacement_spcCreation_spcfrom_spcMap_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.lexmark.lbs.placements.LXK_spcLBS_spcPlacement_spcCreation_spcfrom_spcMap_PortType getLXK_spcLBS_spcPlacement_spcCreation_spcfrom_spcMap_PortType() {
    if (lXK_spcLBS_spcPlacement_spcCreation_spcfrom_spcMap_PortType == null)
      _initLXK_spcLBS_spcPlacement_spcCreation_spcfrom_spcMapProxy();
    return lXK_spcLBS_spcPlacement_spcCreation_spcfrom_spcMap_PortType;
  }
  
  public com.lexmark.lbs.placements.Create_Placements_Output create_Placements(com.lexmark.lbs.placements.Create_Placements_Input create_Placements_Input) throws java.rmi.RemoteException{
    if (lXK_spcLBS_spcPlacement_spcCreation_spcfrom_spcMap_PortType == null)
      _initLXK_spcLBS_spcPlacement_spcCreation_spcfrom_spcMapProxy();
    return lXK_spcLBS_spcPlacement_spcCreation_spcfrom_spcMap_PortType.create_Placements(create_Placements_Input);
  }
  
  
}