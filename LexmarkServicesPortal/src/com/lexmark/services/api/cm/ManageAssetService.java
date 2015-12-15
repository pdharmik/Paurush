package com.lexmark.services.api.cm;

import java.util.Map;

import com.lexmark.contract.CreateServiceRequestContract;
import com.lexmark.exceptionimpl.checked.business.LGSBusinessException;
import com.lexmark.exceptionimpl.runtime.LGSRuntimeException;
import com.lexmark.result.CreateServiceRequestResult;

/**
 * @author wipro
 * @version 2.1
 *
 */

public interface ManageAssetService {

	/*public String createAddAssetRequest(ServiceRequestDTO assetFormDTO) throws LGSRuntimeException,
			LGSCheckedException;

	public String createChangeAssetRequest(ServiceRequestDTO assetFormDTO) throws LGSRuntimeException,
			LGSCheckedException;
	
	public String createDecommissionAssetRequest(ServiceRequestDTO assetFormDTO) throws LGSRuntimeException,
			LGSCheckedException;*/
	
	/*public String createManageAssetRequest(ServiceRequestDTO assetFormDTO) throws LGSRuntimeException,
	LGSCheckedException;*/
	
	/*public String createSwapAssetRequest() throws LGSRuntimeException,
			LGSCheckedException;

	public String createOthersAssetRequest() throws LGSRuntimeException,
			LGSCheckedException;

	public String createMoveAssetRequest() throws LGSRuntimeException,
	LGSCheckedException;*/
	
//	public CreateServiceRequestResult createManageAssetRequest(CreateServiceRequestContract serviceReqContract) throws LGSRuntimeException,
//	LGSBusinessException;
	
    CreateServiceRequestResult createCMRequestService(CreateServiceRequestContract serviceReqContract, Map<String,String> accountDetails) throws LGSRuntimeException,
	LGSBusinessException;
}
