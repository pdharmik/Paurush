package com.lexmark.webservice.api;

import com.lexmark.contract.ClaimDebriefSubmitContract;
import com.lexmark.contract.ClaimUpdateContract;
import com.lexmark.contract.WarrantyClaimCreateContract;
import com.lexmark.result.ClaimDebriefSubmitResult;
import com.lexmark.result.ClaimUpdateResult;
import com.lexmark.result.WarrantyClaimCreateResult;

public interface ClaimService {
	public WarrantyClaimCreateResult createWarrantyClaim(WarrantyClaimCreateContract contract) throws Exception;
	public ClaimUpdateResult updateWarrantyClaim(ClaimUpdateContract contract) throws Exception;
	public ClaimDebriefSubmitResult submitClaimDebrief(ClaimDebriefSubmitContract contract) throws Exception;

}
