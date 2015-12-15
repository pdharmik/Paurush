package com.lexmark.webservice.impl.mock;

import java.util.ArrayList;
import java.util.List;

import com.lexmark.contract.SRListContract;
import com.lexmark.domain.ServiceRequestAP;
import com.lexmark.framework.logging.LEXLogger;
import com.lexmark.result.SRListResult;

public class ARSRServiceMockImpl {
	private static LEXLogger LOGGER = LEXLogger.getLEXLogger(ARSRServiceMockImpl.class);
	
	
	
	public SRListResult retrieveAPSRList(SRListContract _contract) throws Exception {
		LOGGER.debug("Entering Invoice List IMPL");
		//ZG_AR_TRANSFER_TO_SIEBELProxy proxy = ProxyManager.getARSRListProxy(getEndpointSR(),getUserName(),getPassword());
		
		/*TableOfBapiret2Holder BAPIHolder = new TableOfBapiret2Holder();
		BAPIHolder.value = new Bapiret2[0];
		
		TableOfZgsiebelApDataHolder dataHolder = new TableOfZgsiebelApDataHolder();
		dataHolder.value = new ZgsiebelApData[0];*/
		
		/*LOGGER.debug("Before Service Call");
		setDataIntoHolder(dataHolder);*/
		
		//proxy.zgArDataTransferToSiebel(_contract.getCompanyCode(), _contract.getVendorID(), _contract.getInvoiceNumber(), BAPIHolder, dataHolder);
		
		/*LOGGER.debug("After Service Call");*/
		
		SRListResult srListResult = mapWSResult();
		return srListResult;
	}

	

	private SRListResult mapWSResult() {
		SRListResult srListResult = new SRListResult();
		
		List<ServiceRequestAP> srList = new ArrayList<ServiceRequestAP>(40);
		
		for (int i = 0; i < 1000; i++) {
//			ZgsiebelApData siebelAPData = dataHolder.value[i];
//			ZgsiebelApData siebelAPData = new ZgsiebelApData();
			ServiceRequestAP srData = ws2Sr(i);
			srList.add(srData);
		}		
		srListResult.setServiceRequest(srList);
		return srListResult;
	}

	private ServiceRequestAP ws2Sr(int i) {
		ServiceRequestAP servReqData = new ServiceRequestAP();		
		servReqData.setAmount(225.0+Math.round(Math.random())+i);
		servReqData.setCurrencyType("USD");
		servReqData.setRequestNumber("1-TWS098156"+String.valueOf(i));	
		return servReqData;		
	}
}
