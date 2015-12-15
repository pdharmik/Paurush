package com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client;

public class ServiceRequestWS_PortTypeProxy implements com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.ServiceRequestWS_PortType {
  private String _endpoint = null;
  private com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.ServiceRequestWS_PortType serviceRequestWS_PortType = null;
  
  public ServiceRequestWS_PortTypeProxy() {
    _initServiceRequestWS_PortTypeProxy();
  }
  
  public ServiceRequestWS_PortTypeProxy(String endpoint) {
    _endpoint = endpoint;
    _initServiceRequestWS_PortTypeProxy();
  }
  
  private void _initServiceRequestWS_PortTypeProxy() {
    try {
      serviceRequestWS_PortType = (new com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.ServiceRequestWSLocator()).getServiceRequest_serviceRequestWS_Port();
      if (serviceRequestWS_PortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)serviceRequestWS_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)serviceRequestWS_PortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (serviceRequestWS_PortType != null)
      ((javax.xml.rpc.Stub)serviceRequestWS_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.ServiceRequestWS_PortType getServiceRequestWS_PortType() {
    if (serviceRequestWS_PortType == null)
      _initServiceRequestWS_PortTypeProxy();
    return serviceRequestWS_PortType;
  }
  
  public void cancelServiceAppointmentOnReschedule(com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.CancelServiceAppointmentReschedule cancelServiceAppointmentReschedule, javax.xml.rpc.holders.StringHolder errorCode, javax.xml.rpc.holders.StringHolder message) throws java.rmi.RemoteException{
    if (serviceRequestWS_PortType == null)
      _initServiceRequestWS_PortTypeProxy();
    serviceRequestWS_PortType.cancelServiceAppointmentOnReschedule(cancelServiceAppointmentReschedule, errorCode, message);
  }
  
  public void cancelServiceAppointmentRequestList(com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.CancelServiceAppointmentRequest cancelServiceAppointmentRequest, javax.xml.rpc.holders.StringHolder errorCode, javax.xml.rpc.holders.StringHolder message) throws java.rmi.RemoteException{
    if (serviceRequestWS_PortType == null)
      _initServiceRequestWS_PortTypeProxy();
    serviceRequestWS_PortType.cancelServiceAppointmentRequestList(cancelServiceAppointmentRequest, errorCode, message);
  }
  
  public void cancelServiceRequestAppointment(com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.CancelServiceAppointment cancelServiceAppointment, javax.xml.rpc.holders.StringHolder errorCode, javax.xml.rpc.holders.StringHolder message) throws java.rmi.RemoteException{
    if (serviceRequestWS_PortType == null)
      _initServiceRequestWS_PortTypeProxy();
    serviceRequestWS_PortType.cancelServiceRequestAppointment(cancelServiceAppointment, errorCode, message);
  }
  
  public void confirmServiceAppointment(com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.ConfirmServiceAppointment2 confirmServiceAppointment, javax.xml.rpc.holders.StringHolder errorCode, com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.holders.ServiceRequestDetailsOutputHolder serviceRequestDetailsOutput, javax.xml.rpc.holders.StringHolder message) throws java.rmi.RemoteException{
    if (serviceRequestWS_PortType == null)
      _initServiceRequestWS_PortTypeProxy();
    serviceRequestWS_PortType.confirmServiceAppointment(confirmServiceAppointment, errorCode, serviceRequestDetailsOutput, message);
  }
  
  public void getServiceAppointment(com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.ServiceAppointment serviceAppointment, java.lang.String debug, com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.holders.ServiceAppointmentTimeSlotsHolder serviceAppointmentTimeSlots, javax.xml.rpc.holders.StringHolder sessionRequestId, javax.xml.rpc.holders.StringHolder errorCode, javax.xml.rpc.holders.StringHolder message) throws java.rmi.RemoteException{
    if (serviceRequestWS_PortType == null)
      _initServiceRequestWS_PortTypeProxy();
    serviceRequestWS_PortType.getServiceAppointment(serviceAppointment, debug, serviceAppointmentTimeSlots, sessionRequestId, errorCode, message);
  }
  
  public void getSRAndActivityDetails(java.lang.String debug, com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.ServiceRequestAndActivityDetailsInput getServiceRequestAndActivityDetails, com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.holders.ServiceRequestDetailsOutputHolder serviceRequestDetailsOutput, javax.xml.rpc.holders.StringHolder errorCode, javax.xml.rpc.holders.StringHolder message) throws java.rmi.RemoteException{
    if (serviceRequestWS_PortType == null)
      _initServiceRequestWS_PortTypeProxy();
    serviceRequestWS_PortType.getSRAndActivityDetails(debug, getServiceRequestAndActivityDetails, serviceRequestDetailsOutput, errorCode, message);
  }
  
  public java.lang.String updateServiceRequestActivity(java.lang.String debug, java.lang.String synchOrAsynch, com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.UpdateServiceRequestActivityInput updateServiceRequestActivityInput) throws java.rmi.RemoteException{
    if (serviceRequestWS_PortType == null)
      _initServiceRequestWS_PortTypeProxy();
    return serviceRequestWS_PortType.updateServiceRequestActivity(debug, synchOrAsynch, updateServiceRequestActivityInput);
  }
  
  public java.lang.String debriefServiceRequestActivity(java.lang.String debug, java.lang.String synchOrAsynch, com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.DebriefServiceRequestActivityInput debriefServiceRequestActivityInput) throws java.rmi.RemoteException{
    if (serviceRequestWS_PortType == null)
      _initServiceRequestWS_PortTypeProxy();
    return serviceRequestWS_PortType.debriefServiceRequestActivity(debug, synchOrAsynch, debriefServiceRequestActivityInput);
  }
  
  public java.lang.String cancelServiceRequest(com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.CancelServiceRequestWSInput cancelServiceRequestWSInput, java.lang.String debug) throws java.rmi.RemoteException{
    if (serviceRequestWS_PortType == null)
      _initServiceRequestWS_PortTypeProxy();
    return serviceRequestWS_PortType.cancelServiceRequest(cancelServiceRequestWSInput, debug);
  }
  
  public com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.StatusServiceRequestWSOutput getServiceRequestStatus(com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.GetServiceRequestStatusInput getServiceRequestStatus, java.lang.String debug) throws java.rmi.RemoteException{
    if (serviceRequestWS_PortType == null)
      _initServiceRequestWS_PortTypeProxy();
    return serviceRequestWS_PortType.getServiceRequestStatus(getServiceRequestStatus, debug);
  }
  
  public void createServiceRequest(java.lang.String debug, com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.ConsumablesServiceRequestWSInput consumablesServiceRequestWSInput) throws java.rmi.RemoteException{
    if (serviceRequestWS_PortType == null)
      _initServiceRequestWS_PortTypeProxy();
    serviceRequestWS_PortType.createServiceRequest(debug, consumablesServiceRequestWSInput);
  }
  
  public void createChangeManagementServiceRequest(java.lang.String debug, com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.ChangeManagementServiceRequestWSInput changeManagementServiceRequestWSInput, java.lang.String synchOrAsynch, com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.holders.ServiceRequestDetailsOutputHolder serviceRequestDetails, javax.xml.rpc.holders.StringHolder srRowId, javax.xml.rpc.holders.StringHolder srNumber) throws java.rmi.RemoteException{
    if (serviceRequestWS_PortType == null)
      _initServiceRequestWS_PortTypeProxy();
    serviceRequestWS_PortType.createChangeManagementServiceRequest(debug, changeManagementServiceRequestWSInput, synchOrAsynch, serviceRequestDetails, srRowId, srNumber);
  }
  
  public java.lang.String escalateServiceRequest(com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.EscalateServiceRequestWSInput escalateServiceRequestWSInput, java.lang.String debug) throws java.rmi.RemoteException{
    if (serviceRequestWS_PortType == null)
      _initServiceRequestWS_PortTypeProxy();
    return serviceRequestWS_PortType.escalateServiceRequest(escalateServiceRequestWSInput, debug);
  }
  
  public void createConsumablesServiceRequest(java.lang.String debug, com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.ConsumablesServiceRequestWSInput consumablesServiceRequestWSInput, java.lang.String synchOrAsynch, com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.holders.ServiceRequestDetailsOutputHolder serviceRequestDetailsOutput, javax.xml.rpc.holders.StringHolder sRNumber, javax.xml.rpc.holders.StringHolder sRRowId) throws java.rmi.RemoteException{
    if (serviceRequestWS_PortType == null)
      _initServiceRequestWS_PortTypeProxy();
    serviceRequestWS_PortType.createConsumablesServiceRequest(debug, consumablesServiceRequestWSInput, synchOrAsynch, serviceRequestDetailsOutput, sRNumber, sRRowId);
  }
  
  public void updateConsumablesServiceRequest(java.lang.String debug, com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.ConsumablesServiceRequestWSInput consumablesServiceRequestWSInput) throws java.rmi.RemoteException{
    if (serviceRequestWS_PortType == null)
      _initServiceRequestWS_PortTypeProxy();
    serviceRequestWS_PortType.updateConsumablesServiceRequest(debug, consumablesServiceRequestWSInput);
  }
  
  public com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.AddressCleanseWSOutput addressCleanse(com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.AddressCleanseWSInput addressCleanseWSInput) throws java.rmi.RemoteException{
    if (serviceRequestWS_PortType == null)
      _initServiceRequestWS_PortTypeProxy();
    return serviceRequestWS_PortType.addressCleanse(addressCleanseWSInput);
  }
  
  
}