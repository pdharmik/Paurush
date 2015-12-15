package com.lexmark.SiebelShared.createServiceRequest.client;

public class ServiceRequestWS_PortTypeProxy implements com.lexmark.SiebelShared.createServiceRequest.client.ServiceRequestWS_PortType {
  private String _endpoint = null;
  private com.lexmark.SiebelShared.createServiceRequest.client.ServiceRequestWS_PortType serviceRequestWS_PortType = null;
  
  public ServiceRequestWS_PortTypeProxy() {
    _initServiceRequestWS_PortTypeProxy();
  }
  
  public ServiceRequestWS_PortTypeProxy(String endpoint) {
    _endpoint = endpoint;
    _initServiceRequestWS_PortTypeProxy();
  }
  
  private void _initServiceRequestWS_PortTypeProxy() {
    try {
      serviceRequestWS_PortType = (new com.lexmark.SiebelShared.createServiceRequest.client.ServiceRequestWSLocator()).getServiceRequest_serviceRequestWS_Port();
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
  
  public com.lexmark.SiebelShared.createServiceRequest.client.ServiceRequestWS_PortType getServiceRequestWS_PortType() {
    if (serviceRequestWS_PortType == null)
      _initServiceRequestWS_PortTypeProxy();
    return serviceRequestWS_PortType;
  }
  
  public com.lexmark.SiebelShared.createServiceRequest.client.StatusServiceRequestWSOutput getServiceRequestStatus(com.lexmark.SiebelShared.createServiceRequest.client.GetServiceRequestStatusInput getServiceRequestStatus, java.lang.String debug) throws java.rmi.RemoteException{
    if (serviceRequestWS_PortType == null)
      _initServiceRequestWS_PortTypeProxy();
    return serviceRequestWS_PortType.getServiceRequestStatus(getServiceRequestStatus, debug);
  }
  
  public void createChangeManagementServiceRequest(java.lang.String debug, com.lexmark.SiebelShared.createServiceRequest.client.ChangeManagementServiceRequestWSInput changeManagementServiceRequestWSInput, java.lang.String synchOrAsynch, com.lexmark.SiebelShared.createServiceRequest.client.holders.ServiceRequestDetailsOutputHolder serviceRequestDetails, javax.xml.rpc.holders.StringHolder srRowId, javax.xml.rpc.holders.StringHolder srNumber) throws java.rmi.RemoteException{
    if (serviceRequestWS_PortType == null)
      _initServiceRequestWS_PortTypeProxy();
    serviceRequestWS_PortType.createChangeManagementServiceRequest(debug, changeManagementServiceRequestWSInput, synchOrAsynch, serviceRequestDetails, srRowId, srNumber);
  }
  
  public void getServiceAppointment(com.lexmark.SiebelShared.createServiceRequest.client.ServiceAppointment serviceAppointment, java.lang.String debug, com.lexmark.SiebelShared.createServiceRequest.client.holders.ServiceAppointmentTimeSlotsHolder serviceAppointmentTimeSlots, javax.xml.rpc.holders.StringHolder sessionRequestId, javax.xml.rpc.holders.StringHolder errorCode, javax.xml.rpc.holders.StringHolder message) throws java.rmi.RemoteException{
    if (serviceRequestWS_PortType == null)
      _initServiceRequestWS_PortTypeProxy();
    serviceRequestWS_PortType.getServiceAppointment(serviceAppointment, debug, serviceAppointmentTimeSlots, sessionRequestId, errorCode, message);
  }
  
  public void updateConsumablesServiceRequest(com.lexmark.SiebelShared.createServiceRequest.client.ConsumablesServiceRequestWSInput consumablesServiceRequestWSInput, java.lang.String debug) throws java.rmi.RemoteException{
    if (serviceRequestWS_PortType == null)
      _initServiceRequestWS_PortTypeProxy();
    serviceRequestWS_PortType.updateConsumablesServiceRequest(consumablesServiceRequestWSInput, debug);
  }
  
  public java.lang.String updateServiceRequestActivity(java.lang.String debug, java.lang.String synchOrAsynch, com.lexmark.SiebelShared.createServiceRequest.client.UpdateServiceRequestActivityInput updateServiceRequestActivityInput) throws java.rmi.RemoteException{
    if (serviceRequestWS_PortType == null)
      _initServiceRequestWS_PortTypeProxy();
    return serviceRequestWS_PortType.updateServiceRequestActivity(debug, synchOrAsynch, updateServiceRequestActivityInput);
  }
  
  public void updateServiceRequest(java.lang.String debug, com.lexmark.SiebelShared.createServiceRequest.client.ServiceRequestInput serviceRequest) throws java.rmi.RemoteException{
    if (serviceRequestWS_PortType == null)
      _initServiceRequestWS_PortTypeProxy();
    serviceRequestWS_PortType.updateServiceRequest(debug, serviceRequest);
  }
  
  public void escalateServiceRequest(java.lang.String debug, com.lexmark.SiebelShared.createServiceRequest.client.EscalateServiceRequestWSInput escalateServiceRequestWSInput, javax.xml.rpc.holders.StringHolder escalateServiceRequestResult, javax.xml.rpc.holders.StringHolder description) throws java.rmi.RemoteException{
    if (serviceRequestWS_PortType == null)
      _initServiceRequestWS_PortTypeProxy();
    serviceRequestWS_PortType.escalateServiceRequest(debug, escalateServiceRequestWSInput, escalateServiceRequestResult, description);
  }
  
  public void cancelServiceRequest(com.lexmark.SiebelShared.createServiceRequest.client.CancelServiceRequestWSInput cancelServiceRequestWSInput, java.lang.String debug, javax.xml.rpc.holders.StringHolder cancelServiceRequestResult, javax.xml.rpc.holders.StringHolder description) throws java.rmi.RemoteException{
    if (serviceRequestWS_PortType == null)
      _initServiceRequestWS_PortTypeProxy();
    serviceRequestWS_PortType.cancelServiceRequest(cancelServiceRequestWSInput, debug, cancelServiceRequestResult, description);
  }
  
  public void createH2HServiceRequest(java.lang.String debug, com.lexmark.SiebelShared.createServiceRequest.client.ServiceRequestInputForH2H serviceRequest, javax.xml.rpc.holders.StringHolder serviceRequestNumber, javax.xml.rpc.holders.StringHolder serivceRequestRowId, javax.xml.rpc.holders.StringHolder partnerId) throws java.rmi.RemoteException{
    if (serviceRequestWS_PortType == null)
      _initServiceRequestWS_PortTypeProxy();
    serviceRequestWS_PortType.createH2HServiceRequest(debug, serviceRequest, serviceRequestNumber, serivceRequestRowId, partnerId);
  }
  
  public void updateSellableItemsServiceRequest(com.lexmark.SiebelShared.createServiceRequest.client.SellableItemServiceRequestWSInput sellableItemServiceRequestWSInput, java.lang.String debug) throws java.rmi.RemoteException{
    if (serviceRequestWS_PortType == null)
      _initServiceRequestWS_PortTypeProxy();
    serviceRequestWS_PortType.updateSellableItemsServiceRequest(sellableItemServiceRequestWSInput, debug);
  }
  
  public void cancelServiceAppointmentOnReschedule(com.lexmark.SiebelShared.createServiceRequest.client.CancelServiceAppointmentReschedule cancelServiceAppointmentReschedule, javax.xml.rpc.holders.StringHolder errorCode, javax.xml.rpc.holders.StringHolder message) throws java.rmi.RemoteException{
    if (serviceRequestWS_PortType == null)
      _initServiceRequestWS_PortTypeProxy();
    serviceRequestWS_PortType.cancelServiceAppointmentOnReschedule(cancelServiceAppointmentReschedule, errorCode, message);
  }
  
  public void getSRAndActivityDetails(com.lexmark.SiebelShared.createServiceRequest.client.ServiceRequestAndActivityDetailsInput getServiceRequestAndActivityDetails, com.lexmark.SiebelShared.createServiceRequest.client.holders.ServiceRequestDetailsOutputHolder serviceRequestDetailsOutput, javax.xml.rpc.holders.StringHolder errorCode, javax.xml.rpc.holders.StringHolder message) throws java.rmi.RemoteException{
    if (serviceRequestWS_PortType == null)
      _initServiceRequestWS_PortTypeProxy();
    serviceRequestWS_PortType.getSRAndActivityDetails(getServiceRequestAndActivityDetails, serviceRequestDetailsOutput, errorCode, message);
  }
  
  public void cancelServiceAppointmentRequestList(com.lexmark.SiebelShared.createServiceRequest.client.CancelServiceAppointmentRequest cancelServiceAppointmentRequest, javax.xml.rpc.holders.StringHolder errorCode, javax.xml.rpc.holders.StringHolder message) throws java.rmi.RemoteException{
    if (serviceRequestWS_PortType == null)
      _initServiceRequestWS_PortTypeProxy();
    serviceRequestWS_PortType.cancelServiceAppointmentRequestList(cancelServiceAppointmentRequest, errorCode, message);
  }
  
  public java.lang.String debriefServiceRequestActivity(java.lang.String debug, java.lang.String synchOrAsynch, com.lexmark.SiebelShared.createServiceRequest.client.DebriefServiceRequestActivityInput debriefServiceRequestActivityInput) throws java.rmi.RemoteException{
    if (serviceRequestWS_PortType == null)
      _initServiceRequestWS_PortTypeProxy();
    return serviceRequestWS_PortType.debriefServiceRequestActivity(debug, synchOrAsynch, debriefServiceRequestActivityInput);
  }
  
  public void confirmServiceAppointment(com.lexmark.SiebelShared.createServiceRequest.client.ConfirmServiceAppointment2 confirmServiceAppointment, javax.xml.rpc.holders.StringHolder errorCode, com.lexmark.SiebelShared.createServiceRequest.client.holders.ServiceRequestDetailsOutputHolder serviceRequestDetailsOutput, javax.xml.rpc.holders.StringHolder message) throws java.rmi.RemoteException{
    if (serviceRequestWS_PortType == null)
      _initServiceRequestWS_PortTypeProxy();
    serviceRequestWS_PortType.confirmServiceAppointment(confirmServiceAppointment, errorCode, serviceRequestDetailsOutput, message);
  }
  
  public void createServiceRequest(java.lang.String debug, com.lexmark.SiebelShared.createServiceRequest.client.ServiceRequestInput serviceRequest, java.lang.String synchOrAsynch, java.lang.String returnDetail_x003F_, javax.xml.rpc.holders.StringHolder srNumber, javax.xml.rpc.holders.StringHolder activityId, javax.xml.rpc.holders.StringHolder SRRowId, javax.xml.rpc.holders.StringHolder SRNumHashValue, javax.xml.rpc.holders.StringHolder serviceRegionId, javax.xml.rpc.holders.StringHolder siebelStatusMessage, com.lexmark.SiebelShared.createServiceRequest.client.holders.ServiceRequestDetailsOutputHolder serviceRequestDetails) throws java.rmi.RemoteException{
    if (serviceRequestWS_PortType == null)
      _initServiceRequestWS_PortTypeProxy();
    serviceRequestWS_PortType.createServiceRequest(debug, serviceRequest, synchOrAsynch, returnDetail_x003F_, srNumber, activityId, SRRowId, SRNumHashValue, serviceRegionId, siebelStatusMessage, serviceRequestDetails);
  }
  
  public void createConsumablesServiceRequest(java.lang.String debug, com.lexmark.SiebelShared.createServiceRequest.client.ConsumablesServiceRequestWSInput consumablesServiceRequestWSInput, java.lang.String synchOrAsynch, com.lexmark.SiebelShared.createServiceRequest.client.holders.ServiceRequestDetailsOutputHolder serviceRequestDetailsOutput, javax.xml.rpc.holders.StringHolder sRNumber, javax.xml.rpc.holders.StringHolder sRRowId) throws java.rmi.RemoteException{
    if (serviceRequestWS_PortType == null)
      _initServiceRequestWS_PortTypeProxy();
    serviceRequestWS_PortType.createConsumablesServiceRequest(debug, consumablesServiceRequestWSInput, synchOrAsynch, serviceRequestDetailsOutput, sRNumber, sRRowId);
  }
  
  public void createSellableItemsServiceRequest(com.lexmark.SiebelShared.createServiceRequest.client.SellableItemServiceRequestWSInput sellableItemServiceRequestWSInput, java.lang.String synchOrAsynch, java.lang.String debug, com.lexmark.SiebelShared.createServiceRequest.client.holders.ServiceRequestDetailsOutputHolder serviceRequestDetails, javax.xml.rpc.holders.StringHolder sRNumber, javax.xml.rpc.holders.StringHolder sRRowId) throws java.rmi.RemoteException{
    if (serviceRequestWS_PortType == null)
      _initServiceRequestWS_PortTypeProxy();
    serviceRequestWS_PortType.createSellableItemsServiceRequest(sellableItemServiceRequestWSInput, synchOrAsynch, debug, serviceRequestDetails, sRNumber, sRRowId);
  }
  
  public void cancelServiceRequestAppointment(com.lexmark.SiebelShared.createServiceRequest.client.CancelServiceAppointment cancelServiceAppointment, javax.xml.rpc.holders.StringHolder errorCode, javax.xml.rpc.holders.StringHolder message) throws java.rmi.RemoteException{
    if (serviceRequestWS_PortType == null)
      _initServiceRequestWS_PortTypeProxy();
    serviceRequestWS_PortType.cancelServiceRequestAppointment(cancelServiceAppointment, errorCode, message);
  }
  
  public com.lexmark.SiebelShared.createServiceRequest.client.AddressCleanseWSOutput addressCleanse(com.lexmark.SiebelShared.createServiceRequest.client.AddressCleanseWSInput addressCleanseWSInput, java.lang.String debug) throws java.rmi.RemoteException{
    if (serviceRequestWS_PortType == null)
      _initServiceRequestWS_PortTypeProxy();
    return serviceRequestWS_PortType.addressCleanse(addressCleanseWSInput, debug);
  }
  
  
}