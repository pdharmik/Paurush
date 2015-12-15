package com.lexmark.service.api;

import com.lexmark.contract.LBSAssetListContract;
import com.lexmark.result.LBSFloorPlanListResult;


public interface LBSFloorPlanService {

	public LBSFloorPlanListResult retrieveLBSFloorPlanAssetList(LBSAssetListContract contract) throws Exception;

}
