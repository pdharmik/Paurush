package com.lexmark.service.impl.mock;

import static java.util.Locale.CHINA;
import static java.util.Locale.FRANCE;
import static java.util.Locale.GERMAN;
import static java.util.Locale.JAPAN;
import static java.util.Locale.US;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

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
import com.lexmark.domain.Document;
import com.lexmark.domain.DocumentDefinition;
import com.lexmark.domain.Role;
import com.lexmark.domain.RoleCategory;
import com.lexmark.domain.RoleCategoryLocale;
import com.lexmark.domain.SupportedLocale;
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
import com.lexmark.service.api.DocumentManagementService;
public class DocumentManagementServiceImpl implements DocumentManagementService {
	private static long documentId = 1;
	private static int definitionId = 1;
	private static List<String> files;

	private static HashMap<String, Tree> mappedNodes = new HashMap<String, Tree>();
	

	
	private static String[][]  allRoles  = new String[10][];
	static{
		for (int i = 0; i < allRoles.length; i++) {
			allRoles[i] = new String[2];
			allRoles[i][0] =  "ROLE"+i;
			allRoles[i][1] = "Role "+i;
		}
	}
	private static SupportedLocale[]  allLocales  = new SupportedLocale[5];
	
	static {
		Locale[] _locales = {US, GERMAN, CHINA, FRANCE, JAPAN};
		for (int i = 0; i < allLocales.length; i++) {
			allLocales[i] =  new SupportedLocale();
			allLocales[i].setSupportedLocaleCode(_locales[i].getLanguage());
			allLocales[i].setSupportedLocaleName(_locales[i].getDisplayName());
		}
	}
	private static RoleCategory[]  allCategories  = new RoleCategory[8];
	static{
		for (int i = 0; i < allCategories.length; i++) {
			allCategories[i] = createCategory(i);
		}
	}
	static {
		files = new ArrayList<String>();
		String rootPath = "/Structure/";
		files.add(rootPath);
		final int NUM_FISRT_LEVEL = 3;
		for (int i = 0; i < NUM_FISRT_LEVEL; i++) {
			String level1Name = rootPath + "Level 1 Node " + i + "/";
			files.add(level1Name);
			final int NUM_SECOND_LEVEL = 4;
			for (int j = 0; j < NUM_SECOND_LEVEL; j++) {
				String level2Name = level1Name + "Level 2 Node " + i + "." + j + "/";
				files.add(level2Name);
				final int NUM_THIRD_LEVEL = 5;
				for (int k = 0; k < NUM_THIRD_LEVEL; k++) {
					String level3Name = level2Name + "Level 3 Node " + i + "." + j + "." + k + "/";
					files.add(level3Name);
					final int NUM_FILES = 35;
					for (int l = 0; l < NUM_FILES; l++) {
						files.add(level3Name + "File " + i + "." + j + "." + k + "." + l);
					}
				}
			}
		}
		
		HashMap<String, ArrayList<Tree>> strMappedPendingNodes = new HashMap<String, ArrayList<Tree>>();
		int j = 0;
		for (String file : files) {
			Document fileDoc = createDocument(file, j);
			Tree node = new Tree();
			node.doc = fileDoc;
			mappedNodes.put(file, node);
			String parent = getParentOf(file);
			Tree parentNode = mappedNodes.get(parent);
			
			if(parentNode==null){
				ArrayList<Tree> pendingNodes = strMappedPendingNodes.get(parent);
				if(pendingNodes==null){
					strMappedPendingNodes.put(parent,pendingNodes = new ArrayList<Tree>());
				}
				pendingNodes.add(node);
			} else {
				parentNode.addSub(node);
			}
			
			ArrayList<Tree> pendingNodes = strMappedPendingNodes.get(file);
			if(pendingNodes!=null){
				for (Tree tree : pendingNodes) {
					node.addSub(tree);
				}
			}
			j ++;
		}
		
		RoleCategory[] categories = new RoleCategory[5];
		for (int i = 0; i < categories.length; i++) {
			categories[i] = createCategory(i);
		}
	}

	private static String getParentOf(String file) {
		if (file.endsWith("/"))
			file = file.substring(0, file.length() - 1);
		String parentPath = file.substring(0, file.lastIndexOf('/') + 1);
		return parentPath;
	}

	private static RoleCategory createCategory(int i) {
		RoleCategory category = new RoleCategory();
		category.setCategoryId(i);
		List<RoleCategoryLocale> localeList = new ArrayList<RoleCategoryLocale>();
		for(int j=0;j<allLocales.length;j++){
			RoleCategoryLocale locale = new RoleCategoryLocale();
			locale.setCategoryLocaleId(j);
			locale.setName("Category"+i+" - "+allLocales[j].getSupportedLocaleName());
			locale.setSupportedLocale(allLocales[j]);
			localeList.add(locale);
		}
		category.setLocaleList(localeList);
		category.setName("Category"+i);
		category.setOrderNumber(i);
		List<Role> roles = new ArrayList<Role>();
		for (int j = 0; j < allRoles.length; j++) {
			if(j%(i+1)==0){
				Role role = new Role();
				role.setName( allRoles[j][1]);
				roles .add(role);
			}
		}
		category.setRoles(roles);
		return category;
	}

	public DocumentViewResult retrieveDocumentHierarchy(DocumentViewContract contract) {
		DocumentViewResult result = new DocumentViewResult();
		String path = contract.getPath();
		if (!path.endsWith("/")) {
			path += "/";
		}
		Tree tree = mappedNodes.get(path);
		if(tree!=null){
			Object mapOrList = convertToMapOrList(tree);
			HashMap<Document, Object> hierarchy = new HashMap<Document, Object>();
			hierarchy.put(tree.doc, mapOrList);
			result.setDocumentHierarchy(hierarchy);
		}
		return result;
	}

	private Object convertToMapOrList(Tree tree) {
		if(tree.isLeaf){
			ArrayList<Document> list = new ArrayList<Document>();
			for (Tree subTree : tree.subs) {
				list.add(subTree.doc);
			}
			return list;
		}else{
			HashMap<Document,Object> map = new HashMap<Document,Object>();
			for (Tree subTree : tree.subs) {
				map.put(subTree.doc,convertToMapOrList(subTree));
			}
			return map;
		}
	}

	private static Document createDocument(String path, int j) {
		Document document = new Document();
		boolean isDirectory = path.endsWith("/");

		String[] tokens = path.split("/");
		String lastToken = tokens.length == 0 ? "%ROOT%" : tokens[tokens.length - 1];

		document.setFileType("%DIR%");
		document.setFilePath(path);
		document.setFileName(lastToken);

		document.setFileObjectId(""+documentId++);
		document.setFiledataLink(0L);
		document.setLastUpdateTime(new Date());
		document.setFileSize(3123);
		document.setDirectory(isDirectory);

		DocumentDefinition definition = new DocumentDefinition();
		{
			definition.setId(definitionId);
			definition.setMdmId("Mdm" + definitionId % 5);
			definition.setMdmLevel("LV" + definitionId % 5);
			definition.setName("DEF" + definitionId);
			definition.setRoleCategory(allCategories[(int) j%3]);
			definitionId++;
		}
		document.setDefinition(definition);
		return document;
	}

	private static class Tree {
		Document doc;
		boolean isLeaf = true;
		ArrayList<Tree> subs = new ArrayList<Tree>();
		public  void addSub(Tree sub){
			subs.add(sub);
			isLeaf = !sub.doc.isDirectory();
		}
		@Override
		public String toString() {
			StringBuffer sbuff = new StringBuffer();
			sbuff.append(doc).append("\n");
			printSub(1,sbuff,subs);
			return sbuff.toString();
		}
		private void printSub(int indent, StringBuffer sbuff, ArrayList<Tree> subs2) {
			for (Tree tree : subs2) {
				for (int j = 0; j < indent; j++) {
					sbuff.append("    ");
				}
				sbuff.append(tree.doc).append("\n");
				printSub(indent+1,sbuff,tree.subs);
			}
			
		}
	}
	public static void main(String[] args) {
		for (int i = 0; i < args.length; i++) {
			
		}
	}

	public DocumentConfigurePathResult configurePath(DocumentConfigurePathContract contract) {
		// TODO Auto-generated method stub
		return null;
	}

	public DocumentGetConfigurePathResult getConfiguredPath(DocumentGetConfigurePathContract contract) {
		// TODO Auto-generated method stub
		return null;
	}

	public DocumentAdministrationListResult retrieveDocumentAdministrationList(DocumentAdministrationListContract contract) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public DocumentAdministrationListResult retrieveDocumentAdministrationDisplayOrder(DocumentAdministrationListContract contract, String categoryId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public String retrieveCategoryIDForPopup(String categoryName) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Boolean updateDisplayOrder(List<String> docId, List<String> orderValue) {
		// TODO Auto-generated method stub
		return null;
	}

	public CategoryAdministrationListResult retrieveCategoryAdministrationList(CategoryAdministrationListContract contract) {
		// TODO Auto-generated method stub
		return null;
	}

	public CategoryDetailResult retrieveCategoryDetail(CategoryDetailContract contract) {
		// TODO Auto-generated method stub
		return null;
	}

	public CategorySaveResult saveCategory(CategorySaveContract contract) {
		// TODO Auto-generated method stub
		return null;
	}

	public CategoryDeleteResult deleteCategory(CategoryDeleteContract contract) {
		// TODO Auto-generated method stub
		return null;
	}

	public DocumentDefinitionDetailsResult getDocumentDefinitionDetails(DocumentDefinitionDetailsContract contract) {
		// TODO Auto-generated method stub
		return null;
	}

	public CategoryHierarchyResult retrieveCategoryHierarchy(CategoryHierarchyContract contract) {
		// TODO Auto-generated method stub
		return null;
	}

	public DocumentUserListResult retrieveDocumentUserList(DocumentUserListContract contract) {
		// TODO Auto-generated method stub
		return null;
	}

	public DocumentDefinitionSaveResult saveDocumentDefinition(DocumentDefinitionSaveContract contract) {
		// TODO Auto-generated method stub
		return null;
	}
	public String retrieveMaxDisplayOrder(String categoryId) {
        // TODO Auto-generated method stub
        return null;
}

	@Override
	public DocumentDefinitionResult deleteDocumentDefinition(DocumentDefinitionDeleteContract contract) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DocumentDeleteResult handleDocumentDelete(DocumentDeleteContract contract) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Boolean saveCountry(List<String> countryListArray, String categoryId) {
		// TODO Auto-generated method stub
		return true;
	}
	
	public Boolean savePartnerType(List<String> partnerTypeList, String categoryId) {
		// TODO Auto-generated method stub
		return true;
	}
	
	public List<String> getDB_CountryList(String categoryId){
		return null;
	}
	
	public List<String> getDB_PartnerTypeList(String categoryId){
		return null;
	}
		
}
