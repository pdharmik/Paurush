package com.lexmark.emc.client.servicesweb;

import com.lexmark.properties.schema.sw.document.DocumentProperties.DocumentInfo;

public interface DocumentumWebServiceFacade {
	public String authenticate(); 
	
	/**
	 * 
	 * @param documentInfo
	 * @param content
	 * @return  the rObjectId
	 */
	public String  createDocument(DocumentInfo documentInfo,  byte[] content);
	
	public Boolean deleteDocument(String rObjectId);
}
