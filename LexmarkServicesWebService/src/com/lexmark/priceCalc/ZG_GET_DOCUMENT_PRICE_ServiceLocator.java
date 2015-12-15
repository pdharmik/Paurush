/**
 * ZG_GET_DOCUMENT_PRICE_ServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.priceCalc;

public class ZG_GET_DOCUMENT_PRICE_ServiceLocator extends org.apache.axis.client.Service implements com.lexmark.priceCalc.ZG_GET_DOCUMENT_PRICE_Service {

    public ZG_GET_DOCUMENT_PRICE_ServiceLocator() {
    }


    public ZG_GET_DOCUMENT_PRICE_ServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public ZG_GET_DOCUMENT_PRICE_ServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for zg_get_document_price_bnd
    private java.lang.String zg_get_document_price_bnd_address = "http://plexwcrma001a.na.ds.lexmark.com:8010/sap/bc/srt/rfc/sap/zg_get_document_price/750/zg_get_document_price/zg_get_document_price_bnd";

    public java.lang.String getzg_get_document_price_bndAddress() {
        return zg_get_document_price_bnd_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String zg_get_document_price_bndWSDDServiceName = "zg_get_document_price_bnd";

    public java.lang.String getzg_get_document_price_bndWSDDServiceName() {
        return zg_get_document_price_bndWSDDServiceName;
    }

    public void setzg_get_document_price_bndWSDDServiceName(java.lang.String name) {
        zg_get_document_price_bndWSDDServiceName = name;
    }

    public com.lexmark.priceCalc.ZG_GET_DOCUMENT_PRICE_PortType getzg_get_document_price_bnd() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(zg_get_document_price_bnd_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getzg_get_document_price_bnd(endpoint);
    }

    public com.lexmark.priceCalc.ZG_GET_DOCUMENT_PRICE_PortType getzg_get_document_price_bnd(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.lexmark.priceCalc.Zg_get_document_price_bndStub _stub = new com.lexmark.priceCalc.Zg_get_document_price_bndStub(portAddress, this);
            _stub.setPortName(getzg_get_document_price_bndWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setzg_get_document_price_bndEndpointAddress(java.lang.String address) {
        zg_get_document_price_bnd_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.lexmark.priceCalc.ZG_GET_DOCUMENT_PRICE_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                com.lexmark.priceCalc.Zg_get_document_price_bndStub _stub = new com.lexmark.priceCalc.Zg_get_document_price_bndStub(new java.net.URL(zg_get_document_price_bnd_address), this);
                _stub.setPortName(getzg_get_document_price_bndWSDDServiceName());
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
        if ("zg_get_document_price_bnd".equals(inputPortName)) {
            return getzg_get_document_price_bnd();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("urn:sap-com:document:sap:rfc:functions", "ZG_GET_DOCUMENT_PRICE");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("urn:sap-com:document:sap:rfc:functions", "zg_get_document_price_bnd"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("zg_get_document_price_bnd".equals(portName)) {
            setzg_get_document_price_bndEndpointAddress(address);
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
