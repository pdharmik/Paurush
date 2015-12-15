/**
 * ReportEditingServiceSoap.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.siebel.analytics.web.soap.v5;

public interface ReportEditingServiceSoap extends java.rmi.Remote {
    public java.lang.Object applyReportParams(com.siebel.analytics.web.soap.v5.ReportRef reportRef, com.siebel.analytics.web.soap.v5.ReportParams reportParams, boolean encodeInString, java.lang.String sessionID) throws java.rmi.RemoteException;
    public java.lang.String generateReportSQL(com.siebel.analytics.web.soap.v5.ReportRef reportRef, com.siebel.analytics.web.soap.v5.ReportParams reportParams, java.lang.String sessionID) throws java.rmi.RemoteException;
}
