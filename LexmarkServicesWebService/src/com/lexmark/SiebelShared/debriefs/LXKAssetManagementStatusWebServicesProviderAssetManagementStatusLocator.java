/**
 * LXKAssetManagementStatusWebServicesProviderAssetManagementStatusLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.SiebelShared.debriefs;

public class LXKAssetManagementStatusWebServicesProviderAssetManagementStatusLocator extends org.apache.axis.client.Service implements com.lexmark.SiebelShared.debriefs.LXKAssetManagementStatusWebServicesProviderAssetManagementStatus {

    public LXKAssetManagementStatusWebServicesProviderAssetManagementStatusLocator() {
    }


    public LXKAssetManagementStatusWebServicesProviderAssetManagementStatusLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public LXKAssetManagementStatusWebServicesProviderAssetManagementStatusLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for LXKAssetManagementStatus_webServices_provider_AssetManagementStatus_Port
    private java.lang.String LXKAssetManagementStatus_webServices_provider_AssetManagementStatus_Port_address = "http://dashrwmis002.lex.lexmark.com:8700/ws/LXKAssetManagementStatus.webServices.provider.AssetManagementStatus/LXKAssetManagementStatus_webServices_provider_AssetManagementStatus_Port";

    public java.lang.String getLXKAssetManagementStatus_webServices_provider_AssetManagementStatus_PortAddress() {
        return LXKAssetManagementStatus_webServices_provider_AssetManagementStatus_Port_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String LXKAssetManagementStatus_webServices_provider_AssetManagementStatus_PortWSDDServiceName = "LXKAssetManagementStatus_webServices_provider_AssetManagementStatus_Port";

    public java.lang.String getLXKAssetManagementStatus_webServices_provider_AssetManagementStatus_PortWSDDServiceName() {
        return LXKAssetManagementStatus_webServices_provider_AssetManagementStatus_PortWSDDServiceName;
    }

    public void setLXKAssetManagementStatus_webServices_provider_AssetManagementStatus_PortWSDDServiceName(java.lang.String name) {
        LXKAssetManagementStatus_webServices_provider_AssetManagementStatus_PortWSDDServiceName = name;
    }

    public com.lexmark.SiebelShared.debriefs.AssetManagementStatus_PortType getLXKAssetManagementStatus_webServices_provider_AssetManagementStatus_Port() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(LXKAssetManagementStatus_webServices_provider_AssetManagementStatus_Port_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getLXKAssetManagementStatus_webServices_provider_AssetManagementStatus_Port(endpoint);
    }

    public com.lexmark.SiebelShared.debriefs.AssetManagementStatus_PortType getLXKAssetManagementStatus_webServices_provider_AssetManagementStatus_Port(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.lexmark.SiebelShared.debriefs.LXKAssetManagementStatus_webServices_provider_AssetManagementStatus_BinderStub _stub = new com.lexmark.SiebelShared.debriefs.LXKAssetManagementStatus_webServices_provider_AssetManagementStatus_BinderStub(portAddress, this);
            _stub.setPortName(getLXKAssetManagementStatus_webServices_provider_AssetManagementStatus_PortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setLXKAssetManagementStatus_webServices_provider_AssetManagementStatus_PortEndpointAddress(java.lang.String address) {
        LXKAssetManagementStatus_webServices_provider_AssetManagementStatus_Port_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.lexmark.SiebelShared.debriefs.AssetManagementStatus_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                com.lexmark.SiebelShared.debriefs.LXKAssetManagementStatus_webServices_provider_AssetManagementStatus_BinderStub _stub = new com.lexmark.SiebelShared.debriefs.LXKAssetManagementStatus_webServices_provider_AssetManagementStatus_BinderStub(new java.net.URL(LXKAssetManagementStatus_webServices_provider_AssetManagementStatus_Port_address), this);
                _stub.setPortName(getLXKAssetManagementStatus_webServices_provider_AssetManagementStatus_PortWSDDServiceName());
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
        if ("LXKAssetManagementStatus_webServices_provider_AssetManagementStatus_Port".equals(inputPortName)) {
            return getLXKAssetManagementStatus_webServices_provider_AssetManagementStatus_Port();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://dashrwmis002.lex.lexmark.com/LXKAssetManagementStatus.webServices.provider:AssetManagementStatus", "LXKAssetManagementStatus.webServices.provider.AssetManagementStatus");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://dashrwmis002.lex.lexmark.com/LXKAssetManagementStatus.webServices.provider:AssetManagementStatus", "LXKAssetManagementStatus_webServices_provider_AssetManagementStatus_Port"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("LXKAssetManagementStatus_webServices_provider_AssetManagementStatus_Port".equals(portName)) {
            setLXKAssetManagementStatus_webServices_provider_AssetManagementStatus_PortEndpointAddress(address);
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
