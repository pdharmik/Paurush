/**
 * SiebelActivityNotes.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.SiebelShared.createServiceRequest.client;

public class SiebelActivityNotes  implements java.io.Serializable {
    private java.lang.String noteId;

    private java.lang.String noteDate;

    private com.lexmark.SiebelShared.createServiceRequest.client.NoteAuthor noteAuthor;

    private java.lang.String noteDetails;

    private java.lang.String noteUpdateFlag;

    public SiebelActivityNotes() {
    }

    public SiebelActivityNotes(
           java.lang.String noteId,
           java.lang.String noteDate,
           com.lexmark.SiebelShared.createServiceRequest.client.NoteAuthor noteAuthor,
           java.lang.String noteDetails,
           java.lang.String noteUpdateFlag) {
           this.noteId = noteId;
           this.noteDate = noteDate;
           this.noteAuthor = noteAuthor;
           this.noteDetails = noteDetails;
           this.noteUpdateFlag = noteUpdateFlag;
    }


    /**
     * Gets the noteId value for this SiebelActivityNotes.
     * 
     * @return noteId
     */
    public java.lang.String getNoteId() {
        return noteId;
    }


    /**
     * Sets the noteId value for this SiebelActivityNotes.
     * 
     * @param noteId
     */
    public void setNoteId(java.lang.String noteId) {
        this.noteId = noteId;
    }


    /**
     * Gets the noteDate value for this SiebelActivityNotes.
     * 
     * @return noteDate
     */
    public java.lang.String getNoteDate() {
        return noteDate;
    }


    /**
     * Sets the noteDate value for this SiebelActivityNotes.
     * 
     * @param noteDate
     */
    public void setNoteDate(java.lang.String noteDate) {
        this.noteDate = noteDate;
    }


    /**
     * Gets the noteAuthor value for this SiebelActivityNotes.
     * 
     * @return noteAuthor
     */
    public com.lexmark.SiebelShared.createServiceRequest.client.NoteAuthor getNoteAuthor() {
        return noteAuthor;
    }


    /**
     * Sets the noteAuthor value for this SiebelActivityNotes.
     * 
     * @param noteAuthor
     */
    public void setNoteAuthor(com.lexmark.SiebelShared.createServiceRequest.client.NoteAuthor noteAuthor) {
        this.noteAuthor = noteAuthor;
    }


    /**
     * Gets the noteDetails value for this SiebelActivityNotes.
     * 
     * @return noteDetails
     */
    public java.lang.String getNoteDetails() {
        return noteDetails;
    }


    /**
     * Sets the noteDetails value for this SiebelActivityNotes.
     * 
     * @param noteDetails
     */
    public void setNoteDetails(java.lang.String noteDetails) {
        this.noteDetails = noteDetails;
    }


    /**
     * Gets the noteUpdateFlag value for this SiebelActivityNotes.
     * 
     * @return noteUpdateFlag
     */
    public java.lang.String getNoteUpdateFlag() {
        return noteUpdateFlag;
    }


    /**
     * Sets the noteUpdateFlag value for this SiebelActivityNotes.
     * 
     * @param noteUpdateFlag
     */
    public void setNoteUpdateFlag(java.lang.String noteUpdateFlag) {
        this.noteUpdateFlag = noteUpdateFlag;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SiebelActivityNotes)) return false;
        SiebelActivityNotes other = (SiebelActivityNotes) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.noteId==null && other.getNoteId()==null) || 
             (this.noteId!=null &&
              this.noteId.equals(other.getNoteId()))) &&
            ((this.noteDate==null && other.getNoteDate()==null) || 
             (this.noteDate!=null &&
              this.noteDate.equals(other.getNoteDate()))) &&
            ((this.noteAuthor==null && other.getNoteAuthor()==null) || 
             (this.noteAuthor!=null &&
              this.noteAuthor.equals(other.getNoteAuthor()))) &&
            ((this.noteDetails==null && other.getNoteDetails()==null) || 
             (this.noteDetails!=null &&
              this.noteDetails.equals(other.getNoteDetails()))) &&
            ((this.noteUpdateFlag==null && other.getNoteUpdateFlag()==null) || 
             (this.noteUpdateFlag!=null &&
              this.noteUpdateFlag.equals(other.getNoteUpdateFlag())));
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
        if (getNoteId() != null) {
            _hashCode += getNoteId().hashCode();
        }
        if (getNoteDate() != null) {
            _hashCode += getNoteDate().hashCode();
        }
        if (getNoteAuthor() != null) {
            _hashCode += getNoteAuthor().hashCode();
        }
        if (getNoteDetails() != null) {
            _hashCode += getNoteDetails().hashCode();
        }
        if (getNoteUpdateFlag() != null) {
            _hashCode += getNoteUpdateFlag().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SiebelActivityNotes.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "SiebelActivityNotes"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("noteId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "NoteId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("noteDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "NoteDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("noteAuthor");
        elemField.setXmlName(new javax.xml.namespace.QName("", "NoteAuthor"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "NoteAuthor"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("noteDetails");
        elemField.setXmlName(new javax.xml.namespace.QName("", "NoteDetails"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("noteUpdateFlag");
        elemField.setXmlName(new javax.xml.namespace.QName("", "NoteUpdateFlag"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
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
