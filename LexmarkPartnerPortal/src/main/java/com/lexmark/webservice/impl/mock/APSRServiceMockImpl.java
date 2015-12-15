package com.lexmark.webservice.impl.mock;

import java.util.LinkedList;

import com.lexmark.contract.SRListContract;
import com.lexmark.domain.ServiceRequestAP;
import com.lexmark.framework.logging.LEXLogger;
import com.lexmark.result.SRListResult;
import com.lexmark.webservice.api.SRService;
import com.lexmark.webservice.impl.real.ARSRServiceImpl;

public class APSRServiceMockImpl implements SRService{
	private static LEXLogger LOGGER = LEXLogger.getLEXLogger(ARSRServiceImpl.class);
	@Override
	public SRListResult retrieveAPSRList(SRListContract contract)
			throws Exception {
		int pageNumber;
		int posStart = contract.getPosStart();
		if(posStart == 0){
			pageNumber = 1;
		}else{
			pageNumber = (posStart/20)+1;
		}
		
		SRListResult srListResult = mapSRResult(pageNumber);
		return srListResult;
	}

	private SRListResult mapSRResult(int pageNumber) {
		SRListResult srListResult = new SRListResult();
		LinkedList<ServiceRequestAP> srList = null;		
		
		LOGGER.debug("Doing pagination start");
		int indexfrom = (pageNumber - 1)*20;
		int indexTo = (pageNumber*20) - 1;
		
		PaginationMock[] pagingObj = PaginationMock.getPagingObj();
		int totalCount = pagingObj.length;
		LOGGER.debug("total count in Mock class, after assignment "+totalCount);
		srList = new LinkedList<ServiceRequestAP>();
		LOGGER.debug("Doing it");
		if(totalCount <= indexTo)
		{
			for (int i = indexfrom; i <= totalCount; i++) {		
				PaginationMock siebelAPData = pagingObj[i];				
				ServiceRequestAP srData = ws2Sr(siebelAPData);
				srList.add(srData);
			}
		}else{
			for (int i = indexfrom; i <= indexTo; i++) {				
				PaginationMock siebelAPData = pagingObj[i];				
				ServiceRequestAP srData = ws2Sr(siebelAPData);
				srList.add(srData);
			}
		}
		
		LOGGER.debug("Doing pagination end");
		srListResult.setServiceRequest(srList);
		LOGGER.debug("total count in Mock class, before setting "+totalCount);
		srListResult.setTotalCount(totalCount);
		LOGGER.debug("Built list");
		return srListResult;
	}

	private ServiceRequestAP ws2Sr(PaginationMock siebelAPData) {
		ServiceRequestAP servReqData = new ServiceRequestAP();		
		servReqData.setAmount(siebelAPData.getAmount());
		servReqData.setCurrencyType(siebelAPData.getCurrencyType());
		servReqData.setRequestNumber(siebelAPData.getRequestNumber());	
		return servReqData;
		
	}

	@Override
	public SRListResult downloadExcelAPSRList(SRListContract contract)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
