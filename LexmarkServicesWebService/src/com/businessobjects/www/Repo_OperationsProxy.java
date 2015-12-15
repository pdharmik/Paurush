package com.businessobjects.www;

public class Repo_OperationsProxy implements com.businessobjects.www.Repo_Operations {
  private String _endpoint = null;
  private com.businessobjects.www.Repo_Operations repo_Operations = null;
  
  public Repo_OperationsProxy() {
    _initRepo_OperationsProxy();
  }
  
  public Repo_OperationsProxy(String endpoint) {
    _endpoint = endpoint;
    _initRepo_OperationsProxy();
  }
  
  private void _initRepo_OperationsProxy() {
    try {
      repo_Operations = (new com.businessobjects.www.DataServices_ServerLocator()).getRepo_Operations();
      if (repo_Operations != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)repo_Operations)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)repo_Operations)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (repo_Operations != null)
      ((javax.xml.rpc.Stub)repo_Operations)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.businessobjects.www.Repo_Operations getRepo_Operations() {
    if (repo_Operations == null)
      _initRepo_OperationsProxy();
    return repo_Operations;
  }
  
  public com.businessobjects.www.DataServices.ServerX_xsd.RepoOperationResponse import_Repo_Object(com.businessobjects.www.DataServices.ServerX_xsd.ImportObjectDefinitionRequest importObjectDefinitionRequest) throws java.rmi.RemoteException{
    if (repo_Operations == null)
      _initRepo_OperationsProxy();
    return repo_Operations.import_Repo_Object(importObjectDefinitionRequest);
  }
  
  public com.businessobjects.www.DataServices.ServerX_xsd.RepoOperationResponse validate_Repo_Object(com.businessobjects.www.DataServices.ServerX_xsd.ValidateObjectDefinitionRequest validateObjectDefinitionRequest) throws java.rmi.RemoteException{
    if (repo_Operations == null)
      _initRepo_OperationsProxy();
    return repo_Operations.validate_Repo_Object(validateObjectDefinitionRequest);
  }
  
  public com.businessobjects.www.DataServices.ServerX_xsd.RepoOperationResponse delete_Repo_Objects(com.businessobjects.www.DataServices.ServerX_xsd.DeleteRepoObjectsRequest deleteRepoObjectsRequest) throws java.rmi.RemoteException{
    if (repo_Operations == null)
      _initRepo_OperationsProxy();
    return repo_Operations.delete_Repo_Objects(deleteRepoObjectsRequest);
  }
  
  public com.businessobjects.www.DataServices.ServerX_xsd.RepoOperationResponse compact_Repo(com.businessobjects.www.DataServices.ServerX_xsd.CompactRepoRequest compactRepoRequest) throws java.rmi.RemoteException{
    if (repo_Operations == null)
      _initRepo_OperationsProxy();
    return repo_Operations.compact_Repo(compactRepoRequest);
  }
  
  public com.businessobjects.www.DataServices.ServerX_xsd.ExportDQReportResponse export_DQReport(com.businessobjects.www.DataServices.ServerX_xsd.ExportDQReportRequest exportDQReportRequest) throws java.rmi.RemoteException{
    if (repo_Operations == null)
      _initRepo_OperationsProxy();
    return repo_Operations.export_DQReport(exportDQReportRequest);
  }
  
  
}