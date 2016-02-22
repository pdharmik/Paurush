package com.lexmark.service.impl.real.jdbc;

import java.io.File;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.lexmark.contract.CheckedEntitlementServiceDetailContract;
import com.lexmark.contract.LocalizedEntitlementServiceDetailContract;
import com.lexmark.contract.LocalizedEntitlementServiceListContract;
import com.lexmark.contract.LocalizedExchangeOptionListContract;
import com.lexmark.contract.LocalizedPageCountNameContract;
import com.lexmark.contract.LocalizedServiceActivityStatusContract;
import com.lexmark.contract.LocalizedServiceStatusContract;
import com.lexmark.contract.LocalizedSiebelLOVListContract;
import com.lexmark.contract.LocalizedSiebelValueContract;
import com.lexmark.contract.SRAdministrationDetailContract;
import com.lexmark.contract.SRAdministrationListContract;
import com.lexmark.contract.SRAdministrationSaveContract;
import com.lexmark.contract.SRLocalizationContract;
import com.lexmark.domain.Document;
import com.lexmark.domain.DocumentDefinition;
import com.lexmark.domain.EntitlementServiceDetail;
import com.lexmark.domain.ListOfValues;
import com.lexmark.domain.RoleCategory;
import com.lexmark.domain.SiebelLocalization;
import com.lexmark.domain.SiebelLocalizationLocale;
import com.lexmark.domain.SupportedLocale;
import com.lexmark.domain.SiebelLocalization.SiebelLocalizationOptionEnum;
import com.lexmark.enums.PartnerTypeEnum;
import com.lexmark.result.CheckedEntitlementServiceDetailResult;
import com.lexmark.result.LocalizedEntitlementServiceDetailResult;
import com.lexmark.result.LocalizedEntitlementServiceListResult;
import com.lexmark.result.LocalizedExchangeOptionListResult;
import com.lexmark.result.LocalizedPageCountNameResult;
import com.lexmark.result.LocalizedServiceActivityStatusResult;
import com.lexmark.result.LocalizedServiceStatusResult;
import com.lexmark.result.LocalizedSiebelLOVListResult;
import com.lexmark.result.LocalizedSiebelValueResult;
import com.lexmark.result.SRAdministrationDetailResult;
import com.lexmark.result.SRAdministrationListResult;
import com.lexmark.result.SRAdministrationSaveResult;
import com.lexmark.result.SRLocalizationResult;
import com.lexmark.result.SupportedLocaleListResult;
import com.lexmark.service.api.ServiceRequestLocale;
import com.lexmark.util.LocaleUtil;

public class ServiceRequestLocaleImpl implements ServiceRequestLocale {
	private static final  Logger logger  = LogManager.getLogger(ServiceRequestLocaleImpl.class);
	private static final String DEFAULT_LOCALE_CODE = "en";
	private static final String QUERY_LOCALIZED_SIEBEL_VALUE = 
		"SELECT sl.display_Value as displayValue FROM siebel_localization s,  siebel_localization_locale  sl, supported_locale l " +
		" where s.siebel_localization_id = sl.siebel_localization_id and sl.supported_locale_id = l.supported_locale_id " + 
		" and l.supported_locale_code = :local_code and s.siebel_Value = :siebel_value and s.option_type = ''{0}''";
	
	private static final String QUERY_CHECKED_ENTITLEMENT_SERVICE_LIST =
		"SELECT sl.display_Value as displayValue, s.SIEBEL_LOCALIZATION_ID as entitlementId, s.siebel_Value as siebelValue FROM siebel_localization s,  siebel_localization_locale  sl, supported_locale l " +
		" where s.siebel_localization_id = sl.siebel_localization_id and sl.supported_locale_id = l.supported_locale_id " + 
		" and l.supported_locale_code = :local_code and s.SHOW_ENTITLEMENT_FLAG = :show_entitlement_flag and s.option_type = ''{0}''";
	
	private static final String QUERY_EXCHANGE_OPTION_LIST = 
		"SELECT sl.display_Value as displayValue FROM siebel_localization s,  siebel_localization_locale  sl, supported_locale l " +
		" where s.siebel_localization_id = sl.siebel_localization_id and sl.supported_locale_id = l.supported_locale_id " + 
		" and l.supported_locale_code = :local_code and s.option_type = ''{0}''"; 
	
	private static final String QUERY_ENTITLEMENT_SERVICE_LIST_QUERY = 
		"SELECT sl.display_Value  as displayValue,  s.siebel_Value as siebelValue FROM siebel_localization s,  siebel_localization_locale  sl, supported_locale l " +
		" where s.siebel_localization_id = sl.siebel_localization_id and sl.supported_locale_id = l.supported_locale_id " + 
		" and l.supported_locale_code = :local_code and s.siebel_Value in ( {1} ) and s.option_type = ''{0}'' order by s.siebel_Value";

	private static final String SQL_GET_SIEBEL_LOCALIZATION =
		"SELECT sl.display_Value as displayValue FROM siebel_localization s,  siebel_localization_locale  sl, supported_locale l " +
		" where s.siebel_localization_id = sl.siebel_localization_id and sl.supported_locale_id = l.supported_locale_id " + 
		" and l.supported_locale_code = :local_code and s.siebel_Value = :siebel_value and s.option_type = :option_type";
	
	private static final String SQL_GET_LOCALIZED_LOV =
		"SELECT sl.display_Value as displayValue, s.siebel_value as value FROM siebel_localization s,  siebel_localization_locale  sl, supported_locale l " +
		" where s.siebel_localization_id = sl.siebel_localization_id and sl.supported_locale_id = l.supported_locale_id " + 
		" and l.supported_locale_code = :local_code and s.option_type = :option_type";
	
	private static final String SQL_GET_LOCALIZED_LOV_SPECIFIC_PARTNER_TYPE =
		"SELECT sl.display_Value as displayValue, s.siebel_value as value FROM siebel_localization s,  siebel_localization_locale  sl, supported_locale l " +
		" where s.siebel_localization_id = sl.siebel_localization_id and sl.supported_locale_id = l.supported_locale_id " + 
		" and l.supported_locale_code = :local_code and s.option_type = :option_type and s.partner_type in (:partner_type_list)";
	
	
	public SRLocalizationResult deleteSRLocalization(
			SRLocalizationContract contract) {
		Integer  siebelLocalizationId = contract.getSiebelLocalizationId();
		SRLocalizationResult result = new SRLocalizationResult();
		try {			
			HibernateUtil.beginTransaction();			
			SiebelLocalization  siebelLocalization = (SiebelLocalization) HibernateUtil.getSession().load(SiebelLocalization.class, siebelLocalizationId);
			HibernateUtil.getSession().delete(siebelLocalization);
			HibernateUtil.commitTransaction();
			HibernateUtil.getSession().flush();
			HibernateUtil.closeSession();
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}

		return result;
	}
	
	private void replaceSiebelValueWithDisplayValue(List<EntitlementServiceDetail> resultList, String siebelValue, String displayValue) {		
		for(EntitlementServiceDetail entitlement: resultList) {
			if(entitlement.getSiebelValue() == null || entitlement.getSiebelValue().equals("")){
				if(entitlement.getServiceDetailDescription() != null) {
					if(entitlement.getServiceDetailDescription().equals(siebelValue)){
						if (displayValue == null || displayValue.equals("")){
							preserveSiebelValue(entitlement, entitlement.getServiceDetailDescription());							
						}else{
							entitlement.setServiceDetailDescription(displayValue);
							preserveSiebelValue(entitlement, siebelValue);							
						}
					}
				}				
			}
			
		}
	}
	
	private void populateQueryListValue(List list, LocalizedEntitlementServiceListResult result) {
		for(int i=0; i< list.size(); i++) {
			Object[] row = (Object[]) list.get(i);
			replaceSiebelValueWithDisplayValue(result.getEntitlementServiceDetails(), (String) row[1], (String) row[0]);
		}
		for(EntitlementServiceDetail entitlement: result.getEntitlementServiceDetails()) {
			if(entitlement.getSiebelValue() == null || entitlement.getSiebelValue().equals("")){
				preserveSiebelValue(entitlement, entitlement.getServiceDetailDescription());
			}
		}
	}

	private void preserveSiebelValue(EntitlementServiceDetail entitlement, String siebelValue){
		entitlement.setSiebelValue(siebelValue);	
	}
	
	/*
	 * Retrieves localized entitlement services for given entitlement services under given locale.
	 * It can be invoked only when asset contains entiltement service.
	 */
	public LocalizedEntitlementServiceListResult retrieveLocalizedEntitlementServiceList(
			LocalizedEntitlementServiceListContract contract) {
		LocalizedEntitlementServiceListResult result = new LocalizedEntitlementServiceListResult();
		// fix the issue of empty Entitlement service detail list from Siebel.
		if(contract.getEntitlementServiceDetails() == null ||
				contract.getEntitlementServiceDetails().size() == 0) {
			return result;
		}
		String language = LocaleUtil.getSupportLocaleCode(contract.getLocale());
		List<String>  siebelValueList = new ArrayList<String>();
		
		StringBuilder sb = new StringBuilder();
		int i=0;
		for(EntitlementServiceDetail entitlement: contract.getEntitlementServiceDetails()) {
			siebelValueList.add(entitlement.getServiceDetailDescription());
			result.getEntitlementServiceDetails().add(entitlement);
			if(i!=0) {
				sb.append(", ");
			}
			sb.append("'" + entitlement.getServiceDetailDescription() + "'");
			i++;
		}
		MessageFormat mf = new MessageFormat(QUERY_ENTITLEMENT_SERVICE_LIST_QUERY);
		String sql = mf.format(
				new Object[]{
						SiebelLocalization.SiebelLocalizationOptionEnum.ENTITLEMENT_SERVICE_DETAILS.getValue()
						,sb.toString()});
			try {			
				Query query = HibernateUtil.getSession().createSQLQuery(sql);
				query.setParameter("local_code", language);
				List list = query.list();
				if(list != null && list.size() > 0) {
					populateQueryListValue(list, result);
				} else {
					query = HibernateUtil.getSession().createSQLQuery(sql);
					query.setParameter("local_code", DEFAULT_LOCALE_CODE);
					list = query.list();
					if(list != null && list.size() > 0) {
						populateQueryListValue(list, result);
					}
				}
				HibernateUtil.closeSession();
			} catch (HibernateException ex) {
				throw new InfrastructureException(ex);
			}
			return result;
	}


	public LocalizedServiceActivityStatusResult retrieveLocalizedServiceActivityStatus(
			LocalizedServiceActivityStatusContract contract) {
		LocalizedServiceActivityStatusResult result = new LocalizedServiceActivityStatusResult();
		result.setLocalizedValue(retrieveLocalizedSiebelValue(
				contract.getSiebelValue(), contract.getLocale(),
				SiebelLocalization.SiebelLocalizationOptionEnum.SERVICE_ACTIVITY_STATUS_DESCRIPTION.getValue()));
		return result;
	}

	/*
	 * Retrieves localized default entitlement services under given locale.
	 * It can be invoked only when asset does not contain entiltement service (data issue),
	 * or a new asset is being regrstered.
	 */
	public CheckedEntitlementServiceDetailResult retrieveCheckedEntitlementServiceDetail(
			CheckedEntitlementServiceDetailContract contract) {
	String language = LocaleUtil.getSupportLocaleCode(contract.getLocale());
	
	MessageFormat mf = new MessageFormat(QUERY_CHECKED_ENTITLEMENT_SERVICE_LIST);
	String sql = mf.format(
			new Object[]{
					SiebelLocalization.SiebelLocalizationOptionEnum.ENTITLEMENT_SERVICE_DETAILS.getValue()
					});
	CheckedEntitlementServiceDetailResult result = new CheckedEntitlementServiceDetailResult();
		try {			
			Query query = HibernateUtil.getSession().createSQLQuery(sql);
			query.setParameter("local_code", language);
			query.setParameter("show_entitlement_flag" , 1);
			List list = query.list();
			if(list != null && list.size() > 0) {
				List<EntitlementServiceDetail> esl = new ArrayList<EntitlementServiceDetail>(0);
				for(Object o: list){
					EntitlementServiceDetail esd = new EntitlementServiceDetail();
					Object[] row = (Object[]) o;
					esd.setServiceDetailId(row[1].toString());
					esd.setServiceDetailDescription(row[0].toString());
					esd.setSiebelValue(row[2].toString());
					esl.add(esd);
				}
				result.setEntitlementServiceDetails(esl);
			} 
			HibernateUtil.closeSession();
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}
		return result;
	}

	public LocalizedServiceStatusResult retrieveLocalizedServiceStatus(
			LocalizedServiceStatusContract contract) {
		LocalizedServiceStatusResult result = new LocalizedServiceStatusResult();
		result.setLocalizedString(retrieveLocalizedSiebelValue(
				contract.getSiebelValue(), contract.getLocale(),
				SiebelLocalization.SiebelLocalizationOptionEnum.SERVICE_STATUS.getValue()));
		return result;
	}

	@Override
	public LocalizedEntitlementServiceDetailResult retrieveLocalizedEntitlementServiceDetail(
			LocalizedEntitlementServiceDetailContract contract) {
		LocalizedEntitlementServiceDetailResult result = new LocalizedEntitlementServiceDetailResult();
		result.setLocalizedString(retrieveLocalizedSiebelValue(
				contract.getSiebelValue(), contract.getLocale(),
				SiebelLocalization.SiebelLocalizationOptionEnum.ENTITLEMENT_SERVICE_DETAILS.getValue()));
		return result;
	}

	public SRAdministrationDetailResult retrieveSRAdministrationDetail(
			SRAdministrationDetailContract contract) {
		SRAdministrationDetailResult result = new SRAdministrationDetailResult();
		try {			
			SiebelLocalization  sl = (SiebelLocalization) HibernateUtil.getSession().get(SiebelLocalization.class, contract.getSiebelLocalizationId());
			HibernateUtil.closeSession();
			result.setSiebelLocalization(sl);
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}
		return result;
	}

	public SRAdministrationListResult retrieveSRAdministrationList(
			SRAdministrationListContract contract) {
		SRAdministrationListResult result = new SRAdministrationListResult();
		try {
			StringBuffer queryString=new StringBuffer("select sl from SiebelLocalization sl");
			if(!contract.isRetrieveAll()){
				queryString.append(" where sl.statusOrder is not null");
			}
			Query query = HibernateUtil.getSession().createQuery(queryString.toString());
			//logger.debug(query);
			for(Iterator it=query.iterate();it.hasNext();){
				SiebelLocalization sl = (SiebelLocalization) it.next();
				result.getSiebelLocalizations().add(sl);
			}
			HibernateUtil.closeSession();
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}
		result.setTotalCount(result.getSiebelLocalizations().size());
		return result;
	}
	
	/* author:sbag
	 * added as a part of the CI 13.10 release
	 * BRD #13-10-11 add filter in the locale settings page under administration tab
	 */
	
	public SRAdministrationListResult retrieveSRAdministrationListFilter(
			SRAdministrationListContract contract,String filterCriterias) {
		SRAdministrationListResult result = new SRAdministrationListResult();
		
		try {
			boolean flag=false;
			boolean flag2=false;
			String queryParam = filterCriterias;
			if(queryParam.contains("partnerType")){
				flag=true;
			}
			
			logger.debug("The query param is-->>>"+queryParam);
			String siebelQuerySplit[] = queryParam.split("\\|");
			String extra="";
			if(queryParam.contains(">>")){				
				if(!queryParam.contains("statusOrder") && queryParam.contains("partnerType")){
					
					extra=queryParam.substring(queryParam.lastIndexOf(">>"));
				}else{
					if(queryParam.contains("partnerType")){
						extra=queryParam.substring(queryParam.indexOf("partnerType"),queryParam.lastIndexOf(">>")+2);	
					}					
				}				
			}else{
				if(queryParam.contains("partnerType")){
					extra=queryParam.substring(queryParam.indexOf("partnerType"));	
				}
			}
			logger.debug("extra"+extra);
			queryParam=queryParam.replace(extra, "");
			logger.debug("queryParam"+queryParam);
			String constantPattern = queryParam.replace(":", ") like UPPER(");
			String siebelQuery = "select sl from SiebelLocalization sl  where UPPER(sl.";
			if(!filterCriterias.contains(">>")){
				if(!filterCriterias.contains("partnerType")){
				siebelQuery = siebelQuery+constantPattern+")";
				}else{
					siebelQuery="select sl from SiebelLocalization sl where"+" ";
					flag2=true;
				}
			}
			else
			{
				String constantPatternMultiple = queryParam.replace(":", ") like UPPER(").replace(">>",") and UPPER(sl.");
				
				siebelQuery = siebelQuery+constantPatternMultiple+")";
			}
			
			
			if((siebelQuery.contains("partnerType is null") && siebelQuery.contains("and")) || siebelQuery.contains("partnerType is null"))
			{
				logger.debug("Forming partner query");
				int lastIndex=siebelQuery.lastIndexOf("UPPER");
				int upperLength = lastIndex+"UPPER".length();			
				siebelQuery=siebelQuery.substring(0,lastIndex)+siebelQuery.substring(upperLength,siebelQuery.length());
			}
			logger.debug("The final Query String is-->> "+siebelQuery);
			/*if(siebelQuery.contains("%DIRECT%"))
			{
				siebelQuery = siebelQuery.replaceAll("('%DIRECT%')", "('DIRECT')");
				logger.debug("IF BLOCK The final Query String DIRECT/INDIRECT is-->> "+siebelQuery);
			}
			if(siebelQuery.contains("%INDIRECT%"))
			{
				siebelQuery = siebelQuery.replaceAll("('%INDIRECT%')", "('INDIRECT')");
				logger.debug("ELSE BLOCK The final Query String DIRECT/INDIRECT is-->> "+siebelQuery);
			}
			if(siebelQuery.contains("%BOTH_INDIRECT%"))
			{
				siebelQuery = siebelQuery.replace("('%BOTH_INDIRECT%')", "('INDIRECT') or sl.partnerType = 'BOTH'");
				logger.debug("ELSE BLOCK The final Query String DIRECT/INDIRECT is-->> "+siebelQuery);
			}
			if(siebelQuery.contains("%BOTH_DIRECT%"))
			{
				siebelQuery = siebelQuery.replace("('%BOTH_DIRECT%')", "('DIRECT') or sl.partnerType = 'BOTH'");
				logger.debug("ELSE BLOCK The final Query String DIRECT/INDIRECT is-->> "+siebelQuery);
			}
			if(siebelQuery.contains("%DIRECT_BOTH_INDIRECT%"))
			{
				siebelQuery = siebelQuery.replace("('%DIRECT_BOTH_INDIRECT%')", "('BOTH')");
				logger.debug("ELSE BLOCK The final Query String DIRECT/INDIRECT is-->> "+siebelQuery);
			}
			*/
			
			
			if(siebelQuery.contains("like UPPER('%null%')"))
			{
				logger.debug("Inside IF");
				siebelQuery=siebelQuery.replace("like UPPER('%null%')","is null");
				logger.debug("Query String is abcdefghi --->>>"+siebelQuery);
			}
			
			if((siebelQuery.contains("UPPER(sl.statusOrder) like UPPER('%N%')")) || (siebelQuery.contains("UPPER(sl.statusOrder) like UPPER('%N/%')"))
					|| (siebelQuery.contains("UPPER(sl.statusOrder) like UPPER('%N/A%')")))
			{
				logger.debug("Inside statusOrder");
				siebelQuery=siebelQuery.replace("UPPER(sl.statusOrder) like UPPER('%N%')","sl.statusOrder is null");
				logger.debug("Query String of statusOrder --->>>"+siebelQuery);
			}
			
			if(siebelQuery.contains("UPPER(sl.showEntitlementFlag) like UPPER('%0_null%')"))
			{
				logger.debug("Inside showEntitlementFlag special case");
				siebelQuery = siebelQuery.replace("UPPER(sl.showEntitlementFlag) like UPPER('%0_null%')", "(sl.showEntitlementFlag =0 or sl.showEntitlementFlag is null)");
				logger.debug("Query String of showEntitlementFlag --->>>"+siebelQuery);
			}
			String directValue="";
			String inDirectValue="";
			if(siebelQuerySplit.length >1){
			if(!siebelQuerySplit[1].toString().equals("NULL")){
				directValue="like'%"+siebelQuerySplit[1].toString().toUpperCase()+"%'";
				if("".equalsIgnoreCase(siebelQuerySplit[1].toString().toUpperCase())){
					directValue=directValue+"OR sl.partnerDirectValue IS NULL";	
				}
			}else{
				directValue="IS NULL";
			}
			if(!siebelQuerySplit[2].toString().equals("NULL")){
				inDirectValue="like'%"+siebelQuerySplit[2].toString().toUpperCase()+"%'";
				if("".equalsIgnoreCase(siebelQuerySplit[2].toString().toUpperCase())){
					inDirectValue=inDirectValue+"OR sl.partnerIndirectValue IS NULL";	
				}
			}else{
				inDirectValue="IS NULL";
			}
		}
			if(flag2){
				siebelQuery=siebelQuery + "(UPPER(sl.partnerDirectValue) "+directValue+") AND (UPPER(sl.partnerIndirectValue)"+inDirectValue+")";
			}else{
				if(flag){
					siebelQuery=siebelQuery + "AND (UPPER(sl.partnerDirectValue) "+directValue+") AND (UPPER(sl.partnerIndirectValue)"+inDirectValue+")";		
				}	
			}
			//Query query = HibernateUtil.getSession().createQuery("select sl from SiebelLocalization sl");
			Query query = HibernateUtil.getSession().createQuery(siebelQuery);
			logger.debug("The query query createQuery()-----<<>> "+query);
			for(Iterator it=query.iterate();it.hasNext();){
				logger.debug("Inside for loop-->>");
				SiebelLocalization sl = (SiebelLocalization) it.next();				
				result.getSiebelLocalizations().add(sl);
			}
			HibernateUtil.closeSession();
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}
		result.setTotalCount(result.getSiebelLocalizations().size());
		return result;
	}
	//ends here

	public SupportedLocaleListResult retrieveSupportedLocaleList() {
		SupportedLocaleListResult result = new SupportedLocaleListResult();
		try {			
			Query query = HibernateUtil.getSession().createQuery("select sl from SupportedLocale sl");
			for(Iterator it=query.iterate();it.hasNext();){
				SupportedLocale sl = (SupportedLocale) it.next();
				result.getSupportedLocales().add(sl);
			}
			HibernateUtil.closeSession();
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}
		return result;
	}

	public SRAdministrationSaveResult saveSRAdministrationDetail(
			SRAdministrationSaveContract contract) {
		   SiebelLocalization siebelLocalization = contract.getSiebelLocalization();
		   for(SiebelLocalizationLocale locale: siebelLocalization.getLocaleList()) {
			   if(locale.getSiebelLocalization() == null) {
				   locale.setSiebelLocalization(siebelLocalization);
			   }
		   }
		try {			
			HibernateUtil.beginTransaction();			
			HibernateUtil.getSession().saveOrUpdate(siebelLocalization);
			HibernateUtil.commitTransaction();
//			HibernateUtil.getSession().flush();
		    HibernateUtil.closeSession();
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}
		
		SRAdministrationSaveResult result = new SRAdministrationSaveResult();
		return result;
	}
	
	/*
	 * Retrieves localized siebel display value for given siebel value, under specific option and locale.
	 * Developed in Phase1
	 */
	private String retrieveLocalizedSiebelValue(String siebelValue, Locale locale, String siebelValueOption) {
		String localizedSiebelValue = null;
		String language = LocaleUtil.getSupportLocaleCode(locale);
		MessageFormat mf = new MessageFormat(QUERY_LOCALIZED_SIEBEL_VALUE);
		String sql = mf.format(new Object[]{siebelValueOption});
		
		try {
			Query query = HibernateUtil.getSession().createSQLQuery(sql);
			query.setParameter("local_code", language);
			query.setParameter("siebel_value" , siebelValue);
			List list = query.list();
			if(list != null && list.size() > 0) {
				localizedSiebelValue = (String) list.get(0);
			} else {
				query = HibernateUtil.getSession().createSQLQuery(sql);
				query.setParameter("local_code", DEFAULT_LOCALE_CODE);
				query.setParameter("siebel_value", siebelValue);
				list = query.list();
				if(list != null && list.size() > 0) {
					localizedSiebelValue = (String) list.get(0);
				} else {
					localizedSiebelValue = siebelValue;
				}
			}
			HibernateUtil.closeSession();
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}
		return localizedSiebelValue; 
	}
	
	public static void main(String[] args) {
	//	Query query = HibernateUtil.getSession().createSQLQuery("select doc from Document doc");
	//	
	//	HashMap hiea = new HashMap();
	//	for(Iterator it=query.iterate();it.hasNext();){
	//		Document document = (Document) it.next();
	//		String filePath = document.getFilePath();
	//		logger.info(filePath);
	//		break;
	//	}
		
		Query query = HibernateUtil.getSession().createQuery("select sl from SiebelLocalization sl");
		logger.debug(query);
		for(Iterator it=query.iterate();it.hasNext();){
			SiebelLocalization sl = (SiebelLocalization) it.next();
		}
		HibernateUtil.closeSession();
	}

	/**
	 * Retrieves a list of ListOfValues with localization.
	 * Developed in Phase2
	 */
	@Override
	public LocalizedSiebelLOVListResult retrieveLocalizedSiebelLOVList(
			LocalizedSiebelLOVListContract contract) {
		LocalizedSiebelLOVListResult result = new LocalizedSiebelLOVListResult();
		List<ListOfValues> localizedSiebelLOVList = new ArrayList<ListOfValues>(0);
		String lovType = contract.getLovListName();
		String partnerType = contract.getPartnerType();
		
		try {
			boolean searchPartnerTypeFlag = lovType.equals(SiebelLocalizationOptionEnum.PARTNER_SERVICE_REQUEST_STATUS.getValue()) ||
					lovType.equals(SiebelLocalizationOptionEnum.PARTNER_SERVICE_REQUEST_SUBSTATUS.getValue());
			String sql;
			if (searchPartnerTypeFlag && partnerType != null) {
				sql = SQL_GET_LOCALIZED_LOV_SPECIFIC_PARTNER_TYPE;
			} else {
				sql = SQL_GET_LOCALIZED_LOV;
			}
			Query query = HibernateUtil.getSession().createSQLQuery(sql);
			query.setParameter("local_code", contract.getLocaleName());
			query.setParameter("option_type", lovType);
			if (searchPartnerTypeFlag && partnerType != null) {
				List<String> partnerTypeList = new ArrayList<String>();
				partnerTypeList.add("INVALID_TYPE");
				if (PartnerTypeEnum.DIRECT.getValue().equals(partnerType)) {
					partnerTypeList.add(PartnerTypeEnum.DIRECT.getValue());
					partnerTypeList.add(PartnerTypeEnum.BOTH.getValue());
				} else if (PartnerTypeEnum.INDIRECT.getValue().equals(partnerType)) {
					partnerTypeList.add(PartnerTypeEnum.INDIRECT.getValue());
					partnerTypeList.add(PartnerTypeEnum.BOTH.getValue());
				} else if (PartnerTypeEnum.BOTH.getValue().equals(partnerType)) {
					partnerTypeList.add(PartnerTypeEnum.DIRECT.getValue());
					partnerTypeList.add(PartnerTypeEnum.INDIRECT.getValue());
					partnerTypeList.add(PartnerTypeEnum.BOTH.getValue());
				}
				query.setParameterList("partner_type_list", partnerTypeList);
			}
			
			List list = query.list();
			if(list != null && list.size() > 0) {
				for (Object object : list) {
					ListOfValues localizedLOV = new ListOfValues();
					Object[] row = (Object[]) object;
					localizedLOV.setValue(row[1].toString());
					localizedLOV.setName(row[0].toString());
					localizedLOV.setType(lovType);
					localizedSiebelLOVList.add(localizedLOV);
				}
			} 
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		} finally {
			HibernateUtil.closeSession();
		}
		
		result.setLocalizedSiebelLOVList(localizedSiebelLOVList);
		return result;
	}

	/**
	 * Retrieves localized siebel display name for given siebel value, under specific option and locale.
	 * Developed in Phase2
	 */
	@Override
	public LocalizedSiebelValueResult retrieveLocalizedSiebelValue(
			LocalizedSiebelValueContract contract) {
		LocalizedSiebelValueResult result = new LocalizedSiebelValueResult();
		String localizedSiebelValue = null;
		String localeName = contract.getLocaleName();
		String lovValue = contract.getLovValue();
		String lovType = contract.getLovListName();
		ListOfValues lov = new ListOfValues();
		lov.setType(lovType);
		lov.setValue(lovValue);
		
		try {
			Query query = HibernateUtil.getSession().createSQLQuery(SQL_GET_SIEBEL_LOCALIZATION);
			query.setParameter("local_code", localeName);
			query.setParameter("siebel_value" , lovValue);
			query.setParameter("option_type", lovType);
			List list = query.list();
			if(list != null && list.size() > 0) {
				localizedSiebelValue = (String) list.get(0);
			} 
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		} finally {
			HibernateUtil.closeSession();
		}
		lov.setName(localizedSiebelValue);
		
		result.setLovValue(lov);
		return result;
	}
	/**.
	 * This method is used to retrieve the localized page count name name
	 */
	public LocalizedPageCountNameResult retrieveLocalizedPageCountName(
			LocalizedPageCountNameContract contract) {
		LocalizedPageCountNameResult result = new LocalizedPageCountNameResult();
		result.setLocalizedValue(retrieveLocalizedSiebelValue(
				contract.getSiebelValue(), contract.getLocale(),
				SiebelLocalization.SiebelLocalizationOptionEnum.PAGE_COUNT_DATA.getValue()));
		return result;
	}
}
