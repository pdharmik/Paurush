/**
 * 
 */
package com.lexmark.services.api;

import java.util.Map;

import com.lexmark.contract.CreateServiceRequestContract;
import com.lexmark.result.CreateServiceRequestResult;

/**.
 * This interface is used to create all kind of requests through webmethod.
 * @author gsarkar
 *
 */
public interface CreateLgsRequest {
	
	/**.
	 * This service method is used to create template based MADC requests. 
	 * @param contract CreateServiceRequestContract object
	 * @return CreateServiceRequestResult object
	 * @throws Exception
	 */
	public CreateServiceRequestResult createChangeManagementServiceRequest(CreateServiceRequestContract contract, Map<String,String> accountDetails) throws Exception;
	//public void createChangeManagementServiceRequest(java.lang.String debug, com.lexmark.lex.dlxkswmis1.ServiceRequest.serviceRequestWS.ServiceRequestInput serviceRequest, java.lang.String synchOrAsynch, java.lang.String returnDetail_x003F_, javax.xml.rpc.holders.StringHolder srNumber, javax.xml.rpc.holders.StringHolder activityId, javax.xml.rpc.holders.StringHolder SRRowId, javax.xml.rpc.holders.StringHolder SRNumHashValue, javax.xml.rpc.holders.StringHolder serviceRegionId, javax.xml.rpc.holders.StringHolder siebelStatusMessage, com.lexmark.lex.dlxkswmis1.ServiceRequest.serviceRequestWS.holders.ServiceRequestDetailsOutputHolder serviceRequestDetails) throws java.rmi.RemoteException;

}
