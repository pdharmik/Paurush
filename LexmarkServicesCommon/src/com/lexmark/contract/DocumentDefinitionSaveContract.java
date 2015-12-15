package com.lexmark.contract;

import java.io.Serializable;

import com.lexmark.domain.DocumentDefinition;

public class DocumentDefinitionSaveContract implements Serializable {
    
    private static final long serialVersionUID = 1L;
	private DocumentDefinition definition;

	public DocumentDefinition getDefinition() {
		return definition;
	}

	public void setDefinition(DocumentDefinition definition) {
		this.definition = definition;
	}
	
}
