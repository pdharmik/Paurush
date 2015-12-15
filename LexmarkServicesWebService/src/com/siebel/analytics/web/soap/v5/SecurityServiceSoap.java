/**
 * SecurityServiceSoap.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.siebel.analytics.web.soap.v5;

public interface SecurityServiceSoap extends java.rmi.Remote {
    public com.siebel.analytics.web.soap.v5.Privilege[] getGlobalPrivileges(java.lang.String sessionID) throws java.rmi.RemoteException;
    public com.siebel.analytics.web.soap.v5.ACL getGlobalPrivilegeACL(java.lang.String privilegeName, java.lang.String sessionID) throws java.rmi.RemoteException;
    public void updateGlobalPrivilegeACL(java.lang.String privilegeName, com.siebel.analytics.web.soap.v5.ACL acl, com.siebel.analytics.web.soap.v5.UpdateACLParams updateACLParams, java.lang.String sessionID) throws java.rmi.RemoteException;
    public void forgetAccount(com.siebel.analytics.web.soap.v5.Account account, int cleanuplevel, java.lang.String sessionID) throws java.rmi.RemoteException;
    public void renameAccount(java.lang.String from, java.lang.String to, java.lang.String sessionID) throws java.rmi.RemoteException;
    public void createAccount(com.siebel.analytics.web.soap.v5.Account account, java.lang.String sessionID) throws java.rmi.RemoteException;
    public void joinGroup(java.lang.String group, java.lang.String[] member, java.lang.String sessionID) throws java.rmi.RemoteException;
    public void leaveGroup(java.lang.String group, java.lang.String[] member, java.lang.String sessionID) throws java.rmi.RemoteException;
    public com.siebel.analytics.web.soap.v5.Account[] getGroups(java.lang.String member, java.lang.String sessionID) throws java.rmi.RemoteException;
    public com.siebel.analytics.web.soap.v5.Account[] getMembers(java.lang.String group, java.lang.String sessionID) throws java.rmi.RemoteException;
    public boolean isMember(java.lang.String group, java.lang.String member, java.lang.String sessionID) throws java.rmi.RemoteException;
    public int getPermissions(com.siebel.analytics.web.soap.v5.ACL acl, com.siebel.analytics.web.soap.v5.Account account, java.lang.String sessionID) throws java.rmi.RemoteException;
    public com.siebel.analytics.web.soap.v5.Account[] getCatalogAccountsDatabase(com.siebel.analytics.web.soap.v5.AccountsFilter accountsFilter, java.lang.String sessionID) throws java.rmi.RemoteException;
}
