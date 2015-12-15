/**
 * XmlViewServiceSoap.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.siebel.analytics.web.soap.v6;

public interface XmlViewServiceSoap extends java.rmi.Remote {
    public com.siebel.analytics.web.soap.v6.QueryResults executeXMLQuery(com.siebel.analytics.web.soap.v6.ReportRef report, com.siebel.analytics.web.soap.v6.XMLQueryOutputFormat outputFormat, com.siebel.analytics.web.soap.v6.XMLQueryExecutionOptions executionOptions, com.siebel.analytics.web.soap.v6.ReportParams reportParams, java.lang.String sessionID) throws java.rmi.RemoteException;
    public java.lang.String upgradeXML(java.lang.String xml, java.lang.String sessionID) throws java.rmi.RemoteException;
    public com.siebel.analytics.web.soap.v6.QueryResults executeSQLQuery(java.lang.String sql, com.siebel.analytics.web.soap.v6.XMLQueryOutputFormat outputFormat, com.siebel.analytics.web.soap.v6.XMLQueryExecutionOptions executionOptions, java.lang.String sessionID) throws java.rmi.RemoteException;
    public com.siebel.analytics.web.soap.v6.QueryResults fetchNext(java.lang.String queryID, java.lang.String sessionID) throws java.rmi.RemoteException;
    public void cancelQuery(java.lang.String queryID, java.lang.String sessionID) throws java.rmi.RemoteException;
    public java.lang.String[] getPromptedFilters(com.siebel.analytics.web.soap.v6.ReportRef report, java.lang.String sessionID) throws java.rmi.RemoteException;
}
