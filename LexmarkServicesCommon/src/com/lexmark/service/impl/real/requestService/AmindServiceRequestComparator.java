package com.lexmark.service.impl.real.requestService;

import java.io.Serializable;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.lexmark.service.impl.real.domain.ServiceRequest;

public class AmindServiceRequestComparator implements Comparator<ServiceRequest>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6737991607373334028L;
	private Map<String,Object> sortMap = new HashMap<String,Object>();

	public AmindServiceRequestComparator(Map<String,Object> argSortMap)
	{
		sortMap = argSortMap;
	}

	@SuppressWarnings("unchecked")
	public int compare(ServiceRequest o1, ServiceRequest o2) {

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

		Iterator<?> sortIterator = sortMap.entrySet().iterator();
		String sr1 = "";
		String sr2 = "";
		int result = 0;

		Map.Entry sortEntryMap = (Map.Entry) sortIterator.next();
		Object key = sortEntryMap.getKey();
		Object value = sortEntryMap.getValue();

		if(key.toString().equalsIgnoreCase("serviceAddress.addressLine1"))
		{
			sr1 = o1.getStreet1();
			sr2 = o2.getStreet1();

		}
		if(key.toString().equalsIgnoreCase("serviceRequestStatusDate"))
		{
			sr1 = o1.getServiceRequestStatusDate().toString();
			sr2 = o2.getServiceRequestStatusDate().toString();

		}
		if(key.toString().equalsIgnoreCase("serviceAddress.city"))
		{
			sr1 = o1.getCity();
			sr2 = o2.getCity();

		}
		if(key.toString().equalsIgnoreCase("serviceAddress.state"))
		{
			sr1 = o1.getState();
			sr2 = o2.getState();

		}
		if(key.toString().equalsIgnoreCase("primaryContact.lastName"))
		{
			sr1 = o1.getPrimaryContactLastName();
			sr2 = o2.getPrimaryContactLastName();

		}
		if(key.toString().equalsIgnoreCase("primaryContact.firstName"))
		{
			sr1 = o1.getPrimaryContactFirstName();
			sr2 = o2.getPrimaryContactFirstName();

		}
		if(key.toString().equalsIgnoreCase("primaryContact.workPhone"))
		{
			sr1 = o1.getPrimaryContactWorkPhone();
			sr2 = o2.getPrimaryContactWorkPhone();

		}
		if(key.toString().equalsIgnoreCase("primaryContact.emailAddress"))
		{
			sr1 = o1.getPrimaryContactEmail();
			sr2 = o2.getPrimaryContactEmail();

		}
		if(key.toString().equalsIgnoreCase("serviceRequestNumber"))
		{
			sr1 = o1.getServiceRequestNumber();
			sr2 = o2.getServiceRequestNumber();

		}
		
		
		if(key.toString().equalsIgnoreCase("serviceRequestNumber"))
		{
			sr1 = o1.getServiceRequestNumber();
			sr2 = o2.getServiceRequestNumber();

		}
		else if(key.toString().equalsIgnoreCase("serviceRequestDate"))
		{
			sr1 = o1.getServiceRequestDate().toString();
			sr2 = o1.getServiceRequestDate().toString();
		}
		else if(key.toString().equalsIgnoreCase("asset.serialNumber"))
		{
			sr1 = o1.getSerialNumber();
			sr2 = o2.getSerialNumber();
		}
		else if(key.toString().equalsIgnoreCase("asset.modelNumber"))
		{
			sr1 = o1.getModelNumber();
			sr2 = o2.getModelNumber();
		}
		else if(key.toString().equalsIgnoreCase("serviceRequestStatus"))
		{
			sr1 = o1.getStatus();
			sr2 = o2.getStatus();
		}
	   else if(key.toString().equalsIgnoreCase("serviceAddress.addressName"))
	   {
		   sr1 = o1.getStreet1();
		   sr2 = o2.getStreet2();
	   }
	   else if(key.toString().equalsIgnoreCase("serviceAddress.province"))
	   {
		   sr1 = o1.getProvince();
		   sr2 = o2.getProvince();
	   }
	   else if(key.toString().equalsIgnoreCase("serviceAddress.postalCode"))
	   {
		   sr1 = o1.getPostalCode();
		   sr2 = o2.getPostalCode();
	   }
	   else if(key.toString().equalsIgnoreCase("serviceAddress.country"))
	   {
		   sr1 = o1.getCountry();
		   sr2 = o2.getCountry();
	   }

		String swapSr1 = sr1;;
		String swapSr2 = sr2;;
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


