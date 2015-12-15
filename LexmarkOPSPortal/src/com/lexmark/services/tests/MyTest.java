package com.lexmark.services.tests;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.lexmark.contract.CategoryDeleteContract;
import com.lexmark.contract.CategoryDetailContract;
import com.lexmark.contract.CategorySaveContract;
import com.lexmark.domain.Document;
import com.lexmark.domain.Role;
import com.lexmark.domain.RoleCategory;
import com.lexmark.domain.RoleCategoryLocale;
import com.lexmark.domain.SupportedLocale;
import com.lexmark.result.CategoryDetailResult;
import com.lexmark.service.impl.mock.DomainMockDataGenerator;
import com.lexmark.service.impl.real.jdbc.DocumentManagementServiceImpl;
import com.lexmark.service.impl.real.jdbc.HibernateUtil;
import com.lexmark.services.util.ContractFactory;

public class MyTest {
	public static void main1(String[] args) {
		String[][] localesStrs = {  { "Italian", "it" },
				{ "Portuguese", "pt" }, { "Traditional Chinese", "zh_TW" },
				{ "Simplified Chinese", "zh_CN" }, };
		HibernateUtil.beginTransaction();
		SupportedLocale[] locales = new SupportedLocale[localesStrs.length];
		int localeLen = locales.length;
		for (int i = 0; i < localeLen; i++) {
			locales[i] = new SupportedLocale();
			SupportedLocale locale = locales[i];
			locale.setSupportedLocaleCode(localesStrs[i][1]);
			locale.setSupportedLocaleName(localesStrs[i][0]);
			
			HibernateUtil.getSession().save(locale);
		}
		HibernateUtil.commitTransaction();
		HibernateUtil.closeSession();
	}
	
	public static void main(String[] args) {
		List<Document> docs = DomainMockDataGenerator.generateDocumentList();
		int i=0;
		for (Document document : docs) {
			HibernateUtil.getSession().save(document);
			if (i++ % 197 == 0) {
				HibernateUtil.commitTransaction();
				HibernateUtil.beginTransaction();
			}
		}
//		HibernateUtil.getSession().save(item);
		HibernateUtil.commitTransaction();
		HibernateUtil.closeSession();
	}

	public static void main4(String[] args) {
		DocumentManagementServiceImpl documentManagementService = new DocumentManagementServiceImpl();
		CategoryDetailContract contract = ContractFactory.getCategoryDetailContract(1);
		CategoryDetailResult result = documentManagementService.retrieveCategoryDetail(contract);
		RoleCategory category = result.getCategory();
		List<Role> roleList = new ArrayList<Role>();
		for (int i = 0; i < 4; i++) {
			Role role = new Role();
			role.setId(i + 1);
			roleList.add(role);
		}
		category.setRoles(roleList);
		CategorySaveContract contract1 = ContractFactory.getCategorySaveContract(category);
		documentManagementService.saveCategory(contract1);
	}
	public static void main2(String[] args) throws Exception {
		DocumentManagementServiceImpl documentManagementService = new DocumentManagementServiceImpl();
		CategoryDeleteContract contract = ContractFactory.getCategoryDeleteContract(1);
		documentManagementService.deleteCategory(contract);
	}
	public static void main3(String[] args) {
		for (int i = 1; i <= 10; i++) {
			HibernateUtil.beginTransaction();
			Session session = HibernateUtil.getSession();
			RoleCategory roleCategory = (RoleCategory) session.load(RoleCategory.class, i);

			List<RoleCategoryLocale> localeList = new ArrayList<RoleCategoryLocale>();
			SupportedLocale[] supportedLocales = generateSupportedLocales();
			for (SupportedLocale supportedLocale : supportedLocales) {
				RoleCategoryLocale categoryLocale = new RoleCategoryLocale();
				categoryLocale.setName(roleCategory.getName() + " - " + supportedLocale.getSupportedLocaleCode());
				categoryLocale.setSupportedLocale(supportedLocale);
				localeList.add(categoryLocale);
				session.saveOrUpdate(categoryLocale);
			}
			roleCategory.setLocaleList(localeList);

			session.saveOrUpdate(roleCategory);
			HibernateUtil.commitTransaction();
			session.flush();
			HibernateUtil.closeSession();
		}
	}

	private static SupportedLocale[] generateSupportedLocales() {
		Query query = HibernateUtil.getSession().createQuery("from SupportedLocale");
		List list = query.list();
		SupportedLocale[] locales = new SupportedLocale[list.size()];
		int localeLen  = locales.length;
		for (int i = 0; i < localeLen; i++) {
			locales[i] = (SupportedLocale) list.get(i);
		}
		// HibernateUtil.closeSession();

		return locales;
	}
}
