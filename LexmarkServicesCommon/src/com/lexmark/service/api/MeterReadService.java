package com.lexmark.service.api;

import com.lexmark.contract.AssetMeterReadContract;
import com.lexmark.contract.MeterReadAssetListContract;
import com.lexmark.contract.MeterReadStatusContract;
import com.lexmark.contract.UpdateAssetMeterReadContract;
import com.lexmark.result.AssetMeterReadResult;
import com.lexmark.result.MeterReadAssetListResult;
import com.lexmark.result.MeterReadStatusFileResult;
import com.lexmark.result.MeterReadStatusListResult;
import com.lexmark.result.UpdateAssetMeterReadResult;

/**
 * Interface to search asset, or retrieve asset.
 * @author Eagle.Kong
 *
 */
public interface MeterReadService {

	public MeterReadAssetListResult retrieveMeterReadAssetList(MeterReadAssetListContract contract)throws Exception;
	public UpdateAssetMeterReadResult updateAssetMeterRead(UpdateAssetMeterReadContract contract)throws Exception;
	public AssetMeterReadResult importAssetMeterRead(AssetMeterReadContract contract)throws Exception;
	public MeterReadStatusListResult retrieveMeterReadStatusList(MeterReadStatusContract contract) throws Exception;
	public MeterReadStatusFileResult retrieveMeterReadStatusFile(MeterReadStatusContract contract) throws Exception;
	
}
