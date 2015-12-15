package com.lexmark.webservice.api;

import com.lexmark.contract.HardwareDebriefContract;
import com.lexmark.result.HardwareDebriefResult;

/**
 * @author wipro
 * @version 2.1
 *
 */

public interface HardwareDebriefService {
	
	HardwareDebriefResult saveDebriefRquest(HardwareDebriefContract contract) throws Exception;
}
