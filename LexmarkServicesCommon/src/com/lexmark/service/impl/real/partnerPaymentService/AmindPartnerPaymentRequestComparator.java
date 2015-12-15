package com.lexmark.service.impl.real.partnerPaymentService;

import java.io.Serializable;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.lexmark.domain.Activity;

public class AmindPartnerPaymentRequestComparator implements Comparator<Activity>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6737991607373334028L;
	private Map<String,Object> sortMap = new HashMap<String,Object>();

	public AmindPartnerPaymentRequestComparator(Map<String,Object> argSortMap)
	{
		sortMap = argSortMap;
	}
	@SuppressWarnings("unchecked")
	public int compare(Activity o1, Activity o2) {

	
		if (o1 == null && o2 == null)
		{
			return 0;
		}
		if (o1 == null)
		{
			return -1;
		}
		if (o2 == null)
		{
			return 1;
		}

		Iterator sortIterator = sortMap.entrySet().iterator();
		Double sr1 = 0.0;
		Double sr2 = 0.0;
		int result = 0;

		Map.Entry sortEntryMap = (Map.Entry) sortIterator.next();
		Object key = sortEntryMap.getKey();
		Object value = sortEntryMap.getValue();

		if("Activity.totalPayment".equalsIgnoreCase(key.toString()))
		{
			sr1 = o1.getTotalPayment();
			sr2 = o2.getTotalPayment();

		}
		
		if("Activity.additionalPayments".equalsIgnoreCase(key.toString()))
		{
			sr1 = o1.getAdditionalPayments();
			sr2 = o2.getAdditionalPayments();

		}

		Double swapSr1 = sr1;;
		Double swapSr2 = sr2;;
		if(value.toString().equalsIgnoreCase("DESCENDING"))
		{
			swapSr2 = sr1;
			swapSr1 = sr2;

		}

		if (swapSr1 == null && swapSr2 == null)
		{
			return 0;
		}
		if (swapSr1 == null)
		{
			return -1;
		}
		if (swapSr2 == null)
		{
			return 1;
		}
		if (!(swapSr1.equals(swapSr2)))
			result=  swapSr1.compareTo(swapSr2);

		return result;
	}

}


