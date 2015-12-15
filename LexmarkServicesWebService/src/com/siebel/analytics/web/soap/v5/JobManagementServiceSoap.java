/**
 * JobManagementServiceSoap.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.siebel.analytics.web.soap.v5;

public interface JobManagementServiceSoap extends java.rmi.Remote {
    public com.siebel.analytics.web.soap.v5.JobInfo writeListFiles(com.siebel.analytics.web.soap.v5.ReportRef report, com.siebel.analytics.web.soap.v5.ReportParams reportParams, java.lang.String segmentPath, com.siebel.analytics.web.soap.v5.TreeNodePath treeNodePath, com.siebel.analytics.web.soap.v5.SegmentationOptions segmentationOptions, java.lang.String filesystem, java.math.BigInteger timeout, java.lang.String sessionID) throws java.rmi.RemoteException;
    public com.siebel.analytics.web.soap.v5.JobInfo getJobInfo(java.math.BigInteger jobID, java.lang.String sessionID) throws java.rmi.RemoteException;
    public com.siebel.analytics.web.soap.v5.JobInfo cancelJob(java.math.BigInteger jobID, java.lang.String sessionID) throws java.rmi.RemoteException;
    public com.siebel.analytics.web.soap.v5.JobInfo getCounts(java.lang.String segmentPath, java.lang.String treePath, com.siebel.analytics.web.soap.v5.SegmentationOptions segmentationOptions, java.lang.String sessionID) throws java.rmi.RemoteException;
    public com.siebel.analytics.web.soap.v5.JobInfo purgeCache(java.lang.String segmentPath, java.lang.String treePath, java.lang.Boolean ignoreCacheRef, java.lang.String sessionID) throws java.rmi.RemoteException;
    public com.siebel.analytics.web.soap.v5.JobInfo prepareCache(java.lang.String segmentPath, java.lang.String treePath, java.lang.Boolean refresh, java.lang.String sessionID) throws java.rmi.RemoteException;
    public com.siebel.analytics.web.soap.v5.JobInfo saveResultSet(java.lang.String segmentPath, com.siebel.analytics.web.soap.v5.TreeNodePath treeNodePath, java.lang.String savedSegmentPath, com.siebel.analytics.web.soap.v5.SegmentationOptions segmentationOptions, java.lang.String SRCustomLabel, java.lang.Boolean appendStaticSegment, java.lang.String sessionID) throws java.rmi.RemoteException;
    public com.siebel.analytics.web.soap.v5.JobInfo deleteResultSet(java.lang.String targetLevel, java.lang.String[] GUIDs, java.lang.String segmentPath, java.lang.String sessionID) throws java.rmi.RemoteException;
}
