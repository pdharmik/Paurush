package com.lexmark.service.impl.real.billerDirect;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.lexmark.contract.BillerDirectContract;
import com.lexmark.domain.SapPortletURL;
import com.lexmark.domain.SupportedLocale;
import com.lexmark.result.BillerDirectResult;
import com.lexmark.service.api.BillerDirectService;
import com.lexmark.service.impl.real.jdbc.HibernateUtil;
import com.lexmark.service.impl.real.jdbc.InfrastructureException;

public class BillerDirectServiceImpl implements BillerDirectService {
	private static Logger LOGGER = LogManager.getLogger(BillerDirectServiceImpl.class);
	/*private static final String GET_SAP_URL = "select sp.URL_ID, sp.HEADER_TYPE, spu.FUNCTIONALITY_NAME, SPU.FUNCTIONALITY_URL " +
			"from SAP_PORTLET sp, SAP_PORTLET_URL spu where sp.URL_ID = spu.URL_ID ORDER BY URL_ID";
	private static final String GET_SAP_URL_DISPLAY = "select sp.URL_ID, sp.HEADER_TYPE, sp.language_support, sp.GRID_FUNCTIONALITY_NAME, " +
			"sp.GRID_FUNCTIONALITY_URL, spu.FUNCTIONALITY_NAME, SPU.FUNCTIONALITY_URL, SPU.LANGUAGE " +
			"from SAP_PORTLET sp left join SAP_PORTLET_URL spu on sp.URL_ID = spu.URL_ID ORDER BY URL_ID"; */
	/*@Override
	public BillerDirectResult retrieveBillerDirectURL(BillerDirectContract contract) throws Exception {
		// TODO Auto-generated method stub
		LOGGER.debug("---------------[IN] retrieveBillerDirectURL method -------------");
		//Lets call DB from here.
		BillerDirectResult billerDirectResult = new BillerDirectResult();
		try {
		Query query = HibernateUtil.getSession().createSQLQuery(GET_SAP_URL_DISPLAY);
		List list = query.list();
		List<BillerDirect> billerDirectURLList = new ArrayList<BillerDirect>();
		if(list != null && list.size() > 0) {
			logger.info("List size is coming as "+list.size());
			for(int i = 0; i < list.size(); i++){
				BillerDirect billerDirect = new BillerDirect();
				Object[] row = (Object[]) list.get(i);
				Integer urlId = new Integer(row[0].toString());
				String headerType = (String)row[1];
				String languageSupport = (String)row[2];
				String grifFuncName = (String)row[3];
				String grifFuncURL = (String)row[4];
				String funcName = (String)row[5];
				String funcUrl = (String)row[6];
				String language = (String)row[7];
				billerDirect.setHeaderType((String)row[1]);
				billerDirect.setFuncName((String)row[2]);
				billerDirect.setFuncUrl((funcUrl == null||funcUrl == "")?grifFuncURL:funcUrl);
				billerDirect.setTabId("tab"+i);
				billerDirectURLList.add(billerDirect);
				logger.info("urlId is "+urlId+" header type is "+headerType+" funcName is "+funcName+" funcUrl is "+funcUrl);
			}
		}
		billerDirectResult.setBillerDirectList(billerDirectURLList);
		}catch(HibernateException ex){
			throw new InfrastructureException(ex);
		}finally{
			HibernateUtil.commitTransaction();
			HibernateUtil.closeSession();
		}
		logger.info("---------------[OUT] retrieveBillerDirectURL method -------------");
		LOGGER.debug("---------------[OUT] retrieveBillerDirectURL method -------------");
		return billerDirectResult;
		DBConnection dbconn= new DBConnection();
		try
		{
			dbconn.open();
			dbconn.openQueryRS(GET_SAP_URL);
			while(dbconn.rs.next()) {
				logger.info("+++++++++++++++++++++++ URL ID is "+dbconn.rs.getString("URL_ID"));
				logger.info("+++++++++++++++++++++++ HEADER_TYPE is "+dbconn.rs.getString("HEADER_TYPE"));
				logger.info("+++++++++++++++++++++++ FUNCTIONALITY_NAME is "+dbconn.rs.getString("FUNCTIONALITY_NAME"));
				logger.info("+++++++++++++++++++++++ FUNCTIONALITY_URL is "+dbconn.rs.getString("FUNCTIONALITY_URL"));
			}
		}
		catch(Exception ex) {
			throw new RuntimeException(ex);
		} finally {
			dbconn.closeQueryRS();
			dbconn.close();
		}
		try {
			Query query = HibernateUtil.getSession().createQuery("from SapPortlet");
			List results = query.list();
			logger.info("Total number of rows "+results.size());
			if(results.size() > 0) {
				for(int row=0;row<results.size();row++) {
					SapPortlet billerDirectFromDB = (SapPortlet) results.get(row);
					logger.info("+++++++++++++++ URL id is "+billerDirectFromDB.getId());
					logger.info("++++++++++++++ Header type value is "+billerDirectFromDB.getHeaderType());
					logger.info("++++++++++++++ no of urls "+billerDirectFromDB.getSapPortletURLs().size());
					for(SapPortletURL sapPortletURL : billerDirectFromDB.getSapPortletURLs()){
						//logger.info("+++++++++++++++ URL id is "+sapPortletURL.getUrlId());
						logger.info("+++++++++++++++ functionalityName is "+sapPortletURL.getFunctionalityName());
						logger.info("+++++++++++++++ functionalityURL is "+sapPortletURL.getFunctionalityURL());
						logger.info("+++++++++++++++ language is "+sapPortletURL.getLanguage());
					}
				}
			}
		}catch(HibernateException ex){
			throw new InfrastructureException(ex);
		}finally{
			HibernateUtil.commitTransaction();
			HibernateUtil.closeSession();
		}
		
	}*/
	@Override
	public boolean saveBillerDirectUrl(BillerDirectContract contract) throws Exception {
		// TODO Auto-generated method stub
		boolean result;
		try{
			LOGGER.info("About to save the data in the database");
			HibernateUtil.beginTransaction();
			for(int i=0;i<contract.getSapPortletList().size();i++){
				LOGGER.info("Entered the loop");
				
				/*for(ReportScheduleParameter parameterValue: schedule.getReportScheduleParameters()){
					parameterValue.setSchedule(schedule);
				}*/
				for(SapPortletURL sapPortletURL:contract.getSapPortletList().get(i).getSapPortletURLs()){
					sapPortletURL.setUrls(contract.getSapPortletList().get(i));
				}
				
				HibernateUtil.getSession().saveOrUpdate(contract.getSapPortletList().get(i));
				LOGGER.info("Exit from the loop");
			}
			LOGGER.info("Out of the loop");
			HibernateUtil.commitTransaction();
			LOGGER.info("commit ransaction done");
			result = Boolean.TRUE;
			
		}catch (Exception e) {
			result=Boolean.FALSE;
			throw new InfrastructureException(e);
		}finally{
			HibernateUtil.closeSession();
		}
		LOGGER.info("result value is "+result);
		return result;
	}
	@Override
	public BillerDirectResult retrieveBillerDirectURLDisplay() throws Exception {
		LOGGER.debug("---------------[IN] retrieveBillerDirectURLDisplay method -------------");
		//Lets call DB from here.
		BillerDirectResult billerDirectResult = new BillerDirectResult();
		try {
		/*Query query = HibernateUtil.getSession().createSQLQuery(GET_SAP_URL_DISPLAY);
		List list = query.list();
		List<SapPortlet> sapPortletUrlList = new ArrayList<SapPortlet>();
		if(list != null && list.size() > 0) {
			logger.info("List size is coming as "+list.size());
			for(int i = 0; i < list.size(); i++){
				Object[] row = (Object[]) list.get(i);
				Integer urlId = new Integer(row[0].toString());
				String headerType = (String)row[1];
				String languageSupport = (String)row[2];
				String grifFuncName = (String)row[3];
				String grifFuncURL = (String)row[4];
				String funcName = (String)row[5];
				String funcUrl = (String)row[6];
				String language = (String)row[7];
				SapPortlet sapPortlet = new SapPortlet();
				List<SapPortletURL> sapPortletList = new ArrayList<SapPortletURL>();
				sapPortlet.setId(urlId);
				sapPortlet.setHeaderType(headerType);
				sapPortlet.setLanguageSupport(languageSupport);
				sapPortlet.setGridFunctionalityName(grifFuncName);
				sapPortlet.setGridFunctionalityURL(grifFuncURL);
				SapPortletURL sapPortletUrl = new SapPortletURL();
				sapPortletUrl.setFunctionalityName(funcName);
				sapPortletUrl.setFunctionalityURL(funcUrl);
				sapPortletUrl.setLanguage(language);
				sapPortletList.add(sapPortletUrl);
				sapPortlet.setSapPortletURLs(sapPortletList);
				sapPortletUrlList.add(sapPortlet);
				}
		}*/
		HibernateUtil.beginTransaction();
		Query query = HibernateUtil.getSession().createQuery("from SapPortlet sp order by sp.id");
		List list = query.list();
		billerDirectResult.setSapPortletList(list);
		HibernateUtil.commitTransaction();
		HibernateUtil.closeSession();
		}catch(HibernateException ex){
			throw new InfrastructureException(ex);
		}finally{
			HibernateUtil.commitTransaction();
			HibernateUtil.closeSession();
		}
		LOGGER.info("---------------[OUT] retrieveBillerDirectURLDisplay method -------------");
		LOGGER.debug("---------------[OUT] retrieveBillerDirectURLDisplay method -------------");
		return billerDirectResult;
	}
	
	
	
	@Override
	public BillerDirectResult retrieveBillerDirectURL() throws Exception {
		LOGGER.debug("---------------[IN] retrieveBillerDirectURL method -------------");
		//Lets call DB from here.
		BillerDirectResult billerDirectResult = new BillerDirectResult();
		try {
		HibernateUtil.beginTransaction();
		Query query = HibernateUtil.getSession().createQuery("from SapPortlet sp order by sp.id");
		List list = query.list();
		billerDirectResult.setSapPortletList(list);
		HibernateUtil.commitTransaction();
		HibernateUtil.closeSession();
		}catch(HibernateException ex){
			throw new InfrastructureException(ex);
		}finally{
			HibernateUtil.commitTransaction();
			HibernateUtil.closeSession();
		}
		LOGGER.info("---------------[OUT] retrieveBillerDirectURL method -------------");
		LOGGER.debug("---------------[OUT] retrieveBillerDirectURL method -------------");
		return billerDirectResult;
	}
	@Override
	public boolean deleteBillerDirectUrl(BillerDirectContract contract)	throws Exception {
		// TODO Auto-generated method stub
		List<Integer> urlIds = putDataInList(contract.getDeleteURL());
		LOGGER.info("In clause value is "+urlIds);
		boolean result = Boolean.FALSE;
		try{
			HibernateUtil.beginTransaction();
			Query query = HibernateUtil.getSession().createQuery("delete SapPortlet where id in (:deleteURLList)");
			int rowsDeleted = query.setParameterList("deleteURLList", urlIds).executeUpdate();
			LOGGER.info("No of rows deleted "+rowsDeleted);
			HibernateUtil.commitTransaction();
			result = Boolean.TRUE;
		}catch (Exception e) {
			result = Boolean.FALSE;
			throw new InfrastructureException(e);
		}finally{
			HibernateUtil.closeSession();
		}

		return result;
	}
	private List<Integer> putDataInList(String data)
	{
		List<Integer> list = null;
		list = new ArrayList<Integer>();
		StringTokenizer st=new StringTokenizer(data, ",");
		while(st.hasMoreTokens())
			{
				list.add(new Integer(st.nextToken().toString()));
			}
		return list;
	}
	@Override
	public boolean deleteBillerDirectSubRowUrl(BillerDirectContract contract)
			throws Exception {
		List<Integer> subRowUrlIds = putDataInList(contract.getDeleteSubRowURLId());
		LOGGER.info("In clause value is "+subRowUrlIds);
		boolean result = Boolean.FALSE;
		try{
			HibernateUtil.beginTransaction();
			Query query = HibernateUtil.getSession().createSQLQuery("delete from SAP_PORTLET_URL where id in (:deleteURLList)");
			int rowsDeleted = query.setParameterList("deleteURLList", subRowUrlIds).executeUpdate();
			LOGGER.info("No of rows deleted "+rowsDeleted);
			HibernateUtil.commitTransaction();
			result = Boolean.TRUE;
		}catch (Exception e) {
			result = Boolean.FALSE;
			throw new InfrastructureException(e);
		}finally{
			HibernateUtil.closeSession();
		}

		return result;
	}
	@Override
	public BillerDirectResult retrieveSupportedLocaleList() throws Exception {
		BillerDirectResult billerDirectResult = new BillerDirectResult();
		try{
			HibernateUtil.beginTransaction();
			Query slQuery = HibernateUtil.getSession().createSQLQuery("select * from SUPPORTED_LOCALE ORDER BY SUPPORTED_LOCALE_ID");
			List slList = slQuery.list();
			Map<String, String> languageMap = new LinkedHashMap<String, String>();
			if(slList != null && slList.size() > 0) {
				LOGGER.info("Supported Locale List size is coming as "+slList.size());
				for(int i = 0; i < slList.size(); i++){
					Object[] row = (Object[]) slList.get(i);
					String localeName = (String)row[1];
					String localeCode = (String)row[2];
					languageMap.put(localeCode, localeName);
				}
			}
			billerDirectResult.setLanguageMap(languageMap);
			HibernateUtil.commitTransaction();
		}catch (Exception e) {
			throw new InfrastructureException(e);
		}finally{
			HibernateUtil.closeSession();
		}
		
		return billerDirectResult;
	}
}
