package com.lexmark.result;

import java.io.Serializable;
import java.util.List;

import com.lexmark.domain.DocumentDefinition;

public class DocumentAdministrationListResult implements Serializable {
    
    private static final long serialVersionUID = 1L;
	private List<DocumentDefinition> documentDefinitions;

	public List<DocumentDefinition> getDocumentDefinitions() {
		return documentDefinitions;
	}

	public void setDocumentDefinitions(List<DocumentDefinition> documentDefinitions) {
		this.documentDefinitions = documentDefinitions;
	}

}
