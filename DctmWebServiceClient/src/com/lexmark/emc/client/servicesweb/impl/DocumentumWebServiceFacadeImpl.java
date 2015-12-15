package com.lexmark.emc.client.servicesweb.impl;

import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.soap.SOAPBinding;

//import org.apache.log4j.Logger;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lexmark.emc.client.servicesweb.DocumentumWebServiceFacade;
import com.lexmark.emc.client.servicesweb.util.DocumentumServiceUtil;
import com.lexmark.properties.schema.sw.document.DocumentResponse;
import com.lexmark.properties.schema.sw.document.DocumentProperties.DocumentInfo;
import com.lexmark.schemas.ecm.dctmwebservice.ecmschema._1.DctmWebService;
import com.lexmark.schemas.ecm.dctmwebservice.ecmschema._1.DctmWebService_Service;
import com.sun.xml.ws.api.message.Header;
import com.sun.xml.ws.api.message.Headers;
import com.sun.xml.ws.developer.WSBindingProvider;

public class DocumentumWebServiceFacadeImpl implements DocumentumWebServiceFacade {
	//private static Logger logger = Logger.getLogger(DocumentumWebServiceFacadeImpl.class);
	private static Logger logger = LogManager.getLogger(DocumentumWebServiceFacadeImpl.class);

	private String token;
	private DctmWebService dctmWebService;
	private static final Integer WEB_SERVICE_TIME_OUT = 1000 * 60 * 60 * 8;
	private boolean isAuthencateSuccess = false;
	private String endPoint;

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}

	private String repositoryName;
	private String userId;
	private String password;
	private String applicationName;
	public DocumentumWebServiceFacadeImpl() {
	}

	public DocumentumWebServiceFacadeImpl(String endPoint, String repositoryName, String userId, String password, String applicationName) {
		this.endPoint = endPoint;
		this.repositoryName = repositoryName;
		this.userId = userId;
		this.password = password;
		this.applicationName = applicationName;
	}
	
	public Boolean deleteDocument(String objectId) {
		if(!isAuthencateSuccess) {
			authenticate();
		}
		try {
			DocumentInfo oInfo = new DocumentInfo();
			oInfo.setRObjectId(objectId);
			String ret = dctmWebService.deleteDocument(DocumentumServiceUtil.getDocumentPropertiesString(oInfo));
			
			DocumentResponse response = DocumentumServiceUtil.parseResponse(ret);
			if(ret != null) {
				if(response.getStatusCode() == 1) {
					logger.debug("documentum " + objectId + " is succussfully deleted");
					return Boolean.TRUE;
				} else if(response.getStatusCode() == -1) {
					authenticate();
					logger.debug("documentum re-authenticate ");
					return deleteDocument(objectId);
				} 
			}
			throw new RuntimeException("some error happen");
			
		}catch(Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	private void initializeDctmWebService() {
		QName serviceName = new QName(
				"http://schemas.lexmark.com/ecm/dctmwebservice/ecmschema/1.0",
				"DctmWebService");

		URL baseUrl = DctmWebService_Service.class.getResource(".");
		try {
		URL url = new URL(baseUrl, endPoint);

		DctmWebService_Service service = new DctmWebService_Service(url,
				serviceName);
		
		dctmWebService = service.getDctmWebServicePort();
		} catch(Exception ex) {
			throw new RuntimeException(ex);
		}
	}
	
	public String authenticate() {
		
		initializeDctmWebService();

		((BindingProvider) dctmWebService).getRequestContext().put(
				"com.sun.xml.ws.request.timeout", WEB_SERVICE_TIME_OUT);
		try {
		  token = dctmWebService.authenticate(
				repositoryName, 
				userId,  
				userId,
				password, 
				applicationName);
			isAuthencateSuccess = true;
			logger.debug("documentum service authenticate token" + token);
			
			WSBindingProvider bp = ((WSBindingProvider) dctmWebService);
			SOAPBinding binding = (SOAPBinding) ((BindingProvider) dctmWebService)
					.getBinding();
			binding.setMTOMEnabled(true);

			Document document = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder().newDocument();

			Element documentumSecurityToken = document
					.createElement("DocumentumSecurityToken");

			Element eToken = document.createElement("token");
			eToken.setTextContent(token);

			documentumSecurityToken.appendChild(eToken);

			Header h = Headers.create(documentumSecurityToken);
			bp.setOutboundHeaders(h);

		}catch(Exception ex) {
			throw new RuntimeException(ex);
		}
		return token;
	}

	public String createDocument(DocumentInfo oInfo,  byte[] content){
		if(!isAuthencateSuccess) {
			authenticate();
		}
		
		if(oInfo.getMdmId()!=null) {
			oInfo.setRFolderPath(oInfo.getRFolderPath() + "/" + oInfo.getMdmId());
		}
		
		try {
			String ret = dctmWebService.createDocument(DocumentumServiceUtil.getDocumentPropertiesString(oInfo),
					content);
			logger.debug("create document ret: " + ret);
			DocumentResponse response = DocumentumServiceUtil.parseResponse(ret);
			if(ret != null) {
				if(response.getStatusCode() == 1) {
					logger.debug("documentum succussfully to load report");
					return response.getRObjectId();
				} else if(response.getStatusCode() == -1) {
					authenticate();
					logger.debug("documentum re-authenticate ");
					return createDocument(oInfo, content);
				} 
			}
			throw new RuntimeException("some error happen");
			
		}catch(Exception ex) {
			throw new RuntimeException(ex);
		}
	}
	
	/*  Setter Getter for properties  */
	
	public String getEndPoint() {
		return endPoint;
	}

	public void setEndPoint(String endPoint) {
		this.endPoint = endPoint;
	}

	public String getRepositoryName() {
		return repositoryName;
	}

	public void setRepositoryName(String repositoryName) {
		this.repositoryName = repositoryName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getApplicationName() {
		return applicationName;
	}


}
