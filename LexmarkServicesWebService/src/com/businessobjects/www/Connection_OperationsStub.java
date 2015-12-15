/**
 * Connection_OperationsStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.businessobjects.www;

public class Connection_OperationsStub extends org.apache.axis.client.Stub implements com.businessobjects.www.Connection_Operations {
    private java.util.Vector cachedSerClasses = new java.util.Vector();
    private java.util.Vector cachedSerQNames = new java.util.Vector();
    private java.util.Vector cachedSerFactories = new java.util.Vector();
    private java.util.Vector cachedDeserFactories = new java.util.Vector();

    static org.apache.axis.description.OperationDesc [] _operations;

    static {
        _operations = new org.apache.axis.description.OperationDesc[3];
        _initOperationDesc1();
    }

    private static void _initOperationDesc1(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("Logout");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.businessobjects.com/DataServices/ServerX.xsd", "Logout_Input"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.businessobjects.com/DataServices/ServerX.xsd", ">Logout_Input"), com.businessobjects.www.DataServices.ServerX_xsd.Logout_Input.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.businessobjects.com/DataServices/ServerX.xsd", ">responseStatus"));
        oper.setReturnClass(com.businessobjects.www.DataServices.ServerX_xsd.ResponseStatus.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.businessobjects.com/DataServices/ServerX.xsd", "responseStatus"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[0] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("Ping");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.businessobjects.com/DataServices/ServerX.xsd", "Ping_Input"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.businessobjects.com/DataServices/ServerX.xsd", ">Ping_Input"), com.businessobjects.www.DataServices.ServerX_xsd.Ping_Input.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.businessobjects.com/DataServices/ServerX.xsd", ">pingVersion"));
        oper.setReturnClass(com.businessobjects.www.DataServices.ServerX_xsd.PingVersion.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.businessobjects.com/DataServices/ServerX.xsd", "pingVersion"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[1] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("Logon");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.businessobjects.com/DataServices/ServerX.xsd", "LogonRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.businessobjects.com/DataServices/ServerX.xsd", ">LogonRequest"), com.businessobjects.www.DataServices.ServerX_xsd.LogonRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.businessobjects.com/DataServices/ServerX.xsd", ">session"));
        oper.setReturnClass(com.businessobjects.www.DataServices.ServerX_xsd.Session.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.businessobjects.com/DataServices/ServerX.xsd", "session"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[2] = oper;

    }

    public Connection_OperationsStub() throws org.apache.axis.AxisFault {
         this(null);
    }

    public Connection_OperationsStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
         this(service);
         super.cachedEndpoint = endpointURL;
    }

    public Connection_OperationsStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
        if (service == null) {
            super.service = new org.apache.axis.client.Service();
        } else {
            super.service = service;
        }
        ((org.apache.axis.client.Service)super.service).setTypeMappingVersion("1.2");
            java.lang.Class cls;
            javax.xml.namespace.QName qName;
            javax.xml.namespace.QName qName2;
            java.lang.Class beansf = org.apache.axis.encoding.ser.BeanSerializerFactory.class;
            java.lang.Class beandf = org.apache.axis.encoding.ser.BeanDeserializerFactory.class;
            java.lang.Class enumsf = org.apache.axis.encoding.ser.EnumSerializerFactory.class;
            java.lang.Class enumdf = org.apache.axis.encoding.ser.EnumDeserializerFactory.class;
            java.lang.Class arraysf = org.apache.axis.encoding.ser.ArraySerializerFactory.class;
            java.lang.Class arraydf = org.apache.axis.encoding.ser.ArrayDeserializerFactory.class;
            java.lang.Class simplesf = org.apache.axis.encoding.ser.SimpleSerializerFactory.class;
            java.lang.Class simpledf = org.apache.axis.encoding.ser.SimpleDeserializerFactory.class;
            java.lang.Class simplelistsf = org.apache.axis.encoding.ser.SimpleListSerializerFactory.class;
            java.lang.Class simplelistdf = org.apache.axis.encoding.ser.SimpleListDeserializerFactory.class;
            qName = new javax.xml.namespace.QName("http://www.businessobjects.com/DataServices/ServerX.xsd", ">>LogonRequest>cms_authentication");
            cachedSerQNames.add(qName);
            cls = com.businessobjects.www.DataServices.ServerX_xsd.LogonRequestCms_authentication.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("http://www.businessobjects.com/DataServices/ServerX.xsd", ">LogonRequest");
            cachedSerQNames.add(qName);
            cls = com.businessobjects.www.DataServices.ServerX_xsd.LogonRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.businessobjects.com/DataServices/ServerX.xsd", ">Logout_Input");
            cachedSerQNames.add(qName);
            cls = com.businessobjects.www.DataServices.ServerX_xsd.Logout_Input.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.businessobjects.com/DataServices/ServerX.xsd", ">Ping_Input");
            cachedSerQNames.add(qName);
            cls = com.businessobjects.www.DataServices.ServerX_xsd.Ping_Input.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.businessobjects.com/DataServices/ServerX.xsd", ">pingVersion");
            cachedSerQNames.add(qName);
            cls = com.businessobjects.www.DataServices.ServerX_xsd.PingVersion.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.businessobjects.com/DataServices/ServerX.xsd", ">responseStatus");
            cachedSerQNames.add(qName);
            cls = com.businessobjects.www.DataServices.ServerX_xsd.ResponseStatus.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.businessobjects.com/DataServices/ServerX.xsd", ">session");
            cachedSerQNames.add(qName);
            cls = com.businessobjects.www.DataServices.ServerX_xsd.Session.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

    }

    protected org.apache.axis.client.Call createCall() throws java.rmi.RemoteException {
        try {
            org.apache.axis.client.Call _call = super._createCall();
            if (super.maintainSessionSet) {
                _call.setMaintainSession(super.maintainSession);
            }
            if (super.cachedUsername != null) {
                _call.setUsername(super.cachedUsername);
            }
            if (super.cachedPassword != null) {
                _call.setPassword(super.cachedPassword);
            }
            if (super.cachedEndpoint != null) {
                _call.setTargetEndpointAddress(super.cachedEndpoint);
            }
            if (super.cachedTimeout != null) {
                _call.setTimeout(super.cachedTimeout);
            }
            if (super.cachedPortName != null) {
                _call.setPortName(super.cachedPortName);
            }
            java.util.Enumeration keys = super.cachedProperties.keys();
            while (keys.hasMoreElements()) {
                java.lang.String key = (java.lang.String) keys.nextElement();
                _call.setProperty(key, super.cachedProperties.get(key));
            }
            // All the type mapping information is registered
            // when the first call is made.
            // The type mapping information is actually registered in
            // the TypeMappingRegistry of the service, which
            // is the reason why registration is only needed for the first call.
            synchronized (this) {
                if (firstCall()) {
                    // must set encoding style before registering serializers
                    _call.setEncodingStyle(null);
                    for (int i = 0; i < cachedSerFactories.size(); ++i) {
                        java.lang.Class cls = (java.lang.Class) cachedSerClasses.get(i);
                        javax.xml.namespace.QName qName =
                                (javax.xml.namespace.QName) cachedSerQNames.get(i);
                        java.lang.Object x = cachedSerFactories.get(i);
                        if (x instanceof Class) {
                            java.lang.Class sf = (java.lang.Class)
                                 cachedSerFactories.get(i);
                            java.lang.Class df = (java.lang.Class)
                                 cachedDeserFactories.get(i);
                            _call.registerTypeMapping(cls, qName, sf, df, false);
                        }
                        else if (x instanceof javax.xml.rpc.encoding.SerializerFactory) {
                            org.apache.axis.encoding.SerializerFactory sf = (org.apache.axis.encoding.SerializerFactory)
                                 cachedSerFactories.get(i);
                            org.apache.axis.encoding.DeserializerFactory df = (org.apache.axis.encoding.DeserializerFactory)
                                 cachedDeserFactories.get(i);
                            _call.registerTypeMapping(cls, qName, sf, df, false);
                        }
                    }
                }
            }
            return _call;
        }
        catch (java.lang.Throwable _t) {
            throw new org.apache.axis.AxisFault("Failure trying to get the Call object", _t);
        }
    }

    public com.businessobjects.www.DataServices.ServerX_xsd.ResponseStatus logout(com.businessobjects.www.DataServices.ServerX_xsd.Logout_Input logout_Input) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[0]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("function=Logout");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "Logout"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {logout_Input});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.businessobjects.www.DataServices.ServerX_xsd.ResponseStatus) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.businessobjects.www.DataServices.ServerX_xsd.ResponseStatus) org.apache.axis.utils.JavaUtils.convert(_resp, com.businessobjects.www.DataServices.ServerX_xsd.ResponseStatus.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.businessobjects.www.DataServices.ServerX_xsd.PingVersion ping(com.businessobjects.www.DataServices.ServerX_xsd.Ping_Input ping_Input) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[1]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("function=Ping");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "Ping"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {ping_Input});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.businessobjects.www.DataServices.ServerX_xsd.PingVersion) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.businessobjects.www.DataServices.ServerX_xsd.PingVersion) org.apache.axis.utils.JavaUtils.convert(_resp, com.businessobjects.www.DataServices.ServerX_xsd.PingVersion.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.businessobjects.www.DataServices.ServerX_xsd.Session logon(com.businessobjects.www.DataServices.ServerX_xsd.LogonRequest logonRequest) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[2]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("function=Logon");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "Logon"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {logonRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.businessobjects.www.DataServices.ServerX_xsd.Session) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.businessobjects.www.DataServices.ServerX_xsd.Session) org.apache.axis.utils.JavaUtils.convert(_resp, com.businessobjects.www.DataServices.ServerX_xsd.Session.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

}
