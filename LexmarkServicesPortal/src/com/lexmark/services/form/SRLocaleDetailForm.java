package com.lexmark.services.form;

import static com.lexmark.services.LexmarkSPConstants.ERROR_CODE_SIEBEL_VALUE_IS_NULL;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import com.lexmark.constants.LexmarkConstants;
import com.lexmark.domain.SiebelLocalization;
import com.lexmark.domain.SiebelLocalizationLocale;
import com.lexmark.domain.SupportedLocale;

public class SRLocaleDetailForm {
	private static final Logger logger = LogManager.getLogger(SRLocaleDetailForm.class);
	private Integer siebelLocalizationId;
	private String optionType;
	private Boolean showEntitlementFlag;
	private String siebelValue;
	private Integer statusOrder;
	private boolean directPartnerFlag;
	private boolean indirectPartnerFlag;
	private Map<String, String> optionTypes = new LinkedHashMap<String, String>();

	private Integer[] localeIds = new Integer[0];
	private String[] localeNames = new String[0];
	private String[] localeCodes = new String[0];
	private String[] displayValues = new String[0];
	private Integer[] localizationLocaleIds = new Integer[0];
/**
 * 
 * @return Integer 
 */
	public Integer getSiebelLocalizationId() {
		return siebelLocalizationId;
	}
/**
 * 
 * @param siebelLocalizationId 
 */
	public void setSiebelLocalizationId(Integer siebelLocalizationId) {
		this.siebelLocalizationId = siebelLocalizationId;
	}
/**
 * 
 * @return String 
 */
	public String getOptionType() {
		return optionType;
	}
/**
 * 
 * @param optionType 
 */
	public void setOptionType(String optionType) {
		this.optionType = optionType;
	}
/**
 * 
 * @return Boolean 
 */
	public Boolean getShowEntitlementFlag() {
		return showEntitlementFlag;
	}
/**
 * 
 * @param showEntitlementFlag 
 */
	public void setShowEntitlementFlag(Boolean showEntitlementFlag) {
		this.showEntitlementFlag = showEntitlementFlag;
	}
/**
 * 
 * @return Boolean 
 */
	public boolean getDirectPartnerFlag() {
		return directPartnerFlag;
	}
/**
 * 
 * @param directPartnerFlag 
 */ 
	public void setDirectPartnerFlag(boolean directPartnerFlag) {
		this.directPartnerFlag = directPartnerFlag;
	}
/**
 * 
 * @return boolean 
 */
	public boolean getIndirectPartnerFlag() {
		return indirectPartnerFlag;
	}
/**
 * 
 * @param indirectPartnerFlag 
 */
	public void setIndirectPartnerFlag(boolean indirectPartnerFlag) {
		this.indirectPartnerFlag = indirectPartnerFlag;
	}
/**
 * 
 * @return String 
 */
	public String getSiebelValue() {
		return siebelValue;
	}
/**
 * 
 * @param siebelValue 
 */
	public void setSiebelValue(String siebelValue) {
		this.siebelValue = siebelValue;
	}
	/**
	 * 
	 * @return Integer 
	 */
	public Integer getStatusOrder() {
		return statusOrder;
	}
/**
 * 
 * @param statusOrder 
 */
	public void setStatusOrder(Integer statusOrder) {
		this.statusOrder = statusOrder;
	}
	/**
	 * 
	 * @return Integer 
	 */
	public Integer[] getLocaleIds() {
		return localeIds;
	}
/**
 * 
 * @param localeIds 
 */
	public void setLocaleIds(Integer[] localeIds) {
		this.localeIds = localeIds;
	}
/**
 * 
 * @return String 
 */
	public String[] getLocaleCodes() {
		return localeCodes;
	}
/**
 * 
 * @param localeCodes 
 */
	public void setLocaleCodes(String[] localeCodes) {
		this.localeCodes = localeCodes;
	}
/**
 * 
 * @return String 
 */
	public String[] getLocaleNames() {
		return localeNames;
	}
/**
 * 
 * @param localeNames 
 */
	public void setLocaleNames(String[] localeNames) {
		this.localeNames = localeNames;
	}
/**
 * 
 * @return String 
 */
	public String[] getDisplayValues() {
		return displayValues;
	}
/**
 * 
 * @param displayValues 
 */
	public void setDisplayValues(String[] displayValues) {
		this.displayValues = displayValues;
	}
/**
 * 
 * @return Integer 
 */
	public Integer[] getLocalizationLocaleIds() {
		return localizationLocaleIds;
	}
/**
 * 
 * @param localizationIds 
 */
	public void setLocalizationLocaleIds(Integer[] localizationIds) {
		this.localizationLocaleIds = localizationIds;
	}


/**
 * 
 * @param optionTypes 
 */
	public void setOptionTypes(Map<String, String> optionTypes) {
		this.optionTypes = optionTypes;
	}
/**	
 * 
 * @return Map 
 */
	public Map<String, String> getOptionTypes() {
		if(optionTypes.isEmpty()) {
			for(SiebelLocalization.SiebelLocalizationOptionEnum option : SiebelLocalization.SiebelLocalizationOptionEnum.values() ) {
				optionTypes.put(option.getValue(), option.getDisplayName());
			}
		}
		return optionTypes;
	}
/**
 * 
 * @param localization 
 * @param supportedLocales 
 * @return SRLocaleDetailForm 
 */
	public static SRLocaleDetailForm assembleFrom(SiebelLocalization localization,
			List<SupportedLocale> supportedLocales) {
		SRLocaleDetailForm form = new SRLocaleDetailForm();
		Map<Integer, SiebelLocalizationLocale> kownDisplays = new LinkedHashMap<Integer, SiebelLocalizationLocale>();
		if (localization != null) {
			form.siebelLocalizationId = localization.getSiebelLocalizationId();
			form.optionType = localization.getOptionType();
			form.showEntitlementFlag = localization.getShowEntitlementFlag();
			form.siebelValue = localization.getSiebelValue();
			form.statusOrder = localization.getStatusOrder();
			
			String partnerType = localization.getPartnerType();
			boolean directPartnerFlag=false;
			boolean indirectPartnerFlag=false;
			if (LexmarkConstants.PARTNER_TYPE_DIRECT.equals(partnerType) ||
					LexmarkConstants.PARTNER_TYPE_BOTH.equals(partnerType)) {
				directPartnerFlag = true;
			} else {
				directPartnerFlag = false;
			}

			if (LexmarkConstants.PARTNER_TYPE_INDIRECT.equals(partnerType) ||
					LexmarkConstants.PARTNER_TYPE_BOTH.equals(partnerType)) {
				indirectPartnerFlag = true;
			} else {
				indirectPartnerFlag = false;
			}
			form.setDirectPartnerFlag(directPartnerFlag);
			form.setIndirectPartnerFlag(indirectPartnerFlag);
			
			List<SiebelLocalizationLocale> localeList = localization.getLocaleList();
			for (SiebelLocalizationLocale siebelLocalizationLocale : localeList) {
				kownDisplays.put(siebelLocalizationLocale.getSupportedLocale().getSupportedLocaleId(), siebelLocalizationLocale);
			}
		}
		int size = supportedLocales.size();
		form.localeIds = new Integer[size];
		form.localeNames = new String[size];
		form.localeCodes = new String[size];
		form.displayValues = new String[size];
		form.localizationLocaleIds = new Integer[size];
		int i = 0;
		for (SupportedLocale supportedLocale : supportedLocales) {
			Integer id = supportedLocale.getSupportedLocaleId();
			form.localeIds[i] = id;
			form.localeNames[i] = supportedLocale.getSupportedLocaleName();
			form.localeCodes[i] = supportedLocale.getSupportedLocaleCode();
			SiebelLocalizationLocale siebelLocalizationLocale = kownDisplays.get(id);
			if (siebelLocalizationLocale != null) {
				form.displayValues[i] = siebelLocalizationLocale.getDisplayValue();
				form.localizationLocaleIds[i] = siebelLocalizationLocale.getSiebelLocalizationLocaleId();
			}
			i++;
		}
		return form;
	}

	@Override
	public String toString() {
		StringBuffer sbuff = new StringBuffer();

		sbuff.append("siebelLocalizationId: ").append(siebelLocalizationId).append("\n");
		sbuff.append("optionType: ").append(optionType).append("\n");
		sbuff.append("showEntitlementFlag: ").append(showEntitlementFlag).append("\n");
		sbuff.append("siebelValue: ").append(siebelValue).append("\n");
		
		sbuff.append("localeIds: \n");
		for (Integer localeId : localeIds) {
			sbuff.append("\t").append(localeId).append("\n");
		}

		sbuff.append("localeCodes: \n");
		for (String localeCode : localeCodes) {
			sbuff.append("\t").append(localeCode).append("\n");
		}
		sbuff.append("localeNames: \n");
		for (String localeName : localeNames) {
			sbuff.append("\t").append(localeName).append("\n");
		}
		sbuff.append("displayValues: \n");
		for (String displayValue : displayValues) {
			sbuff.append("\t").append(displayValue).append("\n");
		}
		sbuff.append("localizationLocaleIds: \n");
		for (Integer localizationLocaleId : localizationLocaleIds) {
			sbuff.append("\t").append(localizationLocaleId).append("\n");
		}
		return sbuff.toString();
	}
/**
 * 
 * @param errors 
 * @return List 
 */
	public SiebelLocalization deassemble(List<String> errors) {
		validate(errors);
		if (!errors.isEmpty()){
			return null;
			}
		SiebelLocalization localization = new SiebelLocalization();
		localization.setSiebelLocalizationId(siebelLocalizationId);
		localization.setOptionType(optionType);
		boolean isESD = SiebelLocalization.SiebelLocalizationOptionEnum.ENTITLEMENT_SERVICE_DETAILS.getValue().equals(optionType);
		logger.debug(optionType+" " + isESD+" showEntitlementFlag "+showEntitlementFlag);
		Boolean flag = isESD ? Boolean.TRUE.equals(showEntitlementFlag) : null;
		localization.setShowEntitlementFlag(flag);
		
		String partnerType = null;
		if (SiebelLocalization.SiebelLocalizationOptionEnum.PARTNER_SERVICE_REQUEST_STATUS.getValue().equals(optionType) ||
				SiebelLocalization.SiebelLocalizationOptionEnum.PARTNER_SERVICE_REQUEST_SUBSTATUS.getValue().equals(optionType)) {
			if (! directPartnerFlag && ! indirectPartnerFlag) {
				partnerType = null;
			} else if (directPartnerFlag && ! indirectPartnerFlag){
				partnerType = LexmarkConstants.PARTNER_TYPE_DIRECT;
			} else if (!directPartnerFlag && indirectPartnerFlag){
				partnerType = LexmarkConstants.PARTNER_TYPE_INDIRECT;
			} else {
				partnerType = LexmarkConstants.PARTNER_TYPE_BOTH;
			}
		}
		localization.setPartnerType(partnerType);
		
		localization.setSiebelValue(siebelValue);
		localization.setStatusOrder(statusOrder);
		if (localeIds != null) {
			List<SiebelLocalizationLocale> localeList = new ArrayList<SiebelLocalizationLocale>(localeIds.length);
			int localeLen = localeIds.length;
			for (int i = 0; i < localeLen; i++) {
				if(displayValues[i] != null && displayValues[i].trim().length() != 0){
			     SiebelLocalizationLocale localizationLocale = new SiebelLocalizationLocale();
				 SupportedLocale locale = new SupportedLocale();

					localizationLocale.setSiebelLocalizationLocaleId(localizationLocaleIds[i]);
					localizationLocale.setDisplayValue(displayValues[i]);

					locale.setSupportedLocaleId(localeIds[i]);
					locale.setSupportedLocaleCode(localeCodes[i]);
					locale.setSupportedLocaleName(localeNames[i]);
					localizationLocale.setSupportedLocale(locale);
					localeList.add(localizationLocale);					
				}
			}
			localization.setLocaleList(localeList);
		}
		return localization;
	}
/**
 * 
 * @param errors 
 */
	private void validate(List<String> errors) {
		if (siebelValue == null || siebelValue.trim().length() == 0) {
			errors.add(ERROR_CODE_SIEBEL_VALUE_IS_NULL);
		}
	}
}
