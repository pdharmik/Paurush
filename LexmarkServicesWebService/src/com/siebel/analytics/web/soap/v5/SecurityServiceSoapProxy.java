package com.siebel.analytics.web.soap.v5;

public class SecurityServiceSoapProxy implements com.siebel.analytics.web.soap.v5.SecurityServiceSoap {
  private String _endpoint = null;
  private com.siebel.analytics.web.soap.v5.SecurityServiceSoap securityServiceSoap = null;
  
  public SecurityServiceSoapProxy() {
    _initSecurityServiceSoapProxy();
  }
  
  public SecurityServiceSoapProxy(String endpoint) {
    _endpoint = endpoint;
    _initSecurityServiceSoapProxy();
  }
  
  private void _initSecurityServiceSoapProxy() {
    try {
      securityServiceSoap = (new com.siebel.analytics.web.soap.v5.SecurityServiceLocator()).getSecurityServiceSoap();
      if (securityServiceSoap != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)securityServiceSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)securityServiceSoap)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (securityServiceSoap != null)
      ((javax.xml.rpc.Stub)securityServiceSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.siebel.analytics.web.soap.v5.SecurityServiceSoap getSecurityServiceSoap() {
    if (securityServiceSoap == null)
      _initSecurityServiceSoapProxy();
    return securityServiceSoap;
  }
  
  public com.siebel.analytics.web.soap.v5.Privilege[] getGlobalPrivileges(java.lang.String sessionID) throws java.rmi.RemoteException{
    if (securityServiceSoap == null)
      _initSecurityServiceSoapProxy();
    return securityServiceSoap.getGlobalPrivileges(sessionID);
  }
  
  public com.siebel.analytics.web.soap.v5.ACL getGlobalPrivilegeACL(java.lang.String privilegeName, java.lang.String sessionID) throws java.rmi.RemoteException{
    if (securityServiceSoap == null)
      _initSecurityServiceSoapProxy();
    return securityServiceSoap.getGlobalPrivilegeACL(privilegeName, sessionID);
  }
  
  public void updateGlobalPrivilegeACL(java.lang.String privilegeName, com.siebel.analytics.web.soap.v5.ACL acl, com.siebel.analytics.web.soap.v5.UpdateACLParams updateACLParams, java.lang.String sessionID) throws java.rmi.RemoteException{
    if (securityServiceSoap == null)
      _initSecurityServiceSoapProxy();
    securityServiceSoap.updateGlobalPrivilegeACL(privilegeName, acl, updateACLParams, sessionID);
  }
  
  public void forgetAccount(com.siebel.analytics.web.soap.v5.Account account, int cleanuplevel, java.lang.String sessionID) throws java.rmi.RemoteException{
    if (securityServiceSoap == null)
      _initSecurityServiceSoapProxy();
    securityServiceSoap.forgetAccount(account, cleanuplevel, sessionID);
  }
  
  public void renameAccount(java.lang.String from, java.lang.String to, java.lang.String sessionID) throws java.rmi.RemoteException{
    if (securityServiceSoap == null)
      _initSecurityServiceSoapProxy();
    securityServiceSoap.renameAccount(from, to, sessionID);
  }
  
  public void createAccount(com.siebel.analytics.web.soap.v5.Account account, java.lang.String sessionID) throws java.rmi.RemoteException{
    if (securityServiceSoap == null)
      _initSecurityServiceSoapProxy();
    securityServiceSoap.createAccount(account, sessionID);
  }
  
  public void joinGroup(java.lang.String group, java.lang.String[] member, java.lang.String sessionID) throws java.rmi.RemoteException{
    if (securityServiceSoap == null)
      _initSecurityServiceSoapProxy();
    securityServiceSoap.joinGroup(group, member, sessionID);
  }
  
  public void leaveGroup(java.lang.String group, java.lang.String[] member, java.lang.String sessionID) throws java.rmi.RemoteException{
    if (securityServiceSoap == null)
      _initSecurityServiceSoapProxy();
    securityServiceSoap.leaveGroup(group, member, sessionID);
  }
  
  public com.siebel.analytics.web.soap.v5.Account[] getGroups(java.lang.String member, java.lang.String sessionID) throws java.rmi.RemoteException{
    if (securityServiceSoap == null)
      _initSecurityServiceSoapProxy();
    return securityServiceSoap.getGroups(member, sessionID);
  }
  
  public com.siebel.analytics.web.soap.v5.Account[] getMembers(java.lang.String group, java.lang.String sessionID) throws java.rmi.RemoteException{
    if (securityServiceSoap == null)
      _initSecurityServiceSoapProxy();
    return securityServiceSoap.getMembers(group, sessionID);
  }
  
  public boolean isMember(java.lang.String group, java.lang.String member, java.lang.String sessionID) throws java.rmi.RemoteException{
    if (securityServiceSoap == null)
      _initSecurityServiceSoapProxy();
    return securityServiceSoap.isMember(group, member, sessionID);
  }
  
  public int getPermissions(com.siebel.analytics.web.soap.v5.ACL acl, com.siebel.analytics.web.soap.v5.Account account, java.lang.String sessionID) throws java.rmi.RemoteException{
    if (securityServiceSoap == null)
      _initSecurityServiceSoapProxy();
    return securityServiceSoap.getPermissions(acl, account, sessionID);
  }
  
  public com.siebel.analytics.web.soap.v5.Account[] getCatalogAccountsDatabase(com.siebel.analytics.web.soap.v5.AccountsFilter accountsFilter, java.lang.String sessionID) throws java.rmi.RemoteException{
    if (securityServiceSoap == null)
      _initSecurityServiceSoapProxy();
    return securityServiceSoap.getCatalogAccountsDatabase(accountsFilter, sessionID);
  }
  
  
}