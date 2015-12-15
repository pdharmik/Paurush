package com.siebel.analytics.web.soap.v5;

public class ReportEditingServiceSoapProxy implements com.siebel.analytics.web.soap.v5.ReportEditingServiceSoap {
  private String _endpoint = null;
  private com.siebel.analytics.web.soap.v5.ReportEditingServiceSoap reportEditingServiceSoap = null;
  
  public ReportEditingServiceSoapProxy() {
    _initReportEditingServiceSoapProxy();
  }
  
  public ReportEditingServiceSoapProxy(String endpoint) {
    _endpoint = endpoint;
    _initReportEditingServiceSoapProxy();
  }
  
  private void _initReportEditingServiceSoapProxy() {
    try {
      reportEditingServiceSoap = (new com.siebel.analytics.web.soap.v5.ReportEditingServiceLocator()).getReportEditingServiceSoap();
      if (reportEditingServiceSoap != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)reportEditingServiceSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)reportEditingServiceSoap)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (reportEditingServiceSoap != null)
      ((javax.xml.rpc.Stub)reportEditingServiceSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.siebel.analytics.web.soap.v5.ReportEditingServiceSoap getReportEditingServiceSoap() {
    if (reportEditingServiceSoap == null)
      _initReportEditingServiceSoapProxy();
    return reportEditingServiceSoap;
  }
  
  public java.lang.Object applyReportParams(com.siebel.analytics.web.soap.v5.ReportRef reportRef, com.siebel.analytics.web.soap.v5.ReportParams reportParams, boolean encodeInString, java.lang.String sessionID) throws java.rmi.RemoteException{
    if (reportEditingServiceSoap == null)
      _initReportEditingServiceSoapProxy();
    return reportEditingServiceSoap.applyReportParams(reportRef, reportParams, encodeInString, sessionID);
  }
  
  public java.lang.String generateReportSQL(com.siebel.analytics.web.soap.v5.ReportRef reportRef, com.siebel.analytics.web.soap.v5.ReportParams reportParams, java.lang.String sessionID) throws java.rmi.RemoteException{
    if (reportEditingServiceSoap == null)
      _initReportEditingServiceSoapProxy();
    return reportEditingServiceSoap.generateReportSQL(reportRef, reportParams, sessionID);
  }
  
  
}