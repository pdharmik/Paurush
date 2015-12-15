/**
 * LXK_spcMPS_spcAuto_spcAccept_spcActivities_spcPartner_spcPortal_ServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.webServices.acceptActivityWS;

public class LXK_spcMPS_spcAuto_spcAccept_spcActivities_spcPartner_spcPortal_ServiceLocator extends org.apache.axis.client.Service implements com.lexmark.webServices.acceptActivityWS.LXK_spcMPS_spcAuto_spcAccept_spcActivities_spcPartner_spcPortal_Service {

    public LXK_spcMPS_spcAuto_spcAccept_spcActivities_spcPartner_spcPortal_ServiceLocator() {
    }


    public LXK_spcMPS_spcAuto_spcAccept_spcActivities_spcPartner_spcPortal_ServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public LXK_spcMPS_spcAuto_spcAccept_spcActivities_spcPartner_spcPortal_ServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for LXK_spcMPS_spcAuto_spcAccept_spcActivities_spcPartner_spcPortal
    private java.lang.String LXK_spcMPS_spcAuto_spcAccept_spcActivities_spcPartner_spcPortal_address = " https://siebelqa.lexmark.com/eai_enu/start.swe?SWEExtSource=WebService&SWEExtCmd=Execute";

    public java.lang.String getLXK_spcMPS_spcAuto_spcAccept_spcActivities_spcPartner_spcPortalAddress() {
        return LXK_spcMPS_spcAuto_spcAccept_spcActivities_spcPartner_spcPortal_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String LXK_spcMPS_spcAuto_spcAccept_spcActivities_spcPartner_spcPortalWSDDServiceName = "LXK_spcMPS_spcAuto_spcAccept_spcActivities_spcPartner_spcPortal";

    public java.lang.String getLXK_spcMPS_spcAuto_spcAccept_spcActivities_spcPartner_spcPortalWSDDServiceName() {
        return LXK_spcMPS_spcAuto_spcAccept_spcActivities_spcPartner_spcPortalWSDDServiceName;
    }

    public void setLXK_spcMPS_spcAuto_spcAccept_spcActivities_spcPartner_spcPortalWSDDServiceName(java.lang.String name) {
        LXK_spcMPS_spcAuto_spcAccept_spcActivities_spcPartner_spcPortalWSDDServiceName = name;
    }

    public com.lexmark.webServices.acceptActivityWS.LXK_spcMPS_spcAuto_spcAccept_spcActivities_spcPartner_spcPortal_PortType getLXK_spcMPS_spcAuto_spcAccept_spcActivities_spcPartner_spcPortal() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(LXK_spcMPS_spcAuto_spcAccept_spcActivities_spcPartner_spcPortal_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getLXK_spcMPS_spcAuto_spcAccept_spcActivities_spcPartner_spcPortal(endpoint);
    }

    public com.lexmark.webServices.acceptActivityWS.LXK_spcMPS_spcAuto_spcAccept_spcActivities_spcPartner_spcPortal_PortType getLXK_spcMPS_spcAuto_spcAccept_spcActivities_spcPartner_spcPortal(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.lexmark.webServices.acceptActivityWS.LXK_spcMPS_spcAuto_spcAccept_spcActivities_spcPartner_spcPortal_BindingStub _stub = new com.lexmark.webServices.acceptActivityWS.LXK_spcMPS_spcAuto_spcAccept_spcActivities_spcPartner_spcPortal_BindingStub(portAddress, this);
            _stub.setPortName(getLXK_spcMPS_spcAuto_spcAccept_spcActivities_spcPartner_spcPortalWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setLXK_spcMPS_spcAuto_spcAccept_spcActivities_spcPartner_spcPortalEndpointAddress(java.lang.String address) {
        LXK_spcMPS_spcAuto_spcAccept_spcActivities_spcPartner_spcPortal_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.lexmark.webServices.acceptActivityWS.LXK_spcMPS_spcAuto_spcAccept_spcActivities_spcPartner_spcPortal_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                com.lexmark.webServices.acceptActivityWS.LXK_spcMPS_spcAuto_spcAccept_spcActivities_spcPartner_spcPortal_BindingStub _stub = new com.lexmark.webServices.acceptActivityWS.LXK_spcMPS_spcAuto_spcAccept_spcActivities_spcPartner_spcPortal_BindingStub(new java.net.URL(LXK_spcMPS_spcAuto_spcAccept_spcActivities_spcPartner_spcPortal_address), this);
                _stub.setPortName(getLXK_spcMPS_spcAuto_spcAccept_spcActivities_spcPartner_spcPortalWSDDServiceName());
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
        if ("LXK_spcMPS_spcAuto_spcAccept_spcActivities_spcPartner_spcPortal".equals(inputPortName)) {
            return getLXK_spcMPS_spcAuto_spcAccept_spcActivities_spcPartner_spcPortal();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://siebel.com/CustomUI", "LXK_spcMPS_spcAuto_spcAccept_spcActivities_spcPartner_spcPortal");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://siebel.com/CustomUI", "LXK_spcMPS_spcAuto_spcAccept_spcActivities_spcPartner_spcPortal"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("LXK_spcMPS_spcAuto_spcAccept_spcActivities_spcPartner_spcPortal".equals(portName)) {
            setLXK_spcMPS_spcAuto_spcAccept_spcActivities_spcPartner_spcPortalEndpointAddress(address);
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
