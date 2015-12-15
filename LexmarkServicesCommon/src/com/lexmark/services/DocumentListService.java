package com.lexmark.services;

import java.sql.SQLException;
import java.util.List;

import com.lexmark.domain.Document;

public interface DocumentListService {

	public abstract List<Document> listDocumentByPath(String path) throws SQLException;
	public abstract List<Document> listDocumentByDefinitionId(int definitionId) throws SQLException;
}