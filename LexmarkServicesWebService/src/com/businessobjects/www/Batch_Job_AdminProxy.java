package com.businessobjects.www;

public class Batch_Job_AdminProxy implements com.businessobjects.www.Batch_Job_Admin {
  private String _endpoint = null;
  private com.businessobjects.www.Batch_Job_Admin batch_Job_Admin = null;
  
  public Batch_Job_AdminProxy() {
    _initBatch_Job_AdminProxy();
  }
  
  public Batch_Job_AdminProxy(String endpoint) {
    _endpoint = endpoint;
    _initBatch_Job_AdminProxy();
  }
  
  private void _initBatch_Job_AdminProxy() {
    try {
      batch_Job_Admin = (new com.businessobjects.www.DataServices_ServerLocator()).getBatch_Job_Admin();
      if (batch_Job_Admin != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)batch_Job_Admin)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)batch_Job_Admin)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (batch_Job_Admin != null)
      ((javax.xml.rpc.Stub)batch_Job_Admin)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.businessobjects.www.Batch_Job_Admin getBatch_Job_Admin() {
    if (batch_Job_Admin == null)
      _initBatch_Job_AdminProxy();
    return batch_Job_Admin;
  }
  
  public com.businessobjects.www.DataServices.ServerX_xsd.ErrorLogResponse get_Error_Log(com.businessobjects.www.DataServices.ServerX_xsd.ErrorLogRequest errorLogRequest) throws java.rmi.RemoteException{
    if (batch_Job_Admin == null)
      _initBatch_Job_AdminProxy();
    return batch_Job_Admin.get_Error_Log(errorLogRequest);
  }
  
  public com.businessobjects.www.DataServices.ServerX_xsd.TraceLogResponse get_Trace_Log(com.businessobjects.www.DataServices.ServerX_xsd.TraceLogRequest traceLogRequest) throws java.rmi.RemoteException{
    if (batch_Job_Admin == null)
      _initBatch_Job_AdminProxy();
    return batch_Job_Admin.get_Trace_Log(traceLogRequest);
  }
  
  public com.businessobjects.www.DataServices.ServerX_xsd.MonitorLogResponse get_Monitor_Log(com.businessobjects.www.DataServices.ServerX_xsd.MonitorLogRequest monitorLogRequest) throws java.rmi.RemoteException{
    if (batch_Job_Admin == null)
      _initBatch_Job_AdminProxy();
    return batch_Job_Admin.get_Monitor_Log(monitorLogRequest);
  }
  
  public com.businessobjects.www.DataServices.ServerX_xsd.BatchJobStatusResponse get_BatchJob_Status(com.businessobjects.www.DataServices.ServerX_xsd.BatchJobStatusRequest batchJobStatusRequest) throws java.rmi.RemoteException{
    if (batch_Job_Admin == null)
      _initBatch_Job_AdminProxy();
    return batch_Job_Admin.get_BatchJob_Status(batchJobStatusRequest);
  }
  
  public com.businessobjects.www.DataServices.ServerX_xsd.StopBatchJobResponse stop_Batch_Job(com.businessobjects.www.DataServices.ServerX_xsd.StopBatchJobRequest stopBatchJobRequest) throws java.rmi.RemoteException{
    if (batch_Job_Admin == null)
      _initBatch_Job_AdminProxy();
    return batch_Job_Admin.stop_Batch_Job(stopBatchJobRequest);
  }
  
  public com.businessobjects.www.DataServices.ServerX_xsd.BatchJobRunIDsResponse get_BatchJob_RunIDs(com.businessobjects.www.DataServices.ServerX_xsd.BatchJobRunIDsRequest batchJobRunIDsRequest) throws java.rmi.RemoteException{
    if (batch_Job_Admin == null)
      _initBatch_Job_AdminProxy();
    return batch_Job_Admin.get_BatchJob_RunIDs(batchJobRunIDsRequest);
  }
  
  public com.businessobjects.www.DataServices.ServerX_xsd.ListOfBatchJobsResponse get_BatchJob_List(com.businessobjects.www.DataServices.ServerX_xsd.GetListOfBatchJobsRequest getListOfBatchJobsRequest) throws java.rmi.RemoteException{
    if (batch_Job_Admin == null)
      _initBatch_Job_AdminProxy();
    return batch_Job_Admin.get_BatchJob_List(getListOfBatchJobsRequest);
  }
  
  public com.businessobjects.www.DataServices.ServerX_xsd.BatchJobResponse run_Batch_Job(com.businessobjects.www.DataServices.ServerX_xsd.RunBatchJobRequest runBatchJobRequest) throws java.rmi.RemoteException{
    if (batch_Job_Admin == null)
      _initBatch_Job_AdminProxy();
    return batch_Job_Admin.run_Batch_Job(runBatchJobRequest);
  }
  
  public com.businessobjects.www.DataServices.ServerX_xsd.GetBatchJobInputFormatResponse get_Job_Input_Format(com.businessobjects.www.DataServices.ServerX_xsd.GetBatchJobInputFormatRequest getBatchJobInputFormatRequest) throws java.rmi.RemoteException{
    if (batch_Job_Admin == null)
      _initBatch_Job_AdminProxy();
    return batch_Job_Admin.get_Job_Input_Format(getBatchJobInputFormatRequest);
  }
  
  
}