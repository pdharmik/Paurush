/**
 * Batch_Job_Admin.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.businessobjects.www;

public interface Batch_Job_Admin extends java.rmi.Remote {
    public com.businessobjects.www.DataServices.ServerX_xsd.ErrorLogResponse get_Error_Log(com.businessobjects.www.DataServices.ServerX_xsd.ErrorLogRequest errorLogRequest) throws java.rmi.RemoteException;
    public com.businessobjects.www.DataServices.ServerX_xsd.TraceLogResponse get_Trace_Log(com.businessobjects.www.DataServices.ServerX_xsd.TraceLogRequest traceLogRequest) throws java.rmi.RemoteException;
    public com.businessobjects.www.DataServices.ServerX_xsd.MonitorLogResponse get_Monitor_Log(com.businessobjects.www.DataServices.ServerX_xsd.MonitorLogRequest monitorLogRequest) throws java.rmi.RemoteException;
    public com.businessobjects.www.DataServices.ServerX_xsd.BatchJobStatusResponse get_BatchJob_Status(com.businessobjects.www.DataServices.ServerX_xsd.BatchJobStatusRequest batchJobStatusRequest) throws java.rmi.RemoteException;
    public com.businessobjects.www.DataServices.ServerX_xsd.StopBatchJobResponse stop_Batch_Job(com.businessobjects.www.DataServices.ServerX_xsd.StopBatchJobRequest stopBatchJobRequest) throws java.rmi.RemoteException;
    public com.businessobjects.www.DataServices.ServerX_xsd.BatchJobRunIDsResponse get_BatchJob_RunIDs(com.businessobjects.www.DataServices.ServerX_xsd.BatchJobRunIDsRequest batchJobRunIDsRequest) throws java.rmi.RemoteException;
    public com.businessobjects.www.DataServices.ServerX_xsd.ListOfBatchJobsResponse get_BatchJob_List(com.businessobjects.www.DataServices.ServerX_xsd.GetListOfBatchJobsRequest getListOfBatchJobsRequest) throws java.rmi.RemoteException;
    public com.businessobjects.www.DataServices.ServerX_xsd.BatchJobResponse run_Batch_Job(com.businessobjects.www.DataServices.ServerX_xsd.RunBatchJobRequest runBatchJobRequest) throws java.rmi.RemoteException;
    public com.businessobjects.www.DataServices.ServerX_xsd.GetBatchJobInputFormatResponse get_Job_Input_Format(com.businessobjects.www.DataServices.ServerX_xsd.GetBatchJobInputFormatRequest getBatchJobInputFormatRequest) throws java.rmi.RemoteException;
}
