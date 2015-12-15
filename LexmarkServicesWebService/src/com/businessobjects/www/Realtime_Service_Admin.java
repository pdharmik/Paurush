/**
 * Realtime_Service_Admin.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.businessobjects.www;

public interface Realtime_Service_Admin extends java.rmi.Remote {
    public com.businessobjects.www.DataServices.ServerX_xsd.ListOfRealtimeServicesResponse get_RTService_List(com.businessobjects.www.DataServices.ServerX_xsd.GetListOfRTServicesRequest getListOfRTServicesRequest) throws java.rmi.RemoteException;
    public com.businessobjects.www.DataServices.ServerX_xsd.RunRealtimeServiceResponse run_Realtime_Service(com.businessobjects.www.DataServices.ServerX_xsd.RunRealtimeServiceRequest runRealtimeServiceRequest) throws java.rmi.RemoteException;
    public com.businessobjects.www.DataServices.ServerX_xsd.GetMsgFormatResponse get_RTMsg_Format(com.businessobjects.www.DataServices.ServerX_xsd.GetMsgFormatRequest getMsgFormatRequest) throws java.rmi.RemoteException;
}
