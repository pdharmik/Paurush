package com.lexmark.result;

import java.io.Serializable;

import com.lexmark.domain.DocumentDefinition;

public class DocumentDefinitionDetailsResult implements Serializable {
    
    private static final long serialVersionUID = 1L;
	private DocumentDefinition documentDefinition;

	public DocumentDefinition getDocumentDefinition() {
		return documentDefinition;
	}

	public void setDocumentDefinition(DocumentDefinition documentDefinition) {
		this.documentDefinition = documentDefinition;
	}
	
}
