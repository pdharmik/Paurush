package com.lexmark.webservice.api;

import com.lexmark.contract.SRListContract;
import com.lexmark.result.SRListResult;


public interface SRService {
		public SRListResult retrieveAPSRList(SRListContract contract) throws Exception;
		public SRListResult downloadExcelAPSRList(SRListContract contract) throws Exception;
}