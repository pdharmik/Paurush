/**
 * HtmlViewServiceSoap.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.siebel.analytics.web.soap.v6;

public interface HtmlViewServiceSoap extends java.rmi.Remote {
    public java.lang.String startPage(com.siebel.analytics.web.soap.v6.StartPageParams options, java.lang.String sessionID) throws java.rmi.RemoteException;
    public void endPage(java.lang.String pageID, java.lang.String sessionID) throws java.rmi.RemoteException;
    public void addReportToPage(java.lang.String pageID, java.lang.String reportID, com.siebel.analytics.web.soap.v6.ReportRef report, java.lang.String reportViewName, com.siebel.analytics.web.soap.v6.ReportParams reportParams, com.siebel.analytics.web.soap.v6.ReportHTMLOptions options, java.lang.String sessionID) throws java.rmi.RemoteException;
    public java.lang.String getHeadersHtml(java.lang.String pageID, java.lang.String sessionID) throws java.rmi.RemoteException;
    public java.lang.String getCommonBodyHtml(java.lang.String pageID, java.lang.String sessionID) throws java.rmi.RemoteException;
    public java.lang.String getHtmlForReport(java.lang.String pageID, java.lang.String pageReportID, java.lang.String sessionID) throws java.rmi.RemoteException;
    public void setBridge(java.lang.String bridge, java.lang.String sessionID) throws java.rmi.RemoteException;
    public java.lang.String getHtmlForPageWithOneReport(java.lang.String reportID, com.siebel.analytics.web.soap.v6.ReportRef report, java.lang.String reportViewName, com.siebel.analytics.web.soap.v6.ReportParams reportParams, com.siebel.analytics.web.soap.v6.ReportHTMLOptions reportOptions, com.siebel.analytics.web.soap.v6.StartPageParams pageParams, java.lang.String sessionID) throws java.rmi.RemoteException;
}
