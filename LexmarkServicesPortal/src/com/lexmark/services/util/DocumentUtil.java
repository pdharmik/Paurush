package com.lexmark.services.util;

import com.lexmark.contract.DocumentUploadContract;

public class DocumentUtil {
	/**
	 * @param fileName 
	 * @param contract 
	 */
	public static void fillUploadContract(String fileName, DocumentUploadContract contract){
		String contentType = DocumentumWebServiceUtil.getDocumentTypeFromFilePath(fileName);
		contract.setaContentType(contentType);
		contract.setFileName(fileName);
	};
}
