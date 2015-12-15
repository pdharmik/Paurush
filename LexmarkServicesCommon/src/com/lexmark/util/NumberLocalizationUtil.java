package com.lexmark.util;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class NumberLocalizationUtil {

	public static String formatIntegerToStringByLocale(String numberToFormat,
			Locale locale) {
		int myNumber = 0;
		if (numberToFormat != null && !numberToFormat.equals("")) {
			myNumber = new Integer(numberToFormat).intValue();
		}
		return formatIntegerToStringByLocale(myNumber, locale);
	}

	public static String formatIntegerToStringByLocale(int myNumber,
			Locale locale) {
		NumberFormat form;
		form = NumberFormat.getInstance(locale);

		Number formattedNumber = 0;
		try {
			formattedNumber = form.parse(form.format(myNumber));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return formattedNumber.toString();
	}

	public static String formatDecimalToStringByLocale(String numberToFormat,
			Locale locale) {
		double myNumber = formatStringToDecimal(numberToFormat, locale);
		String formattedNumber = formatDecimalToStringByLocale(myNumber, locale);

		return formattedNumber;
	}

	public static String formatDecimalToStringByLocale(double myNumber,
			Locale locale) {
		NumberFormat form;
		form = NumberFormat.getInstance(locale);
		form.setMinimumFractionDigits(2);
		String formattedNumber = form.format(myNumber);
		return formattedNumber;
	}
	
	public static double formatStringToDecimal(String numberToFormat, Locale locale) {
		return numberToFormat != null && !numberToFormat.equals("") 
			? new Double(numberToFormat).doubleValue() : 0;
	}

}
