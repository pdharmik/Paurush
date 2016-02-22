package com.businessobjects.www;

public class RealTime_ServicesProxy implements com.businessobjects.www.RealTime_Services {
  private String _endpoint = null;
  private com.businessobjects.www.RealTime_Services realTime_Services = null;
  
  public RealTime_ServicesProxy() {
    _initRealTime_ServicesProxy();
  }
  
  public RealTime_ServicesProxy(String endpoint) {
    _endpoint = endpoint;
    _initRealTime_ServicesProxy();
  }
  
  private void _initRealTime_ServicesProxy() {
    try {
      realTime_Services = (new com.businessobjects.www.DataServices_ServerLocator()).getRealTime_Services();
      if (realTime_Services != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)realTime_Services)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)realTime_Services)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (realTime_Services != null)
      ((javax.xml.rpc.Stub)realTime_Services)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.businessobjects.www.RealTime_Services getRealTime_Services() {
    if (realTime_Services == null)
      _initRealTime_ServicesProxy();
    return realTime_Services;
  }
  
  public com.businessobjects.service.Service_Realtime_Batch_DQ_Siebel_business_address_datacleanse.output.DataSet service_Realtime_Batch_DQ_Siebel_business_address_datacleanse(com.businessobjects.service.Service_Realtime_Batch_DQ_Siebel_business_address_datacleanse.input.DataSet inputBody) throws java.rmi.RemoteException{
    if (realTime_Services == null)
      _initRealTime_ServicesProxy();
    return realTime_Services.service_Realtime_Batch_DQ_Siebel_business_address_datacleanse(inputBody);
  }
  
  public com.businessobjects.service.ABC.output.DataSet ABC(com.businessobjects.service.ABC.input.DataSet inputBody) throws java.rmi.RemoteException{
    if (realTime_Services == null)
      _initRealTime_ServicesProxy();
    return realTime_Services.ABC(inputBody);
  }
  
  public com.businessobjects.service.Service_Realtime_DQ_Siebel_account_match.output.DataSet service_Realtime_DQ_Siebel_account_match(com.businessobjects.service.Service_Realtime_DQ_Siebel_account_match.input.DataSet inputBody) throws java.rmi.RemoteException{
    if (realTime_Services == null)
      _initRealTime_ServicesProxy();
    return realTime_Services.service_Realtime_DQ_Siebel_account_match(inputBody);
  }
  
  public com.businessobjects.service.Service_Realtime_DQ_Siebel_business_address_datacleanse.output.DataSet service_Realtime_DQ_Siebel_business_address_datacleanse(com.businessobjects.service.Service_Realtime_DQ_Siebel_business_address_datacleanse.input.DataSet inputBody) throws java.rmi.RemoteException{
    if (realTime_Services == null)
      _initRealTime_ServicesProxy();
    return realTime_Services.service_Realtime_DQ_Siebel_business_address_datacleanse(inputBody);
  }
  
  public com.businessobjects.service.RT_ADDRESS.output.ROOT RT_ADDRESS(com.businessobjects.service.RT_ADDRESS.input.ROOT inputBody) throws java.rmi.RemoteException{
    if (realTime_Services == null)
      _initRealTime_ServicesProxy();
    return realTime_Services.RT_ADDRESS(inputBody);
  }
  
  public com.businessobjects.service.RT_ADDR_MATCH_CUST_SQL_INS_UPD.output.ROOT RT_ADDR_MATCH_CUST_SQL_INS_UPD(com.businessobjects.service.RT_ADDR_MATCH_CUST_SQL_INS_UPD.input.ROOT inputBody) throws java.rmi.RemoteException{
    if (realTime_Services == null)
      _initRealTime_ServicesProxy();
    return realTime_Services.RT_ADDR_MATCH_CUST_SQL_INS_UPD(inputBody);
  }
  
  public com.businessobjects.service.Service_Realtime_DQ_Portal_business_address_region_code.output.DataSet service_Realtime_DQ_Portal_business_address_region_code(com.businessobjects.service.Service_Realtime_DQ_Portal_business_address_region_code.input.DataSet inputBody) throws java.rmi.RemoteException{
	if (realTime_Services == null)
	  _initRealTime_ServicesProxy();
	return realTime_Services.service_Realtime_DQ_Portal_business_address_region_code(inputBody);
  }	
}