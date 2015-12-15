/**
 * Batch_Job_AdminStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.businessobjects.www;

public class Batch_Job_AdminStub extends org.apache.axis.client.Stub implements com.businessobjects.www.Batch_Job_Admin {
    private java.util.Vector cachedSerClasses = new java.util.Vector();
    private java.util.Vector cachedSerQNames = new java.util.Vector();
    private java.util.Vector cachedSerFactories = new java.util.Vector();
    private java.util.Vector cachedDeserFactories = new java.util.Vector();

    static org.apache.axis.description.OperationDesc [] _operations;

    static {
        _operations = new org.apache.axis.description.OperationDesc[9];
        _initOperationDesc1();
    }

    private static void _initOperationDesc1(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("Get_Error_Log");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.businessobjects.com/DataServices/ServerX.xsd", "ErrorLogRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.businessobjects.com/DataServices/ServerX.xsd", ">ErrorLogRequest"), com.businessobjects.www.DataServices.ServerX_xsd.ErrorLogRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.businessobjects.com/DataServices/ServerX.xsd", ">ErrorLogResponse"));
        oper.setReturnClass(com.businessobjects.www.DataServices.ServerX_xsd.ErrorLogResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.businessobjects.com/DataServices/ServerX.xsd", "ErrorLogResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[0] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("Get_Trace_Log");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.businessobjects.com/DataServices/ServerX.xsd", "TraceLogRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.businessobjects.com/DataServices/ServerX.xsd", ">TraceLogRequest"), com.businessobjects.www.DataServices.ServerX_xsd.TraceLogRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.businessobjects.com/DataServices/ServerX.xsd", ">TraceLogResponse"));
        oper.setReturnClass(com.businessobjects.www.DataServices.ServerX_xsd.TraceLogResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.businessobjects.com/DataServices/ServerX.xsd", "TraceLogResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[1] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("Get_Monitor_Log");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.businessobjects.com/DataServices/ServerX.xsd", "MonitorLogRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.businessobjects.com/DataServices/ServerX.xsd", ">MonitorLogRequest"), com.businessobjects.www.DataServices.ServerX_xsd.MonitorLogRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.businessobjects.com/DataServices/ServerX.xsd", ">MonitorLogResponse"));
        oper.setReturnClass(com.businessobjects.www.DataServices.ServerX_xsd.MonitorLogResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.businessobjects.com/DataServices/ServerX.xsd", "MonitorLogResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[2] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("Get_BatchJob_Status");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.businessobjects.com/DataServices/ServerX.xsd", "batchJobStatusRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.businessobjects.com/DataServices/ServerX.xsd", ">batchJobStatusRequest"), com.businessobjects.www.DataServices.ServerX_xsd.BatchJobStatusRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.businessobjects.com/DataServices/ServerX.xsd", ">batchJobStatusResponse"));
        oper.setReturnClass(com.businessobjects.www.DataServices.ServerX_xsd.BatchJobStatusResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.businessobjects.com/DataServices/ServerX.xsd", "batchJobStatusResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[3] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("Stop_Batch_Job");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.businessobjects.com/DataServices/ServerX.xsd", "stopBatchJobRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.businessobjects.com/DataServices/ServerX.xsd", ">stopBatchJobRequest"), com.businessobjects.www.DataServices.ServerX_xsd.StopBatchJobRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.businessobjects.com/DataServices/ServerX.xsd", ">stopBatchJobResponse"));
        oper.setReturnClass(com.businessobjects.www.DataServices.ServerX_xsd.StopBatchJobResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.businessobjects.com/DataServices/ServerX.xsd", "stopBatchJobResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[4] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("Get_BatchJob_RunIDs");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.businessobjects.com/DataServices/ServerX.xsd", "batchJobRunIDsRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.businessobjects.com/DataServices/ServerX.xsd", ">batchJobRunIDsRequest"), com.businessobjects.www.DataServices.ServerX_xsd.BatchJobRunIDsRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.businessobjects.com/DataServices/ServerX.xsd", ">batchJobRunIDsResponse"));
        oper.setReturnClass(com.businessobjects.www.DataServices.ServerX_xsd.BatchJobRunIDsResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.businessobjects.com/DataServices/ServerX.xsd", "batchJobRunIDsResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[5] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("Get_BatchJob_List");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.businessobjects.com/DataServices/ServerX.xsd", "GetListOfBatchJobsRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.businessobjects.com/DataServices/ServerX.xsd", ">GetListOfBatchJobsRequest"), com.businessobjects.www.DataServices.ServerX_xsd.GetListOfBatchJobsRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.businessobjects.com/DataServices/ServerX.xsd", ">ListOfBatchJobsResponse"));
        oper.setReturnClass(com.businessobjects.www.DataServices.ServerX_xsd.ListOfBatchJobsResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.businessobjects.com/DataServices/ServerX.xsd", "ListOfBatchJobsResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[6] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("Run_Batch_Job");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.businessobjects.com/DataServices/ServerX.xsd", "RunBatchJobRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.businessobjects.com/DataServices/ServerX.xsd", ">RunBatchJobRequest"), com.businessobjects.www.DataServices.ServerX_xsd.RunBatchJobRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.businessobjects.com/DataServices/ServerX.xsd", ">BatchJobResponse"));
        oper.setReturnClass(com.businessobjects.www.DataServices.ServerX_xsd.BatchJobResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.businessobjects.com/DataServices/ServerX.xsd", "BatchJobResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[7] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("Get_Job_Input_Format");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.businessobjects.com/DataServices/ServerX.xsd", "GetBatchJobInputFormatRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.businessobjects.com/DataServices/ServerX.xsd", ">GetBatchJobInputFormatRequest"), com.businessobjects.www.DataServices.ServerX_xsd.GetBatchJobInputFormatRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.businessobjects.com/DataServices/ServerX.xsd", ">GetBatchJobInputFormatResponse"));
        oper.setReturnClass(com.businessobjects.www.DataServices.ServerX_xsd.GetBatchJobInputFormatResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.businessobjects.com/DataServices/ServerX.xsd", "GetBatchJobInputFormatResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[8] = oper;

    }

    public Batch_Job_AdminStub() throws org.apache.axis.AxisFault {
         this(null);
    }

    public Batch_Job_AdminStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
         this(service);
         super.cachedEndpoint = endpointURL;
    }

    public Batch_Job_AdminStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
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
            qName = new javax.xml.namespace.QName("http://www.businessobjects.com/DataServices/ServerX.xsd", ">>>RunBatchJobRequest>globalVariables>variable");
            cachedSerQNames.add(qName);
            cls = com.businessobjects.www.DataServices.ServerX_xsd.RunBatchJobRequestGlobalVariablesVariable.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.businessobjects.com/DataServices/ServerX.xsd", ">>batchJobRunIDsResponse>run");
            cachedSerQNames.add(qName);
            cls = com.businessobjects.www.DataServices.ServerX_xsd.BatchJobRunIDsResponseRun.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.businessobjects.com/DataServices/ServerX.xsd", ">>job_param_type>substitutionParameters>parameter");
            cachedSerQNames.add(qName);
            cls = com.businessobjects.www.DataServices.ServerX_xsd.Job_param_typeSubstitutionParametersParameter.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.businessobjects.com/DataServices/ServerX.xsd", ">>ListOfBatchJobsResponse>jobName");
            cachedSerQNames.add(qName);
            cls = com.businessobjects.www.DataServices.ServerX_xsd.ListOfBatchJobsResponseJobName.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.businessobjects.com/DataServices/ServerX.xsd", ">>RunBatchJobRequest>globalVariables");
            cachedSerQNames.add(qName);
            cls = com.businessobjects.www.DataServices.ServerX_xsd.RunBatchJobRequestGlobalVariablesVariable[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.businessobjects.com/DataServices/ServerX.xsd", ">>>RunBatchJobRequest>globalVariables>variable");
            qName2 = new javax.xml.namespace.QName("", "variable");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.businessobjects.com/DataServices/ServerX.xsd", ">BatchJobResponse");
            cachedSerQNames.add(qName);
            cls = com.businessobjects.www.DataServices.ServerX_xsd.BatchJobResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.businessobjects.com/DataServices/ServerX.xsd", ">batchJobRunIDsRequest");
            cachedSerQNames.add(qName);
            cls = com.businessobjects.www.DataServices.ServerX_xsd.BatchJobRunIDsRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.businessobjects.com/DataServices/ServerX.xsd", ">batchJobRunIDsResponse");
            cachedSerQNames.add(qName);
            cls = com.businessobjects.www.DataServices.ServerX_xsd.BatchJobRunIDsResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.businessobjects.com/DataServices/ServerX.xsd", ">batchJobStatusRequest");
            cachedSerQNames.add(qName);
            cls = com.businessobjects.www.DataServices.ServerX_xsd.BatchJobStatusRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.businessobjects.com/DataServices/ServerX.xsd", ">batchJobStatusResponse");
            cachedSerQNames.add(qName);
            cls = com.businessobjects.www.DataServices.ServerX_xsd.BatchJobStatusResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.businessobjects.com/DataServices/ServerX.xsd", ">ErrorLogRequest");
            cachedSerQNames.add(qName);
            cls = com.businessobjects.www.DataServices.ServerX_xsd.ErrorLogRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.businessobjects.com/DataServices/ServerX.xsd", ">ErrorLogResponse");
            cachedSerQNames.add(qName);
            cls = com.businessobjects.www.DataServices.ServerX_xsd.ErrorLogResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.businessobjects.com/DataServices/ServerX.xsd", ">GetBatchJobInputFormatRequest");
            cachedSerQNames.add(qName);
            cls = com.businessobjects.www.DataServices.ServerX_xsd.GetBatchJobInputFormatRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.businessobjects.com/DataServices/ServerX.xsd", ">GetBatchJobInputFormatResponse");
            cachedSerQNames.add(qName);
            cls = com.businessobjects.www.DataServices.ServerX_xsd.GetBatchJobInputFormatResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.businessobjects.com/DataServices/ServerX.xsd", ">GetListOfBatchJobsRequest");
            cachedSerQNames.add(qName);
            cls = com.businessobjects.www.DataServices.ServerX_xsd.GetListOfBatchJobsRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.businessobjects.com/DataServices/ServerX.xsd", ">job_param_type>distribution_level");
            cachedSerQNames.add(qName);
            cls = com.businessobjects.www.DataServices.ServerX_xsd.Job_param_typeDistribution_level.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("http://www.businessobjects.com/DataServices/ServerX.xsd", ">job_param_type>substitutionParameters");
            cachedSerQNames.add(qName);
            cls = com.businessobjects.www.DataServices.ServerX_xsd.Job_param_typeSubstitutionParametersParameter[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.businessobjects.com/DataServices/ServerX.xsd", ">>job_param_type>substitutionParameters>parameter");
            qName2 = new javax.xml.namespace.QName("", "parameter");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.businessobjects.com/DataServices/ServerX.xsd", ">ListOfBatchJobsResponse");
            cachedSerQNames.add(qName);
            cls = com.businessobjects.www.DataServices.ServerX_xsd.ListOfBatchJobsResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.businessobjects.com/DataServices/ServerX.xsd", ">MonitorLogRequest");
            cachedSerQNames.add(qName);
            cls = com.businessobjects.www.DataServices.ServerX_xsd.MonitorLogRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.businessobjects.com/DataServices/ServerX.xsd", ">MonitorLogResponse");
            cachedSerQNames.add(qName);
            cls = com.businessobjects.www.DataServices.ServerX_xsd.MonitorLogResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.businessobjects.com/DataServices/ServerX.xsd", ">RunBatchJobRequest");
            cachedSerQNames.add(qName);
            cls = com.businessobjects.www.DataServices.ServerX_xsd.RunBatchJobRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.businessobjects.com/DataServices/ServerX.xsd", ">stopBatchJobRequest");
            cachedSerQNames.add(qName);
            cls = com.businessobjects.www.DataServices.ServerX_xsd.StopBatchJobRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.businessobjects.com/DataServices/ServerX.xsd", ">stopBatchJobResponse");
            cachedSerQNames.add(qName);
            cls = com.businessobjects.www.DataServices.ServerX_xsd.StopBatchJobResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.businessobjects.com/DataServices/ServerX.xsd", ">TraceLogRequest");
            cachedSerQNames.add(qName);
            cls = com.businessobjects.www.DataServices.ServerX_xsd.TraceLogRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.businessobjects.com/DataServices/ServerX.xsd", ">TraceLogResponse");
            cachedSerQNames.add(qName);
            cls = com.businessobjects.www.DataServices.ServerX_xsd.TraceLogResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.businessobjects.com/DataServices/ServerX.xsd", "job_param_type");
            cachedSerQNames.add(qName);
            cls = com.businessobjects.www.DataServices.ServerX_xsd.Job_param_type.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.businessobjects.com/DataServices/ServerX.xsd", "TracingTypeType");
            cachedSerQNames.add(qName);
            cls = com.businessobjects.www.DataServices.ServerX_xsd.TracingTypeType.class;
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

    public com.businessobjects.www.DataServices.ServerX_xsd.ErrorLogResponse get_Error_Log(com.businessobjects.www.DataServices.ServerX_xsd.ErrorLogRequest errorLogRequest) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[0]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("jobAdmin=Get_Error_Log");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "Get_Error_Log"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {errorLogRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.businessobjects.www.DataServices.ServerX_xsd.ErrorLogResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.businessobjects.www.DataServices.ServerX_xsd.ErrorLogResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.businessobjects.www.DataServices.ServerX_xsd.ErrorLogResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.businessobjects.www.DataServices.ServerX_xsd.TraceLogResponse get_Trace_Log(com.businessobjects.www.DataServices.ServerX_xsd.TraceLogRequest traceLogRequest) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[1]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("jobAdmin=Get_Trace_Log");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "Get_Trace_Log"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {traceLogRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.businessobjects.www.DataServices.ServerX_xsd.TraceLogResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.businessobjects.www.DataServices.ServerX_xsd.TraceLogResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.businessobjects.www.DataServices.ServerX_xsd.TraceLogResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.businessobjects.www.DataServices.ServerX_xsd.MonitorLogResponse get_Monitor_Log(com.businessobjects.www.DataServices.ServerX_xsd.MonitorLogRequest monitorLogRequest) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[2]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("jobAdmin=Get_Monitor_Log");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "Get_Monitor_Log"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {monitorLogRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.businessobjects.www.DataServices.ServerX_xsd.MonitorLogResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.businessobjects.www.DataServices.ServerX_xsd.MonitorLogResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.businessobjects.www.DataServices.ServerX_xsd.MonitorLogResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.businessobjects.www.DataServices.ServerX_xsd.BatchJobStatusResponse get_BatchJob_Status(com.businessobjects.www.DataServices.ServerX_xsd.BatchJobStatusRequest batchJobStatusRequest) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[3]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("jobAdmin=Get_BatchJob_Status");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "Get_BatchJob_Status"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {batchJobStatusRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.businessobjects.www.DataServices.ServerX_xsd.BatchJobStatusResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.businessobjects.www.DataServices.ServerX_xsd.BatchJobStatusResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.businessobjects.www.DataServices.ServerX_xsd.BatchJobStatusResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.businessobjects.www.DataServices.ServerX_xsd.StopBatchJobResponse stop_Batch_Job(com.businessobjects.www.DataServices.ServerX_xsd.StopBatchJobRequest stopBatchJobRequest) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[4]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("jobAdmin=Stop_Batch_Job");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "Stop_Batch_Job"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {stopBatchJobRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.businessobjects.www.DataServices.ServerX_xsd.StopBatchJobResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.businessobjects.www.DataServices.ServerX_xsd.StopBatchJobResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.businessobjects.www.DataServices.ServerX_xsd.StopBatchJobResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.businessobjects.www.DataServices.ServerX_xsd.BatchJobRunIDsResponse get_BatchJob_RunIDs(com.businessobjects.www.DataServices.ServerX_xsd.BatchJobRunIDsRequest batchJobRunIDsRequest) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[5]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("jobAdmin=Get_BatchJob_RunIDs");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "Get_BatchJob_RunIDs"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {batchJobRunIDsRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.businessobjects.www.DataServices.ServerX_xsd.BatchJobRunIDsResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.businessobjects.www.DataServices.ServerX_xsd.BatchJobRunIDsResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.businessobjects.www.DataServices.ServerX_xsd.BatchJobRunIDsResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.businessobjects.www.DataServices.ServerX_xsd.ListOfBatchJobsResponse get_BatchJob_List(com.businessobjects.www.DataServices.ServerX_xsd.GetListOfBatchJobsRequest getListOfBatchJobsRequest) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[6]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("jobAdmin=Get_BatchJob_List");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "Get_BatchJob_List"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {getListOfBatchJobsRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.businessobjects.www.DataServices.ServerX_xsd.ListOfBatchJobsResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.businessobjects.www.DataServices.ServerX_xsd.ListOfBatchJobsResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.businessobjects.www.DataServices.ServerX_xsd.ListOfBatchJobsResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.businessobjects.www.DataServices.ServerX_xsd.BatchJobResponse run_Batch_Job(com.businessobjects.www.DataServices.ServerX_xsd.RunBatchJobRequest runBatchJobRequest) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[7]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("jobAdmin=Run_Batch_Job");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "Run_Batch_Job"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {runBatchJobRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.businessobjects.www.DataServices.ServerX_xsd.BatchJobResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.businessobjects.www.DataServices.ServerX_xsd.BatchJobResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.businessobjects.www.DataServices.ServerX_xsd.BatchJobResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.businessobjects.www.DataServices.ServerX_xsd.GetBatchJobInputFormatResponse get_Job_Input_Format(com.businessobjects.www.DataServices.ServerX_xsd.GetBatchJobInputFormatRequest getBatchJobInputFormatRequest) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[8]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("jobAdmin=Get_Job_Input_Format");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "Get_Job_Input_Format"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {getBatchJobInputFormatRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.businessobjects.www.DataServices.ServerX_xsd.GetBatchJobInputFormatResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.businessobjects.www.DataServices.ServerX_xsd.GetBatchJobInputFormatResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.businessobjects.www.DataServices.ServerX_xsd.GetBatchJobInputFormatResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

}
