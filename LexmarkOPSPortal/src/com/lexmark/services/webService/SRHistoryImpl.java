package com.lexmark.services.webService;


import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import com.lexmark.contract.RequestListContract;
import com.lexmark.domain.ServiceRequest;
import com.lexmark.srHistory.AssetServiceRequestHistory;
import com.lexmark.srHistory.LXKServiceRequestWebservicesServiceRequestWSLocator;

import com.lexmark.srHistory.ServiceRequest2;
import com.lexmark.srHistory.ServiceRequestHistoryData;
import com.lexmark.srHistory.ServiceRequestHistoryWSInput;
import com.lexmark.srHistory.ServiceRequestHistoryWSInput2;
import com.lexmark.srHistory.ServiceRequestWS_PortType;

public class SRHistoryImpl {
private static Logger logger = LogManager.getLogger(SRHistoryImpl.class);
	
	

	private String endpointDevice="http://54.173.12.62:8000/ws/LXKServiceRequest.webservices.serviceRequestWS/LXKServiceRequest_webservices_serviceRequestWS_Port";
	private String userName="serviceswebportal"; 
	private String password="serviceswebportal@123";
	
	public static DateFormat DATE_FORMAT_SAP = new SimpleDateFormat("yyyy-MM-dd");
	public static DateFormat INVOICE_DATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy");
	
	
	public String getEndpointDevice() {
		return endpointDevice;
	}

	public void setEndpointDevice(String endpointDevice) {
		this.endpointDevice = endpointDevice;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	

	/* (non-Javadoc)
	 * @see com.lexmark.services.api.CustomerInvoiceService#retrieveInvoiceList(com.lexmark.contract.InvoiceListContract, java.lang.String[])
	 */
	public List<ServiceRequest2> retrieveServiceRequestList(RequestListContract contract)
			throws Exception {
		
		LXKServiceRequestWebservicesServiceRequestWSLocator locator =new LXKServiceRequestWebservicesServiceRequestWSLocator();
		logger.debug("end point is "+getEndpointDevice());
		ServiceRequestWS_PortType  port=locator.getLXKServiceRequest_webservices_serviceRequestWS_Port(new URL(getEndpointDevice()));
		
		int timeout=10800000;
		org.apache.axis.client.Stub stub = ((org.apache.axis.client.Stub) port);
		stub.setUsername(getUserName());
		stub.setPassword(getPassword());
		stub.setTimeout(timeout);
			
		logger.debug("before port call");
		
		
		ServiceRequestHistoryWSInput2 serviceRequestHistoryWSInput2=new ServiceRequestHistoryWSInput2() ;
		ServiceRequestHistoryData[]  serviceRequestHistoryData=new ServiceRequestHistoryData[1];
	    logger.debug("ACCount ID-->>"+contract.getMdmId());
	    logger.debug("Asset ID-->>"+contract.getAssetId());
			//serviceRequestHistoryData[0]=new ServiceRequestHistoryData(contract.getMdmId(),contract.getAssetId(),"");
	    /*Hardcoded for testing*/
			serviceRequestHistoryData[0]=new ServiceRequestHistoryData("1-H0M-5102","1-MN9-1807","Diagnosis");
	
		
		serviceRequestHistoryWSInput2.setServiceRequestHistoryData(serviceRequestHistoryData);
		ServiceRequestHistoryWSInput serv=new ServiceRequestHistoryWSInput();
		serv.setServiceRequestHistoryWSInput(serviceRequestHistoryWSInput2);
		AssetServiceRequestHistory[] serviceRequestArr = port.getServiceRequestHistory(null, serv);
		ServiceRequest2[] resultList = serviceRequestArr[0].getAssetServiceRequestHistory().getServiceRequest();
		
		//logger.debug(serviceRequestArr.length);
		//logger.debug("** MPS PERFORMANCE TESTING SERVICE CALL ==>: " + (System.currentTimeMillis() - timeBeforeCall) / 1000.0);
		
		
		return Arrays.asList(resultList);
	}
	
	
	
	
//	public static void main(String [] args)throws Exception{
//		SRHistoryImpl service=new SRHistoryImpl();
//		
//		service.retrieveServiceRequestList();
//	}
}
