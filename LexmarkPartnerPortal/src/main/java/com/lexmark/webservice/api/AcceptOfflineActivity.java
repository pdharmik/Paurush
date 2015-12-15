package com.lexmark.webservice.api;

import java.net.MalformedURLException;
import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import com.lexmark.exceptionimpl.checked.business.LGSBusinessException;
import com.lexmark.exceptionimpl.runtime.LGSRuntimeException;
import com.lexmark.result.ActivityDebriefSubmitResult;

public interface AcceptOfflineActivity {
	
	public String createofflineActivity(String SrNumber)throws LGSRuntimeException,
	LGSBusinessException,ServiceException,RemoteException,MalformedURLException;

}
