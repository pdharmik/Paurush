/**
 * ZG_AP_GET_DATA_BindingStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.SAPWebServices.AP.invoice.client;

public class ZG_AP_GET_DATA_BindingStub extends org.apache.axis.client.Stub implements com.lexmark.SAPWebServices.AP.invoice.client.ZG_AP_GET_DATA_PortType {
    private java.util.Vector cachedSerClasses = new java.util.Vector();
    private java.util.Vector cachedSerQNames = new java.util.Vector();
    private java.util.Vector cachedSerFactories = new java.util.Vector();
    private java.util.Vector cachedDeserFactories = new java.util.Vector();

    static org.apache.axis.description.OperationDesc [] _operations;

    static {
        _operations = new org.apache.axis.description.OperationDesc[1];
        _initOperationDesc1();
    }

    private static void _initOperationDesc1(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("APAR_EBPP_GET_DATA");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "I_ADDSEL"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:sap-com:document:sap:rfc:functions", "EBPP_ADDSEL"), com.lexmark.SAPWebServices.AP.invoice.client.EBPP_ADDSEL.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "I_CONTROLDATA"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:sap-com:document:sap:rfc:functions", "EBPP_CONTROL"), com.lexmark.SAPWebServices.AP.invoice.client.EBPP_CONTROL.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "I_DMSELECTION"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:sap-com:document:sap:rfc:functions", "FDM_EBPP_DMSELECTION"), com.lexmark.SAPWebServices.AP.invoice.client.FDM_EBPP_DMSELECTION.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "I_PARTNER"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:sap-com:document:sap:rfc:functions", "EBPP_PARTNER"), com.lexmark.SAPWebServices.AP.invoice.client.EBPP_PARTNER.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "T_ALLOCATION"), org.apache.axis.description.ParameterDesc.INOUT, new javax.xml.namespace.QName("urn:sap-com:document:sap:rfc:functions", "TABLE_OF_EBPP_ALLOCATION"), com.lexmark.SAPWebServices.AP.invoice.client.EBPP_ALLOCATION[].class, false, false);
        param.setItemQName(new javax.xml.namespace.QName("", "item"));
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "T_BANKS"), org.apache.axis.description.ParameterDesc.INOUT, new javax.xml.namespace.QName("urn:sap-com:document:sap:rfc:functions", "TABLE_OF_FIN_BANKID"), com.lexmark.SAPWebServices.AP.invoice.client.FIN_BANKID[].class, false, false);
        param.setItemQName(new javax.xml.namespace.QName("", "item"));
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "T_CARDS"), org.apache.axis.description.ParameterDesc.INOUT, new javax.xml.namespace.QName("urn:sap-com:document:sap:rfc:functions", "TABLE_OF_APAR_EBPP_CARD"), com.lexmark.SAPWebServices.AP.invoice.client.APAR_EBPP_CARD[].class, false, false);
        param.setItemQName(new javax.xml.namespace.QName("", "item"));
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "T_CARDTYPES"), org.apache.axis.description.ParameterDesc.INOUT, new javax.xml.namespace.QName("urn:sap-com:document:sap:rfc:functions", "TABLE_OF_APAR_EBPP_CARDTYPES"), com.lexmark.SAPWebServices.AP.invoice.client.APAR_EBPP_CARDTYPES[].class, false, false);
        param.setItemQName(new javax.xml.namespace.QName("", "item"));
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "T_DISPUTES"), org.apache.axis.description.ParameterDesc.INOUT, new javax.xml.namespace.QName("urn:sap-com:document:sap:rfc:functions", "TABLE_OF_FDM_EBPP_DMDISPUTES"), com.lexmark.SAPWebServices.AP.invoice.client.FDM_EBPP_DMDISPUTES[].class, false, false);
        param.setItemQName(new javax.xml.namespace.QName("", "item"));
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "T_DISPUTES_ALLOCATION"), org.apache.axis.description.ParameterDesc.INOUT, new javax.xml.namespace.QName("urn:sap-com:document:sap:rfc:functions", "TABLE_OF_EBPP_DM_ALLOCATION"), com.lexmark.SAPWebServices.AP.invoice.client.EBPP_DM_ALLOCATION[].class, false, false);
        param.setItemQName(new javax.xml.namespace.QName("", "item"));
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "T_INIT_DATA"), org.apache.axis.description.ParameterDesc.INOUT, new javax.xml.namespace.QName("urn:sap-com:document:sap:rfc:functions", "TABLE_OF_EBPP_INIT_ADD_DATA"), com.lexmark.SAPWebServices.AP.invoice.client.EBPP_INIT_ADD_DATA[].class, false, false);
        param.setItemQName(new javax.xml.namespace.QName("", "item"));
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "T_INVOICES"), org.apache.axis.description.ParameterDesc.INOUT, new javax.xml.namespace.QName("urn:sap-com:document:sap:rfc:functions", "TABLE_OF_EBPP_INVOICE"), com.lexmark.SAPWebServices.AP.invoice.client.EBPP_INVOICE[].class, false, false);
        param.setItemQName(new javax.xml.namespace.QName("", "item"));
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "T_ITEMS"), org.apache.axis.description.ParameterDesc.INOUT, new javax.xml.namespace.QName("urn:sap-com:document:sap:rfc:functions", "TABLE_OF_EBPP_ITEM"), com.lexmark.SAPWebServices.AP.invoice.client.EBPP_ITEM[].class, false, false);
        param.setItemQName(new javax.xml.namespace.QName("", "item"));
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "T_MESSAGES"), org.apache.axis.description.ParameterDesc.INOUT, new javax.xml.namespace.QName("urn:sap-com:document:sap:rfc:functions", "TABLE_OF_EBPP_MESSAGES"), com.lexmark.SAPWebServices.AP.invoice.client.EBPP_MESSAGES[].class, false, false);
        param.setItemQName(new javax.xml.namespace.QName("", "item"));
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "T_MYPAYMENTS"), org.apache.axis.description.ParameterDesc.INOUT, new javax.xml.namespace.QName("urn:sap-com:document:sap:rfc:functions", "TABLE_OF_EBPP_MYPAYMENTS"), com.lexmark.SAPWebServices.AP.invoice.client.EBPP_MYPAYMENTS[].class, false, false);
        param.setItemQName(new javax.xml.namespace.QName("", "item"));
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "T_PAYALLOCATION"), org.apache.axis.description.ParameterDesc.INOUT, new javax.xml.namespace.QName("urn:sap-com:document:sap:rfc:functions", "TABLE_OF_EBPP_PAYALLOCATION"), com.lexmark.SAPWebServices.AP.invoice.client.EBPP_PAYALLOCATION[].class, false, false);
        param.setItemQName(new javax.xml.namespace.QName("", "item"));
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "T_PAYEXPLANATION"), org.apache.axis.description.ParameterDesc.INOUT, new javax.xml.namespace.QName("urn:sap-com:document:sap:rfc:functions", "TABLE_OF_EBPP_PAYEXPLANATION"), com.lexmark.SAPWebServices.AP.invoice.client.EBPP_PAYEXPLANATION[].class, false, false);
        param.setItemQName(new javax.xml.namespace.QName("", "item"));
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "T_TOTALS"), org.apache.axis.description.ParameterDesc.INOUT, new javax.xml.namespace.QName("urn:sap-com:document:sap:rfc:functions", "TABLE_OF_EBPP_TOTALS"), com.lexmark.SAPWebServices.AP.invoice.client.EBPP_TOTALS[].class, false, false);
        param.setItemQName(new javax.xml.namespace.QName("", "item"));
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "E_CREDITS"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("urn:sap-com:document:sap:rfc:functions", "EBPP_CREDITS"), com.lexmark.SAPWebServices.AP.invoice.client.EBPP_CREDITS.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "E_PARTNER"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("urn:sap-com:document:sap:rfc:functions", "EBPP_PARTNER"), com.lexmark.SAPWebServices.AP.invoice.client.EBPP_PARTNER.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "E_RETURNCODE"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "RETURN"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("urn:sap-com:document:sap:rfc:functions", "BAPIRET1"), com.lexmark.SAPWebServices.AP.invoice.client.BAPIRET1.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[0] = oper;

    }

    public ZG_AP_GET_DATA_BindingStub() throws org.apache.axis.AxisFault {
         this(null);
    }

    public ZG_AP_GET_DATA_BindingStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
         this(service);
         super.cachedEndpoint = endpointURL;
    }

    public ZG_AP_GET_DATA_BindingStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
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
            qName = new javax.xml.namespace.QName("urn:sap-com:document:sap:rfc:functions", "APAR_EBPP_CARD");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SAPWebServices.AP.invoice.client.APAR_EBPP_CARD.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:sap-com:document:sap:rfc:functions", "APAR_EBPP_CARDTYPES");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SAPWebServices.AP.invoice.client.APAR_EBPP_CARDTYPES.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:sap-com:document:sap:rfc:functions", "BAPIRET1");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SAPWebServices.AP.invoice.client.BAPIRET1.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:sap-com:document:sap:rfc:functions", "char1");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("urn:sap-com:document:sap:rfc:functions", "char10");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("urn:sap-com:document:sap:rfc:functions", "char1024");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("urn:sap-com:document:sap:rfc:functions", "char12");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("urn:sap-com:document:sap:rfc:functions", "char120");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("urn:sap-com:document:sap:rfc:functions", "char128");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("urn:sap-com:document:sap:rfc:functions", "char140");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("urn:sap-com:document:sap:rfc:functions", "char16");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("urn:sap-com:document:sap:rfc:functions", "char18");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("urn:sap-com:document:sap:rfc:functions", "char2");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("urn:sap-com:document:sap:rfc:functions", "char20");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("urn:sap-com:document:sap:rfc:functions", "char220");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("urn:sap-com:document:sap:rfc:functions", "char24");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("urn:sap-com:document:sap:rfc:functions", "char3");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("urn:sap-com:document:sap:rfc:functions", "char30");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("urn:sap-com:document:sap:rfc:functions", "char300");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("urn:sap-com:document:sap:rfc:functions", "char32");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("urn:sap-com:document:sap:rfc:functions", "char4");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("urn:sap-com:document:sap:rfc:functions", "char40");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("urn:sap-com:document:sap:rfc:functions", "char4096");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("urn:sap-com:document:sap:rfc:functions", "char43");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("urn:sap-com:document:sap:rfc:functions", "char50");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("urn:sap-com:document:sap:rfc:functions", "char6");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("urn:sap-com:document:sap:rfc:functions", "char60");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("urn:sap-com:document:sap:rfc:functions", "char8");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("urn:sap-com:document:sap:rfc:functions", "char80");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("urn:sap-com:document:sap:rfc:functions", "cuky5");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("urn:sap-com:document:sap:rfc:functions", "date10");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("urn:sap-com:document:sap:rfc:functions", "decimal15.0");
            cachedSerQNames.add(qName);
            cls = java.math.BigDecimal.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("urn:sap-com:document:sap:rfc:functions", "decimal23.4");
            cachedSerQNames.add(qName);
            cls = java.math.BigDecimal.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("urn:sap-com:document:sap:rfc:functions", "decimal5.3");
            cachedSerQNames.add(qName);
            cls = java.math.BigDecimal.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("urn:sap-com:document:sap:rfc:functions", "EBPP_ADDSEL");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SAPWebServices.AP.invoice.client.EBPP_ADDSEL.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:sap-com:document:sap:rfc:functions", "EBPP_ALLOCATION");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SAPWebServices.AP.invoice.client.EBPP_ALLOCATION.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:sap-com:document:sap:rfc:functions", "EBPP_CONTROL");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SAPWebServices.AP.invoice.client.EBPP_CONTROL.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:sap-com:document:sap:rfc:functions", "EBPP_CREDITS");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SAPWebServices.AP.invoice.client.EBPP_CREDITS.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:sap-com:document:sap:rfc:functions", "EBPP_DM_ALLOCATION");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SAPWebServices.AP.invoice.client.EBPP_DM_ALLOCATION.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:sap-com:document:sap:rfc:functions", "EBPP_INIT_ADD_DATA");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SAPWebServices.AP.invoice.client.EBPP_INIT_ADD_DATA.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:sap-com:document:sap:rfc:functions", "EBPP_INVOICE");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SAPWebServices.AP.invoice.client.EBPP_INVOICE.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:sap-com:document:sap:rfc:functions", "EBPP_ITEM");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SAPWebServices.AP.invoice.client.EBPP_ITEM.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:sap-com:document:sap:rfc:functions", "EBPP_MESSAGES");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SAPWebServices.AP.invoice.client.EBPP_MESSAGES.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:sap-com:document:sap:rfc:functions", "EBPP_MYPAYMENTS");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SAPWebServices.AP.invoice.client.EBPP_MYPAYMENTS.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:sap-com:document:sap:rfc:functions", "EBPP_PARTNER");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SAPWebServices.AP.invoice.client.EBPP_PARTNER.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:sap-com:document:sap:rfc:functions", "EBPP_PAYALLOCATION");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SAPWebServices.AP.invoice.client.EBPP_PAYALLOCATION.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:sap-com:document:sap:rfc:functions", "EBPP_PAYEXPLANATION");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SAPWebServices.AP.invoice.client.EBPP_PAYEXPLANATION.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:sap-com:document:sap:rfc:functions", "EBPP_TOTALS");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SAPWebServices.AP.invoice.client.EBPP_TOTALS.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:sap-com:document:sap:rfc:functions", "FDM_EBPP_DMDISPUTES");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SAPWebServices.AP.invoice.client.FDM_EBPP_DMDISPUTES.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:sap-com:document:sap:rfc:functions", "FDM_EBPP_DMSELECTION");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SAPWebServices.AP.invoice.client.FDM_EBPP_DMSELECTION.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:sap-com:document:sap:rfc:functions", "FIN_BANKID");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SAPWebServices.AP.invoice.client.FIN_BANKID.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:sap-com:document:sap:rfc:functions", "numeric3");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("urn:sap-com:document:sap:rfc:functions", "numeric4");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("urn:sap-com:document:sap:rfc:functions", "numeric6");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("urn:sap-com:document:sap:rfc:functions", "TABLE_OF_APAR_EBPP_CARD");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SAPWebServices.AP.invoice.client.APAR_EBPP_CARD[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("urn:sap-com:document:sap:rfc:functions", "APAR_EBPP_CARD");
            qName2 = new javax.xml.namespace.QName("", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("urn:sap-com:document:sap:rfc:functions", "TABLE_OF_APAR_EBPP_CARDTYPES");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SAPWebServices.AP.invoice.client.APAR_EBPP_CARDTYPES[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("urn:sap-com:document:sap:rfc:functions", "APAR_EBPP_CARDTYPES");
            qName2 = new javax.xml.namespace.QName("", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("urn:sap-com:document:sap:rfc:functions", "TABLE_OF_EBPP_ALLOCATION");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SAPWebServices.AP.invoice.client.EBPP_ALLOCATION[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("urn:sap-com:document:sap:rfc:functions", "EBPP_ALLOCATION");
            qName2 = new javax.xml.namespace.QName("", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("urn:sap-com:document:sap:rfc:functions", "TABLE_OF_EBPP_DM_ALLOCATION");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SAPWebServices.AP.invoice.client.EBPP_DM_ALLOCATION[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("urn:sap-com:document:sap:rfc:functions", "EBPP_DM_ALLOCATION");
            qName2 = new javax.xml.namespace.QName("", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("urn:sap-com:document:sap:rfc:functions", "TABLE_OF_EBPP_INIT_ADD_DATA");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SAPWebServices.AP.invoice.client.EBPP_INIT_ADD_DATA[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("urn:sap-com:document:sap:rfc:functions", "EBPP_INIT_ADD_DATA");
            qName2 = new javax.xml.namespace.QName("", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("urn:sap-com:document:sap:rfc:functions", "TABLE_OF_EBPP_INVOICE");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SAPWebServices.AP.invoice.client.EBPP_INVOICE[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("urn:sap-com:document:sap:rfc:functions", "EBPP_INVOICE");
            qName2 = new javax.xml.namespace.QName("", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("urn:sap-com:document:sap:rfc:functions", "TABLE_OF_EBPP_ITEM");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SAPWebServices.AP.invoice.client.EBPP_ITEM[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("urn:sap-com:document:sap:rfc:functions", "EBPP_ITEM");
            qName2 = new javax.xml.namespace.QName("", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("urn:sap-com:document:sap:rfc:functions", "TABLE_OF_EBPP_MESSAGES");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SAPWebServices.AP.invoice.client.EBPP_MESSAGES[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("urn:sap-com:document:sap:rfc:functions", "EBPP_MESSAGES");
            qName2 = new javax.xml.namespace.QName("", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("urn:sap-com:document:sap:rfc:functions", "TABLE_OF_EBPP_MYPAYMENTS");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SAPWebServices.AP.invoice.client.EBPP_MYPAYMENTS[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("urn:sap-com:document:sap:rfc:functions", "EBPP_MYPAYMENTS");
            qName2 = new javax.xml.namespace.QName("", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("urn:sap-com:document:sap:rfc:functions", "TABLE_OF_EBPP_PAYALLOCATION");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SAPWebServices.AP.invoice.client.EBPP_PAYALLOCATION[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("urn:sap-com:document:sap:rfc:functions", "EBPP_PAYALLOCATION");
            qName2 = new javax.xml.namespace.QName("", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("urn:sap-com:document:sap:rfc:functions", "TABLE_OF_EBPP_PAYEXPLANATION");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SAPWebServices.AP.invoice.client.EBPP_PAYEXPLANATION[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("urn:sap-com:document:sap:rfc:functions", "EBPP_PAYEXPLANATION");
            qName2 = new javax.xml.namespace.QName("", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("urn:sap-com:document:sap:rfc:functions", "TABLE_OF_EBPP_TOTALS");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SAPWebServices.AP.invoice.client.EBPP_TOTALS[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("urn:sap-com:document:sap:rfc:functions", "EBPP_TOTALS");
            qName2 = new javax.xml.namespace.QName("", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("urn:sap-com:document:sap:rfc:functions", "TABLE_OF_FDM_EBPP_DMDISPUTES");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SAPWebServices.AP.invoice.client.FDM_EBPP_DMDISPUTES[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("urn:sap-com:document:sap:rfc:functions", "FDM_EBPP_DMDISPUTES");
            qName2 = new javax.xml.namespace.QName("", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("urn:sap-com:document:sap:rfc:functions", "TABLE_OF_FIN_BANKID");
            cachedSerQNames.add(qName);
            cls = com.lexmark.SAPWebServices.AP.invoice.client.FIN_BANKID[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("urn:sap-com:document:sap:rfc:functions", "FIN_BANKID");
            qName2 = new javax.xml.namespace.QName("", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

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

    public void APAR_EBPP_GET_DATA(com.lexmark.SAPWebServices.AP.invoice.client.EBPP_ADDSEL i_ADDSEL, com.lexmark.SAPWebServices.AP.invoice.client.EBPP_CONTROL i_CONTROLDATA, com.lexmark.SAPWebServices.AP.invoice.client.FDM_EBPP_DMSELECTION i_DMSELECTION, com.lexmark.SAPWebServices.AP.invoice.client.EBPP_PARTNER i_PARTNER, com.lexmark.SAPWebServices.AP.invoice.client.holders.TABLE_OF_EBPP_ALLOCATIONHolder t_ALLOCATION, com.lexmark.SAPWebServices.AP.invoice.client.holders.TABLE_OF_FIN_BANKIDHolder t_BANKS, com.lexmark.SAPWebServices.AP.invoice.client.holders.TABLE_OF_APAR_EBPP_CARDHolder t_CARDS, com.lexmark.SAPWebServices.AP.invoice.client.holders.TABLE_OF_APAR_EBPP_CARDTYPESHolder t_CARDTYPES, com.lexmark.SAPWebServices.AP.invoice.client.holders.TABLE_OF_FDM_EBPP_DMDISPUTESHolder t_DISPUTES, com.lexmark.SAPWebServices.AP.invoice.client.holders.TABLE_OF_EBPP_DM_ALLOCATIONHolder t_DISPUTES_ALLOCATION, com.lexmark.SAPWebServices.AP.invoice.client.holders.TABLE_OF_EBPP_INIT_ADD_DATAHolder t_INIT_DATA, com.lexmark.SAPWebServices.AP.invoice.client.holders.TABLE_OF_EBPP_INVOICEHolder t_INVOICES, com.lexmark.SAPWebServices.AP.invoice.client.holders.TABLE_OF_EBPP_ITEMHolder t_ITEMS, com.lexmark.SAPWebServices.AP.invoice.client.holders.TABLE_OF_EBPP_MESSAGESHolder t_MESSAGES, com.lexmark.SAPWebServices.AP.invoice.client.holders.TABLE_OF_EBPP_MYPAYMENTSHolder t_MYPAYMENTS, com.lexmark.SAPWebServices.AP.invoice.client.holders.TABLE_OF_EBPP_PAYALLOCATIONHolder t_PAYALLOCATION, com.lexmark.SAPWebServices.AP.invoice.client.holders.TABLE_OF_EBPP_PAYEXPLANATIONHolder t_PAYEXPLANATION, com.lexmark.SAPWebServices.AP.invoice.client.holders.TABLE_OF_EBPP_TOTALSHolder t_TOTALS, com.lexmark.SAPWebServices.AP.invoice.client.holders.EBPP_CREDITSHolder e_CREDITS, com.lexmark.SAPWebServices.AP.invoice.client.holders.EBPP_PARTNERHolder e_PARTNER, javax.xml.rpc.holders.IntHolder e_RETURNCODE, com.lexmark.SAPWebServices.AP.invoice.client.holders.BAPIRET1Holder RETURN) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[0]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("urn:sap-com:document:sap:rfc:functions:ZG_AP_GET_DATA:APAR_EBPP_GET_DATARequest");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:sap-com:document:sap:rfc:functions", "APAR_EBPP_GET_DATA"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        
	 java.lang.Object _resp = _call.invoke(new java.lang.Object[] {i_ADDSEL, i_CONTROLDATA, i_DMSELECTION, i_PARTNER, t_ALLOCATION.value, t_BANKS.value, t_CARDS.value, t_CARDTYPES.value, t_DISPUTES.value, t_DISPUTES_ALLOCATION.value, t_INIT_DATA.value, t_INVOICES.value, t_ITEMS.value, t_MESSAGES.value, t_MYPAYMENTS.value, t_PAYALLOCATION.value, t_PAYEXPLANATION.value, t_TOTALS.value});

	 /*String requestXML = _call.getMessageContext().getRequestMessage().getSOAPPartAsString(); 
	 logger.info("*********************requestXML***************************");
	 logger.info(requestXML);
	 String responseXML = _call.getMessageContext().getResponseMessage().getSOAPPartAsString();
	 logger.info("*********************responseXML***************************");
	 logger.info(responseXML);*/
	 
        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            java.util.Map _output;
            _output = _call.getOutputParams();
           // logger.info("before t_allocation");
            try {
                t_ALLOCATION.value = (com.lexmark.SAPWebServices.AP.invoice.client.EBPP_ALLOCATION[]) _output.get(new javax.xml.namespace.QName("", "T_ALLOCATION"));
            } catch (java.lang.Exception _exception) {
                t_ALLOCATION.value = (com.lexmark.SAPWebServices.AP.invoice.client.EBPP_ALLOCATION[]) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "T_ALLOCATION")), com.lexmark.SAPWebServices.AP.invoice.client.EBPP_ALLOCATION[].class);
            }
            try {
                t_BANKS.value = (com.lexmark.SAPWebServices.AP.invoice.client.FIN_BANKID[]) _output.get(new javax.xml.namespace.QName("", "T_BANKS"));
            } catch (java.lang.Exception _exception) {
                t_BANKS.value = (com.lexmark.SAPWebServices.AP.invoice.client.FIN_BANKID[]) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "T_BANKS")), com.lexmark.SAPWebServices.AP.invoice.client.FIN_BANKID[].class);
            }
            try {
                t_CARDS.value = (com.lexmark.SAPWebServices.AP.invoice.client.APAR_EBPP_CARD[]) _output.get(new javax.xml.namespace.QName("", "T_CARDS"));
            } catch (java.lang.Exception _exception) {
                t_CARDS.value = (com.lexmark.SAPWebServices.AP.invoice.client.APAR_EBPP_CARD[]) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "T_CARDS")), com.lexmark.SAPWebServices.AP.invoice.client.APAR_EBPP_CARD[].class);
            }
            try {
                t_CARDTYPES.value = (com.lexmark.SAPWebServices.AP.invoice.client.APAR_EBPP_CARDTYPES[]) _output.get(new javax.xml.namespace.QName("", "T_CARDTYPES"));
            } catch (java.lang.Exception _exception) {
                t_CARDTYPES.value = (com.lexmark.SAPWebServices.AP.invoice.client.APAR_EBPP_CARDTYPES[]) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "T_CARDTYPES")), com.lexmark.SAPWebServices.AP.invoice.client.APAR_EBPP_CARDTYPES[].class);
            }
            try {
                t_DISPUTES.value = (com.lexmark.SAPWebServices.AP.invoice.client.FDM_EBPP_DMDISPUTES[]) _output.get(new javax.xml.namespace.QName("", "T_DISPUTES"));
            } catch (java.lang.Exception _exception) {
                t_DISPUTES.value = (com.lexmark.SAPWebServices.AP.invoice.client.FDM_EBPP_DMDISPUTES[]) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "T_DISPUTES")), com.lexmark.SAPWebServices.AP.invoice.client.FDM_EBPP_DMDISPUTES[].class);
            }
            try {
                t_DISPUTES_ALLOCATION.value = (com.lexmark.SAPWebServices.AP.invoice.client.EBPP_DM_ALLOCATION[]) _output.get(new javax.xml.namespace.QName("", "T_DISPUTES_ALLOCATION"));
            } catch (java.lang.Exception _exception) {
                t_DISPUTES_ALLOCATION.value = (com.lexmark.SAPWebServices.AP.invoice.client.EBPP_DM_ALLOCATION[]) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "T_DISPUTES_ALLOCATION")), com.lexmark.SAPWebServices.AP.invoice.client.EBPP_DM_ALLOCATION[].class);
            }
            try {
                t_INIT_DATA.value = (com.lexmark.SAPWebServices.AP.invoice.client.EBPP_INIT_ADD_DATA[]) _output.get(new javax.xml.namespace.QName("", "T_INIT_DATA"));
            } catch (java.lang.Exception _exception) {
                t_INIT_DATA.value = (com.lexmark.SAPWebServices.AP.invoice.client.EBPP_INIT_ADD_DATA[]) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "T_INIT_DATA")), com.lexmark.SAPWebServices.AP.invoice.client.EBPP_INIT_ADD_DATA[].class);
            }
            try {
                t_INVOICES.value = (com.lexmark.SAPWebServices.AP.invoice.client.EBPP_INVOICE[]) _output.get(new javax.xml.namespace.QName("", "T_INVOICES"));
            } catch (java.lang.Exception _exception) {
                t_INVOICES.value = (com.lexmark.SAPWebServices.AP.invoice.client.EBPP_INVOICE[]) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "T_INVOICES")), com.lexmark.SAPWebServices.AP.invoice.client.EBPP_INVOICE[].class);
            }
            try {
                t_ITEMS.value = (com.lexmark.SAPWebServices.AP.invoice.client.EBPP_ITEM[]) _output.get(new javax.xml.namespace.QName("", "T_ITEMS"));
            } catch (java.lang.Exception _exception) {
                t_ITEMS.value = (com.lexmark.SAPWebServices.AP.invoice.client.EBPP_ITEM[]) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "T_ITEMS")), com.lexmark.SAPWebServices.AP.invoice.client.EBPP_ITEM[].class);
            }
            try {
                t_MESSAGES.value = (com.lexmark.SAPWebServices.AP.invoice.client.EBPP_MESSAGES[]) _output.get(new javax.xml.namespace.QName("", "T_MESSAGES"));
            } catch (java.lang.Exception _exception) {
                t_MESSAGES.value = (com.lexmark.SAPWebServices.AP.invoice.client.EBPP_MESSAGES[]) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "T_MESSAGES")), com.lexmark.SAPWebServices.AP.invoice.client.EBPP_MESSAGES[].class);
            }
            try {
                t_MYPAYMENTS.value = (com.lexmark.SAPWebServices.AP.invoice.client.EBPP_MYPAYMENTS[]) _output.get(new javax.xml.namespace.QName("", "T_MYPAYMENTS"));
            } catch (java.lang.Exception _exception) {
                t_MYPAYMENTS.value = (com.lexmark.SAPWebServices.AP.invoice.client.EBPP_MYPAYMENTS[]) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "T_MYPAYMENTS")), com.lexmark.SAPWebServices.AP.invoice.client.EBPP_MYPAYMENTS[].class);
            }
            try {
                t_PAYALLOCATION.value = (com.lexmark.SAPWebServices.AP.invoice.client.EBPP_PAYALLOCATION[]) _output.get(new javax.xml.namespace.QName("", "T_PAYALLOCATION"));
            } catch (java.lang.Exception _exception) {
                t_PAYALLOCATION.value = (com.lexmark.SAPWebServices.AP.invoice.client.EBPP_PAYALLOCATION[]) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "T_PAYALLOCATION")), com.lexmark.SAPWebServices.AP.invoice.client.EBPP_PAYALLOCATION[].class);
            }
            try {
                t_PAYEXPLANATION.value = (com.lexmark.SAPWebServices.AP.invoice.client.EBPP_PAYEXPLANATION[]) _output.get(new javax.xml.namespace.QName("", "T_PAYEXPLANATION"));
            } catch (java.lang.Exception _exception) {
                t_PAYEXPLANATION.value = (com.lexmark.SAPWebServices.AP.invoice.client.EBPP_PAYEXPLANATION[]) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "T_PAYEXPLANATION")), com.lexmark.SAPWebServices.AP.invoice.client.EBPP_PAYEXPLANATION[].class);
            }
            try {
                t_TOTALS.value = (com.lexmark.SAPWebServices.AP.invoice.client.EBPP_TOTALS[]) _output.get(new javax.xml.namespace.QName("", "T_TOTALS"));
            } catch (java.lang.Exception _exception) {
                t_TOTALS.value = (com.lexmark.SAPWebServices.AP.invoice.client.EBPP_TOTALS[]) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "T_TOTALS")), com.lexmark.SAPWebServices.AP.invoice.client.EBPP_TOTALS[].class);
            }
            try {
                e_CREDITS.value = (com.lexmark.SAPWebServices.AP.invoice.client.EBPP_CREDITS) _output.get(new javax.xml.namespace.QName("", "E_CREDITS"));
            } catch (java.lang.Exception _exception) {
                e_CREDITS.value = (com.lexmark.SAPWebServices.AP.invoice.client.EBPP_CREDITS) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "E_CREDITS")), com.lexmark.SAPWebServices.AP.invoice.client.EBPP_CREDITS.class);
            }
            try {
                e_PARTNER.value = (com.lexmark.SAPWebServices.AP.invoice.client.EBPP_PARTNER) _output.get(new javax.xml.namespace.QName("", "E_PARTNER"));
            } catch (java.lang.Exception _exception) {
                e_PARTNER.value = (com.lexmark.SAPWebServices.AP.invoice.client.EBPP_PARTNER) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "E_PARTNER")), com.lexmark.SAPWebServices.AP.invoice.client.EBPP_PARTNER.class);
            }
            try {
                e_RETURNCODE.value = ((java.lang.Integer) _output.get(new javax.xml.namespace.QName("", "E_RETURNCODE"))).intValue();
            } catch (java.lang.Exception _exception) {
                e_RETURNCODE.value = ((java.lang.Integer) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "E_RETURNCODE")), int.class)).intValue();
            }
            try {
                RETURN.value = (com.lexmark.SAPWebServices.AP.invoice.client.BAPIRET1) _output.get(new javax.xml.namespace.QName("", "RETURN"));
            } catch (java.lang.Exception _exception) {
                RETURN.value = (com.lexmark.SAPWebServices.AP.invoice.client.BAPIRET1) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "RETURN")), com.lexmark.SAPWebServices.AP.invoice.client.BAPIRET1.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

}
