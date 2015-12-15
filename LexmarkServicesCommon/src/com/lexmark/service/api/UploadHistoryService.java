package com.lexmark.service.api;


import com.lexmark.contract.uploadHistory.UploadHistoryListContract;
import com.lexmark.result.uploadHistory.UploadHistoryResult;

public interface UploadHistoryService {
	 public UploadHistoryResult retrieveUploadHistoryList(UploadHistoryListContract contract) throws Exception;
}
