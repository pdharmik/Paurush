package com.lexmark.result;

import java.io.Serializable;
import java.util.List;

import com.lexmark.domain.Document;

public class DocumentUserListResult implements Serializable {
    
    private static final long serialVersionUID = 1L;
	private List<Document> documentList;

	public List<Document> getDocumentList() {
		return documentList;
	}

	public void setDocumentList(List<Document> documentList) {
		this.documentList = documentList;
	}

}
