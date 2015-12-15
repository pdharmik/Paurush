/**
 * XmlViewServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.siebel.analytics.web.soap.v6;

public class XmlViewServiceLocator extends org.apache.axis.client.Service implements com.siebel.analytics.web.soap.v6.XmlViewService {

    public XmlViewServiceLocator() {
    }


    public XmlViewServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public XmlViewServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for XmlViewServiceSoap
    private java.lang.String XmlViewServiceSoap_address = "http://dlxkssbliapp03.lex.lexmark.com:9704/analytics/saw.dll?SoapImpl=xmlViewService";

    public java.lang.String getXmlViewServiceSoapAddress() {
        return XmlViewServiceSoap_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String XmlViewServiceSoapWSDDServiceName = "XmlViewServiceSoap";

    public java.lang.String getXmlViewServiceSoapWSDDServiceName() {
        return XmlViewServiceSoapWSDDServiceName;
    }

    public void setXmlViewServiceSoapWSDDServiceName(java.lang.String name) {
        XmlViewServiceSoapWSDDServiceName = name;
    }

    public com.siebel.analytics.web.soap.v6.XmlViewServiceSoap getXmlViewServiceSoap() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(XmlViewServiceSoap_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getXmlViewServiceSoap(endpoint);
    }

    public com.siebel.analytics.web.soap.v6.XmlViewServiceSoap getXmlViewServiceSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.siebel.analytics.web.soap.v6.XmlViewServiceStub _stub = new com.siebel.analytics.web.soap.v6.XmlViewServiceStub(portAddress, this);
            _stub.setPortName(getXmlViewServiceSoapWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setXmlViewServiceSoapEndpointAddress(java.lang.String address) {
        XmlViewServiceSoap_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.siebel.analytics.web.soap.v6.XmlViewServiceSoap.class.isAssignableFrom(serviceEndpointInterface)) {
                com.siebel.analytics.web.soap.v6.XmlViewServiceStub _stub = new com.siebel.analytics.web.soap.v6.XmlViewServiceStub(new java.net.URL(XmlViewServiceSoap_address), this);
                _stub.setPortName(getXmlViewServiceSoapWSDDServiceName());
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
        if ("XmlViewServiceSoap".equals(inputPortName)) {
            return getXmlViewServiceSoap();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("urn://oracle.bi.webservices/v6", "XmlViewService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("urn://oracle.bi.webservices/v6", "XmlViewServiceSoap"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("XmlViewServiceSoap".equals(portName)) {
            setXmlViewServiceSoapEndpointAddress(address);
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
