package com.lexmark.service.impl.real.util;

import static com.lexmark.service.impl.real.util.AmindPartnerDataManagerUtil.setPartnerFlags;
import static com.lexmark.service.impl.real.util.AmindPartnerDataManagerUtil.setPartnerTypes;
import static com.lexmark.service.impl.real.util.AmindServiceUtil.GLOBAL_MDMLEVEL;
import static com.lexmark.service.impl.real.util.AmindServiceUtil.LEGAL_MDMLEVEL;
import static com.lexmark.util.LangUtil.isEmpty;
import static com.lexmark.util.LangUtil.isNotEmpty;
import static com.lexmark.util.LangUtil.notNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.logging.log4j.LogManager;

import com.amind.data.service.IDataManager;
import com.lexmark.domain.Account;
import com.lexmark.domain.GlobalAccount;
import com.lexmark.domain.PartnerAgreement;
import com.lexmark.service.impl.real.domain.GlobalLegalEntityDO;
import com.lexmark.service.impl.real.domain.ListOfValues;
import com.lexmark.service.impl.real.domain.PartnerAgreementDo;
import com.lexmark.service.impl.real.domain.PartnerDirectAccountDo;
import org.apache.logging.log4j.Logger;

public class AmindGlobalConversionUtil {

	//private static final Log logger = LogFactory.getLog(AmindGlobalConversionUtil.class);
	private static final Logger logger = LogManager.getLogger(AmindGlobalConversionUtil.class);
	
	public static List<PartnerAgreement> convertPartnerAgreementDoList(List<PartnerAgreementDo> agreementList) {
		List<PartnerAgreement> portalAgreementList = new ArrayList<PartnerAgreement>();
		if (agreementList == null) {
			return portalAgreementList;
		}
		for (PartnerAgreementDo agreement : agreementList) {
			PartnerAgreement portalAgreement = new PartnerAgreement();
			portalAgreement.setPartnerAgreementId(agreement.getId());
			portalAgreement.setPartnerAgreementName(agreement.getName());
			portalAgreementList.add(portalAgreement);
		}
		return portalAgreementList;
	}
	
	public static GlobalAccount convertGlobalLegalEntityToGlobalAccount(GlobalLegalEntityDO entity,
			String mdmId, IDataManager dataManager) {
		GlobalAccount account = new GlobalAccount();

		String mdmLevel1Id = entity.getMdmLevel1AccountId();
		String mdmLevel3Id = entity.getMdmLevel3AccountId();
		String accountLevel = entity.getAccountLevel();
		String name = entity.getName();

		if (GLOBAL_MDMLEVEL.equals(accountLevel)) {
			account.setMdmId(mdmLevel1Id);
			account.setMdmLevel(accountLevel);
			account.setLegalName(name);
			account.setDisplayMdmId(mdmLevel3Id);
		} else if (LEGAL_MDMLEVEL.equals(accountLevel) && isEmpty(mdmLevel1Id)) {
			// Use name from account
			account.setMdmId(mdmLevel3Id);
			account.setMdmLevel(accountLevel);
			account.setLegalName(name);
			account.setDisplayMdmId(mdmLevel3Id);
		} else {

			String searchExpression = buildGlobalLegalEntitySearchExpression(mdmLevel1Id, mdmLevel3Id);

			if (isNotEmpty(searchExpression)) {
				List<GlobalLegalEntityDO> globalList = queryGlobalLegalEntity(searchExpression, mdmId,
						dataManager);

				if (isNotEmpty(globalList)) {
					// Found the global entity from which to get the name
					GlobalLegalEntityDO globalEntity = globalList.get(0);
					account.setDisplayMdmId(globalEntity.getMdmLevel3AccountId());
					if (GLOBAL_MDMLEVEL.equals(globalEntity.getAccountLevel())) {
						account.setMdmId(globalEntity.getMdmLevel1AccountId());
					} else {
						account.setMdmId(globalEntity.getMdmLevel3AccountId());
					}
					account.setMdmLevel(globalEntity.getAccountLevel());
					account.setLegalName(globalEntity.getName());
				}
			}
		}

		return account;
	}
	
	public static List<GlobalAccount> convertEntityDOtoGlobalAccount(List<GlobalLegalEntityDO> globalList) {
		List<GlobalAccount> entityList = new ArrayList<GlobalAccount>();
		
		logger.debug("Inside convertEntityDOtoGlobalAccount :::::");
		if (isEmpty(globalList)) {
			logger.debug("Inside convertEntityDOtoGlobalAccount Empty :::::");

			return entityList;
		}

		for (GlobalLegalEntityDO entity : globalList) {
			GlobalAccount account = new GlobalAccount();
			if (GLOBAL_MDMLEVEL.equals(entity.getAccountLevel())) {
				account.setMdmId(entity.getMdmLevel1AccountId());
				account.setDisplayMdmId(entity.getMdmLevel1AccountId());
								
			} else if (LEGAL_MDMLEVEL.equals(entity.getAccountLevel())) {
				account.setMdmId(entity.getMdmLevel3AccountId());
				account.setDisplayMdmId(entity.getMdmLevel3AccountId());

			}else{
				account.setDisplayMdmId(entity.getMdmLevel3AccountId());
			
			}
			//account.setDisplayMdmId(entity.getMdmLevel3AccountId());
			account.setLegalName(entity.getName());
			account.setMdmLevel(entity.getAccountLevel());
			entityList.add(account);
		}
		logger.debug("Inside convertEntityDOtoGlobalAccount ::::: ");

		return entityList;
	}
	
	public static List<com.lexmark.domain.ListOfValues> convertLOVDoToLOV(List<ListOfValues> lovDoList) {
		List<com.lexmark.domain.ListOfValues> list = new ArrayList<com.lexmark.domain.ListOfValues>();
		
		if (lovDoList == null) {
			return list;
		}
		
		Set<ListOfValues> lofSet = new HashSet<ListOfValues>(lovDoList);
		lovDoList = new ArrayList<ListOfValues>(lofSet);

		for (ListOfValues lovDo : lovDoList) {
			com.lexmark.domain.ListOfValues lov = new com.lexmark.domain.ListOfValues();
			lov.setId(lovDo.getId());
			lov.setType(lovDo.getType());
			lov.setValue(lovDo.getValue());
			lov.setLanguage(lovDo.getLanguage());
			lov.setLanguageName(lovDo.getLanguageName());
			lov.setName(lovDo.getName());
			lov.setDescription(lovDo.getDescription());
			list.add(lov);
		}
		
		return list;
	}
	
	public static List<Account> convertPartnerDirectAccountDoListToAccountList(
			List<PartnerDirectAccountDo> partnerAccountDoList) {
		
		List<Account> portalAccountList = new ArrayList<Account>();
		
		if (partnerAccountDoList == null) {
			return portalAccountList;
		}
		
		for (PartnerDirectAccountDo partnerDirectAccount : partnerAccountDoList) {

			Account portalAccount = new Account();
			portalAccount.setAccountId(partnerDirectAccount.getAccountId());
			portalAccount.setAccountName(partnerDirectAccount.getAccountName());
			portalAccount.setOrganizationID(partnerDirectAccount.getOrganizationId());

			if (partnerDirectAccount.getFlags() != null) {
				portalAccount = setPartnerFlags(partnerDirectAccount.getFlags(), portalAccount);
			}

			if (partnerDirectAccount.getAccountTypes() != null) {
				portalAccount = setPartnerTypes(partnerDirectAccount.getAccountTypes(), portalAccount);
			}

			portalAccountList.add(portalAccount);
		}

		return portalAccountList;
	}
	
	private static String buildGlobalLegalEntitySearchExpression(String mdmLevel1Id, String mdmLevel3Id) {
		// Query for Global/legal account to get name
		StringBuilder builder = new StringBuilder();

		if (isNotEmpty(mdmLevel1Id)) {
			builder.append("[mdmLevel1AccountId]='");
			builder.append(mdmLevel1Id);
			builder.append("' AND [accountLevel] = '");
			builder.append(GLOBAL_MDMLEVEL);
			builder.append("'");
		} else if (isNotEmpty(mdmLevel3Id)) {
			builder.append("[mdmLevel3AccountId]='");
			builder.append(mdmLevel3Id);
			builder.append("' AND [accountLevel] = '");
			builder.append(LEGAL_MDMLEVEL);
			builder.append("'");
		} else {
			logger.warn("Acount does not have a non-empty Global or Legal ID");
		}
		
		return builder.toString();
	}
	
	@SuppressWarnings("unchecked")
	private static List<GlobalLegalEntityDO> queryGlobalLegalEntity(String searchExpression, String mdmId, IDataManager dataManager) {
		List<GlobalLegalEntityDO> globalList = dataManager.queryBySearchExpression(GlobalLegalEntityDO.class,
				searchExpression);
		return notNull(globalList);
	}

	private AmindGlobalConversionUtil() {
	}
}
