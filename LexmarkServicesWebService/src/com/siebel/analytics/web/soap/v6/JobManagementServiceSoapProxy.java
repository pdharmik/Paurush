package com.siebel.analytics.web.soap.v6;

public class JobManagementServiceSoapProxy implements com.siebel.analytics.web.soap.v6.JobManagementServiceSoap {
  private String _endpoint = null;
  private com.siebel.analytics.web.soap.v6.JobManagementServiceSoap jobManagementServiceSoap = null;
  
  public JobManagementServiceSoapProxy() {
    _initJobManagementServiceSoapProxy();
  }
  
  public JobManagementServiceSoapProxy(String endpoint) {
    _endpoint = endpoint;
    _initJobManagementServiceSoapProxy();
  }
  
  private void _initJobManagementServiceSoapProxy() {
    try {
      jobManagementServiceSoap = (new com.siebel.analytics.web.soap.v6.JobManagementServiceLocator()).getJobManagementServiceSoap();
      if (jobManagementServiceSoap != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)jobManagementServiceSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)jobManagementServiceSoap)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (jobManagementServiceSoap != null)
      ((javax.xml.rpc.Stub)jobManagementServiceSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.siebel.analytics.web.soap.v6.JobManagementServiceSoap getJobManagementServiceSoap() {
    if (jobManagementServiceSoap == null)
      _initJobManagementServiceSoapProxy();
    return jobManagementServiceSoap;
  }
  
  public com.siebel.analytics.web.soap.v6.JobInfo writeListFiles(com.siebel.analytics.web.soap.v6.ReportRef report, com.siebel.analytics.web.soap.v6.ReportParams reportParams, java.lang.String segmentPath, com.siebel.analytics.web.soap.v6.TreeNodePath treeNodePath, com.siebel.analytics.web.soap.v6.SegmentationOptions segmentationOptions, java.lang.String filesystem, java.math.BigInteger timeout, java.lang.String sessionID) throws java.rmi.RemoteException{
    if (jobManagementServiceSoap == null)
      _initJobManagementServiceSoapProxy();
    return jobManagementServiceSoap.writeListFiles(report, reportParams, segmentPath, treeNodePath, segmentationOptions, filesystem, timeout, sessionID);
  }
  
  public com.siebel.analytics.web.soap.v6.JobInfo getJobInfo(java.math.BigInteger jobID, java.lang.String sessionID) throws java.rmi.RemoteException{
    if (jobManagementServiceSoap == null)
      _initJobManagementServiceSoapProxy();
    return jobManagementServiceSoap.getJobInfo(jobID, sessionID);
  }
  
  public com.siebel.analytics.web.soap.v6.JobInfo cancelJob(java.math.BigInteger jobID, java.lang.String sessionID) throws java.rmi.RemoteException{
    if (jobManagementServiceSoap == null)
      _initJobManagementServiceSoapProxy();
    return jobManagementServiceSoap.cancelJob(jobID, sessionID);
  }
  
  public com.siebel.analytics.web.soap.v6.JobInfo getCounts(java.lang.String segmentPath, java.lang.String treePath, com.siebel.analytics.web.soap.v6.SegmentationOptions segmentationOptions, java.lang.String sessionID) throws java.rmi.RemoteException{
    if (jobManagementServiceSoap == null)
      _initJobManagementServiceSoapProxy();
    return jobManagementServiceSoap.getCounts(segmentPath, treePath, segmentationOptions, sessionID);
  }
  
  public java.lang.String[] getPromptedColumns(java.lang.String segmentPath, java.lang.String treePath, java.lang.String sessionID) throws java.rmi.RemoteException{
    if (jobManagementServiceSoap == null)
      _initJobManagementServiceSoapProxy();
    return jobManagementServiceSoap.getPromptedColumns(segmentPath, treePath, sessionID);
  }
  
  public com.siebel.analytics.web.soap.v6.JobInfo purgeCache(java.lang.String segmentPath, java.lang.String treePath, java.lang.Boolean ignoreCacheRef, java.lang.String sessionID) throws java.rmi.RemoteException{
    if (jobManagementServiceSoap == null)
      _initJobManagementServiceSoapProxy();
    return jobManagementServiceSoap.purgeCache(segmentPath, treePath, ignoreCacheRef, sessionID);
  }
  
  public com.siebel.analytics.web.soap.v6.JobInfo prepareCache(java.lang.String segmentPath, java.lang.String treePath, java.lang.Boolean refresh, java.lang.String sessionID) throws java.rmi.RemoteException{
    if (jobManagementServiceSoap == null)
      _initJobManagementServiceSoapProxy();
    return jobManagementServiceSoap.prepareCache(segmentPath, treePath, refresh, sessionID);
  }
  
  public com.siebel.analytics.web.soap.v6.JobInfo saveResultSet(java.lang.String segmentPath, com.siebel.analytics.web.soap.v6.TreeNodePath treeNodePath, java.lang.String savedSegmentPath, com.siebel.analytics.web.soap.v6.SegmentationOptions segmentationOptions, java.lang.String SRCustomLabel, java.lang.Boolean appendStaticSegment, java.lang.String sessionID) throws java.rmi.RemoteException{
    if (jobManagementServiceSoap == null)
      _initJobManagementServiceSoapProxy();
    return jobManagementServiceSoap.saveResultSet(segmentPath, treeNodePath, savedSegmentPath, segmentationOptions, SRCustomLabel, appendStaticSegment, sessionID);
  }
  
  public com.siebel.analytics.web.soap.v6.JobInfo deleteResultSet(java.lang.String targetLevel, java.lang.String[] GUIDs, java.lang.String segmentPath, java.lang.String sessionID) throws java.rmi.RemoteException{
    if (jobManagementServiceSoap == null)
      _initJobManagementServiceSoapProxy();
    return jobManagementServiceSoap.deleteResultSet(targetLevel, GUIDs, segmentPath, sessionID);
  }
  
  
}