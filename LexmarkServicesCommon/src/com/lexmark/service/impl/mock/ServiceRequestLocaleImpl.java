package com.lexmark.service.impl.mock;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Logger;

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
import com.lexmark.domain.SiebelLocalization;
import com.lexmark.domain.SiebelLocalization.SiebelLocalizationOptionEnum;
import com.lexmark.domain.ListOfValues;
import com.lexmark.domain.SiebelLocalizationLocale;
import com.lexmark.domain.SupportedLocale;
import com.lexmark.enums.RequestStatusEnum;
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
import com.lexmark.service.impl.real.jdbc.HibernateUtil;
import com.lexmark.service.impl.real.jdbc.InfrastructureException;

public class ServiceRequestLocaleImpl implements ServiceRequestLocale {
//	private static Logger logger = Logger.getAnonymousLogger(ServiceRequestLocaleImpl.class);

	
	private static final int NUM_OF_EACH_TYPE = 7;
	private static final int IDX_TYPE_Entitlement_Service_Details = 1;
	private static final int NUM_OF_TYPES = SiebelLocalizationOptionEnum.values().length;

	private static int nextId = 1;
	private static int nextLocaleId = 1;
	private static Map<Integer, SiebelLocalization> mapSiebelLocalizations = new HashMap<Integer, SiebelLocalization>();
	private static List<SiebelLocalization> listSiebelLocalizations = new ArrayList<SiebelLocalization>();

	static {
		for (int i = 0; i < NUM_OF_TYPES; i++) {
			for (int j = 0; j < NUM_OF_EACH_TYPE; j++) {
				SiebelLocalization localization = createLocalization(i, j);
				listSiebelLocalizations.add(localization);
				Integer id = localization.getSiebelLocalizationId();
				mapSiebelLocalizations.put(id, localization);
				nextId = id + 1;
			}
		}
	}

	public SRLocalizationResult deleteSRLocalization(SRLocalizationContract contract) {
		SRLocalizationResult result = new SRLocalizationResult();
		Integer siebelLocalizationId = contract.getSiebelLocalizationId();
		if (siebelLocalizationId != null && siebelLocalizationId % 2 == 0) {
			delete(siebelLocalizationId);
			result.setSucceed(true);
		} else {
			result.setSucceed(false);
		}
		return result;
	}

	public LocalizedEntitlementServiceListResult retrieveLocalizedEntitlementServiceList(
			LocalizedEntitlementServiceListContract contract) {
		// TODO Auto-generated method stub
		return null;
	}

	public LocalizedExchangeOptionListResult retrieveLocalizedExchangeOptionList(
			LocalizedExchangeOptionListContract contract) {
		// TODO Auto-generated method stub
		return null;
	}

	public LocalizedServiceActivityStatusResult retrieveLocalizedServiceActivityStatus(
			LocalizedServiceActivityStatusContract contract) {
		// TODO Auto-generated method stub
		return null;
	}

	public LocalizedServiceStatusResult retrieveLocalizedServiceStatus(LocalizedServiceStatusContract contract) {
		// TODO Auto-generated method stub
		LocalizedServiceStatusResult result =  new LocalizedServiceStatusResult();
		String sibelValue = contract.getSiebelValue();
		if(sibelValue.equalsIgnoreCase(RequestStatusEnum.OPEN.getValue())){
			result.setLocalizedString(RequestStatusEnum.OPEN.getValue());
		}else if(sibelValue.equalsIgnoreCase(RequestStatusEnum.COMPLETED.getValue())){
			result.setLocalizedString(RequestStatusEnum.COMPLETED.getValue());
		}else if(sibelValue.equalsIgnoreCase(RequestStatusEnum.CLAIM_SUBMITTED.getValue())){
			result.setLocalizedString(RequestStatusEnum.CLAIM_SUBMITTED.getValue());
		}else{
			result.setLocalizedString(sibelValue);
		}

		return result;
	}

	public SRAdministrationDetailResult retrieveSRAdministrationDetail(SRAdministrationDetailContract contract) {
		Integer siebelLocalizationId = contract.getSiebelLocalizationId();
		SiebelLocalization siebelLocalization = mapSiebelLocalizations.get(siebelLocalizationId);
		SRAdministrationDetailResult result = new SRAdministrationDetailResult();
		result.setSiebelLocalization(siebelLocalization);
		return result;
	}

	public SRAdministrationListResult retrieveSRAdministrationList(SRAdministrationListContract contract) {
		SRAdministrationListResult result = new SRAdministrationListResult();
		result.setTotalCount(listSiebelLocalizations.size());
		result.setSiebelLocalizations(listSiebelLocalizations);
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
			String queryParam = filterCriterias;
			String constantPattern = queryParam.replace(":", ") like UPPER(");
			String siebelQuery = "select sl from SiebelLocalization sl  where UPPER(sl.";
			if(!queryParam.contains(">>")){
				siebelQuery = siebelQuery+constantPattern+")";
			}
			else
			{
				String constantPatternMultiple = queryParam.replace(":", ") like UPPER(").replace(">>",") and UPPER(sl.");
				
				siebelQuery = siebelQuery+constantPatternMultiple+")";
			}
			//logger.debug("The final Query String is-->> "+siebelQuery);
			//Query query = HibernateUtil.getSession().createQuery("select sl from SiebelLocalization sl");
			Query query = HibernateUtil.getSession().createQuery(siebelQuery);
			//logger.debug("The query query createQuery()-----<<>> "+query);
			for(Iterator it=query.iterate();it.hasNext();){
			//	logger.debug("Inside for loop-->>");
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
		List<SupportedLocale> supportedLocales = DomainMockDataGenerator.getSupportedLocales();
		result.setSupportedLocales(supportedLocales);
		return result;
	}

	private static SiebelLocalization createLocalization(int idxType, int idxValue) {
		SiebelLocalization localization = new SiebelLocalization();
		String optionType = SiebelLocalizationOptionEnum.values()[idxType].getValue();
		localization.setOptionType(optionType);
		Boolean flag = null;
		if (idxType == IDX_TYPE_Entitlement_Service_Details) {
			flag = idxValue % 2 == 0;
		}
		localization.setShowEntitlementFlag(flag);
		localization.setSiebelLocalizationId(calculateID(idxType, idxValue));
		localization.setSiebelValue(optionType + idxValue);

		List<SiebelLocalizationLocale> localeList = generateLocaleList(localization);
		localization.setLocaleList(localeList);

		return localization;
	}

	private static int calculateID(int idxType, int idxValue) {
		return idxType * NUM_OF_EACH_TYPE + idxValue;
	}

	private static List<SiebelLocalizationLocale> generateLocaleList(SiebelLocalization localization) {
		List<SupportedLocale> locales = DomainMockDataGenerator.getSupportedLocales();
		List<SiebelLocalizationLocale> localeList = new ArrayList<SiebelLocalizationLocale>();
		int i = 0;
		for (SupportedLocale supportedLocale : locales) {
			SiebelLocalizationLocale locale = new SiebelLocalizationLocale();
			locale.setDisplayValue(localization.getOptionType() + " - " + supportedLocale.getSupportedLocaleCode());
			int siebelLocalizationLocaleId = localization.getSiebelLocalizationId() * locales.size() + i;
			locale.setSiebelLocalizationLocaleId(siebelLocalizationLocaleId);
			locale.setSupportedLocale(supportedLocale);
			localeList.add(locale);
			i++;
			nextLocaleId = siebelLocalizationLocaleId + 1;
		}
		return localeList;
	}

	public SRAdministrationSaveResult saveSRAdministrationDetail(SRAdministrationSaveContract contract) {
		SiebelLocalization siebelLocalization = contract.getSiebelLocalization();
		if (siebelLocalization.getSiebelLocalizationId() == null) {
			siebelLocalization.setSiebelLocalizationId(nextId++);
			List<SiebelLocalizationLocale> localeList = siebelLocalization.getLocaleList();
			for (SiebelLocalizationLocale siebelLocalizationLocale : localeList) {
				siebelLocalizationLocale.setSiebelLocalizationLocaleId(nextLocaleId++);
			}
		} else {
			delete(siebelLocalization.getSiebelLocalizationId());
		}
		listSiebelLocalizations.add(siebelLocalization);
		Collections.sort(listSiebelLocalizations, new Comparator<SiebelLocalization>() {
			public int compare(SiebelLocalization arg0, SiebelLocalization arg1) {
				return arg0.getSiebelLocalizationId().compareTo(arg1.getSiebelLocalizationId());
			}
		});
		mapSiebelLocalizations.put(siebelLocalization.getSiebelLocalizationId(), siebelLocalization);

		SRAdministrationSaveResult result = new SRAdministrationSaveResult();
		result.setSucceed(true);
		return result;
	}

	private void delete(Integer siebelLocalizationId) {
		SiebelLocalization toDelete = null;
		for (SiebelLocalization item : listSiebelLocalizations) {
			if (item.getSiebelLocalizationId().equals(siebelLocalizationId)) {
				toDelete = item;
			}
		}
		if (toDelete != null) {
			listSiebelLocalizations.remove(toDelete);
			mapSiebelLocalizations.put(toDelete.getSiebelLocalizationId(), null);
		}
	}

	public static void main(String[] args) {
		ServiceRequestLocaleImpl serviceRequestLocaleImpl = new ServiceRequestLocaleImpl();
		SRAdministrationListResult result = serviceRequestLocaleImpl.retrieveSRAdministrationList(null);
		List<SiebelLocalization> siebelLocalizations = result.getSiebelLocalizations();
		for (SiebelLocalization siebelLocalization : siebelLocalizations) {
//			logger.info(siebelLocalization.getOptionType());
		}
	}

	@Override
	public CheckedEntitlementServiceDetailResult retrieveCheckedEntitlementServiceDetail(
			CheckedEntitlementServiceDetailContract contract) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LocalizedEntitlementServiceDetailResult retrieveLocalizedEntitlementServiceDetail(
			LocalizedEntitlementServiceDetailContract contract) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LocalizedSiebelLOVListResult retrieveLocalizedSiebelLOVList(
			LocalizedSiebelLOVListContract contract) {
		LocalizedSiebelLOVListResult result = new LocalizedSiebelLOVListResult();
		result.setLocalizedSiebelLOVList(new ArrayList<ListOfValues>(0));
		return result;
	}

	@Override
	public LocalizedSiebelValueResult retrieveLocalizedSiebelValue(
			LocalizedSiebelValueContract contract) {
		LocalizedSiebelValueResult result = new LocalizedSiebelValueResult();
		ListOfValues lov = new ListOfValues();
		lov.setType(contract.getLovListName());
		lov.setValue(contract.getLovValue());
		lov.setName(contract.getLovValue());
		result.setLovValue(lov);
		return null;
	}

	@Override
	public LocalizedPageCountNameResult retrieveLocalizedPageCountName(
			LocalizedPageCountNameContract contract) {
		// TODO Auto-generated method stub
		return null;
	}

}
