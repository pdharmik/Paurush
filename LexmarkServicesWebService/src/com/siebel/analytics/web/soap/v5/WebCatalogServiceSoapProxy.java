package com.siebel.analytics.web.soap.v5;

public class WebCatalogServiceSoapProxy implements com.siebel.analytics.web.soap.v5.WebCatalogServiceSoap {
  private String _endpoint = null;
  private com.siebel.analytics.web.soap.v5.WebCatalogServiceSoap webCatalogServiceSoap = null;
  
  public WebCatalogServiceSoapProxy() {
    _initWebCatalogServiceSoapProxy();
  }
  
  public WebCatalogServiceSoapProxy(String endpoint) {
    _endpoint = endpoint;
    _initWebCatalogServiceSoapProxy();
  }
  
  private void _initWebCatalogServiceSoapProxy() {
    try {
      webCatalogServiceSoap = (new com.siebel.analytics.web.soap.v5.WebCatalogServiceLocator()).getWebCatalogServiceSoap();
      if (webCatalogServiceSoap != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)webCatalogServiceSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)webCatalogServiceSoap)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (webCatalogServiceSoap != null)
      ((javax.xml.rpc.Stub)webCatalogServiceSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.siebel.analytics.web.soap.v5.WebCatalogServiceSoap getWebCatalogServiceSoap() {
    if (webCatalogServiceSoap == null)
      _initWebCatalogServiceSoapProxy();
    return webCatalogServiceSoap;
  }
  
  public void deleteItem(java.lang.String path, java.lang.String sessionID) throws java.rmi.RemoteException{
    if (webCatalogServiceSoap == null)
      _initWebCatalogServiceSoapProxy();
    webCatalogServiceSoap.deleteItem(path, sessionID);
  }
  
  public void removeFolder(java.lang.String path, boolean recursive, java.lang.String sessionID) throws java.rmi.RemoteException{
    if (webCatalogServiceSoap == null)
      _initWebCatalogServiceSoapProxy();
    webCatalogServiceSoap.removeFolder(path, recursive, sessionID);
  }
  
  public void createFolder(java.lang.String path, boolean createIfNotExists, java.lang.String sessionID) throws java.rmi.RemoteException{
    if (webCatalogServiceSoap == null)
      _initWebCatalogServiceSoapProxy();
    webCatalogServiceSoap.createFolder(path, createIfNotExists, sessionID);
  }
  
  public void createLink(java.lang.String path, java.lang.String pathTarget, boolean overwriteIfExists, java.lang.String sessionID) throws java.rmi.RemoteException{
    if (webCatalogServiceSoap == null)
      _initWebCatalogServiceSoapProxy();
    webCatalogServiceSoap.createLink(path, pathTarget, overwriteIfExists, sessionID);
  }
  
  public void moveItem(java.lang.String pathSrc, java.lang.String pathDest, int flagACL, java.lang.String sessionID) throws java.rmi.RemoteException{
    if (webCatalogServiceSoap == null)
      _initWebCatalogServiceSoapProxy();
    webCatalogServiceSoap.moveItem(pathSrc, pathDest, flagACL, sessionID);
  }
  
  public void copyItem(java.lang.String pathSrc, java.lang.String pathDest, int flagACL, java.lang.String sessionID) throws java.rmi.RemoteException{
    if (webCatalogServiceSoap == null)
      _initWebCatalogServiceSoapProxy();
    webCatalogServiceSoap.copyItem(pathSrc, pathDest, flagACL, sessionID);
  }
  
  public java.lang.String copyItem2(java.lang.String[] path, boolean recursive, boolean permissions, boolean timestamps, java.lang.String sessionID) throws java.rmi.RemoteException{
    if (webCatalogServiceSoap == null)
      _initWebCatalogServiceSoapProxy();
    return webCatalogServiceSoap.copyItem2(path, recursive, permissions, timestamps, sessionID);
  }
  
  public void pasteItem2(java.lang.String archive, java.lang.String replacePath, int flagACL, int flagOverwrite, java.lang.String sessionID) throws java.rmi.RemoteException{
    if (webCatalogServiceSoap == null)
      _initWebCatalogServiceSoapProxy();
    webCatalogServiceSoap.pasteItem2(archive, replacePath, flagACL, flagOverwrite, sessionID);
  }
  
  public com.siebel.analytics.web.soap.v5.ItemInfo[] getSubItems(java.lang.String path, java.lang.String mask, boolean resolveLinks, com.siebel.analytics.web.soap.v5.GetSubItemsParams options, java.lang.String sessionID) throws java.rmi.RemoteException{
    if (webCatalogServiceSoap == null)
      _initWebCatalogServiceSoapProxy();
    return webCatalogServiceSoap.getSubItems(path, mask, resolveLinks, options, sessionID);
  }
  
  public com.siebel.analytics.web.soap.v5.ItemInfo getItemInfo(java.lang.String path, boolean resolveLinks, java.lang.String sessionID) throws java.rmi.RemoteException{
    if (webCatalogServiceSoap == null)
      _initWebCatalogServiceSoapProxy();
    return webCatalogServiceSoap.getItemInfo(path, resolveLinks, sessionID);
  }
  
  public void setItemProperty(java.lang.String[] path, java.lang.String[] name, java.lang.String[] value, boolean recursive, java.lang.String sessionID) throws java.rmi.RemoteException{
    if (webCatalogServiceSoap == null)
      _initWebCatalogServiceSoapProxy();
    webCatalogServiceSoap.setItemProperty(path, name, value, recursive, sessionID);
  }
  
  public com.siebel.analytics.web.soap.v5.CatalogObject readObject(java.lang.String path, boolean resolveLinks, java.lang.String sessionID) throws java.rmi.RemoteException{
    if (webCatalogServiceSoap == null)
      _initWebCatalogServiceSoapProxy();
    return webCatalogServiceSoap.readObject(path, resolveLinks, sessionID);
  }
  
  public com.siebel.analytics.web.soap.v5.CatalogObject[] readObjects(java.lang.String[] paths, boolean resolveLinks, com.siebel.analytics.web.soap.v5.ErrorDetailsLevel errorMode, java.lang.String sessionID) throws java.rmi.RemoteException{
    if (webCatalogServiceSoap == null)
      _initWebCatalogServiceSoapProxy();
    return webCatalogServiceSoap.readObjects(paths, resolveLinks, errorMode, sessionID);
  }
  
  public void writeObject(com.siebel.analytics.web.soap.v5.CatalogObject obj, java.lang.String path, boolean resolveLinks, boolean allowOverwrite, java.lang.String sessionID) throws java.rmi.RemoteException{
    if (webCatalogServiceSoap == null)
      _initWebCatalogServiceSoapProxy();
    webCatalogServiceSoap.writeObject(obj, path, resolveLinks, allowOverwrite, sessionID);
  }
  
  public void writeReport(com.siebel.analytics.web.soap.v5.CatalogObject obj, java.lang.String path, boolean resolveLinks, boolean allowOverwrite, java.lang.String sessionID) throws java.rmi.RemoteException{
    if (webCatalogServiceSoap == null)
      _initWebCatalogServiceSoapProxy();
    webCatalogServiceSoap.writeReport(obj, path, resolveLinks, allowOverwrite, sessionID);
  }
  
  public void writeDashboard(com.siebel.analytics.web.soap.v5.CatalogObject obj, java.lang.String path, boolean resolveLinks, boolean allowOverwrite, java.lang.String sessionID) throws java.rmi.RemoteException{
    if (webCatalogServiceSoap == null)
      _initWebCatalogServiceSoapProxy();
    webCatalogServiceSoap.writeDashboard(obj, path, resolveLinks, allowOverwrite, sessionID);
  }
  
  public void writeDashboardPage(com.siebel.analytics.web.soap.v5.CatalogObject obj, java.lang.String path, boolean resolveLinks, boolean allowOverwrite, java.lang.String sessionID) throws java.rmi.RemoteException{
    if (webCatalogServiceSoap == null)
      _initWebCatalogServiceSoapProxy();
    webCatalogServiceSoap.writeDashboardPage(obj, path, resolveLinks, allowOverwrite, sessionID);
  }
  
  public void writeDashboardPrompt(com.siebel.analytics.web.soap.v5.CatalogObject obj, java.lang.String path, boolean resolveLinks, boolean allowOverwrite, java.lang.String sessionID) throws java.rmi.RemoteException{
    if (webCatalogServiceSoap == null)
      _initWebCatalogServiceSoapProxy();
    webCatalogServiceSoap.writeDashboardPrompt(obj, path, resolveLinks, allowOverwrite, sessionID);
  }
  
  public void writeSavedFilter(com.siebel.analytics.web.soap.v5.CatalogObject obj, java.lang.String path, boolean resolveLinks, boolean allowOverwrite, java.lang.String sessionID) throws java.rmi.RemoteException{
    if (webCatalogServiceSoap == null)
      _initWebCatalogServiceSoapProxy();
    webCatalogServiceSoap.writeSavedFilter(obj, path, resolveLinks, allowOverwrite, sessionID);
  }
  
  public com.siebel.analytics.web.soap.v5.ErrorInfo[] writeObjects(com.siebel.analytics.web.soap.v5.CatalogObject[] catalogObjects, boolean allowOverwrite, com.siebel.analytics.web.soap.v5.ErrorDetailsLevel errorMode, java.lang.String sessionID) throws java.rmi.RemoteException{
    if (webCatalogServiceSoap == null)
      _initWebCatalogServiceSoapProxy();
    return webCatalogServiceSoap.writeObjects(catalogObjects, allowOverwrite, errorMode, sessionID);
  }
  
  public void updateCatalogItemACL(java.lang.String path, com.siebel.analytics.web.soap.v5.ACL acl, com.siebel.analytics.web.soap.v5.UpdateCatalogItemACLParams options, java.lang.String sessionID) throws java.rmi.RemoteException{
    if (webCatalogServiceSoap == null)
      _initWebCatalogServiceSoapProxy();
    webCatalogServiceSoap.updateCatalogItemACL(path, acl, options, sessionID);
  }
  
  public void takeOwnership(java.lang.String[] path, java.lang.String name, boolean recursive, java.lang.String sessionID) throws java.rmi.RemoteException{
    if (webCatalogServiceSoap == null)
      _initWebCatalogServiceSoapProxy();
    webCatalogServiceSoap.takeOwnership(path, name, recursive, sessionID);
  }
  
  public void setItemAttributes(java.lang.String[] path, int value, int valueOff, boolean recursive, java.lang.String sessionID) throws java.rmi.RemoteException{
    if (webCatalogServiceSoap == null)
      _initWebCatalogServiceSoapProxy();
    webCatalogServiceSoap.setItemAttributes(path, value, valueOff, recursive, sessionID);
  }
  
  
}