/**
 * DataServices_Server.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.businessobjects.www;

public interface DataServices_Server extends javax.xml.rpc.Service {
    public java.lang.String getBatch_Job_AdminAddress();

    public com.businessobjects.www.Batch_Job_Admin getBatch_Job_Admin() throws javax.xml.rpc.ServiceException;

    public com.businessobjects.www.Batch_Job_Admin getBatch_Job_Admin(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
    public java.lang.String getConnection_OperationsAddress();

    public com.businessobjects.www.Connection_Operations getConnection_Operations() throws javax.xml.rpc.ServiceException;

    public com.businessobjects.www.Connection_Operations getConnection_Operations(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
    public java.lang.String getRealtime_Service_AdminAddress();

    public com.businessobjects.www.Realtime_Service_Admin getRealtime_Service_Admin() throws javax.xml.rpc.ServiceException;

    public com.businessobjects.www.Realtime_Service_Admin getRealtime_Service_Admin(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
    public java.lang.String getRepo_OperationsAddress();

    public com.businessobjects.www.Repo_Operations getRepo_Operations() throws javax.xml.rpc.ServiceException;

    public com.businessobjects.www.Repo_Operations getRepo_Operations(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
    public java.lang.String getRealTime_ServicesAddress();

    public com.businessobjects.www.RealTime_Services getRealTime_Services() throws javax.xml.rpc.ServiceException;

    public com.businessobjects.www.RealTime_Services getRealTime_Services(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
