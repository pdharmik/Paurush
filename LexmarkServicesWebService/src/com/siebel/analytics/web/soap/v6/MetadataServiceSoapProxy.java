package com.siebel.analytics.web.soap.v6;

public class MetadataServiceSoapProxy implements com.siebel.analytics.web.soap.v6.MetadataServiceSoap {
  private String _endpoint = null;
  private com.siebel.analytics.web.soap.v6.MetadataServiceSoap metadataServiceSoap = null;
  
  public MetadataServiceSoapProxy() {
    _initMetadataServiceSoapProxy();
  }
  
  public MetadataServiceSoapProxy(String endpoint) {
    _endpoint = endpoint;
    _initMetadataServiceSoapProxy();
  }
  
  private void _initMetadataServiceSoapProxy() {
    try {
      metadataServiceSoap = (new com.siebel.analytics.web.soap.v6.MetadataServiceLocator()).getMetadataServiceSoap();
      if (metadataServiceSoap != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)metadataServiceSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)metadataServiceSoap)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (metadataServiceSoap != null)
      ((javax.xml.rpc.Stub)metadataServiceSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.siebel.analytics.web.soap.v6.MetadataServiceSoap getMetadataServiceSoap() {
    if (metadataServiceSoap == null)
      _initMetadataServiceSoapProxy();
    return metadataServiceSoap;
  }
  
  public boolean clearQueryCache(java.lang.String sessionID) throws java.rmi.RemoteException{
    if (metadataServiceSoap == null)
      _initMetadataServiceSoapProxy();
    return metadataServiceSoap.clearQueryCache(sessionID);
  }
  
  public com.siebel.analytics.web.soap.v6.SASubjectArea[] getSubjectAreas(java.lang.String sessionID) throws java.rmi.RemoteException{
    if (metadataServiceSoap == null)
      _initMetadataServiceSoapProxy();
    return metadataServiceSoap.getSubjectAreas(sessionID);
  }
  
  public com.siebel.analytics.web.soap.v6.SASubjectArea describeSubjectArea(java.lang.String subjectAreaName, com.siebel.analytics.web.soap.v6.SASubjectAreaDetails detailsLevel, java.lang.String sessionID) throws java.rmi.RemoteException{
    if (metadataServiceSoap == null)
      _initMetadataServiceSoapProxy();
    return metadataServiceSoap.describeSubjectArea(subjectAreaName, detailsLevel, sessionID);
  }
  
  public com.siebel.analytics.web.soap.v6.SATable describeTable(java.lang.String subjectAreaName, java.lang.String tableName, com.siebel.analytics.web.soap.v6.SATableDetails detailsLevel, java.lang.String sessionID) throws java.rmi.RemoteException{
    if (metadataServiceSoap == null)
      _initMetadataServiceSoapProxy();
    return metadataServiceSoap.describeTable(subjectAreaName, tableName, detailsLevel, sessionID);
  }
  
  public com.siebel.analytics.web.soap.v6.SAColumn describeColumn(java.lang.String subjectAreaName, java.lang.String tableName, java.lang.String columnName, java.lang.String sessionID) throws java.rmi.RemoteException{
    if (metadataServiceSoap == null)
      _initMetadataServiceSoapProxy();
    return metadataServiceSoap.describeColumn(subjectAreaName, tableName, columnName, sessionID);
  }
  
  
}