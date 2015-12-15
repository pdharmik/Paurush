package com.siebel.analytics.web.soap.v5;

public class HtmlViewServiceSoapProxy implements com.siebel.analytics.web.soap.v5.HtmlViewServiceSoap {
  private String _endpoint = null;
  private com.siebel.analytics.web.soap.v5.HtmlViewServiceSoap htmlViewServiceSoap = null;
  
  public HtmlViewServiceSoapProxy() {
    _initHtmlViewServiceSoapProxy();
  }
  
  public HtmlViewServiceSoapProxy(String endpoint) {
    _endpoint = endpoint;
    _initHtmlViewServiceSoapProxy();
  }
  
  private void _initHtmlViewServiceSoapProxy() {
    try {
      htmlViewServiceSoap = (new com.siebel.analytics.web.soap.v5.HtmlViewServiceLocator()).getHtmlViewService();
      if (htmlViewServiceSoap != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)htmlViewServiceSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)htmlViewServiceSoap)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (htmlViewServiceSoap != null)
      ((javax.xml.rpc.Stub)htmlViewServiceSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.siebel.analytics.web.soap.v5.HtmlViewServiceSoap getHtmlViewServiceSoap() {
    if (htmlViewServiceSoap == null)
      _initHtmlViewServiceSoapProxy();
    return htmlViewServiceSoap;
  }
  
  public java.lang.String startPage(com.siebel.analytics.web.soap.v5.StartPageParams options, java.lang.String sessionID) throws java.rmi.RemoteException{
    if (htmlViewServiceSoap == null)
      _initHtmlViewServiceSoapProxy();
    return htmlViewServiceSoap.startPage(options, sessionID);
  }
  
  public void endPage(java.lang.String pageID, java.lang.String sessionID) throws java.rmi.RemoteException{
    if (htmlViewServiceSoap == null)
      _initHtmlViewServiceSoapProxy();
    htmlViewServiceSoap.endPage(pageID, sessionID);
  }
  
  public void addReportToPage(java.lang.String pageID, java.lang.String reportID, com.siebel.analytics.web.soap.v5.ReportRef report, java.lang.String reportViewName, com.siebel.analytics.web.soap.v5.ReportParams reportParams, com.siebel.analytics.web.soap.v5.ReportHTMLOptions options, java.lang.String sessionID) throws java.rmi.RemoteException{
    if (htmlViewServiceSoap == null)
      _initHtmlViewServiceSoapProxy();
    htmlViewServiceSoap.addReportToPage(pageID, reportID, report, reportViewName, reportParams, options, sessionID);
  }
  
  public java.lang.String getHeadersHtml(java.lang.String pageID, java.lang.String sessionID) throws java.rmi.RemoteException{
    if (htmlViewServiceSoap == null)
      _initHtmlViewServiceSoapProxy();
    return htmlViewServiceSoap.getHeadersHtml(pageID, sessionID);
  }
  
  public java.lang.String getCommonBodyHtml(java.lang.String pageID, java.lang.String sessionID) throws java.rmi.RemoteException{
    if (htmlViewServiceSoap == null)
      _initHtmlViewServiceSoapProxy();
    return htmlViewServiceSoap.getCommonBodyHtml(pageID, sessionID);
  }
  
  public java.lang.String getHtmlForReport(java.lang.String pageID, java.lang.String pageReportID, java.lang.String sessionID) throws java.rmi.RemoteException{
    if (htmlViewServiceSoap == null)
      _initHtmlViewServiceSoapProxy();
    return htmlViewServiceSoap.getHtmlForReport(pageID, pageReportID, sessionID);
  }
  
  public void setBridge(java.lang.String bridge, java.lang.String sessionID) throws java.rmi.RemoteException{
    if (htmlViewServiceSoap == null)
      _initHtmlViewServiceSoapProxy();
    htmlViewServiceSoap.setBridge(bridge, sessionID);
  }
  
  public java.lang.String getHtmlForPageWithOneReport(java.lang.String reportID, com.siebel.analytics.web.soap.v5.ReportRef report, java.lang.String reportViewName, com.siebel.analytics.web.soap.v5.ReportParams reportParams, com.siebel.analytics.web.soap.v5.ReportHTMLOptions reportOptions, com.siebel.analytics.web.soap.v5.StartPageParams pageParams, java.lang.String sessionID) throws java.rmi.RemoteException{
    if (htmlViewServiceSoap == null)
      _initHtmlViewServiceSoapProxy();
    return htmlViewServiceSoap.getHtmlForPageWithOneReport(reportID, report, reportViewName, reportParams, reportOptions, pageParams, sessionID);
  }
  
  
}