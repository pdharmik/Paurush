package com.lexmark.service.api;

import com.lexmark.contract.BulkUploadContract;
import com.lexmark.contract.BulkUploadStatusContract;
import com.lexmark.result.BulkUploadFileResult;
import com.lexmark.result.BulkUploadStatusFileResult;
import com.lexmark.result.BulkUploadStatusListResult;

public interface BulkUploadService {
	/**
	 * Interface to send and receive the file as attachment and show the history data
	 * @author mseal
	 */
	public BulkUploadFileResult bulkUploadFile(BulkUploadContract contract)throws Exception;

	public BulkUploadStatusListResult retrieveBulkUploadStatusList(BulkUploadStatusContract contract)throws Exception;

	public BulkUploadStatusFileResult retrieveBulkUploadStatusFile(BulkUploadStatusContract contract)throws Exception;
}
