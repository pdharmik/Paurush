package com.lexmark.services.portlet;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.portlet.PortletRequest;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.lexmark.constants.LexmarkConstants;
import com.lexmark.contract.ReportInstanceListContract;
import com.lexmark.contract.ScheduleReportContract;
import com.lexmark.domain.Report;
import com.lexmark.domain.ReportScheduleParameter;
import com.lexmark.result.ReportDefinitionNameListResult;
import com.lexmark.result.ReportInstanceListResult;
import com.lexmark.result.ScheduleReportResult;
import com.lexmark.service.api.CustomerReportService;
import com.lexmark.services.reports.bean.ReportListOutput;
import com.lexmark.services.reports.bean.ReportListRecord;
import com.lexmark.services.reports.bean.ScheduleReportInfoBean;
import com.lexmark.services.reports.enums.FormatTypeEnum;
import com.lexmark.services.reports.service.LexmarkReportService;
import com.lexmark.services.reports.service.LexmarkReportServiceImpl;
import com.lexmark.services.reports.util.LexmarkReportUtil;
import com.lexmark.services.util.DocumentumWebServiceUtil;
import com.lexmark.services.util.ServiceStatusUtil;
import com.lexmark.util.StringUtil;
import com.lexmark.util.TimezoneUtil;
import com.liferay.portal.kernel.util.JavaConstants;

@Controller
@RequestMapping("VIEW")
public class ReportSchedulerMaintenanceController extends BaseController{
	
	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy");
	
	@Autowired
	private CustomerReportService customerReportService;

	private LexmarkReportService reportService;

	@RequestMapping
	public String getReportList(Model model, RenderRequest request,
			RenderResponse response) throws Exception {
		ReportDefinitionNameListResult result = customerReportService.retrieveReportDefinitionNameList();
		List<String> nameList = result.getReportDefinitionNameList();
		model.addAttribute("nameList",nameList);
		
		PortletRequest portletRequest = (PortletRequest)request.getAttribute(JavaConstants.JAVAX_PORTLET_REQUEST);
		PortletSession portletSession = portletRequest.getPortletSession();
        if (portletSession.getAttribute("definitionName", PortletSession.APPLICATION_SCOPE) != null) {
        	model.addAttribute("definitionName", String.valueOf(portletSession.getAttribute("definitionName", PortletSession.APPLICATION_SCOPE)));
		}
		return "customerReportsAdmin/schedulerMaintenance";
	}
	
	/**
	 * @param model 
	 * @param request 
	 * @param response 
	 * @return String 
	 * @throws Exception 
	 */
	@RequestMapping(params = "action=runLogParametersLightBox")
	public String showRunLogParametersLightBox(Model model, RenderRequest request,
			RenderResponse response) throws Exception {
		int runLogID = Integer.parseInt(request.getParameter("runLogID"));
		Map<String,String> parameters = customerReportService.retrieveRunLogParameterList(runLogID);
		model.addAttribute("parameters",parameters);
		return "customerReportsAdmin/parameterDetails";
	}
	
	/**
	 * @param request 
	 * @param response  
	 * @throws Exception 
	 */
	@ResourceMapping
	public void retrieveReportList(ResourceRequest request, ResourceResponse response) throws Exception{
		String strBeginDate = request.getParameter("beginDate");
		String strEndDate = request.getParameter("endDate");
		String strTimezone = request.getParameter("timezone");
		float timezone=0;
		if (StringUtil.isStringEmpty(strTimezone)) {
			timezone = 0;
		} else {
			try{
			timezone = Float.parseFloat(strTimezone);
			}
			catch(NumberFormatException e){
				e.printStackTrace();
			}
		}
		if (timezone < -12 || timezone > 12) {
			timezone = 0;
		}
		String definitionName = request.getParameter("definitionName");
		
		try{
			
		ReportInstanceListContract contract = new ReportInstanceListContract();
		if (strBeginDate!=null&&strEndDate!=null) {
			Date beginDate;
			Date endDate;
			beginDate = DATE_FORMAT.parse(strBeginDate);
			TimezoneUtil.adjustDate(beginDate, timezone);
			// HH:mm:ss =  00:00
			contract.setStartDate(beginDate);
			
			endDate = DATE_FORMAT.parse(strEndDate);
			// HH:mm:ss =  23:59:59
			endDate.setTime(endDate.getTime() + LexmarkConstants.MILLISECONDS_IN_HOUR * 24 - 1000);
			TimezoneUtil.adjustDate(endDate,  timezone);
			contract.setEndDate(endDate);
		
		} else {
			contract.setStartDate(getDefaultBeginDate());
			Date end = getDefaultBeginDate();
			end.setTime(end.getTime() + LexmarkConstants.MILLISECONDS_IN_HOUR * 24 - 1000);
			contract.setEndDate(end);
		}
		PortletRequest portletRequest = (PortletRequest)request.getAttribute(JavaConstants.JAVAX_PORTLET_REQUEST);
		PortletSession portletSession = portletRequest.getPortletSession();
		if (portletSession.getAttribute("definitionName", PortletSession.APPLICATION_SCOPE) != null) {
			definitionName = String.valueOf(portletSession.getAttribute("definitionName", PortletSession.APPLICATION_SCOPE));
			portletSession.removeAttribute("definitionName", PortletSession.APPLICATION_SCOPE);
		}
		if (!StringUtil.isStringEmpty(definitionName)){
			contract.setReportDefinitionName(definitionName);
		}
		ReportListOutput result = null;
			reportService = getLexmarkReportServiceImpl();
			result = reportService.retrieveScheduledReportsListforAdminRerun(contract);
			List<ReportListRecord> reportList = result.getReportListRows();
			LexmarkReportUtil.adjustReportsDate(result.getReportListRows(), -timezone);
			LexmarkReportUtil.sortReportListWithSchesuleDate(result.getReportListRows());
			int totalCount = reportList.size();
			String xmlContent = getXmlOutputGenerator(request.getLocale()).convertReportsToXML(result, totalCount, 0);
			PrintWriter out = response.getWriter();
			response.setContentType("text/xml");
			out.print(xmlContent);
			out.flush();
			out.close();
		}
		catch(Exception e){
			e.printStackTrace();

		}
	}

	LexmarkReportService getLexmarkReportServiceImpl() {
		if (reportService == null) {
			reportService = new LexmarkReportServiceImpl();
		}
		return reportService;
	}
	
	/**
	 * @return Date 
	 */
	private Date getDefaultBeginDate() {
		Calendar calendarDateTime = Calendar.getInstance();
		calendarDateTime.set(Calendar.HOUR_OF_DAY, 0);
		calendarDateTime.set(Calendar.MINUTE, 0);
		calendarDateTime.set(Calendar.SECOND, 0);
		
		return calendarDateTime.getTime();
	}
	
	/**
	 * @param definitionId 
	 * @param request 
	 * @param response 
	 * @throws Exception 
	 */
	@ResourceMapping("rerunScheduleMaintenance")
	public void rerunScheduleMaintenance(@RequestParam("batchId") String batchId, ResourceRequest request, ResourceResponse response) throws Exception {
		
		boolean success = false;
		try {
			ScheduleReportContract contract = getLexmarkReportServiceImpl().retriveRerunContract(batchId);
			ScheduleReportResult scheduleReportResult = customerReportService.saveReportSchedule(contract);
			

			
		
			ScheduleReportInfoBean scheduleReportInfoBean = new ScheduleReportInfoBean();

			scheduleReportInfoBean = getLexmarkReportServiceImpl().setReportAttributes(scheduleReportResult.getSchedule().getId(), scheduleReportInfoBean);

			// Batch Id will be same for pdf and excel
			String jobBatchId = getLexmarkReportServiceImpl().getJobBatchId();

			scheduleReportInfoBean.setFormatType(FormatTypeEnum.EXCEL.getValue());
			scheduleReportInfoBean = LexmarkReportUtil.getScheduleReportInfoBeanForRerun(scheduleReportInfoBean, scheduleReportResult);
			ScheduleReportInfoBean respBean = getLexmarkReportServiceImpl().scheduleReport(scheduleReportInfoBean);
			respBean.setFormatType(scheduleReportInfoBean.getFormatType());
			respBean.setRepDefName(scheduleReportInfoBean.getRepDefName());
			getLexmarkReportServiceImpl().saveReportInfo(respBean, scheduleReportResult, jobBatchId);
			scheduleReportInfoBean.setFormatType(FormatTypeEnum.PDF.getValue());			
			respBean = getLexmarkReportServiceImpl().scheduleReport(scheduleReportInfoBean);
			respBean.setFormatType(scheduleReportInfoBean.getFormatType());
			respBean.setRepDefName(scheduleReportInfoBean.getRepDefName());
			getLexmarkReportServiceImpl().saveReportInfo(respBean, scheduleReportResult, jobBatchId);
			if (scheduleReportInfoBean.getParameterMap().size() > 0) {
				getLexmarkReportServiceImpl().saveScheduleInstanceParameters(scheduleReportInfoBean.getScheduleId(), scheduleReportInfoBean.getParameterMap());
			}
			
			success = true;
		} catch (Exception e) {
			e.printStackTrace();
			success = false;
		}
		String errorCode = success ? "message.reportAdmin.rerunScheduleMaintenance" : "exception.reportAdmin.rerunScheduleMaintenance";
		ServiceStatusUtil.responseResult(response, errorCode, request.getLocale());
	}

	/**
	 * Adjust run date of report based on client time zone.
	 * 
	 * @param reportList
	 * @param timezone
	 */
	private void adjustReportsRunDate(List<Report> reportList, float timezone) {
		for (Report report : reportList) {
			Date runTime = report.getRunTime();
			TimezoneUtil.adjustDate(runTime, timezone - TimezoneUtil.getServerTimeZone());
			report.setRunTime(runTime);
		}
	}
}
