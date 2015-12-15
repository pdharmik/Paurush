package com.lexmark.service.api;

import com.lexmark.contract.LBSCHLContract;
import com.lexmark.result.LBSCHLListResult;


public interface LBSCHLMappingService {

	public LBSCHLListResult retrieveCHLtList(LBSCHLContract contract) throws Exception;
}
