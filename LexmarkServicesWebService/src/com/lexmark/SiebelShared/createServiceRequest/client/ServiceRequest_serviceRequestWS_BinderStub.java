/**
 * ServiceRequest_serviceRequestWS_BinderStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.SiebelShared.createServiceRequest.client;

public class ServiceRequest_serviceRequestWS_BinderStub extends org.apache.axis.client.Stub implements com.lexmark.SiebelShared.createServiceRequest.client.ServiceRequestWS_PortType {
    private java.util.Vector cachedSerClasses = new java.util.Vector();
    private java.util.Vector cachedSerQNames = new java.util.Vector();
    private java.util.Vector cachedSerFactories = new java.util.Vector();
    private java.util.Vector cachedDeserFactories = new java.util.Vector();

    static org.apache.axis.description.OperationDesc [] _operations;

    static {
        _operations = new org.apache.axis.description.OperationDesc[20];
        _initOperationDesc1();
        _initOperationDesc2();
    }

    private static void _initOperationDesc1(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getServiceRequestStatus");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "getServiceRequestStatus"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "getServiceRequestStatusInput"), com.lexmark.SiebelShared.createServiceRequest.client.GetServiceRequestStatusInput.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "debug"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "statusServiceRequestWSOutput"));
        oper.setReturnClass(com.lexmark.SiebelShared.createServiceRequest.client.StatusServiceRequestWSOutput.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "statusServiceRequestWSOutput"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[0] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("createChangeManagementServiceRequest");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "debug"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "ChangeManagementServiceRequestWSInput"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "ChangeManagementServiceRequestWSInput"), com.lexmark.SiebelShared.createServiceRequest.client.ChangeManagementServiceRequestWSInput.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "synchOrAsynch"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "serviceRequestDetails"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "serviceRequestDetailsOutput"), com.lexmark.SiebelShared.createServiceRequest.client.ServiceRequestDetailsOutput.class, false, false);
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
        _operations[1] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getServiceAppointment");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "serviceAppointment"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "serviceAppointment"), com.lexmark.SiebelShared.createServiceRequest.client.ServiceAppointment.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "debug"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "serviceAppointmentTimeSlots"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "serviceAppointmentTimeSlots"), com.lexmark.SiebelShared.createServiceRequest.client.ServiceAppointmentTimeSlots.class, false, false);
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
        _operations[2] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("updateConsumablesServiceRequest");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "ConsumablesServiceRequestWSInput"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "ConsumablesServiceRequestWSInput"), com.lexmark.SiebelShared.createServiceRequest.client.ConsumablesServiceRequestWSInput.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "debug"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[3] = oper;

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
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "updateServiceRequestActivityInput"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "updateServiceRequestActivityInput"), com.lexmark.SiebelShared.createServiceRequest.client.UpdateServiceRequestActivityInput.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        oper.setReturnClass(java.lang.String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "status"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[4] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("updateServiceRequest");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "debug"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "serviceRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "serviceRequestInput"), com.lexmark.SiebelShared.createServiceRequest.client.ServiceRequestInput.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[5] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("escalateServiceRequest");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "debug"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "escalateServiceRequestWSInput"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "escalateServiceRequestWSInput"), com.lexmark.SiebelShared.createServiceRequest.client.EscalateServiceRequestWSInput.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "escalateServiceRequestResult"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "description"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[6] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("cancelServiceRequest");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "cancelServiceRequestWSInput"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "cancelServiceRequestWSInput"), com.lexmark.SiebelShared.createServiceRequest.client.CancelServiceRequestWSInput.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "debug"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "cancelServiceRequestResult"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "description"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[7] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("createH2HServiceRequest");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "debug"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "serviceRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "serviceRequestInputForH2H"), com.lexmark.SiebelShared.createServiceRequest.client.ServiceRequestInputForH2H.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "serviceRequestNumber"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "serivceRequestRowId"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "partnerId"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[8] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("updateSellableItemsServiceRequest");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "SellableItemServiceRequestWSInput"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "SellableItemServiceRequestWSInput"), com.lexmark.SiebelShared.createServiceRequest.client.SellableItemServiceRequestWSInput.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "debug"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[9] = oper;

    }

    private static void _initOperationDesc2(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("cancelServiceAppointmentOnReschedule");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "cancelServiceAppointmentReschedule"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "cancelServiceAppointmentReschedule"), com.lexmark.SiebelShared.createServiceRequest.client.CancelServiceAppointmentReschedule.class, false, false);
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
        _operations[10] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getSRAndActivityDetails");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "getServiceRequestAndActivityDetails"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "serviceRequestAndActivityDetailsInput"), com.lexmark.SiebelShared.createServiceRequest.client.ServiceRequestAndActivityDetailsInput.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "serviceRequestDetailsOutput"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "serviceRequestDetailsOutput"), com.lexmark.SiebelShared.createServiceRequest.client.ServiceRequestDetailsOutput.class, false, false);
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
        _operations[11] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("cancelServiceAppointmentRequestList");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "cancelServiceAppointmentRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "cancelServiceAppointmentRequest"), com.lexmark.SiebelShared.createServiceRequest.client.CancelServiceAppointmentRequest.class, false, false);
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
        _operations[12] = oper;

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
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "debriefServiceRequestActivityInput"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "debriefServiceRequestActivityInput"), com.lexmark.SiebelShared.createServiceRequest.client.DebriefServiceRequestActivityInput.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        oper.setReturnClass(java.lang.String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "status"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[13] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("confirmServiceAppointment");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "confirmServiceAppointment"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "confirmServiceAppointment2"), com.lexmark.SiebelShared.createServiceRequest.client.ConfirmServiceAppointment2.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "errorCode"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "serviceRequestDetailsOutput"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "serviceRequestDetailsOutput"), com.lexmark.SiebelShared.createServiceRequest.client.ServiceRequestDetailsOutput.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "message"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[14] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("createServiceRequest");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "debug"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "serviceRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "serviceRequestInput"), com.lexmark.SiebelShared.createServiceRequest.client.ServiceRequestInput.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "synchOrAsynch"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "returnDetail_x003F_"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "srNumber"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "activityId"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "SRRowId"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "SRNumHashValue"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "serviceRegionId"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "siebelStatusMessage"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "serviceRequestDetails"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "serviceRequestDetailsOutput"), com.lexmark.SiebelShared.createServiceRequest.client.ServiceRequestDetailsOutput.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[15] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("createConsumablesServiceRequest");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "debug"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "ConsumablesServiceRequestWSInput"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "ConsumablesServiceRequestWSInput"), com.lexmark.SiebelShared.createServiceRequest.client.ConsumablesServiceRequestWSInput.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "synchOrAsynch"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "serviceRequestDetailsOutput"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "serviceRequestDetailsOutput"), com.lexmark.SiebelShared.createServiceRequest.client.ServiceRequestDetailsOutput.class, false, false);
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
        _operations[16] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("createSellableItemsServiceRequest");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "SellableItemServiceRequestWSInput"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "SellableItemServiceRequestWSInput"), com.lexmark.SiebelShared.createServiceRequest.client.SellableItemServiceRequestWSInput.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "synchOrAsynch"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "debug"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "serviceRequestDetails"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "serviceRequestDetailsOutput"), com.lexmark.SiebelShared.createServiceRequest.client.ServiceRequestDetailsOutput.class, false, false);
        param.setOmittable(true);
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
        _operations[17] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("cancelServiceRequestAppointment");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "cancelServiceAppointment"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "cancelServiceAppointment"), com.lexmark.SiebelShared.createServiceRequest.client.CancelServiceAppointment.class, false, false);
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
        _operations[18] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("addressCleanse");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "AddressCleanseWSInput"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "AddressCleanseWSInput"), com.lexmark.SiebelShared.createServiceRequest.client.AddressCleanseWSInput.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "debug"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "AddressCleanseWSOutput"));
        oper.setReturnClass(com.lexmark.SiebelShared.createServiceRequest.client.AddressCleanseWSOutput.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "AddressCleanseWSOutput"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[19] = oper;

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
            cls = com.lexmark.SiebelShared.createServiceRequest.client.Account.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "Account2");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.createServiceRequest.client.Account2.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "Account3");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.createServiceRequest.client.Account3.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "AccountAddress");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.createServiceRequest.client.AccountAddress.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "AccountAddress2");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.createServiceRequest.client.AccountAddress2.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "ActivityDetails");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.createServiceRequest.client.ActivityDetails.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "ActivityDetails2");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.createServiceRequest.client.ActivityDetails2.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "addedPropSet");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.createServiceRequest.client.AddedPropSet.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "AddressCleanseWSInput");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.createServiceRequest.client.AddressCleanseWSInput.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "AddressCleanseWSOutput");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.createServiceRequest.client.AddressCleanseWSOutput.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "AddressData");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.createServiceRequest.client.AddressData.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "AddressData2");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.createServiceRequest.client.AddressData2.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "AddressWSInput");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.createServiceRequest.client.AddressWSInput.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "AddressWSOutput");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.createServiceRequest.client.AddressWSOutput.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "ArrayOfActivityDetails");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.createServiceRequest.client.ActivityDetails[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "ActivityDetails");
            qName2 = new javax.xml.namespace.QName("", "ArrayOfActivityDetailsItem");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "ArrayOfActivityDetails2");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.createServiceRequest.client.ActivityDetails2[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "ActivityDetails2");
            qName2 = new javax.xml.namespace.QName("", "ArrayOfActivityDetails2Item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "ArrayOfAssetContacts");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.createServiceRequest.client.AssetContacts[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "AssetContacts");
            qName2 = new javax.xml.namespace.QName("", "ArrayOfAssetContactsItem");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "ArrayOfMaterialDetails");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.createServiceRequest.client.MaterialDetails[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "MaterialDetails");
            qName2 = new javax.xml.namespace.QName("", "ArrayOfMaterialDetailsItem");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "ArrayOfPageCountData");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.createServiceRequest.client.PageCountData[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "PageCountData");
            qName2 = new javax.xml.namespace.QName("", "ArrayOfPageCountDataItem");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "ArrayOfProductDetails");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.createServiceRequest.client.ProductDetails[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "ProductDetails");
            qName2 = new javax.xml.namespace.QName("", "ArrayOfProductDetailsItem");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "ArrayOfRelatedActivityInformation");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.createServiceRequest.client.RelatedActivityInformation[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "RelatedActivityInformation");
            qName2 = new javax.xml.namespace.QName("", "ArrayOfRelatedActivityInformationItem");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "ArrayOfSelectedServiceDetails");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.createServiceRequest.client.SelectedServiceDetails[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "SelectedServiceDetails");
            qName2 = new javax.xml.namespace.QName("", "ArrayOfSelectedServiceDetailsItem");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "ArrayOfSelectedServiceDetails2");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.createServiceRequest.client.SelectedServiceDetails2[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "SelectedServiceDetails2");
            qName2 = new javax.xml.namespace.QName("", "ArrayOfSelectedServiceDetails2Item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "ArrayOfSelectedServiceDetails3");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.createServiceRequest.client.SelectedServiceDetails3[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "SelectedServiceDetails3");
            qName2 = new javax.xml.namespace.QName("", "ArrayOfSelectedServiceDetails3Item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "ArrayOfShipmentInformation");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.createServiceRequest.client.ShipmentInformation[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "ShipmentInformation");
            qName2 = new javax.xml.namespace.QName("", "ArrayOfShipmentInformationItem");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "ArrayOfSiebelActivityNotes");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.createServiceRequest.client.SiebelActivityNotes[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "SiebelActivityNotes");
            qName2 = new javax.xml.namespace.QName("", "ArrayOfSiebelActivityNotesItem");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "ArrayOfSiebelMeterReadInformation");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.createServiceRequest.client.SiebelMeterReadInformation[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "SiebelMeterReadInformation");
            qName2 = new javax.xml.namespace.QName("", "ArrayOfSiebelMeterReadInformationItem");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "ArrayOfSiebelPaymentRequestList");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.createServiceRequest.client.SiebelPaymentRequestList[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "SiebelPaymentRequestList");
            qName2 = new javax.xml.namespace.QName("", "ArrayOfSiebelPaymentRequestListItem");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "ArrayOfSiebelRecommendedPartsList");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.createServiceRequest.client.SiebelRecommendedPartsList[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "SiebelRecommendedPartsList");
            qName2 = new javax.xml.namespace.QName("", "ArrayOfSiebelRecommendedPartsListItem");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "ArrayOfSiebelReturnPartsList");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.createServiceRequest.client.SiebelReturnPartsList[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "SiebelReturnPartsList");
            qName2 = new javax.xml.namespace.QName("", "ArrayOfSiebelReturnPartsListItem");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "ArrayOfTimeSlot");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.createServiceRequest.client.TimeSlot[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "TimeSlot");
            qName2 = new javax.xml.namespace.QName("", "ArrayOfTimeSlotItem");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "AssetContact");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.createServiceRequest.client.AssetContact.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "AssetContact2");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.createServiceRequest.client.AssetContact2.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "AssetContacts");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.createServiceRequest.client.AssetContacts.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "AssetInformation");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.createServiceRequest.client.AssetInformation.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "AssetInformation2");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.createServiceRequest.client.AssetInformation2.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "cancelServiceAppointment");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.createServiceRequest.client.CancelServiceAppointment.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "cancelServiceAppointmentData");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.createServiceRequest.client.CancelServiceAppointmentData.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "cancelServiceAppointmentData2");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.createServiceRequest.client.CancelServiceAppointmentData2.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "cancelServiceAppointmentRequest");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.createServiceRequest.client.CancelServiceAppointmentRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "cancelServiceAppointmentRequestListWSInput");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.createServiceRequest.client.CancelServiceAppointmentRequestListWSInput.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "cancelServiceAppointmentReschedule");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.createServiceRequest.client.CancelServiceAppointmentReschedule.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "cancelServiceAppointmentRescheduleData");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.createServiceRequest.client.CancelServiceAppointmentRescheduleData.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "cancelServiceAppointmentRescheduleWSInput");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.createServiceRequest.client.CancelServiceAppointmentRescheduleWSInput.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "cancelServiceAppointmentWSInput");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.createServiceRequest.client.CancelServiceAppointmentWSInput.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "cancelServiceRequestData");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.createServiceRequest.client.CancelServiceRequestData.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "cancelServiceRequestWSInput");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.createServiceRequest.client.CancelServiceRequestWSInput.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "cancelServiceRequestWSInput2");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.createServiceRequest.client.CancelServiceRequestWSInput2.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "ChangeManagementServiceRequestData");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.createServiceRequest.client.ChangeManagementServiceRequestData.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "ChangeManagementServiceRequestWSInput");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.createServiceRequest.client.ChangeManagementServiceRequestWSInput.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "ChangeManagementServiceRequestWSInput2");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.createServiceRequest.client.ChangeManagementServiceRequestWSInput2.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "confirmServiceAppointment2");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.createServiceRequest.client.ConfirmServiceAppointment2.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "confirmServiceAppointmentData");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.createServiceRequest.client.ConfirmServiceAppointmentData.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "confirmServiceAppointmentWSInput");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.createServiceRequest.client.ConfirmServiceAppointmentWSInput.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "ConsumablesServiceRequestData");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.createServiceRequest.client.ConsumablesServiceRequestData.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "ConsumablesServiceRequestWSInput");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.createServiceRequest.client.ConsumablesServiceRequestWSInput.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "ConsumablesServiceRequestWSInput2");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.createServiceRequest.client.ConsumablesServiceRequestWSInput2.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "CreditCardInformation");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.createServiceRequest.client.CreditCardInformation.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "CustomerInformation");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.createServiceRequest.client.CustomerInformation.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "DebriefDetails");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.createServiceRequest.client.DebriefDetails.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "DebriefInformation");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.createServiceRequest.client.DebriefInformation.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "debriefServiceRequestActivityInput");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.createServiceRequest.client.DebriefServiceRequestActivityInput.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "debriefServiceRequestActivityWSInput");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.createServiceRequest.client.DebriefServiceRequestActivityWSInput.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "DocumentMetaData");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.createServiceRequest.client.DocumentMetaData.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "DocumentMetaData2");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.createServiceRequest.client.DocumentMetaData2.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "DocumentMetaData3");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.createServiceRequest.client.DocumentMetaData3.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "Entitlement");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.createServiceRequest.client.Entitlement.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "Entitlement2");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.createServiceRequest.client.Entitlement2.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "Entitlement3");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.createServiceRequest.client.Entitlement3.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "Entitlement4");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.createServiceRequest.client.Entitlement4.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "Entitlement5");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.createServiceRequest.client.Entitlement5.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "Entitlement6");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.createServiceRequest.client.Entitlement6.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "EntitlementServiceDetails");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.createServiceRequest.client.EntitlementServiceDetails.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "EntitlementServiceDetails2");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.createServiceRequest.client.EntitlementServiceDetails2.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "EntitlementServiceDetails3");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.createServiceRequest.client.EntitlementServiceDetails3.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "EntitlementServiceDetails4");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.createServiceRequest.client.EntitlementServiceDetails4.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "EntitlementServiceDetails5");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.createServiceRequest.client.EntitlementServiceDetails5.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "EntitlementServiceDetails6");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.createServiceRequest.client.EntitlementServiceDetails6.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "escalateServiceRequestData");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.createServiceRequest.client.EscalateServiceRequestData.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "escalateServiceRequestWSInput");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.createServiceRequest.client.EscalateServiceRequestWSInput.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "escalateServiceRequestWSInput2");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.createServiceRequest.client.EscalateServiceRequestWSInput2.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "getServiceAppointmentData");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.createServiceRequest.client.GetServiceAppointmentData.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "getServiceAppointmentWSInput");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.createServiceRequest.client.GetServiceAppointmentWSInput.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "getServiceRequestAndActivityDetails");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.createServiceRequest.client.GetServiceRequestAndActivityDetails.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "getServiceRequestAndActivityDetailsData");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.createServiceRequest.client.GetServiceRequestAndActivityDetailsData.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "getServiceRequestStatusData");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.createServiceRequest.client.GetServiceRequestStatusData.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "getServiceRequestStatusInput");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.createServiceRequest.client.GetServiceRequestStatusInput.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "getServiceRequestStatusWSInput");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.createServiceRequest.client.GetServiceRequestStatusWSInput.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "InstalledAtAddress");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.createServiceRequest.client.InstalledAtAddress.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "InstalledAtAddress2");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.createServiceRequest.client.InstalledAtAddress2.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "MaterialDetails");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.createServiceRequest.client.MaterialDetails.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "NoteAuthor");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.createServiceRequest.client.NoteAuthor.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "OrderInformation");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.createServiceRequest.client.OrderInformation.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "OrderInformation2");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.createServiceRequest.client.OrderInformation2.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "OriginalDeviceInformation");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.createServiceRequest.client.OriginalDeviceInformation.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "PageCountData");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.createServiceRequest.client.PageCountData.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "PartInformation");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.createServiceRequest.client.PartInformation.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "PartInfromation");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.createServiceRequest.client.PartInfromation.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "PaymentDetails");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.createServiceRequest.client.PaymentDetails.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "PrimaryContact");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.createServiceRequest.client.PrimaryContact.class;
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
            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "ProductDetails");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.createServiceRequest.client.ProductDetails.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "RelatedActivityInformation");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.createServiceRequest.client.RelatedActivityInformation.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "ReplacementDeviceInformation");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.createServiceRequest.client.ReplacementDeviceInformation.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "Requester");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.createServiceRequest.client.Requester.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "ReturnedAssetInformation");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.createServiceRequest.client.ReturnedAssetInformation.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "SecondaryContact");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.createServiceRequest.client.SecondaryContact.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "SelectedServiceDetails");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.createServiceRequest.client.SelectedServiceDetails.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "SelectedServiceDetails2");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.createServiceRequest.client.SelectedServiceDetails2.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "SelectedServiceDetails3");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.createServiceRequest.client.SelectedServiceDetails3.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "SellableItemServiceRequestWSInput");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.createServiceRequest.client.SellableItemServiceRequestWSInput.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "SellableItemsServiceRequestData");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.createServiceRequest.client.SellableItemsServiceRequestData.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "SellableItemsServiceRequestWSInput");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.createServiceRequest.client.SellableItemsServiceRequestWSInput.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "ServiceAddress");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.createServiceRequest.client.ServiceAddress.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "serviceAppointment");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.createServiceRequest.client.ServiceAppointment.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "serviceAppointmentTimeSlots");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.createServiceRequest.client.ServiceAppointmentTimeSlots.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "ServiceProviderInformation");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.createServiceRequest.client.ServiceProviderInformation.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "serviceRequestAndActivityDetailsInput");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.createServiceRequest.client.ServiceRequestAndActivityDetailsInput.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "ServiceRequestData");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.createServiceRequest.client.ServiceRequestData.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "ServiceRequestData2");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.createServiceRequest.client.ServiceRequestData2.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "ServiceRequestData3");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.createServiceRequest.client.ServiceRequestData3.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "ServiceRequestData4");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.createServiceRequest.client.ServiceRequestData4.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "ServiceRequestData5");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.createServiceRequest.client.ServiceRequestData5.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "serviceRequestDetailsOutput");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.createServiceRequest.client.ServiceRequestDetailsOutput.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "serviceRequestInput");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.createServiceRequest.client.ServiceRequestInput.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "serviceRequestInputForH2H");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.createServiceRequest.client.ServiceRequestInputForH2H.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "ServiceRequestWSInput");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.createServiceRequest.client.ServiceRequestWSInput.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "ServiceRequestWSInput2");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.createServiceRequest.client.ServiceRequestWSInput2.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "ServiceRequestWSOutput");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.createServiceRequest.client.ServiceRequestWSOutput.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "ShipmentInformation");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.createServiceRequest.client.ShipmentInformation.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "SiebelAccount");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.createServiceRequest.client.SiebelAccount.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "SiebelActivityDetails");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.createServiceRequest.client.SiebelActivityDetails.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "SiebelActivityNotes");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.createServiceRequest.client.SiebelActivityNotes.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "SiebelAddress");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.createServiceRequest.client.SiebelAddress.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "SiebelAssetInformation");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.createServiceRequest.client.SiebelAssetInformation.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "SiebelAssetManagementInformation");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.createServiceRequest.client.SiebelAssetManagementInformation.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "SiebelContact");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.createServiceRequest.client.SiebelContact.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "SiebelMDMCustomerInformation");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.createServiceRequest.client.SiebelMDMCustomerInformation.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "SiebelMeterReadInformation");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.createServiceRequest.client.SiebelMeterReadInformation.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "SiebelPaymentDetails");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.createServiceRequest.client.SiebelPaymentDetails.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "SiebelPaymentRequestList");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.createServiceRequest.client.SiebelPaymentRequestList.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "SiebelRecommendedPartsList");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.createServiceRequest.client.SiebelRecommendedPartsList.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "SiebelReturnPartsList");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.createServiceRequest.client.SiebelReturnPartsList.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "SiebelServiceProviderInformation");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.createServiceRequest.client.SiebelServiceProviderInformation.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "SiebelServiceRequestDetails");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.createServiceRequest.client.SiebelServiceRequestDetails.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "StatusServiceRequestData");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.createServiceRequest.client.StatusServiceRequestData.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "statusServiceRequestWSOutput");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.createServiceRequest.client.StatusServiceRequestWSOutput.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "statusServiceRequestWSOutput2");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.createServiceRequest.client.StatusServiceRequestWSOutput2.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "TimeSlot");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.createServiceRequest.client.TimeSlot.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "updateServiceRequestActivityInput");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.createServiceRequest.client.UpdateServiceRequestActivityInput.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "updateServiceRequestActivityWSInput");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.createServiceRequest.client.UpdateServiceRequestActivityWSInput.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "WebServiceDocumentMetaData");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SiebelShared.createServiceRequest.client.WebServiceDocumentMetaData.class;
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

    public com.lexmark.SiebelShared.createServiceRequest.client.StatusServiceRequestWSOutput getServiceRequestStatus(com.lexmark.SiebelShared.createServiceRequest.client.GetServiceRequestStatusInput getServiceRequestStatus, java.lang.String debug) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[0]);
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
                return (com.lexmark.SiebelShared.createServiceRequest.client.StatusServiceRequestWSOutput) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.lexmark.SiebelShared.createServiceRequest.client.StatusServiceRequestWSOutput) org.apache.axis.utils.JavaUtils.convert(_resp, com.lexmark.SiebelShared.createServiceRequest.client.StatusServiceRequestWSOutput.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public void createChangeManagementServiceRequest(java.lang.String debug, com.lexmark.SiebelShared.createServiceRequest.client.ChangeManagementServiceRequestWSInput changeManagementServiceRequestWSInput, java.lang.String synchOrAsynch, com.lexmark.SiebelShared.createServiceRequest.client.holders.ServiceRequestDetailsOutputHolder serviceRequestDetails, javax.xml.rpc.holders.StringHolder srRowId, javax.xml.rpc.holders.StringHolder srNumber) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[1]);
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
                serviceRequestDetails.value = (com.lexmark.SiebelShared.createServiceRequest.client.ServiceRequestDetailsOutput) _output.get(new javax.xml.namespace.QName("", "serviceRequestDetails"));
            } catch (java.lang.Exception _exception) {
                serviceRequestDetails.value = (com.lexmark.SiebelShared.createServiceRequest.client.ServiceRequestDetailsOutput) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "serviceRequestDetails")), com.lexmark.SiebelShared.createServiceRequest.client.ServiceRequestDetailsOutput.class);
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

    public void getServiceAppointment(com.lexmark.SiebelShared.createServiceRequest.client.ServiceAppointment serviceAppointment, java.lang.String debug, com.lexmark.SiebelShared.createServiceRequest.client.holders.ServiceAppointmentTimeSlotsHolder serviceAppointmentTimeSlots, javax.xml.rpc.holders.StringHolder sessionRequestId, javax.xml.rpc.holders.StringHolder errorCode, javax.xml.rpc.holders.StringHolder message) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[2]);
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
                serviceAppointmentTimeSlots.value = (com.lexmark.SiebelShared.createServiceRequest.client.ServiceAppointmentTimeSlots) _output.get(new javax.xml.namespace.QName("", "serviceAppointmentTimeSlots"));
            } catch (java.lang.Exception _exception) {
                serviceAppointmentTimeSlots.value = (com.lexmark.SiebelShared.createServiceRequest.client.ServiceAppointmentTimeSlots) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "serviceAppointmentTimeSlots")), com.lexmark.SiebelShared.createServiceRequest.client.ServiceAppointmentTimeSlots.class);
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

    public void updateConsumablesServiceRequest(com.lexmark.SiebelShared.createServiceRequest.client.ConsumablesServiceRequestWSInput consumablesServiceRequestWSInput, java.lang.String debug) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[3]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("ServiceRequest_serviceRequestWS_Binder_updateConsumablesServiceRequest");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "updateConsumablesServiceRequest"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {consumablesServiceRequestWSInput, debug});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        extractAttachments(_call);
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public java.lang.String updateServiceRequestActivity(java.lang.String debug, java.lang.String synchOrAsynch, com.lexmark.SiebelShared.createServiceRequest.client.UpdateServiceRequestActivityInput updateServiceRequestActivityInput) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[4]);
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

    public void updateServiceRequest(java.lang.String debug, com.lexmark.SiebelShared.createServiceRequest.client.ServiceRequestInput serviceRequest) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[5]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("ServiceRequest_serviceRequestWS_Binder_updateServiceRequest");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "updateServiceRequest"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {debug, serviceRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        extractAttachments(_call);
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public void escalateServiceRequest(java.lang.String debug, com.lexmark.SiebelShared.createServiceRequest.client.EscalateServiceRequestWSInput escalateServiceRequestWSInput, javax.xml.rpc.holders.StringHolder escalateServiceRequestResult, javax.xml.rpc.holders.StringHolder description) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[6]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("ServiceRequest_serviceRequestWS_Binder_escalateServiceRequest");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "escalateServiceRequest"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {debug, escalateServiceRequestWSInput});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            java.util.Map _output;
            _output = _call.getOutputParams();
            try {
                escalateServiceRequestResult.value = (java.lang.String) _output.get(new javax.xml.namespace.QName("", "escalateServiceRequestResult"));
            } catch (java.lang.Exception _exception) {
                escalateServiceRequestResult.value = (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "escalateServiceRequestResult")), java.lang.String.class);
            }
            try {
                description.value = (java.lang.String) _output.get(new javax.xml.namespace.QName("", "description"));
            } catch (java.lang.Exception _exception) {
                description.value = (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "description")), java.lang.String.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public void cancelServiceRequest(com.lexmark.SiebelShared.createServiceRequest.client.CancelServiceRequestWSInput cancelServiceRequestWSInput, java.lang.String debug, javax.xml.rpc.holders.StringHolder cancelServiceRequestResult, javax.xml.rpc.holders.StringHolder description) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[7]);
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
            java.util.Map _output;
            _output = _call.getOutputParams();
            try {
                cancelServiceRequestResult.value = (java.lang.String) _output.get(new javax.xml.namespace.QName("", "cancelServiceRequestResult"));
            } catch (java.lang.Exception _exception) {
                cancelServiceRequestResult.value = (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "cancelServiceRequestResult")), java.lang.String.class);
            }
            try {
                description.value = (java.lang.String) _output.get(new javax.xml.namespace.QName("", "description"));
            } catch (java.lang.Exception _exception) {
                description.value = (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "description")), java.lang.String.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public void createH2HServiceRequest(java.lang.String debug, com.lexmark.SiebelShared.createServiceRequest.client.ServiceRequestInputForH2H serviceRequest, javax.xml.rpc.holders.StringHolder serviceRequestNumber, javax.xml.rpc.holders.StringHolder serivceRequestRowId, javax.xml.rpc.holders.StringHolder partnerId) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[8]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("ServiceRequest_serviceRequestWS_Binder_createH2HServiceRequest");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "createH2HServiceRequest"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {debug, serviceRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            java.util.Map _output;
            _output = _call.getOutputParams();
            try {
                serviceRequestNumber.value = (java.lang.String) _output.get(new javax.xml.namespace.QName("", "serviceRequestNumber"));
            } catch (java.lang.Exception _exception) {
                serviceRequestNumber.value = (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "serviceRequestNumber")), java.lang.String.class);
            }
            try {
                serivceRequestRowId.value = (java.lang.String) _output.get(new javax.xml.namespace.QName("", "serivceRequestRowId"));
            } catch (java.lang.Exception _exception) {
                serivceRequestRowId.value = (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "serivceRequestRowId")), java.lang.String.class);
            }
            try {
                partnerId.value = (java.lang.String) _output.get(new javax.xml.namespace.QName("", "partnerId"));
            } catch (java.lang.Exception _exception) {
                partnerId.value = (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "partnerId")), java.lang.String.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public void updateSellableItemsServiceRequest(com.lexmark.SiebelShared.createServiceRequest.client.SellableItemServiceRequestWSInput sellableItemServiceRequestWSInput, java.lang.String debug) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[9]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("ServiceRequest_serviceRequestWS_Binder_updateSellableItemsServiceRequest");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "updateSellableItemsServiceRequest"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {sellableItemServiceRequestWSInput, debug});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        extractAttachments(_call);
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public void cancelServiceAppointmentOnReschedule(com.lexmark.SiebelShared.createServiceRequest.client.CancelServiceAppointmentReschedule cancelServiceAppointmentReschedule, javax.xml.rpc.holders.StringHolder errorCode, javax.xml.rpc.holders.StringHolder message) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[10]);
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

    public void getSRAndActivityDetails(com.lexmark.SiebelShared.createServiceRequest.client.ServiceRequestAndActivityDetailsInput getServiceRequestAndActivityDetails, com.lexmark.SiebelShared.createServiceRequest.client.holders.ServiceRequestDetailsOutputHolder serviceRequestDetailsOutput, javax.xml.rpc.holders.StringHolder errorCode, javax.xml.rpc.holders.StringHolder message) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[11]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("ServiceRequest_serviceRequestWS_Binder_getSRAndActivityDetails");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "getSRAndActivityDetails"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {getServiceRequestAndActivityDetails});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            java.util.Map _output;
            _output = _call.getOutputParams();
            try {
                serviceRequestDetailsOutput.value = (com.lexmark.SiebelShared.createServiceRequest.client.ServiceRequestDetailsOutput) _output.get(new javax.xml.namespace.QName("", "serviceRequestDetailsOutput"));
            } catch (java.lang.Exception _exception) {
                serviceRequestDetailsOutput.value = (com.lexmark.SiebelShared.createServiceRequest.client.ServiceRequestDetailsOutput) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "serviceRequestDetailsOutput")), com.lexmark.SiebelShared.createServiceRequest.client.ServiceRequestDetailsOutput.class);
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

    public void cancelServiceAppointmentRequestList(com.lexmark.SiebelShared.createServiceRequest.client.CancelServiceAppointmentRequest cancelServiceAppointmentRequest, javax.xml.rpc.holders.StringHolder errorCode, javax.xml.rpc.holders.StringHolder message) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[12]);
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

    public java.lang.String debriefServiceRequestActivity(java.lang.String debug, java.lang.String synchOrAsynch, com.lexmark.SiebelShared.createServiceRequest.client.DebriefServiceRequestActivityInput debriefServiceRequestActivityInput) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[13]);
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

    public void confirmServiceAppointment(com.lexmark.SiebelShared.createServiceRequest.client.ConfirmServiceAppointment2 confirmServiceAppointment, javax.xml.rpc.holders.StringHolder errorCode, com.lexmark.SiebelShared.createServiceRequest.client.holders.ServiceRequestDetailsOutputHolder serviceRequestDetailsOutput, javax.xml.rpc.holders.StringHolder message) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[14]);
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
                serviceRequestDetailsOutput.value = (com.lexmark.SiebelShared.createServiceRequest.client.ServiceRequestDetailsOutput) _output.get(new javax.xml.namespace.QName("", "serviceRequestDetailsOutput"));
            } catch (java.lang.Exception _exception) {
                serviceRequestDetailsOutput.value = (com.lexmark.SiebelShared.createServiceRequest.client.ServiceRequestDetailsOutput) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "serviceRequestDetailsOutput")), com.lexmark.SiebelShared.createServiceRequest.client.ServiceRequestDetailsOutput.class);
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

    public void createServiceRequest(java.lang.String debug, com.lexmark.SiebelShared.createServiceRequest.client.ServiceRequestInput serviceRequest, java.lang.String synchOrAsynch, java.lang.String returnDetail_x003F_, javax.xml.rpc.holders.StringHolder srNumber, javax.xml.rpc.holders.StringHolder activityId, javax.xml.rpc.holders.StringHolder SRRowId, javax.xml.rpc.holders.StringHolder SRNumHashValue, javax.xml.rpc.holders.StringHolder serviceRegionId, javax.xml.rpc.holders.StringHolder siebelStatusMessage, com.lexmark.SiebelShared.createServiceRequest.client.holders.ServiceRequestDetailsOutputHolder serviceRequestDetails) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[15]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("ServiceRequest_serviceRequestWS_Binder_createServiceRequest");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "createServiceRequest"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {debug, serviceRequest, synchOrAsynch, returnDetail_x003F_});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            java.util.Map _output;
            _output = _call.getOutputParams();
            try {
                srNumber.value = (java.lang.String) _output.get(new javax.xml.namespace.QName("", "srNumber"));
            } catch (java.lang.Exception _exception) {
                srNumber.value = (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "srNumber")), java.lang.String.class);
            }
            try {
                activityId.value = (java.lang.String) _output.get(new javax.xml.namespace.QName("", "activityId"));
            } catch (java.lang.Exception _exception) {
                activityId.value = (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "activityId")), java.lang.String.class);
            }
            try {
                SRRowId.value = (java.lang.String) _output.get(new javax.xml.namespace.QName("", "SRRowId"));
            } catch (java.lang.Exception _exception) {
                SRRowId.value = (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "SRRowId")), java.lang.String.class);
            }
            try {
                SRNumHashValue.value = (java.lang.String) _output.get(new javax.xml.namespace.QName("", "SRNumHashValue"));
            } catch (java.lang.Exception _exception) {
                SRNumHashValue.value = (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "SRNumHashValue")), java.lang.String.class);
            }
            try {
                serviceRegionId.value = (java.lang.String) _output.get(new javax.xml.namespace.QName("", "serviceRegionId"));
            } catch (java.lang.Exception _exception) {
                serviceRegionId.value = (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "serviceRegionId")), java.lang.String.class);
            }
            try {
                siebelStatusMessage.value = (java.lang.String) _output.get(new javax.xml.namespace.QName("", "siebelStatusMessage"));
            } catch (java.lang.Exception _exception) {
                siebelStatusMessage.value = (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "siebelStatusMessage")), java.lang.String.class);
            }
            try {
                serviceRequestDetails.value = (com.lexmark.SiebelShared.createServiceRequest.client.ServiceRequestDetailsOutput) _output.get(new javax.xml.namespace.QName("", "serviceRequestDetails"));
            } catch (java.lang.Exception _exception) {
                serviceRequestDetails.value = (com.lexmark.SiebelShared.createServiceRequest.client.ServiceRequestDetailsOutput) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "serviceRequestDetails")), com.lexmark.SiebelShared.createServiceRequest.client.ServiceRequestDetailsOutput.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public void createConsumablesServiceRequest(java.lang.String debug, com.lexmark.SiebelShared.createServiceRequest.client.ConsumablesServiceRequestWSInput consumablesServiceRequestWSInput, java.lang.String synchOrAsynch, com.lexmark.SiebelShared.createServiceRequest.client.holders.ServiceRequestDetailsOutputHolder serviceRequestDetailsOutput, javax.xml.rpc.holders.StringHolder sRNumber, javax.xml.rpc.holders.StringHolder sRRowId) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[16]);
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
                serviceRequestDetailsOutput.value = (com.lexmark.SiebelShared.createServiceRequest.client.ServiceRequestDetailsOutput) _output.get(new javax.xml.namespace.QName("", "serviceRequestDetailsOutput"));
            } catch (java.lang.Exception _exception) {
                serviceRequestDetailsOutput.value = (com.lexmark.SiebelShared.createServiceRequest.client.ServiceRequestDetailsOutput) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "serviceRequestDetailsOutput")), com.lexmark.SiebelShared.createServiceRequest.client.ServiceRequestDetailsOutput.class);
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

    public void createSellableItemsServiceRequest(com.lexmark.SiebelShared.createServiceRequest.client.SellableItemServiceRequestWSInput sellableItemServiceRequestWSInput, java.lang.String synchOrAsynch, java.lang.String debug, com.lexmark.SiebelShared.createServiceRequest.client.holders.ServiceRequestDetailsOutputHolder serviceRequestDetails, javax.xml.rpc.holders.StringHolder sRNumber, javax.xml.rpc.holders.StringHolder sRRowId) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[17]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("ServiceRequest_serviceRequestWS_Binder_createSellableItemsServiceRequest");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "createSellableItemsServiceRequest"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {sellableItemServiceRequestWSInput, synchOrAsynch, debug});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            java.util.Map _output;
            _output = _call.getOutputParams();
            try {
                serviceRequestDetails.value = (com.lexmark.SiebelShared.createServiceRequest.client.ServiceRequestDetailsOutput) _output.get(new javax.xml.namespace.QName("", "serviceRequestDetails"));
            } catch (java.lang.Exception _exception) {
                serviceRequestDetails.value = (com.lexmark.SiebelShared.createServiceRequest.client.ServiceRequestDetailsOutput) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "serviceRequestDetails")), com.lexmark.SiebelShared.createServiceRequest.client.ServiceRequestDetailsOutput.class);
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

    public void cancelServiceRequestAppointment(com.lexmark.SiebelShared.createServiceRequest.client.CancelServiceAppointment cancelServiceAppointment, javax.xml.rpc.holders.StringHolder errorCode, javax.xml.rpc.holders.StringHolder message) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[18]);
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

    public com.lexmark.SiebelShared.createServiceRequest.client.AddressCleanseWSOutput addressCleanse(com.lexmark.SiebelShared.createServiceRequest.client.AddressCleanseWSInput addressCleanseWSInput, java.lang.String debug) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[19]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("ServiceRequest_serviceRequestWS_Binder_addressCleanse");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "addressCleanse"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {addressCleanseWSInput, debug});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.lexmark.SiebelShared.createServiceRequest.client.AddressCleanseWSOutput) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.lexmark.SiebelShared.createServiceRequest.client.AddressCleanseWSOutput) org.apache.axis.utils.JavaUtils.convert(_resp, com.lexmark.SiebelShared.createServiceRequest.client.AddressCleanseWSOutput.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

}
