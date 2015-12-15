/**
 * TaxCalculateWSLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.taxCalc;

public class TaxCalculateWSLocator extends org.apache.axis.client.Service implements com.lexmark.taxCalc.TaxCalculateWS {

    public TaxCalculateWSLocator() {
    }


    public TaxCalculateWSLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public TaxCalculateWSLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for LXKTaxCalculationWS_webservices_provider_taxCalculateWS_Port
    private java.lang.String LXKTaxCalculationWS_webservices_provider_taxCalculateWS_Port_address = "http://qlexrwmis004.lex.lexmark.com:9600/ws/LXKTaxCalculationWS.webservices.provider:taxCalculateWS/LXKTaxCalculationWS_webservices_provider_taxCalculateWS_Port";

    public java.lang.String getLXKTaxCalculationWS_webservices_provider_taxCalculateWS_PortAddress() {
        return LXKTaxCalculationWS_webservices_provider_taxCalculateWS_Port_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String LXKTaxCalculationWS_webservices_provider_taxCalculateWS_PortWSDDServiceName = "LXKTaxCalculationWS_webservices_provider_taxCalculateWS_Port";

    public java.lang.String getLXKTaxCalculationWS_webservices_provider_taxCalculateWS_PortWSDDServiceName() {
        return LXKTaxCalculationWS_webservices_provider_taxCalculateWS_PortWSDDServiceName;
    }

    public void setLXKTaxCalculationWS_webservices_provider_taxCalculateWS_PortWSDDServiceName(java.lang.String name) {
        LXKTaxCalculationWS_webservices_provider_taxCalculateWS_PortWSDDServiceName = name;
    }

    public com.lexmark.taxCalc.TaxCalculateWS_PortType getLXKTaxCalculationWS_webservices_provider_taxCalculateWS_Port() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(LXKTaxCalculationWS_webservices_provider_taxCalculateWS_Port_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getLXKTaxCalculationWS_webservices_provider_taxCalculateWS_Port(endpoint);
    }

    public com.lexmark.taxCalc.TaxCalculateWS_PortType getLXKTaxCalculationWS_webservices_provider_taxCalculateWS_Port(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.lexmark.taxCalc.LXKTaxCalculationWS_webservices_provider_taxCalculateWS_BinderStub _stub = new com.lexmark.taxCalc.LXKTaxCalculationWS_webservices_provider_taxCalculateWS_BinderStub(portAddress, this);
            _stub.setPortName(getLXKTaxCalculationWS_webservices_provider_taxCalculateWS_PortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setLXKTaxCalculationWS_webservices_provider_taxCalculateWS_PortEndpointAddress(java.lang.String address) {
        LXKTaxCalculationWS_webservices_provider_taxCalculateWS_Port_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.lexmark.taxCalc.TaxCalculateWS_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                com.lexmark.taxCalc.LXKTaxCalculationWS_webservices_provider_taxCalculateWS_BinderStub _stub = new com.lexmark.taxCalc.LXKTaxCalculationWS_webservices_provider_taxCalculateWS_BinderStub(new java.net.URL(LXKTaxCalculationWS_webservices_provider_taxCalculateWS_Port_address), this);
                _stub.setPortName(getLXKTaxCalculationWS_webservices_provider_taxCalculateWS_PortWSDDServiceName());
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
        if ("LXKTaxCalculationWS_webservices_provider_taxCalculateWS_Port".equals(inputPortName)) {
            return getLXKTaxCalculationWS_webservices_provider_taxCalculateWS_Port();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://dlexrwmis001.lex.lexmark.com/LXKTaxCalculationWS/webservices/provider/taxCalculateWS", "taxCalculateWS");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://dlexrwmis001.lex.lexmark.com/LXKTaxCalculationWS/webservices/provider/taxCalculateWS", "LXKTaxCalculationWS_webservices_provider_taxCalculateWS_Port"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("LXKTaxCalculationWS_webservices_provider_taxCalculateWS_Port".equals(portName)) {
            setLXKTaxCalculationWS_webservices_provider_taxCalculateWS_PortEndpointAddress(address);
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
