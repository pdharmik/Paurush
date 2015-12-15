package com.lexmark.webservice.impl.real;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import com.amind.common.util.StringUtils;
import com.lexmark.exceptionimpl.checked.business.LGSBusinessException;
import com.lexmark.exceptionimpl.runtime.LGSRuntimeException;
import com.lexmark.lbs.directMove.Update_Asset_Input;
import com.lexmark.result.ActivityDebriefSubmitResult;
import com.lexmark.webServices.acceptActivityWS.LXK_spcMPS_spcAuto_spcAccept_spcActivities_spcPartner_spcPortal_BindingStub;
import com.lexmark.webServices.acceptActivityWS.LXK_spcMPS_spcAuto_spcAccept_spcActivities_spcPartner_spcPortal_PortType;
import com.lexmark.webServices.acceptActivityWS.LXK_spcMPS_spcAuto_spcAccept_spcActivities_spcPartner_spcPortal_ServiceLocator;
import com.lexmark.webServices.acceptActivityWS.Update_Activities_Input;
import com.lexmark.webServices.acceptActivityWS.Update_Activities_Output;
import com.lexmark.webservice.api.AcceptOfflineActivity;

public class AcceptOfflineActivityImpl implements AcceptOfflineActivity{

	private String userName;
	private String password;
	private String url;
	private String message;
	
	public String createofflineActivity(String SrNumber)
			throws LGSRuntimeException, LGSBusinessException, ServiceException, RemoteException, MalformedURLException {
		// TODO Auto-generated method stub
		
		String url=getUrl();
		LXK_spcMPS_spcAuto_spcAccept_spcActivities_spcPartner_spcPortal_ServiceLocator locator= new LXK_spcMPS_spcAuto_spcAccept_spcActivities_spcPartner_spcPortal_ServiceLocator();
		LXK_spcMPS_spcAuto_spcAccept_spcActivities_spcPartner_spcPortal_PortType port= locator.getLXK_spcMPS_spcAuto_spcAccept_spcActivities_spcPartner_spcPortal(new URL(url));
		LXK_spcMPS_spcAuto_spcAccept_spcActivities_spcPartner_spcPortal_BindingStub stub=((LXK_spcMPS_spcAuto_spcAccept_spcActivities_spcPartner_spcPortal_BindingStub) port);
		
		Update_Activities_Input  input= new Update_Activities_Input("","","","","","","","","");
		
		stub.setHeader("http://siebel.com/webservices","UsernameToken",getUserName());
		stub.setHeader("http://siebel.com/webservices","PasswordText", getPassword());
		stub.setHeader("http://siebel.com/webservices","SessionType", "None");
		input.setSR_spcNumber(SrNumber);
		port.update_Activities(input);
		Update_Activities_Output output = new Update_Activities_Output();		
		message=output.getError_spcMessage();
		if(StringUtils.isBlank(message)){
			
			message="success";
		}
		
		
		return message;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
