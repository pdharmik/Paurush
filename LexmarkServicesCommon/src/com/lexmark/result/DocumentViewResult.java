package com.lexmark.result;

import java.io.Serializable;
import java.util.HashMap;

@SuppressWarnings("rawtypes")
public class DocumentViewResult implements Serializable {
    
    private static final long serialVersionUID = 1L;
	private HashMap documentHierarchy;

	public HashMap getDocumentHierarchy() {
		return documentHierarchy;
	}

	public void setDocumentHierarchy(HashMap documentHierarchy) {
		this.documentHierarchy = documentHierarchy;
	}
	
	
	
}
