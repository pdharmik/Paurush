package com.lexmark.service.impl.mock;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.lexmark.contract.uploadHistory.UploadHistoryListContract;
import com.lexmark.result.uploadHistory.UploadHistoryResult;
import com.lexmark.service.api.UploadHistoryService;
import com.lexmark.domain.UploadDetail;

public class UploadHistoryServiceImpl implements UploadHistoryService{

	@Override
	public UploadHistoryResult retrieveUploadHistoryList(
			UploadHistoryListContract contract) throws Exception {
		List<UploadDetail> uploadDetailsList=mockDataGenerator(contract);
		UploadHistoryResult uploadHistoryResult=new UploadHistoryResult();
		uploadHistoryResult.setUploadOrderList(uploadDetailsList);
		uploadHistoryResult.setTotalCount(uploadDetailsList.size());
		return uploadHistoryResult;
	}
	
	private List<UploadDetail> mockDataGenerator(UploadHistoryListContract contract){
		
		final String size=String.valueOf(Math.random()%1024);
		final String type[]={"Service Order Req","Other Req"};
		final String status[]={"Error","Completed","In Progress"};
		final Date submittedOn=new Date();
		final String fileName[]={"filename_hardwareReq.csv","file_consumable.csv"};
		List<UploadDetail> uploadDetailList=new ArrayList<UploadDetail>();
		for(int i=0;i<contract.getIncrement();i++){
			UploadDetail up=new UploadDetail();
			up.setRequestNumber(String.valueOf(i));
			up.setSize(size);
			if(i%2==0){
				up.setType(type[0]);
				up.setStatus(status[0]);
			}else{
				up.setType(type[1]);
				up.setStatus(status[1]);
			}
			if(i%5==0)
				up.setStatus(status[2]);
			up.setSubmittedOn(submittedOn);
			up.setCompletedOn(submittedOn);
			up.setFileName(fileName[(int)Math.random()%fileName.length]);
			uploadDetailList.add(up);
		}
		return uploadDetailList;
	}
	
	

}
