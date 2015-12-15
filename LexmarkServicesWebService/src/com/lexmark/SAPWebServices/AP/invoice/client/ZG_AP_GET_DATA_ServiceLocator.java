/**
 * ZG_AP_GET_DATA_ServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.SAPWebServices.AP.invoice.client;

public class ZG_AP_GET_DATA_ServiceLocator extends org.apache.axis.client.Service implements com.lexmark.SAPWebServices.AP.invoice.client.ZG_AP_GET_DATA_Service {

    public ZG_AP_GET_DATA_ServiceLocator() {
    }


    public ZG_AP_GET_DATA_ServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public ZG_AP_GET_DATA_ServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for ZG_AP_GET_DATA
    private java.lang.String ZG_AP_GET_DATA_address = "http://duslexecd02.na.ds.lexmark.com:8010/sap/bc/srt/rfc/sap/zg_ap_get_data/220/zg_ap_get_data/zg_ap_get_data";

    public java.lang.String getZG_AP_GET_DATAAddress() {
        return ZG_AP_GET_DATA_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String ZG_AP_GET_DATAWSDDServiceName = "ZG_AP_GET_DATA";

    public java.lang.String getZG_AP_GET_DATAWSDDServiceName() {
        return ZG_AP_GET_DATAWSDDServiceName;
    }

    public void setZG_AP_GET_DATAWSDDServiceName(java.lang.String name) {
        ZG_AP_GET_DATAWSDDServiceName = name;
    }

    public com.lexmark.SAPWebServices.AP.invoice.client.ZG_AP_GET_DATA_PortType getZG_AP_GET_DATA() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(ZG_AP_GET_DATA_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getZG_AP_GET_DATA(endpoint);
    }

    public com.lexmark.SAPWebServices.AP.invoice.client.ZG_AP_GET_DATA_PortType getZG_AP_GET_DATA(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.lexmark.SAPWebServices.AP.invoice.client.ZG_AP_GET_DATA_BindingStub _stub = new com.lexmark.SAPWebServices.AP.invoice.client.ZG_AP_GET_DATA_BindingStub(portAddress, this);
            _stub.setPortName(getZG_AP_GET_DATAWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setZG_AP_GET_DATAEndpointAddress(java.lang.String address) {
        ZG_AP_GET_DATA_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.lexmark.SAPWebServices.AP.invoice.client.ZG_AP_GET_DATA_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                com.lexmark.SAPWebServices.AP.invoice.client.ZG_AP_GET_DATA_BindingStub _stub = new com.lexmark.SAPWebServices.AP.invoice.client.ZG_AP_GET_DATA_BindingStub(new java.net.URL(ZG_AP_GET_DATA_address), this);
                _stub.setPortName(getZG_AP_GET_DATAWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("ZG_AP_GET_DATA".equals(inputPortName)) {
            return getZG_AP_GET_DATA();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("urn:sap-com:document:sap:rfc:functions", "ZG_AP_GET_DATA");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("urn:sap-com:document:sap:rfc:functions", "ZG_AP_GET_DATA"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("ZG_AP_GET_DATA".equals(portName)) {
            setZG_AP_GET_DATAEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
