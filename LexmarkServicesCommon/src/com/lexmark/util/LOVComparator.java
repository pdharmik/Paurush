package com.lexmark.util;

import java.text.Collator;
import java.util.Comparator;
import java.util.Locale;

import com.lexmark.domain.ListOfValues;

/**
 * This comparator is used to compare ListOfValues. It compares by comparing ListOfValues.name. 
 */
public class LOVComparator implements Comparator<ListOfValues> {
	
	private Locale locale;
	
	public LOVComparator(Locale locale) {
		this.locale = locale;
	}

	/*
	 * Compares ListofValues by comparing ListOfValues.name, and returns result.
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(ListOfValues o1, ListOfValues o2) {
		if (o1 == null && o2 == null) {
			return 0;
		} else if (o1 == null && o2 != null) {
			return -1;
		} else if (o1 != null && o2 == null) {
			return 1;
		}
		if (locale == null) {
			locale = Locale.US;
		}
		Collator collator = Collator.getInstance(locale);
		
		return collator.compare(o1.getName(), o2.getName());
	}

}
