package com.lexmark.services.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.lexmark.contract.LocalizedSiebelLOVListContract;
import com.lexmark.domain.ListOfValues;
import com.lexmark.result.LocalizedSiebelLOVListResult;
import com.lexmark.service.api.ServiceRequestLocale;
import com.lexmark.services.util.ContractFactory;
import com.lexmark.util.LOVComparator;

public class ControllerUtil {
	/**
	 * Retrieves a map of localized LOV from database.
	 * It will be used in list page to fill selection filter, and to localize LOV string in grid.
	 * @param lovType
	 * @param globalService
	 * @param serviceRequestLocaleService
	 * @param locale
	 * @return
	 * @throws Exception
	 */
	public static Map<String, String> retrieveLocalizedLOVMap(String lovType,
			String customerType,
			ServiceRequestLocale serviceRequestLocaleService,
			Locale locale) {
		Map<String, String> lovMap = new LinkedHashMap<String, String>();
		List<ListOfValues> valuesList =
			retrieveLocalizedSiebelLOVList(lovType, customerType, serviceRequestLocaleService, locale).getLocalizedSiebelLOVList();
		Collections.sort(valuesList, new LOVComparator(locale));
		
		for(ListOfValues lov : valuesList){
			lovMap.put(lov.getValue(), lov.getName());
		}
		return lovMap;		
	}
	/**
	 * Retrieves localized localized Siebel LOV list from database.
	 * It will be used in list page to fill selection filter, and to localize LOV string in grid.
	 * @param lovType
	 * @param partnerType only used to retrieve activity status or activity substatus
	 * @param locale
	 * @param serviceRequestLocaleService
	 * @return
	 */
	
	private static LocalizedSiebelLOVListResult retrieveLocalizedSiebelLOVList(
			String lovType, String partnerType, ServiceRequestLocale serviceRequestLocaleService, Locale locale) {
		LocalizedSiebelLOVListResult result;
		LocalizedSiebelLOVListContract contract = ContractFactory.createLocalizedSiebelLOVListContract(
				lovType, partnerType, locale);
		try {
			result = serviceRequestLocaleService.retrieveLocalizedSiebelLOVList(contract);
		} catch (Exception ex) {
			result = new LocalizedSiebelLOVListResult();
			result.setLocalizedSiebelLOVList(new ArrayList<ListOfValues>(0));
		}
		return result;
	}


}
