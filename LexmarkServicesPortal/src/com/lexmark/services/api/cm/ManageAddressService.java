package com.lexmark.services.api.cm;

import java.util.Map;

import com.lexmark.contract.CreateServiceRequestContract;
import com.lexmark.exceptionimpl.checked.business.LGSBusinessException;
import com.lexmark.exceptionimpl.runtime.LGSRuntimeException;
import com.lexmark.result.CreateServiceRequestResult;

/**
 * @author Wipro 
 * @version 2.1 
 *
 */

public interface ManageAddressService {

	/*public String createAddAssetRequest(ServiceRequestDTO assetFormDTO) throws LGSRuntimeException,
			LGSCheckedException;

	public String createChangeAssetRequest(ServiceRequestDTO assetFormDTO) throws LGSRuntimeException,
			LGSCheckedException;
	
	public String createDecommissionAssetRequest(ServiceRequestDTO assetFormDTO) throws LGSRuntimeException,
			LGSCheckedException;*/
	
	/*public String createSwapAssetRequest() throws LGSRuntimeException,
			LGSCheckedException;

	public String createOthersAssetRequest() throws LGSRuntimeException,
			LGSCheckedException;

	public String createMoveAssetRequest() throws LGSRuntimeException,
	LGSCheckedException;*/
//	public CreateServiceRequestResult createManageAddressRequest(CreateServiceRequestContract serviceReqContract) throws LGSRuntimeException,
//	LGSCheckedException, Exception;
	
    CreateServiceRequestResult createCMRequestService(CreateServiceRequestContract serviceReqContract, Map<String,String> accountDetails) throws LGSRuntimeException,
	LGSBusinessException;
}
