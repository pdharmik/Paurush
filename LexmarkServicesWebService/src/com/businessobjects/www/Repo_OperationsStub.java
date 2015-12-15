/**
 * Repo_OperationsStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.businessobjects.www;

public class Repo_OperationsStub extends org.apache.axis.client.Stub implements com.businessobjects.www.Repo_Operations {
    private java.util.Vector cachedSerClasses = new java.util.Vector();
    private java.util.Vector cachedSerQNames = new java.util.Vector();
    private java.util.Vector cachedSerFactories = new java.util.Vector();
    private java.util.Vector cachedDeserFactories = new java.util.Vector();

    static org.apache.axis.description.OperationDesc [] _operations;

    static {
        _operations = new org.apache.axis.description.OperationDesc[5];
        _initOperationDesc1();
    }

    private static void _initOperationDesc1(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("Import_Repo_Object");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.businessobjects.com/DataServices/ServerX.xsd", "ImportObjectDefinitionRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.businessobjects.com/DataServices/ServerX.xsd", ">ImportObjectDefinitionRequest"), com.businessobjects.www.DataServices.ServerX_xsd.ImportObjectDefinitionRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.businessobjects.com/DataServices/ServerX.xsd", ">RepoOperationResponse"));
        oper.setReturnClass(com.businessobjects.www.DataServices.ServerX_xsd.RepoOperationResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.businessobjects.com/DataServices/ServerX.xsd", "RepoOperationResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[0] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("Validate_Repo_Object");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.businessobjects.com/DataServices/ServerX.xsd", "ValidateObjectDefinitionRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.businessobjects.com/DataServices/ServerX.xsd", ">ValidateObjectDefinitionRequest"), com.businessobjects.www.DataServices.ServerX_xsd.ValidateObjectDefinitionRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.businessobjects.com/DataServices/ServerX.xsd", ">RepoOperationResponse"));
        oper.setReturnClass(com.businessobjects.www.DataServices.ServerX_xsd.RepoOperationResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.businessobjects.com/DataServices/ServerX.xsd", "RepoOperationResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[1] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("Delete_Repo_Objects");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.businessobjects.com/DataServices/ServerX.xsd", "DeleteRepoObjectsRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.businessobjects.com/DataServices/ServerX.xsd", ">DeleteRepoObjectsRequest"), com.businessobjects.www.DataServices.ServerX_xsd.DeleteRepoObjectsRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.businessobjects.com/DataServices/ServerX.xsd", ">RepoOperationResponse"));
        oper.setReturnClass(com.businessobjects.www.DataServices.ServerX_xsd.RepoOperationResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.businessobjects.com/DataServices/ServerX.xsd", "RepoOperationResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[2] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("Compact_Repo");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.businessobjects.com/DataServices/ServerX.xsd", "CompactRepoRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.businessobjects.com/DataServices/ServerX.xsd", ">CompactRepoRequest"), com.businessobjects.www.DataServices.ServerX_xsd.CompactRepoRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.businessobjects.com/DataServices/ServerX.xsd", ">RepoOperationResponse"));
        oper.setReturnClass(com.businessobjects.www.DataServices.ServerX_xsd.RepoOperationResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.businessobjects.com/DataServices/ServerX.xsd", "RepoOperationResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[3] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("Export_DQReport");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.businessobjects.com/DataServices/ServerX.xsd", "ExportDQReportRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.businessobjects.com/DataServices/ServerX.xsd", ">ExportDQReportRequest"), com.businessobjects.www.DataServices.ServerX_xsd.ExportDQReportRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.businessobjects.com/DataServices/ServerX.xsd", ">ExportDQReportResponse"));
        oper.setReturnClass(com.businessobjects.www.DataServices.ServerX_xsd.ExportDQReportResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.businessobjects.com/DataServices/ServerX.xsd", "ExportDQReportResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[4] = oper;

    }

    public Repo_OperationsStub() throws org.apache.axis.AxisFault {
         this(null);
    }

    public Repo_OperationsStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
         this(service);
         super.cachedEndpoint = endpointURL;
    }

    public Repo_OperationsStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
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
            qName = new javax.xml.namespace.QName("http://www.businessobjects.com/DataServices/ServerX.xsd", ">>>ValidateObjectDefinitionRequest>substitutionParameters>parameter");
            cachedSerQNames.add(qName);
            cls = com.businessobjects.www.DataServices.ServerX_xsd.ValidateObjectDefinitionRequestSubstitutionParametersParameter.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.businessobjects.com/DataServices/ServerX.xsd", ">>DeleteRepoObjectsRequest>objName");
            cachedSerQNames.add(qName);
            cls = com.businessobjects.www.DataServices.ServerX_xsd.DeleteRepoObjectsRequestObjName.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.businessobjects.com/DataServices/ServerX.xsd", ">>ExportDQReportResponse>report");
            cachedSerQNames.add(qName);
            cls = com.businessobjects.www.DataServices.ServerX_xsd.ExportDQReportResponseReport.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.businessobjects.com/DataServices/ServerX.xsd", ">>ValidateObjectDefinitionRequest>substitutionParameters");
            cachedSerQNames.add(qName);
            cls = com.businessobjects.www.DataServices.ServerX_xsd.ValidateObjectDefinitionRequestSubstitutionParametersParameter[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.businessobjects.com/DataServices/ServerX.xsd", ">>>ValidateObjectDefinitionRequest>substitutionParameters>parameter");
            qName2 = new javax.xml.namespace.QName("", "parameter");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.businessobjects.com/DataServices/ServerX.xsd", ">CompactRepoRequest");
            cachedSerQNames.add(qName);
            cls = com.businessobjects.www.DataServices.ServerX_xsd.CompactRepoRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.businessobjects.com/DataServices/ServerX.xsd", ">DeleteRepoObjectsRequest");
            cachedSerQNames.add(qName);
            cls = com.businessobjects.www.DataServices.ServerX_xsd.DeleteRepoObjectsRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.businessobjects.com/DataServices/ServerX.xsd", ">ExportDQReportRequest");
            cachedSerQNames.add(qName);
            cls = com.businessobjects.www.DataServices.ServerX_xsd.ExportDQReportRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.businessobjects.com/DataServices/ServerX.xsd", ">ExportDQReportResponse");
            cachedSerQNames.add(qName);
            cls = com.businessobjects.www.DataServices.ServerX_xsd.ExportDQReportResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.businessobjects.com/DataServices/ServerX.xsd", ">ImportObjectDefinitionRequest");
            cachedSerQNames.add(qName);
            cls = com.businessobjects.www.DataServices.ServerX_xsd.ImportObjectDefinitionRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.businessobjects.com/DataServices/ServerX.xsd", ">RepoOperationResponse");
            cachedSerQNames.add(qName);
            cls = com.businessobjects.www.DataServices.ServerX_xsd.RepoOperationResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.businessobjects.com/DataServices/ServerX.xsd", ">ValidateObjectDefinitionRequest");
            cachedSerQNames.add(qName);
            cls = com.businessobjects.www.DataServices.ServerX_xsd.ValidateObjectDefinitionRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.businessobjects.com/DataServices/ServerX.xsd", "DeleteType");
            cachedSerQNames.add(qName);
            cls = com.businessobjects.www.DataServices.ServerX_xsd.DeleteType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("http://www.businessobjects.com/DataServices/ServerX.xsd", "ValidateType");
            cachedSerQNames.add(qName);
            cls = com.businessobjects.www.DataServices.ServerX_xsd.ValidateType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

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

    public com.businessobjects.www.DataServices.ServerX_xsd.RepoOperationResponse import_Repo_Object(com.businessobjects.www.DataServices.ServerX_xsd.ImportObjectDefinitionRequest importObjectDefinitionRequest) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[0]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("repoAdmin=Import_Repo_Object");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "Import_Repo_Object"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {importObjectDefinitionRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.businessobjects.www.DataServices.ServerX_xsd.RepoOperationResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.businessobjects.www.DataServices.ServerX_xsd.RepoOperationResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.businessobjects.www.DataServices.ServerX_xsd.RepoOperationResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.businessobjects.www.DataServices.ServerX_xsd.RepoOperationResponse validate_Repo_Object(com.businessobjects.www.DataServices.ServerX_xsd.ValidateObjectDefinitionRequest validateObjectDefinitionRequest) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[1]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("repoAdmin=Validate_Repo_Object");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "Validate_Repo_Object"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {validateObjectDefinitionRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.businessobjects.www.DataServices.ServerX_xsd.RepoOperationResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.businessobjects.www.DataServices.ServerX_xsd.RepoOperationResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.businessobjects.www.DataServices.ServerX_xsd.RepoOperationResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.businessobjects.www.DataServices.ServerX_xsd.RepoOperationResponse delete_Repo_Objects(com.businessobjects.www.DataServices.ServerX_xsd.DeleteRepoObjectsRequest deleteRepoObjectsRequest) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[2]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("repoAdmin=Delete_Repo_Objects");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "Delete_Repo_Objects"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {deleteRepoObjectsRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.businessobjects.www.DataServices.ServerX_xsd.RepoOperationResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.businessobjects.www.DataServices.ServerX_xsd.RepoOperationResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.businessobjects.www.DataServices.ServerX_xsd.RepoOperationResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.businessobjects.www.DataServices.ServerX_xsd.RepoOperationResponse compact_Repo(com.businessobjects.www.DataServices.ServerX_xsd.CompactRepoRequest compactRepoRequest) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[3]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("repoAdmin=Compact_Repo");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "Compact_Repo"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {compactRepoRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.businessobjects.www.DataServices.ServerX_xsd.RepoOperationResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.businessobjects.www.DataServices.ServerX_xsd.RepoOperationResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.businessobjects.www.DataServices.ServerX_xsd.RepoOperationResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.businessobjects.www.DataServices.ServerX_xsd.ExportDQReportResponse export_DQReport(com.businessobjects.www.DataServices.ServerX_xsd.ExportDQReportRequest exportDQReportRequest) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[4]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("repoAdmin=Export_DQReport");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "Export_DQReport"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {exportDQReportRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.businessobjects.www.DataServices.ServerX_xsd.ExportDQReportResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.businessobjects.www.DataServices.ServerX_xsd.ExportDQReportResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.businessobjects.www.DataServices.ServerX_xsd.ExportDQReportResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

}
