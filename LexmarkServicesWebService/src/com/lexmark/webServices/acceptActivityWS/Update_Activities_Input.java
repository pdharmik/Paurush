/**
 * Update_Activities_Input.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.webServices.acceptActivityWS;

public class Update_Activities_Input  implements java.io.Serializable {
    private java.lang.String process_spcInstance_spcId;

    private java.lang.String object_spcId;

    private java.lang.String siebel_spcOperation_spcObject_spcId;

    private java.lang.String SR_spcNumber;

    private java.lang.String response_spcMessage;

    private java.lang.String numRows;

    private java.lang.String error_spcCode;

    private java.lang.String SR_spcId;

    private java.lang.String error_spcMessage;

    public Update_Activities_Input() {
    }

    public Update_Activities_Input(
           java.lang.String process_spcInstance_spcId,
           java.lang.String object_spcId,
           java.lang.String siebel_spcOperation_spcObject_spcId,
           java.lang.String SR_spcNumber,
           java.lang.String response_spcMessage,
           java.lang.String numRows,
           java.lang.String error_spcCode,
           java.lang.String SR_spcId,
           java.lang.String error_spcMessage) {
           this.process_spcInstance_spcId = process_spcInstance_spcId;
           this.object_spcId = object_spcId;
           this.siebel_spcOperation_spcObject_spcId = siebel_spcOperation_spcObject_spcId;
           this.SR_spcNumber = SR_spcNumber;
           this.response_spcMessage = response_spcMessage;
           this.numRows = numRows;
           this.error_spcCode = error_spcCode;
           this.SR_spcId = SR_spcId;
           this.error_spcMessage = error_spcMessage;
    }


    /**
     * Gets the process_spcInstance_spcId value for this Update_Activities_Input.
     * 
     * @return process_spcInstance_spcId
     */
    public java.lang.String getProcess_spcInstance_spcId() {
        return process_spcInstance_spcId;
    }


    /**
     * Sets the process_spcInstance_spcId value for this Update_Activities_Input.
     * 
     * @param process_spcInstance_spcId
     */
    public void setProcess_spcInstance_spcId(java.lang.String process_spcInstance_spcId) {
        this.process_spcInstance_spcId = process_spcInstance_spcId;
    }


    /**
     * Gets the object_spcId value for this Update_Activities_Input.
     * 
     * @return object_spcId
     */
    public java.lang.String getObject_spcId() {
        return object_spcId;
    }


    /**
     * Sets the object_spcId value for this Update_Activities_Input.
     * 
     * @param object_spcId
     */
    public void setObject_spcId(java.lang.String object_spcId) {
        this.object_spcId = object_spcId;
    }


    /**
     * Gets the siebel_spcOperation_spcObject_spcId value for this Update_Activities_Input.
     * 
     * @return siebel_spcOperation_spcObject_spcId
     */
    public java.lang.String getSiebel_spcOperation_spcObject_spcId() {
        return siebel_spcOperation_spcObject_spcId;
    }


    /**
     * Sets the siebel_spcOperation_spcObject_spcId value for this Update_Activities_Input.
     * 
     * @param siebel_spcOperation_spcObject_spcId
     */
    public void setSiebel_spcOperation_spcObject_spcId(java.lang.String siebel_spcOperation_spcObject_spcId) {
        this.siebel_spcOperation_spcObject_spcId = siebel_spcOperation_spcObject_spcId;
    }


    /**
     * Gets the SR_spcNumber value for this Update_Activities_Input.
     * 
     * @return SR_spcNumber
     */
    public java.lang.String getSR_spcNumber() {
        return SR_spcNumber;
    }


    /**
     * Sets the SR_spcNumber value for this Update_Activities_Input.
     * 
     * @param SR_spcNumber
     */
    public void setSR_spcNumber(java.lang.String SR_spcNumber) {
        this.SR_spcNumber = SR_spcNumber;
    }


    /**
     * Gets the response_spcMessage value for this Update_Activities_Input.
     * 
     * @return response_spcMessage
     */
    public java.lang.String getResponse_spcMessage() {
        return response_spcMessage;
    }


    /**
     * Sets the response_spcMessage value for this Update_Activities_Input.
     * 
     * @param response_spcMessage
     */
    public void setResponse_spcMessage(java.lang.String response_spcMessage) {
        this.response_spcMessage = response_spcMessage;
    }


    /**
     * Gets the numRows value for this Update_Activities_Input.
     * 
     * @return numRows
     */
    public java.lang.String getNumRows() {
        return numRows;
    }


    /**
     * Sets the numRows value for this Update_Activities_Input.
     * 
     * @param numRows
     */
    public void setNumRows(java.lang.String numRows) {
        this.numRows = numRows;
    }


    /**
     * Gets the error_spcCode value for this Update_Activities_Input.
     * 
     * @return error_spcCode
     */
    public java.lang.String getError_spcCode() {
        return error_spcCode;
    }


    /**
     * Sets the error_spcCode value for this Update_Activities_Input.
     * 
     * @param error_spcCode
     */
    public void setError_spcCode(java.lang.String error_spcCode) {
        this.error_spcCode = error_spcCode;
    }


    /**
     * Gets the SR_spcId value for this Update_Activities_Input.
     * 
     * @return SR_spcId
     */
    public java.lang.String getSR_spcId() {
        return SR_spcId;
    }


    /**
     * Sets the SR_spcId value for this Update_Activities_Input.
     * 
     * @param SR_spcId
     */
    public void setSR_spcId(java.lang.String SR_spcId) {
        this.SR_spcId = SR_spcId;
    }


    /**
     * Gets the error_spcMessage value for this Update_Activities_Input.
     * 
     * @return error_spcMessage
     */
    public java.lang.String getError_spcMessage() {
        return error_spcMessage;
    }


    /**
     * Sets the error_spcMessage value for this Update_Activities_Input.
     * 
     * @param error_spcMessage
     */
    public void setError_spcMessage(java.lang.String error_spcMessage) {
        this.error_spcMessage = error_spcMessage;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Update_Activities_Input)) return false;
        Update_Activities_Input other = (Update_Activities_Input) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.process_spcInstance_spcId==null && other.getProcess_spcInstance_spcId()==null) || 
             (this.process_spcInstance_spcId!=null &&
              this.process_spcInstance_spcId.equals(other.getProcess_spcInstance_spcId()))) &&
            ((this.object_spcId==null && other.getObject_spcId()==null) || 
             (this.object_spcId!=null &&
              this.object_spcId.equals(other.getObject_spcId()))) &&
            ((this.siebel_spcOperation_spcObject_spcId==null && other.getSiebel_spcOperation_spcObject_spcId()==null) || 
             (this.siebel_spcOperation_spcObject_spcId!=null &&
              this.siebel_spcOperation_spcObject_spcId.equals(other.getSiebel_spcOperation_spcObject_spcId()))) &&
            ((this.SR_spcNumber==null && other.getSR_spcNumber()==null) || 
             (this.SR_spcNumber!=null &&
              this.SR_spcNumber.equals(other.getSR_spcNumber()))) &&
            ((this.response_spcMessage==null && other.getResponse_spcMessage()==null) || 
             (this.response_spcMessage!=null &&
              this.response_spcMessage.equals(other.getResponse_spcMessage()))) &&
            ((this.numRows==null && other.getNumRows()==null) || 
             (this.numRows!=null &&
              this.numRows.equals(other.getNumRows()))) &&
            ((this.error_spcCode==null && other.getError_spcCode()==null) || 
             (this.error_spcCode!=null &&
              this.error_spcCode.equals(other.getError_spcCode()))) &&
            ((this.SR_spcId==null && other.getSR_spcId()==null) || 
             (this.SR_spcId!=null &&
              this.SR_spcId.equals(other.getSR_spcId()))) &&
            ((this.error_spcMessage==null && other.getError_spcMessage()==null) || 
             (this.error_spcMessage!=null &&
              this.error_spcMessage.equals(other.getError_spcMessage())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getProcess_spcInstance_spcId() != null) {
            _hashCode += getProcess_spcInstance_spcId().hashCode();
        }
        if (getObject_spcId() != null) {
            _hashCode += getObject_spcId().hashCode();
        }
        if (getSiebel_spcOperation_spcObject_spcId() != null) {
            _hashCode += getSiebel_spcOperation_spcObject_spcId().hashCode();
        }
        if (getSR_spcNumber() != null) {
            _hashCode += getSR_spcNumber().hashCode();
        }
        if (getResponse_spcMessage() != null) {
            _hashCode += getResponse_spcMessage().hashCode();
        }
        if (getNumRows() != null) {
            _hashCode += getNumRows().hashCode();
        }
        if (getError_spcCode() != null) {
            _hashCode += getError_spcCode().hashCode();
        }
        if (getSR_spcId() != null) {
            _hashCode += getSR_spcId().hashCode();
        }
        if (getError_spcMessage() != null) {
            _hashCode += getError_spcMessage().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Update_Activities_Input.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://siebel.com/CustomUI", ">Update_Activities_Input"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("process_spcInstance_spcId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://siebel.com/CustomUI", "Process_spcInstance_spcId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("object_spcId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://siebel.com/CustomUI", "Object_spcId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("siebel_spcOperation_spcObject_spcId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://siebel.com/CustomUI", "Siebel_spcOperation_spcObject_spcId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SR_spcNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("http://siebel.com/CustomUI", "SR_spcNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("response_spcMessage");
        elemField.setXmlName(new javax.xml.namespace.QName("http://siebel.com/CustomUI", "Response_spcMessage"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numRows");
        elemField.setXmlName(new javax.xml.namespace.QName("http://siebel.com/CustomUI", "NumRows"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("error_spcCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://siebel.com/CustomUI", "Error_spcCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SR_spcId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://siebel.com/CustomUI", "SR_spcId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("error_spcMessage");
        elemField.setXmlName(new javax.xml.namespace.QName("http://siebel.com/CustomUI", "Error_spcMessage"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
