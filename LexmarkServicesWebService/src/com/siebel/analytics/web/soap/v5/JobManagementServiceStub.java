/**
 * JobManagementServiceStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.siebel.analytics.web.soap.v5;

public class JobManagementServiceStub extends org.apache.axis.client.Stub implements com.siebel.analytics.web.soap.v5.JobManagementServiceSoap {
    private java.util.Vector cachedSerClasses = new java.util.Vector();
    private java.util.Vector cachedSerQNames = new java.util.Vector();
    private java.util.Vector cachedSerFactories = new java.util.Vector();
    private java.util.Vector cachedDeserFactories = new java.util.Vector();

    static org.apache.axis.description.OperationDesc [] _operations;

    static {
        _operations = new org.apache.axis.description.OperationDesc[8];
        _initOperationDesc1();
    }

    private static void _initOperationDesc1(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("writeListFiles");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "report"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "ReportRef"), com.siebel.analytics.web.soap.v5.ReportRef.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "reportParams"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "ReportParams"), com.siebel.analytics.web.soap.v5.ReportParams.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "segmentPath"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "treeNodePath"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "TreeNodePath"), com.siebel.analytics.web.soap.v5.TreeNodePath.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "segmentationOptions"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "SegmentationOptions"), com.siebel.analytics.web.soap.v5.SegmentationOptions.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "filesystem"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "timeout"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"), java.math.BigInteger.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "sessionID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "JobInfo"));
        oper.setReturnClass(com.siebel.analytics.web.soap.v5.JobInfo.class);
        oper.setReturnQName(new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "jobInfo"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[0] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getJobInfo");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "jobID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"), java.math.BigInteger.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "sessionID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "JobInfo"));
        oper.setReturnClass(com.siebel.analytics.web.soap.v5.JobInfo.class);
        oper.setReturnQName(new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "jobInfo"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[1] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("cancelJob");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "jobID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"), java.math.BigInteger.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "sessionID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "JobInfo"));
        oper.setReturnClass(com.siebel.analytics.web.soap.v5.JobInfo.class);
        oper.setReturnQName(new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "jobInfo"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[2] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getCounts");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "segmentPath"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "treePath"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "segmentationOptions"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "SegmentationOptions"), com.siebel.analytics.web.soap.v5.SegmentationOptions.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "sessionID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "JobInfo"));
        oper.setReturnClass(com.siebel.analytics.web.soap.v5.JobInfo.class);
        oper.setReturnQName(new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "jobInfo"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[3] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("purgeCache");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "segmentPath"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "treePath"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "ignoreCacheRef"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"), java.lang.Boolean.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "sessionID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "JobInfo"));
        oper.setReturnClass(com.siebel.analytics.web.soap.v5.JobInfo.class);
        oper.setReturnQName(new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "jobInfo"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[4] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("prepareCache");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "segmentPath"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "treePath"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "refresh"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"), java.lang.Boolean.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "sessionID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "JobInfo"));
        oper.setReturnClass(com.siebel.analytics.web.soap.v5.JobInfo.class);
        oper.setReturnQName(new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "jobInfo"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[5] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("saveResultSet");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "segmentPath"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "treeNodePath"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "TreeNodePath"), com.siebel.analytics.web.soap.v5.TreeNodePath.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "savedSegmentPath"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "segmentationOptions"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "SegmentationOptions"), com.siebel.analytics.web.soap.v5.SegmentationOptions.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "SRCustomLabel"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "appendStaticSegment"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"), java.lang.Boolean.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "sessionID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "JobInfo"));
        oper.setReturnClass(com.siebel.analytics.web.soap.v5.JobInfo.class);
        oper.setReturnQName(new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "jobInfo"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[6] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("deleteResultSet");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "targetLevel"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "GUIDs"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "arrayOfGUIDs"), java.lang.String[].class, false, false);
        param.setItemQName(new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "GUID"));
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "segmentPath"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "sessionID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "JobInfo"));
        oper.setReturnClass(com.siebel.analytics.web.soap.v5.JobInfo.class);
        oper.setReturnQName(new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "jobInfo"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[7] = oper;

    }

    public JobManagementServiceStub() throws org.apache.axis.AxisFault {
         this(null);
    }

    public JobManagementServiceStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
         this(service);
         super.cachedEndpoint = endpointURL;
    }

    public JobManagementServiceStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
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
        addBindings0();
        addBindings1();
        addBindings2();
    }

    private void addBindings0() {
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
            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", ">addReportToPage");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.AddReportToPage.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", ">addReportToPageResult");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.AddReportToPageResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", ">applyReportParams");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.ApplyReportParams.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", ">applyReportParamsResult");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.ApplyReportParamsResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", ">cancelJob");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.CancelJob.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", ">cancelJobResult");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.CancelJobResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", ">cancelQuery");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.CancelQuery.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", ">cancelQueryResult");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.CancelQueryResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", ">copyItem");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.CopyItem.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", ">copyItem2");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.CopyItem2.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", ">copyItem2Result");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.CopyItem2Result.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", ">copyItemResult");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.CopyItemResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", ">createAccount");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.CreateAccount.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", ">createAccountResult");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.CreateAccountResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", ">createFolder");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.CreateFolder.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", ">createFolderResult");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.CreateFolderResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", ">createLink");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.CreateLink.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", ">createLinkResult");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.CreateLinkResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", ">deleteItem");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.DeleteItem.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", ">deleteItemResult");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.DeleteItemResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", ">deleteResultSet");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.DeleteResultSet.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", ">deleteResultSetResult");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.DeleteResultSetResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", ">describeColumn");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.DescribeColumn.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", ">describeColumnResult");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.DescribeColumnResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", ">describeSubjectArea");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.DescribeSubjectArea.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", ">describeSubjectAreaResult");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.DescribeSubjectAreaResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", ">describeTable");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.DescribeTable.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", ">describeTableResult");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.DescribeTableResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", ">endPage");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.EndPage.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", ">endPageResult");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.EndPageResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", ">executeIBotNow");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.ExecuteIBotNow.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", ">executeIBotNowResult");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.ExecuteIBotNowResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", ">executeSQLQuery");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.ExecuteSQLQuery.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", ">executeSQLQueryResult");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.ExecuteSQLQueryResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", ">executeXMLQuery");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.ExecuteXMLQuery.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", ">executeXMLQueryResult");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.ExecuteXMLQueryResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", ">export");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.Export.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", ">exportResult");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.ExportResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", ">fetchNext");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.FetchNext.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", ">fetchNextResult");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.FetchNextResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", ">forgetAccount");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.ForgetAccount.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", ">forgetAccountResult");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.ForgetAccountResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", ">generateReportSQL");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.GenerateReportSQL.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", ">generateReportSQLResult");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.GenerateReportSQLResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", ">getCatalogAccountsDatabase");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.GetCatalogAccountsDatabase.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", ">getCatalogAccountsDatabaseResult");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.Account[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "Account");
            qName2 = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "account");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", ">getCommonBodyHtml");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.GetCommonBodyHtml.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", ">getCommonBodyHtmlResult");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.GetCommonBodyHtmlResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", ">getCounts");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.GetCounts.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", ">getCountsResult");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.GetCountsResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", ">getCurUser");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.GetCurUser.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", ">getCurUserResult");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.GetCurUserResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", ">getGlobalPrivilegeACL");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.GetGlobalPrivilegeACL.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", ">getGlobalPrivilegeACLResult");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.GetGlobalPrivilegeACLResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", ">getGlobalPrivileges");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.GetGlobalPrivileges.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", ">getGlobalPrivilegesResult");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.Privilege[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "Privilege");
            qName2 = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "sawPrivileges");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", ">getGroups");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.GetGroups.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", ">getGroupsResult");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.Account[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "Account");
            qName2 = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "account");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", ">getHeadersHtml");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.GetHeadersHtml.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", ">getHeadersHtmlResult");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.GetHeadersHtmlResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", ">getHtmlForPageWithOneReport");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.GetHtmlForPageWithOneReport.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", ">getHtmlForPageWithOneReportResult");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.GetHtmlForPageWithOneReportResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", ">getHtmlForReport");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.GetHtmlForReport.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", ">getHtmlForReportResult");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.GetHtmlForReportResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", ">getItemInfo");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.GetItemInfo.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", ">getItemInfoResult");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.GetItemInfoResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", ">getJobInfo");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.GetJobInfo.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", ">getJobInfoResult");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.GetJobInfoResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", ">getMembers");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.GetMembers.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", ">getMembersResult");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.Account[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "Account");
            qName2 = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "account");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", ">getPermissions");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.GetPermissions.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", ">getPermissionsResult");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.GetPermissionsResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", ">getResults");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.GetResults.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", ">getResultsResult");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.GetResultsResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", ">getSessionEnvironment");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.GetSessionEnvironment.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", ">getSessionEnvironmentResult");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.GetSessionEnvironmentResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", ">getSubItems");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.GetSubItems.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", ">getSubItemsResult");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.ItemInfo[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "ItemInfo");
            qName2 = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "itemInfo");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", ">getSubjectAreas");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.GetSubjectAreas.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", ">getSubjectAreasResult");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.SASubjectArea[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "SASubjectArea");
            qName2 = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "subjectArea");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", ">impersonate");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.Impersonate.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", ">impersonateex");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.Impersonateex.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", ">impersonateexResult");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.ImpersonateexResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", ">impersonateResult");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.ImpersonateResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", ">import");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5._import.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", ">importResult");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.ImportError[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "ImportError");
            qName2 = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "error");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", ">isMember");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.IsMember.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", ">isMemberResult");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.IsMemberResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", ">JobInfo>detailedInfo");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.JobInfoDetailedInfo.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", ">JobStats>jobState");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.JobStatsJobState.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", ">joinGroup");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.JoinGroup.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", ">joinGroupResult");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.JoinGroupResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", ">keepAlive");
            cachedSerQNames.add(qName);
            cls = java.lang.String[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string");
            qName2 = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "sessionID");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", ">keepAliveResult");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.KeepAliveResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", ">leaveGroup");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.LeaveGroup.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", ">leaveGroupResult");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.LeaveGroupResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", ">logoff");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.Logoff.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", ">logoffResult");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.LogoffResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", ">logon");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.Logon.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", ">logonex");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.Logonex.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

    }
    private void addBindings1() {
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
            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", ">logonexResult");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.LogonexResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", ">logonResult");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.LogonResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", ">markForReplication");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.MarkForReplication.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", ">markForReplicationResult");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.MarkForReplicationResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", ">moveItem");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.MoveItem.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", ">moveItemResult");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.MoveItemResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", ">pasteItem2");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.PasteItem2.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", ">pasteItem2Result");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.PasteItem2Result.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", ">prepareCache");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.PrepareCache.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", ">prepareCacheResult");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.PrepareCacheResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", ">purgeCache");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.PurgeCache.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", ">purgeCacheResult");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.PurgeCacheResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", ">purgeLog");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.PurgeLog.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", ">purgeLogResult");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.PurgeLogResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", ">readObject");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.ReadObject.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", ">readObjectResult");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.ReadObjectResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", ">readObjects");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.ReadObjects.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", ">readObjectsResult");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.CatalogObject[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "CatalogObject");
            qName2 = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "catalogObject");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", ">removeFolder");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.RemoveFolder.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", ">removeFolderResult");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.RemoveFolderResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", ">renameAccount");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.RenameAccount.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", ">renameAccountResult");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.RenameAccountResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", ">saveResultSet");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.SaveResultSet.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", ">saveResultSetResult");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.SaveResultSetResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", ">setBridge");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.SetBridge.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", ">setBridgeResult");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.SetBridgeResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", ">setItemAttributes");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.SetItemAttributes.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", ">setItemAttributesResult");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.SetItemAttributesResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", ">setItemProperty");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.SetItemProperty.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", ">setItemPropertyResult");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.SetItemPropertyResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", ">startPage");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.StartPage.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", ">startPageResult");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.StartPageResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", ">takeOwnership");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.TakeOwnership.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", ">takeOwnershipResult");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.TakeOwnershipResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", ">updateCatalogItemACL");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.UpdateCatalogItemACL.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", ">updateCatalogItemACLResult");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.UpdateCatalogItemACLResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", ">updateGlobalPrivilegeACL");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.UpdateGlobalPrivilegeACL.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", ">updateGlobalPrivilegeACLResult");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.UpdateGlobalPrivilegeACLResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", ">voidType");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.VoidType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", ">writeDashboard");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.WriteDashboard.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", ">writeDashboardPage");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.WriteDashboardPage.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", ">writeDashboardPageResult");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.WriteDashboardPageResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", ">writeDashboardPrompt");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.WriteDashboardPrompt.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", ">writeDashboardPromptResult");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.WriteDashboardPromptResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", ">writeDashboardResult");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.WriteDashboardResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", ">writeListFiles");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.WriteListFiles.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", ">writeListFilesResult");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.WriteListFilesResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", ">writeObject");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.WriteObject.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", ">writeObjectResult");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.WriteObjectResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", ">writeObjects");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.WriteObjects.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", ">writeObjectsResult");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.ErrorInfo[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "ErrorInfo");
            qName2 = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "errorInfo");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", ">writeReport");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.WriteReport.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", ">writeReportResult");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.WriteReportResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", ">writeSavedFilter");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.WriteSavedFilter.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", ">writeSavedFilterResult");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.WriteSavedFilterResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "AccessControlToken");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.AccessControlToken.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "Account");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.Account.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "AccountsFilter");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.AccountsFilter.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "ACL");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.ACL.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "AggregationRule");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.AggregationRule.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "arrayOfGUIDs");
            cachedSerQNames.add(qName);
            cls = java.lang.String[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string");
            qName2 = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "GUID");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "AuthResult");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.AuthResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "CatalogItemsFilter");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.CatalogItemsFilter.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "CatalogObject");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.CatalogObject.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "ErrorDetailsLevel");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.ErrorDetailsLevel.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "ErrorInfo");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.ErrorInfo.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "ExportFlags");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.ExportFlags.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "FileInfo");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.FileInfo.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "GetSubItemsFilter");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.NameValuePair[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "NameValuePair");
            qName2 = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "itemInfoFilters");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "GetSubItemsParams");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.GetSubItemsParams.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "ImportError");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.ImportError.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "ImportFlags");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.ImportFlags.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "ItemInfo");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.ItemInfo.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "ItemInfoType");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.ItemInfoType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "JobInfo");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.JobInfo.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "JobStats");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.JobStats.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "LogonParameter");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.LogonParameter.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "NameValueArrayPair");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.NameValueArrayPair.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "NameValuePair");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.NameValuePair.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "OverrideType");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.OverrideType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "PathMap");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.PathMapEntry[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "PathMapEntry");
            qName2 = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "pathMapEntries");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "PathMapEntry");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.PathMapEntry.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "Privilege");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.Privilege.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "QueryResults");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.QueryResults.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "ReportHTMLLinksMode");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.ReportHTMLLinksMode.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "ReportHTMLOptions");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.ReportHTMLOptions.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "ReportParams");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.ReportParams.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "ReportRef");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.ReportRef.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "SAColumn");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.SAColumn.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "SADataType");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.SADataType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "SASubjectArea");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.SASubjectArea.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "SASubjectAreaDetails");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.SASubjectAreaDetails.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "SATable");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.SATable.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "SATableDetails");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.SATableDetails.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "SAWException");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.SAWException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "SAWLocale");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.SAWLocale.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "SAWSessionParameters");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.SAWSessionParameters.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "SegmentationOptions");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.SegmentationOptions.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "SessionEnvironment");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.SessionEnvironment.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "StartPageParams");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.StartPageParams.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

    }
    private void addBindings2() {
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
            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "TemplateInfo");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.TemplateInfo.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "TemplateInfoInstance");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.TemplateInfoInstance.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "TreeNodePath");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.TreeNodePath.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "UpdateACLMode");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.UpdateACLMode.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "UpdateACLParams");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.UpdateACLParams.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "UpdateCatalogItemACLParams");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.UpdateCatalogItemACLParams.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "Variable");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.Variable.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "XMLQueryExecutionOptions");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.XMLQueryExecutionOptions.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "XMLQueryOutputFormat");
            cachedSerQNames.add(qName);
            cls = com.siebel.analytics.web.soap.v5.XMLQueryOutputFormat.class;
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

    public com.siebel.analytics.web.soap.v5.JobInfo writeListFiles(com.siebel.analytics.web.soap.v5.ReportRef report, com.siebel.analytics.web.soap.v5.ReportParams reportParams, java.lang.String segmentPath, com.siebel.analytics.web.soap.v5.TreeNodePath treeNodePath, com.siebel.analytics.web.soap.v5.SegmentationOptions segmentationOptions, java.lang.String filesystem, java.math.BigInteger timeout, java.lang.String sessionID) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[0]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("#writeListFiles");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "writeListFiles"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {report, reportParams, segmentPath, treeNodePath, segmentationOptions, filesystem, timeout, sessionID});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.siebel.analytics.web.soap.v5.JobInfo) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.siebel.analytics.web.soap.v5.JobInfo) org.apache.axis.utils.JavaUtils.convert(_resp, com.siebel.analytics.web.soap.v5.JobInfo.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.siebel.analytics.web.soap.v5.JobInfo getJobInfo(java.math.BigInteger jobID, java.lang.String sessionID) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[1]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("#getJobInfo");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "getJobInfo"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {jobID, sessionID});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.siebel.analytics.web.soap.v5.JobInfo) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.siebel.analytics.web.soap.v5.JobInfo) org.apache.axis.utils.JavaUtils.convert(_resp, com.siebel.analytics.web.soap.v5.JobInfo.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.siebel.analytics.web.soap.v5.JobInfo cancelJob(java.math.BigInteger jobID, java.lang.String sessionID) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[2]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("#cancelJob");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "cancelJob"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {jobID, sessionID});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.siebel.analytics.web.soap.v5.JobInfo) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.siebel.analytics.web.soap.v5.JobInfo) org.apache.axis.utils.JavaUtils.convert(_resp, com.siebel.analytics.web.soap.v5.JobInfo.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.siebel.analytics.web.soap.v5.JobInfo getCounts(java.lang.String segmentPath, java.lang.String treePath, com.siebel.analytics.web.soap.v5.SegmentationOptions segmentationOptions, java.lang.String sessionID) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[3]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("#getCounts");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "getCounts"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {segmentPath, treePath, segmentationOptions, sessionID});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.siebel.analytics.web.soap.v5.JobInfo) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.siebel.analytics.web.soap.v5.JobInfo) org.apache.axis.utils.JavaUtils.convert(_resp, com.siebel.analytics.web.soap.v5.JobInfo.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.siebel.analytics.web.soap.v5.JobInfo purgeCache(java.lang.String segmentPath, java.lang.String treePath, java.lang.Boolean ignoreCacheRef, java.lang.String sessionID) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[4]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("#purgeCache");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "purgeCache"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {segmentPath, treePath, ignoreCacheRef, sessionID});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.siebel.analytics.web.soap.v5.JobInfo) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.siebel.analytics.web.soap.v5.JobInfo) org.apache.axis.utils.JavaUtils.convert(_resp, com.siebel.analytics.web.soap.v5.JobInfo.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.siebel.analytics.web.soap.v5.JobInfo prepareCache(java.lang.String segmentPath, java.lang.String treePath, java.lang.Boolean refresh, java.lang.String sessionID) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[5]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("#prepareCache");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "prepareCache"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {segmentPath, treePath, refresh, sessionID});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.siebel.analytics.web.soap.v5.JobInfo) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.siebel.analytics.web.soap.v5.JobInfo) org.apache.axis.utils.JavaUtils.convert(_resp, com.siebel.analytics.web.soap.v5.JobInfo.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.siebel.analytics.web.soap.v5.JobInfo saveResultSet(java.lang.String segmentPath, com.siebel.analytics.web.soap.v5.TreeNodePath treeNodePath, java.lang.String savedSegmentPath, com.siebel.analytics.web.soap.v5.SegmentationOptions segmentationOptions, java.lang.String SRCustomLabel, java.lang.Boolean appendStaticSegment, java.lang.String sessionID) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[6]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("#saveResultSet");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "saveResultSet"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {segmentPath, treeNodePath, savedSegmentPath, segmentationOptions, SRCustomLabel, appendStaticSegment, sessionID});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.siebel.analytics.web.soap.v5.JobInfo) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.siebel.analytics.web.soap.v5.JobInfo) org.apache.axis.utils.JavaUtils.convert(_resp, com.siebel.analytics.web.soap.v5.JobInfo.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.siebel.analytics.web.soap.v5.JobInfo deleteResultSet(java.lang.String targetLevel, java.lang.String[] GUIDs, java.lang.String segmentPath, java.lang.String sessionID) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[7]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("#deleteResultSet");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "deleteResultSet"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {targetLevel, GUIDs, segmentPath, sessionID});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.siebel.analytics.web.soap.v5.JobInfo) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.siebel.analytics.web.soap.v5.JobInfo) org.apache.axis.utils.JavaUtils.convert(_resp, com.siebel.analytics.web.soap.v5.JobInfo.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

}
