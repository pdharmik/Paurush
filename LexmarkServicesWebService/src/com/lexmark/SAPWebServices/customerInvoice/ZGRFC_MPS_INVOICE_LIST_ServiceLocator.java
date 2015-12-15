/**
 * ZGRFC_MPS_INVOICE_LIST_ServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.SAPWebServices.customerInvoice;

public class ZGRFC_MPS_INVOICE_LIST_ServiceLocator extends org.apache.axis.client.Service implements com.lexmark.SAPWebServices.customerInvoice.ZGRFC_MPS_INVOICE_LIST_Service {

    public ZGRFC_MPS_INVOICE_LIST_ServiceLocator() {
    }


    public ZGRFC_MPS_INVOICE_LIST_ServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public ZGRFC_MPS_INVOICE_LIST_ServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for ZGRFC_MPS_INVOICE_LIST
    private java.lang.String ZGRFC_MPS_INVOICE_LIST_address = "http://duslexecd02.na.ds.lexmark.com:8010/sap/bc/srt/rfc/sap/zgrfc_mps_invoice_list/220/zgrfc_mps_invoice_list/zgrfc_mps_invoice_list";

    public java.lang.String getZGRFC_MPS_INVOICE_LISTAddress() {
        return ZGRFC_MPS_INVOICE_LIST_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String ZGRFC_MPS_INVOICE_LISTWSDDServiceName = "ZGRFC_MPS_INVOICE_LIST";

    public java.lang.String getZGRFC_MPS_INVOICE_LISTWSDDServiceName() {
        return ZGRFC_MPS_INVOICE_LISTWSDDServiceName;
    }

    public void setZGRFC_MPS_INVOICE_LISTWSDDServiceName(java.lang.String name) {
        ZGRFC_MPS_INVOICE_LISTWSDDServiceName = name;
    }

    public com.lexmark.SAPWebServices.customerInvoice.ZGRFC_MPS_INVOICE_LIST_PortType getZGRFC_MPS_INVOICE_LIST() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(ZGRFC_MPS_INVOICE_LIST_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getZGRFC_MPS_INVOICE_LIST(endpoint);
    }

    public com.lexmark.SAPWebServices.customerInvoice.ZGRFC_MPS_INVOICE_LIST_PortType getZGRFC_MPS_INVOICE_LIST(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.lexmark.SAPWebServices.customerInvoice.ZGRFC_MPS_INVOICE_LIST_BindingStub _stub = new com.lexmark.SAPWebServices.customerInvoice.ZGRFC_MPS_INVOICE_LIST_BindingStub(portAddress, this);
            _stub.setPortName(getZGRFC_MPS_INVOICE_LISTWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setZGRFC_MPS_INVOICE_LISTEndpointAddress(java.lang.String address) {
        ZGRFC_MPS_INVOICE_LIST_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.lexmark.SAPWebServices.customerInvoice.ZGRFC_MPS_INVOICE_LIST_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                com.lexmark.SAPWebServices.customerInvoice.ZGRFC_MPS_INVOICE_LIST_BindingStub _stub = new com.lexmark.SAPWebServices.customerInvoice.ZGRFC_MPS_INVOICE_LIST_BindingStub(new java.net.URL(ZGRFC_MPS_INVOICE_LIST_address), this);
                _stub.setPortName(getZGRFC_MPS_INVOICE_LISTWSDDServiceName());
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
        if ("ZGRFC_MPS_INVOICE_LIST".equals(inputPortName)) {
            return getZGRFC_MPS_INVOICE_LIST();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("urn:sap-com:document:sap:rfc:functions", "ZGRFC_MPS_INVOICE_LIST");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("urn:sap-com:document:sap:rfc:functions", "ZGRFC_MPS_INVOICE_LIST"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("ZGRFC_MPS_INVOICE_LIST".equals(portName)) {
            setZGRFC_MPS_INVOICE_LISTEndpointAddress(address);
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
