/**
 * TaxCalculateWS_PortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.taxCalc;

public interface TaxCalculateWS_PortType extends java.rmi.Remote {
    public void calculateTax(java.lang.String debug, com.lexmark.taxCalc.TaxCalculationWSInput taxCalculationWSInput, com.lexmark.taxCalc.holders.TaxCalculationWSOutputHolder taxCalculationWSOutput, javax.xml.rpc.holders.StringHolder integrationResponse) throws java.rmi.RemoteException;
}
