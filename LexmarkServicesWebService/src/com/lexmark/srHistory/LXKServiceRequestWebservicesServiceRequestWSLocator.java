/**
 * LXKServiceRequestWebservicesServiceRequestWSLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.srHistory;

public class LXKServiceRequestWebservicesServiceRequestWSLocator extends org.apache.axis.client.Service implements com.lexmark.srHistory.LXKServiceRequestWebservicesServiceRequestWS {

    public LXKServiceRequestWebservicesServiceRequestWSLocator() {
    }


    public LXKServiceRequestWebservicesServiceRequestWSLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public LXKServiceRequestWebservicesServiceRequestWSLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for LXKServiceRequest_webservices_serviceRequestWS_Port
    private java.lang.String LXKServiceRequest_webservices_serviceRequestWS_Port_address = "http://wmamzdev.lexmark.com:8000/ws/LXKServiceRequest.webservices.serviceRequestWS/LXKServiceRequest_webservices_serviceRequestWS_Port";

    public java.lang.String getLXKServiceRequest_webservices_serviceRequestWS_PortAddress() {
        return LXKServiceRequest_webservices_serviceRequestWS_Port_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String LXKServiceRequest_webservices_serviceRequestWS_PortWSDDServiceName = "LXKServiceRequest_webservices_serviceRequestWS_Port";

    public java.lang.String getLXKServiceRequest_webservices_serviceRequestWS_PortWSDDServiceName() {
        return LXKServiceRequest_webservices_serviceRequestWS_PortWSDDServiceName;
    }

    public void setLXKServiceRequest_webservices_serviceRequestWS_PortWSDDServiceName(java.lang.String name) {
        LXKServiceRequest_webservices_serviceRequestWS_PortWSDDServiceName = name;
    }

    public com.lexmark.srHistory.ServiceRequestWS_PortType getLXKServiceRequest_webservices_serviceRequestWS_Port() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(LXKServiceRequest_webservices_serviceRequestWS_Port_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getLXKServiceRequest_webservices_serviceRequestWS_Port(endpoint);
    }

    public com.lexmark.srHistory.ServiceRequestWS_PortType getLXKServiceRequest_webservices_serviceRequestWS_Port(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.lexmark.srHistory.LXKServiceRequest_webservices_serviceRequestWS_BinderStub _stub = new com.lexmark.srHistory.LXKServiceRequest_webservices_serviceRequestWS_BinderStub(portAddress, this);
            _stub.setPortName(getLXKServiceRequest_webservices_serviceRequestWS_PortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setLXKServiceRequest_webservices_serviceRequestWS_PortEndpointAddress(java.lang.String address) {
        LXKServiceRequest_webservices_serviceRequestWS_Port_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.lexmark.srHistory.ServiceRequestWS_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                com.lexmark.srHistory.LXKServiceRequest_webservices_serviceRequestWS_BinderStub _stub = new com.lexmark.srHistory.LXKServiceRequest_webservices_serviceRequestWS_BinderStub(new java.net.URL(LXKServiceRequest_webservices_serviceRequestWS_Port_address), this);
                _stub.setPortName(getLXKServiceRequest_webservices_serviceRequestWS_PortWSDDServiceName());
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
        if ("LXKServiceRequest_webservices_serviceRequestWS_Port".equals(inputPortName)) {
            return getLXKServiceRequest_webservices_serviceRequestWS_Port();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://wmamzdev.lexmark.com/LXKServiceRequest.webservices:serviceRequestWS", "LXKServiceRequest.webservices.serviceRequestWS");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://wmamzdev.lexmark.com/LXKServiceRequest.webservices:serviceRequestWS", "LXKServiceRequest_webservices_serviceRequestWS_Port"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("LXKServiceRequest_webservices_serviceRequestWS_Port".equals(portName)) {
            setLXKServiceRequest_webservices_serviceRequestWS_PortEndpointAddress(address);
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
