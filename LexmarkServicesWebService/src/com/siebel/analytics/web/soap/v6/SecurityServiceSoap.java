/**
 * SecurityServiceSoap.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.siebel.analytics.web.soap.v6;

public interface SecurityServiceSoap extends java.rmi.Remote {
    public com.siebel.analytics.web.soap.v6.Privilege[] getGlobalPrivileges(java.lang.String sessionID) throws java.rmi.RemoteException;
    public com.siebel.analytics.web.soap.v6.ACL getGlobalPrivilegeACL(java.lang.String privilegeName, java.lang.String sessionID) throws java.rmi.RemoteException;
    public void updateGlobalPrivilegeACL(java.lang.String privilegeName, com.siebel.analytics.web.soap.v6.ACL acl, com.siebel.analytics.web.soap.v6.UpdateACLParams updateACLParams, java.lang.String sessionID) throws java.rmi.RemoteException;
    public void forgetAccounts(com.siebel.analytics.web.soap.v6.Account[] account, int cleanuplevel, java.lang.String sessionID) throws java.rmi.RemoteException;
    public void renameAccounts(com.siebel.analytics.web.soap.v6.Account[] from, com.siebel.analytics.web.soap.v6.Account[] to, java.lang.String sessionID) throws java.rmi.RemoteException;
    public void joinGroups(com.siebel.analytics.web.soap.v6.Account[] group, com.siebel.analytics.web.soap.v6.Account[] member, java.lang.String sessionID) throws java.rmi.RemoteException;
    public void leaveGroups(com.siebel.analytics.web.soap.v6.Account[] group, com.siebel.analytics.web.soap.v6.Account[] member, java.lang.String sessionID) throws java.rmi.RemoteException;
    public com.siebel.analytics.web.soap.v6.Account[] getGroups(com.siebel.analytics.web.soap.v6.Account[] member, java.lang.Boolean expandGroups, java.lang.String sessionID) throws java.rmi.RemoteException;
    public com.siebel.analytics.web.soap.v6.Account[] getMembers(com.siebel.analytics.web.soap.v6.Account[] group, java.lang.Boolean expandGroups, java.lang.String sessionID) throws java.rmi.RemoteException;
    public boolean isMember(com.siebel.analytics.web.soap.v6.Account[] group, com.siebel.analytics.web.soap.v6.Account[] member, java.lang.Boolean expandGroups, java.lang.String sessionID) throws java.rmi.RemoteException;
    public int[] getPermissions(com.siebel.analytics.web.soap.v6.ACL[] acls, com.siebel.analytics.web.soap.v6.Account account, java.lang.String sessionID) throws java.rmi.RemoteException;
    public boolean[] getPrivilegesStatus(java.lang.String[] privileges, java.lang.String sessionID) throws java.rmi.RemoteException;
    public com.siebel.analytics.web.soap.v6.Account[] getAccounts(com.siebel.analytics.web.soap.v6.Account[] account, java.lang.String sessionID) throws java.rmi.RemoteException;
}
