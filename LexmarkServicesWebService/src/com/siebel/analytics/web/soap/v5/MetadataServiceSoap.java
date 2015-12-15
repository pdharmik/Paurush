/**
 * MetadataServiceSoap.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.siebel.analytics.web.soap.v5;

public interface MetadataServiceSoap extends java.rmi.Remote {
    public com.siebel.analytics.web.soap.v5.SASubjectArea[] getSubjectAreas(java.lang.String sessionID) throws java.rmi.RemoteException;
    public com.siebel.analytics.web.soap.v5.SASubjectArea describeSubjectArea(java.lang.String subjectAreaName, com.siebel.analytics.web.soap.v5.SASubjectAreaDetails detailsLevel, java.lang.String sessionID) throws java.rmi.RemoteException;
    public com.siebel.analytics.web.soap.v5.SATable describeTable(java.lang.String subjectAreaName, java.lang.String tableName, com.siebel.analytics.web.soap.v5.SATableDetails detailsLevel, java.lang.String sessionID) throws java.rmi.RemoteException;
    public com.siebel.analytics.web.soap.v5.SAColumn describeColumn(java.lang.String subjectAreaName, java.lang.String tableName, java.lang.String columnName, java.lang.String sessionID) throws java.rmi.RemoteException;
}
