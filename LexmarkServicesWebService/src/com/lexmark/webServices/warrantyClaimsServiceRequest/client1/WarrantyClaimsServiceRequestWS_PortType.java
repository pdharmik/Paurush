/**
 * WarrantyClaimsServiceRequestWS_PortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.webServices.warrantyClaimsServiceRequest.client1;

public interface WarrantyClaimsServiceRequestWS_PortType extends java.rmi.Remote {
    public java.lang.String debriefWarrantyClaim(java.lang.String debug, java.lang.String synchOrAsynch, com.lexmark.webServices.warrantyClaimsServiceRequest.client1.DebriefClaimsServiceRequestInput debriefClaimsServiceRequestInput) throws java.rmi.RemoteException;
    public void updateWarrantyClaim(java.lang.String debug, java.lang.String synchOrAsynch, com.lexmark.webServices.warrantyClaimsServiceRequest.client1.UpdateClaimsServiceRequestInput updateClaimsServiceRequestInput, javax.xml.rpc.holders.StringHolder status, javax.xml.rpc.holders.StringHolder claimNumber, javax.xml.rpc.holders.StringHolder claimId) throws java.rmi.RemoteException;
    public void createWarrantyClaim(java.lang.String debug, java.lang.String synchOrAsynch, com.lexmark.webServices.warrantyClaimsServiceRequest.client1.CreateClaimsServiceRequestInput createClaimsServiceRequestInput, javax.xml.rpc.holders.StringHolder claimNumber, javax.xml.rpc.holders.StringHolder claimId) throws java.rmi.RemoteException;
}
