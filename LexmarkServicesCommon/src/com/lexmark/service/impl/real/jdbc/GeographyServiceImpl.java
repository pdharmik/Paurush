package com.lexmark.service.impl.real.jdbc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.lexmark.contract.GeographyCountryContract;
import com.lexmark.contract.GeographyStateContract;
import com.lexmark.result.GeographyListResult;
import com.lexmark.service.api.GeographyService;

public class GeographyServiceImpl implements GeographyService {
	
	public static Map<String, List<GeographyStateContract>> allStateMap;
	
	private static Logger logger = LogManager.getLogger(GeographyServiceImpl.class);
	private static final String SQL_SEARCH_COUNTRY_LIST = "SELECT COUNTRY_NAME, COUNTRY_CODE, COUNTRY_NUMBER FROM IDMDA.COUNTRY ORDER BY COUNTRY_NAME" ;
	private static final String SQL_SEARCH_STATE_LIST = "SELECT COUNTRY_CODE, STATE_CODE, STATE_NAME FROM IDMDA.STATE WHERE COUNTRY_CODE = :countryCode ORDER BY STATE_NAME" ;
	
	@Override
	public GeographyListResult getCountryDetails() {
		// TODO Auto-generated method stub
		/*CountryListResult result;
		List<String> countryList = new ArrayList<String>();
		
		result = new CountryListResult();
		result.setCountryList(countryList);*/
		
		//return result;
		GeographyListResult result = new GeographyListResult();;
		List<GeographyCountryContract> countryList = new ArrayList<GeographyCountryContract>();
		
		
		String countryName;
		String countryCode;
		Query query;
		try {
			query = HibernateUtil2.getSession().createSQLQuery(SQL_SEARCH_COUNTRY_LIST);
			List list = query.list();
			for(int i = 0; i < list.size(); i++){
				GeographyCountryContract contract = new GeographyCountryContract();
				Object[] row = (Object[]) list.get(i);
				countryName = ((String)row[0]);
				countryCode = ((String)row[1]);
				logger.debug(row[2]);
				logger.debug("key "+countryName+" value "+countryCode);
				contract.setCountryName(countryName);
				contract.setCountryCode(countryCode);
				countryList.add(contract);
			}
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InfrastructureException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
		result.setCountryList(countryList);
		return result;
		/*Query query;
		try {
			query = HibernateUtil.getSession().createSQLQuery(SQL_SEARCH_COUNTRY_LIST);
			List list = query.list();
			for(int i = 0; i < list.size(); i++){
				Object[] row = (Object[]) list.get(i);
				logger.debug((String)row[0]);
				logger.debug((String)row[1]);
				logger.debug(Integer.valueOf(row[2].toString()));
			}
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InfrastructureException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
	}
	@Override
	public GeographyListResult getStateDetails(String countryCode) {
		// TODO Auto-generated method stub
		//logger.debug("--------- Starting GeographyserviceImpl --------------- country is "+countryCode);
		GeographyListResult result = new GeographyListResult();
		List<GeographyStateContract> stateList = new ArrayList<GeographyStateContract>();
		//List<String> stateList = new ArrayList<String>();
		//logger.debug("Country code is "+countryCode);
		Query query;
		try {
			query = HibernateUtil2.getSession().createSQLQuery(SQL_SEARCH_STATE_LIST);
			query.setParameter("countryCode", countryCode);
			List list = query.list();
			for(int i = 0; i < list.size(); i++){
				GeographyStateContract contract = new GeographyStateContract();
				Object[] row = (Object[]) list.get(i);
				contract.setStateCode((String)row[1]);
				contract.setStateName((String)row[2]);
				stateList.add(contract);
			}
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InfrastructureException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		result.setStateList(stateList);
		return result;
	}
	
	
	@Override	
	public Map<String, List<GeographyStateContract>> getAllStateDetails() throws Exception {
		
		if(allStateMap!=null && !allStateMap.isEmpty())
		{
			return Collections.unmodifiableMap(allStateMap);
		}
		else{
		
		GeographyListResult countryResult = getCountryDetails();		
		
		allStateMap=new LinkedHashMap<String, List<GeographyStateContract>>();
		
		try {
			
			for(GeographyCountryContract listResult:countryResult.getCountryList())
			{
				GeographyListResult stateResult=getStateDetails(listResult.getCountryCode());
				allStateMap.put(listResult.getCountryCode(), stateResult.getStateList());
			}
			
		} catch (HibernateException e) {
			e.printStackTrace();
		} catch (InfrastructureException e) {
			e.printStackTrace();
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		/*result.setStateList(stateList);
		return result;*/
		return Collections.unmodifiableMap(allStateMap);
		}
	}
	
	
}
