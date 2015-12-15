/**
 * SalesOrderWSLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.SiebelShared.source.updateServiceOrders.client;

public class SalesOrderWSLocator extends org.apache.axis.client.Service implements com.lexmark.SiebelShared.source.updateServiceOrders.client.SalesOrderWS {

    public SalesOrderWSLocator() {
    }


    public SalesOrderWSLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public SalesOrderWSLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for SalesOrder_salesOrderWS_Port
    private java.lang.String SalesOrder_salesOrderWS_Port_address = "http://tlxkswmis5.lex.lexmark.com:8800/ws/SalesOrder:salesOrderWS";

    public java.lang.String getSalesOrder_salesOrderWS_PortAddress() {
        return SalesOrder_salesOrderWS_Port_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String SalesOrder_salesOrderWS_PortWSDDServiceName = "SalesOrder_salesOrderWS_Port";

    public java.lang.String getSalesOrder_salesOrderWS_PortWSDDServiceName() {
        return SalesOrder_salesOrderWS_PortWSDDServiceName;
    }

    public void setSalesOrder_salesOrderWS_PortWSDDServiceName(java.lang.String name) {
        SalesOrder_salesOrderWS_PortWSDDServiceName = name;
    }

    public com.lexmark.SiebelShared.source.updateServiceOrders.client.SalesOrderWS_PortType getSalesOrder_salesOrderWS_Port() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(SalesOrder_salesOrderWS_Port_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getSalesOrder_salesOrderWS_Port(endpoint);
    }

    public com.lexmark.SiebelShared.source.updateServiceOrders.client.SalesOrderWS_PortType getSalesOrder_salesOrderWS_Port(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.lexmark.SiebelShared.source.updateServiceOrders.client.SalesOrder_salesOrderWS_BinderStub _stub = new com.lexmark.SiebelShared.source.updateServiceOrders.client.SalesOrder_salesOrderWS_BinderStub(portAddress, this);
            _stub.setPortName(getSalesOrder_salesOrderWS_PortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setSalesOrder_salesOrderWS_PortEndpointAddress(java.lang.String address) {
        SalesOrder_salesOrderWS_Port_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.lexmark.SiebelShared.source.updateServiceOrders.client.SalesOrderWS_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                com.lexmark.SiebelShared.source.updateServiceOrders.client.SalesOrder_salesOrderWS_BinderStub _stub = new com.lexmark.SiebelShared.source.updateServiceOrders.client.SalesOrder_salesOrderWS_BinderStub(new java.net.URL(SalesOrder_salesOrderWS_Port_address), this);
                _stub.setPortName(getSalesOrder_salesOrderWS_PortWSDDServiceName());
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
        if ("SalesOrder_salesOrderWS_Port".equals(inputPortName)) {
            return getSalesOrder_salesOrderWS_Port();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/SalesOrder/salesOrderWS", "salesOrderWS");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/SalesOrder/salesOrderWS", "SalesOrder_salesOrderWS_Port"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("SalesOrder_salesOrderWS_Port".equals(portName)) {
            setSalesOrder_salesOrderWS_PortEndpointAddress(address);
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
