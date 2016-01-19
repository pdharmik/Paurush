/**
 * RealTime_ServicesStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.businessobjects.www;

public class RealTime_ServicesStub extends org.apache.axis.client.Stub implements com.businessobjects.www.RealTime_Services {
    private java.util.Vector cachedSerClasses = new java.util.Vector();
    private java.util.Vector cachedSerQNames = new java.util.Vector();
    private java.util.Vector cachedSerFactories = new java.util.Vector();
    private java.util.Vector cachedDeserFactories = new java.util.Vector();

    static org.apache.axis.description.OperationDesc [] _operations;

    static {
        _operations = new org.apache.axis.description.OperationDesc[7];
        _initOperationDesc1();
    }

    private static void _initOperationDesc1(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("Service_Realtime_Batch_DQ_Siebel_business_address_datacleanse");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://businessobjects.com/service/Service_Realtime_Batch_DQ_Siebel_business_address_datacleanse/input", "DataSet"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://businessobjects.com/service/Service_Realtime_Batch_DQ_Siebel_business_address_datacleanse/input", ">DataSet"), com.businessobjects.service.Service_Realtime_Batch_DQ_Siebel_business_address_datacleanse.input.DataSet.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://businessobjects.com/service/Service_Realtime_Batch_DQ_Siebel_business_address_datacleanse/output", ">DataSet"));
        oper.setReturnClass(com.businessobjects.service.Service_Realtime_Batch_DQ_Siebel_business_address_datacleanse.output.DataSet.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://businessobjects.com/service/Service_Realtime_Batch_DQ_Siebel_business_address_datacleanse/output", "DataSet"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[0] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("ABC");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://businessobjects.com/service/ABC/input", "DataSet"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://businessobjects.com/service/ABC/input", ">DataSet"), com.businessobjects.service.ABC.input.DataSet.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://businessobjects.com/service/ABC/output", ">DataSet"));
        oper.setReturnClass(com.businessobjects.service.ABC.output.DataSet.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://businessobjects.com/service/ABC/output", "DataSet"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[1] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("Service_Realtime_DQ_Siebel_account_match");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://businessobjects.com/service/Service_Realtime_DQ_Siebel_account_match/input", "DataSet"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://businessobjects.com/service/Service_Realtime_DQ_Siebel_account_match/input", ">DataSet"), com.businessobjects.service.Service_Realtime_DQ_Siebel_account_match.input.DataSet.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://businessobjects.com/service/Service_Realtime_DQ_Siebel_account_match/output", ">DataSet"));
        oper.setReturnClass(com.businessobjects.service.Service_Realtime_DQ_Siebel_account_match.output.DataSet.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://businessobjects.com/service/Service_Realtime_DQ_Siebel_account_match/output", "DataSet"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[2] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("Service_Realtime_DQ_Siebel_business_address_datacleanse");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://businessobjects.com/service/Service_Realtime_DQ_Siebel_business_address_datacleanse/input", "DataSet"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://businessobjects.com/service/Service_Realtime_DQ_Siebel_business_address_datacleanse/input", ">DataSet"), com.businessobjects.service.Service_Realtime_DQ_Siebel_business_address_datacleanse.input.DataSet.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://businessobjects.com/service/Service_Realtime_DQ_Siebel_business_address_datacleanse/output", ">DataSet"));
        oper.setReturnClass(com.businessobjects.service.Service_Realtime_DQ_Siebel_business_address_datacleanse.output.DataSet.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://businessobjects.com/service/Service_Realtime_DQ_Siebel_business_address_datacleanse/output", "DataSet"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[3] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("RT_ADDRESS");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://businessobjects.com/service/RT_ADDRESS/input", "ROOT"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://businessobjects.com/service/RT_ADDRESS/input", ">ROOT"), com.businessobjects.service.RT_ADDRESS.input.ROOT.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://businessobjects.com/service/RT_ADDRESS/output", ">ROOT"));
        oper.setReturnClass(com.businessobjects.service.RT_ADDRESS.output.ROOT.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://businessobjects.com/service/RT_ADDRESS/output", "ROOT"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[4] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("RT_ADDR_MATCH_CUST_SQL_INS_UPD");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://businessobjects.com/service/RT_ADDR_MATCH_CUST_SQL_INS_UPD/input", "ROOT"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://businessobjects.com/service/RT_ADDR_MATCH_CUST_SQL_INS_UPD/input", ">ROOT"), com.businessobjects.service.RT_ADDR_MATCH_CUST_SQL_INS_UPD.input.ROOT.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://businessobjects.com/service/RT_ADDR_MATCH_CUST_SQL_INS_UPD/output", ">ROOT"));
        oper.setReturnClass(com.businessobjects.service.RT_ADDR_MATCH_CUST_SQL_INS_UPD.output.ROOT.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://businessobjects.com/service/RT_ADDR_MATCH_CUST_SQL_INS_UPD/output", "ROOT"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[5] = oper;
        
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("Service_Realtime_DQ_Portal_business_address_region_code");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://businessobjects.com/service/Service_Realtime_DQ_Portal_business_address_region_code/input", "DataSet"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://businessobjects.com/service/Service_Realtime_DQ_Portal_business_address_region_code/input", ">DataSet"), com.businessobjects.service.Service_Realtime_DQ_Portal_business_address_region_code.input.DataSet.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://businessobjects.com/service/Service_Realtime_DQ_Portal_business_address_region_code/output", ">DataSet"));
        oper.setReturnClass(com.businessobjects.service.Service_Realtime_DQ_Portal_business_address_region_code.output.DataSet.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://businessobjects.com/service/Service_Realtime_DQ_Portal_business_address_region_code/output", "DataSet"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[6] = oper;
    }

    public RealTime_ServicesStub() throws org.apache.axis.AxisFault {
         this(null);
    }

    public RealTime_ServicesStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
         this(service);
         super.cachedEndpoint = endpointURL;
    }

    public RealTime_ServicesStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
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
            qName = new javax.xml.namespace.QName("http://businessobjects.com/service/ABC/input", ">>DataSet>Record");
            cachedSerQNames.add(qName);
            cls = com.businessobjects.service.ABC.input.DataSetRecord.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://businessobjects.com/service/ABC/input", ">DataSet");
            cachedSerQNames.add(qName);
            cls = com.businessobjects.service.ABC.input.DataSet.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://businessobjects.com/service/ABC/output", ">>DataSet>Record");
            cachedSerQNames.add(qName);
            cls = com.businessobjects.service.ABC.output.DataSetRecord.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://businessobjects.com/service/ABC/output", ">DataSet");
            cachedSerQNames.add(qName);
            cls = com.businessobjects.service.ABC.output.DataSet.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://businessobjects.com/service/RT_ADDR_MATCH_CUST_SQL_INS_UPD/input", ">ROOT");
            cachedSerQNames.add(qName);
            cls = com.businessobjects.service.RT_ADDR_MATCH_CUST_SQL_INS_UPD.input.ROOT.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://businessobjects.com/service/RT_ADDR_MATCH_CUST_SQL_INS_UPD/input", "DIType-varchar-200");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://businessobjects.com/service/RT_ADDR_MATCH_CUST_SQL_INS_UPD/output", ">ROOT");
            cachedSerQNames.add(qName);
            cls = com.businessobjects.service.RT_ADDR_MATCH_CUST_SQL_INS_UPD.output.ROOT.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://businessobjects.com/service/RT_ADDR_MATCH_CUST_SQL_INS_UPD/output", "DIType-varchar-200");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://businessobjects.com/service/RT_ADDRESS/input", ">ROOT");
            cachedSerQNames.add(qName);
            cls = com.businessobjects.service.RT_ADDRESS.input.ROOT.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://businessobjects.com/service/RT_ADDRESS/input", "DIType-varchar-200");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://businessobjects.com/service/RT_ADDRESS/output", ">ROOT");
            cachedSerQNames.add(qName);
            cls = com.businessobjects.service.RT_ADDRESS.output.ROOT.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://businessobjects.com/service/RT_ADDRESS/output", "DIType-varchar-200");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://businessobjects.com/service/Service_Realtime_Batch_DQ_Siebel_business_address_datacleanse/input", ">>DataSet>Record");
            cachedSerQNames.add(qName);
            cls = com.businessobjects.service.Service_Realtime_Batch_DQ_Siebel_business_address_datacleanse.input.DataSetRecord.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://businessobjects.com/service/Service_Realtime_Batch_DQ_Siebel_business_address_datacleanse/input", ">DataSet");
            cachedSerQNames.add(qName);
            cls = com.businessobjects.service.Service_Realtime_Batch_DQ_Siebel_business_address_datacleanse.input.DataSet.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://businessobjects.com/service/Service_Realtime_Batch_DQ_Siebel_business_address_datacleanse/output", ">>DataSet>Record");
            cachedSerQNames.add(qName);
            cls = com.businessobjects.service.Service_Realtime_Batch_DQ_Siebel_business_address_datacleanse.output.DataSetRecord.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://businessobjects.com/service/Service_Realtime_Batch_DQ_Siebel_business_address_datacleanse/output", ">DataSet");
            cachedSerQNames.add(qName);
            cls = com.businessobjects.service.Service_Realtime_Batch_DQ_Siebel_business_address_datacleanse.output.DataSet.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://businessobjects.com/service/Service_Realtime_DQ_Siebel_account_match/input", ">>DataSet>Record");
            cachedSerQNames.add(qName);
            cls = com.businessobjects.service.Service_Realtime_DQ_Siebel_account_match.input.DataSetRecord.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://businessobjects.com/service/Service_Realtime_DQ_Siebel_account_match/input", ">DataSet");
            cachedSerQNames.add(qName);
            cls = com.businessobjects.service.Service_Realtime_DQ_Siebel_account_match.input.DataSet.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://businessobjects.com/service/Service_Realtime_DQ_Siebel_account_match/output", ">>DataSet>Record");
            cachedSerQNames.add(qName);
            cls = com.businessobjects.service.Service_Realtime_DQ_Siebel_account_match.output.DataSetRecord.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://businessobjects.com/service/Service_Realtime_DQ_Siebel_account_match/output", ">DataSet");
            cachedSerQNames.add(qName);
            cls = com.businessobjects.service.Service_Realtime_DQ_Siebel_account_match.output.DataSet.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://businessobjects.com/service/Service_Realtime_DQ_Siebel_business_address_datacleanse/input", ">>DataSet>Record");
            cachedSerQNames.add(qName);
            cls = com.businessobjects.service.Service_Realtime_DQ_Siebel_business_address_datacleanse.input.DataSetRecord.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://businessobjects.com/service/Service_Realtime_DQ_Siebel_business_address_datacleanse/input", ">DataSet");
            cachedSerQNames.add(qName);
            cls = com.businessobjects.service.Service_Realtime_DQ_Siebel_business_address_datacleanse.input.DataSet.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://businessobjects.com/service/Service_Realtime_DQ_Siebel_business_address_datacleanse/output", ">>DataSet>Record");
            cachedSerQNames.add(qName);
            cls = com.businessobjects.service.Service_Realtime_DQ_Siebel_business_address_datacleanse.output.DataSetRecord.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://businessobjects.com/service/Service_Realtime_DQ_Siebel_business_address_datacleanse/output", ">DataSet");
            cachedSerQNames.add(qName);
            cls = com.businessobjects.service.Service_Realtime_DQ_Siebel_business_address_datacleanse.output.DataSet.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);
            
            qName = new javax.xml.namespace.QName("http://businessobjects.com/service/Service_Realtime_DQ_Portal_business_address_region_code/input", ">>DataSet>Record");
            cachedSerQNames.add(qName);
            cls = com.businessobjects.service.Service_Realtime_DQ_Portal_business_address_region_code.input.DataSetRecord.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://businessobjects.com/service/Service_Realtime_DQ_Portal_business_address_region_code/input", ">DataSet");
            cachedSerQNames.add(qName);
            cls = com.businessobjects.service.Service_Realtime_DQ_Portal_business_address_region_code.input.DataSet.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://businessobjects.com/service/Service_Realtime_DQ_Portal_business_address_region_code/output", ">>DataSet>Record");
            cachedSerQNames.add(qName);
            cls = com.businessobjects.service.Service_Realtime_DQ_Portal_business_address_region_code.output.DataSetRecord.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://businessobjects.com/service/Service_Realtime_DQ_Portal_business_address_region_code/output", ">DataSet");
            cachedSerQNames.add(qName);
            cls = com.businessobjects.service.Service_Realtime_DQ_Portal_business_address_region_code.output.DataSet.class;
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

    public com.businessobjects.service.Service_Realtime_Batch_DQ_Siebel_business_address_datacleanse.output.DataSet service_Realtime_Batch_DQ_Siebel_business_address_datacleanse(com.businessobjects.service.Service_Realtime_Batch_DQ_Siebel_business_address_datacleanse.input.DataSet inputBody) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[0]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("service=Service_Realtime_Batch_DQ_Siebel_business_address_datacleanse");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "Service_Realtime_Batch_DQ_Siebel_business_address_datacleanse"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {inputBody});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.businessobjects.service.Service_Realtime_Batch_DQ_Siebel_business_address_datacleanse.output.DataSet) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.businessobjects.service.Service_Realtime_Batch_DQ_Siebel_business_address_datacleanse.output.DataSet) org.apache.axis.utils.JavaUtils.convert(_resp, com.businessobjects.service.Service_Realtime_Batch_DQ_Siebel_business_address_datacleanse.output.DataSet.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.businessobjects.service.ABC.output.DataSet ABC(com.businessobjects.service.ABC.input.DataSet inputBody) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[1]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("service=ABC");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "ABC"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {inputBody});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.businessobjects.service.ABC.output.DataSet) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.businessobjects.service.ABC.output.DataSet) org.apache.axis.utils.JavaUtils.convert(_resp, com.businessobjects.service.ABC.output.DataSet.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.businessobjects.service.Service_Realtime_DQ_Siebel_account_match.output.DataSet service_Realtime_DQ_Siebel_account_match(com.businessobjects.service.Service_Realtime_DQ_Siebel_account_match.input.DataSet inputBody) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[2]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("service=Service_Realtime_DQ_Siebel_account_match");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "Service_Realtime_DQ_Siebel_account_match"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {inputBody});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.businessobjects.service.Service_Realtime_DQ_Siebel_account_match.output.DataSet) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.businessobjects.service.Service_Realtime_DQ_Siebel_account_match.output.DataSet) org.apache.axis.utils.JavaUtils.convert(_resp, com.businessobjects.service.Service_Realtime_DQ_Siebel_account_match.output.DataSet.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.businessobjects.service.Service_Realtime_DQ_Siebel_business_address_datacleanse.output.DataSet service_Realtime_DQ_Siebel_business_address_datacleanse(com.businessobjects.service.Service_Realtime_DQ_Siebel_business_address_datacleanse.input.DataSet inputBody) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[3]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("service=Service_Realtime_DQ_Siebel_business_address_datacleanse");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "Service_Realtime_DQ_Siebel_business_address_datacleanse"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {inputBody});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.businessobjects.service.Service_Realtime_DQ_Siebel_business_address_datacleanse.output.DataSet) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.businessobjects.service.Service_Realtime_DQ_Siebel_business_address_datacleanse.output.DataSet) org.apache.axis.utils.JavaUtils.convert(_resp, com.businessobjects.service.Service_Realtime_DQ_Siebel_business_address_datacleanse.output.DataSet.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.businessobjects.service.RT_ADDRESS.output.ROOT RT_ADDRESS(com.businessobjects.service.RT_ADDRESS.input.ROOT inputBody) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[4]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("service=RT_ADDRESS");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "RT_ADDRESS"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {inputBody});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.businessobjects.service.RT_ADDRESS.output.ROOT) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.businessobjects.service.RT_ADDRESS.output.ROOT) org.apache.axis.utils.JavaUtils.convert(_resp, com.businessobjects.service.RT_ADDRESS.output.ROOT.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.businessobjects.service.RT_ADDR_MATCH_CUST_SQL_INS_UPD.output.ROOT RT_ADDR_MATCH_CUST_SQL_INS_UPD(com.businessobjects.service.RT_ADDR_MATCH_CUST_SQL_INS_UPD.input.ROOT inputBody) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[5]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("service=RT_ADDR_MATCH_CUST_SQL_INS_UPD");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "RT_ADDR_MATCH_CUST_SQL_INS_UPD"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {inputBody});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.businessobjects.service.RT_ADDR_MATCH_CUST_SQL_INS_UPD.output.ROOT) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.businessobjects.service.RT_ADDR_MATCH_CUST_SQL_INS_UPD.output.ROOT) org.apache.axis.utils.JavaUtils.convert(_resp, com.businessobjects.service.RT_ADDR_MATCH_CUST_SQL_INS_UPD.output.ROOT.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }
    
    public com.businessobjects.service.Service_Realtime_DQ_Portal_business_address_region_code.output.DataSet service_Realtime_DQ_Portal_business_address_region_code(com.businessobjects.service.Service_Realtime_DQ_Portal_business_address_region_code.input.DataSet inputBody) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[6]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("service=Service_Realtime_DQ_Portal_business_address_region_code");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "Service_Realtime_DQ_Portal_business_address_region_code"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {inputBody});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.businessobjects.service.Service_Realtime_DQ_Portal_business_address_region_code.output.DataSet) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.businessobjects.service.Service_Realtime_DQ_Portal_business_address_region_code.output.DataSet) org.apache.axis.utils.JavaUtils.convert(_resp, com.businessobjects.service.Service_Realtime_DQ_Portal_business_address_region_code.output.DataSet.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
	  throw axisFaultException;
  }
    }
}
