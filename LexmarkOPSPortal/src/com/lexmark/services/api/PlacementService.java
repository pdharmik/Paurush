package com.lexmark.services.api;

import com.lexmark.contract.PlacementContract;

public interface PlacementService {
	
	/**
	 * @param contract
	 * @return
	 * @throws Exception
	 */
	public String addPlacement(PlacementContract contract)throws Exception;
	/**
	 * @param contract
	 * @return
	 * @throws Exception
	 */
	public String changePlacement(PlacementContract contract)throws Exception;
	/**
	 * @param contract
	 * @return
	 * @throws Exception
	 */
	public String removePlacement(PlacementContract contract)throws Exception;
	
}
