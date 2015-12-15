/**
 * Connection_Operations.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.businessobjects.www;

public interface Connection_Operations extends java.rmi.Remote {
    public com.businessobjects.www.DataServices.ServerX_xsd.ResponseStatus logout(com.businessobjects.www.DataServices.ServerX_xsd.Logout_Input logout_Input) throws java.rmi.RemoteException;
    public com.businessobjects.www.DataServices.ServerX_xsd.PingVersion ping(com.businessobjects.www.DataServices.ServerX_xsd.Ping_Input ping_Input) throws java.rmi.RemoteException;
    public com.businessobjects.www.DataServices.ServerX_xsd.Session logon(com.businessobjects.www.DataServices.ServerX_xsd.LogonRequest logonRequest) throws java.rmi.RemoteException;
}
