/**
 * ServiceRequest_warrantyClaimsServiceRequestWS_BinderStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.webServices.warrantyClaimsServiceRequest.client1;

public class ServiceRequest_warrantyClaimsServiceRequestWS_BinderStub extends org.apache.axis.client.Stub implements com.lexmark.webServices.warrantyClaimsServiceRequest.client1.WarrantyClaimsServiceRequestWS_PortType {
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
        oper.setName("debriefWarrantyClaim");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "debug"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "synchOrAsynch"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "debriefClaimsServiceRequestInput"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/warrantyClaimsServiceRequestWS", "debriefClaimsServiceRequestInput"), com.lexmark.webServices.warrantyClaimsServiceRequest.client1.DebriefClaimsServiceRequestInput.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        oper.setReturnClass(java.lang.String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "status"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[0] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("updateWarrantyClaim");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "debug"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "synchOrAsynch"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "updateClaimsServiceRequestInput"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/warrantyClaimsServiceRequestWS", "updateClaimsServiceRequestInput"), com.lexmark.webServices.warrantyClaimsServiceRequest.client1.UpdateClaimsServiceRequestInput.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "status"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "claimNumber"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "claimId"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[1] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("createWarrantyClaim");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "debug"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "synchOrAsynch"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "createClaimsServiceRequestInput"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/warrantyClaimsServiceRequestWS", "createClaimsServiceRequestInput"), com.lexmark.webServices.warrantyClaimsServiceRequest.client1.CreateClaimsServiceRequestInput.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "claimNumber"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "claimId"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[2] = oper;

    }

    public ServiceRequest_warrantyClaimsServiceRequestWS_BinderStub() throws org.apache.axis.AxisFault {
         this(null);
    }

    public ServiceRequest_warrantyClaimsServiceRequestWS_BinderStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
         this(service);
         super.cachedEndpoint = endpointURL;
    }

    public ServiceRequest_warrantyClaimsServiceRequestWS_BinderStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
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
            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/warrantyClaimsServiceRequestWS", "Account");
            cachedSerQNames.add(qName);
            cls = com.lexmark.webServices.warrantyClaimsServiceRequest.client1.Account.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/warrantyClaimsServiceRequestWS", "ClaimsServiceRequestData");
            cachedSerQNames.add(qName);
            cls = com.lexmark.webServices.warrantyClaimsServiceRequest.client1.ClaimsServiceRequestData.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/warrantyClaimsServiceRequestWS", "ClaimsServiceRequestData2");
            cachedSerQNames.add(qName);
            cls = com.lexmark.webServices.warrantyClaimsServiceRequest.client1.ClaimsServiceRequestData2.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/warrantyClaimsServiceRequestWS", "ClaimsServiceRequestData3");
            cachedSerQNames.add(qName);
            cls = com.lexmark.webServices.warrantyClaimsServiceRequest.client1.ClaimsServiceRequestData3.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/warrantyClaimsServiceRequestWS", "createClaimsServiceRequestInput");
            cachedSerQNames.add(qName);
            cls = com.lexmark.webServices.warrantyClaimsServiceRequest.client1.CreateClaimsServiceRequestInput.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/warrantyClaimsServiceRequestWS", "createClaimsServiceRequestWSInput");
            cachedSerQNames.add(qName);
            cls = com.lexmark.webServices.warrantyClaimsServiceRequest.client1.CreateClaimsServiceRequestWSInput.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/warrantyClaimsServiceRequestWS", "debriefClaimsServiceRequestInput");
            cachedSerQNames.add(qName);
            cls = com.lexmark.webServices.warrantyClaimsServiceRequest.client1.DebriefClaimsServiceRequestInput.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/warrantyClaimsServiceRequestWS", "debriefClaimsServiceRequestWSInput");
            cachedSerQNames.add(qName);
            cls = com.lexmark.webServices.warrantyClaimsServiceRequest.client1.DebriefClaimsServiceRequestWSInput.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/warrantyClaimsServiceRequestWS", "Entitlement");
            cachedSerQNames.add(qName);
            cls = com.lexmark.webServices.warrantyClaimsServiceRequest.client1.Entitlement.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/warrantyClaimsServiceRequestWS", "EntitlementServiceDetails");
            cachedSerQNames.add(qName);
            cls = com.lexmark.webServices.warrantyClaimsServiceRequest.client1.EntitlementServiceDetails.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/warrantyClaimsServiceRequestWS", "NoteAuthor");
            cachedSerQNames.add(qName);
            cls = com.lexmark.webServices.warrantyClaimsServiceRequest.client1.NoteAuthor.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/warrantyClaimsServiceRequestWS", "PartInformation");
            cachedSerQNames.add(qName);
            cls = com.lexmark.webServices.warrantyClaimsServiceRequest.client1.PartInformation.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/warrantyClaimsServiceRequestWS", "PartInfromation");
            cachedSerQNames.add(qName);
            cls = com.lexmark.webServices.warrantyClaimsServiceRequest.client1.PartInfromation.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/warrantyClaimsServiceRequestWS", "SiebelActivityNotes");
            cachedSerQNames.add(qName);
            cls = com.lexmark.webServices.warrantyClaimsServiceRequest.client1.SiebelActivityNotes.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/warrantyClaimsServiceRequestWS", "SiebelAddress");
            cachedSerQNames.add(qName);
            cls = com.lexmark.webServices.warrantyClaimsServiceRequest.client1.SiebelAddress.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/warrantyClaimsServiceRequestWS", "SiebelAssetInformation");
            cachedSerQNames.add(qName);
            cls = com.lexmark.webServices.warrantyClaimsServiceRequest.client1.SiebelAssetInformation.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/warrantyClaimsServiceRequestWS", "SiebelContact");
            cachedSerQNames.add(qName);
            cls = com.lexmark.webServices.warrantyClaimsServiceRequest.client1.SiebelContact.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/warrantyClaimsServiceRequestWS", "SiebelDebriefInformation");
            cachedSerQNames.add(qName);
            cls = com.lexmark.webServices.warrantyClaimsServiceRequest.client1.SiebelDebriefInformation.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/warrantyClaimsServiceRequestWS", "SiebelMDMCustomerInformation");
            cachedSerQNames.add(qName);
            cls = com.lexmark.webServices.warrantyClaimsServiceRequest.client1.SiebelMDMCustomerInformation.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/warrantyClaimsServiceRequestWS", "SiebelPaymentRequestList");
            cachedSerQNames.add(qName);
            cls = com.lexmark.webServices.warrantyClaimsServiceRequest.client1.SiebelPaymentRequestList.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/warrantyClaimsServiceRequestWS", "SiebelRecommendedPartsList");
            cachedSerQNames.add(qName);
            cls = com.lexmark.webServices.warrantyClaimsServiceRequest.client1.SiebelRecommendedPartsList.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/warrantyClaimsServiceRequestWS", "SiebelReturnPartsList");
            cachedSerQNames.add(qName);
            cls = com.lexmark.webServices.warrantyClaimsServiceRequest.client1.SiebelReturnPartsList.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/warrantyClaimsServiceRequestWS", "SiebelServiceProviderInformation");
            cachedSerQNames.add(qName);
            cls = com.lexmark.webServices.warrantyClaimsServiceRequest.client1.SiebelServiceProviderInformation.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/warrantyClaimsServiceRequestWS", "SiebelServiceRequestDetails");
            cachedSerQNames.add(qName);
            cls = com.lexmark.webServices.warrantyClaimsServiceRequest.client1.SiebelServiceRequestDetails.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/warrantyClaimsServiceRequestWS", "updateClaimsServiceRequestInput");
            cachedSerQNames.add(qName);
            cls = com.lexmark.webServices.warrantyClaimsServiceRequest.client1.UpdateClaimsServiceRequestInput.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/warrantyClaimsServiceRequestWS", "updateClaimsServiceRequestWSInput");
            cachedSerQNames.add(qName);
            cls = com.lexmark.webServices.warrantyClaimsServiceRequest.client1.UpdateClaimsServiceRequestWSInput.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/warrantyClaimsServiceRequestWS", "WebServiceDocumentMetaData");
            cachedSerQNames.add(qName);
            cls = com.lexmark.webServices.warrantyClaimsServiceRequest.client1.WebServiceDocumentMetaData.class;
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

    public java.lang.String debriefWarrantyClaim(java.lang.String debug, java.lang.String synchOrAsynch, com.lexmark.webServices.warrantyClaimsServiceRequest.client1.DebriefClaimsServiceRequestInput debriefClaimsServiceRequestInput) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[0]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("ServiceRequest_warrantyClaimsServiceRequestWS_Binder_debriefWarrantyClaim");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/warrantyClaimsServiceRequestWS", "debriefWarrantyClaim"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {debug, synchOrAsynch, debriefClaimsServiceRequestInput});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.String) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public void updateWarrantyClaim(java.lang.String debug, java.lang.String synchOrAsynch, com.lexmark.webServices.warrantyClaimsServiceRequest.client1.UpdateClaimsServiceRequestInput updateClaimsServiceRequestInput, javax.xml.rpc.holders.StringHolder status, javax.xml.rpc.holders.StringHolder claimNumber, javax.xml.rpc.holders.StringHolder claimId) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[1]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("ServiceRequest_warrantyClaimsServiceRequestWS_Binder_updateWarrantyClaim");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/warrantyClaimsServiceRequestWS", "updateWarrantyClaim"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {debug, synchOrAsynch, updateClaimsServiceRequestInput});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            java.util.Map _output;
            _output = _call.getOutputParams();
            try {
                status.value = (java.lang.String) _output.get(new javax.xml.namespace.QName("", "status"));
            } catch (java.lang.Exception _exception) {
                status.value = (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "status")), java.lang.String.class);
            }
            try {
                claimNumber.value = (java.lang.String) _output.get(new javax.xml.namespace.QName("", "claimNumber"));
            } catch (java.lang.Exception _exception) {
                claimNumber.value = (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "claimNumber")), java.lang.String.class);
            }
            try {
                claimId.value = (java.lang.String) _output.get(new javax.xml.namespace.QName("", "claimId"));
            } catch (java.lang.Exception _exception) {
                claimId.value = (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "claimId")), java.lang.String.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public void createWarrantyClaim(java.lang.String debug, java.lang.String synchOrAsynch, com.lexmark.webServices.warrantyClaimsServiceRequest.client1.CreateClaimsServiceRequestInput createClaimsServiceRequestInput, javax.xml.rpc.holders.StringHolder claimNumber, javax.xml.rpc.holders.StringHolder claimId) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[2]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("ServiceRequest_warrantyClaimsServiceRequestWS_Binder_createWarrantyClaim");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/warrantyClaimsServiceRequestWS", "createWarrantyClaim"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {debug, synchOrAsynch, createClaimsServiceRequestInput});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            java.util.Map _output;
            _output = _call.getOutputParams();
            try {
                claimNumber.value = (java.lang.String) _output.get(new javax.xml.namespace.QName("", "claimNumber"));
            } catch (java.lang.Exception _exception) {
                claimNumber.value = (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "claimNumber")), java.lang.String.class);
            }
            try {
                claimId.value = (java.lang.String) _output.get(new javax.xml.namespace.QName("", "claimId"));
            } catch (java.lang.Exception _exception) {
                claimId.value = (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "claimId")), java.lang.String.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

}
