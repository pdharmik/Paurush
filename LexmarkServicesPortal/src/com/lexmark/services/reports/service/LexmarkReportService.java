package com.lexmark.services.reports.service;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;

import com.lexmark.contract.DocumentDeleteContract;
import com.lexmark.contract.ReportInstanceListContract;
import com.lexmark.contract.ReportListContract;
import com.lexmark.contract.ScheduleReportContract;
import com.lexmark.result.ScheduleReportResult;
import com.lexmark.services.reports.bean.DocDelDownloadInfo;
import com.lexmark.services.reports.bean.DocListInfo;
import com.lexmark.services.reports.bean.DocumentInformation;
import com.lexmark.services.reports.bean.DocumentBean;
import com.lexmark.services.reports.bean.ReportListOutput;
import com.lexmark.services.reports.bean.ScheduleReportInfoBean;

/**
 * @author Wipro
 *
 */
public interface LexmarkReportService {
	
	public abstract String createDocument(DocumentInformation docInfo) throws Exception;

	public abstract List<DocumentBean> listDocumentByDefinitionId(DocListInfo docListInfo) throws Exception;

	public abstract void deleteDocument(DocDelDownloadInfo docDelDownloadInfo) throws Exception;

	public abstract HttpResponse downloadDocument(DocDelDownloadInfo docDelDownloadInfo) throws Exception;

	public abstract void saveManualReport(String docId,
			double fileSize,Date date, DocumentInformation docInfo);
	
	public ReportListOutput retrieveManuUploadedReportsList(ReportListContract contract) throws Exception;
	
	public abstract void deleteReport(String docId);
	
	public String getJobBatchId();

	public abstract void saveReportInfo(ScheduleReportInfoBean scBean, ScheduleReportResult result, String jobBatchId);

	public ScheduleReportInfoBean setReportAttributes(Integer id , ScheduleReportInfoBean scheduleReportInfoBean);

	public abstract ScheduleReportInfoBean scheduleReport(ScheduleReportInfoBean scheduleReportInfoBean) throws Exception ;

	public abstract void saveScheduleInstanceParameters(String scheduleId, Map<String, String> parameterMap);

	public abstract ReportListOutput retrieveScheduledReportsListEmployee(ReportListContract contract);

	public abstract ReportListOutput retrieveScheduledReportsList(ReportListContract contract);

	public abstract ReportListOutput retrieveTopTenReportsList(ReportListContract contract);

	public abstract ReportListOutput retrieveTopTenReportsListEmployee(ReportListContract contract);

	public abstract void deleteReportOrDocument(DocumentDeleteContract contract);

	public abstract ReportListOutput retrieveScheduledReportsListforAdminRerun(ReportInstanceListContract contract)throws ParseException;

	public abstract ScheduleReportContract retriveRerunContract(String batchId);

	public abstract HashMap<String, String> getReportInfoMap(String string);

	public abstract String getFileName(HashMap<String, String> reportInfoMap);

}
