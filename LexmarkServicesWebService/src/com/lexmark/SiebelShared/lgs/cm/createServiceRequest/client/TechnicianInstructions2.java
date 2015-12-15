/**
 * TechnicianInstructions2.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client;

public class TechnicianInstructions2  implements java.io.Serializable {
    private java.lang.String instructionDate;

    private java.lang.String instructionType;

    private java.lang.String instruction;

    public TechnicianInstructions2() {
    }

    public TechnicianInstructions2(
           java.lang.String instructionDate,
           java.lang.String instructionType,
           java.lang.String instruction) {
           this.instructionDate = instructionDate;
           this.instructionType = instructionType;
           this.instruction = instruction;
    }


    /**
     * Gets the instructionDate value for this TechnicianInstructions2.
     * 
     * @return instructionDate
     */
    public java.lang.String getInstructionDate() {
        return instructionDate;
    }


    /**
     * Sets the instructionDate value for this TechnicianInstructions2.
     * 
     * @param instructionDate
     */
    public void setInstructionDate(java.lang.String instructionDate) {
        this.instructionDate = instructionDate;
    }


    /**
     * Gets the instructionType value for this TechnicianInstructions2.
     * 
     * @return instructionType
     */
    public java.lang.String getInstructionType() {
        return instructionType;
    }


    /**
     * Sets the instructionType value for this TechnicianInstructions2.
     * 
     * @param instructionType
     */
    public void setInstructionType(java.lang.String instructionType) {
        this.instructionType = instructionType;
    }


    /**
     * Gets the instruction value for this TechnicianInstructions2.
     * 
     * @return instruction
     */
    public java.lang.String getInstruction() {
        return instruction;
    }


    /**
     * Sets the instruction value for this TechnicianInstructions2.
     * 
     * @param instruction
     */
    public void setInstruction(java.lang.String instruction) {
        this.instruction = instruction;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TechnicianInstructions2)) return false;
        TechnicianInstructions2 other = (TechnicianInstructions2) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.instructionDate==null && other.getInstructionDate()==null) || 
             (this.instructionDate!=null &&
              this.instructionDate.equals(other.getInstructionDate()))) &&
            ((this.instructionType==null && other.getInstructionType()==null) || 
             (this.instructionType!=null &&
              this.instructionType.equals(other.getInstructionType()))) &&
            ((this.instruction==null && other.getInstruction()==null) || 
             (this.instruction!=null &&
              this.instruction.equals(other.getInstruction())));
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
        if (getInstructionDate() != null) {
            _hashCode += getInstructionDate().hashCode();
        }
        if (getInstructionType() != null) {
            _hashCode += getInstructionType().hashCode();
        }
        if (getInstruction() != null) {
            _hashCode += getInstruction().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TechnicianInstructions2.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "TechnicianInstructions2"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("instructionDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "InstructionDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("instructionType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "InstructionType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("instruction");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Instruction"));
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
