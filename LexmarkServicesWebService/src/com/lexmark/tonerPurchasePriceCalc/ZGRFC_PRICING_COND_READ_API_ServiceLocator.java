/**
 * ZGRFC_PRICING_COND_READ_API_ServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.tonerPurchasePriceCalc;

public class ZGRFC_PRICING_COND_READ_API_ServiceLocator extends org.apache.axis.client.Service implements com.lexmark.tonerPurchasePriceCalc.ZGRFC_PRICING_COND_READ_API_Service {

    public ZGRFC_PRICING_COND_READ_API_ServiceLocator() {
    }


    public ZGRFC_PRICING_COND_READ_API_ServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public ZGRFC_PRICING_COND_READ_API_ServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for ZGRFC_PRICING_COND_READ_API_BN
    private java.lang.String ZGRFC_PRICING_COND_READ_API_BN_address = "http://qlexwcrma001a.na.ds.lexmark.com:8010/sap/bc/srt/rfc/sap/zgrfc_pricing_cond_read/750/zgrfc_pricing_cond_read_api/zgrfc_pricing_cond_read_api_bn";

    public java.lang.String getZGRFC_PRICING_COND_READ_API_BNAddress() {
        return ZGRFC_PRICING_COND_READ_API_BN_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String ZGRFC_PRICING_COND_READ_API_BNWSDDServiceName = "ZGRFC_PRICING_COND_READ_API_BN";

    public java.lang.String getZGRFC_PRICING_COND_READ_API_BNWSDDServiceName() {
        return ZGRFC_PRICING_COND_READ_API_BNWSDDServiceName;
    }

    public void setZGRFC_PRICING_COND_READ_API_BNWSDDServiceName(java.lang.String name) {
        ZGRFC_PRICING_COND_READ_API_BNWSDDServiceName = name;
    }

    public com.lexmark.tonerPurchasePriceCalc.ZGRFC_PRICING_COND_READ getZGRFC_PRICING_COND_READ_API_BN() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(ZGRFC_PRICING_COND_READ_API_BN_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getZGRFC_PRICING_COND_READ_API_BN(endpoint);
    }

    public com.lexmark.tonerPurchasePriceCalc.ZGRFC_PRICING_COND_READ getZGRFC_PRICING_COND_READ_API_BN(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.lexmark.tonerPurchasePriceCalc.ZGRFC_PRICING_COND_READ_API_BNStub _stub = new com.lexmark.tonerPurchasePriceCalc.ZGRFC_PRICING_COND_READ_API_BNStub(portAddress, this);
            _stub.setPortName(getZGRFC_PRICING_COND_READ_API_BNWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setZGRFC_PRICING_COND_READ_API_BNEndpointAddress(java.lang.String address) {
        ZGRFC_PRICING_COND_READ_API_BN_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.lexmark.tonerPurchasePriceCalc.ZGRFC_PRICING_COND_READ.class.isAssignableFrom(serviceEndpointInterface)) {
                com.lexmark.tonerPurchasePriceCalc.ZGRFC_PRICING_COND_READ_API_BNStub _stub = new com.lexmark.tonerPurchasePriceCalc.ZGRFC_PRICING_COND_READ_API_BNStub(new java.net.URL(ZGRFC_PRICING_COND_READ_API_BN_address), this);
                _stub.setPortName(getZGRFC_PRICING_COND_READ_API_BNWSDDServiceName());
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
        if ("ZGRFC_PRICING_COND_READ_API_BN".equals(inputPortName)) {
            return getZGRFC_PRICING_COND_READ_API_BN();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("urn:sap-com:document:sap:rfc:functions", "ZGRFC_PRICING_COND_READ_API");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("urn:sap-com:document:sap:rfc:functions", "ZGRFC_PRICING_COND_READ_API_BN"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("ZGRFC_PRICING_COND_READ_API_BN".equals(portName)) {
            setZGRFC_PRICING_COND_READ_API_BNEndpointAddress(address);
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
