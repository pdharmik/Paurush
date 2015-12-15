/**
 * ServiceRequest_serviceRequestWS_BinderStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client;

public class ServiceRequest_serviceRequestWS_BinderStub extends org.apache.axis.client.Stub implements com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.ServiceRequestWS_PortType {
    private java.util.Vector cachedSerClasses = new java.util.Vector();
    private java.util.Vector cachedSerQNames = new java.util.Vector();
    private java.util.Vector cachedSerFactories = new java.util.Vector();
    private java.util.Vector cachedDeserFactories = new java.util.Vector();

    static org.apache.axis.description.OperationDesc [] _operations;

    static {
        _operations = new org.apache.axis.description.OperationDesc[16];
        _initOperationDesc1();
        _initOperationDesc2();
    }

    private static void _initOperationDesc1(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("cancelServiceAppointmentOnReschedule");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "cancelServiceAppointmentReschedule"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "cancelServiceAppointmentReschedule"), com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.CancelServiceAppointmentReschedule.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "errorCode"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "message"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[0] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("cancelServiceAppointmentRequestList");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "cancelServiceAppointmentRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "cancelServiceAppointmentRequest"), com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.CancelServiceAppointmentRequest.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "errorCode"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "message"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[1] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("cancelServiceRequestAppointment");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "cancelServiceAppointment"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "cancelServiceAppointment"), com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.CancelServiceAppointment.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "errorCode"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "message"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[2] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("confirmServiceAppointment");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "confirmServiceAppointment"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "confirmServiceAppointment2"), com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.ConfirmServiceAppointment2.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "errorCode"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "serviceRequestDetailsOutput"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "serviceRequestDetailsOutput"), com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.ServiceRequestDetailsOutput.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "message"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[3] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getServiceAppointment");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "serviceAppointment"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "serviceAppointment"), com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.ServiceAppointment.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "debug"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "serviceAppointmentTimeSlots"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "serviceAppointmentTimeSlots"), com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.ServiceAppointmentTimeSlots.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "sessionRequestId"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "errorCode"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "message"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[4] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getSRAndActivityDetails");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "debug"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "getServiceRequestAndActivityDetails"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "serviceRequestAndActivityDetailsInput"), com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.ServiceRequestAndActivityDetailsInput.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "serviceRequestDetailsOutput"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "serviceRequestDetailsOutput"), com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.ServiceRequestDetailsOutput.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "errorCode"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "message"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[5] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("updateServiceRequestActivity");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "debug"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "synchOrAsynch"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "updateServiceRequestActivityInput"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "updateServiceRequestActivityInput"), com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.UpdateServiceRequestActivityInput.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        oper.setReturnClass(java.lang.String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "status"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[6] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("debriefServiceRequestActivity");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "debug"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "synchOrAsynch"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "debriefServiceRequestActivityInput"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "debriefServiceRequestActivityInput"), com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.DebriefServiceRequestActivityInput.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        oper.setReturnClass(java.lang.String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "status"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[7] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("cancelServiceRequest");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "cancelServiceRequestWSInput"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "cancelServiceRequestWSInput"), com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.CancelServiceRequestWSInput.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "debug"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        oper.setReturnClass(java.lang.String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "cancelServiceRequestResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[8] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getServiceRequestStatus");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "getServiceRequestStatus"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "getServiceRequestStatusInput"), com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.GetServiceRequestStatusInput.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "debug"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "statusServiceRequestWSOutput"));
        oper.setReturnClass(com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.StatusServiceRequestWSOutput.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "statusServiceRequestWSOutput"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[9] = oper;

    }

    private static void _initOperationDesc2(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("createServiceRequest");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "debug"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "ConsumablesServiceRequestWSInput"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "ConsumablesServiceRequestWSInput"), com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.ConsumablesServiceRequestWSInput.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[10] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("createChangeManagementServiceRequest");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "debug"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "ChangeManagementServiceRequestWSInput"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "ChangeManagementServiceRequestWSInput"), com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.ChangeManagementServiceRequestWSInput.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "synchOrAsynch"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "serviceRequestDetails"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "serviceRequestDetailsOutput"), com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.ServiceRequestDetailsOutput.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "srRowId"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "srNumber"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[11] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("escalateServiceRequest");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "escalateServiceRequestWSInput"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "escalateServiceRequestWSInput"), com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.EscalateServiceRequestWSInput.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "debug"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        oper.setReturnClass(java.lang.String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "escalateServiceRequestResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[12] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("createConsumablesServiceRequest");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "debug"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "ConsumablesServiceRequestWSInput"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "ConsumablesServiceRequestWSInput"), com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.ConsumablesServiceRequestWSInput.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "synchOrAsynch"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "serviceRequestDetailsOutput"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "serviceRequestDetailsOutput"), com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.ServiceRequestDetailsOutput.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "sRNumber"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "sRRowId"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[13] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("updateConsumablesServiceRequest");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "debug"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "ConsumablesServiceRequestWSInput"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "ConsumablesServiceRequestWSInput"), com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.ConsumablesServiceRequestWSInput.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[14] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("addressCleanse");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "AddressCleanseWSInput"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "AddressCleanseWSInput"), com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.AddressCleanseWSInput.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "AddressCleanseWSOutput"));
        oper.setReturnClass(com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.AddressCleanseWSOutput.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "AddressCleanseWSOutput"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[15] = oper;

    }

    public ServiceRequest_serviceRequestWS_BinderStub() throws org.apache.axis.AxisFault {
         this(null);
    }

    public ServiceRequest_serviceRequestWS_BinderStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
         this(service);
         super.cachedEndpoint = endpointURL;
    }

    public ServiceRequest_serviceRequestWS_BinderStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
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
            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "Account");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.Account.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "Account2");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.Account2.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "Account3");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.Account3.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "Account6");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.Account6.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "AccountAddress");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.AccountAddress.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "AccountAddress2");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.AccountAddress2.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "AccountAddress3");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.AccountAddress3.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "AccountAddress6");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.AccountAddress6.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "AccountInformation");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.AccountInformation.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "AccountInformation2");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.AccountInformation2.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "ActivityDetails");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.ActivityDetails.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "ActivityNotes");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.ActivityNotes.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "ActivityNotes2");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.ActivityNotes2.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "addedPropSet");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.AddedPropSet.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "AddressCleanseWSInput");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.AddressCleanseWSInput.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "AddressCleanseWSOutput");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.AddressCleanseWSOutput.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "AddressData");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.AddressData.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "AddressData2");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.AddressData2.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "AddressWSInput");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.AddressWSInput.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "AddressWSOutput");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.AddressWSOutput.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "ArrayOfActivityDetails");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.ActivityDetails[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "ActivityDetails");
            qName2 = new javax.xml.namespace.QName("", "ArrayOfActivityDetailsItem");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "ArrayOfActivityNotes");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.ActivityNotes[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "ActivityNotes");
            qName2 = new javax.xml.namespace.QName("", "ArrayOfActivityNotesItem");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "ArrayOfActivityNotes2");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.ActivityNotes2[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "ActivityNotes2");
            qName2 = new javax.xml.namespace.QName("", "ArrayOfActivityNotes2Item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "ArrayOfMaterialDetails");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.MaterialDetails[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "MaterialDetails");
            qName2 = new javax.xml.namespace.QName("", "ArrayOfMaterialDetailsItem");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "ArrayOfPageCountData");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.PageCountData[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "PageCountData");
            qName2 = new javax.xml.namespace.QName("", "ArrayOfPageCountDataItem");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "ArrayOfPaymentRequestList");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.PaymentRequestList[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "PaymentRequestList");
            qName2 = new javax.xml.namespace.QName("", "ArrayOfPaymentRequestListItem");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "ArrayOfRecommendedPartsList");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.RecommendedPartsList[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "RecommendedPartsList");
            qName2 = new javax.xml.namespace.QName("", "ArrayOfRecommendedPartsListItem");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "ArrayOfRecommendedPartsList3");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.RecommendedPartsList3[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "RecommendedPartsList3");
            qName2 = new javax.xml.namespace.QName("", "ArrayOfRecommendedPartsList3Item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "ArrayOfReturnPartsList");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.ReturnPartsList[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "ReturnPartsList");
            qName2 = new javax.xml.namespace.QName("", "ArrayOfReturnPartsListItem");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "ArrayOfReturnPartsList2");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.ReturnPartsList2[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "ReturnPartsList2");
            qName2 = new javax.xml.namespace.QName("", "ArrayOfReturnPartsList2Item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "ArrayOfSelectedServiceDetails");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.SelectedServiceDetails[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "SelectedServiceDetails");
            qName2 = new javax.xml.namespace.QName("", "ArrayOfSelectedServiceDetailsItem");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "ArrayOfShipmentInformation");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.ShipmentInformation[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "ShipmentInformation");
            qName2 = new javax.xml.namespace.QName("", "ArrayOfShipmentInformationItem");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "ArrayOfTechnicianInstructions");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.TechnicianInstructions[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "TechnicianInstructions");
            qName2 = new javax.xml.namespace.QName("", "ArrayOfTechnicianInstructionsItem");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "ArrayOfTechnicianInstructions2");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.TechnicianInstructions2[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "TechnicianInstructions2");
            qName2 = new javax.xml.namespace.QName("", "ArrayOfTechnicianInstructions2Item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "ArrayOfTimeSlot");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.TimeSlot[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "TimeSlot");
            qName2 = new javax.xml.namespace.QName("", "ArrayOfTimeSlotItem");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "AssetContact");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.AssetContact.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "AssetContact2");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.AssetContact2.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "AssetContact3");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.AssetContact3.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "AssetContact6");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.AssetContact6.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "AssetInformation");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.AssetInformation.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "AssetInformation2");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.AssetInformation2.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "AssetInformation3");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.AssetInformation3.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "AssetInformation5");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.AssetInformation5.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "cancelServiceAppointment");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.CancelServiceAppointment.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "cancelServiceAppointmentData");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.CancelServiceAppointmentData.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "cancelServiceAppointmentData2");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.CancelServiceAppointmentData2.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "cancelServiceAppointmentRequest");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.CancelServiceAppointmentRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "cancelServiceAppointmentRequestListWSInput");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.CancelServiceAppointmentRequestListWSInput.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "cancelServiceAppointmentReschedule");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.CancelServiceAppointmentReschedule.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "cancelServiceAppointmentRescheduleData");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.CancelServiceAppointmentRescheduleData.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "cancelServiceAppointmentRescheduleWSInput");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.CancelServiceAppointmentRescheduleWSInput.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "cancelServiceAppointmentWSInput");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.CancelServiceAppointmentWSInput.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "cancelServiceRequestData");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.CancelServiceRequestData.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "cancelServiceRequestWSInput");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.CancelServiceRequestWSInput.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "cancelServiceRequestWSInput2");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.CancelServiceRequestWSInput2.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "ChangeManagementServiceRequestData");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.ChangeManagementServiceRequestData.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "ChangeManagementServiceRequestWSInput");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.ChangeManagementServiceRequestWSInput.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "ChangeManagementServiceRequestWSInput2");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.ChangeManagementServiceRequestWSInput2.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "confirmServiceAppointment2");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.ConfirmServiceAppointment2.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "confirmServiceAppointmentData");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.ConfirmServiceAppointmentData.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "confirmServiceAppointmentWSInput");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.ConfirmServiceAppointmentWSInput.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "ConsumablesServiceRequestData");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.ConsumablesServiceRequestData.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "ConsumablesServiceRequestWSInput");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.ConsumablesServiceRequestWSInput.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "ConsumablesServiceRequestWSInput2");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.ConsumablesServiceRequestWSInput2.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "CustomerInformation");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.CustomerInformation.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "CustomerInformation2");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.CustomerInformation2.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "CustomerInformation4");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.CustomerInformation4.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "DebriefDetails");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.DebriefDetails.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "DebriefInformation");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.DebriefInformation.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "debriefServiceRequestActivityInput");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.DebriefServiceRequestActivityInput.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "debriefServiceRequestActivityWSInput");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.DebriefServiceRequestActivityWSInput.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "DocumentMetaData");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.DocumentMetaData.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "DocumentMetaData10");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.DocumentMetaData10.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "DocumentMetaData11");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.DocumentMetaData11.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "DocumentMetaData12");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.DocumentMetaData12.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "DocumentMetaData14");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.DocumentMetaData14.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "DocumentMetaData15");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.DocumentMetaData15.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "DocumentMetaData16");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.DocumentMetaData16.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "DocumentMetaData2");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.DocumentMetaData2.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "DocumentMetaData3");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.DocumentMetaData3.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "DocumentMetaData4");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.DocumentMetaData4.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "DocumentMetaData5");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.DocumentMetaData5.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "DocumentMetaData6");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.DocumentMetaData6.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "DocumentMetaData7");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.DocumentMetaData7.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "DocumentMetaData8");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.DocumentMetaData8.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "DocumentMetaData9");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.DocumentMetaData9.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "Entitlement");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.Entitlement.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "Entitlement2");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.Entitlement2.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "Entitlement3");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.Entitlement3.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "Entitlement4");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.Entitlement4.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "Entitlement8");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.Entitlement8.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "EntitlementServiceDetails");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.EntitlementServiceDetails.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "EntitlementServiceDetails2");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.EntitlementServiceDetails2.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "EntitlementServiceDetails3");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.EntitlementServiceDetails3.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "EntitlementServiceDetails4");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.EntitlementServiceDetails4.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "EntitlementServiceDetails8");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.EntitlementServiceDetails8.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "escalateServiceRequestData");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.EscalateServiceRequestData.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "escalateServiceRequestWSInput");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.EscalateServiceRequestWSInput.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "escalateServiceRequestWSInput2");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.EscalateServiceRequestWSInput2.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "getServiceAppointmentData");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.GetServiceAppointmentData.class;
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
            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "getServiceAppointmentWSInput");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.GetServiceAppointmentWSInput.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "getServiceRequestAndActivityDetails");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.GetServiceRequestAndActivityDetails.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "getServiceRequestAndActivityDetailsData");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.GetServiceRequestAndActivityDetailsData.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "getServiceRequestStatusData");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.GetServiceRequestStatusData.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "getServiceRequestStatusInput");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.GetServiceRequestStatusInput.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "getServiceRequestStatusWSInput");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.GetServiceRequestStatusWSInput.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "InstalledAtAddress");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.InstalledAtAddress.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "InstalledAtAddress2");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.InstalledAtAddress2.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "InstalledAtAddress3");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.InstalledAtAddress3.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "InstalledAtAddress6");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.InstalledAtAddress6.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "MaterialDetails");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.MaterialDetails.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "NoteAuthor");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.NoteAuthor.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "NoteAuthor2");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.NoteAuthor2.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "OrderInformation");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.OrderInformation.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "OriginalDeviceInformation");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.OriginalDeviceInformation.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "PageCountData");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.PageCountData.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "PartInfromation");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.PartInfromation.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "PartInfromation2");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.PartInfromation2.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "PartInfromation3");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.PartInfromation3.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "PartInfromation4");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.PartInfromation4.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "PaymentInformation");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.PaymentInformation.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "PaymentRequestList");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.PaymentRequestList.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "PrimaryContact");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.PrimaryContact.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "PrimaryContact2");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.PrimaryContact2.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "PrimaryContact3");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.PrimaryContact3.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "PrimaryContact4");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.PrimaryContact4.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "PrimaryContact6");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.PrimaryContact6.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "RecommendedPartsList");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.RecommendedPartsList.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "RecommendedPartsList3");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.RecommendedPartsList3.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "ReplacementDeviceInformation");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.ReplacementDeviceInformation.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "Requester");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.Requester.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "Requester2");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.Requester2.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "Requester3");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.Requester3.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "Requester4");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.Requester4.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "Requester6");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.Requester6.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "ReturnedAssetInformation");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.ReturnedAssetInformation.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "ReturnPartsList");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.ReturnPartsList.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "ReturnPartsList2");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.ReturnPartsList2.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "SecondaryContact");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.SecondaryContact.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "SecondaryContact2");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.SecondaryContact2.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "SecondaryContact3");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.SecondaryContact3.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "SecondaryContact4");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.SecondaryContact4.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "SecondaryContact6");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.SecondaryContact6.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "SelectedServiceDetails");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.SelectedServiceDetails.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "ServiceAddress");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.ServiceAddress.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "ServiceAddress2");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.ServiceAddress2.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "ServiceAddress4");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.ServiceAddress4.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "serviceAppointment");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.ServiceAppointment.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "serviceAppointmentTimeSlots");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.ServiceAppointmentTimeSlots.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "ServiceProviderInformation");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.ServiceProviderInformation.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "ServiceProviderInformation2");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.ServiceProviderInformation2.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "serviceRequestAndActivityDetailsInput");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.ServiceRequestAndActivityDetailsInput.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "ServiceRequestData");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.ServiceRequestData.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "ServiceRequestData2");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.ServiceRequestData2.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "ServiceRequestData4");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.ServiceRequestData4.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "ServiceRequestDetails");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.ServiceRequestDetails.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "serviceRequestDetailsOutput");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.ServiceRequestDetailsOutput.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "ServiceRequestWSOutput");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.ServiceRequestWSOutput.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "ShipmentInformation");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.ShipmentInformation.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "ShipToAddress");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.ShipToAddress.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "ShipToAddress2");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.ShipToAddress2.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "ShipToAddress3");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.ShipToAddress3.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "StatusServiceRequestData");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.StatusServiceRequestData.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "statusServiceRequestWSOutput");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.StatusServiceRequestWSOutput.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "statusServiceRequestWSOutput2");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.StatusServiceRequestWSOutput2.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "Technician");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.Technician.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "Technician2");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.Technician2.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "TechnicianInstructions");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.TechnicianInstructions.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "TechnicianInstructions2");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.TechnicianInstructions2.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "TimeSlot");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.TimeSlot.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "updateServiceRequestActivityInput");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.UpdateServiceRequestActivityInput.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "updateServiceRequestActivityWSInput");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.UpdateServiceRequestActivityWSInput.class;
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

    public void cancelServiceAppointmentOnReschedule(com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.CancelServiceAppointmentReschedule cancelServiceAppointmentReschedule, javax.xml.rpc.holders.StringHolder errorCode, javax.xml.rpc.holders.StringHolder message) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[0]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("ServiceRequest_serviceRequestWS_Binder_cancelServiceAppointmentOnReschedule");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "cancelServiceAppointmentOnReschedule"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {cancelServiceAppointmentReschedule});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            java.util.Map _output;
            _output = _call.getOutputParams();
            try {
                errorCode.value = (java.lang.String) _output.get(new javax.xml.namespace.QName("", "errorCode"));
            } catch (java.lang.Exception _exception) {
                errorCode.value = (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "errorCode")), java.lang.String.class);
            }
            try {
                message.value = (java.lang.String) _output.get(new javax.xml.namespace.QName("", "message"));
            } catch (java.lang.Exception _exception) {
                message.value = (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "message")), java.lang.String.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public void cancelServiceAppointmentRequestList(com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.CancelServiceAppointmentRequest cancelServiceAppointmentRequest, javax.xml.rpc.holders.StringHolder errorCode, javax.xml.rpc.holders.StringHolder message) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[1]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("ServiceRequest_serviceRequestWS_Binder_cancelServiceAppointmentRequestList");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "cancelServiceAppointmentRequestList"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {cancelServiceAppointmentRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            java.util.Map _output;
            _output = _call.getOutputParams();
            try {
                errorCode.value = (java.lang.String) _output.get(new javax.xml.namespace.QName("", "errorCode"));
            } catch (java.lang.Exception _exception) {
                errorCode.value = (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "errorCode")), java.lang.String.class);
            }
            try {
                message.value = (java.lang.String) _output.get(new javax.xml.namespace.QName("", "message"));
            } catch (java.lang.Exception _exception) {
                message.value = (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "message")), java.lang.String.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public void cancelServiceRequestAppointment(com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.CancelServiceAppointment cancelServiceAppointment, javax.xml.rpc.holders.StringHolder errorCode, javax.xml.rpc.holders.StringHolder message) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[2]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("ServiceRequest_serviceRequestWS_Binder_cancelServiceRequestAppointment");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "cancelServiceRequestAppointment"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {cancelServiceAppointment});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            java.util.Map _output;
            _output = _call.getOutputParams();
            try {
                errorCode.value = (java.lang.String) _output.get(new javax.xml.namespace.QName("", "errorCode"));
            } catch (java.lang.Exception _exception) {
                errorCode.value = (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "errorCode")), java.lang.String.class);
            }
            try {
                message.value = (java.lang.String) _output.get(new javax.xml.namespace.QName("", "message"));
            } catch (java.lang.Exception _exception) {
                message.value = (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "message")), java.lang.String.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public void confirmServiceAppointment(com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.ConfirmServiceAppointment2 confirmServiceAppointment, javax.xml.rpc.holders.StringHolder errorCode, com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.holders.ServiceRequestDetailsOutputHolder serviceRequestDetailsOutput, javax.xml.rpc.holders.StringHolder message) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[3]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("ServiceRequest_serviceRequestWS_Binder_confirmServiceAppointment");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "confirmServiceAppointment"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {confirmServiceAppointment});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            java.util.Map _output;
            _output = _call.getOutputParams();
            try {
                errorCode.value = (java.lang.String) _output.get(new javax.xml.namespace.QName("", "errorCode"));
            } catch (java.lang.Exception _exception) {
                errorCode.value = (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "errorCode")), java.lang.String.class);
            }
            try {
                serviceRequestDetailsOutput.value = (com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.ServiceRequestDetailsOutput) _output.get(new javax.xml.namespace.QName("", "serviceRequestDetailsOutput"));
            } catch (java.lang.Exception _exception) {
                serviceRequestDetailsOutput.value = (com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.ServiceRequestDetailsOutput) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "serviceRequestDetailsOutput")), com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.ServiceRequestDetailsOutput.class);
            }
            try {
                message.value = (java.lang.String) _output.get(new javax.xml.namespace.QName("", "message"));
            } catch (java.lang.Exception _exception) {
                message.value = (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "message")), java.lang.String.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public void getServiceAppointment(com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.ServiceAppointment serviceAppointment, java.lang.String debug, com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.holders.ServiceAppointmentTimeSlotsHolder serviceAppointmentTimeSlots, javax.xml.rpc.holders.StringHolder sessionRequestId, javax.xml.rpc.holders.StringHolder errorCode, javax.xml.rpc.holders.StringHolder message) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[4]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("ServiceRequest_serviceRequestWS_Binder_getServiceAppointment");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "getServiceAppointment"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {serviceAppointment, debug});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            java.util.Map _output;
            _output = _call.getOutputParams();
            try {
                serviceAppointmentTimeSlots.value = (com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.ServiceAppointmentTimeSlots) _output.get(new javax.xml.namespace.QName("", "serviceAppointmentTimeSlots"));
            } catch (java.lang.Exception _exception) {
                serviceAppointmentTimeSlots.value = (com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.ServiceAppointmentTimeSlots) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "serviceAppointmentTimeSlots")), com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.ServiceAppointmentTimeSlots.class);
            }
            try {
                sessionRequestId.value = (java.lang.String) _output.get(new javax.xml.namespace.QName("", "sessionRequestId"));
            } catch (java.lang.Exception _exception) {
                sessionRequestId.value = (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "sessionRequestId")), java.lang.String.class);
            }
            try {
                errorCode.value = (java.lang.String) _output.get(new javax.xml.namespace.QName("", "errorCode"));
            } catch (java.lang.Exception _exception) {
                errorCode.value = (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "errorCode")), java.lang.String.class);
            }
            try {
                message.value = (java.lang.String) _output.get(new javax.xml.namespace.QName("", "message"));
            } catch (java.lang.Exception _exception) {
                message.value = (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "message")), java.lang.String.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public void getSRAndActivityDetails(java.lang.String debug, com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.ServiceRequestAndActivityDetailsInput getServiceRequestAndActivityDetails, com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.holders.ServiceRequestDetailsOutputHolder serviceRequestDetailsOutput, javax.xml.rpc.holders.StringHolder errorCode, javax.xml.rpc.holders.StringHolder message) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[5]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("ServiceRequest_serviceRequestWS_Binder_getSRAndActivityDetails");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "getSRAndActivityDetails"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {debug, getServiceRequestAndActivityDetails});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            java.util.Map _output;
            _output = _call.getOutputParams();
            try {
                serviceRequestDetailsOutput.value = (com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.ServiceRequestDetailsOutput) _output.get(new javax.xml.namespace.QName("", "serviceRequestDetailsOutput"));
            } catch (java.lang.Exception _exception) {
                serviceRequestDetailsOutput.value = (com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.ServiceRequestDetailsOutput) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "serviceRequestDetailsOutput")), com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.ServiceRequestDetailsOutput.class);
            }
            try {
                errorCode.value = (java.lang.String) _output.get(new javax.xml.namespace.QName("", "errorCode"));
            } catch (java.lang.Exception _exception) {
                errorCode.value = (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "errorCode")), java.lang.String.class);
            }
            try {
                message.value = (java.lang.String) _output.get(new javax.xml.namespace.QName("", "message"));
            } catch (java.lang.Exception _exception) {
                message.value = (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "message")), java.lang.String.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public java.lang.String updateServiceRequestActivity(java.lang.String debug, java.lang.String synchOrAsynch, com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.UpdateServiceRequestActivityInput updateServiceRequestActivityInput) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[6]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("ServiceRequest_serviceRequestWS_Binder_updateServiceRequestActivity");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "updateServiceRequestActivity"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {debug, synchOrAsynch, updateServiceRequestActivityInput});

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

    public java.lang.String debriefServiceRequestActivity(java.lang.String debug, java.lang.String synchOrAsynch, com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.DebriefServiceRequestActivityInput debriefServiceRequestActivityInput) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[7]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("ServiceRequest_serviceRequestWS_Binder_debriefServiceRequestActivity");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "debriefServiceRequestActivity"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {debug, synchOrAsynch, debriefServiceRequestActivityInput});

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

    public java.lang.String cancelServiceRequest(com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.CancelServiceRequestWSInput cancelServiceRequestWSInput, java.lang.String debug) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[8]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("ServiceRequest_serviceRequestWS_Binder_cancelServiceRequest");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "cancelServiceRequest"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {cancelServiceRequestWSInput, debug});

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

    public com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.StatusServiceRequestWSOutput getServiceRequestStatus(com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.GetServiceRequestStatusInput getServiceRequestStatus, java.lang.String debug) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[9]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("ServiceRequest_serviceRequestWS_Binder_getServiceRequestStatus");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "getServiceRequestStatus"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {getServiceRequestStatus, debug});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.StatusServiceRequestWSOutput) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.StatusServiceRequestWSOutput) org.apache.axis.utils.JavaUtils.convert(_resp, com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.StatusServiceRequestWSOutput.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public void createServiceRequest(java.lang.String debug, com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.ConsumablesServiceRequestWSInput consumablesServiceRequestWSInput) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[10]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("ServiceRequest_serviceRequestWS_Binder_createServiceRequest");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "createServiceRequest"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {debug, consumablesServiceRequestWSInput});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        extractAttachments(_call);
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public void createChangeManagementServiceRequest(java.lang.String debug, com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.ChangeManagementServiceRequestWSInput changeManagementServiceRequestWSInput, java.lang.String synchOrAsynch, com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.holders.ServiceRequestDetailsOutputHolder serviceRequestDetails, javax.xml.rpc.holders.StringHolder srRowId, javax.xml.rpc.holders.StringHolder srNumber) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[11]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("ServiceRequest_serviceRequestWS_Binder_createChangeManagementServiceRequest");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "createChangeManagementServiceRequest"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {debug, changeManagementServiceRequestWSInput, synchOrAsynch});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            java.util.Map _output;
            _output = _call.getOutputParams();
            try {
                serviceRequestDetails.value = (com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.ServiceRequestDetailsOutput) _output.get(new javax.xml.namespace.QName("", "serviceRequestDetails"));
            } catch (java.lang.Exception _exception) {
                serviceRequestDetails.value = (com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.ServiceRequestDetailsOutput) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "serviceRequestDetails")), com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.ServiceRequestDetailsOutput.class);
            }
            try {
                srRowId.value = (java.lang.String) _output.get(new javax.xml.namespace.QName("", "srRowId"));
            } catch (java.lang.Exception _exception) {
                srRowId.value = (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "srRowId")), java.lang.String.class);
            }
            try {
                srNumber.value = (java.lang.String) _output.get(new javax.xml.namespace.QName("", "srNumber"));
            } catch (java.lang.Exception _exception) {
                srNumber.value = (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "srNumber")), java.lang.String.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public java.lang.String escalateServiceRequest(com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.EscalateServiceRequestWSInput escalateServiceRequestWSInput, java.lang.String debug) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[12]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("ServiceRequest_serviceRequestWS_Binder_escalateServiceRequest");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "escalateServiceRequest"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {escalateServiceRequestWSInput, debug});

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

    public void createConsumablesServiceRequest(java.lang.String debug, com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.ConsumablesServiceRequestWSInput consumablesServiceRequestWSInput, java.lang.String synchOrAsynch, com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.holders.ServiceRequestDetailsOutputHolder serviceRequestDetailsOutput, javax.xml.rpc.holders.StringHolder sRNumber, javax.xml.rpc.holders.StringHolder sRRowId) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[13]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("ServiceRequest_serviceRequestWS_Binder_createConsumablesServiceRequest");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "createConsumablesServiceRequest"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {debug, consumablesServiceRequestWSInput, synchOrAsynch});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            java.util.Map _output;
            _output = _call.getOutputParams();
            try {
                serviceRequestDetailsOutput.value = (com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.ServiceRequestDetailsOutput) _output.get(new javax.xml.namespace.QName("", "serviceRequestDetailsOutput"));
            } catch (java.lang.Exception _exception) {
                serviceRequestDetailsOutput.value = (com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.ServiceRequestDetailsOutput) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "serviceRequestDetailsOutput")), com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.ServiceRequestDetailsOutput.class);
            }
            try {
                sRNumber.value = (java.lang.String) _output.get(new javax.xml.namespace.QName("", "sRNumber"));
            } catch (java.lang.Exception _exception) {
                sRNumber.value = (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "sRNumber")), java.lang.String.class);
            }
            try {
                sRRowId.value = (java.lang.String) _output.get(new javax.xml.namespace.QName("", "sRRowId"));
            } catch (java.lang.Exception _exception) {
                sRRowId.value = (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "sRRowId")), java.lang.String.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public void updateConsumablesServiceRequest(java.lang.String debug, com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.ConsumablesServiceRequestWSInput consumablesServiceRequestWSInput) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[14]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("ServiceRequest_serviceRequestWS_Binder_updateConsumablesServiceRequest");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "createServiceRequest"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {debug, consumablesServiceRequestWSInput});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        extractAttachments(_call);
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.AddressCleanseWSOutput addressCleanse(com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.AddressCleanseWSInput addressCleanseWSInput) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[15]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("ServiceRequest_serviceRequestWS_Binder_addressCleanse");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "addressCleanse"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {addressCleanseWSInput});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.AddressCleanseWSOutput) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.AddressCleanseWSOutput) org.apache.axis.utils.JavaUtils.convert(_resp, com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.AddressCleanseWSOutput.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

}
