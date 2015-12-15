/**
 * ZG_GET_AP_DETAILS_ServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.SAPWebServices.AP.serviceRequest.client;

public class ZG_GET_AP_DETAILS_ServiceLocator extends org.apache.axis.client.Service implements com.lexmark.SAPWebServices.AP.serviceRequest.client.ZG_GET_AP_DETAILS_Service {

    public ZG_GET_AP_DETAILS_ServiceLocator() {
    }


    public ZG_GET_AP_DETAILS_ServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public ZG_GET_AP_DETAILS_ServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for ZG_GET_AP_DETAILS_BN
    private java.lang.String ZG_GET_AP_DETAILS_BN_address = "http://uslexecp07.na.ds.lexmark.com:8015/sap/bc/srt/rfc/sap/zg_get_ap_details/750/zg_get_ap_details/zg_get_ap_details_bn";

    public java.lang.String getZG_GET_AP_DETAILS_BNAddress() {
        return ZG_GET_AP_DETAILS_BN_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String ZG_GET_AP_DETAILS_BNWSDDServiceName = "ZG_GET_AP_DETAILS_BN";

    public java.lang.String getZG_GET_AP_DETAILS_BNWSDDServiceName() {
        return ZG_GET_AP_DETAILS_BNWSDDServiceName;
    }

    public void setZG_GET_AP_DETAILS_BNWSDDServiceName(java.lang.String name) {
        ZG_GET_AP_DETAILS_BNWSDDServiceName = name;
    }

    public com.lexmark.SAPWebServices.AP.serviceRequest.client.ZG_GET_AP_DETAILS_PortType getZG_GET_AP_DETAILS_BN() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(ZG_GET_AP_DETAILS_BN_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getZG_GET_AP_DETAILS_BN(endpoint);
    }

    public com.lexmark.SAPWebServices.AP.serviceRequest.client.ZG_GET_AP_DETAILS_PortType getZG_GET_AP_DETAILS_BN(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.lexmark.SAPWebServices.AP.serviceRequest.client.ZG_GET_AP_DETAILS_BNStub _stub = new com.lexmark.SAPWebServices.AP.serviceRequest.client.ZG_GET_AP_DETAILS_BNStub(portAddress, this);
            _stub.setPortName(getZG_GET_AP_DETAILS_BNWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setZG_GET_AP_DETAILS_BNEndpointAddress(java.lang.String address) {
        ZG_GET_AP_DETAILS_BN_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.lexmark.SAPWebServices.AP.serviceRequest.client.ZG_GET_AP_DETAILS_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                com.lexmark.SAPWebServices.AP.serviceRequest.client.ZG_GET_AP_DETAILS_BNStub _stub = new com.lexmark.SAPWebServices.AP.serviceRequest.client.ZG_GET_AP_DETAILS_BNStub(new java.net.URL(ZG_GET_AP_DETAILS_BN_address), this);
                _stub.setPortName(getZG_GET_AP_DETAILS_BNWSDDServiceName());
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
        if ("ZG_GET_AP_DETAILS_BN".equals(inputPortName)) {
            return getZG_GET_AP_DETAILS_BN();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("urn:sap-com:document:sap:rfc:functions", "ZG_GET_AP_DETAILS");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("urn:sap-com:document:sap:rfc:functions", "ZG_GET_AP_DETAILS_BN"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("ZG_GET_AP_DETAILS_BN".equals(portName)) {
            setZG_GET_AP_DETAILS_BNEndpointAddress(address);
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
