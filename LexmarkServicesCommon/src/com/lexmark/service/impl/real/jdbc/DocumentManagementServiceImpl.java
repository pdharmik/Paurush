package com.lexmark.service.impl.real.jdbc;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import com.lexmark.constants.LexmarkConstants;
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
import com.lexmark.domain.Category_Country;
import com.lexmark.domain.Category_PartnerType;
import com.lexmark.domain.ConfigurationItem;
import com.lexmark.domain.Document;
import com.lexmark.domain.DocumentDefinition;
import com.lexmark.domain.ReportDeleteStatus;
import com.lexmark.domain.RoleCategory;
import com.lexmark.domain.RoleCategoryLocale;
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
import com.lexmark.services.DocumentListService;
import com.lexmark.util.StringUtil;

@SuppressWarnings( { "unchecked" })
public class DocumentManagementServiceImpl implements DocumentManagementService {

	
	private static final String CONFIG_KEY_DOC_ROOT_PATH = "doc.rootPath";
	private static Logger logger = LogManager.getLogger(DocumentManagementServiceImpl.class);
	//CI7 BRD 14-06-01
	//private static final String RETRIEVE_MAXDISPLAYORDER="select max(display_order) from report_definition where category_id = :category_id";
	private static final String RETRIEVE_MAXDISPLAYORDER="select count(*) from report_definition where category_id = :category_id and is_deleted='F'";
	private static final String QUERY_DOCUMENT_DEFINITION_LIST = 
		"SELECT RD.REPORT_DEFINITION_ID, RD.NAME, C.NAME AS CATEGORY_NAME, RD.DISPLAY_ORDER, RD.LIMIT_FLAG, RD.CUSTOMER_ACCOUNT from REPORT_DEFINITION RD, CATEGORY C " +
		" WHERE RD.CATEGORY_ID = C.CATEGORY_ID " +
		" AND RD.DEFINITION_TYPE = 'DOC' AND C.is_deleted = 'F' AND RD.is_deleted = 'F'";	 
	//Done for CI BRD 14-06-01
	private static final String QUERY_DOCUMENT_DISPLAYORDER_LIST = 
			"SELECT RD.REPORT_DEFINITION_ID, RD.NAME, RD.DISPLAY_ORDER from REPORT_DEFINITION RD" +
			" WHERE RD.CATEGORY_ID = :categoryID" +
			" AND RD.is_deleted = 'F'";	 
	private static final String QUERY_DOCUMENT_CATEGORYID = 
			"select category_id from category where name= :categoryName";
	private static final String QUERY_CATEGORYID_NOT_IN_PARTNERTYPE_AND_COUNTRY="select distinct category_id from category where category_id  not in" +
	"(select category_id_original from category_country " +
	"union " +
	"select category_id_original from category_partnertype)" +
	"and is_deleted='F'"; 
	private DocumentListService documentListService;

	public DocumentListService getDocumentListService() {
		return documentListService;
	}

	public void setDocumentListService(DocumentListService documentListService) {
		this.documentListService = documentListService;
	}

	public DocumentViewResult retrieveDocumentHierarchy(DocumentViewContract contract) throws SQLException {
		String path = contract.getPath();
		if (!path.endsWith("/"))
			path += "/";
		List<Document> documents = documentListService.listDocumentByPath(path);
		HashMap hiea = new HashMap();
		for (Iterator<Document> it = documents.iterator(); it.hasNext();) {
			Document document = it.next();
			String filePath = document.getFilePath();
			List store = ensureFolder(path, hiea, filePath);
			store.add(document);
		}
		DocumentViewResult result = new DocumentViewResult();
		result.setDocumentHierarchy(hiea);
		return result;
	}

	private List ensureFolder(String root, HashMap hiea, String filePath) {
		for (int idxOfSlash = root.length() - 1; idxOfSlash >= 0; idxOfSlash = filePath.indexOf('/', idxOfSlash + 1)) {
			String currentPath = filePath.substring(0, idxOfSlash);
			Document current = new FolderDocument(currentPath);
			Object store = hiea.get(current);
			if (idxOfSlash == filePath.lastIndexOf('/')) {
				if (store == null || !(store instanceof List)) {
					store = new ArrayList();
					hiea.put(current, store);
				}
				return (List) store;
			} else {
				if (store == null || !(store instanceof Map)) {
					store = new HashMap();
					hiea.put(current, store);
				}
				hiea = (HashMap) store;
			}
		}
		throw new RuntimeException();

	}

	private static class FolderDocument extends Document {
		private static final long serialVersionUID = 8918005697584524715L;

		private FolderDocument(String path) {
			if (path.length() <= 1) {
				this.setFilePath("/");
				this.setFileName("/");
			} else {
				this.setFilePath(path);
				int lastIdx = path.lastIndexOf('/');
				this.setFileName(path.substring(lastIdx + 1));
			}
		}

		@Override
		public boolean isDirectory() {
			return true;
		}

		@Override
		public boolean equals(Object obj) {
			if (!(obj instanceof FolderDocument))
				return false;
			FolderDocument other = (FolderDocument) obj;
			return this.getFilePath().equalsIgnoreCase(other.getFilePath());
		}

		@Override
		public int hashCode() {
			return this.getFilePath().hashCode();
		}
	}

	public DocumentConfigurePathResult configurePath(DocumentConfigurePathContract contract) {
		logger.debug("configurePath....");
		ConfigurationItem item = fetchConfigureItem();
		if (item == null) {
			item = new ConfigurationItem();
			item.setItemKey(CONFIG_KEY_DOC_ROOT_PATH);
		}
		String path = contract.getPath();
		item.setItemValue(path);
		logger.debug("to save path as: " + path + "item id: " + item.getId());
		HibernateUtil.beginTransaction();
		Session session = HibernateUtil.getSession();
		session.saveOrUpdate(item);
		HibernateUtil.commitTransaction();
		session.flush();
		HibernateUtil.closeSession();

		logger.debug("fetch imediately after save. " + fetchConfigureItem());
		// throw new RuntimeException("aaaaaa");
		return new DocumentConfigurePathResult();
	}

	public DocumentGetConfigurePathResult getConfiguredPath(DocumentGetConfigurePathContract contract) {
		logger.debug("getConfiguredPath....");
		ConfigurationItem item = fetchConfigureItem();
		logger.debug(item);
		String path = item == null ? "/" : item.getItemValue();
		DocumentGetConfigurePathResult result = new DocumentGetConfigurePathResult();
		result.setPath(path);
		return result;
	}

	private ConfigurationItem fetchConfigureItem() {
		try {
			Query query = HibernateUtil.getSession().createQuery("from ConfigurationItem where itemKey=?");

			query.setString(0, CONFIG_KEY_DOC_ROOT_PATH);
			Iterator iterate = query.iterate();
			ConfigurationItem item = null;
			if (iterate.hasNext()) {
				item = (ConfigurationItem) iterate.next();
				logger.debug("item queried out is: " + item);
			}
			HibernateUtil.closeSession();
			return item;
		} catch (Exception e) {
			return null;
		}
	}

	public DocumentAdministrationListResult retrieveDocumentAdministrationList(DocumentAdministrationListContract contract) {

		Query query = HibernateUtil.getSession().createSQLQuery(QUERY_DOCUMENT_DEFINITION_LIST);
		List list = query.list();
		logger.debug("total num of definition: " + list.size());
		DocumentAdministrationListResult result = new DocumentAdministrationListResult();
		List<DocumentDefinition> definitions = new ArrayList<DocumentDefinition>();
		for (Object object : list) {
			Object[] arr = (Object[]) object;
			DocumentDefinition definition = new DocumentDefinition();
			definition.setId(Integer.valueOf(arr[0].toString()));
			definition.setName((String) arr[1]);
			if (arr[2] != null) {
				RoleCategory category = new RoleCategory();
				category.setName((String) arr[2]);
				definition.setRoleCategory(category);
			}
			
			if (arr[3] != null) {
				logger.debug("Array 3 NOT NULL---->>");
//				RoleCategory category = new RoleCategory();
//				category.setName((String) arr[2].toString());
//				definition.setRoleCategory(category);
				definition.setDisplay_order((String)arr[3].toString());
			}
			else{
				logger.debug("Array 3 IS NULL---->>");
//				RoleCategory category = new RoleCategory();
//				category.setName("");
//				definition.setRoleCategory(category);
				definition.setDisplay_order("");
			}
			
			if (arr[4] != null) {
				logger.debug("Array 4 NOT NULL---->>");
//				RoleCategory category = new RoleCategory();
//				category.setName((String) arr[2].toString());
//				definition.setRoleCategory(category);
				if(!"".equals((String)arr[4].toString()) && "T".equalsIgnoreCase((String)arr[4].toString())){
					
					Boolean limitFlag = new Boolean(true);
					logger.debug("limit flag value is true "+limitFlag);
					definition.setLimitFlag(limitFlag);
				}
				else{
					Boolean limitFlag = new Boolean(false);
					//Boolean limitFlag = false;
					definition.setLimitFlag(limitFlag);
				}
				
			}
			else{
				logger.debug("limit flag value is null from db ");
			}
			if (arr[5] != null) {
				logger.debug("Array 5 NOT NULL---->>");
				definition.setCustomerAccount((String)arr[5].toString());				
			}
			else{
				logger.debug("Customer Account value is null from db ");
			}
			
			definitions.add(definition);
		}
		result.setDocumentDefinitions(definitions);
		return result;
	}
	//For CI BRD 14-06-01
	public DocumentAdministrationListResult retrieveDocumentAdministrationDisplayOrder(DocumentAdministrationListContract contract, String categoryID) {
//		LOGGER.enter(this.getClass().getSimpleName(), "retrieveDocumentAdministrationDisplayOrder");
		logger.debug("Category Id inside retrieveDocumentAdministrationDisplayOrder---->>"+categoryID);
		Query query = HibernateUtil.getSession().createSQLQuery(QUERY_DOCUMENT_DISPLAYORDER_LIST);
		query.setParameter("categoryID", categoryID);
		List list = query.list();
		logger.debug("total num of definition: " + list.size());
		DocumentAdministrationListResult result = new DocumentAdministrationListResult();
		List<DocumentDefinition> definitions = new ArrayList<DocumentDefinition>();
		for (Object object : list) {
			Object[] arr = (Object[]) object;
			DocumentDefinition definition = new DocumentDefinition();
			logger.debug("document ID from DB---->>"+arr[0].toString());
			logger.debug("document Name from DB---->>"+arr[1].toString());
			definition.setId(Integer.valueOf(arr[0].toString()));
			definition.setName((String) arr[1]);
			if (arr[2] != null) {
				logger.debug("Array 2 NOT NULL---->>");
//				RoleCategory category = new RoleCategory();
//				category.setName((String) arr[2].toString());
//				definition.setRoleCategory(category);
				definition.setDisplay_order((String)arr[2].toString());
			}
			else{
				logger.debug("Array 2 IS NULL---->>");
//				RoleCategory category = new RoleCategory();
//				category.setName("");
//				definition.setRoleCategory(category);
				definition.setDisplay_order("");
			}
				
			definitions.add(definition);
		}
		result.setDocumentDefinitions(definitions);
		return result;
	}
	
	//For CI BRD 14-06-01
	public String retrieveCategoryIDForPopup(String categoryName) {
//		LOGGER.enter(this.getClass().getSimpleName(), "retrieveCategoryIDForPopup");
		logger.debug("category name to be sent to the query---->>"+categoryName);
		String categoryId="";
		try{
		Query query = HibernateUtil.getSession().createSQLQuery(QUERY_DOCUMENT_CATEGORYID);
		logger.debug("category name to be sent just before the query---->>"+categoryName);
		query.setParameter("categoryName", categoryName);
		List list= query.list();
		if(list.size()>0){
			categoryId=list.get(0).toString();
		}
		logger.debug("categoryId fetched from the query---->>"+categoryId);
		}
		
		catch(HibernateException hbex){
			hbex.getMessage();
		}
		catch(Exception ex){
			ex.printStackTrace();
		}

//		LOGGER.exit(this.getClass().getSimpleName(), "retrieveCategoryIDForPopup");
		return categoryId;
	}
	

	public CategoryAdministrationListResult retrieveCategoryAdministrationList(CategoryAdministrationListContract contract) {
		CategoryAdministrationListResult result = new CategoryAdministrationListResult();
		Query query = HibernateUtil.getSession().createQuery("from RoleCategory where type=? and isDeleted=? order by orderNumber");
		query.setString(0, LexmarkConstants.CATEGORY_TYPE_DOCUMENT);
		query.setBoolean(1, false);
		List list = query.list();
		result.setRoleCategoryList(list);
		HibernateUtil.closeSession();
		return result;
	}

	public CategoryDetailResult retrieveCategoryDetail(CategoryDetailContract contract) {
		Session session = HibernateUtil.getSession();
		RoleCategory category = (RoleCategory) session.load(RoleCategory.class, contract.getCategoryId());
		CategoryDetailResult result = new CategoryDetailResult();
		result.setCategory(category);
		session.close();
		return result;
	}

	public CategorySaveResult saveCategory(CategorySaveContract contract) {
		CategorySaveResult result = new CategorySaveResult();
		RoleCategory category = contract.getCategory();
		
//		Integer categoryId = category.getCategoryId();
		category.setType(LexmarkConstants.CATEGORY_TYPE_DOCUMENT);
		List<RoleCategoryLocale> categoryLocaleList = new ArrayList<RoleCategoryLocale>();
		for (RoleCategoryLocale categoryLocale : category.getLocaleList()) {
			if (!StringUtil.isStringEmpty(categoryLocale.getName())) {
				categoryLocaleList.add(categoryLocale);
			}
		}
		category.setLocaleList(categoryLocaleList);
		try {
			HibernateUtil.beginTransaction();
			// generate order number for new category
			if (category.getCategoryId() == null) {
				int maxOrder = 0;
				Query queryMaxOrderNumber = HibernateUtil.getSession().createSQLQuery("select nvl(max(order_num),0) from category where type = 'D' and is_deleted = 'F'");
				List list = queryMaxOrderNumber.list();
				if (list != null && list.size() > 0) {
					maxOrder = ((BigDecimal) list.get(0)).intValue();
				}
				
				category.setOrderNumber(maxOrder + 1);
			} else { // Delete empty category locale
				Set<Integer> ToBeDeletedLocaleSet = new HashSet<Integer>();
				Query queryCategoryLocaleList = HibernateUtil.getSession().createSQLQuery(
						"select category_locale_id from category_locale where category_id = :categoryId");
				queryCategoryLocaleList.setParameter("categoryId", category.getCategoryId());
				List list = queryCategoryLocaleList.list();
				if (list != null && list.size() > 0) {
					for (int i = 0; i < list.size(); i ++) {
 						ToBeDeletedLocaleSet.add(((BigDecimal) list.get(i)).intValue());
					}

					for (RoleCategoryLocale categoryLocale : categoryLocaleList) {
						if (categoryLocale.getCategoryLocaleId() == null) {
							continue;
						}
						Integer categoryLocaleId = Integer.valueOf(categoryLocale.getCategoryLocaleId());
						if (ToBeDeletedLocaleSet.contains(categoryLocaleId)) {
							ToBeDeletedLocaleSet.remove(categoryLocaleId);
						}
					}
					
					for (Integer categoryLocaleId : ToBeDeletedLocaleSet) {
						RoleCategoryLocale categoryLocale = (RoleCategoryLocale)HibernateUtil.getSession().get(RoleCategoryLocale.class, categoryLocaleId);
						HibernateUtil.getSession().delete(categoryLocale);
					}
				}
				
			}
			
			HibernateUtil.getSession().saveOrUpdate(category);
			HibernateUtil.commitTransaction();
			result.setResult(true);
		} catch (Exception e) {
			logger.debug(e.getMessage());
			result.setResult(false);
		} finally {
			HibernateUtil.closeSession();
		}
		return result;
	}

	public CategoryDeleteResult deleteCategory(CategoryDeleteContract contract) throws Exception {
		Session session = HibernateUtil.getSession();
		// check whether the category has associated valid document definitions
		// SQL to search document definitions' id associated to provided category_id
		String sqlSearchDocumentByCategoryId = 
			"select report_definition_id from report_definition where category_id = :categoryId and definition_type = :definitionType and is_deleted = 'F'";
		Query queryDocumentIds = session.createSQLQuery(
				sqlSearchDocumentByCategoryId);
		queryDocumentIds.setParameter("categoryId", contract.getCategoryId());
		queryDocumentIds.setParameter("definitionType", "DOC");
		List docIdList = queryDocumentIds.list();
		if (docIdList.size() > 0) {
			HibernateUtil.closeSession();
			logger.debug("Category:" + contract.getCategoryId() + " can not be deleted, because valid document definitions are associated.");
			throw new Exception(LexmarkConstants.DOC_DEFINITION_ASSOCIATED);
		}
		
		Query query = session.createQuery("SELECT orderNumber from RoleCategory where categoryId=?");
		query.setInteger(0, contract.getCategoryId());
		Integer orderNum = (Integer) query.uniqueResult();

		HibernateUtil.beginTransaction();
		query = session.createQuery("update RoleCategory set isDeleted=? where categoryId=?");
		query.setBoolean(0, true);
		query.setInteger(1, contract.getCategoryId());
		query.executeUpdate();
		logger.debug("orderNum is: "+orderNum);
		
		if (orderNum != null) {
			query = session.createQuery("update RoleCategory set orderNumber = orderNumber - 1 where orderNumber > ? and type=?");

			query.setInteger(0, orderNum);
			query.setString(1, LexmarkConstants.CATEGORY_TYPE_DOCUMENT);
			logger.debug("orderNum is: "+orderNum);
			query.executeUpdate();
		}
		HibernateUtil.commitTransaction();
		HibernateUtil.closeSession();
		return new CategoryDeleteResult();
	}
	
	//Done for CI BRD 14-06-01-- to update Documents Display Order in DB
	public Boolean updateDisplayOrder(List<String> docId, List<String> orderValue) {
		
		try{
		Session session = HibernateUtil.getSession();
		// check whether the category has associated valid document definitions
		// SQL to search document definitions' id associated to provided category_id
		
//		Query query = session.createQuery("SELECT orderNumber from RoleCategory where categoryId=?");
//		query.setInteger(0, contract.getCategoryId());
//		Integer orderNum = (Integer) query.uniqueResult();
		Query query=null;
		
		HibernateUtil.beginTransaction();
		for(int i=0;i<docId.size();i++){		
		query = session.createQuery("update DocumentDefinition set display_order= :orderValue where id= :docId" );
		query.setParameter("docId", Integer.parseInt(docId.get(i).toString().trim()));
		//query.setParameter("orderValue", Integer.parseInt(orderValue.get(i).toString().trim()));
		query.setParameter("orderValue", orderValue.get(i).toString().trim());
		query.executeUpdate();
		}
	
		HibernateUtil.commitTransaction();
		HibernateUtil.closeSession();
		return true;
		}
		
		catch(Exception ex){
			ex.printStackTrace();
			return false;
		}
	}

	public DocumentDefinitionDetailsResult getDocumentDefinitionDetails(DocumentDefinitionDetailsContract contract) {
		logger.debug("--------------------------->" + contract.getDefinitionId());
		Session session = HibernateUtil.getSession();
		DocumentDefinition documentDefinition = (DocumentDefinition) session.load(DocumentDefinition.class, contract
				.getDefinitionId());
		DocumentDefinitionDetailsResult result = new DocumentDefinitionDetailsResult();
		result.setDocumentDefinition(documentDefinition);
		logger.debug(documentDefinition.getLocaleList());
		return result;
	}

	private static final String SQL_CATEGORY_QUERY = "select distinct nvl(cl.name, c.name) categoryName, to_char(c.category_id),  nvl(dl.name, rd.name), to_char(rd.report_definition_id), rd.display_order " +
			"from supported_locale sl left join category_locale cl on (sl.supported_locale_id = cl.supported_locale_id and sl.supported_locale_code = :localeCode) right join " +
			"category c on (c.category_id = cl.category_id), " +
			"supported_locale sl2 left join definition_locale dl on (sl2.supported_locale_id = dl.supported_locale_id and sl2.supported_locale_code = :localeCode) right join " +
			"report_definition rd on (rd.report_definition_id = dl.report_definition_id), " +
			"role r, report_acl ra " +
			"where rd.category_id = c.category_id {0} and r.role_id = ra.role_id and ra.category_id = c.category_id " +
			"and UPPER(r.name) in ({1}) " +
			"and c.type= :categoryType " +
			"and rd.DEFINITION_TYPE = :definitionType " +
			"and c.is_deleted = :isDeleted and rd.is_deleted = :isDeleted " +
			"and (rd.mdm_level = :mdmLevel or rd.mdm_level is null) " +
			"and (rd.mdm_id = :mdmId or rd.mdm_id is null)" +
			" order by categoryName,ABS(display_order)";
	
	public CategoryHierarchyResult retrieveCategoryHierarchy(CategoryHierarchyContract contract) {
		String catQuery="";
			if(contract.getPartnerPortal()){
				catQuery="and c.category_id in (:categories)";
			}
			
		CategoryHierarchyResult categoryHierarchyResult = new CategoryHierarchyResult();
		List<RoleCategory> categoryList = new ArrayList<RoleCategory>();
		categoryHierarchyResult.setCategoryHierarchy(categoryList);
		//Got Siebel Lists
		List<String> siebel_PartnerTypeList = contract.getPartnerTypeList();
		List<String> siebel_CountryList = contract.getCountryList();
		
		List<Object[]> db_partnerTypeList;
		List<Object[]> db_countryList;

		StringBuilder rolesString = new StringBuilder();
		for (String roleName : contract.getRoles()){
			rolesString.append("'" + roleName.toUpperCase() + "'");
			rolesString.append(",");
		}
		if(rolesString.length() > 0)
			rolesString.delete(rolesString.length() - 1, rolesString.length());

		String language = contract.getLocale();
		MessageFormat mf = new MessageFormat(SQL_CATEGORY_QUERY);
		String sql = mf.format(new Object[]{catQuery,rolesString.toString()});		
		Set<String> cat_ids_displayed = new HashSet<String>();
		if(contract.getServicesPortal()){
			cat_ids_displayed.add("");
		}
		try {
			
			
			if(contract.getPartnerPortal()){
			//added for BRD 14-07-04
			//Get DB Lists
			
			/*
			 * The below query returns the join of category, category_partnertype , category_country 
			 * the columns selected are category id , country and patner type
			 * */
			
			String country_AND_partnerType_CATIDS="select c.category_id,cc.country,cp.partner_type " +
			"from category c left join category_partnertype cp on (c.category_id=cp.category_id_original) " +
			"left join category_country cc on (c.category_id=cc.category_id_original) " +
			"where c.type='D'	" +
			"and c.is_deleted='F' " +
			"and c.name is not null";
			Query queryType = HibernateUtil.getSession().createSQLQuery(country_AND_partnerType_CATIDS);
			List<Object[]> catId_PartnerType_PartnerCountry=queryType.list();
			
			List<String> defaultCategories=new LinkedList<String>();//this will contain catids whose partnertype and country is null
			List<String> cat_id_partnerType= new LinkedList<String>();//this will contain catids whose partnertype is present
			List<String> cat_id_countries = new LinkedList<String>();//this will contain catids whose  country is present
			List<String> cat_id_partnerType_AND_countries = new LinkedList<String>();//this will contain catids whose partnertype and country are both present
			
			logger.debug("partner type list = "+siebel_PartnerTypeList);
			for(Object[] objArr:catId_PartnerType_PartnerCountry){
				//objArr[2] = category Country 
				//pbjArr[3] = category partner type
				if(objArr[1]==null && objArr[2]==null){
					defaultCategories.add(objArr[0].toString());
				}else{
					if(objArr[1]!=null && objArr[2]==null){
						// this means country is present parther type is not present
						//need to check if the country matches user's country
						if(siebel_CountryList.contains(objArr[1].toString())){
							cat_id_countries.add(objArr[0].toString());
						}
						
					}else if(objArr[1]==null && objArr[2]!=null){
						// this means country is not present parther type is  present
						//need to check if the country matches user's country
						logger.debug("siebel_PartnerTypeList.contains(objArr[2].toString())"+siebel_PartnerTypeList.contains(objArr[2].toString()));
						if(siebel_PartnerTypeList.contains(objArr[2].toString())){
							cat_id_partnerType.add(objArr[0].toString());
						}
					}else{
						//both value is present need to check for both
						if(siebel_PartnerTypeList.contains(objArr[2].toString()) && siebel_CountryList.contains(objArr[1].toString())){
							cat_id_partnerType_AND_countries.add(objArr[0].toString());
						}
					}
				}
				
			}
			
			
			
			
			logger.debug("default categories "+defaultCategories);
			logger.debug(" cat_id_partnerType "+ cat_id_partnerType);
			logger.debug(" cat_id_countries "+ cat_id_countries);
			logger.debug(" cat_id_partnerType_AND_countries "+ cat_id_partnerType_AND_countries);
			
			
			cat_ids_displayed.addAll(cat_id_partnerType);
			cat_ids_displayed.addAll(cat_id_countries);
			cat_ids_displayed.addAll(defaultCategories);
			cat_ids_displayed.addAll(cat_id_partnerType_AND_countries);
			
			}
		
			
			
			//if(cat_ids_displayed.size()>0){
				Query query = HibernateUtil.getSession().createSQLQuery(sql);
				query.setParameter("localeCode", language);
				query.setParameter("categoryType", "D");
				query.setParameter("definitionType", "DOC");
				query.setParameter("isDeleted", "F");
				query.setParameter("mdmLevel", contract.getMdmLevel());
				query.setParameter("mdmId", contract.getMdmId());
				//query.setParameter("categories", catagories);
				if(contract.getPartnerPortal()){
				query.setParameterList("categories", cat_ids_displayed);
				}
				Map<String, RoleCategory> categories = new HashMap<String, RoleCategory>();
				List<Object[]> list = query.list();
				for (Object[] row : list) {
					String catename = (String) row[0];
					String categroyId = (String) row[1];
					String defname = (String) row[2];
					String defid = (String) row[3];
					RoleCategory category = categories.get(categroyId);
					if (category == null) {
						category = new RoleCategory();
						category.setDocumentList(new ArrayList<DocumentDefinition>());
						categories.put(categroyId, category);
						categoryList.add(category);
						category.setCategoryId(Integer.valueOf(categroyId));
						category.setName(catename);
					}

					DocumentDefinition definition = new DocumentDefinition();
					definition.setId(Integer.valueOf(defid));
					definition.setName(defname);
					category.getDocumentList().add(definition);
				}
			//}
		}catch(Exception e ){
			e.printStackTrace();
		}
		finally {
			HibernateUtil.closeSession();
		}
		return categoryHierarchyResult;
	}


	public DocumentUserListResult retrieveDocumentUserList(DocumentUserListContract contract) throws SQLException {
		DocumentUserListResult result = new DocumentUserListResult();
		List list = documentListService.listDocumentByDefinitionId(contract.getDefinitionId());
		result.setDocumentList(list);
		return result;
	}

	public DocumentDefinitionSaveResult saveDocumentDefinition(DocumentDefinitionSaveContract contract) {
		Session session = HibernateUtil.getSession();
		HibernateUtil.beginTransaction();
		session.saveOrUpdate(contract.getDefinition());
		HibernateUtil.commitTransaction();
		HibernateUtil.closeSession();
		return new DocumentDefinitionSaveResult();
	}
	//CI7 BRD 14-06-01
	public String retrieveMaxDisplayOrder(String categoryId) {
        String maxDisplayOrder="";
        try{
        
        logger.debug("categoryId fetched from form and sent to the Query for max order-->>"+categoryId);
        Query query = HibernateUtil.getSession().createSQLQuery(RETRIEVE_MAXDISPLAYORDER);
        query.setParameter("category_id", categoryId);
        List list= query.list();
        if(list.size()>0){
                maxDisplayOrder=list.get(0).toString();
        }
        logger.debug("maxDisplayOrder fetched from the query-->>"+maxDisplayOrder);
        }
        catch(HibernateException hbex){
                hbex.getMessage();
        }
        catch(Exception ex){
                ex.printStackTrace();
        }
        
        return maxDisplayOrder;
}
//end CI7 BRD 14-06-01

	public DocumentDeleteResult handleDocumentDelete(DocumentDeleteContract contract) {
//      just use the soft delete to mark a delete flag in the report_delete_status table		
/*		DocumentumWebServiceFacadeImpl target;
		target = new DocumentumWebServiceFacadeImpl(SERVICE_END_POINT, REPOSITORY_NAME, SUPERUSER_NAME, PASSWORD,
				APPLICATION_NAME);
		target.deleteDocument(contract.getObjectId());
*/
		markDocumentDeleteStatus(contract.getObjectId());
		DocumentDeleteResult documentDeleteResult = new DocumentDeleteResult();
		return documentDeleteResult;
	}
	
	
	private void markDocumentDeleteStatus(String objectId) {
		try{
			HibernateUtil.beginTransaction();
			Query query = HibernateUtil.getSession().createQuery("from ReportDeleteStatus where reportId = :objectId");
			query.setParameter("objectId", objectId);
			List list = query.list(); 
			if(list != null && list.size() > 0) {
				ReportDeleteStatus deleteStatus = (ReportDeleteStatus)list.get(0);
				deleteStatus.setIsDeleted(Boolean.TRUE);
				HibernateUtil.getSession().saveOrUpdate(deleteStatus);
			}
			else{
				ReportDeleteStatus deleteStatus = new ReportDeleteStatus();
				deleteStatus.setReportId(objectId);
				deleteStatus.setIsDeleted(Boolean.TRUE);
				HibernateUtil.getSession().save(deleteStatus);
			}
			HibernateUtil.commitTransaction();
		}
		catch(HibernateException e){
			if (logger.isDebugEnabled()) {
				logger.debug(e.getMessage());
			}
			throw e;
		}
		finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public DocumentDefinitionResult deleteDocumentDefinition(DocumentDefinitionDeleteContract contract) {
		Query query = HibernateUtil.getSession().createQuery("update DocumentDefinition set isDeleted=? where id=?");
		query.setBoolean(0, true);
		query.setInteger(1, contract.getDefinitionId());
		HibernateUtil.beginTransaction();
		query.executeUpdate();
		HibernateUtil.commitTransaction();
		HibernateUtil.closeSession();
		return new DocumentDefinitionResult();
	}
	
	//added for CI BRD 14-07-04
		public Boolean savePartnerType(List<String> partnerTypeList, String categoryId) {
			logger.debug("in save partner type ");
			try{
			Session session = HibernateUtil.getSession();
			Query query_delete=null;
			
			HibernateUtil.beginTransaction();
			for(int i=0;i<partnerTypeList.size();i++){
			int rowsDeleted=0;
			query_delete = session.createQuery("delete from Category_PartnerType where category_id_original= :cat_id" );
			rowsDeleted = query_delete.setParameter("cat_id", categoryId).executeUpdate();
			logger.info("No of rows deleted "+rowsDeleted);
			}
			if(partnerTypeList.size()==0){
				logger.debug("inside partnerTypeList size 0");
				int rowsDeleted=0;
				query_delete = session.createQuery("delete from Category_PartnerType where category_id_original= :cat_id" );
				rowsDeleted = query_delete.setParameter("cat_id", categoryId).executeUpdate();
				logger.info("No of rows deleted "+rowsDeleted);
			}
			session.clear();
			
			Iterator it= partnerTypeList.iterator();
			while(it.hasNext()){
				logger.debug("partner Types inside IMPL----------->>"+it.next().toString());
			}
			
			if(partnerTypeList.size()>0){
				
				for(int i=0;i<partnerTypeList.size();i++){
					logger.debug("partner type size inside for----->>"+partnerTypeList.size());
					Category_PartnerType partnerType = new Category_PartnerType();
					 partnerType.setCategory_id_original(categoryId);
					 logger.debug("partner type being set in DB----->>"+partnerTypeList.get(i));
					 partnerType.setPartnerType(partnerTypeList.get(i));
					 session.save(partnerType);
					
				}
			}
			
		
			HibernateUtil.commitTransaction();
			HibernateUtil.closeSession();
			return true;
			}
			
			catch(Exception ex){
				ex.printStackTrace();
				return false;
			}
			
		}
		
	public Boolean saveCountry(List<String> countryListArray, String categoryId) {
			
			try{
			Session session = HibernateUtil.getSession();
			Query query_delete=null;		
			HibernateUtil.beginTransaction();
			for(int i=0;i<countryListArray.size();i++){
				logger.info("---------------DELETE COUNTRY LIST ");
				int rowsDeleted=0;
			query_delete = session.createQuery("delete from Category_Country WHERE category_id_original= :cat_id" );
			rowsDeleted = query_delete.setParameter("cat_id", categoryId).executeUpdate();
			logger.info("No of rows deleted "+rowsDeleted);
			}
			if(countryListArray.size()==0){
				logger.info("---------------DELETE COUNTRY LIST ");
				int rowsDeleted=0;
			query_delete = session.createQuery("delete from Category_Country WHERE category_id_original= :cat_id" );
			rowsDeleted = query_delete.setParameter("cat_id", categoryId).executeUpdate();
			logger.info("No of rows deleted "+rowsDeleted);
			}
			session.clear();
			Session sessionInsertUpdate = HibernateUtil.getSession();
			if(countryListArray.size()>0){
				for(int i=0;i<countryListArray.size();i++){
					logger.debug("in countryListArray for insert into db---"+countryListArray.size());
					 Category_Country country = new Category_Country();
					 country.setCategory_id_original(categoryId);
					 country.setCountry(countryListArray.get(i));
					 sessionInsertUpdate.save(country);
				}
			}
			
		
			HibernateUtil.commitTransaction();
			HibernateUtil.closeSession();
			return true;
			}
			
			catch(Exception ex){
				ex.printStackTrace();
				return false;
			}
		}

public List<String> getDB_CountryList(String categoryId){
	logger.debug("in getDB_CountryList");
	String db_CountryQuery = "select COUNTRY FROM Category_Country WHERE CATEGORY_ID_ORIGINAL = :categoryId";
	Query queryCountries = HibernateUtil.getSession().createSQLQuery(db_CountryQuery);
	queryCountries.setParameter("categoryId", categoryId);
	logger.debug("in getDB_CountryList after Query");
	List<Object> countryList = queryCountries.list();
	List<String> countryToBeDisplayed = new ArrayList<String>();
	for(int i =0;i<countryList.size();i++){
		logger.debug("in getDB_CountryList after Query"+countryList.get(i).toString());
		countryToBeDisplayed.add(countryList.get(i).toString());
		}
	return countryToBeDisplayed;
	}

public List<String> getDB_PartnerTypeList(String categoryId){
	logger.debug("in getDB_PartnerTypeList");
	String db_PartnerTypeQuery = "select PARTNER_TYPE FROM CATEGORY_PARTNERTYPE WHERE CATEGORY_ID_ORIGINAL = :categoryId";
	Query queryPartnerType = HibernateUtil.getSession().createSQLQuery(db_PartnerTypeQuery);
	queryPartnerType.setParameter("categoryId", categoryId);
	logger.debug("in getDB_PartnerTypeList after Query");
	List<Object> partnerTypeList = queryPartnerType.list();
	List<String> partnerTypesToBeDisplayed = new ArrayList<String>();
	for(int i =0;i<partnerTypeList.size();i++){
		logger.debug("in getDB_CountryList after Query"+partnerTypeList.get(i).toString());
		partnerTypesToBeDisplayed.add(partnerTypeList.get(i).toString());
		}
	return partnerTypesToBeDisplayed;
	}

	public static void main(String[] args) {

	}
}
