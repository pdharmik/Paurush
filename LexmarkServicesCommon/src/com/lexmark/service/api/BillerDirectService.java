package com.lexmark.service.api;

import com.lexmark.contract.BillerDirectContract;
import com.lexmark.result.BillerDirectResult;

public interface BillerDirectService {
	public BillerDirectResult retrieveBillerDirectURL () throws Exception;
	public BillerDirectResult retrieveBillerDirectURLDisplay () throws Exception;
	public boolean saveBillerDirectUrl(BillerDirectContract contract) throws Exception; 
	public boolean deleteBillerDirectUrl(BillerDirectContract contract) throws Exception;
	public boolean deleteBillerDirectSubRowUrl(BillerDirectContract contract) throws Exception;
	public BillerDirectResult retrieveSupportedLocaleList() throws Exception;
}
