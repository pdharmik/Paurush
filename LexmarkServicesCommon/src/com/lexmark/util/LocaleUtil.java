package com.lexmark.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import com.lexmark.domain.GenericLocale;
import com.lexmark.domain.SupportedLocale;

/**
 * The String Util for Lexmark Service Portal.
 * 
 * @author roger.lin
 * 
 */

public class LocaleUtil {
	private static Logger LOGGER = LogManager.getLogger(LocaleUtil.class);
	private static final String DEFAULT_LOCALE_CODE = "en";
	public static String getLocalizedName(List<? extends GenericLocale> locales, Locale locale) {
		String name = null;
		//TODO need take into account the zh_CN,  zh_TW,  pt_BR, pt_PT
		for (GenericLocale definitionLocale : locales) {
			SupportedLocale supportedLocale = definitionLocale.getSupportedLocale();
			String localeCode = supportedLocale.getSupportedLocaleCode();
			if (localeCode.equalsIgnoreCase(locale.getLanguage())) {
				name = definitionLocale.getName();
			} else if (name == null && localeCode.equalsIgnoreCase(locale.getLanguage())) {
				name = definitionLocale.getName();
			}
		}
		return name;
	}
	
	public static String getSupportLocaleCode(Locale locale) {
		if(locale == null) {
			return DEFAULT_LOCALE_CODE;
		}
		String localeCode = locale.getLanguage();
		if (localeCode.equalsIgnoreCase("zh") || localeCode.equalsIgnoreCase("pt")) {
			localeCode = localeCode + "_" + locale.getCountry();
		}
		return localeCode;
	
	}

	public static List<? extends GenericLocale> expandLocaleList(List<? extends GenericLocale> knowLocales,
			List<SupportedLocale> supportedLocales) {
		List<GenericLocale> expandedLocales = new ArrayList<GenericLocale>();
		for (SupportedLocale supportedLocale : supportedLocales) {
			boolean supported = false;
			if (knowLocales != null) {
				for (GenericLocale knowLocale : knowLocales) {
					
					Integer kId = knowLocale.getSupportedLocale().getSupportedLocaleId();
					Integer supportedLocaleId = supportedLocale.getSupportedLocaleId();
					LOGGER.info(kId+" -- "+ supportedLocaleId);
					LOGGER.info(knowLocale.getSupportedLocale().getSupportedLocaleCode()+" -- "+ supportedLocale.getSupportedLocaleCode());
					if (kId.intValue() == supportedLocaleId) {
						supported = true;
						LOGGER.info(knowLocale.getSupportedLocale().getSupportedLocaleCode()+"add");
						expandedLocales.add(knowLocale);
						break;
					}
				}
			}
			if (!supported) {
				GenericLocale locale = new GenericLocale();
				locale.setSupportedLocale(supportedLocale);
				expandedLocales.add(locale);
			}
		}
		return expandedLocales;
	}
}
