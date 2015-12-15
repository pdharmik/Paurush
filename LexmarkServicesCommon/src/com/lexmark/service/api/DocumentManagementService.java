package com.lexmark.service.api;

import java.sql.SQLException;
import java.util.List;

import com.lexmark.contract.CategoryAdministrationListContract;
import com.lexmark.contract.CategoryDeleteContract;
import com.lexmark.contract.CategoryDetailContract;
import com.lexmark.contract.CategoryHierarchyContract;
import com.lexmark.contract.CategorySaveContract;
import com.lexmark.contract.DocumentAdministrationListContract;
import com.lexmark.contract.DocumentConfigurePathContract;
import com.lexmark.contract.DocumentConfigurePathResult;
import com.lexmark.contract.DocumentDefinitionDeleteContract;
import com.lexmark.contract.DocumentDefinitionDetailsContract;
import com.lexmark.contract.DocumentDefinitionSaveContract;
import com.lexmark.contract.DocumentDeleteContract;
import com.lexmark.contract.DocumentGetConfigurePathContract;
import com.lexmark.contract.DocumentUserListContract;
import com.lexmark.contract.DocumentViewContract;
import com.lexmark.result.CategoryAdministrationListResult;
import com.lexmark.result.CategoryDeleteResult;
import com.lexmark.result.CategoryDetailResult;
import com.lexmark.result.CategoryHierarchyResult;
import com.lexmark.result.CategorySaveResult;
import com.lexmark.result.DocumentAdministrationListResult;
import com.lexmark.result.DocumentDefinitionDetailsResult;
import com.lexmark.result.DocumentDefinitionResult;
import com.lexmark.result.DocumentDefinitionSaveResult;
import com.lexmark.result.DocumentDeleteResult;
import com.lexmark.result.DocumentGetConfigurePathResult;
import com.lexmark.result.DocumentUserListResult;
import com.lexmark.result.DocumentViewResult;

public interface DocumentManagementService {
	public DocumentViewResult retrieveDocumentHierarchy(DocumentViewContract contract) throws SQLException;

	public DocumentConfigurePathResult configurePath(DocumentConfigurePathContract contract);

	public DocumentGetConfigurePathResult getConfiguredPath(DocumentGetConfigurePathContract contract);

	public DocumentAdministrationListResult retrieveDocumentAdministrationList(DocumentAdministrationListContract contract);
	
	public DocumentAdministrationListResult retrieveDocumentAdministrationDisplayOrder(DocumentAdministrationListContract contract, String categoryId);
	
	public String retrieveCategoryIDForPopup(String categoryName);
	
	public Boolean updateDisplayOrder(List<String> docIdList, List<String> orderValuesList);

	public CategoryAdministrationListResult retrieveCategoryAdministrationList(CategoryAdministrationListContract contract);

	public CategoryDetailResult retrieveCategoryDetail(CategoryDetailContract contract);

	public CategorySaveResult saveCategory(CategorySaveContract contract);

	public CategoryDeleteResult deleteCategory(CategoryDeleteContract contract) throws Exception;

	public DocumentDefinitionDetailsResult getDocumentDefinitionDetails(DocumentDefinitionDetailsContract contract);

	public CategoryHierarchyResult retrieveCategoryHierarchy(CategoryHierarchyContract contract);

	public DocumentUserListResult retrieveDocumentUserList(DocumentUserListContract contract) throws SQLException;

	public DocumentDefinitionSaveResult saveDocumentDefinition(DocumentDefinitionSaveContract contract);

	public DocumentDefinitionResult deleteDocumentDefinition(DocumentDefinitionDeleteContract contract);

	public DocumentDeleteResult handleDocumentDelete(DocumentDeleteContract contract) ;
	
	public String retrieveMaxDisplayOrder(String categoryId);
	
	public Boolean savePartnerType(List<String> partnerTypeList, String categoryId);
	
	public Boolean saveCountry(List<String> countryListArray, String categoryId);
	
	public List<String> getDB_CountryList(String categoryId);
	
	public List<String> getDB_PartnerTypeList(String categoryId);
}
