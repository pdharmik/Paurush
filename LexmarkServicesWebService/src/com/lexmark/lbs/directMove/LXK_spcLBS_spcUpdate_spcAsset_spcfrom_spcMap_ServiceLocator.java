/**
 * LXK_spcLBS_spcUpdate_spcAsset_spcfrom_spcMap_ServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.lbs.directMove;

public class LXK_spcLBS_spcUpdate_spcAsset_spcfrom_spcMap_ServiceLocator extends org.apache.axis.client.Service implements com.lexmark.lbs.directMove.LXK_spcLBS_spcUpdate_spcAsset_spcfrom_spcMap_Service {

    public LXK_spcLBS_spcUpdate_spcAsset_spcfrom_spcMap_ServiceLocator() {
    }


    public LXK_spcLBS_spcUpdate_spcAsset_spcfrom_spcMap_ServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public LXK_spcLBS_spcUpdate_spcAsset_spcfrom_spcMap_ServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for LXK_spcLBS_spcUpdate_spcAsset_spcfrom_spcMap
    private java.lang.String LXK_spcLBS_spcUpdate_spcAsset_spcfrom_spcMap_address = " https://siebelqa.lexmark.com/eai_enu/start.swe?SWEExtSource=WebService&SWEExtCmd=Execute";

    public java.lang.String getLXK_spcLBS_spcUpdate_spcAsset_spcfrom_spcMapAddress() {
        return LXK_spcLBS_spcUpdate_spcAsset_spcfrom_spcMap_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String LXK_spcLBS_spcUpdate_spcAsset_spcfrom_spcMapWSDDServiceName = "LXK_spcLBS_spcUpdate_spcAsset_spcfrom_spcMap";

    public java.lang.String getLXK_spcLBS_spcUpdate_spcAsset_spcfrom_spcMapWSDDServiceName() {
        return LXK_spcLBS_spcUpdate_spcAsset_spcfrom_spcMapWSDDServiceName;
    }

    public void setLXK_spcLBS_spcUpdate_spcAsset_spcfrom_spcMapWSDDServiceName(java.lang.String name) {
        LXK_spcLBS_spcUpdate_spcAsset_spcfrom_spcMapWSDDServiceName = name;
    }

    public com.lexmark.lbs.directMove.LXK_spcLBS_spcUpdate_spcAsset_spcfrom_spcMap_PortType getLXK_spcLBS_spcUpdate_spcAsset_spcfrom_spcMap() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(LXK_spcLBS_spcUpdate_spcAsset_spcfrom_spcMap_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getLXK_spcLBS_spcUpdate_spcAsset_spcfrom_spcMap(endpoint);
    }

    public com.lexmark.lbs.directMove.LXK_spcLBS_spcUpdate_spcAsset_spcfrom_spcMap_PortType getLXK_spcLBS_spcUpdate_spcAsset_spcfrom_spcMap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.lexmark.lbs.directMove.LXK_spcLBS_spcUpdate_spcAsset_spcfrom_spcMap_BindingStub _stub = new com.lexmark.lbs.directMove.LXK_spcLBS_spcUpdate_spcAsset_spcfrom_spcMap_BindingStub(portAddress, this);
            _stub.setPortName(getLXK_spcLBS_spcUpdate_spcAsset_spcfrom_spcMapWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setLXK_spcLBS_spcUpdate_spcAsset_spcfrom_spcMapEndpointAddress(java.lang.String address) {
        LXK_spcLBS_spcUpdate_spcAsset_spcfrom_spcMap_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.lexmark.lbs.directMove.LXK_spcLBS_spcUpdate_spcAsset_spcfrom_spcMap_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                com.lexmark.lbs.directMove.LXK_spcLBS_spcUpdate_spcAsset_spcfrom_spcMap_BindingStub _stub = new com.lexmark.lbs.directMove.LXK_spcLBS_spcUpdate_spcAsset_spcfrom_spcMap_BindingStub(new java.net.URL(LXK_spcLBS_spcUpdate_spcAsset_spcfrom_spcMap_address), this);
                _stub.setPortName(getLXK_spcLBS_spcUpdate_spcAsset_spcfrom_spcMapWSDDServiceName());
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
        if ("LXK_spcLBS_spcUpdate_spcAsset_spcfrom_spcMap".equals(inputPortName)) {
            return getLXK_spcLBS_spcUpdate_spcAsset_spcfrom_spcMap();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://siebel.com/CustomUI", "LXK_spcLBS_spcUpdate_spcAsset_spcfrom_spcMap");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://siebel.com/CustomUI", "LXK_spcLBS_spcUpdate_spcAsset_spcfrom_spcMap"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("LXK_spcLBS_spcUpdate_spcAsset_spcfrom_spcMap".equals(portName)) {
            setLXK_spcLBS_spcUpdate_spcAsset_spcfrom_spcMapEndpointAddress(address);
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
