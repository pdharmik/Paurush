/**
 * DataServices_ServerLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.businessobjects.www;

public class DataServices_ServerLocator extends org.apache.axis.client.Service implements com.businessobjects.www.DataServices_Server {

    public DataServices_ServerLocator() {
    }


    public DataServices_ServerLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public DataServices_ServerLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for Batch_Job_Admin
    private java.lang.String Batch_Job_Admin_address = "http://dlexwbods001:8080/DataServices/servlet/webservices?ver=2.1";

    public java.lang.String getBatch_Job_AdminAddress() {
        return Batch_Job_Admin_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String Batch_Job_AdminWSDDServiceName = "Batch_Job_Admin";

    public java.lang.String getBatch_Job_AdminWSDDServiceName() {
        return Batch_Job_AdminWSDDServiceName;
    }

    public void setBatch_Job_AdminWSDDServiceName(java.lang.String name) {
        Batch_Job_AdminWSDDServiceName = name;
    }

    public com.businessobjects.www.Batch_Job_Admin getBatch_Job_Admin() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(Batch_Job_Admin_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getBatch_Job_Admin(endpoint);
    }

    public com.businessobjects.www.Batch_Job_Admin getBatch_Job_Admin(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.businessobjects.www.Batch_Job_AdminStub _stub = new com.businessobjects.www.Batch_Job_AdminStub(portAddress, this);
            _stub.setPortName(getBatch_Job_AdminWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setBatch_Job_AdminEndpointAddress(java.lang.String address) {
        Batch_Job_Admin_address = address;
    }


    // Use to get a proxy class for Connection_Operations
    private java.lang.String Connection_Operations_address = "http://dlexwbods001:8080/DataServices/servlet/webservices?ver=2.1";

    public java.lang.String getConnection_OperationsAddress() {
        return Connection_Operations_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String Connection_OperationsWSDDServiceName = "Connection_Operations";

    public java.lang.String getConnection_OperationsWSDDServiceName() {
        return Connection_OperationsWSDDServiceName;
    }

    public void setConnection_OperationsWSDDServiceName(java.lang.String name) {
        Connection_OperationsWSDDServiceName = name;
    }

    public com.businessobjects.www.Connection_Operations getConnection_Operations() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(Connection_Operations_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getConnection_Operations(endpoint);
    }

    public com.businessobjects.www.Connection_Operations getConnection_Operations(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.businessobjects.www.Connection_OperationsStub _stub = new com.businessobjects.www.Connection_OperationsStub(portAddress, this);
            _stub.setPortName(getConnection_OperationsWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setConnection_OperationsEndpointAddress(java.lang.String address) {
        Connection_Operations_address = address;
    }


    // Use to get a proxy class for Realtime_Service_Admin
    private java.lang.String Realtime_Service_Admin_address = "http://dlexwbods001:8080/DataServices/servlet/webservices?ver=2.1";

    public java.lang.String getRealtime_Service_AdminAddress() {
        return Realtime_Service_Admin_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String Realtime_Service_AdminWSDDServiceName = "Realtime_Service_Admin";

    public java.lang.String getRealtime_Service_AdminWSDDServiceName() {
        return Realtime_Service_AdminWSDDServiceName;
    }

    public void setRealtime_Service_AdminWSDDServiceName(java.lang.String name) {
        Realtime_Service_AdminWSDDServiceName = name;
    }

    public com.businessobjects.www.Realtime_Service_Admin getRealtime_Service_Admin() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(Realtime_Service_Admin_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getRealtime_Service_Admin(endpoint);
    }

    public com.businessobjects.www.Realtime_Service_Admin getRealtime_Service_Admin(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.businessobjects.www.Realtime_Service_AdminStub _stub = new com.businessobjects.www.Realtime_Service_AdminStub(portAddress, this);
            _stub.setPortName(getRealtime_Service_AdminWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setRealtime_Service_AdminEndpointAddress(java.lang.String address) {
        Realtime_Service_Admin_address = address;
    }


    // Use to get a proxy class for Repo_Operations
    private java.lang.String Repo_Operations_address = "http://dlexwbods001:8080/DataServices/servlet/webservices?ver=2.1";

    public java.lang.String getRepo_OperationsAddress() {
        return Repo_Operations_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String Repo_OperationsWSDDServiceName = "Repo_Operations";

    public java.lang.String getRepo_OperationsWSDDServiceName() {
        return Repo_OperationsWSDDServiceName;
    }

    public void setRepo_OperationsWSDDServiceName(java.lang.String name) {
        Repo_OperationsWSDDServiceName = name;
    }

    public com.businessobjects.www.Repo_Operations getRepo_Operations() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(Repo_Operations_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getRepo_Operations(endpoint);
    }

    public com.businessobjects.www.Repo_Operations getRepo_Operations(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.businessobjects.www.Repo_OperationsStub _stub = new com.businessobjects.www.Repo_OperationsStub(portAddress, this);
            _stub.setPortName(getRepo_OperationsWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setRepo_OperationsEndpointAddress(java.lang.String address) {
        Repo_Operations_address = address;
    }


    // Use to get a proxy class for RealTime_Services
    private java.lang.String RealTime_Services_address = "http://dlexwbods001:8080/DataServices/servlet/webservices?ver=2.1";

    public java.lang.String getRealTime_ServicesAddress() {
        return RealTime_Services_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String RealTime_ServicesWSDDServiceName = "Real-time_Services";

    public java.lang.String getRealTime_ServicesWSDDServiceName() {
        return RealTime_ServicesWSDDServiceName;
    }

    public void setRealTime_ServicesWSDDServiceName(java.lang.String name) {
        RealTime_ServicesWSDDServiceName = name;
    }

    public com.businessobjects.www.RealTime_Services getRealTime_Services() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(RealTime_Services_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getRealTime_Services(endpoint);
    }

    public com.businessobjects.www.RealTime_Services getRealTime_Services(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.businessobjects.www.RealTime_ServicesStub _stub = new com.businessobjects.www.RealTime_ServicesStub(portAddress, this);
            _stub.setPortName(getRealTime_ServicesWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setRealTime_ServicesEndpointAddress(java.lang.String address) {
        RealTime_Services_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.businessobjects.www.Batch_Job_Admin.class.isAssignableFrom(serviceEndpointInterface)) {
                com.businessobjects.www.Batch_Job_AdminStub _stub = new com.businessobjects.www.Batch_Job_AdminStub(new java.net.URL(Batch_Job_Admin_address), this);
                _stub.setPortName(getBatch_Job_AdminWSDDServiceName());
                return _stub;
            }
            if (com.businessobjects.www.Connection_Operations.class.isAssignableFrom(serviceEndpointInterface)) {
                com.businessobjects.www.Connection_OperationsStub _stub = new com.businessobjects.www.Connection_OperationsStub(new java.net.URL(Connection_Operations_address), this);
                _stub.setPortName(getConnection_OperationsWSDDServiceName());
                return _stub;
            }
            if (com.businessobjects.www.Realtime_Service_Admin.class.isAssignableFrom(serviceEndpointInterface)) {
                com.businessobjects.www.Realtime_Service_AdminStub _stub = new com.businessobjects.www.Realtime_Service_AdminStub(new java.net.URL(Realtime_Service_Admin_address), this);
                _stub.setPortName(getRealtime_Service_AdminWSDDServiceName());
                return _stub;
            }
            if (com.businessobjects.www.Repo_Operations.class.isAssignableFrom(serviceEndpointInterface)) {
                com.businessobjects.www.Repo_OperationsStub _stub = new com.businessobjects.www.Repo_OperationsStub(new java.net.URL(Repo_Operations_address), this);
                _stub.setPortName(getRepo_OperationsWSDDServiceName());
                return _stub;
            }
            if (com.businessobjects.www.RealTime_Services.class.isAssignableFrom(serviceEndpointInterface)) {
                com.businessobjects.www.RealTime_ServicesStub _stub = new com.businessobjects.www.RealTime_ServicesStub(new java.net.URL(RealTime_Services_address), this);
                _stub.setPortName(getRealTime_ServicesWSDDServiceName());
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
        if ("Batch_Job_Admin".equals(inputPortName)) {
            return getBatch_Job_Admin();
        }
        else if ("Connection_Operations".equals(inputPortName)) {
            return getConnection_Operations();
        }
        else if ("Realtime_Service_Admin".equals(inputPortName)) {
            return getRealtime_Service_Admin();
        }
        else if ("Repo_Operations".equals(inputPortName)) {
            return getRepo_Operations();
        }
        else if ("Real-time_Services".equals(inputPortName)) {
            return getRealTime_Services();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://www.businessobjects.com", "DataServices_Server");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://www.businessobjects.com", "Batch_Job_Admin"));
            ports.add(new javax.xml.namespace.QName("http://www.businessobjects.com", "Connection_Operations"));
            ports.add(new javax.xml.namespace.QName("http://www.businessobjects.com", "Realtime_Service_Admin"));
            ports.add(new javax.xml.namespace.QName("http://www.businessobjects.com", "Repo_Operations"));
            ports.add(new javax.xml.namespace.QName("http://www.businessobjects.com", "Real-time_Services"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("Batch_Job_Admin".equals(portName)) {
            setBatch_Job_AdminEndpointAddress(address);
        }
        else 
if ("Connection_Operations".equals(portName)) {
            setConnection_OperationsEndpointAddress(address);
        }
        else 
if ("Realtime_Service_Admin".equals(portName)) {
            setRealtime_Service_AdminEndpointAddress(address);
        }
        else 
if ("Repo_Operations".equals(portName)) {
            setRepo_OperationsEndpointAddress(address);
        }
        else 
if ("RealTime_Services".equals(portName)) {
            setRealTime_ServicesEndpointAddress(address);
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
