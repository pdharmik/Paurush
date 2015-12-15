package com.lexmark.webservice.api;




import com.lexmark.contract.MassUploadCreateRequestContract;
import com.lexmark.exceptionimpl.checked.business.LGSBusinessException;
import com.lexmark.exceptionimpl.runtime.LGSRuntimeException;
import com.lexmark.result.CreateServiceRequestResult;
/**
 * @author Wipro 
 * @version 1.0 
 * */
public interface MassUploadService {

	/**
	 * @param contract 
	 * @return CreateServiceRequestResult 
	 * @throws LGSRuntimeException 
	 * @throws LGSBusinessException 
	 *  
	 * */	
	CreateServiceRequestResult createMassUploadRequest(MassUploadCreateRequestContract contract) throws LGSRuntimeException,
	LGSBusinessException;
}
