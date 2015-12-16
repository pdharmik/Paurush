package com.lexmark.services.webService;

import java.net.URLEncoder;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.lexmark.services.api.DeviceStatusService;
import com.lexmark.services.portlet.fleetmanagement.DeviceStatusController;

public class DeviceStatusServiceImpl implements DeviceStatusService {

	private String username;
	private String password;
	private String endPoint;
	

	private static final String format="{\"getDeviceStatusInput\":{\"QueryId\":\"%s\",\"AssetId\":\"%s\"}}";
	
	private static Logger LOGGER = LogManager.getLogger(DeviceStatusServiceImpl.class); 
	@Override
	public String retrieveDeviceStatusInformation(String id) throws Exception {
		LOGGER.debug("[in retrieveDeviceStatusInformation]");
		
		StringBuffer sb=new StringBuffer(getEndPoint());
		sb.append("?getDeviceStatusInput=")
		.append(URLEncoder.encode(String.format(format,System.currentTimeMillis(),id), "UTF-8"));
		
			HttpClient client = HttpClientBuilder.create().build();
			HttpGet getRequest = new HttpGet(sb.toString());
			getRequest.setHeader("Authorization", "Basic "+getAuth());
			HttpResponse response = null;
			response = client.execute(getRequest);
			LOGGER.debug("[after get request]");
			StringBuffer result = new StringBuffer();
	        result.append(EntityUtils.toString(response.getEntity()));
	        LOGGER.debug("[result ]"+result);
	        LOGGER.debug("[out retrieveDeviceStatusInformation]");
	        return result.toString();
		
		
	}
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the endPoint
	 */
	public String getEndPoint() {
		return endPoint;
	}

	/**
	 * @param endPoint the endPoint to set
	 */
	public void setEndPoint(String endPoint) {
		this.endPoint = endPoint;
	}

	/**
	 * @return the format
	 */
	public static String getFormat() {
		return format;
	}
	
	public String getAuth(){
		String auth=getUsername()+":"+getPassword();
		return new String(Base64.encodeBase64(auth.getBytes()));
	}
}
