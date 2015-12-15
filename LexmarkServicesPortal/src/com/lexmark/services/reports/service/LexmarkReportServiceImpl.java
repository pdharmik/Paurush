package com.lexmark.services.reports.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import com.lexmark.contract.DocumentDeleteContract;
import com.lexmark.contract.ReportInstanceListContract;
import com.lexmark.contract.ReportListContract;
import com.lexmark.contract.ScheduleReportContract;
import com.lexmark.domain.ReportSchedule;
import com.lexmark.domain.ReportScheduleParameter;
import com.lexmark.result.ScheduleReportResult;
import com.lexmark.service.api.CustomerReportService;
import com.lexmark.service.impl.real.jdbc.HibernateUtil;
import com.lexmark.service.impl.real.jdbc.InfrastructureException;
import com.lexmark.services.reports.bean.DocDelDownloadInfo;
import com.lexmark.services.reports.bean.DocListInfo;
import com.lexmark.services.reports.bean.DocumentBean;
import com.lexmark.services.reports.bean.DocumentInformation;
import com.lexmark.services.reports.bean.DocumentList;
import com.lexmark.services.reports.bean.ReportListOutput;
import com.lexmark.services.reports.bean.ReportListRecord;
import com.lexmark.services.reports.bean.ScheduleReportInfoBean;
import com.lexmark.services.reports.util.EncryptionUtil;
import com.lexmark.services.reports.util.LexmarkReportUtil;
import com.lexmark.util.LocaleUtil;
import com.lexmark.util.TimezoneUtil;

public class LexmarkReportServiceImpl implements LexmarkReportService {

	private static Logger logger = LogManager.getLogger(LexmarkReportServiceImpl.class);

	public static String createDocURL = LexmarkReportUtil.getConfigProperties().getProperty("imageNow.createDocURL");
	public static String listDocURL = LexmarkReportUtil.getConfigProperties().getProperty("imageNow.listDocURL");
	public static String deleteDocURL = LexmarkReportUtil.getConfigProperties().getProperty("imageNow.deleteDocURL");
	public static String downloadDocURL = LexmarkReportUtil.getConfigProperties().getProperty("imageNow.downloadDocURL");

	public static String runNowURL = LexmarkReportUtil.getConfigProperties().getProperty("runNowURL");

	private static final String SQL_GET_REPORT_ATTRIBUTE_ID_BY_SCHEDULE_ID = "select rd.REPORT_SOURCE_ID,rd.NAME,rd.IS_MDM from REPORT_DEFINITION rd, REPORT_SCHEDULE rs where rd.REPORT_DEFINITION_ID = rs.report_definition_id and rs.REPORT_SCHEDULE_ID= :id";

	private static final String GET_JOB_BATCH_ID = "select JOB_BATCH_ID_SEQ.nextval as JOB_BATCH_ID from dual";

	private static final String SQL_GET_CHL_BY_USER_NUMBER = "select CHL_NODE_ID from SERVICES_USER  WHERE USERNUMBER = :usernumber";

	private static final String SQL_INSERT_SCHEDULE_INSTANCE_PARAMETERS = "insert into schedule_instance_parameters(report_schedule_id, name, value) values(:scheduleId, :name,:value)";
	
	private static final String SQL_GET_BO_FILENAME_PARAMETERS = "select created_date,file_name,batch_id,file_type,report_type from report_job  where document_id = :documentId";
	
	public static SimpleDateFormat dateformatter = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");

	@Autowired
	private CustomerReportService customerReportService;
	
	@Override
	public String createDocument(DocumentInformation docInfo) throws Exception {

		logger.info("Start-------LexmarkReportServiceImpl-----createDocument");
		HttpClient client = HttpClientBuilder.create().build();
		HttpPost httprequest = new HttpPost(LexmarkReportUtil.encryptURL(createDocURL));
		String jsonInput = LexmarkReportUtil.objectToJson(docInfo);
		ByteArrayEntity entity = new ByteArrayEntity(jsonInput.getBytes());
		entity.setContentType("application/json");
		httprequest.setEntity(entity);
		httprequest.addHeader("username", EncryptionUtil.encryptData(LexmarkReportUtil.USERNAME));
		httprequest.addHeader("password", EncryptionUtil.encryptData(LexmarkReportUtil.PASSWORD));
		httprequest.addHeader("baseUrl", EncryptionUtil.encryptData(LexmarkReportUtil.BASEURL));
		HttpResponse httpresp = client.execute(httprequest);

		if (httpresp.getStatusLine().getStatusCode() == 200) {
			return EntityUtils.toString(httpresp.getEntity());
		} else {
			throw new Exception();
		}
	}

	@Override
	public List<DocumentBean> listDocumentByDefinitionId(DocListInfo docListInfo) throws Exception {

		logger.info("Start-------LexmarkReportServiceImpl-----listDocumentByDefinitionId");
		HttpClient client = HttpClientBuilder.create().build();
		client = HttpClientBuilder.create().build();
		HttpPost request = new HttpPost(LexmarkReportUtil.encryptURL(listDocURL));

		String jsonInput = null;
		jsonInput = LexmarkReportUtil.objectToJson(docListInfo);

		ByteArrayEntity entity = new ByteArrayEntity(jsonInput.getBytes());
		entity.setContentType("application/json");

		request.setEntity(entity);
		request.addHeader("username", EncryptionUtil.encryptData(LexmarkReportUtil.USERNAME));
		request.addHeader("password", EncryptionUtil.encryptData(LexmarkReportUtil.PASSWORD));
		request.addHeader("baseUrl", EncryptionUtil.encryptData(LexmarkReportUtil.BASEURL));
		HttpResponse response = null;

		DocumentList documentList = null;

		response = client.execute(request);
		String result = LexmarkReportUtil.processHttpResponse(response);
		//logger.debug("List Result *********************************"+result);
		documentList = (DocumentList)LexmarkReportUtil.getXmltoObject(result, 1);
		
		if (response.getStatusLine().getStatusCode() != 200)
			throw new Exception();

		logger.info("End-------LexmarkReportServiceImpl-----listDocumentByDefinitionId");
		return documentList.getDocumentList();
	}

	@Override
	public void deleteDocument(DocDelDownloadInfo docDelDownloadInfo) throws Exception {
		logger.info("Start-------LexmarkReportServiceImpl-----deleteDocument");
		HttpClient client = HttpClientBuilder.create().build();
		HttpPost httprequest = new HttpPost(LexmarkReportUtil.encryptURL(deleteDocURL));
		String jsonInput = LexmarkReportUtil.objectToJson(docDelDownloadInfo);
		logger.info("jsonInput for delete--------------------" + jsonInput);
		ByteArrayEntity entity = new ByteArrayEntity(jsonInput.getBytes());
		entity.setContentType("application/json");
		httprequest.setEntity(entity);
		httprequest.addHeader("username", EncryptionUtil.encryptData(LexmarkReportUtil.USERNAME));
		httprequest.addHeader("password", EncryptionUtil.encryptData(LexmarkReportUtil.PASSWORD));
		httprequest.addHeader("baseUrl", EncryptionUtil.encryptData(LexmarkReportUtil.BASEURL));
		HttpResponse httpresp = client.execute(httprequest);
		if (httpresp.getStatusLine().getStatusCode() != 200)
			throw new Exception();
		logger.info("End-------LexmarkReportServiceImpl-----deleteDocument");
	}

	@Override
	public HttpResponse downloadDocument(DocDelDownloadInfo docDelDownloadInfo) throws Exception {
		logger.info("Start-------LexmarkReportServiceImpl-----downloadDocument");
		HttpClient client = HttpClientBuilder.create().build();
		HttpPost httprequest = new HttpPost(LexmarkReportUtil.encryptURL(downloadDocURL));
		String jsonInput = LexmarkReportUtil.objectToJson(docDelDownloadInfo);
		logger.info("jsonInput for download--------------------" + jsonInput);
		ByteArrayEntity entity = new ByteArrayEntity(jsonInput.getBytes());
		entity.setContentType("application/json");
		httprequest.setEntity(entity);
		httprequest.addHeader("Username", EncryptionUtil.encryptData(LexmarkReportUtil.USERNAME));
		httprequest.addHeader("Password", EncryptionUtil.encryptData(LexmarkReportUtil.PASSWORD));
		httprequest.addHeader("BaseUrl", EncryptionUtil.encryptData(LexmarkReportUtil.BASEURL));
		HttpResponse resp = client.execute(httprequest);
		if (resp.getStatusLine().getStatusCode() != 200)
			throw new Exception();

		logger.info("End-------LexmarkReportServiceImpl-----downloadDocument");
		return resp;
	}

	@Override
	public void saveManualReport(String docId, double fileSize, Date date, DocumentInformation docInfo) {
		logger.info("Start-------LexmarkReportServiceImpl-----saveManualReport");

		String queryStr = "insert into report_job (REPORT_JOB_ID,BATCH_ID, MDM_ID,MDM_LEVEL,LEGAL_NAME,STATUS_CODE,FILE_TYPE,DOCUMENT_ID,FILE_SIZE,REPORT_TYPE, CREATED_DATE, IS_DELETED, FILE_NAME, REPORT_DEFINITION_ID,SCHEDULE_CREATION_DATE) values (JOB_RUNLOG_ID_SEQ.NEXTVAL,JOB_BATCH_ID_SEQ.NEXTVAL,:mdmId,:mdmLevel,:legalName,:statusCode,:fileType,:docId,:fileSize,:reportType ,:createdDate, :isDeleted,:fileName, :reportDefId, :scheduleCreationDate)";
		try {
			Session session = HibernateUtil.getSession();
			Transaction transaction = session.getTransaction();
			transaction.begin();
			Query query = session.createSQLQuery(queryStr);

			query.setParameter("mdmId", docInfo.getPropertyMap().get(LexmarkReportUtil.MDM_ID));
			query.setParameter("mdmLevel", docInfo.getPropertyMap().get(LexmarkReportUtil.MDM_LEVEL));
			query.setParameter("legalName", docInfo.getPropertyMap().get(LexmarkReportUtil.LEGAL_NAME));
			query.setParameter("statusCode", "Complete");
			query.setParameter("fileType", docInfo.getPropertyMap().get(LexmarkReportUtil.ACONTENT_TYPE));
			query.setParameter("docId", docId);
			query.setParameter("fileSize", fileSize);
			query.setParameter("reportType", "MU");
			query.setParameter("createdDate", date);
			query.setParameter("scheduleCreationDate",date); // same date
			query.setParameter("isDeleted", "F");
			query.setParameter("fileName", docInfo.getPropertyMap().get(LexmarkReportUtil.FILE_NAME));
			query.setParameter("reportDefId", docInfo.getPropertyMap().get(LexmarkReportUtil.DEFINITION_ID));

			query.executeUpdate();
			transaction.commit();

		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		} finally {
			HibernateUtil.closeSession();
		}
		logger.info("End-------LexmarkReportServiceImpl-----saveManualReport");

	}

	private static final String SQL_GET_MANU_UPLOADED_REPORTS_BY_DEFINITION_ID_MU = "select report_definition_name, report_definition_id, report_creation_date, report_job, report_type, file_name, file_size,document_id, file_type,category_id   from ( "
			+ "select nvl(dl.name, rd.name) report_definition_name, rd.report_definition_id, TO_CHAR(RJ.CREATED_DATE, 'DD-MM-YYYY HH24:MI:SS')  report_creation_date, RJ.REPORT_JOB_ID report_job, "
			+ " RJ.REPORT_TYPE report_type, RJ.FILE_NAME file_name,RJ.FILE_SIZE file_size ,RJ.DOCUMENT_ID document_id,RJ.FILE_TYPE file_type,c.category_id category_id"
			+ " from supported_locale "
			+ " sl left join definition_locale dl on (sl.supported_locale_id = dl.supported_locale_id and sl.supported_locale_code = :locationCode) right join"
			+ " report_definition rd on (rd.report_definition_id = dl.report_definition_id), REPORT_JOB RJ,"
			+ " category c where rd.category_id = c.category_id"
			+ " and RJ.is_deleted ='F'"
			+ " and RJ.report_type= 'MU'"
			+ " and RJ.report_definition_id   = rd.report_definition_id"
			+ " and rd.report_definition_id = (select rc.report_definition_id FROM report_customers rc where (rc.mdm_id = :mdmId or rc.mdm_id is null) and "
			+ " (rc.mdm_level = :mdmLevel or rc.mdm_level is null) and rc.report_definition_id = :definitionId  OR (rc.restrict_report = :restrict_reportExclude "
  +"AND (rc.mdm_id             != :mdmId)"
  +"AND rc.report_definition_id = :definitionId))" + " order by report_creation_date desc" + " ) where rownum > 0 and rownum <=  13";
	
	

	public ReportListOutput retrieveManuUploadedReportsList(ReportListContract contract) throws Exception {

		ReportListOutput result = new ReportListOutput();

		String language = LocaleUtil.getSupportLocaleCode(contract.getLocale());
		
		try {
			SQLQuery query = HibernateUtil.getSession().createSQLQuery(SQL_GET_MANU_UPLOADED_REPORTS_BY_DEFINITION_ID_MU);

			query.setParameter("locationCode", language);
			query.setParameter("mdmLevel", contract.getMdmLevel());
			query.setParameter("mdmId", contract.getMdmId());
			query.setParameter("definitionId", contract.getReportDefinitionId());
			query.setParameter("restrict_reportExclude", "F");

			List list = query.list();
			if (list != null && list.size() > 0) {
				HashMap<Object, ReportListRecord> reportListRows = new LinkedHashMap<Object, ReportListRecord>();
				for (int i = 0; i < list.size(); i++) {
					Object[] rows = (Object[]) list.get(i);
					Object docId = rows[7].toString();
					ReportListRecord reportRow;
					if (docId != null) {
						reportRow = new ReportListRecord();
						reportRow.setReportName((String) rows[0]);
						reportRow.setReportDefinitionId(new Integer(rows[1].toString()));
						String createdDateString = rows[2].toString();
						SimpleDateFormat dateformatter = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
						Date createdDate = dateformatter.parse(createdDateString);
						reportRow.setCreatedDate(createdDate);
				
						reportRow.setReportJobId(new Integer(rows[3].toString()));
						reportRow.setReportType((String) rows[4]);
						reportRow.setFilename((String) rows[5]);
						reportRow.setFileSize(new Integer(rows[6].toString()));
						reportRow.setDocumentId((String) rows[7]);
						reportRow.setFileType((String) rows[8]);
						reportRow.setCategoryId(String.valueOf(rows[9]));
						reportRow.setCategoryId(new Integer(rows[9].toString()).toString());
						reportListRows.put(docId, reportRow);
					}

				}
				for (Map.Entry<Object, ReportListRecord> entry : reportListRows.entrySet()) {
					result.getReportListRows().add(entry.getValue());
				}

			}
		} catch (HibernateException ex) {
			ex.printStackTrace();
			throw new InfrastructureException(ex);
		} finally {
			HibernateUtil.closeSession();
		}
		return result;
	}

	@Override
	public void deleteReport(String docId) {
		try {
			HibernateUtil.beginTransaction();
			
			Query query = HibernateUtil.getSession().createSQLQuery("update REPORT_JOB set IS_DELETED = 'T'" + " where DOCUMENT_ID  = :docId");
			query.setParameter("docId", docId);
			int result = query.executeUpdate();
			if(result == 1){
			logger.info("Report Delete status set to true");
			}
			
			HibernateUtil.commitTransaction();
		}
		catch(HibernateException e){
			if (logger.isDebugEnabled()) {
				logger.debug(e.getMessage());
			}
			throw e;
		} 
		finally {
			HibernateUtil.closeSession();
		}

	}


	@Override
	public ScheduleReportInfoBean scheduleReport(ScheduleReportInfoBean scheduleReportInfoBean) throws Exception {
		logger.info("Start-------LexmarkReportServiceImpl-----runNowReport");
		HttpClient client = HttpClientBuilder.create().build();
		HttpPost httprequest = new HttpPost(LexmarkReportUtil.encryptURL(runNowURL));
		String jsonInput = LexmarkReportUtil.objectToJson(scheduleReportInfoBean);
		logger.info("jsonInput for run now--------------------" + jsonInput);
		ByteArrayEntity entity = new ByteArrayEntity(jsonInput.getBytes());
		entity.setContentType("application/json");
		httprequest.setEntity(entity);

		HttpResponse resp = client.execute(httprequest);
		if (resp.getStatusLine().getStatusCode() != 200) {
			logger.error("resp.getStatusLine().getStatusCode()" + resp.getStatusLine().getStatusCode());
			throw new Exception();
		}

		logger.info("End-------LexmarkReportServiceImpl-----runNowReport");
		String jsonResp = EntityUtils.toString(resp.getEntity());
		logger.info("Response Entity" + jsonResp);

		return LexmarkReportUtil.jsonToObject(jsonResp, ScheduleReportInfoBean.class);

	}

	public void saveReportInfo(ScheduleReportInfoBean scBean, ScheduleReportResult result, String jobBatchId) {

		String queryStr = "insert into report_job (REPORT_JOB_ID,MDM_ID,MDM_LEVEL,REPORT_SCHEDULE_ID,STATUS_CODE,FILE_TYPE,BO_OBJECT_ID,FILE_LOCATION,REPORT_TYPE,IS_DELETED,REPORT_DEFINITION_ID, BATCH_ID, FILE_NAME,SCHEDULE_CREATION_DATE) "
				+ "values (JOB_RUNLOG_ID_SEQ.NEXTVAL,:mdmId,:mdmlevel,:scheduleId, 'Pending',:fileType,:boObjectId,:fileLocation,'BO','F',:repDefId,:jobBatchId,:fileName,:scheduleCreationDate)";
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.getTransaction();
		transaction.begin();
		Query query = session.createSQLQuery(queryStr);

		query.setParameter("mdmId", result.getSchedule().getMdmId());
		query.setParameter("mdmlevel", result.getSchedule().getMdmLevel());
		query.setParameter("scheduleId", result.getSchedule().getId());
		query.setParameter("fileType", scBean.getFormatType());
		query.setParameter("boObjectId", scBean.getRecurrenceSiId());
		query.setParameter("fileLocation", scBean.getNfsfileLocation());
		query.setParameter("repDefId", result.getSchedule().getReportDefinitionId());
		query.setParameter("jobBatchId", jobBatchId);
		query.setParameter("fileName", scBean.getRepDefName());
		if(scBean.getStartDate() == null){
			query.setParameter("scheduleCreationDate",TimezoneUtil.getCurrentDateInUTC());
		}else{
			query.setParameter("scheduleCreationDate",scBean.getStartDate());
		}
		query.executeUpdate();
		transaction.commit();

	}

	public String getJobBatchId() {
		try {
			SQLQuery query = HibernateUtil.getSession().createSQLQuery(GET_JOB_BATCH_ID);

			return String.valueOf(query.list().get(0));

		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		} finally {
			HibernateUtil.closeSession();
		}
	}

	@Override
	public ScheduleReportInfoBean setReportAttributes(Integer id, ScheduleReportInfoBean scheduleReportInfoBean) {
		try {
			logger.info("Retrieving attributes of Schedule");
			SQLQuery query = HibernateUtil.getSession().createSQLQuery(SQL_GET_REPORT_ATTRIBUTE_ID_BY_SCHEDULE_ID);
			query.setParameter("id", id);
			List list = query.list();
			if (list != null && list.size() == 1) {
				Object[] row = (Object[]) list.get(0);

				logger.info("reportSourceId" + String.valueOf(row[0]));
				logger.info("repDefName" + String.valueOf(row[1]));
				logger.info("reportISMDM" + String.valueOf(row[2]));

				scheduleReportInfoBean.setReportSourceId(String.valueOf(row[0]));
				scheduleReportInfoBean.setRepDefName(String.valueOf(row[1]));
				scheduleReportInfoBean.setIsMDM(String.valueOf(row[2]));
			}

			return scheduleReportInfoBean;
		} catch (HibernateException ex) {
			ex.printStackTrace();
			throw new InfrastructureException(ex);
		} finally {
			HibernateUtil.closeSession();
		}
	}

	public static String retrieveCHL(String usernumber) {
		try {
			SQLQuery query = HibernateUtil.getSession().createSQLQuery(SQL_GET_CHL_BY_USER_NUMBER);
			query.setParameter("usernumber", usernumber);

			String output = null;
			if (query.list().size() > 0) {
				output = (String) query.list().get(0);
			}
			if (output == null)
				output = "NOVALUE";

			logger.debug("CHL value****************************************" + output);

			return output;
		} catch (HibernateException ex) {
			ex.printStackTrace();
			throw new InfrastructureException(ex);
		} finally {
			HibernateUtil.closeSession();
		}
	}

	@Override
	public void saveScheduleInstanceParameters(String scheduleId, Map<String, String> parameterMap) {
		logger.debug("Inside saveScheduleInstanceParameters");
		try {
			Session session = HibernateUtil.getSession();
			Transaction transaction = session.getTransaction();
			transaction.begin();
			logger.debug("parameterMap.entrySet().size(): " + parameterMap.entrySet().size());
			for (Entry<String, String> entry : parameterMap.entrySet()) {
				logger.debug("entry.getKey(): " + entry.getKey());
				Query query = session.createSQLQuery("insert into schedule_instance_parameters(report_schedule_id, name, value) values(" + scheduleId + ", '" + entry.getKey() + "','"
						+ entry.getValue() + "')");
				query.executeUpdate();

			}
			transaction.commit();
			logger.debug("Inside saveScheduleInstanceParameters  after for");
		} catch (HibernateException ex) {
			ex.printStackTrace();
			throw new InfrastructureException(ex);
		} finally {
			HibernateUtil.closeSession();
		}
	}

	@Override
	public ReportListOutput retrieveScheduledReportsListEmployee(ReportListContract contract) {
		ReportListOutput reportListOutput = new ReportListOutput();
		String sqlQuery = "SELECT RJ.REPORT_SCHEDULE_ID,  RJ.BATCH_ID, RJ.FILE_NAME, RJ.STATUS_CODE,DOCUMENT_ID,RJ.FILE_SIZE, RJ.FILE_TYPE, TO_CHAR(RJ.CREATED_DATE, 'DD-MM-YYYY HH24:MI:SS'),TO_CHAR(RJ.SCHEDULE_CREATION_DATE, 'DD-MM-YYYY HH24:MI:SS'),report_parameters_text1(RJ.REPORT_SCHEDULE_ID) as parameterlist ,RJ.report_job_id, RD.REPORT_DEFINITION_ID, RD.CATEGORY_ID from report_job rj, report_definition rd where rd.REPORT_DEFINITION_ID = rj.REPORT_DEFINITION_ID and rj.is_deleted = 'F' and rj.REPORT_SCHEDULE_ID in (select report_schedule_id from report_schedule where REPORT_DEFINITION_ID in (select report_definition_id from report_definition rd, category c, CATEGORY_LOCALE CL, SUPPORTED_LOCALE SL  where rd.CATEGORY_ID = c.CATEGORY_ID and SL.SUPPORTED_LOCALE_ID = CL.SUPPORTED_LOCALE_ID and c.CATEGORY_ID = cl.CATEGORY_ID AND SL.SUPPORTED_LOCALE_CODE = :local and rd.is_deleted = 'F' and c.is_deleted = 'F') and REPORT_DEFINITION_ID = :definitionid and usernumber = :usernumber)";
		logger.debug("SQL query: " + sqlQuery);
		Map<String, ReportListRecord> recordMap = new HashMap<String, ReportListRecord>();
		ReportListRecord record = null;
		try {
			SQLQuery query = HibernateUtil.getSession().createSQLQuery(sqlQuery);
			query.setParameter("usernumber", contract.getUserNumber());
			query.setParameter("definitionid", contract.getReportDefinitionId());
			query.setParameter("local", contract.getLocale().getLanguage());
			logger.debug("SQL query: " + sqlQuery);
			logger.debug("contract.getReportDefinitionId(): " + contract.getReportDefinitionId());
			logger.debug("contract.getUserNumber(): " + contract.getUserNumber());
			logger.debug("contract.getLocale(): " + contract.getLocale().getLanguage());
			 
			List rows = query.list();
			Iterator it = rows.iterator();
			while (it.hasNext()) {
				Object[] row = (Object[]) it.next();
				record = new ReportListRecord();
				record.setReportScheduleId(String.valueOf(row[0]));
				record.setBatchId(String.valueOf(row[1]));
				record.setFilename(String.valueOf(row[2]));
				record.setStatus(String.valueOf(row[3]));
				record.setFileType(String.valueOf(row[6]));

				if (record.getFileType().equalsIgnoreCase("XLS")) {
					if (row[4] != null) {
						record.setDocumentId(String.valueOf(row[4]));
					}
				} else {
					if (row[4] != null) {
						record.setDocumentId2(String.valueOf(row[4]));
					}
				}

				try {
					record.setFileSize(Integer.parseInt(String.valueOf(row[5])));
				} catch (Exception e) {
					record.setFileSize(0);
				}
				if(row[7]!= null){
					String createdDateString = row[7].toString();
					Date createdDate = dateformatter.parse(createdDateString);
					record.setCreatedDate(createdDate);
					}
					if(row[8]!=null){
					String scheduleCreatedDateString = row[8].toString();
					Date schedulecreatedDate = dateformatter.parse(scheduleCreatedDateString);
					record.setScheduleCreateDate(schedulecreatedDate);
					}
				if (row[9] != null) {
					record.setParameterList((String) row[9]);
				}
				try {
					record.setReportJobId(Integer.parseInt(String.valueOf(row[10])));
				} catch (Exception e) {
					record.setReportJobId(0);
				}

				record.setReportDefinitionId(Integer.parseInt(String.valueOf(row[11])));
				record.setCategoryId(String.valueOf(row[12]));

				recordMap = populateRecordMap(recordMap, record);
			}
			Set<Entry<String, ReportListRecord>> entries = recordMap.entrySet();
			for (Entry<String, ReportListRecord> entry : entries) {
				reportListOutput.getReportListRows().add(entry.getValue());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new InfrastructureException(ex);
		} finally {
			HibernateUtil.closeSession();
		}
		return reportListOutput;
	}

	private Map<String, ReportListRecord> populateRecordMap(Map<String, ReportListRecord> recordMap, ReportListRecord record) {
		if (recordMap.containsKey(record.getBatchId())) {
			if (record.getDocumentId() == null) {
				record.setDocumentId(recordMap.get(record.getBatchId()).getDocumentId());
			}
			if (record.getDocumentId2() == null) {
				record.setDocumentId2(recordMap.get(record.getBatchId()).getDocumentId2());
			}
		}
		if (record.getDocumentId() != null && record.getDocumentId2() != null) {
			record.setStatus("Complete");
		} else {
			record.setStatus("Pending");
		}
		recordMap.put(record.getBatchId(), record);
		return recordMap;
	}

	@Override
	public ReportListOutput retrieveScheduledReportsList(ReportListContract contract) {
		ReportListOutput reportListOutput = new ReportListOutput();
		
	String sqlQuery = "SELECT RJ.REPORT_SCHEDULE_ID,  RJ.BATCH_ID,  RJ.FILE_NAME,  RJ.STATUS_CODE,  rj.DOCUMENT_ID,  RJ.FILE_SIZE, RJ.FILE_TYPE, TO_CHAR(RJ.CREATED_DATE, 'DD-MM-YYYY HH24:MI:SS'),TO_CHAR(RJ.SCHEDULE_CREATION_DATE, 'DD-MM-YYYY HH24:MI:SS'),  report_parameters_text1(RJ.REPORT_SCHEDULE_ID) AS parameterlist ,  RJ.report_job_id,  RD.REPORT_DEFINITION_ID,  RD.CATEGORY_ID FROM report_job rj,  report_definition rd,report_schedule rs,supported_locale sl,    definition_locale dl "   
				+" WHERE rs.report_definition_id = rd.report_definition_id   AND rd.report_definition_id = :definitionId   AND rs.usernumber   = :userNumber   AND dl.supported_locale_id    = sl.supported_locale_id(+)   AND rd.report_definition_id   = dl.report_definition_id(+)   AND rs.report_schedule_id     = rj.report_schedule_id "
				 +" AND sl.supported_locale_code  = :locationCode   AND rd.is_deleted   = 'F'  AND rj.is_deleted='F'"; 
		
		Map<String, ReportListRecord> recordMap = new HashMap<String, ReportListRecord>();
		ReportListRecord record = null;
		try {
			SQLQuery query = HibernateUtil.getSession().createSQLQuery(sqlQuery);
			
			query.setParameter("userNumber", contract.getUserNumber());
			query.setParameter("definitionId", contract.getReportDefinitionId());
			query.setParameter("locationCode", contract.getLocale().getLanguage());
			
			logger.debug("SQL query: " + sqlQuery);
			logger.debug("contract.getReportDefinitionId(): " + contract.getReportDefinitionId());
			logger.debug("contract.getMdmId(): " + contract.getMdmId());
			logger.debug("contract.getLocale(): " + contract.getLocale().getLanguage());
			
			List rows = query.list();
			Iterator it = rows.iterator();
			while (it.hasNext()) {
				Object[] row = (Object[]) it.next();
				record = new ReportListRecord();
				record.setReportScheduleId(String.valueOf(row[0]));
				record.setBatchId(String.valueOf(row[1]));
				record.setFilename(String.valueOf(row[2]));
				record.setStatus(String.valueOf(row[3]));
				record.setFileType(String.valueOf(row[6]));

				if (record.getFileType().equalsIgnoreCase("XLS")) {
					if (row[4] != null) {
						record.setDocumentId(String.valueOf(row[4]));
					}
				} else {
					if (row[4] != null) {
						record.setDocumentId2(String.valueOf(row[4]));
					}
				}

				try {
					record.setFileSize(Integer.parseInt(String.valueOf(row[5])));
				} catch (Exception e) {
					record.setFileSize(0);
				}

				if(row[7]!= null){
					String createdDateString = row[7].toString();
					Date createdDate = dateformatter.parse(createdDateString);
					record.setCreatedDate(createdDate);
					}
					if(row[8]!=null){
					String scheduleCreatedDateString = row[8].toString();
					Date schedulecreatedDate = dateformatter.parse(scheduleCreatedDateString);
					record.setScheduleCreateDate(schedulecreatedDate);
					}
				if (row[9] != null) {
					record.setParameterList((String) row[9]);
				}
				try {
					record.setReportJobId(Integer.parseInt(String.valueOf(row[10])));
				} catch (Exception e) {
					record.setReportJobId(0);
				}

				record.setReportDefinitionId(Integer.parseInt(String.valueOf(row[11])));
				record.setCategoryId(String.valueOf(row[12]));

				recordMap = populateRecordMap(recordMap, record);
			}
			Set<Entry<String, ReportListRecord>> entries = recordMap.entrySet();
			for (Entry<String, ReportListRecord> entry : entries) {
				reportListOutput.getReportListRows().add(entry.getValue());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new InfrastructureException(ex);
		} finally {
			HibernateUtil.closeSession();
		}
		return reportListOutput;
	}

	@Override
	public ReportListOutput retrieveTopTenReportsList(ReportListContract contract) {
		ReportListOutput reportListOutput = new ReportListOutput();
		String sqlQuery = "SELECT REPORT_SCHEDULE_ID, BATCH_ID,NVL((SELECT DISTINCT dl.name "+
				" FROM report_definition rd, "+
				" definition_locale dl, "+
				"   SUPPORTED_LOCALE sl "+
				" WHERE rd.report_definition_id = dl.report_definition_id "+
				" AND rd.report_definition_id   = REPORT_JOB.REPORT_DEFINITION_ID "+
				" AND sl.SUPPORTED_LOCALE_CODE  = :local "+
				" AND dl.SUPPORTED_LOCALE_ID    = sl.SUPPORTED_LOCALE_ID), (SELECT name "+
				" FROM report_definition where "+
				" report_definition_id   = REPORT_JOB.REPORT_DEFINITION_ID)) "+
				" AS "+
				"  report_name, STATUS_CODE, DOCUMENT_ID,FILE_SIZE, FILE_TYPE, TO_CHAR(CREATED_DATE, 'DD-MM-YYYY HH24:MI:SS'),TO_CHAR(SCHEDULE_CREATION_DATE, 'DD-MM-YYYY HH24:MI:SS'),REPORT_TYPE , report_parameters_text1(REPORT_SCHEDULE_ID) as parameterlist, report_job_id FROM REPORT_JOB "+ 
				"where DOCUMENT_ID IS NOT NULL AND REPORT_TYPE IN ('MU','BO') AND IS_DELETED = 'F' "+ 
				"and REPORT_JOB_ID in ( "+ 
				"(  "+ 
				"select report_job_id   from ( select nvl(dl.name, rd.name) report_definition_name, rd.report_definition_id, RJ.CREATED_DATE " +
				"report_creation_date, RJ.REPORT_JOB_ID,  RJ.REPORT_TYPE report_type, RJ.FILE_NAME " +
				"file_name,RJ.FILE_SIZE file_size ,RJ.DOCUMENT_ID document_id,RJ.FILE_TYPE file_type," +
				"c.category_id category_id from supported_locale  sl left join definition_locale dl on " +
				"(sl.supported_locale_id = dl.supported_locale_id and sl.supported_locale_code = :local) right join " +
				"report_definition rd on (rd.report_definition_id = dl.report_definition_id), " +
				"REPORT_JOB RJ, category c where rd.category_id = c.category_id and RJ.is_deleted ='F' and RJ.report_type= 'MU' " +
				"and RJ.report_definition_id  = rd.report_definition_id and rd.report_definition_id IN " +
				"(select rc.report_definition_id FROM report_customers rc where (rc.mdm_id = :mdmId or rc.mdm_id is null) " +
				"and  (rc.mdm_level = :mdmLevel or rc.mdm_level is null)) order by report_creation_date desc )"+
				") "+ 
				"UNION "+ 
				"( "+ 
				"SELECT RJ.report_job_id from report_job rj, report_definition rd where rd.REPORT_DEFINITION_ID = rj.REPORT_DEFINITION_ID and "+ 
				"rj.is_deleted = 'F' and rj.REPORT_SCHEDULE_ID in (select report_schedule_id from report_schedule where REPORT_DEFINITION_ID in "+ 
				"(select report_definition_id from report_definition rd, category c, CATEGORY_LOCALE CL, SUPPORTED_LOCALE SL  "+ 
				"where rd.CATEGORY_ID = c.CATEGORY_ID and SL.SUPPORTED_LOCALE_ID = CL.SUPPORTED_LOCALE_ID and c.CATEGORY_ID = cl.CATEGORY_ID AND "+ 
				"SL.SUPPORTED_LOCALE_CODE = :local and rd.is_deleted = 'F' and c.is_deleted = 'F') and (mdm_id = :mdmId or usernumber=:usernumber) "+ 
				") "+ 
				") "+ 
				") "+ 
				"order by  SCHEDULE_CREATION_DATE,REPORT_JOB_ID ASC ";
		
		logger.debug("SQL query: " + sqlQuery);
		Map<String, ReportListRecord> recordMap = new HashMap<String, ReportListRecord>();
		ReportListRecord record = null;
		try {
			SQLQuery query = HibernateUtil.getSession().createSQLQuery(sqlQuery);
			
			 query.setParameter("mdmId", contract.getMdmId());
			 query.setParameter("local", LocaleUtil.getSupportLocaleCode(contract.getLocale()));
			 query.setParameter("mdmLevel", contract.getMdmLevel());
			 query.setParameter("usernumber",contract.getUserNumber());
			 
			logger.debug("mdmId "+contract.getMdmId());
			logger.debug("local "+ LocaleUtil.getSupportLocaleCode(contract.getLocale()));
			logger.debug("mdmLevel "+ contract.getMdmLevel());
			logger.debug("usernumber "+ contract.getUserNumber());

			logger.debug("SQL query: " + sqlQuery);
			List rows = query.list();
			Iterator it = rows.iterator();
			while (it.hasNext()) {
				Object[] row = (Object[]) it.next();
				record = new ReportListRecord();
				record.setReportScheduleId(String.valueOf(row[0]));
				record.setBatchId(String.valueOf(row[1]));
				record.setFilename(String.valueOf(row[2]));
				record.setStatus(String.valueOf(row[3]));
				record.setFileType(String.valueOf(row[6]));

				if (row[9] != null) {
					record.setReportType((String) row[9]);
				}
				if ("BO".equalsIgnoreCase(record.getReportType())) {

					if (record.getFileType().equalsIgnoreCase("XLS")) {
						if (row[4] != null) {
							record.setDocumentId(String.valueOf(row[4]));
						}
					} else {
						if (row[4] != null) {
							record.setDocumentId2(String.valueOf(row[4]));
						}
					}
				} else {
					if (row[4] != null) {
						record.setDocumentId(String.valueOf(row[4]));
					}
				}
				try {
					record.setFileSize(Integer.parseInt(String.valueOf(row[5])));
				} catch (Exception e) {
					record.setFileSize(0);
				}
				if(row[7]!= null){
				String createdDateString = row[7].toString();
				Date createdDate = dateformatter.parse(createdDateString);
				record.setCreatedDate(createdDate);
				}
				if(row[8]!=null){
				String scheduleCreatedDateString = row[8].toString();
				Date schedulecreatedDate = dateformatter.parse(scheduleCreatedDateString);
				record.setScheduleCreateDate(schedulecreatedDate);
				}
				
				if (row[10] != null) {
					record.setParameterList((String) row[10]);
				}

				try {
					record.setReportJobId(Integer.parseInt(String.valueOf(row[11])));
				} catch (Exception e) {
					record.setReportJobId(0);
				}

				if (record.getReportType().equalsIgnoreCase("BO")) {
					recordMap = populateRecordMap(recordMap, record);
				} else {
					reportListOutput.getReportListRows().add(record);
				}
			}
			Set<Entry<String, ReportListRecord>> entries = recordMap.entrySet();
			for (Entry<String, ReportListRecord> entry : entries) {
				reportListOutput.getReportListRows().add(entry.getValue());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new InfrastructureException(ex);
		} finally {
			HibernateUtil.closeSession();
		}
		return reportListOutput;
	}

	@Override
	public ReportListOutput retrieveTopTenReportsListEmployee(ReportListContract contract) {
		ReportListOutput reportListOutput = new ReportListOutput();
		String sqlQuery = "SELECT REPORT_SCHEDULE_ID, BATCH_ID,NVL((SELECT DISTINCT dl.name "+
				" FROM report_definition rd, "+
				" definition_locale dl, "+
				"   SUPPORTED_LOCALE sl "+
				" WHERE rd.report_definition_id = dl.report_definition_id "+
				" AND rd.report_definition_id   = REPORT_JOB.REPORT_DEFINITION_ID "+
				" AND sl.SUPPORTED_LOCALE_CODE  = :local "+
				" AND dl.SUPPORTED_LOCALE_ID    = sl.SUPPORTED_LOCALE_ID), (SELECT name "+
				" FROM report_definition where "+
				" report_definition_id   = REPORT_JOB.REPORT_DEFINITION_ID)) "+
				" AS "+
				"  report_name, STATUS_CODE, DOCUMENT_ID,FILE_SIZE, FILE_TYPE, TO_CHAR(CREATED_DATE, 'DD-MM-YYYY HH24:MI:SS'),TO_CHAR(SCHEDULE_CREATION_DATE, 'DD-MM-YYYY HH24:MI:SS'),REPORT_TYPE , report_parameters_text1(REPORT_SCHEDULE_ID) as parameterlist, report_job_id FROM REPORT_JOB "+ 
				"where DOCUMENT_ID IS NOT NULL AND REPORT_TYPE IN ('MU','BO') AND IS_DELETED = 'F' "+ 
				"and REPORT_JOB_ID in ( "+ 
				"(  "+ 
				"select report_job_id   from ( select nvl(dl.name, rd.name) report_definition_name, rd.report_definition_id, RJ.CREATED_DATE " +
				"report_creation_date, RJ.REPORT_JOB_ID,  RJ.REPORT_TYPE report_type, RJ.FILE_NAME " +
				"file_name,RJ.FILE_SIZE file_size ,RJ.DOCUMENT_ID document_id,RJ.FILE_TYPE file_type," +
				"c.category_id category_id from supported_locale  sl left join definition_locale dl on " +
				"(sl.supported_locale_id = dl.supported_locale_id and sl.supported_locale_code = :local) right join " +
				"report_definition rd on (rd.report_definition_id = dl.report_definition_id), " +
				"REPORT_JOB RJ, category c where rd.category_id = c.category_id and RJ.is_deleted ='F' and RJ.report_type= 'MU' " +
				"and RJ.report_definition_id  = rd.report_definition_id and rd.report_definition_id IN " +
				"(select rc.report_definition_id FROM report_customers rc where (rc.mdm_id = :mdmId or rc.mdm_id is null) " +
				"and  (rc.mdm_level = :mdmLevel or rc.mdm_level is null)) order by report_creation_date desc )"+
				") "+ 
				"UNION "+ 
				"( "+ 
				"SELECT RJ.report_job_id from report_job rj, report_definition rd where rd.REPORT_DEFINITION_ID = rj.REPORT_DEFINITION_ID and "+ 
				"rj.is_deleted = 'F' and rj.REPORT_SCHEDULE_ID in (select report_schedule_id from report_schedule where REPORT_DEFINITION_ID in "+ 
				"(select report_definition_id from report_definition rd, category c, CATEGORY_LOCALE CL, SUPPORTED_LOCALE SL  "+ 
				"where rd.CATEGORY_ID = c.CATEGORY_ID and SL.SUPPORTED_LOCALE_ID = CL.SUPPORTED_LOCALE_ID and c.CATEGORY_ID = cl.CATEGORY_ID AND "+ 
				"SL.SUPPORTED_LOCALE_CODE = :local and rd.is_deleted = 'F' and c.is_deleted = 'F') and usernumber = :usernumber) "+ 
				") "+ 
				") "+ 
				"order by  SCHEDULE_CREATION_DATE,REPORT_JOB_ID ASC ";
		
		logger.debug("SQL query: " + sqlQuery);
		Map<String, ReportListRecord> recordMap = new HashMap<String, ReportListRecord>();
		ReportListRecord record = null;
		try {
			SQLQuery query = HibernateUtil.getSession().createSQLQuery(sqlQuery);
			 query.setParameter("usernumber", contract.getUserNumber());
			 query.setParameter("local", LocaleUtil.getSupportLocaleCode(contract.getLocale()));
			 query.setParameter("mdmId", contract.getMdmId());
			 query.setParameter("mdmLevel", contract.getMdmLevel());
			 
			 logger.debug("contract.getUserNumber(): "+contract.getUserNumber());
			 logger.debug("contract.getLocale() : "+LocaleUtil.getSupportLocaleCode(contract.getLocale()));
			 logger.debug("contract.getMdmId() : "+contract.getMdmId());
			 logger.debug("contract.getMdmLevel() : "+contract.getMdmLevel());
			 
			List rows = query.list();
			Iterator it = rows.iterator();
			while (it.hasNext()) {
				Object[] row = (Object[]) it.next();
				record = new ReportListRecord();
				record.setReportScheduleId(String.valueOf(row[0]));
				record.setBatchId(String.valueOf(row[1]));
				record.setFilename(String.valueOf(row[2]));
				record.setStatus(String.valueOf(row[3]));
				record.setFileType(String.valueOf(row[6]));

				if (row[9] != null) {
					record.setReportType((String) row[9]);
				}
				if ("BO".equalsIgnoreCase(record.getReportType())) {

					if (record.getFileType().equalsIgnoreCase("XLS")) {
						if (row[4] != null) {
							record.setDocumentId(String.valueOf(row[4]));
						}
					} else {
						if (row[4] != null) {
							record.setDocumentId2(String.valueOf(row[4]));
						}
					}
				} else {
					if (row[4] != null) {
						record.setDocumentId(String.valueOf(row[4]));
					}
				}
				try {
					record.setFileSize(Integer.parseInt(String.valueOf(row[5])));
				} catch (Exception e) {
					record.setFileSize(0);
				}

				if(row[7]!= null){
					String createdDateString = row[7].toString();
					Date createdDate = dateformatter.parse(createdDateString);
					record.setCreatedDate(createdDate);
					}
					if(row[8]!=null){
					String scheduleCreatedDateString = row[8].toString();
					Date schedulecreatedDate = dateformatter.parse(scheduleCreatedDateString);
					record.setScheduleCreateDate(schedulecreatedDate);
					}
				if (row[10] != null) {
					record.setParameterList((String) row[10]);
				}

				try {
					record.setReportJobId(Integer.parseInt(String.valueOf(row[11])));
				} catch (Exception e) {
					record.setReportJobId(0);
				}

				if (record.getReportType().equalsIgnoreCase("BO")) {
					recordMap = populateRecordMap(recordMap, record);
				} else {
					reportListOutput.getReportListRows().add(record);
				}
			}
			Set<Entry<String, ReportListRecord>> entries = recordMap.entrySet();
			for (Entry<String, ReportListRecord> entry : entries) {
				reportListOutput.getReportListRows().add(entry.getValue());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new InfrastructureException(ex);
		} finally {
			HibernateUtil.closeSession();
		}
		return reportListOutput;
	}

	@Override
	public void deleteReportOrDocument(DocumentDeleteContract contract) {
		try {
			logger.debug("in......deleteReportOrDocument: ");
			HibernateUtil.beginTransaction();
			String sqlQuery = "SELECT REPORT_TYPE,BATCH_ID,DOCUMENT_ID FROM REPORT_JOB WHERE REPORT_JOB_ID = :reportJobId";
			logger.debug("sqlQuery : " + sqlQuery);
			Query query = HibernateUtil.getSession().createSQLQuery(sqlQuery);
			query.setParameter("reportJobId", contract.getReportJobId());
			List rows = query.list();
			logger.debug("rows.size: " + rows.size());
			Iterator it = rows.iterator();
			while (it.hasNext()) {
			
				Object[] row = (Object[]) it.next();
			
				contract.setReportType((String) row[0]);
			
				try {
					contract.setBatchId(String.valueOf(row[1]));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		
			logger.debug("contract.getReportJobId()" + contract.getReportJobId());
		
			logger.debug("contract.getReportType()" + contract.getReportType());
			String key = "";
			if ("BO".equals(contract.getReportType())) {
			
				sqlQuery = "UPDATE REPORT_JOB SET IS_DELETED = 'T' WHERE BATCH_ID =:key";
				key = contract.getBatchId();
			
			} else {
			
				sqlQuery = "UPDATE REPORT_JOB SET IS_DELETED = 'T' WHERE REPORT_JOB_ID =:key";
				key = contract.getReportJobId();
			
			}
			logger.debug("sqlQuery : " + sqlQuery);
			Query updateQuery = HibernateUtil.getSession().createSQLQuery(sqlQuery);
			updateQuery.setParameter("key", key);
			updateQuery.executeUpdate();
			HibernateUtil.commitTransaction();
		} catch (HibernateException e) {
			e.printStackTrace();
			if (logger.isDebugEnabled()) {
				logger.debug(e.getMessage());
			}
			throw e;
		} finally {
			HibernateUtil.closeSession();
		}

	}

	@Override
	public ReportListOutput retrieveScheduledReportsListforAdminRerun(ReportInstanceListContract contract) throws ParseException { // Added
		ReportListOutput reportListOutput = new ReportListOutput();
		Map<String, ReportListRecord> recordMap = new HashMap<String, ReportListRecord>();
		String sql;
		SQLQuery runBatchIds;
		if(contract.getReportDefinitionName()!=null ){
			sql = "select TO_CHAR( j.CREATED_DATE, 'DD-MM-YYYY HH24:MI:SS'), rs.RECEPIENT_EMAIL, rd.REPORT_DEFINITION_ID, j.STATUS_CODE, j.ERROR_LOG, j.BATCH_ID, rd.name as report_name, j.document_id,j.FILE_TYPE,j.report_type,rs.REPORT_SCHEDULE_ID FROM REPORT_JOB  j, REPORT_DEFINITION rd ,REPORT_SCHEDULE  rs WHERE j.REPORT_SCHEDULE_ID = rs.REPORT_SCHEDULE_ID AND rs.REPORT_DEFINITION_ID = rd.REPORT_DEFINITION_ID AND TRIM(rd.NAME) = :rdName AND rd.DEFINITION_TYPE='REPORT' AND j.IS_DELETED = 'F' And  j.CREATED_DATE BETWEEN TO_DATE(:startDate, 'DD-MM-YYYY HH24:MI:SS') AND TO_DATE(:endDate, 'DD-MM-YYYY HH24:MI:SS')";
			 runBatchIds = HibernateUtil.getSession().createSQLQuery(sql);
			 runBatchIds.setParameter("rdName", contract.getReportDefinitionName());
			runBatchIds.setParameter("startDate", contract.getStartDate());
			runBatchIds.setParameter("endDate", contract.getEndDate());
		
			logger.debug(sql);
		}
		else{
		sql = "select TO_CHAR( j.CREATED_DATE, 'DD-MM-YYYY HH24:MI:SS'), rs.RECEPIENT_EMAIL, rd.REPORT_DEFINITION_ID, j.STATUS_CODE, j.ERROR_LOG, j.BATCH_ID, rd.name as report_name, j.document_id,j.FILE_TYPE,j.report_type,rs.REPORT_SCHEDULE_ID FROM REPORT_JOB  j, REPORT_DEFINITION rd ,REPORT_SCHEDULE  rs WHERE j.REPORT_SCHEDULE_ID = rs.REPORT_SCHEDULE_ID AND rs.REPORT_DEFINITION_ID = rd.REPORT_DEFINITION_ID AND rd.DEFINITION_TYPE='REPORT' AND j.IS_DELETED = 'F' And j.CREATED_DATE BETWEEN TO_DATE(:startDate, 'DD-MM-YYYY HH24:MI:SS') AND TO_DATE(:endDate, 'DD-MM-YYYY HH24:MI:SS')";
		runBatchIds = HibernateUtil.getSession().createSQLQuery(sql);
		SimpleDateFormat dateformatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		runBatchIds.setParameter("startDate", dateformatter.format(contract.getStartDate()));
		runBatchIds.setParameter("endDate", dateformatter.format(contract.getEndDate()));	
	
		logger.debug(sql);
		}
		
		List list = runBatchIds.list();
		Iterator it = list.iterator();
		ReportListRecord record = null;

		while (it.hasNext()) {
			record = new ReportListRecord();
			Object[] row = (Object[]) it.next();
			if (row[0] != null) {
				String createdDateString = row[0].toString();
				SimpleDateFormat dateformatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
				Date createdDate = dateformatter.parse(createdDateString);
				record.setCreatedDate(createdDate);
			}
			if (row[1] != null) {
				record.setUserEmail((String) row[1]);
			}
			if (row[2] != null) {
				try {					
					record.setReportDefinitionId(Integer.parseInt(String.valueOf(row[2])));
				} catch (Exception e) {
					e.printStackTrace();
					record.setReportDefinitionId(0);
				}
			}
			if (row[3] != null) {
				record.setStatus((String) row[3]);
			}
			if (row[4] != null) {
				record.setErrorMessage((String) row[4]);
			}
			if (row[5] != null) {
				record.setBatchId(String.valueOf(row[5]));
			}
			if (row[6] != null) {
				record.setReportName((String) row[6]);
				
			}
			if (row[8] != null) {
				record.setFileType((String) row[8]);
			}
			if (row[9] != null) {
				record.setReportType((String) row[9]);
			}
			if(row[10]!=null){
				record.setReportScheduleId(String.valueOf(row[10]));
			}
		
			if ("BO".equalsIgnoreCase(record.getReportType())) {
				if (record.getFileType().equalsIgnoreCase("XLS")) {
					if (row[7] != null) {
						record.setDocumentId(String.valueOf(row[7]));
					}
				} else {
					if (row[7] != null) {
						record.setDocumentId2(String.valueOf(row[7]));
					}
				}
			} else {
				if (row[7] != null) {
					record.setDocumentId((String) row[7]);
				}
			}

			recordMap = populateRecordMap(recordMap, record);
		}
		Set<Entry<String, ReportListRecord>> entries = recordMap.entrySet();
		for (Entry<String, ReportListRecord> entry : entries) {
			reportListOutput.getReportListRows().add(entry.getValue());
		}
	//	logger.debug("reportListOutput.getReportListRows().size() : "+reportListOutput.getReportListRows().size());
		return reportListOutput;
	}

	@Override
	public ScheduleReportContract retriveRerunContract (String batchId) {
		HibernateUtil.beginTransaction();

		String scheduleId = getScheduleIdforBatchId(batchId);
		logger.debug("scheduleId: " + scheduleId);
		ScheduleReportContract contract = new ScheduleReportContract();
		ReportSchedule schedule = null;
		Query query = HibernateUtil.getSession().createQuery("from ReportSchedule where id = :id");
		query.setInteger("id", Integer.parseInt(scheduleId));
		List results = query.list();
		if (results.size() > 0) {
			schedule = (ReportSchedule) results.get(0);

			ReportSchedule scheduleForNunNow = new ReportSchedule();

			scheduleForNunNow.setUserNumber(schedule.getUserNumber());

			scheduleForNunNow.setRecepientEmail(schedule.getRecepientEmail());
			scheduleForNunNow.setRunFrequency("O");

			scheduleForNunNow.setRunInterval(1); // for run now
			scheduleForNunNow.setEffectiveDate(new Date());

			scheduleForNunNow.setExpirationDate(schedule.getExpirationDate());
			scheduleForNunNow.setDayOfMonth(schedule.getDayOfMonth());
			scheduleForNunNow.setDayOfWeek(schedule.getDayOfWeek());
			scheduleForNunNow.setReportDefinitionId(schedule.getReportDefinitionId());
			scheduleForNunNow.setPreferedTimezone(schedule.getPreferedTimezone());
			scheduleForNunNow.setCountry(schedule.getCountry());
			scheduleForNunNow.setEmailReminderFlag(schedule.getEmailReminderFlag());
			scheduleForNunNow.setCustomLeadMinutes(schedule.getCustomLeadMinutes());
			scheduleForNunNow.setMdmId(schedule.getMdmId());
			scheduleForNunNow.setMdmLevel(schedule.getMdmLevel());
			scheduleForNunNow.setIsLBSAccount(schedule.getIsLBSAccount());

			contract.setReportSchedule(scheduleForNunNow);
			List<ReportScheduleParameter> parameterList = getPrarameterListFromSchedule(scheduleId);
			contract.setParameterValues(parameterList);			
		}

		return contract;
	}

	private List<ReportScheduleParameter> getPrarameterListFromSchedule(String scheduleId) {
		Map<String,String> parameterMap = new HashMap<String,String>();
		List<ReportScheduleParameter> reportScheduleParameters = new ArrayList<ReportScheduleParameter>();
		String sql = "select report_schedule_id, name,value from schedule_instance_parameters where REPORT_SCHEDULE_ID = :scheduleId";
		logger.debug(sql);
		SQLQuery query = HibernateUtil.getSession().createSQLQuery(sql);
		query.setParameter("scheduleId", scheduleId);
		
		List rows = query.list();
		if(rows.size() > 0){
			Iterator it = rows.iterator();
			while(it.hasNext()){
				String key = "";
				String value = "";
				Object[] row = (Object[])it.next();
				if(row[1] != null){
					key = (String)row[1];
				}
				
				if(row[2] != null){
					value = (String)row[2];
				}
				
				parameterMap.put(key, value);
			}	
			
			ReportScheduleParameter reportScheduleParameter = null;
			for(Entry<String, String> entry: parameterMap.entrySet()){
				reportScheduleParameter = new ReportScheduleParameter();
				reportScheduleParameter.setName(entry.getKey());
				reportScheduleParameter.setValue(entry.getValue());
				reportScheduleParameters.add(reportScheduleParameter);
			}
		}		
		return reportScheduleParameters;
	}

	private String getScheduleIdforBatchId(String batchId) {
		String sql = "select DISTINCT report_schedule_id from report_job where batch_id = :batchId";
		logger.debug(sql);
		SQLQuery query = HibernateUtil.getSession().createSQLQuery(sql);
		query.setParameter("batchId", batchId);
		String scheduleId = null;
		try {
			scheduleId = String.valueOf(query.list().get(0));
		} catch (Exception e) {
			scheduleId = null;
		}
		return scheduleId;
	}

	@Override
	public HashMap<String, String> getReportInfoMap(String id) {
		HashMap<String, String> reportInfoMap = new HashMap<String,String>();
		try {
			SQLQuery query = HibernateUtil.getSession().createSQLQuery(SQL_GET_BO_FILENAME_PARAMETERS);
			query.setParameter("documentId", id);
			List list = query.list();
			if (list != null && list.size() == 1) {
				Object[] row = (Object[]) list.get(0);
			String createdDate = String.valueOf(row[0]);
			String definitionName = String.valueOf(row[1]);
			String batchId = String.valueOf(row[2]);
			String fileType = String.valueOf(row[3]);
			String reportType = String.valueOf(row[4]);
			
			reportInfoMap.put("createdDate", createdDate);
			reportInfoMap.put("definitionName", definitionName);
			reportInfoMap.put("batchId", batchId);
			reportInfoMap.put("fileType", fileType);
			reportInfoMap.put("reportType", reportType);
			}

			return reportInfoMap;
		} catch (HibernateException ex) {
			ex.printStackTrace();
			throw new InfrastructureException(ex);
		} finally {
			HibernateUtil.closeSession();
		}
		
		
	}

	@Override
	public String getFileName(HashMap<String, String> reportInfoMap) {
		StringBuilder sb = new StringBuilder();
		sb.append(reportInfoMap.get("createdDate")).append(" ").append(reportInfoMap.get("definitionName")).append("-").append(reportInfoMap.get("batchId")).append(".").append(reportInfoMap.get("fileType"));
		return sb.toString();
	}
}
