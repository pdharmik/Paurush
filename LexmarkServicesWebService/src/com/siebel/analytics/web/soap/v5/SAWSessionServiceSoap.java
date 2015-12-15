/**
 * SAWSessionServiceSoap.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.siebel.analytics.web.soap.v5;

public interface SAWSessionServiceSoap extends java.rmi.Remote {
    public java.lang.String logon(java.lang.String name, java.lang.String password) throws java.rmi.RemoteException;
    public com.siebel.analytics.web.soap.v5.AuthResult logonex(java.lang.String name, java.lang.String password, com.siebel.analytics.web.soap.v5.SAWSessionParameters sessionparams) throws java.rmi.RemoteException;
    public void logoff(java.lang.String sessionID) throws java.rmi.RemoteException;
    public void keepAlive(java.lang.String[] sessionID) throws java.rmi.RemoteException;
    public java.lang.String getCurUser(java.lang.String sessionID) throws java.rmi.RemoteException;
    public com.siebel.analytics.web.soap.v5.SessionEnvironment getSessionEnvironment(java.lang.String sessionID) throws java.rmi.RemoteException;
    public java.lang.String impersonate(java.lang.String name, java.lang.String password, java.lang.String impersonateID) throws java.rmi.RemoteException;
    public com.siebel.analytics.web.soap.v5.AuthResult impersonateex(java.lang.String name, java.lang.String password, java.lang.String impersonateID, com.siebel.analytics.web.soap.v5.SAWSessionParameters sessionparams) throws java.rmi.RemoteException;
}
