/**
 * ServiceRequestWSLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.SiebelShared.lgs.om.createServiceRequest.client;

public class ServiceRequestWSLocator extends org.apache.axis.client.Service implements com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.ServiceRequestWS {

    public ServiceRequestWSLocator() {
    }


    public ServiceRequestWSLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public ServiceRequestWSLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for ServiceRequest_serviceRequestWS_Port
    private java.lang.String ServiceRequest_serviceRequestWS_Port_address = "http://dlexrwmis002.lex.lexmark.com:8700/ws/ServiceRequest:serviceRequestWS/ServiceRequest_serviceRequestWS_Port";

    public java.lang.String getServiceRequest_serviceRequestWS_PortAddress() {
        return ServiceRequest_serviceRequestWS_Port_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String ServiceRequest_serviceRequestWS_PortWSDDServiceName = "ServiceRequest_serviceRequestWS_Port";

    public java.lang.String getServiceRequest_serviceRequestWS_PortWSDDServiceName() {
        return ServiceRequest_serviceRequestWS_PortWSDDServiceName;
    }

    public void setServiceRequest_serviceRequestWS_PortWSDDServiceName(java.lang.String name) {
        ServiceRequest_serviceRequestWS_PortWSDDServiceName = name;
    }

    public com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.ServiceRequestWS_PortType getServiceRequest_serviceRequestWS_Port() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(ServiceRequest_serviceRequestWS_Port_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getServiceRequest_serviceRequestWS_Port(endpoint);
    }

    public com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.ServiceRequestWS_PortType getServiceRequest_serviceRequestWS_Port(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.ServiceRequest_serviceRequestWS_BinderStub _stub = new com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.ServiceRequest_serviceRequestWS_BinderStub(portAddress, this);
            _stub.setPortName(getServiceRequest_serviceRequestWS_PortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setServiceRequest_serviceRequestWS_PortEndpointAddress(java.lang.String address) {
        ServiceRequest_serviceRequestWS_Port_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.ServiceRequestWS_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.ServiceRequest_serviceRequestWS_BinderStub _stub = new com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.ServiceRequest_serviceRequestWS_BinderStub(new java.net.URL(ServiceRequest_serviceRequestWS_Port_address), this);
                _stub.setPortName(getServiceRequest_serviceRequestWS_PortWSDDServiceName());
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
        if ("ServiceRequest_serviceRequestWS_Port".equals(inputPortName)) {
            return getServiceRequest_serviceRequestWS_Port();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "serviceRequestWS");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "ServiceRequest_serviceRequestWS_Port"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("ServiceRequest_serviceRequestWS_Port".equals(portName)) {
            setServiceRequest_serviceRequestWS_PortEndpointAddress(address);
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
