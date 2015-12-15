package com.lexmark.webservice.impl.real;

import java.util.LinkedList;

import com.lexmark.SAPWebServices.AR.serviceRequests.client.BAPIRET2;
import com.lexmark.SAPWebServices.AR.serviceRequests.client.ZGGET_AP_DATA;
import com.lexmark.SAPWebServices.AR.serviceRequests.client.ZG_GET_AR_DETAILSProxy;
import com.lexmark.SAPWebServices.AR.serviceRequests.client.holders.TABLE_OF_BAPIRET2Holder;
import com.lexmark.SAPWebServices.AR.serviceRequests.client.holders.TABLE_OF_ZGGET_AP_DATAHolder;
import com.lexmark.contract.SRListContract;
import com.lexmark.domain.ServiceRequestAP;
import com.lexmark.framework.logging.LEXLogger;
import com.lexmark.result.SRListResult;
import com.lexmark.util.ProxyManager;
import com.lexmark.webservice.api.SRService;

public class ARSRServiceImpl implements SRService{
	
	private static LEXLogger LOGGER = LEXLogger.getLEXLogger(ARSRServiceImpl.class);
	private String userName; 
	private String password;
	private String endpointSR;
	
	public String getEndpointSR() {
		return endpointSR;
	}

	public void setEndpointSR(String endpointSR) {
		this.endpointSR = endpointSR;
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
	
	@Override
	public SRListResult retrieveAPSRList(SRListContract _contract) throws Exception {
		LOGGER.debug("Entering Invoice List IMPL");
		int pageNumber;
		int posStart = _contract.getPosStart();
		if(posStart == 0){
			pageNumber = 1;
		}else{
			pageNumber = (posStart/20)+1;
		}
		ZG_GET_AR_DETAILSProxy proxy = ProxyManager.getARSRListProxy(getEndpointSR(),getUserName(),getPassword());
		
		TABLE_OF_BAPIRET2Holder BAPIHolder = new TABLE_OF_BAPIRET2Holder();
		BAPIHolder.value = new BAPIRET2[0];
		
		TABLE_OF_ZGGET_AP_DATAHolder dataHolder = new TABLE_OF_ZGGET_AP_DATAHolder();
		dataHolder.value = new ZGGET_AP_DATA[0];
		
		LOGGER.debug("Before Service Call");
		
		
		proxy.ZG_AR_GET_DETAILS(_contract.getCompanyCode(), _contract.getVendorID(), _contract.getInvoiceNumber(), BAPIHolder, dataHolder);
		
		LOGGER.debug("After Service Call");
		
		SRListResult srListResult = mapWSResult(dataHolder,pageNumber);
		return srListResult;
	}
	
	// add
	public SRListResult downloadExcelAPSRList (SRListContract _contract) throws Exception {
		LOGGER.debug("Entering Invoice List IMPL");
		int pageNumber;
		
		ZG_GET_AR_DETAILSProxy proxy = ProxyManager.getARSRListProxy(getEndpointSR(),getUserName(),getPassword());
		
		TABLE_OF_BAPIRET2Holder BAPIHolder = new TABLE_OF_BAPIRET2Holder();
		BAPIHolder.value = new BAPIRET2[0];
		
		TABLE_OF_ZGGET_AP_DATAHolder dataHolder = new TABLE_OF_ZGGET_AP_DATAHolder();
		dataHolder.value = new ZGGET_AP_DATA[0];
		
		LOGGER.debug("Before Service Call");
		
		
		proxy.ZG_AR_GET_DETAILS(_contract.getCompanyCode(), _contract.getVendorID(), _contract.getInvoiceNumber(), BAPIHolder, dataHolder);
		
		LOGGER.debug("After Service Call");
		
		SRListResult srListResult = mapWSResult(dataHolder);
		return srListResult;
	}
	
	private SRListResult mapWSResult(TABLE_OF_ZGGET_AP_DATAHolder dataHolder) {
		SRListResult srListResult = new SRListResult();
		
		int totalCount = dataHolder.value.length;
		LinkedList<ServiceRequestAP> srList = new LinkedList<ServiceRequestAP>();
		if(dataHolder!=null && totalCount >0)
		{
			for(int i=0;i<totalCount;i++)
			{						
					ZGGET_AP_DATA siebelAPData = dataHolder.value[i];				
					ServiceRequestAP srData = ws2Sr(siebelAPData);
					srList.add(srData);
				}
			}
		else{
			LOGGER.debug("No data Returned");
		}
		
		//srListResult.setTotalCount(totalCount);
		srListResult.setServiceRequest(srList);
		return srListResult;
	}
	// end 

	private SRListResult mapWSResult(TABLE_OF_ZGGET_AP_DATAHolder dataHolder,int pageNumber) {
		SRListResult srListResult = new SRListResult();
		LOGGER.debug("value.length "+dataHolder.value.length);
		int indexfrom = (pageNumber - 1)*20;
		int indexTo = (pageNumber*20) - 1;
		int totalCount = 0;
		LinkedList<ServiceRequestAP> srList = new LinkedList<ServiceRequestAP>();
		if(dataHolder!=null && dataHolder.value.length >0)
		{
			totalCount = dataHolder.value.length;
			if(totalCount <= indexTo)
			{
				for (int i = indexfrom; i < totalCount; i++) {		
					ZGGET_AP_DATA siebelAPData = dataHolder.value[i];				
					ServiceRequestAP srData = ws2Sr(siebelAPData);
					srList.add(srData);
				}
			}else{
				for (int i = indexfrom; i <= indexTo; i++) {				
					ZGGET_AP_DATA siebelAPData = dataHolder.value[i];				
					ServiceRequestAP srData = ws2Sr(siebelAPData);
					srList.add(srData);
				}
			}
		}
		else{
			LOGGER.debug("No data Returned");
		}
		
		srListResult.setTotalCount(totalCount);
		srListResult.setServiceRequest(srList);
		return srListResult;
	}

	private ServiceRequestAP ws2Sr(ZGGET_AP_DATA siebelAPData) {
		ServiceRequestAP servReqData = new ServiceRequestAP();		
		servReqData.setAmount(Math.abs(siebelAPData.getRWBTR().doubleValue()));
		servReqData.setCurrencyType(siebelAPData.getWAERS());
		String serviceReqNum=siebelAPData.getSRCDOCNO();
		serviceReqNum=serviceReqNum!=null?serviceReqNum.replaceAll("^0*", ""):"";
		servReqData.setRequestNumber(serviceReqNum);	
		return servReqData;
		
	}



}
