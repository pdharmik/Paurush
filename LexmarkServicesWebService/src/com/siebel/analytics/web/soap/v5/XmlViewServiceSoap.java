/**
 * XmlViewServiceSoap.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.siebel.analytics.web.soap.v5;

public interface XmlViewServiceSoap extends java.rmi.Remote {
    public java.lang.Object getResults(com.siebel.analytics.web.soap.v5.ReportRef report, java.lang.String outputFormat, boolean encodeInString, com.siebel.analytics.web.soap.v5.ReportParams reportParams, java.lang.String sessionID) throws java.rmi.RemoteException;
    public com.siebel.analytics.web.soap.v5.QueryResults executeXMLQuery(com.siebel.analytics.web.soap.v5.ReportRef report, com.siebel.analytics.web.soap.v5.XMLQueryOutputFormat outputFormat, com.siebel.analytics.web.soap.v5.XMLQueryExecutionOptions executionOptions, com.siebel.analytics.web.soap.v5.ReportParams reportParams, java.lang.String sessionID) throws java.rmi.RemoteException;
    public com.siebel.analytics.web.soap.v5.QueryResults executeSQLQuery(java.lang.String sql, com.siebel.analytics.web.soap.v5.XMLQueryOutputFormat outputFormat, com.siebel.analytics.web.soap.v5.XMLQueryExecutionOptions executionOptions, java.lang.String sessionID) throws java.rmi.RemoteException;
    public com.siebel.analytics.web.soap.v5.QueryResults fetchNext(java.lang.String queryID, java.lang.String sessionID) throws java.rmi.RemoteException;
    public void cancelQuery(java.lang.String queryID, java.lang.String sessionID) throws java.rmi.RemoteException;
}
