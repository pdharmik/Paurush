/**
 * Repo_Operations.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.businessobjects.www;

public interface Repo_Operations extends java.rmi.Remote {
    public com.businessobjects.www.DataServices.ServerX_xsd.RepoOperationResponse import_Repo_Object(com.businessobjects.www.DataServices.ServerX_xsd.ImportObjectDefinitionRequest importObjectDefinitionRequest) throws java.rmi.RemoteException;
    public com.businessobjects.www.DataServices.ServerX_xsd.RepoOperationResponse validate_Repo_Object(com.businessobjects.www.DataServices.ServerX_xsd.ValidateObjectDefinitionRequest validateObjectDefinitionRequest) throws java.rmi.RemoteException;
    public com.businessobjects.www.DataServices.ServerX_xsd.RepoOperationResponse delete_Repo_Objects(com.businessobjects.www.DataServices.ServerX_xsd.DeleteRepoObjectsRequest deleteRepoObjectsRequest) throws java.rmi.RemoteException;
    public com.businessobjects.www.DataServices.ServerX_xsd.RepoOperationResponse compact_Repo(com.businessobjects.www.DataServices.ServerX_xsd.CompactRepoRequest compactRepoRequest) throws java.rmi.RemoteException;
    public com.businessobjects.www.DataServices.ServerX_xsd.ExportDQReportResponse export_DQReport(com.businessobjects.www.DataServices.ServerX_xsd.ExportDQReportRequest exportDQReportRequest) throws java.rmi.RemoteException;
}
